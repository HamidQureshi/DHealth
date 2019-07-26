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

module.exports = ".profile-page {\n  display: flex;\n  align-items: center;\n  justify-content: center;\n  height: 100%;\n  position: relative; }\n  .profile-page .content {\n    z-index: 1;\n    display: flex;\n    align-items: center;\n    justify-content: center; }\n  .profile-page .content .app-name {\n      margin-top: 0px;\n      padding-bottom: 10px;\n      font-size: 32px; }\n  .profile-page .content .profile-page {\n      padding: 40px;\n      background: #fff;\n      width: 500px;\n      box-shadow: 0 0 10px #ddd; }\n  .profile-page .content .profile-page input:-webkit-autofill {\n        -webkit-box-shadow: 0 0 0 30px white inset; }\n  .profile-page:after {\n    content: '';\n    background: #fff;\n    position: absolute;\n    top: 0;\n    left: 0;\n    bottom: 50%;\n    right: 0; }\n  .profile-page:before {\n    content: '';\n    background: #3f51b5;\n    position: absolute;\n    top: 50%;\n    left: 0;\n    bottom: 0;\n    right: 0; }\n  .text-center {\n  text-align: center; }\n  .w-100 {\n  width: 100%; }\n  .example-h2 {\n  margin: 10px; }\n  .example-section {\n  display: flex;\n  align-content: center;\n  align-items: center;\n  height: 60px; }\n  .example-margin {\n  margin: 0 10px; }\n  .mat-card-image {\n  width: 200px;\n  height: 200px; }\n\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbInNyYy9hcHAvbGF5b3V0L2FkZC1yZXBvcnQtcGFnZS9DOlxcVXNlcnNcXEhhbWlkXFxEZXNrdG9wXFxBTFNES1xcREhlYWx0aFxcaGVhbHRoXFxESGVhbHRoLVdlYi9zcmNcXGFwcFxcbGF5b3V0XFxhZGQtcmVwb3J0LXBhZ2VcXGFkZC1yZXBvcnQuY29tcG9uZW50LnNjc3MiXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IkFBQUE7RUFDSSxhQUFhO0VBQ2IsbUJBQW1CO0VBQ25CLHVCQUF1QjtFQUN2QixZQUFZO0VBQ1osa0JBQWtCLEVBQUE7RUFMdEI7SUFPUSxVQUFVO0lBQ1YsYUFBYTtJQUNiLG1CQUFtQjtJQUNuQix1QkFBdUIsRUFBQTtFQVYvQjtNQVlZLGVBQWU7TUFDZixvQkFBb0I7TUFDcEIsZUFBZSxFQUFBO0VBZDNCO01BaUJZLGFBQWE7TUFDYixnQkFBZ0I7TUFDaEIsWUFBWTtNQUNaLHlCQUF5QixFQUFBO0VBcEJyQztRQXNCZ0IsMENBQTBDLEVBQUE7RUF0QjFEO0lBNEJRLFdBQVc7SUFDWCxnQkFBZ0I7SUFDaEIsa0JBQWtCO0lBQ2xCLE1BQU07SUFDTixPQUFPO0lBQ1AsV0FBVztJQUNYLFFBQVEsRUFBQTtFQWxDaEI7SUFxQ1EsV0FBVztJQUNYLG1CQUFtQjtJQUNuQixrQkFBa0I7SUFDbEIsUUFBUTtJQUNSLE9BQU87SUFDUCxTQUFTO0lBQ1QsUUFBUSxFQUFBO0VBR2hCO0VBQ0ksa0JBQWtCLEVBQUE7RUFFdEI7RUFDSSxXQUFXLEVBQUE7RUFHZjtFQUNJLFlBQVksRUFBQTtFQUdkO0VBQ0UsYUFBYTtFQUNiLHFCQUFxQjtFQUNyQixtQkFBbUI7RUFDbkIsWUFBWSxFQUFBO0VBR2Q7RUFDRSxjQUFjLEVBQUE7RUFHaEI7RUFDRSxZQUFXO0VBQ1gsYUFBWSxFQUFBIiwiZmlsZSI6InNyYy9hcHAvbGF5b3V0L2FkZC1yZXBvcnQtcGFnZS9hZGQtcmVwb3J0LmNvbXBvbmVudC5zY3NzIiwic291cmNlc0NvbnRlbnQiOlsiLnByb2ZpbGUtcGFnZSB7XHJcbiAgICBkaXNwbGF5OiBmbGV4O1xyXG4gICAgYWxpZ24taXRlbXM6IGNlbnRlcjtcclxuICAgIGp1c3RpZnktY29udGVudDogY2VudGVyO1xyXG4gICAgaGVpZ2h0OiAxMDAlO1xyXG4gICAgcG9zaXRpb246IHJlbGF0aXZlO1xyXG4gICAgLmNvbnRlbnQge1xyXG4gICAgICAgIHotaW5kZXg6IDE7XHJcbiAgICAgICAgZGlzcGxheTogZmxleDtcclxuICAgICAgICBhbGlnbi1pdGVtczogY2VudGVyO1xyXG4gICAgICAgIGp1c3RpZnktY29udGVudDogY2VudGVyO1xyXG4gICAgICAgIC5hcHAtbmFtZSB7XHJcbiAgICAgICAgICAgIG1hcmdpbi10b3A6IDBweDtcclxuICAgICAgICAgICAgcGFkZGluZy1ib3R0b206IDEwcHg7XHJcbiAgICAgICAgICAgIGZvbnQtc2l6ZTogMzJweDtcclxuICAgICAgICB9XHJcbiAgICAgICAgLnByb2ZpbGUtcGFnZSB7XHJcbiAgICAgICAgICAgIHBhZGRpbmc6IDQwcHg7XHJcbiAgICAgICAgICAgIGJhY2tncm91bmQ6ICNmZmY7XHJcbiAgICAgICAgICAgIHdpZHRoOiA1MDBweDtcclxuICAgICAgICAgICAgYm94LXNoYWRvdzogMCAwIDEwcHggI2RkZDtcclxuICAgICAgICAgICAgaW5wdXQ6LXdlYmtpdC1hdXRvZmlsbCB7XHJcbiAgICAgICAgICAgICAgICAtd2Via2l0LWJveC1zaGFkb3c6IDAgMCAwIDMwcHggd2hpdGUgaW5zZXQ7XHJcbiAgICAgICAgICAgIH1cclxuICAgICAgICB9XHJcbiAgICB9XHJcblxyXG4gICAgJjphZnRlciB7XHJcbiAgICAgICAgY29udGVudDogJyc7XHJcbiAgICAgICAgYmFja2dyb3VuZDogI2ZmZjtcclxuICAgICAgICBwb3NpdGlvbjogYWJzb2x1dGU7XHJcbiAgICAgICAgdG9wOiAwO1xyXG4gICAgICAgIGxlZnQ6IDA7XHJcbiAgICAgICAgYm90dG9tOiA1MCU7XHJcbiAgICAgICAgcmlnaHQ6IDA7XHJcbiAgICB9XHJcbiAgICAmOmJlZm9yZSB7XHJcbiAgICAgICAgY29udGVudDogJyc7XHJcbiAgICAgICAgYmFja2dyb3VuZDogIzNmNTFiNTtcclxuICAgICAgICBwb3NpdGlvbjogYWJzb2x1dGU7XHJcbiAgICAgICAgdG9wOiA1MCU7XHJcbiAgICAgICAgbGVmdDogMDtcclxuICAgICAgICBib3R0b206IDA7XHJcbiAgICAgICAgcmlnaHQ6IDA7XHJcbiAgICB9XHJcbn1cclxuLnRleHQtY2VudGVyIHtcclxuICAgIHRleHQtYWxpZ246IGNlbnRlcjtcclxufVxyXG4udy0xMDAge1xyXG4gICAgd2lkdGg6IDEwMCU7XHJcbn1cclxuXHJcbi5leGFtcGxlLWgyIHtcclxuICAgIG1hcmdpbjogMTBweDtcclxuICB9XHJcbiAgXHJcbiAgLmV4YW1wbGUtc2VjdGlvbiB7XHJcbiAgICBkaXNwbGF5OiBmbGV4O1xyXG4gICAgYWxpZ24tY29udGVudDogY2VudGVyO1xyXG4gICAgYWxpZ24taXRlbXM6IGNlbnRlcjtcclxuICAgIGhlaWdodDogNjBweDtcclxuICB9XHJcbiAgXHJcbiAgLmV4YW1wbGUtbWFyZ2luIHtcclxuICAgIG1hcmdpbjogMCAxMHB4O1xyXG4gIH1cclxuXHJcbiAgLm1hdC1jYXJkLWltYWdle1xyXG4gICAgd2lkdGg6MjAwcHg7XHJcbiAgICBoZWlnaHQ6MjAwcHg7XHJcbn0iXX0= */"

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







var AddReportComponent = /** @class */ (function () {
    function AddReportComponent(layoutComp, ledgerHelper, router, http) {
        this.layoutComp = layoutComp;
        this.ledgerHelper = ledgerHelper;
        this.router = router;
        this.http = http;
        this.reports = [];
        this.report = this.ledgerHelper.reports;
        this.dropdownList = [];
        this.selectedItems = [];
        this.selectedDoctorsID = [];
        this.dropdownSettings = {};
    }
    AddReportComponent.prototype.ngOnInit = function () {
        document.getElementById('i_pn').value = this.ledgerHelper.full_name;
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
        var id = this.ledgerHelper._id;
        var headers = new _angular_common_http__WEBPACK_IMPORTED_MODULE_1__["HttpHeaders"]({
            'Content-Type': 'application/json',
            'Authorization': this.ledgerHelper.token
        });
        for (var i = 0; i < this.selectedItems.length; i++) {
            this.selectedDoctorsID.push(this.selectedItems[i].item_id);
        }
        var report = {
            id: '' + (JSON.parse(this.ledgerHelper.reports).length + 1),
            title: document.getElementById('i_rt').value,
            patientName: this.ledgerHelper.full_name,
            fileName: this.filename,
            description: document.getElementById('i_description').value,
            content: this.fileBase64,
            doctors: this.selectedDoctorsID,
        };
        this.reports.push(report);
        var baseTransaction1 = {
            $tx: {
                $contract: 'report',
                $namespace: 'health',
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
        var txHandler = new _activeledger_sdk__WEBPACK_IMPORTED_MODULE_2__["TransactionHandler"]();
        txHandler
            .signTransaction(baseTransaction1, this.ledgerHelper.key)
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
                    _this.layoutComp.showSnackBar('Report Update Successfully!');
                    //update the report in pref
                    _this.reports = JSON.parse(_this.ledgerHelper.reports);
                    _this.reports.push(report);
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
        return this.http.post(this.ledgerHelper.addReportURL, body, { headers: header, observe: 'response' });
    };
    AddReportComponent = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"])({
            selector: 'app-report',
            template: __webpack_require__(/*! ./add-report.component.html */ "./src/app/layout/add-report-page/add-report.component.html"),
            styles: [__webpack_require__(/*! ./add-report.component.scss */ "./src/app/layout/add-report-page/add-report.component.scss")]
        }),
        __metadata("design:paramtypes", [_layout_component__WEBPACK_IMPORTED_MODULE_5__["LayoutComponent"], src_app_helper_ledgerhelper__WEBPACK_IMPORTED_MODULE_3__["LedgerHelper"], _angular_router__WEBPACK_IMPORTED_MODULE_4__["Router"],
            _angular_common_http__WEBPACK_IMPORTED_MODULE_1__["HttpClient"]])
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