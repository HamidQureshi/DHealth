(window["webpackJsonp"] = window["webpackJsonp"] || []).push([["profile-page-profile-page-module"],{

/***/ "./src/app/layout/profile-page/profile-page-routing.module.ts":
/*!********************************************************************!*\
  !*** ./src/app/layout/profile-page/profile-page-routing.module.ts ***!
  \********************************************************************/
/*! exports provided: ProfilePageRoutingModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "ProfilePageRoutingModule", function() { return ProfilePageRoutingModule; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
/* harmony import */ var _profile_page_component__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./profile-page.component */ "./src/app/layout/profile-page/profile-page.component.ts");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};



var routes = [
    {
        path: '',
        component: _profile_page_component__WEBPACK_IMPORTED_MODULE_2__["ProfilePageComponent"]
    }
];
var ProfilePageRoutingModule = /** @class */ (function () {
    function ProfilePageRoutingModule() {
    }
    ProfilePageRoutingModule = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["NgModule"])({
            imports: [_angular_router__WEBPACK_IMPORTED_MODULE_1__["RouterModule"].forChild(routes)],
            exports: [_angular_router__WEBPACK_IMPORTED_MODULE_1__["RouterModule"]]
        })
    ], ProfilePageRoutingModule);
    return ProfilePageRoutingModule;
}());



/***/ }),

/***/ "./src/app/layout/profile-page/profile-page.component.html":
/*!*****************************************************************!*\
  !*** ./src/app/layout/profile-page/profile-page.component.html ***!
  \*****************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<div class=\"create-profile-page\">\r\n    <div class=\"content\">\r\n        <form class=\"create-profile-form\" ng-controller=\"formCtrl\" fxFlex>\r\n\r\n            <div fxFlex fxLayout=\"row\" fxLayout.lt-md=\"column\" class=\"text-center\">\r\n                <div fxFlexFill>\r\n                    <img id=\"i_img\" [src]=\"image\" #img_f mat-card-image [alt]=\"placeholder\"><br>\r\n                    <input [hidden]=\"hidden\" type='file' (change)=\"onSelectFile($event)\">\r\n                </div>\r\n            </div>\r\n\r\n            <div fxFlex fxLayout=\"row\" fxLayout.lt-md=\"column\">\r\n                <div fxFlex style=\"margin: 0px 20px 0px 0\">\r\n                    <div fxFlexFill>\r\n                        <mat-form-field class=\"w-100\">\r\n                            <input id=\"i_fn\" #firstname_f matInput placeholder=\"First Name  \" disabled=\"disabled\"\r\n                                style=\"color: black;\">\r\n                        </mat-form-field>\r\n                    </div>\r\n                </div>\r\n\r\n                <div fxFlex fxLayoutAlign=\"end\">\r\n                    <div fxFlexFill>\r\n                        <mat-form-field class=\"w-100\">\r\n                            <input id=\"i_ln\" #lastname_f matInput placeholder=\"Last Name  \" disabled=\"disabled\"\r\n                                style=\"color: black;\">\r\n                        </mat-form-field>\r\n                    </div>\r\n                </div>\r\n            </div>\r\n\r\n            <div fxFlex fxlayout=\"row\" fxlayout.lt-md=\"column\">\r\n                <div fxFlexFill>\r\n                    <mat-form-field class=\"w-100\">\r\n                        <input id=\"i_email\" #email_f matInput placeholder=\"Email  \" disabled=\"disabled\"\r\n                            style=\"color: black;\">\r\n                    </mat-form-field>\r\n                </div>\r\n            </div>\r\n\r\n            <div fxFlex fxlayout=\"row\" fxlayout.lt-md=\"column\">\r\n                <div fxFlexFill>\r\n                    <mat-form-field class=\"w-100\">\r\n\r\n                        <input id=\"i_dob\" #dob_f matInput #dob_f matInput [matDatepicker]=\"picker\"\r\n                            placeholder=\"Date of Birth\" disabled=\"disabled\" style=\"color: black;\">\r\n                        <mat-datepicker-toggle matSuffix [for]=\"picker\"></mat-datepicker-toggle>\r\n                        <mat-datepicker #picker></mat-datepicker>\r\n\r\n                    </mat-form-field>\r\n                </div>\r\n            </div>\r\n\r\n            <div fxFlex fxlayout=\"row\" fxlayout.lt-md=\"column\">\r\n                <div fxFlexFill>\r\n                    <mat-form-field class=\"w-100\">\r\n                        <input id=\"i_pno\" #pno_f matInput placeholder=\"Phone Number  \" disabled=\"disabled\"\r\n                            style=\"color: black;\">\r\n                    </mat-form-field>\r\n                </div>\r\n            </div>\r\n\r\n            <div fxFlex fxlayout=\"row\" fxlayout.lt-md=\"column\">\r\n                <div fxFlexFill>\r\n                    <mat-form-field class=\"w-100\">\r\n                        <input id=\"i_address\" #address_f matInput placeholder=\"Address  \" disabled=\"disabled\"\r\n                            style=\"color: black;\">\r\n                    </mat-form-field>\r\n                </div>\r\n            </div>\r\n\r\n\r\n            <div fxFlex fxLayout=\"row\" fxLayout.lt-md=\"column\">\r\n\r\n                <div fxFlexFill>\r\n                    <div fxFlex fxLayoutAlign=\"end\">\r\n                        <button mat-raised-button color=\"primary\" class=\"w-50\" (click)=\"enableFields()\"\r\n                            type='button'>Edit\r\n                            Profile</button>\r\n                        <button mat-raised-button color=\"primary\" class=\"w-50\" [disabled]= \"editing_disabled\"\r\n                            (click)=\"onUpdateProfile(firstname_f.value,lastname_f.value,email_f.value,dob_f.value,pno_f.value,address_f.value)\"\r\n                            type='button' style=\"margin: 0px 20px 0px 20px\">Update Profile</button>\r\n                    </div>\r\n                </div>\r\n            </div>\r\n\r\n        </form>\r\n    </div>\r\n</div>"

/***/ }),

/***/ "./src/app/layout/profile-page/profile-page.component.scss":
/*!*****************************************************************!*\
  !*** ./src/app/layout/profile-page/profile-page.component.scss ***!
  \*****************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = ".profile-page {\n  display: flex;\n  align-items: center;\n  justify-content: center;\n  height: 100%;\n  position: relative; }\n  .profile-page .content {\n    z-index: 1;\n    display: flex;\n    align-items: center;\n    justify-content: center; }\n  .profile-page .content .app-name {\n      margin-top: 0px;\n      padding-bottom: 10px;\n      font-size: 32px; }\n  .profile-page .content .profile-page {\n      padding: 40px;\n      background: #fff;\n      width: 500px;\n      box-shadow: 0 0 10px #ddd; }\n  .profile-page .content .profile-page input:-webkit-autofill {\n        -webkit-box-shadow: 0 0 0 30px white inset; }\n  .profile-page:after {\n    content: '';\n    background: #fff;\n    position: absolute;\n    top: 0;\n    left: 0;\n    bottom: 50%;\n    right: 0; }\n  .profile-page:before {\n    content: '';\n    background: #3f51b5;\n    position: absolute;\n    top: 50%;\n    left: 0;\n    bottom: 0;\n    right: 0; }\n  .text-center {\n  text-align: center; }\n  .w-100 {\n  width: 100%; }\n  .example-h2 {\n  margin: 10px; }\n  .example-section {\n  display: flex;\n  align-content: center;\n  align-items: center;\n  height: 60px; }\n  .example-margin {\n  margin: 0 10px; }\n  .mat-card-image {\n  width: 200px;\n  height: 200px; }\n\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbInNyYy9hcHAvbGF5b3V0L3Byb2ZpbGUtcGFnZS9DOlxcVXNlcnNcXEhhbWlkXFxEZXNrdG9wXFxBTFNES1xcZXhhbXBsZXNcXHNiLWFkbWluLW1hdGVyaWFsL3NyY1xcYXBwXFxsYXlvdXRcXHByb2ZpbGUtcGFnZVxccHJvZmlsZS1wYWdlLmNvbXBvbmVudC5zY3NzIl0sIm5hbWVzIjpbXSwibWFwcGluZ3MiOiJBQUFBO0VBQ0ksYUFBYTtFQUNiLG1CQUFtQjtFQUNuQix1QkFBdUI7RUFDdkIsWUFBWTtFQUNaLGtCQUFrQixFQUFBO0VBTHRCO0lBT1EsVUFBVTtJQUNWLGFBQWE7SUFDYixtQkFBbUI7SUFDbkIsdUJBQXVCLEVBQUE7RUFWL0I7TUFZWSxlQUFlO01BQ2Ysb0JBQW9CO01BQ3BCLGVBQWUsRUFBQTtFQWQzQjtNQWlCWSxhQUFhO01BQ2IsZ0JBQWdCO01BQ2hCLFlBQVk7TUFDWix5QkFBeUIsRUFBQTtFQXBCckM7UUFzQmdCLDBDQUEwQyxFQUFBO0VBdEIxRDtJQTRCUSxXQUFXO0lBQ1gsZ0JBQWdCO0lBQ2hCLGtCQUFrQjtJQUNsQixNQUFNO0lBQ04sT0FBTztJQUNQLFdBQVc7SUFDWCxRQUFRLEVBQUE7RUFsQ2hCO0lBcUNRLFdBQVc7SUFDWCxtQkFBbUI7SUFDbkIsa0JBQWtCO0lBQ2xCLFFBQVE7SUFDUixPQUFPO0lBQ1AsU0FBUztJQUNULFFBQVEsRUFBQTtFQUdoQjtFQUNJLGtCQUFrQixFQUFBO0VBRXRCO0VBQ0ksV0FBVyxFQUFBO0VBR2Y7RUFDSSxZQUFZLEVBQUE7RUFHZDtFQUNFLGFBQWE7RUFDYixxQkFBcUI7RUFDckIsbUJBQW1CO0VBQ25CLFlBQVksRUFBQTtFQUdkO0VBQ0UsY0FBYyxFQUFBO0VBR2hCO0VBQ0UsWUFBVztFQUNYLGFBQVksRUFBQSIsImZpbGUiOiJzcmMvYXBwL2xheW91dC9wcm9maWxlLXBhZ2UvcHJvZmlsZS1wYWdlLmNvbXBvbmVudC5zY3NzIiwic291cmNlc0NvbnRlbnQiOlsiLnByb2ZpbGUtcGFnZSB7XHJcbiAgICBkaXNwbGF5OiBmbGV4O1xyXG4gICAgYWxpZ24taXRlbXM6IGNlbnRlcjtcclxuICAgIGp1c3RpZnktY29udGVudDogY2VudGVyO1xyXG4gICAgaGVpZ2h0OiAxMDAlO1xyXG4gICAgcG9zaXRpb246IHJlbGF0aXZlO1xyXG4gICAgLmNvbnRlbnQge1xyXG4gICAgICAgIHotaW5kZXg6IDE7XHJcbiAgICAgICAgZGlzcGxheTogZmxleDtcclxuICAgICAgICBhbGlnbi1pdGVtczogY2VudGVyO1xyXG4gICAgICAgIGp1c3RpZnktY29udGVudDogY2VudGVyO1xyXG4gICAgICAgIC5hcHAtbmFtZSB7XHJcbiAgICAgICAgICAgIG1hcmdpbi10b3A6IDBweDtcclxuICAgICAgICAgICAgcGFkZGluZy1ib3R0b206IDEwcHg7XHJcbiAgICAgICAgICAgIGZvbnQtc2l6ZTogMzJweDtcclxuICAgICAgICB9XHJcbiAgICAgICAgLnByb2ZpbGUtcGFnZSB7XHJcbiAgICAgICAgICAgIHBhZGRpbmc6IDQwcHg7XHJcbiAgICAgICAgICAgIGJhY2tncm91bmQ6ICNmZmY7XHJcbiAgICAgICAgICAgIHdpZHRoOiA1MDBweDtcclxuICAgICAgICAgICAgYm94LXNoYWRvdzogMCAwIDEwcHggI2RkZDtcclxuICAgICAgICAgICAgaW5wdXQ6LXdlYmtpdC1hdXRvZmlsbCB7XHJcbiAgICAgICAgICAgICAgICAtd2Via2l0LWJveC1zaGFkb3c6IDAgMCAwIDMwcHggd2hpdGUgaW5zZXQ7XHJcbiAgICAgICAgICAgIH1cclxuICAgICAgICB9XHJcbiAgICB9XHJcblxyXG4gICAgJjphZnRlciB7XHJcbiAgICAgICAgY29udGVudDogJyc7XHJcbiAgICAgICAgYmFja2dyb3VuZDogI2ZmZjtcclxuICAgICAgICBwb3NpdGlvbjogYWJzb2x1dGU7XHJcbiAgICAgICAgdG9wOiAwO1xyXG4gICAgICAgIGxlZnQ6IDA7XHJcbiAgICAgICAgYm90dG9tOiA1MCU7XHJcbiAgICAgICAgcmlnaHQ6IDA7XHJcbiAgICB9XHJcbiAgICAmOmJlZm9yZSB7XHJcbiAgICAgICAgY29udGVudDogJyc7XHJcbiAgICAgICAgYmFja2dyb3VuZDogIzNmNTFiNTtcclxuICAgICAgICBwb3NpdGlvbjogYWJzb2x1dGU7XHJcbiAgICAgICAgdG9wOiA1MCU7XHJcbiAgICAgICAgbGVmdDogMDtcclxuICAgICAgICBib3R0b206IDA7XHJcbiAgICAgICAgcmlnaHQ6IDA7XHJcbiAgICB9XHJcbn1cclxuLnRleHQtY2VudGVyIHtcclxuICAgIHRleHQtYWxpZ246IGNlbnRlcjtcclxufVxyXG4udy0xMDAge1xyXG4gICAgd2lkdGg6IDEwMCU7XHJcbn1cclxuXHJcbi5leGFtcGxlLWgyIHtcclxuICAgIG1hcmdpbjogMTBweDtcclxuICB9XHJcbiAgXHJcbiAgLmV4YW1wbGUtc2VjdGlvbiB7XHJcbiAgICBkaXNwbGF5OiBmbGV4O1xyXG4gICAgYWxpZ24tY29udGVudDogY2VudGVyO1xyXG4gICAgYWxpZ24taXRlbXM6IGNlbnRlcjtcclxuICAgIGhlaWdodDogNjBweDtcclxuICB9XHJcbiAgXHJcbiAgLmV4YW1wbGUtbWFyZ2luIHtcclxuICAgIG1hcmdpbjogMCAxMHB4O1xyXG4gIH1cclxuXHJcbiAgLm1hdC1jYXJkLWltYWdle1xyXG4gICAgd2lkdGg6MjAwcHg7XHJcbiAgICBoZWlnaHQ6MjAwcHg7XHJcbn0iXX0= */"

/***/ }),

/***/ "./src/app/layout/profile-page/profile-page.component.ts":
/*!***************************************************************!*\
  !*** ./src/app/layout/profile-page/profile-page.component.ts ***!
  \***************************************************************/
/*! exports provided: ProfilePageComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "ProfilePageComponent", function() { return ProfilePageComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/common/http */ "./node_modules/@angular/common/fesm5/http.js");
/* harmony import */ var _angular_material__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @angular/material */ "./node_modules/@angular/material/esm5/material.es5.js");
/* harmony import */ var _activeledger_sdk__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @activeledger/sdk */ "./node_modules/@activeledger/sdk/lib/index.js");
/* harmony import */ var _activeledger_sdk__WEBPACK_IMPORTED_MODULE_4___default = /*#__PURE__*/__webpack_require__.n(_activeledger_sdk__WEBPACK_IMPORTED_MODULE_4__);
/* harmony import */ var _angular_platform_browser__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @angular/platform-browser */ "./node_modules/@angular/platform-browser/fesm5/platform-browser.js");
/* harmony import */ var src_app_helper_ledgerhelper__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! src/app/helper/ledgerhelper */ "./src/app/helper/ledgerhelper.ts");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};








var ProfilePageComponent = /** @class */ (function () {
    function ProfilePageComponent(router, http, snackBar, sanitizer) {
        this.router = router;
        this.http = http;
        this.snackBar = snackBar;
        this.sanitizer = sanitizer;
        this.message = 'Snack Bar opened.';
        this.actionButtonLabel = 'Close';
        this.action = true;
        this.setAutoHide = true;
        this.autoHide = 2000;
        this.horizontalPosition = 'center';
        this.verticalPosition = 'bottom';
        this.addExtraClass = false;
        this.hidden = true;
        this.editing_disabled = true;
    }
    ProfilePageComponent.prototype.ngOnInit = function () {
        document.getElementById('i_fn').value = src_app_helper_ledgerhelper__WEBPACK_IMPORTED_MODULE_6__["LedgerHelper"].first_name; //localStorage.getItem('first_name');
        document.getElementById('i_ln').value = src_app_helper_ledgerhelper__WEBPACK_IMPORTED_MODULE_6__["LedgerHelper"].last_name; //localStorage.getItem('last_name');
        document.getElementById('i_address').value = src_app_helper_ledgerhelper__WEBPACK_IMPORTED_MODULE_6__["LedgerHelper"].address; //localStorage.getItem('address');
        document.getElementById('i_dob').value = src_app_helper_ledgerhelper__WEBPACK_IMPORTED_MODULE_6__["LedgerHelper"].date_of_birth; //localStorage.getItem('date_of_birth');
        document.getElementById('i_email').value = src_app_helper_ledgerhelper__WEBPACK_IMPORTED_MODULE_6__["LedgerHelper"].email; //localStorage.getItem('email');
        document.getElementById('i_pno').value = src_app_helper_ledgerhelper__WEBPACK_IMPORTED_MODULE_6__["LedgerHelper"].phone_number; //localStorage.getItem('phone_number');
        if (!(src_app_helper_ledgerhelper__WEBPACK_IMPORTED_MODULE_6__["LedgerHelper"].dp === null)) {
            console.log('true' + src_app_helper_ledgerhelper__WEBPACK_IMPORTED_MODULE_6__["LedgerHelper"].dp.trim());
            this.image = this.transform('data:image/jpeg;base64, ' + src_app_helper_ledgerhelper__WEBPACK_IMPORTED_MODULE_6__["LedgerHelper"].dp.trim()); //localStorage.getItem('dp'));
        }
        else {
            this.image = this.transform('data:image/jpeg;base64, ' + src_app_helper_ledgerhelper__WEBPACK_IMPORTED_MODULE_6__["LedgerHelper"].placeholder_img);
        }
    };
    ProfilePageComponent.prototype.onUpdateProfile = function (firstname, lastname, email, dob, pno, address) {
        var _this = this;
        // this.key = JSON.parse(localStorage.getItem('key'));
        var id = src_app_helper_ledgerhelper__WEBPACK_IMPORTED_MODULE_6__["LedgerHelper"]._id; //localStorage.getItem('_id');
        var header = new _angular_common_http__WEBPACK_IMPORTED_MODULE_2__["HttpHeaders"]({
            'Content-Type': 'application/json',
            'Authorization': '' + src_app_helper_ledgerhelper__WEBPACK_IMPORTED_MODULE_6__["LedgerHelper"].token //localStorage.getItem('Token')
        });
        var baseTransaction1 = {
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
        var to_remove = 'data:image/jpeg;base64,';
        var to_remove2 = 'SafeValue must use [property]=binding:';
        var dp = this.image.toString();
        baseTransaction1.$tx.$o[id]['dp'] = dp.replace(to_remove, '').replace(to_remove2, '');
        dp = baseTransaction1.$tx.$o[id]['dp'];
        var txHandler = new _activeledger_sdk__WEBPACK_IMPORTED_MODULE_4__["TransactionHandler"]();
        txHandler
            .signTransaction(baseTransaction1, src_app_helper_ledgerhelper__WEBPACK_IMPORTED_MODULE_6__["LedgerHelper"].key) // JSON.parse(localStorage.getItem('key')))
            .then(function (signedTx) {
            baseTransaction1 = signedTx;
            var signature = baseTransaction1['$sigs']['activeledger'];
            baseTransaction1['$sigs'] = {};
            baseTransaction1['$sigs'][id] = signature;
            console.log(baseTransaction1);
            _this.updateProfile(baseTransaction1, header)
                .subscribe(function (data) {
                console.log(data);
                if (data.status === 200) {
                    _this.message = data.body.resp.desc;
                    _this.open();
                    console.log('profile page --> ' + dp);
                    src_app_helper_ledgerhelper__WEBPACK_IMPORTED_MODULE_6__["LedgerHelper"].address = '' + address;
                    src_app_helper_ledgerhelper__WEBPACK_IMPORTED_MODULE_6__["LedgerHelper"].date_of_birth = '' + dob;
                    src_app_helper_ledgerhelper__WEBPACK_IMPORTED_MODULE_6__["LedgerHelper"].dp = '' + dp;
                    src_app_helper_ledgerhelper__WEBPACK_IMPORTED_MODULE_6__["LedgerHelper"].first_name = '' + firstname;
                    src_app_helper_ledgerhelper__WEBPACK_IMPORTED_MODULE_6__["LedgerHelper"].last_name = '' + lastname;
                    src_app_helper_ledgerhelper__WEBPACK_IMPORTED_MODULE_6__["LedgerHelper"].phone_number = '' + pno;
                    // localStorage.setItem('address', '' + address);
                    // localStorage.setItem('date_of_birth', '' + dob);
                    // localStorage.setItem('dp', '' + dp);
                    // localStorage.setItem('first_name', '' + firstname);
                    // localStorage.setItem('last_name', '' + lastname);
                    // localStorage.setItem('phone_number', '' + pno);
                    _this.disableFields();
                }
                else {
                    _this.message = ' Something went wrong! ';
                    _this.open();
                }
            });
        })
            .catch();
    };
    ProfilePageComponent.prototype.onSelectFile = function (event) {
        var _this = this;
        if (event.target.files && event.target.files[0]) {
            var reader = new FileReader();
            reader.readAsDataURL(event.target.files[0]);
            reader.onload = function (event) {
                // this.image = event.target.result;
                _this.image = reader.result;
            };
        }
    };
    ProfilePageComponent.prototype.updateProfile = function (body, headers1) {
        return this.http.post(src_app_helper_ledgerhelper__WEBPACK_IMPORTED_MODULE_6__["LedgerHelper"].updateProfileUrl, body, { headers: headers1, observe: 'response' });
    };
    ProfilePageComponent.prototype.open = function () {
        var config = new _angular_material__WEBPACK_IMPORTED_MODULE_3__["MatSnackBarConfig"]();
        config.verticalPosition = this.verticalPosition;
        config.horizontalPosition = this.horizontalPosition;
        config.duration = this.setAutoHide ? this.autoHide : 0;
        this.snackBar.open(this.message, this.action ? this.actionButtonLabel : undefined, config);
    };
    ProfilePageComponent.prototype.enableFields = function () {
        this.editing_disabled = false;
        document.getElementById('i_fn').disabled = false;
        document.getElementById('i_ln').disabled = false;
        document.getElementById('i_dob').disabled = false;
        document.getElementById('i_pno').disabled = false;
        document.getElementById('i_address').disabled = false;
        this.hidden = false;
        this.f_nameField.nativeElement.focus();
    };
    ProfilePageComponent.prototype.disableFields = function () {
        this.editing_disabled = true;
        document.getElementById('i_fn').disabled = true;
        document.getElementById('i_ln').disabled = true;
        document.getElementById('i_dob').disabled = true;
        document.getElementById('i_pno').disabled = true;
        document.getElementById('i_address').disabled = true;
        this.hidden = true;
    };
    ProfilePageComponent.prototype.transform = function (html) {
        return this.sanitizer.bypassSecurityTrustUrl(html);
    };
    __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["ViewChild"])('firstname_f'),
        __metadata("design:type", _angular_core__WEBPACK_IMPORTED_MODULE_0__["ElementRef"])
    ], ProfilePageComponent.prototype, "f_nameField", void 0);
    ProfilePageComponent = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"])({
            selector: 'app-profile-page',
            template: __webpack_require__(/*! ./profile-page.component.html */ "./src/app/layout/profile-page/profile-page.component.html"),
            styles: [__webpack_require__(/*! ./profile-page.component.scss */ "./src/app/layout/profile-page/profile-page.component.scss")]
        }),
        __metadata("design:paramtypes", [_angular_router__WEBPACK_IMPORTED_MODULE_1__["Router"], _angular_common_http__WEBPACK_IMPORTED_MODULE_2__["HttpClient"], _angular_material__WEBPACK_IMPORTED_MODULE_3__["MatSnackBar"], _angular_platform_browser__WEBPACK_IMPORTED_MODULE_5__["DomSanitizer"]])
    ], ProfilePageComponent);
    return ProfilePageComponent;
}());



/***/ }),

/***/ "./src/app/layout/profile-page/profile-page.module.ts":
/*!************************************************************!*\
  !*** ./src/app/layout/profile-page/profile-page.module.ts ***!
  \************************************************************/
/*! exports provided: ProfilePageModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "ProfilePageModule", function() { return ProfilePageModule; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_common__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/common */ "./node_modules/@angular/common/fesm5/common.js");
/* harmony import */ var _profile_page_routing_module__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./profile-page-routing.module */ "./src/app/layout/profile-page/profile-page-routing.module.ts");
/* harmony import */ var _profile_page_component__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./profile-page.component */ "./src/app/layout/profile-page/profile-page.component.ts");
/* harmony import */ var _angular_flex_layout__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @angular/flex-layout */ "./node_modules/@angular/flex-layout/esm5/flex-layout.es5.js");
/* harmony import */ var _angular_material__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @angular/material */ "./node_modules/@angular/material/esm5/material.es5.js");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};








var ProfilePageModule = /** @class */ (function () {
    function ProfilePageModule() {
    }
    ProfilePageModule = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["NgModule"])({
            imports: [
                _angular_material__WEBPACK_IMPORTED_MODULE_5__["MatRadioModule"],
                _angular_common__WEBPACK_IMPORTED_MODULE_1__["CommonModule"],
                _profile_page_routing_module__WEBPACK_IMPORTED_MODULE_2__["ProfilePageRoutingModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_5__["MatInputModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_5__["MatCheckboxModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_5__["MatSnackBarModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_5__["MatButtonModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_5__["MatCardModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_5__["MatTableModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_5__["MatIconModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_5__["MatDatepickerModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_5__["MatNativeDateModule"],
                _angular_flex_layout__WEBPACK_IMPORTED_MODULE_4__["FlexLayoutModule"].withConfig({ addFlexToParent: false })
            ],
            declarations: [_profile_page_component__WEBPACK_IMPORTED_MODULE_3__["ProfilePageComponent"]]
        })
    ], ProfilePageModule);
    return ProfilePageModule;
}());



/***/ })

}]);
//# sourceMappingURL=profile-page-profile-page-module.js.map