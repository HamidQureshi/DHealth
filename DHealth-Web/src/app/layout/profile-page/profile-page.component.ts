import { Component, OnInit, ViewChild, ElementRef, Renderer } from '@angular/core';
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
import { LedgerHelper } from 'src/app/helper/ledgerhelper';


@Component({
  selector: 'app-profile-page',
  templateUrl: './profile-page.component.html',
  styleUrls: ['./profile-page.component.scss']
})
export class ProfilePageComponent implements OnInit {
  image :any;
  placeholder:any;
  constructor(private ledgerHelper: LedgerHelper, private router: Router, private http: HttpClient, public snackBar: MatSnackBar, private sanitizer: DomSanitizer) { }


  message: string = 'Snack Bar opened.';
  actionButtonLabel: string = 'Close';
  action: boolean = true;
  setAutoHide: boolean = true;
  autoHide: number = 2000;
  horizontalPosition: MatSnackBarHorizontalPosition = 'center';
  verticalPosition: MatSnackBarVerticalPosition = 'bottom';
  addExtraClass: boolean = false;
  public hidden = true;
  public editing_disabled = true;


  @ViewChild('firstname_f') f_nameField: ElementRef;

  ngOnInit() {

    (<HTMLInputElement>document.getElementById('i_fn')).value = this.ledgerHelper.first_name; 
    (<HTMLInputElement>document.getElementById('i_ln')).value = this.ledgerHelper.last_name; 
    (<HTMLInputElement>document.getElementById('i_address')).value = this.ledgerHelper.address; 
    (<HTMLInputElement>document.getElementById('i_dob')).value = this.ledgerHelper.date_of_birth; 
    (<HTMLInputElement>document.getElementById('i_email')).value = this.ledgerHelper.email; 
    (<HTMLInputElement>document.getElementById('i_pno')).value = this.ledgerHelper.phone_number;

    if (!(this.ledgerHelper.dp === null)) {
      // console.log('true' + this.ledgerHelper.dp.trim())

      this.image = this.transform('data:image/jpeg;base64, ' + this.ledgerHelper.dp.trim()); 
    }
    else{
      this.image = this.transform('data:image/jpeg;base64, ' + this.ledgerHelper.placeholder_img);
    }

  }

  onUpdateProfile(firstname, lastname, email, dob, pno, address) {

    let id = this.ledgerHelper._id;

    const header = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': '' + this.ledgerHelper.token 
    });



    let baseTransaction1: IBaseTransaction = {
      $tx: {
        $contract: 'user',
        $namespace: 'healthtestnet',
        $entry: 'update',
        $i: {},
        $o: {},
      },
      $selfsign: false,
      $sigs: {}
    };


    baseTransaction1.$tx.$i[id] = {};
    baseTransaction1.$tx.$o[id] = {};
    baseTransaction1.$tx.$o[id]['first_name'] = firstname;
    baseTransaction1.$tx.$o[id]['last_name'] = lastname;
    baseTransaction1.$tx.$o[id]['date_of_birth'] = dob;
    baseTransaction1.$tx.$o[id]['phone_number'] = pno;
    baseTransaction1.$tx.$o[id]['address'] = address;
    const to_remove = 'data:image/jpeg;base64,';
    const to_remove2 = 'SafeValue must use [property]=binding:';
    let dp = this.image.toString();
    baseTransaction1.$tx.$o[id]['dp'] = dp.replace(to_remove, '').replace(to_remove2, '');
    dp = baseTransaction1.$tx.$o[id]['dp'];


    const txHandler = new TransactionHandler();

    txHandler
      .signTransaction(baseTransaction1, this.ledgerHelper.key) 
      .then((signedTx: IBaseTransaction) => {

        baseTransaction1 = signedTx;
        let signature = baseTransaction1['$sigs']['activeledger']
        baseTransaction1['$sigs'] = {};
        baseTransaction1['$sigs'][id] = signature;

        console.log(baseTransaction1);

        this.updateProfile(baseTransaction1, header)
          .subscribe(data => {
            console.log(data);

            if (data.status === 200) {
              this.message = data.body.resp.desc;
              this.open();

              console.log('profile page --> ' + dp);
              this.ledgerHelper.address = '' + address;
              this.ledgerHelper.date_of_birth = '' + dob;
              this.ledgerHelper.dp = '' + dp;
              this.ledgerHelper.first_name = '' + firstname;
              this.ledgerHelper.last_name = '' + lastname;
              this.ledgerHelper.phone_number = '' + pno;
 
              this.disableFields();
            } else {
              this.message = ' Something went wrong! ';
              this.open();
            }
          });

      })
      .catch();


  }

  onSelectFile(event) { 
    if (event.target.files && event.target.files[0]) {
      var reader = new FileReader();

      reader.readAsDataURL(event.target.files[0]); 

      reader.onload = (event) => {
        // this.image = event.target.result;
        this.image = reader.result;

      }
    }
  }

  updateProfile(body, headers1) {
    return this.http.post<any>(this.ledgerHelper.updateProfileUrl, body, { headers: headers1, observe: 'response' });
  }


  open() {
    let config = new MatSnackBarConfig();
    config.verticalPosition = this.verticalPosition;
    config.horizontalPosition = this.horizontalPosition;
    config.duration = this.setAutoHide ? this.autoHide : 0;
    this.snackBar.open(this.message, this.action ? this.actionButtonLabel : undefined, config);
  }

  enableFields() {
    this.editing_disabled = false;
    (<HTMLInputElement>document.getElementById('i_fn')).disabled = false;
    (<HTMLInputElement>document.getElementById('i_ln')).disabled = false;
    (<HTMLInputElement>document.getElementById('i_dob')).disabled = false;
    (<HTMLInputElement>document.getElementById('i_pno')).disabled = false;
    (<HTMLInputElement>document.getElementById('i_address')).disabled = false;
    this.hidden = false;

    this.f_nameField.nativeElement.focus();
  }

  disableFields() {
    this.editing_disabled = true;
    (<HTMLInputElement>document.getElementById('i_fn')).disabled = true;
    (<HTMLInputElement>document.getElementById('i_ln')).disabled = true;
    (<HTMLInputElement>document.getElementById('i_dob')).disabled = true;
    (<HTMLInputElement>document.getElementById('i_pno')).disabled = true;
    (<HTMLInputElement>document.getElementById('i_address')).disabled = true;
    this.hidden = true;

  }

  transform(html) {
    return this.sanitizer.bypassSecurityTrustUrl(html);
  }

}
