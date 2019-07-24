(window["webpackJsonp"] = window["webpackJsonp"] || []).push([["report-page-report-module"],{

/***/ "./src/app/layout/report-page/report-routing.module.ts":
/*!*************************************************************!*\
  !*** ./src/app/layout/report-page/report-routing.module.ts ***!
  \*************************************************************/
/*! exports provided: ReportRoutingModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "ReportRoutingModule", function() { return ReportRoutingModule; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
/* harmony import */ var _report_component__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./report.component */ "./src/app/layout/report-page/report.component.ts");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};



var routes = [
    {
        path: '',
        component: _report_component__WEBPACK_IMPORTED_MODULE_2__["ReportComponent"]
    }
];
var ReportRoutingModule = /** @class */ (function () {
    function ReportRoutingModule() {
    }
    ReportRoutingModule = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["NgModule"])({
            imports: [_angular_router__WEBPACK_IMPORTED_MODULE_1__["RouterModule"].forChild(routes)],
            exports: [_angular_router__WEBPACK_IMPORTED_MODULE_1__["RouterModule"]]
        })
    ], ReportRoutingModule);
    return ReportRoutingModule;
}());



/***/ }),

/***/ "./src/app/layout/report-page/report.component.html":
/*!**********************************************************!*\
  !*** ./src/app/layout/report-page/report.component.html ***!
  \**********************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<div class=\"report-page\">\r\n    <div class=\"content\">\r\n        <form class=\"report-form\" ng-controller=\"formCtrl\" fxFlex>\r\n\r\n            <div fxFlex fxLayout=\"row\" fxLayout.lt-md=\"column\">\r\n                <div fxFlex style=\"margin: 0px 20px 0px 0\">\r\n                    <div fxFlexFill>\r\n                        <mat-form-field class=\"w-100\">\r\n                            <input id=\"i_pn\" #patientname_f matInput placeholder=\"Patient Name  \" disabled=\"disabled\"\r\n                                style=\"color: black;\">\r\n                        </mat-form-field>\r\n                    </div>\r\n                </div>\r\n\r\n                <div fxFlex fxLayoutAlign=\"end\">\r\n                    <div fxFlexFill>\r\n                        <mat-form-field class=\"w-100\">\r\n                            <input id=\"i_rt\" #title_f matInput placeholder=\"Report Title  \" disabled=\"disabled\"\r\n                                style=\"color: black;\">\r\n                        </mat-form-field>\r\n                    </div>\r\n                </div>\r\n            </div>\r\n\r\n        <div fxFlex fxlayout=\"row\" fxlayout.lt-md=\"column\">\r\n            <div fxFlex style=\"margin: 0px 20px 0px 0\">\r\n            <div fxFlexFill class=\"w-100\">\r\n                <ng-multiselect-dropdown [placeholder]=\"'No Doctor Selected'\" [data]=\"dropdownList\" [(ngModel)]=\"selectedItems\"\r\n                    [settings]=\"dropdownSettings\" (onSelect)=\"onItemSelect($event)\" (onSelectAll)=\"onSelectAll($event)\" name = \"doctorlist\" >\r\n                </ng-multiselect-dropdown>\r\n            </div>\r\n            </div>\r\n        </div>\r\n\r\n            <div fxFlex fxlayout=\"row\" fxlayout.lt-md=\"column\">\r\n                <div fxFlexFill>\r\n                    <mat-form-field class=\"w-100\">\r\n                        <input id=\"i_description\" #description_f matInput placeholder=\"Description  \"\r\n                            disabled=\"disabled\" style=\"color: black;\">\r\n                    </mat-form-field>\r\n                </div>\r\n            </div>\r\n\r\n            <div fxFlex fxlayout=\"row\" fxlayout.lt-md=\"column\">\r\n                <div fxFlexFill>\r\n                    <mat-form-field class=\"w-100\">\r\n                        <input id=\"i_da\" #doc_f matInput placeholder=\"Document Attached  \" disabled=\"disabled\"\r\n                            style=\"color: black; \"  >\r\n                    </mat-form-field>\r\n                </div>\r\n            </div>\r\n\r\n\r\n            <div fxFlex fxLayout=\"row\" fxLayout.lt-md=\"column\">\r\n\r\n                <div fxFlexFill>\r\n                    <div fxFlex fxLayoutAlign=\"end\">\r\n                        <button mat-raised-button color=\"primary\" class=\"w-50\" (click)=\"showPDF()\"\r\n                            type='button'>Download PDF</button>\r\n                        <button mat-raised-button color=\"primary\" class=\"w-50\"\r\n                            (click)=\"onUpdateReport()\" type='button'\r\n                            style=\"margin: 0px 20px 0px 20px\">Update Report</button>\r\n                    </div>\r\n                </div>\r\n            </div>\r\n\r\n\r\n\r\n        </form>\r\n\r\n      \r\n\r\n    </div>\r\n</div>"

/***/ }),

/***/ "./src/app/layout/report-page/report.component.scss":
/*!**********************************************************!*\
  !*** ./src/app/layout/report-page/report.component.scss ***!
  \**********************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = ".profile-page {\n  display: flex;\n  align-items: center;\n  justify-content: center;\n  height: 100%;\n  position: relative; }\n  .profile-page .content {\n    z-index: 1;\n    display: flex;\n    align-items: center;\n    justify-content: center; }\n  .profile-page .content .app-name {\n      margin-top: 0px;\n      padding-bottom: 10px;\n      font-size: 32px; }\n  .profile-page .content .profile-page {\n      padding: 40px;\n      background: #fff;\n      width: 500px;\n      box-shadow: 0 0 10px #ddd; }\n  .profile-page .content .profile-page input:-webkit-autofill {\n        -webkit-box-shadow: 0 0 0 30px white inset; }\n  .profile-page:after {\n    content: '';\n    background: #fff;\n    position: absolute;\n    top: 0;\n    left: 0;\n    bottom: 50%;\n    right: 0; }\n  .profile-page:before {\n    content: '';\n    background: #3f51b5;\n    position: absolute;\n    top: 50%;\n    left: 0;\n    bottom: 0;\n    right: 0; }\n  .text-center {\n  text-align: center; }\n  .w-100 {\n  width: 100%; }\n  .example-h2 {\n  margin: 10px; }\n  .example-section {\n  display: flex;\n  align-content: center;\n  align-items: center;\n  height: 60px; }\n  .example-margin {\n  margin: 0 10px; }\n  .mat-card-image {\n  width: 200px;\n  height: 200px; }\n\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbInNyYy9hcHAvbGF5b3V0L3JlcG9ydC1wYWdlL0M6XFxVc2Vyc1xcSGFtaWRcXERlc2t0b3BcXEFMU0RLXFxleGFtcGxlc1xcc2ItYWRtaW4tbWF0ZXJpYWwvc3JjXFxhcHBcXGxheW91dFxccmVwb3J0LXBhZ2VcXHJlcG9ydC5jb21wb25lbnQuc2NzcyJdLCJuYW1lcyI6W10sIm1hcHBpbmdzIjoiQUFBQTtFQUNJLGFBQWE7RUFDYixtQkFBbUI7RUFDbkIsdUJBQXVCO0VBQ3ZCLFlBQVk7RUFDWixrQkFBa0IsRUFBQTtFQUx0QjtJQU9RLFVBQVU7SUFDVixhQUFhO0lBQ2IsbUJBQW1CO0lBQ25CLHVCQUF1QixFQUFBO0VBVi9CO01BWVksZUFBZTtNQUNmLG9CQUFvQjtNQUNwQixlQUFlLEVBQUE7RUFkM0I7TUFpQlksYUFBYTtNQUNiLGdCQUFnQjtNQUNoQixZQUFZO01BQ1oseUJBQXlCLEVBQUE7RUFwQnJDO1FBc0JnQiwwQ0FBMEMsRUFBQTtFQXRCMUQ7SUE0QlEsV0FBVztJQUNYLGdCQUFnQjtJQUNoQixrQkFBa0I7SUFDbEIsTUFBTTtJQUNOLE9BQU87SUFDUCxXQUFXO0lBQ1gsUUFBUSxFQUFBO0VBbENoQjtJQXFDUSxXQUFXO0lBQ1gsbUJBQW1CO0lBQ25CLGtCQUFrQjtJQUNsQixRQUFRO0lBQ1IsT0FBTztJQUNQLFNBQVM7SUFDVCxRQUFRLEVBQUE7RUFHaEI7RUFDSSxrQkFBa0IsRUFBQTtFQUV0QjtFQUNJLFdBQVcsRUFBQTtFQUdmO0VBQ0ksWUFBWSxFQUFBO0VBR2Q7RUFDRSxhQUFhO0VBQ2IscUJBQXFCO0VBQ3JCLG1CQUFtQjtFQUNuQixZQUFZLEVBQUE7RUFHZDtFQUNFLGNBQWMsRUFBQTtFQUdoQjtFQUNFLFlBQVc7RUFDWCxhQUFZLEVBQUEiLCJmaWxlIjoic3JjL2FwcC9sYXlvdXQvcmVwb3J0LXBhZ2UvcmVwb3J0LmNvbXBvbmVudC5zY3NzIiwic291cmNlc0NvbnRlbnQiOlsiLnByb2ZpbGUtcGFnZSB7XHJcbiAgICBkaXNwbGF5OiBmbGV4O1xyXG4gICAgYWxpZ24taXRlbXM6IGNlbnRlcjtcclxuICAgIGp1c3RpZnktY29udGVudDogY2VudGVyO1xyXG4gICAgaGVpZ2h0OiAxMDAlO1xyXG4gICAgcG9zaXRpb246IHJlbGF0aXZlO1xyXG4gICAgLmNvbnRlbnQge1xyXG4gICAgICAgIHotaW5kZXg6IDE7XHJcbiAgICAgICAgZGlzcGxheTogZmxleDtcclxuICAgICAgICBhbGlnbi1pdGVtczogY2VudGVyO1xyXG4gICAgICAgIGp1c3RpZnktY29udGVudDogY2VudGVyO1xyXG4gICAgICAgIC5hcHAtbmFtZSB7XHJcbiAgICAgICAgICAgIG1hcmdpbi10b3A6IDBweDtcclxuICAgICAgICAgICAgcGFkZGluZy1ib3R0b206IDEwcHg7XHJcbiAgICAgICAgICAgIGZvbnQtc2l6ZTogMzJweDtcclxuICAgICAgICB9XHJcbiAgICAgICAgLnByb2ZpbGUtcGFnZSB7XHJcbiAgICAgICAgICAgIHBhZGRpbmc6IDQwcHg7XHJcbiAgICAgICAgICAgIGJhY2tncm91bmQ6ICNmZmY7XHJcbiAgICAgICAgICAgIHdpZHRoOiA1MDBweDtcclxuICAgICAgICAgICAgYm94LXNoYWRvdzogMCAwIDEwcHggI2RkZDtcclxuICAgICAgICAgICAgaW5wdXQ6LXdlYmtpdC1hdXRvZmlsbCB7XHJcbiAgICAgICAgICAgICAgICAtd2Via2l0LWJveC1zaGFkb3c6IDAgMCAwIDMwcHggd2hpdGUgaW5zZXQ7XHJcbiAgICAgICAgICAgIH1cclxuICAgICAgICB9XHJcbiAgICB9XHJcblxyXG4gICAgJjphZnRlciB7XHJcbiAgICAgICAgY29udGVudDogJyc7XHJcbiAgICAgICAgYmFja2dyb3VuZDogI2ZmZjtcclxuICAgICAgICBwb3NpdGlvbjogYWJzb2x1dGU7XHJcbiAgICAgICAgdG9wOiAwO1xyXG4gICAgICAgIGxlZnQ6IDA7XHJcbiAgICAgICAgYm90dG9tOiA1MCU7XHJcbiAgICAgICAgcmlnaHQ6IDA7XHJcbiAgICB9XHJcbiAgICAmOmJlZm9yZSB7XHJcbiAgICAgICAgY29udGVudDogJyc7XHJcbiAgICAgICAgYmFja2dyb3VuZDogIzNmNTFiNTtcclxuICAgICAgICBwb3NpdGlvbjogYWJzb2x1dGU7XHJcbiAgICAgICAgdG9wOiA1MCU7XHJcbiAgICAgICAgbGVmdDogMDtcclxuICAgICAgICBib3R0b206IDA7XHJcbiAgICAgICAgcmlnaHQ6IDA7XHJcbiAgICB9XHJcbn1cclxuLnRleHQtY2VudGVyIHtcclxuICAgIHRleHQtYWxpZ246IGNlbnRlcjtcclxufVxyXG4udy0xMDAge1xyXG4gICAgd2lkdGg6IDEwMCU7XHJcbn1cclxuXHJcbi5leGFtcGxlLWgyIHtcclxuICAgIG1hcmdpbjogMTBweDtcclxuICB9XHJcbiAgXHJcbiAgLmV4YW1wbGUtc2VjdGlvbiB7XHJcbiAgICBkaXNwbGF5OiBmbGV4O1xyXG4gICAgYWxpZ24tY29udGVudDogY2VudGVyO1xyXG4gICAgYWxpZ24taXRlbXM6IGNlbnRlcjtcclxuICAgIGhlaWdodDogNjBweDtcclxuICB9XHJcbiAgXHJcbiAgLmV4YW1wbGUtbWFyZ2luIHtcclxuICAgIG1hcmdpbjogMCAxMHB4O1xyXG4gIH1cclxuXHJcbiAgLm1hdC1jYXJkLWltYWdle1xyXG4gICAgd2lkdGg6MjAwcHg7XHJcbiAgICBoZWlnaHQ6MjAwcHg7XHJcbn0iXX0= */"

/***/ }),

/***/ "./src/app/layout/report-page/report.component.ts":
/*!********************************************************!*\
  !*** ./src/app/layout/report-page/report.component.ts ***!
  \********************************************************/
/*! exports provided: ReportComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "ReportComponent", function() { return ReportComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/common/http */ "./node_modules/@angular/common/fesm5/http.js");
/* harmony import */ var _angular_material__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/material */ "./node_modules/@angular/material/esm5/material.es5.js");
/* harmony import */ var _activeledger_sdk__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @activeledger/sdk */ "./node_modules/@activeledger/sdk/lib/index.js");
/* harmony import */ var _activeledger_sdk__WEBPACK_IMPORTED_MODULE_3___default = /*#__PURE__*/__webpack_require__.n(_activeledger_sdk__WEBPACK_IMPORTED_MODULE_3__);
/* harmony import */ var src_app_helper_ledgerhelper__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! src/app/helper/ledgerhelper */ "./src/app/helper/ledgerhelper.ts");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};






var ReportComponent = /** @class */ (function () {
    function ReportComponent(ledgerHelper, http, snackBar) {
        this.ledgerHelper = ledgerHelper;
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
        this.report = JSON.parse(src_app_helper_ledgerhelper__WEBPACK_IMPORTED_MODULE_4__["LedgerHelper"].report); //JSON.parse(localStorage.getItem('report'));
        this.dropdownList = [];
        this.selectedItems = [];
        this.selectedDoctorsID = [];
        this.dropdownSettings = {};
    }
    ReportComponent.prototype.ngOnInit = function () {
        document.getElementById('i_pn').value = this.report.patientName;
        document.getElementById('i_rt').value = this.report.title;
        document.getElementById('i_description').value = this.report.description;
        document.getElementById('i_da').value = this.report.fileName;
        var doctorlist = JSON.parse(this.ledgerHelper.userList);
        var selected = this.report.doctors;
        for (var i = 0; i < doctorlist.length; i++) {
            var item = { item_id: doctorlist[i]._id, item_text: '' + doctorlist[i].first_name + ' ' + doctorlist[i].last_name };
            this.dropdownList.push(item);
            for (var j = 0; j < selected.length; j++) {
                if (doctorlist[i]._id === selected[j]) {
                    var item = { item_id: doctorlist[i]._id, item_text: '' + doctorlist[i].first_name + ' ' + doctorlist[i].last_name };
                    this.selectedItems.push(item);
                }
            }
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
    ReportComponent.prototype.onItemSelect = function (item) {
    };
    ReportComponent.prototype.onSelectAll = function (items) {
    };
    ReportComponent.prototype.showPDF = function () {
        var linkSource = 'data:application/pdf;base64,' + this.report.content;
        var downloadLink = document.createElement("a");
        var fileName = this.report.fileName;
        downloadLink.href = linkSource;
        downloadLink.download = fileName;
        downloadLink.click();
    };
    ReportComponent.prototype.onUpdateReport = function () {
        var _this = this;
        var id = src_app_helper_ledgerhelper__WEBPACK_IMPORTED_MODULE_4__["LedgerHelper"]._id;
        var headers = new _angular_common_http__WEBPACK_IMPORTED_MODULE_1__["HttpHeaders"]({
            'Content-Type': 'application/json',
            'Authorization': '' + src_app_helper_ledgerhelper__WEBPACK_IMPORTED_MODULE_4__["LedgerHelper"].token
        });
        for (var i = 0; i < this.selectedItems.length; i++) {
            this.selectedDoctorsID.push(this.selectedItems[i].item_id);
        }
        var report = { content: 'content', doctors: this.selectedDoctorsID };
        var reports = [];
        reports.push(report);
        var baseTransaction1 = {};
        baseTransaction1['$tx'] = {};
        baseTransaction1['$tx']['$namespace'] = 'healthtestnet';
        baseTransaction1['$tx']['$contract'] = 'report';
        baseTransaction1['$tx']['$entry'] = 'entry';
        baseTransaction1['$tx']['$i'] = {};
        baseTransaction1['$tx']['$i'][id] = {};
        baseTransaction1['$tx']['$o'] = {};
        baseTransaction1['$tx']['$o'][id] = reports;
        console.log(baseTransaction1);
        var txHandler = new _activeledger_sdk__WEBPACK_IMPORTED_MODULE_3__["TransactionHandler"]();
        var baseTransaction2 = {};
        txHandler
            .signTransaction('' + baseTransaction1, src_app_helper_ledgerhelper__WEBPACK_IMPORTED_MODULE_4__["LedgerHelper"].key)
            .then(function (signature) {
            baseTransaction2['$tx'] = {};
            baseTransaction2['$tx']['$namespace'] = 'healthtestnet';
            baseTransaction2['$tx']['$contract'] = 'report';
            baseTransaction2['$tx']['$entry'] = 'entry';
            baseTransaction2['$tx']['$i'] = {};
            baseTransaction2['$tx']['$i'][id] = {};
            baseTransaction2['$tx']['$o'] = {};
            baseTransaction2['$tx']['$o'][id] = reports;
            baseTransaction2['$selfsign'] = false;
            baseTransaction2['$sigs'] = {};
            baseTransaction2['$sigs'][id] = signature;
            console.log(baseTransaction2);
        })
            .catch();
        this.updateReportAPI(baseTransaction2, headers)
            .subscribe(function (data) {
            console.log(data);
            if (data.status == 200) {
                _this.message = 'Report Update Successfully!';
                _this.open();
                //update the report in pref
            }
            else {
                _this.message = ' Report Update  Failed! ';
                _this.open();
            }
        });
    };
    ReportComponent.prototype.updateReportAPI = function (body, header) {
        return this.http.post(src_app_helper_ledgerhelper__WEBPACK_IMPORTED_MODULE_4__["LedgerHelper"].updateReportURL, body, { headers: header, observe: 'response' });
    };
    ReportComponent.prototype.open = function () {
        var config = new _angular_material__WEBPACK_IMPORTED_MODULE_2__["MatSnackBarConfig"]();
        config.verticalPosition = this.verticalPosition;
        config.horizontalPosition = this.horizontalPosition;
        config.duration = this.setAutoHide ? this.autoHide : 0;
        this.snackBar.open(this.message, this.action ? this.actionButtonLabel : undefined, config);
    };
    ReportComponent = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"])({
            selector: 'app-report',
            template: __webpack_require__(/*! ./report.component.html */ "./src/app/layout/report-page/report.component.html"),
            styles: [__webpack_require__(/*! ./report.component.scss */ "./src/app/layout/report-page/report.component.scss")]
        }),
        __metadata("design:paramtypes", [src_app_helper_ledgerhelper__WEBPACK_IMPORTED_MODULE_4__["LedgerHelper"], _angular_common_http__WEBPACK_IMPORTED_MODULE_1__["HttpClient"], _angular_material__WEBPACK_IMPORTED_MODULE_2__["MatSnackBar"]])
    ], ReportComponent);
    return ReportComponent;
}());



/***/ }),

/***/ "./src/app/layout/report-page/report.module.ts":
/*!*****************************************************!*\
  !*** ./src/app/layout/report-page/report.module.ts ***!
  \*****************************************************/
/*! exports provided: ReportModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "ReportModule", function() { return ReportModule; });
/* harmony import */ var _angular_common__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/common */ "./node_modules/@angular/common/fesm5/common.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_material__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/material */ "./node_modules/@angular/material/esm5/material.es5.js");
/* harmony import */ var _report_routing_module__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./report-routing.module */ "./src/app/layout/report-page/report-routing.module.ts");
/* harmony import */ var _report_component__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ./report.component */ "./src/app/layout/report-page/report.component.ts");
/* harmony import */ var ng_multiselect_dropdown__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! ng-multiselect-dropdown */ "./node_modules/ng-multiselect-dropdown/fesm5/ng-multiselect-dropdown.js");
/* harmony import */ var _angular_forms__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! @angular/forms */ "./node_modules/@angular/forms/fesm5/forms.js");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};










var ReportModule = /** @class */ (function () {
    function ReportModule() {
    }
    ReportModule = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["NgModule"])({
            imports: [
                _angular_common__WEBPACK_IMPORTED_MODULE_0__["CommonModule"],
                _report_routing_module__WEBPACK_IMPORTED_MODULE_3__["ReportRoutingModule"],
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
            declarations: [_report_component__WEBPACK_IMPORTED_MODULE_4__["ReportComponent"]]
        })
    ], ReportModule);
    return ReportModule;
}());



/***/ })

}]);
//# sourceMappingURL=report-page-report-module.js.map