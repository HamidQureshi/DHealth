(window["webpackJsonp"] = window["webpackJsonp"] || []).push([["add-report-page-add-report-module"],{

/***/ "./src/app/layout/add-report-page/add-report-routing.module.ts":
/*!*********************************************************************!*\
  !*** ./src/app/layout/add-report-page/add-report-routing.module.ts ***!
  \*********************************************************************/
/*! exports provided: AddReportRoutingModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "AddReportRoutingModule", function() { return AddReportRoutingModule; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
/* harmony import */ var _add_report_component__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./add-report.component */ "./src/app/layout/add-report-page/add-report.component.ts");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};



var routes = [
    {
        path: '',
        component: _add_report_component__WEBPACK_IMPORTED_MODULE_2__["AddReportComponent"]
    }
];
var AddReportRoutingModule = /** @class */ (function () {
    function AddReportRoutingModule() {
    }
    AddReportRoutingModule = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["NgModule"])({
            imports: [_angular_router__WEBPACK_IMPORTED_MODULE_1__["RouterModule"].forChild(routes)],
            exports: [_angular_router__WEBPACK_IMPORTED_MODULE_1__["RouterModule"]]
        })
    ], AddReportRoutingModule);
    return AddReportRoutingModule;
}());



/***/ }),

/***/ "./src/app/layout/add-report-page/add-report.component.html":
/*!******************************************************************!*\
  !*** ./src/app/layout/add-report-page/add-report.component.html ***!
  \******************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<div class=\"report-page\">\r\n    <div class=\"content\">\r\n        <form class=\"report-form\" ng-controller=\"formCtrl\" fxFlex>\r\n\r\n            <div fxFlex fxLayout=\"row\" fxLayout.lt-md=\"column\">\r\n                <div fxFlex style=\"margin: 0px 20px 0px 0\">\r\n                    <div fxFlexFill>\r\n                        <mat-form-field class=\"w-100\">\r\n                            <input id=\"i_pn\" #patientname_f matInput placeholder=\"Patient Name  \" disabled=\"disabled\"\r\n                                style=\"color: black;\">\r\n                        </mat-form-field>\r\n                    </div>\r\n                </div>\r\n\r\n                <div fxFlex fxLayoutAlign=\"end\">\r\n                    <div fxFlexFill>\r\n                        <mat-form-field class=\"w-100\">\r\n                            <input id=\"i_rt\" #title_f matInput placeholder=\"Report Title  \" style=\"color: black;\">\r\n                        </mat-form-field>\r\n                    </div>\r\n                </div>\r\n            </div>\r\n\r\n            <div fxFlex fxlayout=\"row\" fxlayout.lt-md=\"column\">\r\n                <div fxFlex style=\"margin: 0px 20px 0px 0\">\r\n                    <div fxFlexFill class=\"w-100\">\r\n                        <ng-multiselect-dropdown [placeholder]=\"'No Doctor Selected'\" [data]=\"dropdownList\"\r\n                            [(ngModel)]=\"selectedItems\" [settings]=\"dropdownSettings\" (onSelect)=\"onItemSelect($event)\"\r\n                            (onSelectAll)=\"onSelectAll($event)\" name=\"doctorlist\">\r\n                        </ng-multiselect-dropdown>\r\n                    </div>\r\n                </div>\r\n            </div>\r\n\r\n            <div fxFlex fxlayout=\"row\" fxlayout.lt-md=\"column\">\r\n                <div fxFlexFill>\r\n                    <mat-form-field class=\"w-100\">\r\n                        <input id=\"i_description\" #description_f matInput placeholder=\"Description  \"\r\n                            style=\"color: black;\">\r\n                    </mat-form-field>\r\n                </div>\r\n            </div>\r\n\r\n            <div fxFlex fxlayout=\"row\" fxlayout.lt-md=\"column\">\r\n                <div fxFlexFill>\r\n                    <mat-form-field class=\"w-100\">\r\n                        <input id=\"i_da\" #doc_f matInput placeholder=\"Document Attached  \" disabled=\"disabled\"\r\n                            style=\"color: black; \">\r\n                    </mat-form-field>\r\n                </div>\r\n            </div>\r\n\r\n\r\n            <div fxFlex fxLayout=\"row\" fxLayout.lt-md=\"column\">\r\n\r\n                <div fxFlexFill>\r\n                    <div fxFlex fxLayoutAlign=\"end\">\r\n\r\n                        <input hidden type=\"file\" #imgFileInput accept=\".pdf\" (change)=\"fileChange($event)\"\r\n                            style=\"color: black; \" />\r\n\r\n                        <button mat-raised-button color=\"primary\" class=\"w-50\" (click)=\"imgFileInput.click()\"\r\n                            type='button'>Attach PDF</button>\r\n                        <button mat-raised-button color=\"primary\" class=\"w-50\" (click)=\"onAddReport()\" type='button'\r\n                            style=\"margin: 0px 20px 0px 20px\">Add Report</button>\r\n\r\n                    </div>\r\n                </div>\r\n            </div>\r\n\r\n        </form>\r\n\r\n\r\n    </div>\r\n</div>"

/***/ }),

/***/ "./src/app/layout/add-report-page/add-report.component.scss":
/*!******************************************************************!*\
  !*** ./src/app/layout/add-report-page/add-report.component.scss ***!
  \******************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = ".profile-page {\n  display: flex;\n  align-items: center;\n  justify-content: center;\n  height: 100%;\n  position: relative; }\n  .profile-page .content {\n    z-index: 1;\n    display: flex;\n    align-items: center;\n    justify-content: center; }\n  .profile-page .content .app-name {\n      margin-top: 0px;\n      padding-bottom: 10px;\n      font-size: 32px; }\n  .profile-page .content .profile-page {\n      padding: 40px;\n      background: #fff;\n      width: 500px;\n      box-shadow: 0 0 10px #ddd; }\n  .profile-page .content .profile-page input:-webkit-autofill {\n        -webkit-box-shadow: 0 0 0 30px white inset; }\n  .profile-page:after {\n    content: '';\n    background: #fff;\n    position: absolute;\n    top: 0;\n    left: 0;\n    bottom: 50%;\n    right: 0; }\n  .profile-page:before {\n    content: '';\n    background: #3f51b5;\n    position: absolute;\n    top: 50%;\n    left: 0;\n    bottom: 0;\n    right: 0; }\n  .text-center {\n  text-align: center; }\n  .w-100 {\n  width: 100%; }\n  .example-h2 {\n  margin: 10px; }\n  .example-section {\n  display: flex;\n  align-content: center;\n  align-items: center;\n  height: 60px; }\n  .example-margin {\n  margin: 0 10px; }\n  .mat-card-image {\n  width: 200px;\n  height: 200px; }\n\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbInNyYy9hcHAvbGF5b3V0L2FkZC1yZXBvcnQtcGFnZS9DOlxcVXNlcnNcXEhhbWlkXFxEZXNrdG9wXFxBTFNES1xcZXhhbXBsZXNcXHNiLWFkbWluLW1hdGVyaWFsL3NyY1xcYXBwXFxsYXlvdXRcXGFkZC1yZXBvcnQtcGFnZVxcYWRkLXJlcG9ydC5jb21wb25lbnQuc2NzcyJdLCJuYW1lcyI6W10sIm1hcHBpbmdzIjoiQUFBQTtFQUNJLGFBQWE7RUFDYixtQkFBbUI7RUFDbkIsdUJBQXVCO0VBQ3ZCLFlBQVk7RUFDWixrQkFBa0IsRUFBQTtFQUx0QjtJQU9RLFVBQVU7SUFDVixhQUFhO0lBQ2IsbUJBQW1CO0lBQ25CLHVCQUF1QixFQUFBO0VBVi9CO01BWVksZUFBZTtNQUNmLG9CQUFvQjtNQUNwQixlQUFlLEVBQUE7RUFkM0I7TUFpQlksYUFBYTtNQUNiLGdCQUFnQjtNQUNoQixZQUFZO01BQ1oseUJBQXlCLEVBQUE7RUFwQnJDO1FBc0JnQiwwQ0FBMEMsRUFBQTtFQXRCMUQ7SUE0QlEsV0FBVztJQUNYLGdCQUFnQjtJQUNoQixrQkFBa0I7SUFDbEIsTUFBTTtJQUNOLE9BQU87SUFDUCxXQUFXO0lBQ1gsUUFBUSxFQUFBO0VBbENoQjtJQXFDUSxXQUFXO0lBQ1gsbUJBQW1CO0lBQ25CLGtCQUFrQjtJQUNsQixRQUFRO0lBQ1IsT0FBTztJQUNQLFNBQVM7SUFDVCxRQUFRLEVBQUE7RUFHaEI7RUFDSSxrQkFBa0IsRUFBQTtFQUV0QjtFQUNJLFdBQVcsRUFBQTtFQUdmO0VBQ0ksWUFBWSxFQUFBO0VBR2Q7RUFDRSxhQUFhO0VBQ2IscUJBQXFCO0VBQ3JCLG1CQUFtQjtFQUNuQixZQUFZLEVBQUE7RUFHZDtFQUNFLGNBQWMsRUFBQTtFQUdoQjtFQUNFLFlBQVc7RUFDWCxhQUFZLEVBQUEiLCJmaWxlIjoic3JjL2FwcC9sYXlvdXQvYWRkLXJlcG9ydC1wYWdlL2FkZC1yZXBvcnQuY29tcG9uZW50LnNjc3MiLCJzb3VyY2VzQ29udGVudCI6WyIucHJvZmlsZS1wYWdlIHtcclxuICAgIGRpc3BsYXk6IGZsZXg7XHJcbiAgICBhbGlnbi1pdGVtczogY2VudGVyO1xyXG4gICAganVzdGlmeS1jb250ZW50OiBjZW50ZXI7XHJcbiAgICBoZWlnaHQ6IDEwMCU7XHJcbiAgICBwb3NpdGlvbjogcmVsYXRpdmU7XHJcbiAgICAuY29udGVudCB7XHJcbiAgICAgICAgei1pbmRleDogMTtcclxuICAgICAgICBkaXNwbGF5OiBmbGV4O1xyXG4gICAgICAgIGFsaWduLWl0ZW1zOiBjZW50ZXI7XHJcbiAgICAgICAganVzdGlmeS1jb250ZW50OiBjZW50ZXI7XHJcbiAgICAgICAgLmFwcC1uYW1lIHtcclxuICAgICAgICAgICAgbWFyZ2luLXRvcDogMHB4O1xyXG4gICAgICAgICAgICBwYWRkaW5nLWJvdHRvbTogMTBweDtcclxuICAgICAgICAgICAgZm9udC1zaXplOiAzMnB4O1xyXG4gICAgICAgIH1cclxuICAgICAgICAucHJvZmlsZS1wYWdlIHtcclxuICAgICAgICAgICAgcGFkZGluZzogNDBweDtcclxuICAgICAgICAgICAgYmFja2dyb3VuZDogI2ZmZjtcclxuICAgICAgICAgICAgd2lkdGg6IDUwMHB4O1xyXG4gICAgICAgICAgICBib3gtc2hhZG93OiAwIDAgMTBweCAjZGRkO1xyXG4gICAgICAgICAgICBpbnB1dDotd2Via2l0LWF1dG9maWxsIHtcclxuICAgICAgICAgICAgICAgIC13ZWJraXQtYm94LXNoYWRvdzogMCAwIDAgMzBweCB3aGl0ZSBpbnNldDtcclxuICAgICAgICAgICAgfVxyXG4gICAgICAgIH1cclxuICAgIH1cclxuXHJcbiAgICAmOmFmdGVyIHtcclxuICAgICAgICBjb250ZW50OiAnJztcclxuICAgICAgICBiYWNrZ3JvdW5kOiAjZmZmO1xyXG4gICAgICAgIHBvc2l0aW9uOiBhYnNvbHV0ZTtcclxuICAgICAgICB0b3A6IDA7XHJcbiAgICAgICAgbGVmdDogMDtcclxuICAgICAgICBib3R0b206IDUwJTtcclxuICAgICAgICByaWdodDogMDtcclxuICAgIH1cclxuICAgICY6YmVmb3JlIHtcclxuICAgICAgICBjb250ZW50OiAnJztcclxuICAgICAgICBiYWNrZ3JvdW5kOiAjM2Y1MWI1O1xyXG4gICAgICAgIHBvc2l0aW9uOiBhYnNvbHV0ZTtcclxuICAgICAgICB0b3A6IDUwJTtcclxuICAgICAgICBsZWZ0OiAwO1xyXG4gICAgICAgIGJvdHRvbTogMDtcclxuICAgICAgICByaWdodDogMDtcclxuICAgIH1cclxufVxyXG4udGV4dC1jZW50ZXIge1xyXG4gICAgdGV4dC1hbGlnbjogY2VudGVyO1xyXG59XHJcbi53LTEwMCB7XHJcbiAgICB3aWR0aDogMTAwJTtcclxufVxyXG5cclxuLmV4YW1wbGUtaDIge1xyXG4gICAgbWFyZ2luOiAxMHB4O1xyXG4gIH1cclxuICBcclxuICAuZXhhbXBsZS1zZWN0aW9uIHtcclxuICAgIGRpc3BsYXk6IGZsZXg7XHJcbiAgICBhbGlnbi1jb250ZW50OiBjZW50ZXI7XHJcbiAgICBhbGlnbi1pdGVtczogY2VudGVyO1xyXG4gICAgaGVpZ2h0OiA2MHB4O1xyXG4gIH1cclxuICBcclxuICAuZXhhbXBsZS1tYXJnaW4ge1xyXG4gICAgbWFyZ2luOiAwIDEwcHg7XHJcbiAgfVxyXG5cclxuICAubWF0LWNhcmQtaW1hZ2V7XHJcbiAgICB3aWR0aDoyMDBweDtcclxuICAgIGhlaWdodDoyMDBweDtcclxufSJdfQ== */"

/***/ }),

/***/ "./src/app/layout/add-report-page/add-report.component.ts":
/*!****************************************************************!*\
  !*** ./src/app/layout/add-report-page/add-report.component.ts ***!
  \****************************************************************/
/*! exports provided: AddReportComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "AddReportComponent", function() { return AddReportComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/common/http */ "./node_modules/@angular/common/fesm5/http.js");
/* harmony import */ var _angular_material__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/material */ "./node_modules/@angular/material/esm5/material.es5.js");
/* harmony import */ var _activeledger_sdk__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @activeledger/sdk */ "./node_modules/@activeledger/sdk/lib/index.js");
/* harmony import */ var _activeledger_sdk__WEBPACK_IMPORTED_MODULE_3___default = /*#__PURE__*/__webpack_require__.n(_activeledger_sdk__WEBPACK_IMPORTED_MODULE_3__);
/* harmony import */ var src_app_helper_ledgerhelper__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! src/app/helper/ledgerhelper */ "./src/app/helper/ledgerhelper.ts");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};







var AddReportComponent = /** @class */ (function () {
    function AddReportComponent(ledgerHelper, router, http, snackBar) {
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
        this.reports = [];
        this.report = this.ledgerHelper.reports;
        this.dropdownList = [];
        this.selectedItems = [];
        this.selectedDoctorsID = [];
        this.dropdownSettings = {};
    }
    AddReportComponent.prototype.ngOnInit = function () {
        document.getElementById('i_pn').value = src_app_helper_ledgerhelper__WEBPACK_IMPORTED_MODULE_4__["LedgerHelper"].full_name;
        var doctorlist = JSON.parse(this.ledgerHelper.userList);
        for (var i = 0; i < doctorlist.length; i++) {
            var item = { item_id: doctorlist[i]._id, item_text: '' + doctorlist[i].first_name + ' ' + doctorlist[i].last_name };
            this.dropdownList.push(item);
        }
        this.dropdownSettings = {
            singleSelection: false,
            idField: 'item_id',
            textField: 'item_text',
            selectAllText: 'Select All',
            unSelectAllText: 'UnSelect All',
            // itemsShowLimit: 3,
            allowSearchFilter: true
        };
    };
    AddReportComponent.prototype.onItemSelect = function (item) {
    };
    AddReportComponent.prototype.onSelectAll = function (items) {
    };
    AddReportComponent.prototype.onAddReport = function () {
        var _this = this;
        var id = src_app_helper_ledgerhelper__WEBPACK_IMPORTED_MODULE_4__["LedgerHelper"]._id;
        var headers = new _angular_common_http__WEBPACK_IMPORTED_MODULE_1__["HttpHeaders"]({
            'Content-Type': 'application/json',
            'Authorization': src_app_helper_ledgerhelper__WEBPACK_IMPORTED_MODULE_4__["LedgerHelper"].token
        });
        for (var i = 0; i < this.selectedItems.length; i++) {
            this.selectedDoctorsID.push(this.selectedItems[i].item_id);
        }
        var report = {
            id: '' + (JSON.parse(this.ledgerHelper.reports).length + 1),
            title: document.getElementById('i_rt').value,
            patientName: src_app_helper_ledgerhelper__WEBPACK_IMPORTED_MODULE_4__["LedgerHelper"].full_name,
            fileName: this.filename,
            description: document.getElementById('i_description').value,
            content: this.fileBase64,
            doctors: this.selectedDoctorsID,
        };
        this.reports.push(report);
        var baseTransaction1 = {
            $tx: {
                $contract: 'report',
                $namespace: 'healthtestnet',
                $entry: 'upload',
                $i: {},
                $o: {},
            },
            $selfsign: false,
            $sigs: {}
        };
        baseTransaction1.$tx.$i[id] = {};
        baseTransaction1.$tx.$o[id] = this.reports;
        console.log(JSON.stringify(baseTransaction1));
        var txHandler = new _activeledger_sdk__WEBPACK_IMPORTED_MODULE_3__["TransactionHandler"]();
        txHandler
            .signTransaction(baseTransaction1, src_app_helper_ledgerhelper__WEBPACK_IMPORTED_MODULE_4__["LedgerHelper"].key)
            .then(function (signedTx) {
            baseTransaction1 = signedTx;
            var signature = baseTransaction1['$sigs']['activeledger'];
            baseTransaction1['$sigs'] = {};
            baseTransaction1['$sigs'][id] = signature;
            console.log(baseTransaction1);
            _this.addReportAPI(JSON.stringify(baseTransaction1), headers)
                .subscribe(function (data) {
                console.log(data);
                if (data.status === 200) {
                    _this.message = 'Report Update Successfully!';
                    _this.open();
                    //update the report in pref
                    _this.reports = JSON.parse(_this.ledgerHelper.reports);
                    _this.reports.push(report);
                    _this.ledgerHelper.reports = JSON.stringify(_this.reports);
                    _this.router.navigate(['/report-list'], { replaceUrl: true });
                }
                else {
                    _this.message = ' Report Update  Failed! ';
                    _this.open();
                }
            });
        })
            .catch();
    };
    AddReportComponent.prototype.fileChange = function (event) {
        var _this = this;
        var fileList = event.target.files;
        if (fileList.length > 0) {
            var file = fileList[0];
            var reader = new FileReader();
            reader.readAsBinaryString(file);
            document.getElementById('i_da').value = file.name;
            this.filename = file.name;
            reader.onload = function (e) {
                var binaryData = e.target.result;
                var base64String = window.btoa(binaryData);
                _this.fileBase64 = base64String;
            };
        }
    };
    AddReportComponent.prototype.addReportAPI = function (body, header) {
        return this.http.post(src_app_helper_ledgerhelper__WEBPACK_IMPORTED_MODULE_4__["LedgerHelper"].addReportURL, body, { headers: header, observe: 'response' });
    };
    AddReportComponent.prototype.open = function () {
        var config = new _angular_material__WEBPACK_IMPORTED_MODULE_2__["MatSnackBarConfig"]();
        config.verticalPosition = this.verticalPosition;
        config.horizontalPosition = this.horizontalPosition;
        config.duration = this.setAutoHide ? this.autoHide : 0;
        this.snackBar.open(this.message, this.action ? this.actionButtonLabel : undefined, config);
    };
    AddReportComponent = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"])({
            selector: 'app-report',
            template: __webpack_require__(/*! ./add-report.component.html */ "./src/app/layout/add-report-page/add-report.component.html"),
            styles: [__webpack_require__(/*! ./add-report.component.scss */ "./src/app/layout/add-report-page/add-report.component.scss")]
        }),
        __metadata("design:paramtypes", [src_app_helper_ledgerhelper__WEBPACK_IMPORTED_MODULE_4__["LedgerHelper"], _angular_router__WEBPACK_IMPORTED_MODULE_5__["Router"],
            _angular_common_http__WEBPACK_IMPORTED_MODULE_1__["HttpClient"], _angular_material__WEBPACK_IMPORTED_MODULE_2__["MatSnackBar"]])
    ], AddReportComponent);
    return AddReportComponent;
}());



/***/ }),

/***/ "./src/app/layout/add-report-page/add-report.module.ts":
/*!*************************************************************!*\
  !*** ./src/app/layout/add-report-page/add-report.module.ts ***!
  \*************************************************************/
/*! exports provided: AddReportModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "AddReportModule", function() { return AddReportModule; });
/* harmony import */ var _angular_common__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/common */ "./node_modules/@angular/common/fesm5/common.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_material__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/material */ "./node_modules/@angular/material/esm5/material.es5.js");
/* harmony import */ var _add_report_routing_module__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./add-report-routing.module */ "./src/app/layout/add-report-page/add-report-routing.module.ts");
/* harmony import */ var _add_report_component__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ./add-report.component */ "./src/app/layout/add-report-page/add-report.component.ts");
/* harmony import */ var ng_multiselect_dropdown__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! ng-multiselect-dropdown */ "./node_modules/ng-multiselect-dropdown/fesm5/ng-multiselect-dropdown.js");
/* harmony import */ var _angular_forms__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! @angular/forms */ "./node_modules/@angular/forms/fesm5/forms.js");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};










var AddReportModule = /** @class */ (function () {
    function AddReportModule() {
    }
    AddReportModule = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["NgModule"])({
            imports: [
                _angular_common__WEBPACK_IMPORTED_MODULE_0__["CommonModule"],
                _add_report_routing_module__WEBPACK_IMPORTED_MODULE_3__["AddReportRoutingModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_2__["MatTableModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_2__["MatFormFieldModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_2__["MatPaginatorModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_2__["MatInputModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_2__["MatSnackBarModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_2__["MatButtonModule"],
                ng_multiselect_dropdown__WEBPACK_IMPORTED_MODULE_5__["NgMultiSelectDropDownModule"],
                _angular_forms__WEBPACK_IMPORTED_MODULE_6__["FormsModule"],
                _angular_forms__WEBPACK_IMPORTED_MODULE_6__["ReactiveFormsModule"]
            ],
            declarations: [_add_report_component__WEBPACK_IMPORTED_MODULE_4__["AddReportComponent"]]
        })
    ], AddReportModule);
    return AddReportModule;
}());



/***/ })

}]);
//# sourceMappingURL=add-report-page-add-report-module.js.map