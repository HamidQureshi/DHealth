import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator, MatSort, MatTableDataSource } from '@angular/material';
import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { MatSnackBar, MatSnackBarConfig, MatSnackBarHorizontalPosition, MatSnackBarVerticalPosition } from '@angular/material';
import { Report } from '../model/report';
import { PersistenceService, StorageType } from 'angular-persistence';
import { KeyHandler, IKey, KeyType, TransactionHandler, IBaseTransaction } from '@activeledger/sdk';
import { LedgerHelper } from 'src/app/helper/ledgerhelper';
import { Router } from '@angular/router';



@Component({
    selector: 'app-report',
    templateUrl: './report.component.html',
    styleUrls: ['./report.component.scss']
})
export class ReportComponent implements OnInit {


    message: string = 'Snack Bar opened.';
    actionButtonLabel: string = 'Close';
    action: boolean = true;
    setAutoHide: boolean = true;
    autoHide: number = 2000;
    horizontalPosition: MatSnackBarHorizontalPosition = 'center';
    verticalPosition: MatSnackBarVerticalPosition = 'bottom';
    addExtraClass: boolean = false;

    reports: Array<Report> = [];

    report = JSON.parse(this.ledgerHelper.report); 

    dropdownList = [];
    selectedItems = [];
    selectedDoctorsID : Array<String> =[];
    dropdownSettings = {};

    cannot_assign_patients = false;

    constructor(private ledgerHelper: LedgerHelper, private http: HttpClient,
        private router: Router, public snackBar: MatSnackBar) {
    }

    ngOnInit() {

        (<HTMLInputElement>document.getElementById('i_pn')).value = this.report.patientName;
        (<HTMLInputElement>document.getElementById('i_rt')).value = this.report.title;
        (<HTMLInputElement>document.getElementById('i_description')).value = this.report.description;
        (<HTMLInputElement>document.getElementById('i_da')).value = this.report.fileName;


        var doctorlist = JSON.parse(this.ledgerHelper.userList);
        var selected = this.report.doctors;


        if (this.ledgerHelper.profile_type === 'Doctor') {
            this.cannot_assign_patients = true;
            return;
        }

        for (let i = 0; i < doctorlist.length ; i++){
            var item = { item_id: doctorlist[i]._id, item_text: '' + doctorlist[i].first_name + ' ' + doctorlist[i].last_name};
            this.dropdownList.push(item);

            for (let j = 0; j < selected.length; j++) {

                if (doctorlist[i]._id === selected[j]){
                    var item = { item_id: doctorlist[i]._id, item_text: '' + doctorlist[i].first_name + ' ' + doctorlist[i].last_name };
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
        const downloadLink = document.createElement("a");
        const fileName = this.report.fileName;

        downloadLink.href = linkSource;
        downloadLink.download = fileName;
        downloadLink.click();
    }

    onUpdateReport() {

        let id = this.ledgerHelper._id; 
        const headers = new HttpHeaders({
            'Content-Type': 'application/json',
            'Authorization': '' + this.ledgerHelper.token 
        });

        for(let i =0; i < this.selectedItems.length ; i++){
            this.selectedDoctorsID.push(this.selectedItems[i].item_id);
        }

        let report = { 

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
                let signature = baseTransaction1['$sigs']['activeledger']
                baseTransaction1['$sigs'] = {};
                baseTransaction1['$sigs'][id] = signature;
          

                console.log(baseTransaction1);

                 this.updateReportAPI(baseTransaction1, headers)
            .subscribe(data => {
                console.log(data);

                if (data.status == 200) {
                    this.message = 'Report Update Successfully!';
                    this.open();

                    this.reports = JSON.parse(this.ledgerHelper.reports);

                    this.reports.find(item => item.id == this.report.id).doctors = this.selectedDoctorsID


                            this.ledgerHelper.reports =  JSON.stringify(this.reports);

                            this.router.navigate(['/report-list'], { replaceUrl: true });

                } else {
                    this.message = ' Report Update  Failed! ';
                    this.open();
                }
            });


            })
            .catch();

    }

    updateReportAPI(body, header) {
        return this.http.post<any>(this.ledgerHelper.updateReportURL, body, { headers: header, observe: 'response' });
    }

    open() {
        let config = new MatSnackBarConfig();
        config.verticalPosition = this.verticalPosition;
        config.horizontalPosition = this.horizontalPosition;
        config.duration = this.setAutoHide ? this.autoHide : 0;
        this.snackBar.open(this.message, this.action ? this.actionButtonLabel : undefined, config);
    }
}
