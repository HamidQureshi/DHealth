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
import { KeyHandler, IKey, KeyType, TransactionHandler, IBaseTransaction } from '@activeledger/sdk';
import { DomSanitizer } from '@angular/platform-browser';
import { LedgerHelper } from '../helper/ledgerhelper';


@Component({
  selector: 'app-create-profile',
  templateUrl: './create-profile.component.html',
  styleUrls: ['./create-profile.component.scss']
})

@Injectable()
export class CreateProfileComponent implements OnInit {
  constructor(private ledgerHelper: LedgerHelper, private router: Router,
     private http: HttpClient, public snackBar: MatSnackBar, private sanitizer: DomSanitizer) { }

  image: any;
  image_set = false;

  message: string = 'Snack Bar opened.';
  actionButtonLabel: string = 'Close';
  action: boolean = true;
  setAutoHide: boolean = true;
  autoHide: number = 2000;
  horizontalPosition: MatSnackBarHorizontalPosition = 'center';
  verticalPosition: MatSnackBarVerticalPosition = 'bottom';
  addExtraClass: boolean = false;

  user_type = 'Doctor';


  ngOnInit() {
    this.generateKeys();
    (<HTMLInputElement>document.getElementById('i_email')).value = this.ledgerHelper.email; 
    this.image = this.transform('data:image/jpeg;base64, ' + this.ledgerHelper.placeholder_img);
  }


  onCreateProfile(firstname, lastname, email, dob, pno, address) {

    this.ledgerHelper.gender = 'Male';

    const header = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': '' + this.ledgerHelper.token 
    });

    let display = null;
    if( this.image_set) {
    const to_remove = 'data:image/jpeg;base64,';
      display = this.image.toString();
      display = display.replace(to_remove, '');
  }

    let baseTransaction: IBaseTransaction = {
      $tx: {
        $contract: 'user',
        $namespace: 'healthtestnet',
        $entry: 'create',
        $i: {
          activeledger: {
            publicKey: this.ledgerHelper.key.key.pub.pkcs8pem,
            type: 'rsa',
            first_name: firstname,
            last_name: lastname,
            email: email,
            date_of_birth: dob,
            phone_number: pno,
            address: address,
            security: 'RSA',
            profile_type: this.user_type,
            gender: this.ledgerHelper.gender,
            dp: display
          }
        },
      },
      $selfsign: true,
      $sigs: {}
    };


    const txHandler = new TransactionHandler();

    txHandler
      .signTransaction(baseTransaction, this.ledgerHelper.key) 
      .then((signedTx: IBaseTransaction) => {

        baseTransaction = signedTx;

        console.log(baseTransaction);
      })
      .catch();


    this.createProfile(baseTransaction, header)
      .subscribe(data => {
        console.log(data);

        if (data.status === 200) {
          this.message = data.body.resp.desc;
          this.open();


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

        } else {
          this.message = ' Something went wrong! ';
          this.open();
        }
      });

  }

  onSelectFile(event) {
    if (event.target.files && event.target.files[0]) {
      var reader = new FileReader();

      reader.readAsDataURL(event.target.files[0]);

      reader.onload = (event) => {
       this.image_set = true;
        this.image = reader.result;
      }
    }
  }

  createProfile(body, header) {
    return this.http.post<any>(this.ledgerHelper.createProfileUrl, body, { headers: header, observe: 'response' });
  }

  generateKeys() {

    const keyHandler = new KeyHandler();

    keyHandler
      .generateKey('activeledger', KeyType.RSA)
      .then((generatedKey: IKey) => {
        this.ledgerHelper.key = generatedKey;
   
      })
      .catch()

  }

  transform(html) {
    return this.sanitizer.bypassSecurityTrustUrl(html);
  }
  open() {
    let config = new MatSnackBarConfig();
    config.verticalPosition = this.verticalPosition;
    config.horizontalPosition = this.horizontalPosition;
    config.duration = this.setAutoHide ? this.autoHide : 0;
    this.snackBar.open(this.message, this.action ? this.actionButtonLabel : undefined, config);
  }



}

