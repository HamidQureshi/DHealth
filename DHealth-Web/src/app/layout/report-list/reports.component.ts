import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator, MatSort, MatTableDataSource } from '@angular/material';
import { Router } from '@angular/router';
import { Report } from '../model/report';
import { LedgerHelper } from 'src/app/helper/ledgerhelper';

@Component({
    selector: 'app-reports',
    templateUrl: './reports.component.html',
    styleUrls: ['./reports.component.scss']
})
export class ReportsComponent implements OnInit {
    displayedColumns = ['id', 'name', 'title', 'doc_name'];
    dataSource: MatTableDataSource<Report>;

    @ViewChild(MatPaginator) paginator: MatPaginator;
    @ViewChild(MatSort) sort: MatSort;

    reports: Array<Report> = [];

    cannot_upload_report = false;

    constructor(private ledgerHelper: LedgerHelper, private router: Router) {
    }

    ngOnInit() {
        this.reports = JSON.parse(this.ledgerHelper.reports);
        this.dataSource = new MatTableDataSource(this.reports);
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;

        if(this.ledgerHelper.profile_type === 'Doctor'){
            this.cannot_upload_report = true;
        }

    }

    addReport(){
        this.router.navigate(['/add-report'], { replaceUrl: true });
    }

    getRecord(row){
        console.log(row);
        this.ledgerHelper.report = JSON.stringify(row);
        this.router.navigate(['/report'], { replaceUrl: true });

    }

    applyFilter(filterValue: string) {
        filterValue = filterValue.trim(); 
        filterValue = filterValue.toLowerCase(); 
        this.dataSource.filter = filterValue;
        if (this.dataSource.paginator) {
            this.dataSource.paginator.firstPage();
        }
    }
}
