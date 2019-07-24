import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import {
  MatSnackBar,
  MatSnackBarConfig,
  MatSnackBarHorizontalPosition,
  MatSnackBarVerticalPosition,
} from '@angular/material';
import { DomSanitizer } from '@angular/platform-browser';
import { User } from '../model/user';
import { LedgerHelper } from 'src/app/helper/ledgerhelper';


@Component({
  selector: 'app-doctorpatient-page',
  templateUrl: './doctorpatient-page.component.html',
  styleUrls: ['./doctorpatient-page.component.scss']
})

export class DoctorpatientPageComponent implements OnInit {

  constructor(private ledgerHelper: LedgerHelper,private router: Router,
     private http: HttpClient, public snackBar: MatSnackBar,
      private sanitizer: DomSanitizer) {

  }

  message: string = 'Snack Bar opened.';
  actionButtonLabel: string = 'Close';
  action: boolean = true;
  setAutoHide: boolean = true;
  autoHide: number = 2000;
  horizontalPosition: MatSnackBarHorizontalPosition = 'center';
  verticalPosition: MatSnackBarVerticalPosition = 'bottom';
  addExtraClass: boolean = false;
  users: Array<User> = [];


  ngOnInit() {
    this.users = JSON.parse(this.ledgerHelper.userList);

  }


  onSelect(user: User): void {
    console.log(user.first_name);

  }


  open() {
    let config = new MatSnackBarConfig();
    config.verticalPosition = this.verticalPosition;
    config.horizontalPosition = this.horizontalPosition;
    config.duration = this.setAutoHide ? this.autoHide : 0;
    this.snackBar.open(this.message, this.action ? this.actionButtonLabel : undefined, config);
  }


  transform(html) {
    return this.sanitizer.bypassSecurityTrustUrl(html);
  }

}
