(window["webpackJsonp"] = window["webpackJsonp"] || []).push([["create-profile-create-profile-module"],{

/***/ "./src/app/create-profile/create-profile-routing.module.ts":
/*!*****************************************************************!*\
  !*** ./src/app/create-profile/create-profile-routing.module.ts ***!
  \*****************************************************************/
/*! exports provided: CreateProfileRoutingModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "CreateProfileRoutingModule", function() { return CreateProfileRoutingModule; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
/* harmony import */ var _create_profile_component__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./create-profile.component */ "./src/app/create-profile/create-profile.component.ts");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};



var routes = [
    {
        path: '',
        component: _create_profile_component__WEBPACK_IMPORTED_MODULE_2__["CreateProfileComponent"]
    }
];
var CreateProfileRoutingModule = /** @class */ (function () {
    function CreateProfileRoutingModule() {
    }
    CreateProfileRoutingModule = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["NgModule"])({
            imports: [_angular_router__WEBPACK_IMPORTED_MODULE_1__["RouterModule"].forChild(routes)],
            exports: [_angular_router__WEBPACK_IMPORTED_MODULE_1__["RouterModule"]]
        })
    ], CreateProfileRoutingModule);
    return CreateProfileRoutingModule;
}());



/***/ }),

/***/ "./src/app/create-profile/create-profile.component.html":
/*!**************************************************************!*\
  !*** ./src/app/create-profile/create-profile.component.html ***!
  \**************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<div class=\"create-profile-page\">\n  <div class=\"content\">\n      <form class=\"create-profile-form\" ng-controller=\"formCtrl\" fxFlex  >\n          <div class=\"text-center\">\n              <h2 class=\"app-name\">DHealth</h2>\n          </div>\n\n            <div fxFlex fxLayout=\"row\" fxLayout.lt-md=\"column\" class=\"text-center\">\n                <div fxFlexFill>\n                    <img id=\"i_img\" [src]=\"image\" #img_f mat-card-image class = \"image\"><br>\n                    <input mat-raised-button color=\"primary\" type='file' (change)=\"onSelectFile($event)\">\n\n                </div>\n            </div>\n\n\n          <div fxFlex  fxLayout=\"row\" fxLayout.lt-md=\"column\" >\n            <div fxFlex style=\"margin: 0px 20px 0px 0\">\n                <div fxFlexFill>\n                    <mat-form-field class=\"w-100\">\n                        <input #firstname_f matInput placeholder=\"First Name  \" >\n                    </mat-form-field>\n                </div>\n            </div>\n\n            <div fxFlex fxLayoutAlign=\"end\">\n                <div fxFlexFill>\n                    <mat-form-field class=\"w-100\">\n                        <input #lastname_f matInput placeholder=\"Last Name  \" >\n                    </mat-form-field>\n                </div>\n            </div>\n        </div>\n\n          <div fxFlex  fxlayout=\"row\" fxlayout.lt-md=\"column\">\n              <div fxFlexFill>\n                  <mat-form-field class=\"w-100\">\n                      <input id=\"i_email\" #email_f matInput placeholder=\"Email  \"  disabled=\"disable\">\n                  </mat-form-field>\n              </div>\n          </div>\n\n          <div fxFlex  fxlayout=\"row\" fxlayout.lt-md=\"column\">\n            <div fxFlexFill>\n            \n                            <mat-form-field class=\"w-100\">\n                                <input #dob_f matInput [matDatepicker]=\"picker\" placeholder=\"Date of Birth\">\n                                <mat-datepicker-toggle matSuffix [for]=\"picker\"></mat-datepicker-toggle>\n                                <mat-datepicker #picker></mat-datepicker>\n                            </mat-form-field>\n                 \n            </div>\n\n\n        </div>\n\n        <div fxFlex  fxlayout=\"row\" fxlayout.lt-md=\"column\">\n            <div fxFlexFill>\n                <mat-form-field class=\"w-100\">\n                    <input #pno_f matInput placeholder=\"Phone Number  \" >\n                </mat-form-field>\n            </div>\n        </div>\n\n        <div fxFlex  fxlayout=\"row\" fxlayout.lt-md=\"column\">\n            <div fxFlexFill>\n                <mat-form-field class=\"w-100\">\n                    <input #address_f matInput placeholder=\"Address  \" >\n                </mat-form-field>\n            </div>\n        </div>\n  \n        <div fxFlex  fxlayout=\"row\" fxlayout.lt-md=\"column\">\n                <div fxFlexFill>\n                    <mat-form-field class=\"w-100\">\n                <mat-select placeholder=\"Select User Type\" [(value)]=\"user_type\">\n                    <mat-option value=\"Doctor\">Doctor</mat-option>\n                    <mat-option value=\"patient\">Patient</mat-option>\n                </mat-select>\n            </mat-form-field>\n        </div>\n    </div>\n          \n\n          <div fxFlex  fxLayout=\"row\" fxLayout.lt-md=\"column\" >\n            <div fxFlexFill>\n                <button mat-raised-button color=\"primary\" class=\"w-100\" (click)=\"onCreateProfile(firstname_f.value,lastname_f.value,email_f.value,dob_f.value,pno_f.value,address_f.value)\" type = 'button'>Create Profile</button>\n            </div>\n        </div>\n\n      </form>\n  </div>\n</div>"

/***/ }),

/***/ "./src/app/create-profile/create-profile.component.scss":
/*!**************************************************************!*\
  !*** ./src/app/create-profile/create-profile.component.scss ***!
  \**************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = ".create-profile-page {\n  display: flex;\n  align-items: center;\n  justify-content: center;\n  height: 100%;\n  position: relative; }\n  .create-profile-page .content {\n    z-index: 1;\n    display: flex;\n    align-items: center;\n    justify-content: center; }\n  .create-profile-page .content .app-name {\n      margin-top: 0px;\n      padding-bottom: 10px;\n      font-size: 32px; }\n  .create-profile-page .content .create-profile-form {\n      padding: 40px;\n      background: #fff;\n      width: 500px;\n      box-shadow: 0 0 10px #ddd; }\n  .create-profile-page .content .create-profile-form input:-webkit-autofill {\n        -webkit-box-shadow: 0 0 0 30px white inset; }\n  .create-profile-page:after {\n    content: '';\n    background: #fff;\n    position: absolute;\n    top: 0;\n    left: 0;\n    bottom: 50%;\n    right: 0; }\n  .create-profile-page:before {\n    content: '';\n    background: #3f51b5;\n    position: absolute;\n    top: 50%;\n    left: 0;\n    bottom: 0;\n    right: 0; }\n  .text-center {\n  text-align: center; }\n  .w-100 {\n  width: 100%; }\n  .example-h2 {\n  margin: 10px; }\n  .example-section {\n  display: flex;\n  align-content: center;\n  align-items: center;\n  height: 60px; }\n  .example-margin {\n  margin: 0 10px; }\n  .image {\n  height: 40%;\n  width: 40%; }\n\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbInNyYy9hcHAvY3JlYXRlLXByb2ZpbGUvQzpcXFVzZXJzXFxIYW1pZFxcRGVza3RvcFxcQUxTREtcXGV4YW1wbGVzXFxzYi1hZG1pbi1tYXRlcmlhbC9zcmNcXGFwcFxcY3JlYXRlLXByb2ZpbGVcXGNyZWF0ZS1wcm9maWxlLmNvbXBvbmVudC5zY3NzIl0sIm5hbWVzIjpbXSwibWFwcGluZ3MiOiJBQUFBO0VBQ0ksYUFBYTtFQUNiLG1CQUFtQjtFQUNuQix1QkFBdUI7RUFDdkIsWUFBWTtFQUNaLGtCQUFrQixFQUFBO0VBTHRCO0lBT1EsVUFBVTtJQUNWLGFBQWE7SUFDYixtQkFBbUI7SUFDbkIsdUJBQXVCLEVBQUE7RUFWL0I7TUFZWSxlQUFlO01BQ2Ysb0JBQW9CO01BQ3BCLGVBQWUsRUFBQTtFQWQzQjtNQWlCWSxhQUFhO01BQ2IsZ0JBQWdCO01BQ2hCLFlBQVk7TUFDWix5QkFBeUIsRUFBQTtFQXBCckM7UUFzQmdCLDBDQUEwQyxFQUFBO0VBdEIxRDtJQTRCUSxXQUFXO0lBQ1gsZ0JBQWdCO0lBQ2hCLGtCQUFrQjtJQUNsQixNQUFNO0lBQ04sT0FBTztJQUNQLFdBQVc7SUFDWCxRQUFRLEVBQUE7RUFsQ2hCO0lBcUNRLFdBQVc7SUFDWCxtQkFBbUI7SUFDbkIsa0JBQWtCO0lBQ2xCLFFBQVE7SUFDUixPQUFPO0lBQ1AsU0FBUztJQUNULFFBQVEsRUFBQTtFQUdoQjtFQUNJLGtCQUFrQixFQUFBO0VBRXRCO0VBQ0ksV0FBVyxFQUFBO0VBR2Y7RUFDSSxZQUFZLEVBQUE7RUFHZDtFQUNFLGFBQWE7RUFDYixxQkFBcUI7RUFDckIsbUJBQW1CO0VBQ25CLFlBQVksRUFBQTtFQUdkO0VBQ0UsY0FBYyxFQUFBO0VBR2hCO0VBQ0csV0FBVztFQUNYLFVBQVUsRUFBQSIsImZpbGUiOiJzcmMvYXBwL2NyZWF0ZS1wcm9maWxlL2NyZWF0ZS1wcm9maWxlLmNvbXBvbmVudC5zY3NzIiwic291cmNlc0NvbnRlbnQiOlsiLmNyZWF0ZS1wcm9maWxlLXBhZ2Uge1xyXG4gICAgZGlzcGxheTogZmxleDtcclxuICAgIGFsaWduLWl0ZW1zOiBjZW50ZXI7XHJcbiAgICBqdXN0aWZ5LWNvbnRlbnQ6IGNlbnRlcjtcclxuICAgIGhlaWdodDogMTAwJTtcclxuICAgIHBvc2l0aW9uOiByZWxhdGl2ZTtcclxuICAgIC5jb250ZW50IHtcclxuICAgICAgICB6LWluZGV4OiAxO1xyXG4gICAgICAgIGRpc3BsYXk6IGZsZXg7XHJcbiAgICAgICAgYWxpZ24taXRlbXM6IGNlbnRlcjtcclxuICAgICAgICBqdXN0aWZ5LWNvbnRlbnQ6IGNlbnRlcjtcclxuICAgICAgICAuYXBwLW5hbWUge1xyXG4gICAgICAgICAgICBtYXJnaW4tdG9wOiAwcHg7XHJcbiAgICAgICAgICAgIHBhZGRpbmctYm90dG9tOiAxMHB4O1xyXG4gICAgICAgICAgICBmb250LXNpemU6IDMycHg7XHJcbiAgICAgICAgfVxyXG4gICAgICAgIC5jcmVhdGUtcHJvZmlsZS1mb3JtIHtcclxuICAgICAgICAgICAgcGFkZGluZzogNDBweDtcclxuICAgICAgICAgICAgYmFja2dyb3VuZDogI2ZmZjtcclxuICAgICAgICAgICAgd2lkdGg6IDUwMHB4O1xyXG4gICAgICAgICAgICBib3gtc2hhZG93OiAwIDAgMTBweCAjZGRkO1xyXG4gICAgICAgICAgICBpbnB1dDotd2Via2l0LWF1dG9maWxsIHtcclxuICAgICAgICAgICAgICAgIC13ZWJraXQtYm94LXNoYWRvdzogMCAwIDAgMzBweCB3aGl0ZSBpbnNldDtcclxuICAgICAgICAgICAgfVxyXG4gICAgICAgIH1cclxuICAgIH1cclxuXHJcbiAgICAmOmFmdGVyIHtcclxuICAgICAgICBjb250ZW50OiAnJztcclxuICAgICAgICBiYWNrZ3JvdW5kOiAjZmZmO1xyXG4gICAgICAgIHBvc2l0aW9uOiBhYnNvbHV0ZTtcclxuICAgICAgICB0b3A6IDA7XHJcbiAgICAgICAgbGVmdDogMDtcclxuICAgICAgICBib3R0b206IDUwJTtcclxuICAgICAgICByaWdodDogMDtcclxuICAgIH1cclxuICAgICY6YmVmb3JlIHtcclxuICAgICAgICBjb250ZW50OiAnJztcclxuICAgICAgICBiYWNrZ3JvdW5kOiAjM2Y1MWI1O1xyXG4gICAgICAgIHBvc2l0aW9uOiBhYnNvbHV0ZTtcclxuICAgICAgICB0b3A6IDUwJTtcclxuICAgICAgICBsZWZ0OiAwO1xyXG4gICAgICAgIGJvdHRvbTogMDtcclxuICAgICAgICByaWdodDogMDtcclxuICAgIH1cclxufVxyXG4udGV4dC1jZW50ZXIge1xyXG4gICAgdGV4dC1hbGlnbjogY2VudGVyO1xyXG59XHJcbi53LTEwMCB7XHJcbiAgICB3aWR0aDogMTAwJTtcclxufVxyXG5cclxuLmV4YW1wbGUtaDIge1xyXG4gICAgbWFyZ2luOiAxMHB4O1xyXG4gIH1cclxuICBcclxuICAuZXhhbXBsZS1zZWN0aW9uIHtcclxuICAgIGRpc3BsYXk6IGZsZXg7XHJcbiAgICBhbGlnbi1jb250ZW50OiBjZW50ZXI7XHJcbiAgICBhbGlnbi1pdGVtczogY2VudGVyO1xyXG4gICAgaGVpZ2h0OiA2MHB4O1xyXG4gIH1cclxuICBcclxuICAuZXhhbXBsZS1tYXJnaW4ge1xyXG4gICAgbWFyZ2luOiAwIDEwcHg7XHJcbiAgfVxyXG5cclxuICAuaW1hZ2Uge1xyXG4gICAgIGhlaWdodDogNDAlO1xyXG4gICAgIHdpZHRoOiA0MCU7IFxyXG4gIH0iXX0= */"

/***/ }),

/***/ "./src/app/create-profile/create-profile.component.ts":
/*!************************************************************!*\
  !*** ./src/app/create-profile/create-profile.component.ts ***!
  \************************************************************/
/*! exports provided: CreateProfileComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "CreateProfileComponent", function() { return CreateProfileComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/common/http */ "./node_modules/@angular/common/fesm5/http.js");
/* harmony import */ var _angular_material__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @angular/material */ "./node_modules/@angular/material/esm5/material.es5.js");
/* harmony import */ var _activeledger_sdk__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @activeledger/sdk */ "./node_modules/@activeledger/sdk/lib/index.js");
/* harmony import */ var _activeledger_sdk__WEBPACK_IMPORTED_MODULE_4___default = /*#__PURE__*/__webpack_require__.n(_activeledger_sdk__WEBPACK_IMPORTED_MODULE_4__);
/* harmony import */ var _angular_platform_browser__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @angular/platform-browser */ "./node_modules/@angular/platform-browser/fesm5/platform-browser.js");
/* harmony import */ var _helper_ledgerhelper__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! ../helper/ledgerhelper */ "./src/app/helper/ledgerhelper.ts");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};









var CreateProfileComponent = /** @class */ (function () {
    function CreateProfileComponent(ledgerHelper, router, http, snackBar, sanitizer) {
        this.ledgerHelper = ledgerHelper;
        this.router = router;
        this.http = http;
        this.snackBar = snackBar;
        this.sanitizer = sanitizer;
        this.image_set = false;
        this.message = 'Snack Bar opened.';
        this.actionButtonLabel = 'Close';
        this.action = true;
        this.setAutoHide = true;
        this.autoHide = 2000;
        this.horizontalPosition = 'center';
        this.verticalPosition = 'bottom';
        this.addExtraClass = false;
        this.user_type = 'Doctor';
    }
    CreateProfileComponent.prototype.ngOnInit = function () {
        this.generateKeys();
        document.getElementById('i_email').value = _helper_ledgerhelper__WEBPACK_IMPORTED_MODULE_6__["LedgerHelper"].email;
        this.image = this.transform('data:image/jpeg;base64, ' + _helper_ledgerhelper__WEBPACK_IMPORTED_MODULE_6__["LedgerHelper"].placeholder_img);
    };
    CreateProfileComponent.prototype.onCreateProfile = function (firstname, lastname, email, dob, pno, address) {
        var _this = this;
        _helper_ledgerhelper__WEBPACK_IMPORTED_MODULE_6__["LedgerHelper"].gender = 'Male';
        var header = new _angular_common_http__WEBPACK_IMPORTED_MODULE_2__["HttpHeaders"]({
            'Content-Type': 'application/json',
            'Authorization': '' + _helper_ledgerhelper__WEBPACK_IMPORTED_MODULE_6__["LedgerHelper"].token
        });
        var display = null;
        if (this.image_set) {
            var to_remove = 'data:image/jpeg;base64,';
            display = this.image.toString();
            display = display.replace(to_remove, '');
        }
        var baseTransaction = {
            $tx: {
                $contract: 'user',
                $namespace: 'healthtestnet',
                $entry: 'create',
                $i: {
                    activeledger: {
                        publicKey: _helper_ledgerhelper__WEBPACK_IMPORTED_MODULE_6__["LedgerHelper"].key.key.pub.pkcs8pem,
                        type: 'rsa',
                        first_name: firstname,
                        last_name: lastname,
                        email: email,
                        date_of_birth: dob,
                        phone_number: pno,
                        address: address,
                        security: 'RSA',
                        profile_type: this.user_type,
                        gender: _helper_ledgerhelper__WEBPACK_IMPORTED_MODULE_6__["LedgerHelper"].gender,
                        dp: display
                    }
                },
            },
            $selfsign: true,
            $sigs: {}
        };
        var txHandler = new _activeledger_sdk__WEBPACK_IMPORTED_MODULE_4__["TransactionHandler"]();
        txHandler
            .signTransaction(baseTransaction, _helper_ledgerhelper__WEBPACK_IMPORTED_MODULE_6__["LedgerHelper"].key)
            .then(function (signedTx) {
            baseTransaction = signedTx;
            console.log(baseTransaction);
        })
            .catch();
        this.createProfile(baseTransaction, header)
            .subscribe(function (data) {
            console.log(data);
            if (data.status === 200) {
                _this.message = data.body.resp.desc;
                _this.open();
                _helper_ledgerhelper__WEBPACK_IMPORTED_MODULE_6__["LedgerHelper"].address = '' + data.body.streams.address;
                _helper_ledgerhelper__WEBPACK_IMPORTED_MODULE_6__["LedgerHelper"].date_of_birth = '' + data.body.streams.date_of_birth;
                _helper_ledgerhelper__WEBPACK_IMPORTED_MODULE_6__["LedgerHelper"].dp = '' + data.body.streams.dp;
                _helper_ledgerhelper__WEBPACK_IMPORTED_MODULE_6__["LedgerHelper"].email = '' + data.body.streams.email;
                _helper_ledgerhelper__WEBPACK_IMPORTED_MODULE_6__["LedgerHelper"].first_name = '' + data.body.streams.first_name;
                _helper_ledgerhelper__WEBPACK_IMPORTED_MODULE_6__["LedgerHelper"].last_name = '' + data.body.streams.last_name;
                _helper_ledgerhelper__WEBPACK_IMPORTED_MODULE_6__["LedgerHelper"].gender = '' + data.body.streams.gender;
                _helper_ledgerhelper__WEBPACK_IMPORTED_MODULE_6__["LedgerHelper"].phone_number = '' + data.body.streams.phone_number;
                _helper_ledgerhelper__WEBPACK_IMPORTED_MODULE_6__["LedgerHelper"].profile_type = '' + data.body.streams.profile_type;
                _this.ledgerHelper.reports = '' + data.body.streams.reports;
                _helper_ledgerhelper__WEBPACK_IMPORTED_MODULE_6__["LedgerHelper"].security = '' + data.body.streams.security;
                _helper_ledgerhelper__WEBPACK_IMPORTED_MODULE_6__["LedgerHelper"]._id = '' + data.body.streams._id;
                _helper_ledgerhelper__WEBPACK_IMPORTED_MODULE_6__["LedgerHelper"].profileCreated = '' + true;
                _helper_ledgerhelper__WEBPACK_IMPORTED_MODULE_6__["LedgerHelper"].isLoggedin = '' + true;
                _this.router.navigate(['/dashboard']);
            }
            else {
                _this.message = ' Something went wrong! ';
                _this.open();
            }
        });
    };
    CreateProfileComponent.prototype.onSelectFile = function (event) {
        var _this = this;
        if (event.target.files && event.target.files[0]) {
            var reader = new FileReader();
            reader.readAsDataURL(event.target.files[0]);
            reader.onload = function (event) {
                _this.image_set = true;
                _this.image = reader.result;
            };
        }
    };
    CreateProfileComponent.prototype.createProfile = function (body, header) {
        return this.http.post(_helper_ledgerhelper__WEBPACK_IMPORTED_MODULE_6__["LedgerHelper"].createProfileUrl, body, { headers: header, observe: 'response' });
    };
    CreateProfileComponent.prototype.generateKeys = function () {
        var keyHandler = new _activeledger_sdk__WEBPACK_IMPORTED_MODULE_4__["KeyHandler"]();
        keyHandler
            .generateKey('activeledger', _activeledger_sdk__WEBPACK_IMPORTED_MODULE_4__["KeyType"].RSA)
            .then(function (generatedKey) {
            _helper_ledgerhelper__WEBPACK_IMPORTED_MODULE_6__["LedgerHelper"].key = generatedKey;
        })
            .catch();
    };
    CreateProfileComponent.prototype.transform = function (html) {
        return this.sanitizer.bypassSecurityTrustUrl(html);
    };
    CreateProfileComponent.prototype.open = function () {
        var config = new _angular_material__WEBPACK_IMPORTED_MODULE_3__["MatSnackBarConfig"]();
        config.verticalPosition = this.verticalPosition;
        config.horizontalPosition = this.horizontalPosition;
        config.duration = this.setAutoHide ? this.autoHide : 0;
        this.snackBar.open(this.message, this.action ? this.actionButtonLabel : undefined, config);
    };
    CreateProfileComponent = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"])({
            selector: 'app-create-profile',
            template: __webpack_require__(/*! ./create-profile.component.html */ "./src/app/create-profile/create-profile.component.html"),
            styles: [__webpack_require__(/*! ./create-profile.component.scss */ "./src/app/create-profile/create-profile.component.scss")]
        }),
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Injectable"])(),
        __metadata("design:paramtypes", [_helper_ledgerhelper__WEBPACK_IMPORTED_MODULE_6__["LedgerHelper"], _angular_router__WEBPACK_IMPORTED_MODULE_1__["Router"],
            _angular_common_http__WEBPACK_IMPORTED_MODULE_2__["HttpClient"], _angular_material__WEBPACK_IMPORTED_MODULE_3__["MatSnackBar"], _angular_platform_browser__WEBPACK_IMPORTED_MODULE_5__["DomSanitizer"]])
    ], CreateProfileComponent);
    return CreateProfileComponent;
}());



/***/ }),

/***/ "./src/app/create-profile/create-profile.module.ts":
/*!*********************************************************!*\
  !*** ./src/app/create-profile/create-profile.module.ts ***!
  \*********************************************************/
/*! exports provided: CreateProfileModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "CreateProfileModule", function() { return CreateProfileModule; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_common__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/common */ "./node_modules/@angular/common/fesm5/common.js");
/* harmony import */ var _angular_flex_layout__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/flex-layout */ "./node_modules/@angular/flex-layout/esm5/flex-layout.es5.js");
/* harmony import */ var _create_profile_routing_module__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./create-profile-routing.module */ "./src/app/create-profile/create-profile-routing.module.ts");
/* harmony import */ var _create_profile_component__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ./create-profile.component */ "./src/app/create-profile/create-profile.component.ts");
/* harmony import */ var _angular_material__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @angular/material */ "./node_modules/@angular/material/esm5/material.es5.js");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};







var CreateProfileModule = /** @class */ (function () {
    function CreateProfileModule() {
    }
    CreateProfileModule = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["NgModule"])({
            imports: [
                _angular_common__WEBPACK_IMPORTED_MODULE_1__["CommonModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_5__["MatAutocompleteModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_5__["MatSlideToggleModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_5__["MatFormFieldModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_5__["MatInputModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_5__["MatCardModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_5__["MatButtonModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_5__["MatCheckboxModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_5__["MatRadioModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_5__["MatDatepickerModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_5__["MatNativeDateModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_5__["MatFormFieldModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_5__["MatSelectModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_5__["MatSliderModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_5__["MatRadioModule"],
                _angular_common__WEBPACK_IMPORTED_MODULE_1__["CommonModule"],
                _create_profile_routing_module__WEBPACK_IMPORTED_MODULE_3__["CreateProfileRoutingModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_5__["MatInputModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_5__["MatCheckboxModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_5__["MatSnackBarModule"],
                _angular_flex_layout__WEBPACK_IMPORTED_MODULE_2__["FlexLayoutModule"].withConfig({ addFlexToParent: false })
            ],
            declarations: [_create_profile_component__WEBPACK_IMPORTED_MODULE_4__["CreateProfileComponent"],
            ]
        })
    ], CreateProfileModule);
    return CreateProfileModule;
}());



/***/ })

}]);
//# sourceMappingURL=create-profile-create-profile-module.js.map