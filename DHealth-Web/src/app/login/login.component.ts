import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';

import {
  MatSnackBar,
  MatSnackBarConfig,
  MatSnackBarHorizontalPosition,
  MatSnackBarVerticalPosition,
} from '@angular/material';
import { LedgerHelper } from '../helper/ledgerhelper';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})


@Injectable()
export class LoginComponent implements OnInit {
  constructor(private ledgerHelper: LedgerHelper,private router: Router,
     private http: HttpClient, public snackBar: MatSnackBar) { }

  message: string = 'Snack Bar opened.';
  actionButtonLabel: string = 'Close';
  action: boolean = true;
  setAutoHide: boolean = true;
  autoHide: number = 2000;
  horizontalPosition: MatSnackBarHorizontalPosition = 'center';
  verticalPosition: MatSnackBarVerticalPosition = 'bottom';
  addExtraClass: boolean = false;


  ngOnInit() { }

  onLogin(username, password) {

    console.log('email = ' + username);
    console.log('password = ' + password);

    const auth = 'Basic ' + btoa(username + ':' + password);

    console.log('auth = ' + auth);

    const header = new HttpHeaders({
      'Authorization': auth
    });

    this.http.post<any>(this.ledgerHelper.loginUrl, null, { headers: header, observe: 'response' })
      .subscribe(data => {
        console.log(data);

        console.log(data.status);
        if (data.status == 200) {
          this.message = data.body.resp.desc;
          this.open();

          console.log(data.headers.get('Token'));

          this.ledgerHelper.token = data.headers.get('Token');

          console.log('streams = ' + data.body.streams);

          if (data.body.streams != null) {

            this.ledgerHelper.address = '' + data.body.streams.address;
            this.ledgerHelper.date_of_birth = '' + data.body.streams.date_of_birth;
            this.ledgerHelper.dp = '' + data.body.streams.dp;
            this.ledgerHelper.email = '' + data.body.streams.email;
            this.ledgerHelper.first_name = '' + data.body.streams.first_name;
            this.ledgerHelper.last_name = '' + data.body.streams.last_name;
            this.ledgerHelper.gender = '' + data.body.streams.gender;
            this.ledgerHelper.phone_number = '' + data.body.streams.phone_number;
            this.ledgerHelper.profile_type = '' + data.body.streams.profile_type;
            this.ledgerHelper.reports = '' + data.body.streams.reports;
            this.ledgerHelper.security = '' + data.body.streams.security;
            this.ledgerHelper._id = '' + data.body.streams._id;

            this.ledgerHelper.profileCreated = '' + true;
            this.ledgerHelper.isLoggedin = '' + true;

            this.router.navigate(['/dashboard']);
          }
          else {

            this.ledgerHelper.email = '' + username;
            this.ledgerHelper.profileCreated = '' + false;
            this.ledgerHelper.isLoggedin = '' + true;

            this.router.navigate(['/create-profile']);

          }

        } else {
          this.message = data.statusText;
          this.open();
        }
      });

  }

  loginUser(header) {
    return this.http.post<any>(this.ledgerHelper.loginUrl, null, { headers: header, responseType: 'json' });
  }


  onRegister() {
    this.router.navigate(['/register']);
  }

  open() {
    let config = new MatSnackBarConfig();
    config.verticalPosition = this.verticalPosition;
    config.horizontalPosition = this.horizontalPosition;
    config.duration = this.setAutoHide ? this.autoHide : 0;
    this.snackBar.open(this.message, this.action ? this.actionButtonLabel : undefined, config);
  }


}
