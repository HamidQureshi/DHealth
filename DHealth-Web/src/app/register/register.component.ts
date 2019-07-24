import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HttpResponse, HttpHeaders } from '@angular/common/http';
import {
  MatSnackBar,
  MatSnackBarConfig,
  MatSnackBarHorizontalPosition,
  MatSnackBarVerticalPosition,
} from '@angular/material';
import { LedgerHelper } from '../helper/ledgerhelper';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})

@Injectable()
export class RegisterComponent implements OnInit {
  constructor(private ledgerHelper: LedgerHelper, private router: Router, private http: HttpClient, public snackBar: MatSnackBar) { }

  message: string = 'Snack Bar opened.';
  actionButtonLabel: string = 'Close';
  action: boolean = true;
  setAutoHide: boolean = true;
  autoHide: number = 2000;
  horizontalPosition: MatSnackBarHorizontalPosition = 'center';
  verticalPosition: MatSnackBarVerticalPosition = 'bottom';
  addExtraClass: boolean = false;

  ngOnInit() { }

  onRegister(username, password) {
    console.log('email = ' + username);
    console.log('password = ' + password);

    const header = new HttpHeaders({
      'Content-Type': 'application/json',
      'Access-Control-Allow-Origin': '*'
    });

    const body = {
      "username": username,
      "password": password
    }


    this.http.post<any>(this.ledgerHelper.registerUrl, body, { headers: header, responseType: 'json', observe: 'response' })
      .subscribe(resp => {

        console.log(resp.headers.get('Token'));

        this.ledgerHelper.token = resp.headers.get('Token');

        this.message = resp.body.desc;
        this.open();

        if (resp.status === 200) {

            this.ledgerHelper.email = '' + username;
            this.ledgerHelper.profileCreated = '' + false;

          this.router.navigate(['/create-profile']);
        }

      });


  }


  onLogin() {
    this.router.navigate(['/login']);
  }

  open() {
    let config = new MatSnackBarConfig();
    config.verticalPosition = this.verticalPosition;
    config.horizontalPosition = this.horizontalPosition;
    config.duration = this.setAutoHide ? this.autoHide : 0;
    this.snackBar.open(this.message, this.action ? this.actionButtonLabel : undefined, config);
  }

}

