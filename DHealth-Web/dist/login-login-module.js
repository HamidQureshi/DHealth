(window["webpackJsonp"] = window["webpackJsonp"] || []).push([["login-login-module"],{

/***/ "./src/app/login/login-routing.module.ts":
/*!***********************************************!*\
  !*** ./src/app/login/login-routing.module.ts ***!
  \***********************************************/
/*! exports provided: LoginRoutingModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "LoginRoutingModule", function() { return LoginRoutingModule; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
/* harmony import */ var _login_component__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./login.component */ "./src/app/login/login.component.ts");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};



var routes = [
    {
        path: '',
        component: _login_component__WEBPACK_IMPORTED_MODULE_2__["LoginComponent"]
    }
];
var LoginRoutingModule = /** @class */ (function () {
    function LoginRoutingModule() {
    }
    LoginRoutingModule = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["NgModule"])({
            imports: [_angular_router__WEBPACK_IMPORTED_MODULE_1__["RouterModule"].forChild(routes)],
            exports: [_angular_router__WEBPACK_IMPORTED_MODULE_1__["RouterModule"]]
        })
    ], LoginRoutingModule);
    return LoginRoutingModule;
}());



/***/ }),

/***/ "./src/app/login/login.component.html":
/*!********************************************!*\
  !*** ./src/app/login/login.component.html ***!
  \********************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<div class=\"login-page\">\r\n    <div class=\"content\">\r\n        <form class=\"login-form\" ng-controller=\"formCtrl\" fxFlex  >\r\n            <div class=\"text-center\">\r\n                <h2 class=\"app-name\">DHealth</h2>\r\n            </div>\r\n            <div fxFlex  fxlayout=\"row\" fxlayout.lt-md=\"column\">\r\n                <div fxFlexFill>\r\n                    <mat-form-field class=\"w-100\">\r\n                        <input #email_f matInput placeholder=\"Email  \" >\r\n                    </mat-form-field>\r\n                </div>\r\n            </div>\r\n            <div fxFlex  fxLayout=\"row\" fxLayout.lt-md=\"column\">\r\n                <div fxFlexFill>\r\n                    <mat-form-field class=\"w-100\">\r\n                        <input #password_f matInput type=\"password\" placeholder=\"Password\" >\r\n                    </mat-form-field>\r\n                </div>\r\n            </div>\r\n            <div fxFlex  fxLayout=\"row\" fxLayout.lt-md=\"column\" style=\"margin: 20px 0 30px 0\">\r\n                <div fxFlex>\r\n                    <mat-checkbox color=\"primary\">Remember Me</mat-checkbox>\r\n                </div>\r\n                <div fxFlex  fxLayoutAlign=\"end\">\r\n                    <a href=\"javascript:void(0)\">Forget Password</a>\r\n                </div>\r\n            </div>\r\n            <div fxFlex  fxLayout=\"row\" fxLayout.lt-md=\"column\" style=\"margin: 0px 0 30px 0\">\r\n                <div fxFlexFill>\r\n                    <button mat-raised-button color=\"primary\" class=\"w-100\" (click)=\"onLogin(email_f.value,password_f.value)\" type = 'button'>Login</button>\r\n                </div>\r\n            </div>\r\n\r\n            <div fxFlex  fxLayout=\"row\" fxLayout.lt-md=\"column\">\r\n                <div fxFlexFill>\r\n                    <button mat-raised-button color=\"primary\" class=\"w-100\" (click)=\"onRegister()\" type = 'button'>Not a user? Register</button>\r\n                </div>\r\n            </div>\r\n\r\n        </form>\r\n    </div>\r\n</div>"

/***/ }),

/***/ "./src/app/login/login.component.scss":
/*!********************************************!*\
  !*** ./src/app/login/login.component.scss ***!
  \********************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = ".login-page {\n  display: flex;\n  align-items: center;\n  justify-content: center;\n  height: 100%;\n  position: relative; }\n  .login-page .content {\n    z-index: 1;\n    display: flex;\n    align-items: center;\n    justify-content: center; }\n  .login-page .content .app-name {\n      margin-top: 0px;\n      padding-bottom: 10px;\n      font-size: 32px; }\n  .login-page .content .login-form {\n      padding: 40px;\n      background: #fff;\n      width: 500px;\n      box-shadow: 0 0 10px #ddd; }\n  .login-page .content .login-form input:-webkit-autofill {\n        -webkit-box-shadow: 0 0 0 30px white inset; }\n  .login-page:after {\n    content: '';\n    background: #fff;\n    position: absolute;\n    top: 0;\n    left: 0;\n    bottom: 50%;\n    right: 0; }\n  .login-page:before {\n    content: '';\n    background: #3f51b5;\n    position: absolute;\n    top: 50%;\n    left: 0;\n    bottom: 0;\n    right: 0; }\n  .text-center {\n  text-align: center; }\n  .w-100 {\n  width: 100%; }\n\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbInNyYy9hcHAvbG9naW4vQzpcXFVzZXJzXFxIYW1pZFxcRGVza3RvcFxcQUxTREtcXGV4YW1wbGVzXFxzYi1hZG1pbi1tYXRlcmlhbC9zcmNcXGFwcFxcbG9naW5cXGxvZ2luLmNvbXBvbmVudC5zY3NzIl0sIm5hbWVzIjpbXSwibWFwcGluZ3MiOiJBQUFBO0VBQ0ksYUFBYTtFQUNiLG1CQUFtQjtFQUNuQix1QkFBdUI7RUFDdkIsWUFBWTtFQUNaLGtCQUFrQixFQUFBO0VBTHRCO0lBT1EsVUFBVTtJQUNWLGFBQWE7SUFDYixtQkFBbUI7SUFDbkIsdUJBQXVCLEVBQUE7RUFWL0I7TUFZWSxlQUFlO01BQ2Ysb0JBQW9CO01BQ3BCLGVBQWUsRUFBQTtFQWQzQjtNQWlCWSxhQUFhO01BQ2IsZ0JBQWdCO01BQ2hCLFlBQVk7TUFDWix5QkFBeUIsRUFBQTtFQXBCckM7UUFzQmdCLDBDQUEwQyxFQUFBO0VBdEIxRDtJQTRCUSxXQUFXO0lBQ1gsZ0JBQWdCO0lBQ2hCLGtCQUFrQjtJQUNsQixNQUFNO0lBQ04sT0FBTztJQUNQLFdBQVc7SUFDWCxRQUFRLEVBQUE7RUFsQ2hCO0lBcUNRLFdBQVc7SUFDWCxtQkFBbUI7SUFDbkIsa0JBQWtCO0lBQ2xCLFFBQVE7SUFDUixPQUFPO0lBQ1AsU0FBUztJQUNULFFBQVEsRUFBQTtFQUdoQjtFQUNJLGtCQUFrQixFQUFBO0VBRXRCO0VBQ0ksV0FBVyxFQUFBIiwiZmlsZSI6InNyYy9hcHAvbG9naW4vbG9naW4uY29tcG9uZW50LnNjc3MiLCJzb3VyY2VzQ29udGVudCI6WyIubG9naW4tcGFnZSB7XHJcbiAgICBkaXNwbGF5OiBmbGV4O1xyXG4gICAgYWxpZ24taXRlbXM6IGNlbnRlcjtcclxuICAgIGp1c3RpZnktY29udGVudDogY2VudGVyO1xyXG4gICAgaGVpZ2h0OiAxMDAlO1xyXG4gICAgcG9zaXRpb246IHJlbGF0aXZlO1xyXG4gICAgLmNvbnRlbnQge1xyXG4gICAgICAgIHotaW5kZXg6IDE7XHJcbiAgICAgICAgZGlzcGxheTogZmxleDtcclxuICAgICAgICBhbGlnbi1pdGVtczogY2VudGVyO1xyXG4gICAgICAgIGp1c3RpZnktY29udGVudDogY2VudGVyO1xyXG4gICAgICAgIC5hcHAtbmFtZSB7XHJcbiAgICAgICAgICAgIG1hcmdpbi10b3A6IDBweDtcclxuICAgICAgICAgICAgcGFkZGluZy1ib3R0b206IDEwcHg7XHJcbiAgICAgICAgICAgIGZvbnQtc2l6ZTogMzJweDtcclxuICAgICAgICB9XHJcbiAgICAgICAgLmxvZ2luLWZvcm0ge1xyXG4gICAgICAgICAgICBwYWRkaW5nOiA0MHB4O1xyXG4gICAgICAgICAgICBiYWNrZ3JvdW5kOiAjZmZmO1xyXG4gICAgICAgICAgICB3aWR0aDogNTAwcHg7XHJcbiAgICAgICAgICAgIGJveC1zaGFkb3c6IDAgMCAxMHB4ICNkZGQ7XHJcbiAgICAgICAgICAgIGlucHV0Oi13ZWJraXQtYXV0b2ZpbGwge1xyXG4gICAgICAgICAgICAgICAgLXdlYmtpdC1ib3gtc2hhZG93OiAwIDAgMCAzMHB4IHdoaXRlIGluc2V0O1xyXG4gICAgICAgICAgICB9XHJcbiAgICAgICAgfVxyXG4gICAgfVxyXG5cclxuICAgICY6YWZ0ZXIge1xyXG4gICAgICAgIGNvbnRlbnQ6ICcnO1xyXG4gICAgICAgIGJhY2tncm91bmQ6ICNmZmY7XHJcbiAgICAgICAgcG9zaXRpb246IGFic29sdXRlO1xyXG4gICAgICAgIHRvcDogMDtcclxuICAgICAgICBsZWZ0OiAwO1xyXG4gICAgICAgIGJvdHRvbTogNTAlO1xyXG4gICAgICAgIHJpZ2h0OiAwO1xyXG4gICAgfVxyXG4gICAgJjpiZWZvcmUge1xyXG4gICAgICAgIGNvbnRlbnQ6ICcnO1xyXG4gICAgICAgIGJhY2tncm91bmQ6ICMzZjUxYjU7XHJcbiAgICAgICAgcG9zaXRpb246IGFic29sdXRlO1xyXG4gICAgICAgIHRvcDogNTAlO1xyXG4gICAgICAgIGxlZnQ6IDA7XHJcbiAgICAgICAgYm90dG9tOiAwO1xyXG4gICAgICAgIHJpZ2h0OiAwO1xyXG4gICAgfVxyXG59XHJcbi50ZXh0LWNlbnRlciB7XHJcbiAgICB0ZXh0LWFsaWduOiBjZW50ZXI7XHJcbn1cclxuLnctMTAwIHtcclxuICAgIHdpZHRoOiAxMDAlO1xyXG59XHJcbiJdfQ== */"

/***/ }),

/***/ "./src/app/login/login.component.ts":
/*!******************************************!*\
  !*** ./src/app/login/login.component.ts ***!
  \******************************************/
/*! exports provided: LoginComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "LoginComponent", function() { return LoginComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/common/http */ "./node_modules/@angular/common/fesm5/http.js");
/* harmony import */ var _angular_material__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @angular/material */ "./node_modules/@angular/material/esm5/material.es5.js");
/* harmony import */ var _helper_ledgerhelper__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ../helper/ledgerhelper */ "./src/app/helper/ledgerhelper.ts");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};







var LoginComponent = /** @class */ (function () {
    function LoginComponent(ledgerHelper, router, http, snackBar) {
        this.ledgerHelper = ledgerHelper;
        this.router = router;
        this.http = http;
        this.snackBar = snackBar;
        this.message = 'Snack Bar opened.';
        this.actionButtonLabel = 'Close';
        this.action = true;
        this.setAutoHide = true;
        this.autoHide = 2000;
        this.horizontalPosition = 'center';
        this.verticalPosition = 'bottom';
        this.addExtraClass = false;
    }
    LoginComponent.prototype.ngOnInit = function () { };
    LoginComponent.prototype.onLogin = function (username, password) {
        var _this = this;
        console.log('email = ' + username);
        console.log('password = ' + password);
        var auth = 'Basic ' + btoa(username + ':' + password);
        console.log('auth = ' + auth);
        var header = new _angular_common_http__WEBPACK_IMPORTED_MODULE_2__["HttpHeaders"]({
            'Authorization': auth
        });
        this.http.post(_helper_ledgerhelper__WEBPACK_IMPORTED_MODULE_4__["LedgerHelper"].loginUrl, null, { headers: header, observe: 'response' })
            .subscribe(function (data) {
            console.log(data);
            console.log(data.status);
            if (data.status == 200) {
                _this.message = data.body.resp.desc;
                _this.open();
                console.log(data.headers.get('Token'));
                _helper_ledgerhelper__WEBPACK_IMPORTED_MODULE_4__["LedgerHelper"].token = data.headers.get('Token');
                console.log('streams = ' + data.body.streams);
                if (data.body.streams != null) {
                    _helper_ledgerhelper__WEBPACK_IMPORTED_MODULE_4__["LedgerHelper"].address = '' + data.body.streams.address;
                    _helper_ledgerhelper__WEBPACK_IMPORTED_MODULE_4__["LedgerHelper"].date_of_birth = '' + data.body.streams.date_of_birth;
                    _helper_ledgerhelper__WEBPACK_IMPORTED_MODULE_4__["LedgerHelper"].dp = '' + data.body.streams.dp;
                    _helper_ledgerhelper__WEBPACK_IMPORTED_MODULE_4__["LedgerHelper"].email = '' + data.body.streams.email;
                    _helper_ledgerhelper__WEBPACK_IMPORTED_MODULE_4__["LedgerHelper"].first_name = '' + data.body.streams.first_name;
                    _helper_ledgerhelper__WEBPACK_IMPORTED_MODULE_4__["LedgerHelper"].last_name = '' + data.body.streams.last_name;
                    _helper_ledgerhelper__WEBPACK_IMPORTED_MODULE_4__["LedgerHelper"].gender = '' + data.body.streams.gender;
                    _helper_ledgerhelper__WEBPACK_IMPORTED_MODULE_4__["LedgerHelper"].phone_number = '' + data.body.streams.phone_number;
                    _helper_ledgerhelper__WEBPACK_IMPORTED_MODULE_4__["LedgerHelper"].profile_type = '' + data.body.streams.profile_type;
                    _this.ledgerHelper.reports = '' + data.body.streams.reports;
                    _helper_ledgerhelper__WEBPACK_IMPORTED_MODULE_4__["LedgerHelper"].security = '' + data.body.streams.security;
                    _helper_ledgerhelper__WEBPACK_IMPORTED_MODULE_4__["LedgerHelper"]._id = '' + data.body.streams._id;
                    _helper_ledgerhelper__WEBPACK_IMPORTED_MODULE_4__["LedgerHelper"].profileCreated = '' + true;
                    _helper_ledgerhelper__WEBPACK_IMPORTED_MODULE_4__["LedgerHelper"].isLoggedin = '' + true;
                    _this.router.navigate(['/dashboard']);
                }
                else {
                    _helper_ledgerhelper__WEBPACK_IMPORTED_MODULE_4__["LedgerHelper"].email = '' + username;
                    _helper_ledgerhelper__WEBPACK_IMPORTED_MODULE_4__["LedgerHelper"].profileCreated = '' + false;
                    _helper_ledgerhelper__WEBPACK_IMPORTED_MODULE_4__["LedgerHelper"].isLoggedin = '' + true;
                    _this.router.navigate(['/create-profile']);
                }
            }
            else {
                _this.message = data.statusText;
                _this.open();
            }
        });
    };
    LoginComponent.prototype.loginUser = function (header) {
        return this.http.post(_helper_ledgerhelper__WEBPACK_IMPORTED_MODULE_4__["LedgerHelper"].loginUrl, null, { headers: header, responseType: 'json' });
    };
    LoginComponent.prototype.onRegister = function () {
        this.router.navigate(['/register']);
    };
    LoginComponent.prototype.open = function () {
        var config = new _angular_material__WEBPACK_IMPORTED_MODULE_3__["MatSnackBarConfig"]();
        config.verticalPosition = this.verticalPosition;
        config.horizontalPosition = this.horizontalPosition;
        config.duration = this.setAutoHide ? this.autoHide : 0;
        this.snackBar.open(this.message, this.action ? this.actionButtonLabel : undefined, config);
    };
    LoginComponent = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"])({
            selector: 'app-login',
            template: __webpack_require__(/*! ./login.component.html */ "./src/app/login/login.component.html"),
            styles: [__webpack_require__(/*! ./login.component.scss */ "./src/app/login/login.component.scss")]
        }),
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Injectable"])(),
        __metadata("design:paramtypes", [_helper_ledgerhelper__WEBPACK_IMPORTED_MODULE_4__["LedgerHelper"], _angular_router__WEBPACK_IMPORTED_MODULE_1__["Router"],
            _angular_common_http__WEBPACK_IMPORTED_MODULE_2__["HttpClient"], _angular_material__WEBPACK_IMPORTED_MODULE_3__["MatSnackBar"]])
    ], LoginComponent);
    return LoginComponent;
}());



/***/ }),

/***/ "./src/app/login/login.module.ts":
/*!***************************************!*\
  !*** ./src/app/login/login.module.ts ***!
  \***************************************/
/*! exports provided: LoginModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "LoginModule", function() { return LoginModule; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_common__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/common */ "./node_modules/@angular/common/fesm5/common.js");
/* harmony import */ var _angular_flex_layout__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/flex-layout */ "./node_modules/@angular/flex-layout/esm5/flex-layout.es5.js");
/* harmony import */ var _angular_material__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @angular/material */ "./node_modules/@angular/material/esm5/material.es5.js");
/* harmony import */ var _login_routing_module__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ./login-routing.module */ "./src/app/login/login-routing.module.ts");
/* harmony import */ var _login_component__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! ./login.component */ "./src/app/login/login.component.ts");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};







var LoginModule = /** @class */ (function () {
    function LoginModule() {
    }
    LoginModule = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["NgModule"])({
            imports: [
                _angular_common__WEBPACK_IMPORTED_MODULE_1__["CommonModule"],
                _login_routing_module__WEBPACK_IMPORTED_MODULE_4__["LoginRoutingModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_3__["MatInputModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_3__["MatCheckboxModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_3__["MatButtonModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_3__["MatSnackBarModule"],
                _angular_flex_layout__WEBPACK_IMPORTED_MODULE_2__["FlexLayoutModule"].withConfig({ addFlexToParent: false })
            ],
            declarations: [_login_component__WEBPACK_IMPORTED_MODULE_5__["LoginComponent"]]
        })
    ], LoginModule);
    return LoginModule;
}());



/***/ })

}]);
//# sourceMappingURL=login-login-module.js.map