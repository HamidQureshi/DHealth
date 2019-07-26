import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { TransactionHandler, IBaseTransaction } from '@activeledger/sdk';
import { Report } from '../model/report';
import { LedgerHelper } from 'src/app/helper/ledgerhelper';
import { Router } from '@angular/router';
import { LayoutComponent } from '../layout.component';

@Component({
    selector: 'app-report',
    templateUrl: './add-report.component.html',
    styleUrls: ['./add-report.component.scss']
})
export class AddReportComponent implements OnInit {

    reports: Array<Report> = [];

    report = this.ledgerHelper.reports; 

    dropdownList = [];
    selectedItems = [];
    selectedDoctorsID : Array<String> =[];
    dropdownSettings = {};

    filename: string;
    fileBase64: string;

    constructor(private layoutComp: LayoutComponent, private ledgerHelper: LedgerHelper, private router: Router,
        private http: HttpClient) {
    }

    ngOnInit() {
        (<HTMLInputElement>document.getElementById('i_pn')).value = this.ledgerHelper.full_name;

        const doctorlist = JSON.parse(this.ledgerHelper.userList);

        for (let i = 0; i < doctorlist.length ; i++){
            const item = { item_id: doctorlist[i]._id, item_text: '' + doctorlist[i].first_name + ' ' + doctorlist[i].last_name};
            this.dropdownList.push(item);
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


    onAddReport() {

        const id = this.ledgerHelper._id;

        const headers = new HttpHeaders({
            'Content-Type': 'application/json',
            'Authorization': this.ledgerHelper.token
        });

        for(let i =0; i < this.selectedItems.length ; i++){
            this.selectedDoctorsID.push(this.selectedItems[i].item_id);
        }


        const report: Report = {
            id: '' + (JSON.parse(this.ledgerHelper.reports).length + 1),
            title: (<HTMLInputElement>document.getElementById('i_rt')).value,
            patientName: this.ledgerHelper.full_name,
            fileName: this.filename,
            description: (<HTMLInputElement>document.getElementById('i_description')).value,
            content: this.fileBase64,
            doctors: this.selectedDoctorsID,

        };

        this.reports.push(report);

        let baseTransaction1: IBaseTransaction = {
            $tx: {
                $contract: 'report',
                $namespace: 'health',
                $entry: 'upload',
                $i: {},
                $o: {},
            },
            $selfsign: false,
            $sigs: {}
        };


        baseTransaction1.$tx.$i[id] = {};
        baseTransaction1.$tx.$o[id] = this.reports;

        console.log(JSON.stringify(baseTransaction1));

        const txHandler = new TransactionHandler();

        txHandler
            .signTransaction(baseTransaction1, this.ledgerHelper.key )
            .then((signedTx: IBaseTransaction) => {

                baseTransaction1 = signedTx;
                const signature = baseTransaction1['$sigs']['activeledger']
                baseTransaction1['$sigs'] = {};
                baseTransaction1['$sigs'][id] = signature;

                console.log(baseTransaction1);

                this.addReportAPI(JSON.stringify(baseTransaction1), headers)
                    .subscribe(data => {
                        console.log(data);

                        if (data.status === 200) {
                           
                            this.layoutComp.showSnackBar('Report Update Successfully!');

                            //update the report in pref
                            this.reports = JSON.parse(this.ledgerHelper.reports);
                            this.reports.push(report);
                            this.ledgerHelper.reports =  JSON.stringify(this.reports);

                            this.router.navigate(['/report-list'], { replaceUrl: true });

                        } else {
                            this.layoutComp.showSnackBar(' Report Update  Failed! ');
                        }
                    });
            })
            .catch();
    }


    fileChange(event) {
        const fileList: FileList = event.target.files;
        if (fileList.length > 0) {
            const file: File = fileList[0];
            const reader = new FileReader();
            reader.readAsBinaryString(file);

            (<HTMLInputElement>document.getElementById('i_da')).value = file.name;
            this.filename = file.name;

            reader.onload = (e: any) => {
                const binaryData = e.target.result;
                const base64String = window.btoa(binaryData);
                this.fileBase64 = base64String;
            }

        }
    }

    addReportAPI(body, header) {
        return this.http.post<any>(this.ledgerHelper.addReportURL, body, { headers: header, observe: 'response' });
    }

}
