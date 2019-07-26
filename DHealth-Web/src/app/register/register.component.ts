import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { LedgerHelper } from '../helper/ledgerhelper';
import { LayoutComponent } from '../layout/layout.component';


@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})

@Injectable()
export class RegisterComponent implements OnInit {
  constructor(private layoutComp: LayoutComponent, private ledgerHelper: LedgerHelper,
     private router: Router, private http: HttpClient) { }

  ngOnInit() { }

  onRegister(username, password) {
    console.log('email = ' + username);
    console.log('password = ' + password);

    const header = new HttpHeaders({
      'Content-Type': 'application/json',
      'Access-Control-Allow-Origin': '*'
    });

    const body = {
      'username': username,
      'password': password
    }


    this.http.post<any>(this.ledgerHelper.registerUrl, body, { headers: header, responseType: 'json', observe: 'response' })
      .subscribe(resp => {

        this.ledgerHelper.token = resp.headers.get('Token');

        this.layoutComp.showSnackBar(resp.body.desc);

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

}

