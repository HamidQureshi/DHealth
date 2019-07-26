import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { KeyHandler, IKey, KeyType, TransactionHandler, IBaseTransaction } from '@activeledger/sdk';
import { DomSanitizer } from '@angular/platform-browser';
import { LedgerHelper } from '../helper/ledgerhelper';
import { LayoutComponent } from '../layout/layout.component';
import { Ng2ImgMaxService } from 'ng2-img-max';


@Component({
  selector: 'app-create-profile',
  templateUrl: './create-profile.component.html',
  styleUrls: ['./create-profile.component.scss']
})

@Injectable()
export class CreateProfileComponent implements OnInit {
  constructor(private layoutComp: LayoutComponent, private ledgerHelper: LedgerHelper, private router: Router,
    private http: HttpClient, private sanitizer: DomSanitizer, private ng2ImgMax: Ng2ImgMaxService) { }

  image: any;
  image_set = false;
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
    if (this.image_set) {
      display = this.image.toString();
      display = this.ledgerHelper.sanitizeDP(display);
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
          this.layoutComp.showSnackBar(data.body.resp.desc);


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
          this.layoutComp.showSnackBar(' Something went wrong! ');
        }
      });

  }

  onSelectFile(event) {
    if (event.target.files && event.target.files[0]) {
      const reader = new FileReader();

      this.ng2ImgMax.resizeImage(event.target.files[0], 400, 300).subscribe(
        result => {
          reader.readAsDataURL(result);
          reader.onload = () => {
            this.image_set = true;
            this.image = reader.result;
                    };
        },
        error => {
          console.log('😢 Oh no!', error);
        }
      );

      // reader.readAsDataURL(event.target.files[0]);

      // reader.onload = () => {
      //   this.image_set = true;
      //   this.image = reader.result;
      // };
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

}

