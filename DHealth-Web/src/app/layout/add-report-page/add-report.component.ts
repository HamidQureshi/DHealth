import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator, MatSort, MatTableDataSource } from '@angular/material';
import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { MatSnackBar, MatSnackBarConfig, MatSnackBarHorizontalPosition, MatSnackBarVerticalPosition } from '@angular/material';
import { PersistenceService, StorageType } from 'angular-persistence';
import { KeyHandler, IKey, KeyType, TransactionHandler, IBaseTransaction } from '@activeledger/sdk';
import { Report } from '../model/report';
import { LedgerHelper } from 'src/app/helper/ledgerhelper';
import { Router } from '@angular/router';



@Component({
    selector: 'app-report',
    templateUrl: './add-report.component.html',
    styleUrls: ['./add-report.component.scss']
})
export class AddReportComponent implements OnInit {

    message: string = 'Snack Bar opened.';
    actionButtonLabel: string = 'Close';
    action: boolean = true;
    setAutoHide: boolean = true;
    autoHide: number = 2000;
    horizontalPosition: MatSnackBarHorizontalPosition = 'center';
    verticalPosition: MatSnackBarVerticalPosition = 'bottom';
    addExtraClass: boolean = false;

    reports: Array<Report> = [];

    report = this.ledgerHelper.reports; 

    dropdownList = [];
    selectedItems = [];
    selectedDoctorsID : Array<String> =[];
    dropdownSettings = {};

    filename: string;
    fileBase64: string;

    constructor(private ledgerHelper: LedgerHelper, private router: Router,
        private http: HttpClient, public snackBar: MatSnackBar) {
    }

    ngOnInit() {
        (<HTMLInputElement>document.getElementById('i_pn')).value = this.ledgerHelper.full_name; 

        var doctorlist = JSON.parse(this.ledgerHelper.userList);

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

        let id = this.ledgerHelper._id; 

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
                let signature = baseTransaction1['$sigs']['activeledger']
                baseTransaction1['$sigs'] = {};
                baseTransaction1['$sigs'][id] = signature;

                console.log(baseTransaction1);

                this.addReportAPI(JSON.stringify(baseTransaction1), headers)
                    .subscribe(data => {
                        console.log(data);

                        if (data.status === 200) {
                            this.message = 'Report Update Successfully!';
                            this.open();

                            //update the report in pref
                            this.reports = JSON.parse(this.ledgerHelper.reports);
                            this.reports.push(report);
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


    fileChange(event) {
        let fileList: FileList = event.target.files;
        if (fileList.length > 0) {
            let file: File = fileList[0];
            let reader = new FileReader();
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

    open() {
        let config = new MatSnackBarConfig();
        config.verticalPosition = this.verticalPosition;
        config.horizontalPosition = this.horizontalPosition;
        config.duration = this.setAutoHide ? this.autoHide : 0;
        this.snackBar.open(this.message, this.action ? this.actionButtonLabel : undefined, config);
    }
}
