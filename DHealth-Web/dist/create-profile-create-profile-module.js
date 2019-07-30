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

module.exports = ".create-profile-page {\n  display: flex;\n  align-items: center;\n  justify-content: center;\n  height: 100%;\n  position: relative; }\n  .create-profile-page .content {\n    z-index: 1;\n    display: flex;\n    align-items: center;\n    justify-content: center; }\n  .create-profile-page .content .app-name {\n      margin-top: 0px;\n      padding-bottom: 10px;\n      font-size: 32px; }\n  .create-profile-page .content .create-profile-form {\n      padding: 40px;\n      background: #fff;\n      width: 500px;\n      box-shadow: 0 0 10px #ddd; }\n  .create-profile-page .content .create-profile-form input:-webkit-autofill {\n        -webkit-box-shadow: 0 0 0 30px white inset; }\n  .create-profile-page:after {\n    content: '';\n    background: #fff;\n    position: absolute;\n    top: 0;\n    left: 0;\n    bottom: 50%;\n    right: 0; }\n  .create-profile-page:before {\n    content: '';\n    background: #3f51b5;\n    position: absolute;\n    top: 50%;\n    left: 0;\n    bottom: 0;\n    right: 0; }\n  .text-center {\n  text-align: center; }\n  .w-100 {\n  width: 100%; }\n  .example-h2 {\n  margin: 10px; }\n  .example-section {\n  display: flex;\n  align-content: center;\n  align-items: center;\n  height: 60px; }\n  .example-margin {\n  margin: 0 10px; }\n  .image {\n  height: 40%;\n  width: 40%; }\n\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbInNyYy9hcHAvY3JlYXRlLXByb2ZpbGUvQzpcXFVzZXJzXFxIYW1pZFxcRGVza3RvcFxcQUxTREtcXERIZWFsdGhcXGhlYWx0aFxcREhlYWx0aC1XZWIvc3JjXFxhcHBcXGNyZWF0ZS1wcm9maWxlXFxjcmVhdGUtcHJvZmlsZS5jb21wb25lbnQuc2NzcyJdLCJuYW1lcyI6W10sIm1hcHBpbmdzIjoiQUFBQTtFQUNJLGFBQWE7RUFDYixtQkFBbUI7RUFDbkIsdUJBQXVCO0VBQ3ZCLFlBQVk7RUFDWixrQkFBa0IsRUFBQTtFQUx0QjtJQU9RLFVBQVU7SUFDVixhQUFhO0lBQ2IsbUJBQW1CO0lBQ25CLHVCQUF1QixFQUFBO0VBVi9CO01BWVksZUFBZTtNQUNmLG9CQUFvQjtNQUNwQixlQUFlLEVBQUE7RUFkM0I7TUFpQlksYUFBYTtNQUNiLGdCQUFnQjtNQUNoQixZQUFZO01BQ1oseUJBQXlCLEVBQUE7RUFwQnJDO1FBc0JnQiwwQ0FBMEMsRUFBQTtFQXRCMUQ7SUE0QlEsV0FBVztJQUNYLGdCQUFnQjtJQUNoQixrQkFBa0I7SUFDbEIsTUFBTTtJQUNOLE9BQU87SUFDUCxXQUFXO0lBQ1gsUUFBUSxFQUFBO0VBbENoQjtJQXFDUSxXQUFXO0lBQ1gsbUJBQW1CO0lBQ25CLGtCQUFrQjtJQUNsQixRQUFRO0lBQ1IsT0FBTztJQUNQLFNBQVM7SUFDVCxRQUFRLEVBQUE7RUFHaEI7RUFDSSxrQkFBa0IsRUFBQTtFQUV0QjtFQUNJLFdBQVcsRUFBQTtFQUdmO0VBQ0ksWUFBWSxFQUFBO0VBR2Q7RUFDRSxhQUFhO0VBQ2IscUJBQXFCO0VBQ3JCLG1CQUFtQjtFQUNuQixZQUFZLEVBQUE7RUFHZDtFQUNFLGNBQWMsRUFBQTtFQUdoQjtFQUNHLFdBQVc7RUFDWCxVQUFVLEVBQUEiLCJmaWxlIjoic3JjL2FwcC9jcmVhdGUtcHJvZmlsZS9jcmVhdGUtcHJvZmlsZS5jb21wb25lbnQuc2NzcyIsInNvdXJjZXNDb250ZW50IjpbIi5jcmVhdGUtcHJvZmlsZS1wYWdlIHtcclxuICAgIGRpc3BsYXk6IGZsZXg7XHJcbiAgICBhbGlnbi1pdGVtczogY2VudGVyO1xyXG4gICAganVzdGlmeS1jb250ZW50OiBjZW50ZXI7XHJcbiAgICBoZWlnaHQ6IDEwMCU7XHJcbiAgICBwb3NpdGlvbjogcmVsYXRpdmU7XHJcbiAgICAuY29udGVudCB7XHJcbiAgICAgICAgei1pbmRleDogMTtcclxuICAgICAgICBkaXNwbGF5OiBmbGV4O1xyXG4gICAgICAgIGFsaWduLWl0ZW1zOiBjZW50ZXI7XHJcbiAgICAgICAganVzdGlmeS1jb250ZW50OiBjZW50ZXI7XHJcbiAgICAgICAgLmFwcC1uYW1lIHtcclxuICAgICAgICAgICAgbWFyZ2luLXRvcDogMHB4O1xyXG4gICAgICAgICAgICBwYWRkaW5nLWJvdHRvbTogMTBweDtcclxuICAgICAgICAgICAgZm9udC1zaXplOiAzMnB4O1xyXG4gICAgICAgIH1cclxuICAgICAgICAuY3JlYXRlLXByb2ZpbGUtZm9ybSB7XHJcbiAgICAgICAgICAgIHBhZGRpbmc6IDQwcHg7XHJcbiAgICAgICAgICAgIGJhY2tncm91bmQ6ICNmZmY7XHJcbiAgICAgICAgICAgIHdpZHRoOiA1MDBweDtcclxuICAgICAgICAgICAgYm94LXNoYWRvdzogMCAwIDEwcHggI2RkZDtcclxuICAgICAgICAgICAgaW5wdXQ6LXdlYmtpdC1hdXRvZmlsbCB7XHJcbiAgICAgICAgICAgICAgICAtd2Via2l0LWJveC1zaGFkb3c6IDAgMCAwIDMwcHggd2hpdGUgaW5zZXQ7XHJcbiAgICAgICAgICAgIH1cclxuICAgICAgICB9XHJcbiAgICB9XHJcblxyXG4gICAgJjphZnRlciB7XHJcbiAgICAgICAgY29udGVudDogJyc7XHJcbiAgICAgICAgYmFja2dyb3VuZDogI2ZmZjtcclxuICAgICAgICBwb3NpdGlvbjogYWJzb2x1dGU7XHJcbiAgICAgICAgdG9wOiAwO1xyXG4gICAgICAgIGxlZnQ6IDA7XHJcbiAgICAgICAgYm90dG9tOiA1MCU7XHJcbiAgICAgICAgcmlnaHQ6IDA7XHJcbiAgICB9XHJcbiAgICAmOmJlZm9yZSB7XHJcbiAgICAgICAgY29udGVudDogJyc7XHJcbiAgICAgICAgYmFja2dyb3VuZDogIzNmNTFiNTtcclxuICAgICAgICBwb3NpdGlvbjogYWJzb2x1dGU7XHJcbiAgICAgICAgdG9wOiA1MCU7XHJcbiAgICAgICAgbGVmdDogMDtcclxuICAgICAgICBib3R0b206IDA7XHJcbiAgICAgICAgcmlnaHQ6IDA7XHJcbiAgICB9XHJcbn1cclxuLnRleHQtY2VudGVyIHtcclxuICAgIHRleHQtYWxpZ246IGNlbnRlcjtcclxufVxyXG4udy0xMDAge1xyXG4gICAgd2lkdGg6IDEwMCU7XHJcbn1cclxuXHJcbi5leGFtcGxlLWgyIHtcclxuICAgIG1hcmdpbjogMTBweDtcclxuICB9XHJcbiAgXHJcbiAgLmV4YW1wbGUtc2VjdGlvbiB7XHJcbiAgICBkaXNwbGF5OiBmbGV4O1xyXG4gICAgYWxpZ24tY29udGVudDogY2VudGVyO1xyXG4gICAgYWxpZ24taXRlbXM6IGNlbnRlcjtcclxuICAgIGhlaWdodDogNjBweDtcclxuICB9XHJcbiAgXHJcbiAgLmV4YW1wbGUtbWFyZ2luIHtcclxuICAgIG1hcmdpbjogMCAxMHB4O1xyXG4gIH1cclxuXHJcbiAgLmltYWdlIHtcclxuICAgICBoZWlnaHQ6IDQwJTtcclxuICAgICB3aWR0aDogNDAlOyBcclxuICB9Il19 */"

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
/* harmony import */ var _activeledger_sdk__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @activeledger/sdk */ "./node_modules/@activeledger/sdk/lib/index.js");
/* harmony import */ var _activeledger_sdk__WEBPACK_IMPORTED_MODULE_3___default = /*#__PURE__*/__webpack_require__.n(_activeledger_sdk__WEBPACK_IMPORTED_MODULE_3__);
/* harmony import */ var _angular_platform_browser__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @angular/platform-browser */ "./node_modules/@angular/platform-browser/fesm5/platform-browser.js");
/* harmony import */ var _helper_ledgerhelper__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! ../helper/ledgerhelper */ "./src/app/helper/ledgerhelper.ts");
/* harmony import */ var _layout_layout_component__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! ../layout/layout.component */ "./src/app/layout/layout.component.ts");
/* harmony import */ var ng2_img_max__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! ng2-img-max */ "./node_modules/ng2-img-max/dist/ng2-img-max.js");
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
    function CreateProfileComponent(layoutComp, ledgerHelper, router, http, sanitizer, ng2ImgMax) {
        this.layoutComp = layoutComp;
        this.ledgerHelper = ledgerHelper;
        this.router = router;
        this.http = http;
        this.sanitizer = sanitizer;
        this.ng2ImgMax = ng2ImgMax;
        this.image_set = false;
        this.user_type = 'Doctor';
    }
    CreateProfileComponent.prototype.ngOnInit = function () {
        this.generateKeys();
        document.getElementById('i_email').value = this.ledgerHelper.email;
        this.image = this.transform('data:image/jpeg;base64, ' + this.ledgerHelper.placeholder_img);
    };
    CreateProfileComponent.prototype.onCreateProfile = function (firstname, lastname, email, dob, pno, address) {
        var _this = this;
        this.ledgerHelper.gender = 'Male';
        var header = new _angular_common_http__WEBPACK_IMPORTED_MODULE_2__["HttpHeaders"]({
            'Content-Type': 'application/json',
            'Authorization': '' + this.ledgerHelper.token
        });
        var display = null;
        if (this.image_set) {
            display = this.image.toString();
            display = this.ledgerHelper.sanitizeDP(display);
        }
        var baseTransaction = {
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
        var txHandler = new _activeledger_sdk__WEBPACK_IMPORTED_MODULE_3__["TransactionHandler"]();
        txHandler
            .signTransaction(baseTransaction, this.ledgerHelper.key)
            .then(function (signedTx) {
            baseTransaction = signedTx;
            console.log(baseTransaction);
        })
            .catch();
        this.createProfile(baseTransaction, header)
            .subscribe(function (data) {
            console.log(data);
            if (data.status === 200) {
                _this.layoutComp.showSnackBar(data.body.resp.desc);
                _this.ledgerHelper.address = '' + data.body.streams.address;
                _this.ledgerHelper.date_of_birth = '' + data.body.streams.date_of_birth;
                _this.ledgerHelper.dp = '' + data.body.streams.dp;
                _this.ledgerHelper.email = '' + data.body.streams.email;
                _this.ledgerHelper.first_name = '' + data.body.streams.first_name;
                _this.ledgerHelper.last_name = '' + data.body.streams.last_name;
                _this.ledgerHelper.gender = '' + data.body.streams.gender;
                _this.ledgerHelper.phone_number = '' + data.body.streams.phone_number;
                _this.ledgerHelper.profile_type = '' + data.body.streams.profile_type;
                _this.ledgerHelper.reports = '' + data.body.streams.reports;
                _this.ledgerHelper.security = '' + data.body.streams.security;
                _this.ledgerHelper._id = '' + data.body.streams._id;
                _this.ledgerHelper.profileCreated = '' + true;
                _this.ledgerHelper.isLoggedin = '' + true;
                _this.router.navigate(['/dashboard']);
            }
            else {
                _this.layoutComp.showSnackBar(' Something went wrong! ');
            }
        });
    };
    CreateProfileComponent.prototype.onSelectFile = function (event) {
        var _this = this;
        if (event.target.files && event.target.files[0]) {
            var reader_1 = new FileReader();
            this.ng2ImgMax.resizeImage(event.target.files[0], 400, 300).subscribe(function (result) {
                reader_1.readAsDataURL(result);
                reader_1.onload = function () {
                    _this.image_set = true;
                    _this.image = reader_1.result;
                };
            }, function (error) {
                console.log('😢 Oh no!', error);
            });
            // reader.readAsDataURL(event.target.files[0]);
            // reader.onload = () => {
            //   this.image_set = true;
            //   this.image = reader.result;
            // };
        }
    };
    CreateProfileComponent.prototype.createProfile = function (body, header) {
        return this.http.post(this.ledgerHelper.createProfileUrl, body, { headers: header, observe: 'response' });
    };
    CreateProfileComponent.prototype.generateKeys = function () {
        var _this = this;
        var keyHandler = new _activeledger_sdk__WEBPACK_IMPORTED_MODULE_3__["KeyHandler"]();
        keyHandler
            .generateKey('activeledger', _activeledger_sdk__WEBPACK_IMPORTED_MODULE_3__["KeyType"].RSA)
            .then(function (generatedKey) {
            _this.ledgerHelper.key = generatedKey;
        })
            .catch();
    };
    CreateProfileComponent.prototype.transform = function (html) {
        return this.sanitizer.bypassSecurityTrustUrl(html);
    };
    CreateProfileComponent = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"])({
            selector: 'app-create-profile',
            template: __webpack_require__(/*! ./create-profile.component.html */ "./src/app/create-profile/create-profile.component.html"),
            styles: [__webpack_require__(/*! ./create-profile.component.scss */ "./src/app/create-profile/create-profile.component.scss")]
        }),
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Injectable"])(),
        __metadata("design:paramtypes", [_layout_layout_component__WEBPACK_IMPORTED_MODULE_6__["LayoutComponent"], _helper_ledgerhelper__WEBPACK_IMPORTED_MODULE_5__["LedgerHelper"], _angular_router__WEBPACK_IMPORTED_MODULE_1__["Router"],
            _angular_common_http__WEBPACK_IMPORTED_MODULE_2__["HttpClient"], _angular_platform_browser__WEBPACK_IMPORTED_MODULE_4__["DomSanitizer"], ng2_img_max__WEBPACK_IMPORTED_MODULE_7__["Ng2ImgMaxService"]])
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