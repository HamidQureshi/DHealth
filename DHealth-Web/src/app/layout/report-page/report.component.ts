import { Component, OnInit, ViewChild } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { Report } from '../model/report';
import { TransactionHandler, IBaseTransaction } from '@activeledger/sdk';
import { LedgerHelper } from 'src/app/helper/ledgerhelper';
import { Router } from '@angular/router';
import { LayoutComponent } from '../layout.component';

@Component({
    selector: 'app-report',
    templateUrl: './report.component.html',
    styleUrls: ['./report.component.scss']
})
export class ReportComponent implements OnInit {

    reports: Array<Report> = [];

    report = JSON.parse(this.ledgerHelper.report);

    dropdownList = [];
    selectedItems = [];
    selectedDoctorsID: Array<String> = [];
    dropdownSettings = {};

    cannot_assign_patients = false;

    constructor(private layoutComp: LayoutComponent, private ledgerHelper: LedgerHelper, private http: HttpClient,
        private router: Router) {
    }

    ngOnInit() {

        (<HTMLInputElement>document.getElementById('i_pn')).value = this.report.patientName;
        (<HTMLInputElement>document.getElementById('i_rt')).value = this.report.title;
        (<HTMLInputElement>document.getElementById('i_description')).value = this.report.description;
        (<HTMLInputElement>document.getElementById('i_da')).value = this.report.fileName;

        const doctorlist = JSON.parse(this.ledgerHelper.userList);
        const selected = this.report.doctors;

        if (this.ledgerHelper.profile_type === 'Doctor') {
            this.cannot_assign_patients = true;
            return;
        }

        for (let i = 0; i < doctorlist.length ; i++){
            const item = { item_id: doctorlist[i]._id, item_text: '' + doctorlist[i].first_name + ' ' + doctorlist[i].last_name};
            this.dropdownList.push(item);

            for (let j = 0; j < selected.length; j++) {

                if (doctorlist[i]._id === selected[j]){
                    const item = { item_id: doctorlist[i]._id, item_text: '' + doctorlist[i].first_name + ' ' + doctorlist[i].last_name };
                    this.selectedItems.push(item);
                }
            }
        }

        this.dropdownSettings = {
            singleSelection: false,
            idField: 'item_id',
            textField: 'item_text',
            selectAllText: 'Select All',
            unSelectAllText: 'UnSelect All',
            // itemsShowLimit: 3,
            allowSearchFilter: true
        };

    }

    onItemSelect(item: any) {
    }
    onSelectAll(items: any) {
    }


    showPDF() {
        const linkSource = 'data:application/pdf;base64,' + this.report.content;
        const downloadLink = document.createElement('a');
        const fileName = this.report.fileName;

        downloadLink.href = linkSource;
        downloadLink.download = fileName;
        downloadLink.click();
    }

    onUpdateReport() {

        const id = this.ledgerHelper._id; 
        const headers = new HttpHeaders({
            'Content-Type': 'application/json',
            'Authorization': '' + this.ledgerHelper.token
        });

        for(let i =0; i < this.selectedItems.length ; i++){
            this.selectedDoctorsID.push(this.selectedItems[i].item_id);
        }

        const report = { 

            id: this.report.id,
            title: this.report.title,
            patientName: this.report.patientName,
            fileName: this.report.fileName,
            description: this.report.description,
            content: this.report.content ,
             doctors: this.selectedDoctorsID};

        let reports = []; 
        reports.push(report);

         let baseTransaction1: IBaseTransaction = {
            $tx: {
                $contract: 'report',
                $namespace: 'health',
                $entry: 'update',
                $i: {},
                $o: {},
            },
            $selfsign: false,
            $sigs: {}
        };

        baseTransaction1.$tx.$i[id] = {};
        baseTransaction1.$tx.$o[id] = reports;


        console.log(JSON.stringify(baseTransaction1));

        const txHandler = new TransactionHandler();

        txHandler
            .signTransaction( baseTransaction1, this.ledgerHelper.key )
            .then((signedTx: IBaseTransaction) => {


                baseTransaction1 = signedTx;
                const signature = baseTransaction1['$sigs']['activeledger']
                baseTransaction1['$sigs'] = {};
                baseTransaction1['$sigs'][id] = signature;

                console.log(baseTransaction1);

                 this.updateReportAPI(baseTransaction1, headers)
            .subscribe(data => {
                console.log(data);

                if (data.status === 200) {
                    this.layoutComp.showSnackBar('Report Update Successfully!');

                    this.reports = JSON.parse(this.ledgerHelper.reports);

                    this.reports.find(item => item.id == this.report.id).doctors = this.selectedDoctorsID;


                            this.ledgerHelper.reports =  JSON.stringify(this.reports);

                            this.router.navigate(['/report-list'], { replaceUrl: true });

                } else {
                    this.layoutComp.showSnackBar(' Report Update  Failed! ');
                }
            });

            })
            .catch();
    }

    updateReportAPI(body, header) {
        return this.http.post<any>(this.ledgerHelper.updateReportURL, body, { headers: header, observe: 'response' });
    }

}
