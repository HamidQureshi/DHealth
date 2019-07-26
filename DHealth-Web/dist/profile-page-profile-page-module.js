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

module.exports = ".profile-page {\n  display: flex;\n  align-items: center;\n  justify-content: center;\n  height: 100%;\n  position: relative; }\n  .profile-page .content {\n    z-index: 1;\n    display: flex;\n    align-items: center;\n    justify-content: center; }\n  .profile-page .content .app-name {\n      margin-top: 0px;\n      padding-bottom: 10px;\n      font-size: 32px; }\n  .profile-page .content .profile-page {\n      padding: 40px;\n      background: #fff;\n      width: 500px;\n      box-shadow: 0 0 10px #ddd; }\n  .profile-page .content .profile-page input:-webkit-autofill {\n        -webkit-box-shadow: 0 0 0 30px white inset; }\n  .profile-page:after {\n    content: '';\n    background: #fff;\n    position: absolute;\n    top: 0;\n    left: 0;\n    bottom: 50%;\n    right: 0; }\n  .profile-page:before {\n    content: '';\n    background: #3f51b5;\n    position: absolute;\n    top: 50%;\n    left: 0;\n    bottom: 0;\n    right: 0; }\n  .text-center {\n  text-align: center; }\n  .w-100 {\n  width: 100%; }\n  .example-h2 {\n  margin: 10px; }\n  .example-section {\n  display: flex;\n  align-content: center;\n  align-items: center;\n  height: 60px; }\n  .example-margin {\n  margin: 0 10px; }\n  .mat-card-image {\n  width: 200px;\n  height: 200px; }\n\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbInNyYy9hcHAvbGF5b3V0L3Byb2ZpbGUtcGFnZS9DOlxcVXNlcnNcXEhhbWlkXFxEZXNrdG9wXFxBTFNES1xcREhlYWx0aFxcaGVhbHRoXFxESGVhbHRoLVdlYi9zcmNcXGFwcFxcbGF5b3V0XFxwcm9maWxlLXBhZ2VcXHByb2ZpbGUtcGFnZS5jb21wb25lbnQuc2NzcyJdLCJuYW1lcyI6W10sIm1hcHBpbmdzIjoiQUFBQTtFQUNJLGFBQWE7RUFDYixtQkFBbUI7RUFDbkIsdUJBQXVCO0VBQ3ZCLFlBQVk7RUFDWixrQkFBa0IsRUFBQTtFQUx0QjtJQU9RLFVBQVU7SUFDVixhQUFhO0lBQ2IsbUJBQW1CO0lBQ25CLHVCQUF1QixFQUFBO0VBVi9CO01BWVksZUFBZTtNQUNmLG9CQUFvQjtNQUNwQixlQUFlLEVBQUE7RUFkM0I7TUFpQlksYUFBYTtNQUNiLGdCQUFnQjtNQUNoQixZQUFZO01BQ1oseUJBQXlCLEVBQUE7RUFwQnJDO1FBc0JnQiwwQ0FBMEMsRUFBQTtFQXRCMUQ7SUE0QlEsV0FBVztJQUNYLGdCQUFnQjtJQUNoQixrQkFBa0I7SUFDbEIsTUFBTTtJQUNOLE9BQU87SUFDUCxXQUFXO0lBQ1gsUUFBUSxFQUFBO0VBbENoQjtJQXFDUSxXQUFXO0lBQ1gsbUJBQW1CO0lBQ25CLGtCQUFrQjtJQUNsQixRQUFRO0lBQ1IsT0FBTztJQUNQLFNBQVM7SUFDVCxRQUFRLEVBQUE7RUFHaEI7RUFDSSxrQkFBa0IsRUFBQTtFQUV0QjtFQUNJLFdBQVcsRUFBQTtFQUdmO0VBQ0ksWUFBWSxFQUFBO0VBR2Q7RUFDRSxhQUFhO0VBQ2IscUJBQXFCO0VBQ3JCLG1CQUFtQjtFQUNuQixZQUFZLEVBQUE7RUFHZDtFQUNFLGNBQWMsRUFBQTtFQUdoQjtFQUNFLFlBQVc7RUFDWCxhQUFZLEVBQUEiLCJmaWxlIjoic3JjL2FwcC9sYXlvdXQvcHJvZmlsZS1wYWdlL3Byb2ZpbGUtcGFnZS5jb21wb25lbnQuc2NzcyIsInNvdXJjZXNDb250ZW50IjpbIi5wcm9maWxlLXBhZ2Uge1xyXG4gICAgZGlzcGxheTogZmxleDtcclxuICAgIGFsaWduLWl0ZW1zOiBjZW50ZXI7XHJcbiAgICBqdXN0aWZ5LWNvbnRlbnQ6IGNlbnRlcjtcclxuICAgIGhlaWdodDogMTAwJTtcclxuICAgIHBvc2l0aW9uOiByZWxhdGl2ZTtcclxuICAgIC5jb250ZW50IHtcclxuICAgICAgICB6LWluZGV4OiAxO1xyXG4gICAgICAgIGRpc3BsYXk6IGZsZXg7XHJcbiAgICAgICAgYWxpZ24taXRlbXM6IGNlbnRlcjtcclxuICAgICAgICBqdXN0aWZ5LWNvbnRlbnQ6IGNlbnRlcjtcclxuICAgICAgICAuYXBwLW5hbWUge1xyXG4gICAgICAgICAgICBtYXJnaW4tdG9wOiAwcHg7XHJcbiAgICAgICAgICAgIHBhZGRpbmctYm90dG9tOiAxMHB4O1xyXG4gICAgICAgICAgICBmb250LXNpemU6IDMycHg7XHJcbiAgICAgICAgfVxyXG4gICAgICAgIC5wcm9maWxlLXBhZ2Uge1xyXG4gICAgICAgICAgICBwYWRkaW5nOiA0MHB4O1xyXG4gICAgICAgICAgICBiYWNrZ3JvdW5kOiAjZmZmO1xyXG4gICAgICAgICAgICB3aWR0aDogNTAwcHg7XHJcbiAgICAgICAgICAgIGJveC1zaGFkb3c6IDAgMCAxMHB4ICNkZGQ7XHJcbiAgICAgICAgICAgIGlucHV0Oi13ZWJraXQtYXV0b2ZpbGwge1xyXG4gICAgICAgICAgICAgICAgLXdlYmtpdC1ib3gtc2hhZG93OiAwIDAgMCAzMHB4IHdoaXRlIGluc2V0O1xyXG4gICAgICAgICAgICB9XHJcbiAgICAgICAgfVxyXG4gICAgfVxyXG5cclxuICAgICY6YWZ0ZXIge1xyXG4gICAgICAgIGNvbnRlbnQ6ICcnO1xyXG4gICAgICAgIGJhY2tncm91bmQ6ICNmZmY7XHJcbiAgICAgICAgcG9zaXRpb246IGFic29sdXRlO1xyXG4gICAgICAgIHRvcDogMDtcclxuICAgICAgICBsZWZ0OiAwO1xyXG4gICAgICAgIGJvdHRvbTogNTAlO1xyXG4gICAgICAgIHJpZ2h0OiAwO1xyXG4gICAgfVxyXG4gICAgJjpiZWZvcmUge1xyXG4gICAgICAgIGNvbnRlbnQ6ICcnO1xyXG4gICAgICAgIGJhY2tncm91bmQ6ICMzZjUxYjU7XHJcbiAgICAgICAgcG9zaXRpb246IGFic29sdXRlO1xyXG4gICAgICAgIHRvcDogNTAlO1xyXG4gICAgICAgIGxlZnQ6IDA7XHJcbiAgICAgICAgYm90dG9tOiAwO1xyXG4gICAgICAgIHJpZ2h0OiAwO1xyXG4gICAgfVxyXG59XHJcbi50ZXh0LWNlbnRlciB7XHJcbiAgICB0ZXh0LWFsaWduOiBjZW50ZXI7XHJcbn1cclxuLnctMTAwIHtcclxuICAgIHdpZHRoOiAxMDAlO1xyXG59XHJcblxyXG4uZXhhbXBsZS1oMiB7XHJcbiAgICBtYXJnaW46IDEwcHg7XHJcbiAgfVxyXG4gIFxyXG4gIC5leGFtcGxlLXNlY3Rpb24ge1xyXG4gICAgZGlzcGxheTogZmxleDtcclxuICAgIGFsaWduLWNvbnRlbnQ6IGNlbnRlcjtcclxuICAgIGFsaWduLWl0ZW1zOiBjZW50ZXI7XHJcbiAgICBoZWlnaHQ6IDYwcHg7XHJcbiAgfVxyXG4gIFxyXG4gIC5leGFtcGxlLW1hcmdpbiB7XHJcbiAgICBtYXJnaW46IDAgMTBweDtcclxuICB9XHJcblxyXG4gIC5tYXQtY2FyZC1pbWFnZXtcclxuICAgIHdpZHRoOjIwMHB4O1xyXG4gICAgaGVpZ2h0OjIwMHB4O1xyXG59Il19 */"

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
/* harmony import */ var _activeledger_sdk__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @activeledger/sdk */ "./node_modules/@activeledger/sdk/lib/index.js");
/* harmony import */ var _activeledger_sdk__WEBPACK_IMPORTED_MODULE_3___default = /*#__PURE__*/__webpack_require__.n(_activeledger_sdk__WEBPACK_IMPORTED_MODULE_3__);
/* harmony import */ var _angular_platform_browser__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @angular/platform-browser */ "./node_modules/@angular/platform-browser/fesm5/platform-browser.js");
/* harmony import */ var src_app_helper_ledgerhelper__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! src/app/helper/ledgerhelper */ "./src/app/helper/ledgerhelper.ts");
/* harmony import */ var _layout_component__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! ../layout.component */ "./src/app/layout/layout.component.ts");
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









var ProfilePageComponent = /** @class */ (function () {
    function ProfilePageComponent(layoutComp, ledgerHelper, router, http, sanitizer, ng2ImgMax) {
        this.layoutComp = layoutComp;
        this.ledgerHelper = ledgerHelper;
        this.router = router;
        this.http = http;
        this.sanitizer = sanitizer;
        this.ng2ImgMax = ng2ImgMax;
        this.hidden = true;
        this.editing_disabled = true;
    }
    ProfilePageComponent.prototype.ngOnInit = function () {
        document.getElementById('i_fn').value = this.ledgerHelper.first_name;
        document.getElementById('i_ln').value = this.ledgerHelper.last_name;
        document.getElementById('i_address').value = this.ledgerHelper.address;
        document.getElementById('i_dob').value = this.ledgerHelper.date_of_birth;
        document.getElementById('i_email').value = this.ledgerHelper.email;
        document.getElementById('i_pno').value = this.ledgerHelper.phone_number;
        if (!(this.ledgerHelper.dp === null)) {
            this.image = this.transform('data:image/jpeg;base64, ' + this.ledgerHelper.dp.trim());
        }
        else {
            this.image = this.transform('data:image/jpeg;base64, ' + this.ledgerHelper.placeholder_img);
        }
    };
    ProfilePageComponent.prototype.onUpdateProfile = function (firstname, lastname, email, dob, pno, address) {
        var _this = this;
        var id = this.ledgerHelper._id;
        var header = new _angular_common_http__WEBPACK_IMPORTED_MODULE_2__["HttpHeaders"]({
            'Content-Type': 'application/json',
            'Authorization': '' + this.ledgerHelper.token
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
        var dp = this.image.toString();
        baseTransaction1.$tx.$o[id]['dp'] = this.ledgerHelper.sanitizeDP(dp);
        dp = baseTransaction1.$tx.$o[id]['dp'];
        var txHandler = new _activeledger_sdk__WEBPACK_IMPORTED_MODULE_3__["TransactionHandler"]();
        txHandler
            .signTransaction(baseTransaction1, this.ledgerHelper.key)
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
                    _this.layoutComp.showSnackBar(data.body.resp.desc);
                    console.log('profile page --> ' + dp);
                    _this.ledgerHelper.address = '' + address;
                    _this.ledgerHelper.date_of_birth = '' + dob;
                    _this.ledgerHelper.dp = '' + dp;
                    _this.ledgerHelper.first_name = '' + firstname;
                    _this.ledgerHelper.last_name = '' + lastname;
                    _this.ledgerHelper.phone_number = '' + pno;
                    _this.disableFields();
                }
                else {
                    _this.layoutComp.showSnackBar(' Something went wrong! ');
                }
            });
        })
            .catch();
    };
    ProfilePageComponent.prototype.onSelectFile = function (event) {
        var _this = this;
        if (event.target.files && event.target.files[0]) {
            var reader_1 = new FileReader();
            this.ng2ImgMax.resizeImage(event.target.files[0], 400, 300).subscribe(function (result) {
                // this.uploadedImage = result;
                reader_1.readAsDataURL(result);
                reader_1.onload = function () {
                    _this.image = reader_1.result;
                };
            }, function (error) {
                console.log('😢 Oh no!', error);
            });
            // reader.readAsDataURL(event.target.files[0]);
            // reader.onload = () => {
            //   this.image = reader.result;
            // };
        }
    };
    ProfilePageComponent.prototype.updateProfile = function (body, headers1) {
        return this.http.post(this.ledgerHelper.updateProfileUrl, body, { headers: headers1, observe: 'response' });
    };
    ProfilePageComponent.prototype.enableFields = function () {
        this.layoutComp.showSnackBar('Profile Editing Enabled');
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
        __metadata("design:paramtypes", [_layout_component__WEBPACK_IMPORTED_MODULE_6__["LayoutComponent"], src_app_helper_ledgerhelper__WEBPACK_IMPORTED_MODULE_5__["LedgerHelper"], _angular_router__WEBPACK_IMPORTED_MODULE_1__["Router"],
            _angular_common_http__WEBPACK_IMPORTED_MODULE_2__["HttpClient"], _angular_platform_browser__WEBPACK_IMPORTED_MODULE_4__["DomSanitizer"], ng2_img_max__WEBPACK_IMPORTED_MODULE_7__["Ng2ImgMaxService"]])
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