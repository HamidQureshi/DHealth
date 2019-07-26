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

module.exports = "<div class=\"report-page\">\r\n    <div class=\"content\">\r\n        <form class=\"report-form\" ng-controller=\"formCtrl\" fxFlex>\r\n\r\n            <div fxFlex fxLayout=\"row\" fxLayout.lt-md=\"column\">\r\n                <div fxFlex style=\"margin: 0px 0px 0px 0\">\r\n                    <div fxFlexFill>\r\n                        <mat-form-field class=\"w-100\">\r\n                            <input id=\"i_pn\" #patientname_f matInput placeholder=\"Patient Name  \" disabled=\"disabled\"\r\n                                style=\"color: black;\">\r\n                        </mat-form-field>\r\n                    </div>\r\n                </div>\r\n\r\n                <div fxFlex fxLayoutAlign=\"end\">\r\n                    <div fxFlexFill>\r\n                        <mat-form-field class=\"w-100\">\r\n                            <input id=\"i_rt\" #title_f matInput placeholder=\"Report Title  \" disabled=\"disabled\"\r\n                                style=\"color: black;\">\r\n                        </mat-form-field>\r\n                    </div>\r\n                </div>\r\n            </div>\r\n\r\n        <div fxFlex fxlayout=\"row\" fxlayout.lt-md=\"column\">\r\n            <div fxFlex style=\"margin: 0px 20px 20px 0px\">\r\n            <div fxFlexFill class=\"w-100\">\r\n                <ng-multiselect-dropdown [placeholder]=\"'Please Select Doctor'\" [data]=\"dropdownList\" [(ngModel)]=\"selectedItems\" [hidden] = \"cannot_assign_patients\"\r\n                    [settings]=\"dropdownSettings\" (onSelect)=\"onItemSelect($event)\" (onSelectAll)=\"onSelectAll($event)\" name = \"doctorlist\" >\r\n                </ng-multiselect-dropdown>\r\n            </div>\r\n            </div>\r\n        </div>\r\n\r\n            <div fxFlex fxlayout=\"row\" fxlayout.lt-md=\"column\">\r\n                <div fxFlexFill>\r\n                    <mat-form-field class=\"w-100\">\r\n                        <input id=\"i_description\" #description_f matInput placeholder=\"Description  \"\r\n                            disabled=\"disabled\" style=\"color: black;\">\r\n                    </mat-form-field>\r\n                </div>\r\n            </div>\r\n\r\n            <div fxFlex fxlayout=\"row\" fxlayout.lt-md=\"column\">\r\n                <div fxFlexFill>\r\n                    <mat-form-field class=\"w-100\">\r\n                        <input id=\"i_da\" #doc_f matInput placeholder=\"Document Attached  \" disabled=\"disabled\"\r\n                            style=\"color: black; \"  >\r\n                    </mat-form-field>\r\n                </div>\r\n            </div>\r\n\r\n\r\n            <div fxFlex fxLayout=\"row\" fxLayout.lt-md=\"column\">\r\n\r\n                <div fxFlexFill>\r\n                    <div fxFlex fxLayoutAlign=\"end\">\r\n                        <button mat-raised-button color=\"primary\" class=\"w-50\" (click)=\"showPDF()\"\r\n                            type='button'>Download PDF</button>\r\n                        <button mat-raised-button color=\"primary\" class=\"w-50\"  [disabled]=\"cannot_assign_patients\"\r\n                            (click)=\"onUpdateReport()\" type='button' \r\n                            style=\"margin: 0px 20px 0px 20px\">Update Report</button>\r\n                    </div>\r\n                </div>\r\n            </div>\r\n\r\n\r\n\r\n        </form>\r\n\r\n      \r\n\r\n    </div>\r\n</div>"

/***/ }),

/***/ "./src/app/layout/report-page/report.component.scss":
/*!**********************************************************!*\
  !*** ./src/app/layout/report-page/report.component.scss ***!
  \**********************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = ".profile-page {\n  display: flex;\n  align-items: center;\n  justify-content: center;\n  height: 100%;\n  position: relative; }\n  .profile-page .content {\n    z-index: 1;\n    display: flex;\n    align-items: center;\n    justify-content: center; }\n  .profile-page .content .app-name {\n      margin-top: 0px;\n      padding-bottom: 10px;\n      font-size: 32px; }\n  .profile-page .content .profile-page {\n      padding: 40px;\n      background: #fff;\n      width: 500px;\n      box-shadow: 0 0 10px #ddd; }\n  .profile-page .content .profile-page input:-webkit-autofill {\n        -webkit-box-shadow: 0 0 0 30px white inset; }\n  .profile-page:after {\n    content: '';\n    background: #fff;\n    position: absolute;\n    top: 0;\n    left: 0;\n    bottom: 50%;\n    right: 0; }\n  .profile-page:before {\n    content: '';\n    background: #3f51b5;\n    position: absolute;\n    top: 50%;\n    left: 0;\n    bottom: 0;\n    right: 0; }\n  .text-center {\n  text-align: center; }\n  .w-100 {\n  width: 100%; }\n  .example-h2 {\n  margin: 10px; }\n  .example-section {\n  display: flex;\n  align-content: center;\n  align-items: center;\n  height: 60px; }\n  .example-margin {\n  margin: 0 10px; }\n  .mat-card-image {\n  width: 200px;\n  height: 200px; }\n\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbInNyYy9hcHAvbGF5b3V0L3JlcG9ydC1wYWdlL0M6XFxVc2Vyc1xcSGFtaWRcXERlc2t0b3BcXEFMU0RLXFxESGVhbHRoXFxoZWFsdGhcXERIZWFsdGgtV2ViL3NyY1xcYXBwXFxsYXlvdXRcXHJlcG9ydC1wYWdlXFxyZXBvcnQuY29tcG9uZW50LnNjc3MiXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IkFBQUE7RUFDSSxhQUFhO0VBQ2IsbUJBQW1CO0VBQ25CLHVCQUF1QjtFQUN2QixZQUFZO0VBQ1osa0JBQWtCLEVBQUE7RUFMdEI7SUFPUSxVQUFVO0lBQ1YsYUFBYTtJQUNiLG1CQUFtQjtJQUNuQix1QkFBdUIsRUFBQTtFQVYvQjtNQVlZLGVBQWU7TUFDZixvQkFBb0I7TUFDcEIsZUFBZSxFQUFBO0VBZDNCO01BaUJZLGFBQWE7TUFDYixnQkFBZ0I7TUFDaEIsWUFBWTtNQUNaLHlCQUF5QixFQUFBO0VBcEJyQztRQXNCZ0IsMENBQTBDLEVBQUE7RUF0QjFEO0lBNEJRLFdBQVc7SUFDWCxnQkFBZ0I7SUFDaEIsa0JBQWtCO0lBQ2xCLE1BQU07SUFDTixPQUFPO0lBQ1AsV0FBVztJQUNYLFFBQVEsRUFBQTtFQWxDaEI7SUFxQ1EsV0FBVztJQUNYLG1CQUFtQjtJQUNuQixrQkFBa0I7SUFDbEIsUUFBUTtJQUNSLE9BQU87SUFDUCxTQUFTO0lBQ1QsUUFBUSxFQUFBO0VBR2hCO0VBQ0ksa0JBQWtCLEVBQUE7RUFFdEI7RUFDSSxXQUFXLEVBQUE7RUFHZjtFQUNJLFlBQVksRUFBQTtFQUdkO0VBQ0UsYUFBYTtFQUNiLHFCQUFxQjtFQUNyQixtQkFBbUI7RUFDbkIsWUFBWSxFQUFBO0VBR2Q7RUFDRSxjQUFjLEVBQUE7RUFHaEI7RUFDRSxZQUFXO0VBQ1gsYUFBWSxFQUFBIiwiZmlsZSI6InNyYy9hcHAvbGF5b3V0L3JlcG9ydC1wYWdlL3JlcG9ydC5jb21wb25lbnQuc2NzcyIsInNvdXJjZXNDb250ZW50IjpbIi5wcm9maWxlLXBhZ2Uge1xyXG4gICAgZGlzcGxheTogZmxleDtcclxuICAgIGFsaWduLWl0ZW1zOiBjZW50ZXI7XHJcbiAgICBqdXN0aWZ5LWNvbnRlbnQ6IGNlbnRlcjtcclxuICAgIGhlaWdodDogMTAwJTtcclxuICAgIHBvc2l0aW9uOiByZWxhdGl2ZTtcclxuICAgIC5jb250ZW50IHtcclxuICAgICAgICB6LWluZGV4OiAxO1xyXG4gICAgICAgIGRpc3BsYXk6IGZsZXg7XHJcbiAgICAgICAgYWxpZ24taXRlbXM6IGNlbnRlcjtcclxuICAgICAgICBqdXN0aWZ5LWNvbnRlbnQ6IGNlbnRlcjtcclxuICAgICAgICAuYXBwLW5hbWUge1xyXG4gICAgICAgICAgICBtYXJnaW4tdG9wOiAwcHg7XHJcbiAgICAgICAgICAgIHBhZGRpbmctYm90dG9tOiAxMHB4O1xyXG4gICAgICAgICAgICBmb250LXNpemU6IDMycHg7XHJcbiAgICAgICAgfVxyXG4gICAgICAgIC5wcm9maWxlLXBhZ2Uge1xyXG4gICAgICAgICAgICBwYWRkaW5nOiA0MHB4O1xyXG4gICAgICAgICAgICBiYWNrZ3JvdW5kOiAjZmZmO1xyXG4gICAgICAgICAgICB3aWR0aDogNTAwcHg7XHJcbiAgICAgICAgICAgIGJveC1zaGFkb3c6IDAgMCAxMHB4ICNkZGQ7XHJcbiAgICAgICAgICAgIGlucHV0Oi13ZWJraXQtYXV0b2ZpbGwge1xyXG4gICAgICAgICAgICAgICAgLXdlYmtpdC1ib3gtc2hhZG93OiAwIDAgMCAzMHB4IHdoaXRlIGluc2V0O1xyXG4gICAgICAgICAgICB9XHJcbiAgICAgICAgfVxyXG4gICAgfVxyXG5cclxuICAgICY6YWZ0ZXIge1xyXG4gICAgICAgIGNvbnRlbnQ6ICcnO1xyXG4gICAgICAgIGJhY2tncm91bmQ6ICNmZmY7XHJcbiAgICAgICAgcG9zaXRpb246IGFic29sdXRlO1xyXG4gICAgICAgIHRvcDogMDtcclxuICAgICAgICBsZWZ0OiAwO1xyXG4gICAgICAgIGJvdHRvbTogNTAlO1xyXG4gICAgICAgIHJpZ2h0OiAwO1xyXG4gICAgfVxyXG4gICAgJjpiZWZvcmUge1xyXG4gICAgICAgIGNvbnRlbnQ6ICcnO1xyXG4gICAgICAgIGJhY2tncm91bmQ6ICMzZjUxYjU7XHJcbiAgICAgICAgcG9zaXRpb246IGFic29sdXRlO1xyXG4gICAgICAgIHRvcDogNTAlO1xyXG4gICAgICAgIGxlZnQ6IDA7XHJcbiAgICAgICAgYm90dG9tOiAwO1xyXG4gICAgICAgIHJpZ2h0OiAwO1xyXG4gICAgfVxyXG59XHJcbi50ZXh0LWNlbnRlciB7XHJcbiAgICB0ZXh0LWFsaWduOiBjZW50ZXI7XHJcbn1cclxuLnctMTAwIHtcclxuICAgIHdpZHRoOiAxMDAlO1xyXG59XHJcblxyXG4uZXhhbXBsZS1oMiB7XHJcbiAgICBtYXJnaW46IDEwcHg7XHJcbiAgfVxyXG4gIFxyXG4gIC5leGFtcGxlLXNlY3Rpb24ge1xyXG4gICAgZGlzcGxheTogZmxleDtcclxuICAgIGFsaWduLWNvbnRlbnQ6IGNlbnRlcjtcclxuICAgIGFsaWduLWl0ZW1zOiBjZW50ZXI7XHJcbiAgICBoZWlnaHQ6IDYwcHg7XHJcbiAgfVxyXG4gIFxyXG4gIC5leGFtcGxlLW1hcmdpbiB7XHJcbiAgICBtYXJnaW46IDAgMTBweDtcclxuICB9XHJcblxyXG4gIC5tYXQtY2FyZC1pbWFnZXtcclxuICAgIHdpZHRoOjIwMHB4O1xyXG4gICAgaGVpZ2h0OjIwMHB4O1xyXG59Il19 */"

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
/* harmony import */ var _activeledger_sdk__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @activeledger/sdk */ "./node_modules/@activeledger/sdk/lib/index.js");
/* harmony import */ var _activeledger_sdk__WEBPACK_IMPORTED_MODULE_2___default = /*#__PURE__*/__webpack_require__.n(_activeledger_sdk__WEBPACK_IMPORTED_MODULE_2__);
/* harmony import */ var src_app_helper_ledgerhelper__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! src/app/helper/ledgerhelper */ "./src/app/helper/ledgerhelper.ts");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
/* harmony import */ var _layout_component__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! ../layout.component */ "./src/app/layout/layout.component.ts");
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
    function ReportComponent(layoutComp, ledgerHelper, http, router) {
        this.layoutComp = layoutComp;
        this.ledgerHelper = ledgerHelper;
        this.http = http;
        this.router = router;
        this.reports = [];
        this.report = JSON.parse(this.ledgerHelper.report);
        this.dropdownList = [];
        this.selectedItems = [];
        this.selectedDoctorsID = [];
        this.dropdownSettings = {};
        this.cannot_assign_patients = false;
    }
    ReportComponent.prototype.ngOnInit = function () {
        document.getElementById('i_pn').value = this.report.patientName;
        document.getElementById('i_rt').value = this.report.title;
        document.getElementById('i_description').value = this.report.description;
        document.getElementById('i_da').value = this.report.fileName;
        var doctorlist = JSON.parse(this.ledgerHelper.userList);
        var selected = this.report.doctors;
        if (this.ledgerHelper.profile_type === 'Doctor') {
            this.cannot_assign_patients = true;
            return;
        }
        for (var i = 0; i < doctorlist.length; i++) {
            var item = { item_id: doctorlist[i]._id, item_text: '' + doctorlist[i].first_name + ' ' + doctorlist[i].last_name };
            this.dropdownList.push(item);
            for (var j = 0; j < selected.length; j++) {
                if (doctorlist[i]._id === selected[j]) {
                    var item_1 = { item_id: doctorlist[i]._id, item_text: '' + doctorlist[i].first_name + ' ' + doctorlist[i].last_name };
                    this.selectedItems.push(item_1);
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
        var downloadLink = document.createElement('a');
        var fileName = this.report.fileName;
        downloadLink.href = linkSource;
        downloadLink.download = fileName;
        downloadLink.click();
    };
    ReportComponent.prototype.onUpdateReport = function () {
        var _this = this;
        var id = this.ledgerHelper._id;
        var headers = new _angular_common_http__WEBPACK_IMPORTED_MODULE_1__["HttpHeaders"]({
            'Content-Type': 'application/json',
            'Authorization': '' + this.ledgerHelper.token
        });
        for (var i = 0; i < this.selectedItems.length; i++) {
            this.selectedDoctorsID.push(this.selectedItems[i].item_id);
        }
        var report = {
            id: this.report.id,
            title: this.report.title,
            patientName: this.report.patientName,
            fileName: this.report.fileName,
            description: this.report.description,
            content: this.report.content,
            doctors: this.selectedDoctorsID
        };
        var reports = [];
        reports.push(report);
        var baseTransaction1 = {
            $tx: {
                $contract: 'report',
                $namespace: 'health',
                $entry: 'update',
                $i: {},
                $o: {},
            },
            $selfsign: false,
            $sigs: {}
        };
        baseTransaction1.$tx.$i[id] = {};
        baseTransaction1.$tx.$o[id] = reports;
        console.log(JSON.stringify(baseTransaction1));
        var txHandler = new _activeledger_sdk__WEBPACK_IMPORTED_MODULE_2__["TransactionHandler"]();
        txHandler
            .signTransaction(baseTransaction1, this.ledgerHelper.key)
            .then(function (signedTx) {
            baseTransaction1 = signedTx;
            var signature = baseTransaction1['$sigs']['activeledger'];
            baseTransaction1['$sigs'] = {};
            baseTransaction1['$sigs'][id] = signature;
            console.log(baseTransaction1);
            _this.updateReportAPI(baseTransaction1, headers)
                .subscribe(function (data) {
                console.log(data);
                if (data.status === 200) {
                    _this.layoutComp.showSnackBar('Report Update Successfully!');
                    _this.reports = JSON.parse(_this.ledgerHelper.reports);
                    _this.reports.find(function (item) { return item.id == _this.report.id; }).doctors = _this.selectedDoctorsID;
                    _this.ledgerHelper.reports = JSON.stringify(_this.reports);
                    _this.router.navigate(['/report-list'], { replaceUrl: true });
                }
                else {
                    _this.layoutComp.showSnackBar(' Report Update  Failed! ');
                }
            });
        })
            .catch();
    };
    ReportComponent.prototype.updateReportAPI = function (body, header) {
        return this.http.post(this.ledgerHelper.updateReportURL, body, { headers: header, observe: 'response' });
    };
    ReportComponent = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"])({
            selector: 'app-report',
            template: __webpack_require__(/*! ./report.component.html */ "./src/app/layout/report-page/report.component.html"),
            styles: [__webpack_require__(/*! ./report.component.scss */ "./src/app/layout/report-page/report.component.scss")]
        }),
        __metadata("design:paramtypes", [_layout_component__WEBPACK_IMPORTED_MODULE_5__["LayoutComponent"], src_app_helper_ledgerhelper__WEBPACK_IMPORTED_MODULE_3__["LedgerHelper"], _angular_common_http__WEBPACK_IMPORTED_MODULE_1__["HttpClient"],
            _angular_router__WEBPACK_IMPORTED_MODULE_4__["Router"]])
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