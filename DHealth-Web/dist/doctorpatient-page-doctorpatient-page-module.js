(window["webpackJsonp"] = window["webpackJsonp"] || []).push([["doctorpatient-page-doctorpatient-page-module"],{

/***/ "./src/app/layout/doctorpatient-page/doctorpatient-page-routing.module.ts":
/*!********************************************************************************!*\
  !*** ./src/app/layout/doctorpatient-page/doctorpatient-page-routing.module.ts ***!
  \********************************************************************************/
/*! exports provided: DoctorpatientPageRoutingModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "DoctorpatientPageRoutingModule", function() { return DoctorpatientPageRoutingModule; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
/* harmony import */ var _doctorpatient_page_component__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./doctorpatient-page.component */ "./src/app/layout/doctorpatient-page/doctorpatient-page.component.ts");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};



var routes = [
    {
        path: '',
        component: _doctorpatient_page_component__WEBPACK_IMPORTED_MODULE_2__["DoctorpatientPageComponent"]
    }
];
var DoctorpatientPageRoutingModule = /** @class */ (function () {
    function DoctorpatientPageRoutingModule() {
    }
    DoctorpatientPageRoutingModule = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["NgModule"])({
            imports: [_angular_router__WEBPACK_IMPORTED_MODULE_1__["RouterModule"].forChild(routes)],
            exports: [_angular_router__WEBPACK_IMPORTED_MODULE_1__["RouterModule"]]
        })
    ], DoctorpatientPageRoutingModule);
    return DoctorpatientPageRoutingModule;
}());



/***/ }),

/***/ "./src/app/layout/doctorpatient-page/doctorpatient-page.component.html":
/*!*****************************************************************************!*\
  !*** ./src/app/layout/doctorpatient-page/doctorpatient-page.component.html ***!
  \*****************************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<div class=\"mb-20\" fxLayout=\"column\" fxLayout.lt-md=\"column\" fxFlex fxLayoutGap=\"20px\">\r\n    <div fxFlex *ngFor=\"let item of users; let i = index\">\r\n\r\n        <div *ngIf=\"i % 2 == 0\" class=\"row\">\r\n\r\n            <div fxFlex fxLayout=\"row\" fxLayout.lt-md=\"column\">\r\n\r\n\r\n                <div fxFlex style=\"margin: 0px 20px 0px 0px\">\r\n                    <div fxFlexFill *ngIf=\"i + 0 < users.length\">\r\n\r\n                        <mat-card (click)=\"onSelect(users[i + 0])\">\r\n\r\n                            <div *ngIf=\"item.dp && item.dp.length > 10\">\r\n                                <img mat-card-image [src]=\"transform('data:image/jpeg;base64, ' + users[i+0].dp)\"\r\n                                    alt=\"Red dot\" style=\"margin: 20px 20px 0px 0px\" style=\"border-radius:50%\">\r\n                            </div>\r\n\r\n                            <mat-card-header style=\"justify-content: center\">\r\n                               \r\n                                <div class=\"content\">\r\n\r\n                                        <div fxFlex fxlayout=\"row\" fxlayout.lt-md=\"column\">\r\n                                            <div fxFlexFill>\r\n                                                <mat-form-field class=\"w-100\">\r\n                                                    <input #firstname_f matInput placeholder=\"First Name  \" value='{{users[i + 0].first_name}}' disabled=\"disabled\" style=\"color: black;\">\r\n                                                </mat-form-field>\r\n                                            </div>\r\n                                        </div>\r\n\r\n                                        <div fxFlex fxlayout=\"row\" fxlayout.lt-md=\"column\">\r\n                                            <div fxFlexFill>\r\n                                                <mat-form-field class=\"w-100\">\r\n                                                    <input #lastname_f matInput placeholder=\"Last Name  \" value='{{users[i + 0].last_name}}'disabled=\"disabled\" style=\"color: black;\">\r\n                                                </mat-form-field>\r\n                                            </div>\r\n                                        </div>\r\n\r\n                                    <div fxFlex fxlayout=\"row\" fxlayout.lt-md=\"column\">\r\n                                        <div fxFlexFill>\r\n                                            <mat-form-field class=\"w-100\">\r\n                                                <input #email_f matInput placeholder=\"Email  \" value='{{users[i + 0].email}}'disabled=\"disabled\" style=\"color: black;\">\r\n                                            </mat-form-field>\r\n                                        </div>\r\n                                    </div>\r\n\r\n                                    <div fxFlex fxlayout=\"row\" fxlayout.lt-md=\"column\">\r\n                                        <div fxFlexFill>\r\n                                            <mat-form-field class=\"w-100\">\r\n                                                <input #dob_f matInput placeholder=\"Date of Birth  \" value='{{users[i + 0].date_of_birth}}'disabled=\"disabled\" style=\"color: black;\">\r\n                                            </mat-form-field>\r\n                                        </div>\r\n                                    </div>\r\n\r\n                                    <div fxFlex fxlayout=\"row\" fxlayout.lt-md=\"column\">\r\n                                        <div fxFlexFill>\r\n                                            <mat-form-field class=\"w-100\">\r\n                                                <input #pno_f matInput placeholder=\"Phone Number  \" value='{{users[i + 0].phone_number}}'disabled=\"disabled\" style=\"color: black;\">\r\n                                            </mat-form-field>\r\n                                        </div>\r\n                                    </div>\r\n\r\n                                    <div fxFlex fxlayout=\"row\" fxlayout.lt-md=\"column\">\r\n                                        <div fxFlexFill>\r\n                                            <mat-form-field class=\"w-100\">\r\n                                                <input #address_f matInput placeholder=\"Address  \" value='{{users[i + 0].address}}'disabled=\"disabled\" style=\"color: black;\">\r\n                                            </mat-form-field>\r\n                                        </div>\r\n                                    </div>\r\n\r\n                                    <div fxFlex fxlayout=\"row\" fxlayout.lt-md=\"column\">\r\n                                        <div fxFlexFill>\r\n                                            <mat-form-field class=\"w-100\">\r\n                                                <input #address_f matInput placeholder=\"Gender  \" value='{{users[i + 0].gender}}'disabled=\"disabled\" style=\"color: black;\">\r\n                                            </mat-form-field>\r\n                                        </div>\r\n                                    </div>\r\n\r\n                                </div>\r\n\r\n                            </mat-card-header>\r\n                        </mat-card>\r\n\r\n                    </div>\r\n                </div>\r\n\r\n                <div fxFlex style=\"margin: 0px 20px 0px 0\">\r\n                    <div fxFlexFill *ngIf=\"i + 1 < users.length\">\r\n\r\n                        <mat-card (click)=\"onSelect(users[i + 1])\">\r\n                            <div *ngIf=\"item.dp \">\r\n                                <img mat-card-image [src]=\"transform('data:image/jpeg;base64, ' + users[i+1].dp)\"\r\n                                    alt=\"Red dot\" style=\"margin: 20px 30px 0px 0px\"  style=\"border-radius:50%\">\r\n\r\n                            </div>\r\n                            <mat-card-header style=\"justify-content: center\">\r\n                                \r\n\r\n                                    <div class=\"content\">\r\n                                    \r\n                                        <div fxFlex fxlayout=\"row\" fxlayout.lt-md=\"column\">\r\n                                            <div fxFlexFill>\r\n                                                <mat-form-field class=\"w-100\">\r\n                                                    <input #firstname_f matInput placeholder=\"First Name  \" value='{{users[i + 1].first_name}}'disabled=\"disabled\" style=\"color: black;\">\r\n                                                </mat-form-field>\r\n                                            </div>\r\n                                        </div>\r\n                                    \r\n                                        <div fxFlex fxlayout=\"row\" fxlayout.lt-md=\"column\">\r\n                                            <div fxFlexFill>\r\n                                                <mat-form-field class=\"w-100\">\r\n                                                    <input #lastname_f matInput placeholder=\"Last Name  \" value='{{users[i + 1].last_name}}'disabled=\"disabled\" style=\"color: black;\">\r\n                                                </mat-form-field>\r\n                                            </div>\r\n                                        </div>\r\n                                    \r\n                                        <div fxFlex fxlayout=\"row\" fxlayout.lt-md=\"column\">\r\n                                            <div fxFlexFill>\r\n                                                <mat-form-field class=\"w-100\">\r\n                                                    <input #email_f matInput placeholder=\"Email  \" value='{{users[i + 1].email}}'disabled=\"disabled\" style=\"color: black;\">\r\n                                                </mat-form-field>\r\n                                            </div>\r\n                                        </div>\r\n                                    \r\n                                        <div fxFlex fxlayout=\"row\" fxlayout.lt-md=\"column\">\r\n                                            <div fxFlexFill>\r\n                                                <mat-form-field class=\"w-100\">\r\n                                                    <input #dob_f matInput placeholder=\"Date of Birth  \" value='{{users[i + 1].date_of_birth}}'disabled=\"disabled\" style=\"color: black;\">\r\n                                                </mat-form-field>\r\n                                            </div>\r\n                                        </div>\r\n                                    \r\n                                        <div fxFlex fxlayout=\"row\" fxlayout.lt-md=\"column\">\r\n                                            <div fxFlexFill>\r\n                                                <mat-form-field class=\"w-100\">\r\n                                                    <input #pno_f matInput placeholder=\"Phone Number  \" value='{{users[i + 1].phone_number}}'disabled=\"disabled\" style=\"color: black;\">\r\n                                                </mat-form-field>\r\n                                            </div>\r\n                                        </div>\r\n                                    \r\n                                        <div fxFlex fxlayout=\"row\" fxlayout.lt-md=\"column\">\r\n                                            <div fxFlexFill>\r\n                                                <mat-form-field class=\"w-100\">\r\n                                                    <input #address_f matInput placeholder=\"Address  \" value='{{users[i + 1].address}}'disabled=\"disabled\" style=\"color: black;\">\r\n                                                </mat-form-field>\r\n                                            </div>\r\n                                        </div>\r\n                                    \r\n                                        <div fxFlex fxlayout=\"row\" fxlayout.lt-md=\"column\">\r\n                                            <div fxFlexFill>\r\n                                                <mat-form-field class=\"w-100\">\r\n                                                    <input #address_f matInput placeholder=\"Gender  \" value='{{users[i + 1].gender}}'disabled=\"disabled\" style=\"color: black;\">\r\n                                                </mat-form-field>\r\n                                            </div>\r\n                                        </div>\r\n                                    \r\n                                    </div>\r\n\r\n\r\n                            </mat-card-header>\r\n                        </mat-card>\r\n\r\n                    </div>\r\n                </div>\r\n\r\n            </div>\r\n        </div>\r\n\r\n    </div>\r\n\r\n\r\n</div>"

/***/ }),

/***/ "./src/app/layout/doctorpatient-page/doctorpatient-page.component.scss":
/*!*****************************************************************************!*\
  !*** ./src/app/layout/doctorpatient-page/doctorpatient-page.component.scss ***!
  \*****************************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "table {\n  width: 100%; }\n\n.mat-card {\n  text-align: center;\n  width: 98%;\n  height: 100%; }\n\n.mat-card img {\n    border-radius: 5px;\n    margin-top: -25px; }\n\n.mat-card-image {\n  width: 200px;\n  height: 200px; }\n\n.mat-table {\n  box-shadow: 0 3px 1px -2px rgba(0, 0, 0, 0.2), 0 2px 2px 0 rgba(0, 0, 0, 0.14), 0 1px 5px 0 rgba(0, 0, 0, 0.12); }\n\n.mb-20 {\n  margin-bottom: 20px; }\n\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbInNyYy9hcHAvbGF5b3V0L2RvY3RvcnBhdGllbnQtcGFnZS9DOlxcVXNlcnNcXEhhbWlkXFxEZXNrdG9wXFxBTFNES1xcZXhhbXBsZXNcXHNiLWFkbWluLW1hdGVyaWFsL3NyY1xcYXBwXFxsYXlvdXRcXGRvY3RvcnBhdGllbnQtcGFnZVxcZG9jdG9ycGF0aWVudC1wYWdlLmNvbXBvbmVudC5zY3NzIl0sIm5hbWVzIjpbXSwibWFwcGluZ3MiOiJBQUFBO0VBQ0ksV0FBVyxFQUFBOztBQUVmO0VBQ0ksa0JBQWtCO0VBQ2hCLFVBQVM7RUFDWCxZQUFXLEVBQUE7O0FBSGY7SUFLUSxrQkFBa0I7SUFDbEIsaUJBQWlCLEVBQUE7O0FBSXpCO0VBQ0ksWUFBVztFQUNYLGFBQVksRUFBQTs7QUFHaEI7RUFDSSwrR0FDbUMsRUFBQTs7QUFLdkM7RUFDSSxtQkFBbUIsRUFBQSIsImZpbGUiOiJzcmMvYXBwL2xheW91dC9kb2N0b3JwYXRpZW50LXBhZ2UvZG9jdG9ycGF0aWVudC1wYWdlLmNvbXBvbmVudC5zY3NzIiwic291cmNlc0NvbnRlbnQiOlsidGFibGUge1xyXG4gICAgd2lkdGg6IDEwMCU7XHJcbn1cclxuLm1hdC1jYXJkIHtcclxuICAgIHRleHQtYWxpZ246IGNlbnRlcjtcclxuICAgICAgd2lkdGg6OTglO1xyXG4gICAgaGVpZ2h0OjEwMCU7XHJcbiAgICBpbWcge1xyXG4gICAgICAgIGJvcmRlci1yYWRpdXM6IDVweDtcclxuICAgICAgICBtYXJnaW4tdG9wOiAtMjVweDtcclxuICAgIH1cclxufVxyXG5cclxuLm1hdC1jYXJkLWltYWdle1xyXG4gICAgd2lkdGg6MjAwcHg7XHJcbiAgICBoZWlnaHQ6MjAwcHg7XHJcbn1cclxuXHJcbi5tYXQtdGFibGUge1xyXG4gICAgYm94LXNoYWRvdzogMCAzcHggMXB4IC0ycHggcmdiYSgwLCAwLCAwLCAwLjIpLCAwIDJweCAycHggMCByZ2JhKDAsIDAsIDAsIDAuMTQpLFxyXG4gICAgICAgIDAgMXB4IDVweCAwIHJnYmEoMCwgMCwgMCwgMC4xMik7XHJcbiAgICAgICAgICAgICBcclxuICAgXHJcbn1cclxuXHJcbi5tYi0yMCB7XHJcbiAgICBtYXJnaW4tYm90dG9tOiAyMHB4O1xyXG59XHJcblxyXG5cclxuXHJcblxyXG5cclxuIl19 */"

/***/ }),

/***/ "./src/app/layout/doctorpatient-page/doctorpatient-page.component.ts":
/*!***************************************************************************!*\
  !*** ./src/app/layout/doctorpatient-page/doctorpatient-page.component.ts ***!
  \***************************************************************************/
/*! exports provided: DoctorpatientPageComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "DoctorpatientPageComponent", function() { return DoctorpatientPageComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/common/http */ "./node_modules/@angular/common/fesm5/http.js");
/* harmony import */ var _angular_material__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @angular/material */ "./node_modules/@angular/material/esm5/material.es5.js");
/* harmony import */ var _angular_platform_browser__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @angular/platform-browser */ "./node_modules/@angular/platform-browser/fesm5/platform-browser.js");
/* harmony import */ var src_app_helper_ledgerhelper__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! src/app/helper/ledgerhelper */ "./src/app/helper/ledgerhelper.ts");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};






var DoctorpatientPageComponent = /** @class */ (function () {
    function DoctorpatientPageComponent(ledgerHelper, router, http, snackBar, sanitizer) {
        this.ledgerHelper = ledgerHelper;
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
        this.users = [];
    }
    DoctorpatientPageComponent.prototype.ngOnInit = function () {
        this.users = JSON.parse(this.ledgerHelper.userList);
    };
    DoctorpatientPageComponent.prototype.onSelect = function (user) {
        console.log(user.first_name);
    };
    DoctorpatientPageComponent.prototype.open = function () {
        var config = new _angular_material__WEBPACK_IMPORTED_MODULE_3__["MatSnackBarConfig"]();
        config.verticalPosition = this.verticalPosition;
        config.horizontalPosition = this.horizontalPosition;
        config.duration = this.setAutoHide ? this.autoHide : 0;
        this.snackBar.open(this.message, this.action ? this.actionButtonLabel : undefined, config);
    };
    DoctorpatientPageComponent.prototype.transform = function (html) {
        return this.sanitizer.bypassSecurityTrustUrl(html);
    };
    DoctorpatientPageComponent = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"])({
            selector: 'app-doctorpatient-page',
            template: __webpack_require__(/*! ./doctorpatient-page.component.html */ "./src/app/layout/doctorpatient-page/doctorpatient-page.component.html"),
            styles: [__webpack_require__(/*! ./doctorpatient-page.component.scss */ "./src/app/layout/doctorpatient-page/doctorpatient-page.component.scss")]
        }),
        __metadata("design:paramtypes", [src_app_helper_ledgerhelper__WEBPACK_IMPORTED_MODULE_5__["LedgerHelper"], _angular_router__WEBPACK_IMPORTED_MODULE_1__["Router"],
            _angular_common_http__WEBPACK_IMPORTED_MODULE_2__["HttpClient"], _angular_material__WEBPACK_IMPORTED_MODULE_3__["MatSnackBar"],
            _angular_platform_browser__WEBPACK_IMPORTED_MODULE_4__["DomSanitizer"]])
    ], DoctorpatientPageComponent);
    return DoctorpatientPageComponent;
}());



/***/ }),

/***/ "./src/app/layout/doctorpatient-page/doctorpatient-page.module.ts":
/*!************************************************************************!*\
  !*** ./src/app/layout/doctorpatient-page/doctorpatient-page.module.ts ***!
  \************************************************************************/
/*! exports provided: DoctorpatientPageModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "DoctorpatientPageModule", function() { return DoctorpatientPageModule; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_common__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/common */ "./node_modules/@angular/common/fesm5/common.js");
/* harmony import */ var _doctorpatient_page_routing_module__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./doctorpatient-page-routing.module */ "./src/app/layout/doctorpatient-page/doctorpatient-page-routing.module.ts");
/* harmony import */ var _doctorpatient_page_component__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./doctorpatient-page.component */ "./src/app/layout/doctorpatient-page/doctorpatient-page.component.ts");
/* harmony import */ var _angular_flex_layout__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @angular/flex-layout */ "./node_modules/@angular/flex-layout/esm5/flex-layout.es5.js");
/* harmony import */ var _angular_material__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @angular/material */ "./node_modules/@angular/material/esm5/material.es5.js");
/* harmony import */ var _angular_material_grid_list__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! @angular/material/grid-list */ "./node_modules/@angular/material/esm5/grid-list.es5.js");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};









var DoctorpatientPageModule = /** @class */ (function () {
    function DoctorpatientPageModule() {
    }
    DoctorpatientPageModule = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["NgModule"])({
            imports: [
                _angular_material__WEBPACK_IMPORTED_MODULE_5__["MatRadioModule"],
                _angular_common__WEBPACK_IMPORTED_MODULE_1__["CommonModule"],
                _doctorpatient_page_routing_module__WEBPACK_IMPORTED_MODULE_2__["DoctorpatientPageRoutingModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_5__["MatInputModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_5__["MatCheckboxModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_5__["MatSnackBarModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_5__["MatButtonModule"],
                _angular_material_grid_list__WEBPACK_IMPORTED_MODULE_6__["MatGridListModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_5__["MatCardModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_5__["MatCardModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_5__["MatTableModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_5__["MatButtonModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_5__["MatIconModule"],
                _angular_flex_layout__WEBPACK_IMPORTED_MODULE_4__["FlexLayoutModule"].withConfig({ addFlexToParent: false })
            ],
            declarations: [_doctorpatient_page_component__WEBPACK_IMPORTED_MODULE_3__["DoctorpatientPageComponent"]]
        })
    ], DoctorpatientPageModule);
    return DoctorpatientPageModule;
}());



/***/ })

}]);
//# sourceMappingURL=doctorpatient-page-doctorpatient-page-module.js.map