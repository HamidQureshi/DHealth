import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { DomSanitizer } from '@angular/platform-browser';
import { User } from '../model/user';
import { LedgerHelper } from 'src/app/helper/ledgerhelper';


@Component({
  selector: 'app-doctorpatient-page',
  templateUrl: './doctorpatient-page.component.html',
  styleUrls: ['./doctorpatient-page.component.scss']
})

export class DoctorpatientPageComponent implements OnInit {

  constructor(private ledgerHelper: LedgerHelper, private router: Router,
     private sanitizer: DomSanitizer) {

  }

  users: Array<User> = [];

  ngOnInit() {
    this.users = JSON.parse(this.ledgerHelper.userList);
  }


  onSelect(user: User): void {
    console.log(user.first_name);

  }

  transform(html) {
    return this.sanitizer.bypassSecurityTrustUrl(html);
  }

}
