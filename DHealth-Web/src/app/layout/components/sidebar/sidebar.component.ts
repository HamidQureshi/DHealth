import { Component, OnInit } from '@angular/core';
import { LedgerHelper } from 'src/app/helper/ledgerhelper';

@Component({
    selector: 'app-sidebar',
    templateUrl: './sidebar.component.html',
    styleUrls: ['./sidebar.component.scss']
})
export class SidebarComponent implements OnInit {
    public showMenu: string;
    item = 'Doctor List';

    constructor(private ledgerHelper: LedgerHelper) {}


    ngOnInit() {
        this.showMenu = '';
        if (this.ledgerHelper.profile_type === 'Doctor') {
            this.item = 'Patient List';
        }
        else{
            this.item = 'Doctor List';
        }

    }


    addExpandClass(element: any) {
        if (element === this.showMenu) {
            this.showMenu = '0';
        } else {
            this.showMenu = element;
        }
    }
}
