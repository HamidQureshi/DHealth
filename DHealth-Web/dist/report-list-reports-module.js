(window["webpackJsonp"] = window["webpackJsonp"] || []).push([["report-list-reports-module"],{

/***/ "./node_modules/ngx-fab/fesm5/ngx-fab.js":
/*!***********************************************!*\
  !*** ./node_modules/ngx-fab/fesm5/ngx-fab.js ***!
  \***********************************************/
/*! exports provided: NgxFabComponent, NgxFabModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "NgxFabComponent", function() { return NgxFabComponent; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "NgxFabModule", function() { return NgxFabModule; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_common__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/common */ "./node_modules/@angular/common/fesm5/common.js");



/**
 * @fileoverview added by tsickle
 * @suppress {checkTypes,extraRequire,uselessCode} checked by tsc
 */
var NgxFabComponent = /** @class */ (function () {
    function NgxFabComponent(elementRef) {
        this.elementRef = elementRef;
        this.backgroundColor = 'crimson';
        this.fabSize = 'medium';
        this.iconClass = 'fa fa-plus';
        this.position = 'bottom-right';
        this.additionalX = 0;
        this.additionalY = 0;
    }
    /**
     * @return {?}
     */
    NgxFabComponent.prototype.background = /**
     * @return {?}
     */
    function () {
        return {
            'background-color': this.backgroundColor,
            'border-color': this.backgroundColor,
        };
    };
    /**
     * @return {?}
     */
    NgxFabComponent.prototype.icon = /**
     * @return {?}
     */
    function () {
        return this.iconClass + " " + (this.fabSize === 'large' ? 'fa-2x' : this.fabSize === 'medium' ? 'fa-lg' : '');
    };
    /**
     * @return {?}
     */
    NgxFabComponent.prototype.color = /**
     * @return {?}
     */
    function () {
        return this.iconColor || this.defaultIconColor();
    };
    /**
     * @return {?}
     */
    NgxFabComponent.prototype.size = /**
     * @return {?}
     */
    function () {
        return "fab-" + this.fabSize;
    };
    /**
     * @return {?}
     */
    NgxFabComponent.prototype.positionStyle = /**
     * @return {?}
     */
    function () {
        /** @type {?} */
        var parentBoundingRect = this.elementRef.nativeElement.parentElement.getBoundingClientRect();
        /** @type {?} */
        var fabWidth = this.fabSize === 'small' ? 40 : this.fabSize === 'medium' ? 60 : 100;
        /** @type {?} */
        var desiredOffsetX = (fabWidth / 3) + this.additionalX;
        /** @type {?} */
        var desiredOffsetY = (fabWidth / 3) + this.additionalY;
        /** @type {?} */
        var leftLeft = parentBoundingRect.left + desiredOffsetX + "px";
        /** @type {?} */
        var leftMid = parentBoundingRect.left + (parentBoundingRect.width / 2) - (fabWidth / 2) + this.additionalX + "px";
        /** @type {?} */
        var leftRight = parentBoundingRect.right - (fabWidth + desiredOffsetX) + "px";
        /** @type {?} */
        var topTop = parentBoundingRect.top + desiredOffsetY + "px";
        /** @type {?} */
        var topMid = parentBoundingRect.top + (parentBoundingRect.height / 2) - (fabWidth / 2) + this.additionalY + "px";
        /** @type {?} */
        var topBottom = parentBoundingRect.bottom - (fabWidth + desiredOffsetY) + "px";
        switch (this.position) {
            case 'top-left': return { top: topTop, left: leftLeft };
            case 'top-middle': return { top: topTop, left: leftMid };
            case 'top-right': return { top: topTop, left: leftRight };
            case 'middle-left': return { top: topMid, left: leftLeft };
            case 'middle-right': return { top: topMid, left: leftRight };
            case 'middle-middle': return { top: topMid, left: leftMid };
            case 'bottom-left': return { top: topBottom, left: leftLeft };
            case 'bottom-middle': return { top: topBottom, left: leftMid };
            case 'bottom-right': return { top: topBottom, left: leftRight };
        }
    };
    /**
     * @return {?}
     */
    NgxFabComponent.prototype.defaultIconColor = /**
     * @return {?}
     */
    function () {
        /** @type {?} */
        var colorHex = this.standardizeColor(this.backgroundColor);
        if (!colorHex || colorHex.length !== 7) {
            return 'white';
        }
        /** @type {?} */
        var sRGBs = [colorHex.substring(1, 3), colorHex.substring(3, 5), colorHex.substring(5, 7)].map(function (s) { return parseInt(s, 16); });
        return (sRGBs[0] * 0.299 + sRGBs[1] * 0.587 + sRGBs[2] * 0.114) > 186 ? 'black' : 'white';
    };
    /**
     * @param {?} colorName
     * @return {?}
     */
    NgxFabComponent.prototype.standardizeColor = /**
     * @param {?} colorName
     * @return {?}
     */
    function (colorName) {
        /** @type {?} */
        var ctx = document.createElement('canvas').getContext('2d');
        ctx.fillStyle = colorName;
        return ctx.fillStyle.toString();
    };
    NgxFabComponent.decorators = [
        { type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"], args: [{
                    selector: 'ngx-fab',
                    template: "<link rel=\"stylesheet\" href=\"https://use.fontawesome.com/releases/v5.2.0/css/all.css\" integrity=\"sha384-hWVjflwFxL6sNzntih27bfxkr27PmbbK/iSvJ+a4+0owXq79v+lsFkW54bOGbiDQ\" crossorigin=\"anonymous\">\n\n<div id=\"fab\" class=\"floating-action-button\" [ngClass]=\"size()\" [ngStyle]=\"positionStyle()\">\n  <button [ngClass]=\"size()\" [ngStyle]=\"background()\" role=\"button\">\n    <i [ngClass]=\"icon()\" [ngStyle]=\"{ color: color() }\" aria-hidden=\"true\"></i>\n  </button>\n</div>\n",
                    styles: [".floating-action-button{position:fixed;z-index:999}.floating-action-button button{border-radius:50%;border:none;outline:0;box-shadow:2px 2px 3px 0 rgba(0,0,0,.25)}.floating-action-button button:active{box-shadow:2px 2px 3px -1px rgba(0,0,0,.25);-webkit-transform:translate(1px,1px);transform:translate(1px,1px)}.fab-small{height:40px;width:40px}.fab-medium{height:60px;width:60px}.fab-large{height:100px;width:100px}"],
                },] },
    ];
    /** @nocollapse */
    NgxFabComponent.ctorParameters = function () { return [
        { type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["ElementRef"] }
    ]; };
    NgxFabComponent.propDecorators = {
        backgroundColor: [{ type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["Input"] }],
        fabSize: [{ type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["Input"] }],
        iconClass: [{ type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["Input"] }],
        iconColor: [{ type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["Input"] }],
        position: [{ type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["Input"] }],
        additionalX: [{ type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["Input"] }],
        additionalY: [{ type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["Input"] }]
    };
    return NgxFabComponent;
}());

/**
 * @fileoverview added by tsickle
 * @suppress {checkTypes,extraRequire,uselessCode} checked by tsc
 */
var NgxFabModule = /** @class */ (function () {
    function NgxFabModule() {
    }
    NgxFabModule.decorators = [
        { type: _angular_core__WEBPACK_IMPORTED_MODULE_0__["NgModule"], args: [{
                    imports: [
                        _angular_common__WEBPACK_IMPORTED_MODULE_1__["CommonModule"]
                    ],
                    declarations: [
                        NgxFabComponent,
                    ],
                    exports: [
                        NgxFabComponent,
                    ]
                },] },
    ];
    return NgxFabModule;
}());

/**
 * @fileoverview added by tsickle
 * @suppress {checkTypes,extraRequire,uselessCode} checked by tsc
 */

/**
 * @fileoverview added by tsickle
 * @suppress {checkTypes,extraRequire,uselessCode} checked by tsc
 */



//# sourceMappingURL=data:application/json;charset=utf-8;base64,eyJ2ZXJzaW9uIjozLCJmaWxlIjoibmd4LWZhYi5qcy5tYXAiLCJzb3VyY2VzIjpbIm5nOi8vbmd4LWZhYi9saWIvbmd4LWZhYi5jb21wb25lbnQudHMiLCJuZzovL25neC1mYWIvbGliL25neC1mYWIubW9kdWxlLnRzIl0sInNvdXJjZXNDb250ZW50IjpbImltcG9ydCB7IENvbXBvbmVudCwgRWxlbWVudFJlZiwgSW5wdXQgfSBmcm9tICdAYW5ndWxhci9jb3JlJztcblxuQENvbXBvbmVudCh7XG4gIHNlbGVjdG9yOiAnbmd4LWZhYicsXG4gIHRlbXBsYXRlOiBgPGxpbmsgcmVsPVwic3R5bGVzaGVldFwiIGhyZWY9XCJodHRwczovL3VzZS5mb250YXdlc29tZS5jb20vcmVsZWFzZXMvdjUuMi4wL2Nzcy9hbGwuY3NzXCIgaW50ZWdyaXR5PVwic2hhMzg0LWhXVmpmbHdGeEw2c056bnRpaDI3YmZ4a3IyN1BtYmJLL2lTdkorYTQrMG93WHE3OXYrbHNGa1c1NGJPR2JpRFFcIiBjcm9zc29yaWdpbj1cImFub255bW91c1wiPlxuXG48ZGl2IGlkPVwiZmFiXCIgY2xhc3M9XCJmbG9hdGluZy1hY3Rpb24tYnV0dG9uXCIgW25nQ2xhc3NdPVwic2l6ZSgpXCIgW25nU3R5bGVdPVwicG9zaXRpb25TdHlsZSgpXCI+XG4gIDxidXR0b24gW25nQ2xhc3NdPVwic2l6ZSgpXCIgW25nU3R5bGVdPVwiYmFja2dyb3VuZCgpXCIgcm9sZT1cImJ1dHRvblwiPlxuICAgIDxpIFtuZ0NsYXNzXT1cImljb24oKVwiIFtuZ1N0eWxlXT1cInsgY29sb3I6IGNvbG9yKCkgfVwiIGFyaWEtaGlkZGVuPVwidHJ1ZVwiPjwvaT5cbiAgPC9idXR0b24+XG48L2Rpdj5cbmAsXG4gIHN0eWxlczogW2AuZmxvYXRpbmctYWN0aW9uLWJ1dHRvbntwb3NpdGlvbjpmaXhlZDt6LWluZGV4Ojk5OX0uZmxvYXRpbmctYWN0aW9uLWJ1dHRvbiBidXR0b257Ym9yZGVyLXJhZGl1czo1MCU7Ym9yZGVyOm5vbmU7b3V0bGluZTowO2JveC1zaGFkb3c6MnB4IDJweCAzcHggMCByZ2JhKDAsMCwwLC4yNSl9LmZsb2F0aW5nLWFjdGlvbi1idXR0b24gYnV0dG9uOmFjdGl2ZXtib3gtc2hhZG93OjJweCAycHggM3B4IC0xcHggcmdiYSgwLDAsMCwuMjUpOy13ZWJraXQtdHJhbnNmb3JtOnRyYW5zbGF0ZSgxcHgsMXB4KTt0cmFuc2Zvcm06dHJhbnNsYXRlKDFweCwxcHgpfS5mYWItc21hbGx7aGVpZ2h0OjQwcHg7d2lkdGg6NDBweH0uZmFiLW1lZGl1bXtoZWlnaHQ6NjBweDt3aWR0aDo2MHB4fS5mYWItbGFyZ2V7aGVpZ2h0OjEwMHB4O3dpZHRoOjEwMHB4fWBdLFxufSlcbmV4cG9ydCBjbGFzcyBOZ3hGYWJDb21wb25lbnQge1xuXG4gIEBJbnB1dCgpXG4gIGJhY2tncm91bmRDb2xvcjogc3RyaW5nID0gJ2NyaW1zb24nO1xuXG4gIEBJbnB1dCgpXG4gIGZhYlNpemU6IEZhYlNpemUgPSAnbWVkaXVtJztcblxuICBASW5wdXQoKVxuICBpY29uQ2xhc3M6IHN0cmluZyA9ICdmYSBmYS1wbHVzJztcblxuICBASW5wdXQoKVxuICBpY29uQ29sb3I6IHN0cmluZztcblxuICBASW5wdXQoKVxuICBwb3NpdGlvbjogRmFiUG9zaXRpb24gPSAnYm90dG9tLXJpZ2h0JztcblxuICBASW5wdXQoKVxuICBhZGRpdGlvbmFsWDogbnVtYmVyID0gMDtcblxuICBASW5wdXQoKVxuICBhZGRpdGlvbmFsWTogbnVtYmVyID0gMDtcblxuICBjb25zdHJ1Y3Rvcihwcml2YXRlIGVsZW1lbnRSZWY6IEVsZW1lbnRSZWYpIHsgfVxuXG4gIGJhY2tncm91bmQoKTogT2JqZWN0IHtcbiAgICByZXR1cm4ge1xuICAgICAgJ2JhY2tncm91bmQtY29sb3InOiB0aGlzLmJhY2tncm91bmRDb2xvcixcbiAgICAgICdib3JkZXItY29sb3InOiB0aGlzLmJhY2tncm91bmRDb2xvcixcbiAgICB9O1xuICB9XG5cbiAgaWNvbigpOiBzdHJpbmcge1xuICAgIHJldHVybiBgJHt0aGlzLmljb25DbGFzc30gJHt0aGlzLmZhYlNpemUgPT09ICdsYXJnZScgPyAnZmEtMngnIDogdGhpcy5mYWJTaXplID09PSAnbWVkaXVtJyA/ICdmYS1sZycgOiAnJ31gO1xuICB9XG5cbiAgY29sb3IoKTogc3RyaW5nIHtcbiAgICByZXR1cm4gdGhpcy5pY29uQ29sb3IgfHwgdGhpcy5kZWZhdWx0SWNvbkNvbG9yKCk7XG4gIH1cblxuICBzaXplKCk6IHN0cmluZyB7XG4gICAgcmV0dXJuIGBmYWItJHt0aGlzLmZhYlNpemV9YDtcbiAgfVxuXG4gIHBvc2l0aW9uU3R5bGUoKTogT2JqZWN0IHtcbiAgICBjb25zdCBwYXJlbnRCb3VuZGluZ1JlY3QgPSB0aGlzLmVsZW1lbnRSZWYubmF0aXZlRWxlbWVudC5wYXJlbnRFbGVtZW50LmdldEJvdW5kaW5nQ2xpZW50UmVjdCgpO1xuICAgIGNvbnN0IGZhYldpZHRoID0gdGhpcy5mYWJTaXplID09PSAnc21hbGwnID8gNDAgOiB0aGlzLmZhYlNpemUgPT09ICdtZWRpdW0nID8gNjAgOiAxMDA7XG4gICAgY29uc3QgZGVzaXJlZE9mZnNldFggPSAoZmFiV2lkdGggLyAzKSArIHRoaXMuYWRkaXRpb25hbFg7XG4gICAgY29uc3QgZGVzaXJlZE9mZnNldFkgPSAoZmFiV2lkdGggLyAzKSArIHRoaXMuYWRkaXRpb25hbFk7XG5cbiAgICBjb25zdCBsZWZ0TGVmdCA9IGAke3BhcmVudEJvdW5kaW5nUmVjdC5sZWZ0ICsgZGVzaXJlZE9mZnNldFh9cHhgO1xuICAgIGNvbnN0IGxlZnRNaWQgPSBgJHtwYXJlbnRCb3VuZGluZ1JlY3QubGVmdCArIChwYXJlbnRCb3VuZGluZ1JlY3Qud2lkdGggLyAyKSAtIChmYWJXaWR0aCAvIDIpICsgdGhpcy5hZGRpdGlvbmFsWH1weGA7XG4gICAgY29uc3QgbGVmdFJpZ2h0ID0gYCR7cGFyZW50Qm91bmRpbmdSZWN0LnJpZ2h0IC0gKGZhYldpZHRoICsgZGVzaXJlZE9mZnNldFgpfXB4YDtcblxuICAgIGNvbnN0IHRvcFRvcCA9IGAke3BhcmVudEJvdW5kaW5nUmVjdC50b3AgKyBkZXNpcmVkT2Zmc2V0WX1weGA7XG4gICAgY29uc3QgdG9wTWlkID0gYCR7cGFyZW50Qm91bmRpbmdSZWN0LnRvcCArIChwYXJlbnRCb3VuZGluZ1JlY3QuaGVpZ2h0IC8gMikgLSAoZmFiV2lkdGggLyAyKSArIHRoaXMuYWRkaXRpb25hbFl9cHhgO1xuICAgIGNvbnN0IHRvcEJvdHRvbSA9IGAke3BhcmVudEJvdW5kaW5nUmVjdC5ib3R0b20gLSAoZmFiV2lkdGggKyBkZXNpcmVkT2Zmc2V0WSl9cHhgO1xuXG4gICAgc3dpdGNoICh0aGlzLnBvc2l0aW9uKSB7XG4gICAgICBjYXNlICd0b3AtbGVmdCc6IHJldHVybiB7IHRvcDogdG9wVG9wLCBsZWZ0OiBsZWZ0TGVmdCB9O1xuICAgICAgY2FzZSAndG9wLW1pZGRsZSc6IHJldHVybiB7IHRvcDogdG9wVG9wLCBsZWZ0OiBsZWZ0TWlkIH07XG4gICAgICBjYXNlICd0b3AtcmlnaHQnOiByZXR1cm4geyB0b3A6IHRvcFRvcCwgbGVmdDogbGVmdFJpZ2h0IH07XG4gICAgICBjYXNlICdtaWRkbGUtbGVmdCc6IHJldHVybiB7IHRvcDogdG9wTWlkLCBsZWZ0OiBsZWZ0TGVmdCB9O1xuICAgICAgY2FzZSAnbWlkZGxlLXJpZ2h0JzogcmV0dXJuIHsgdG9wOiB0b3BNaWQsIGxlZnQ6IGxlZnRSaWdodCB9O1xuICAgICAgY2FzZSAnbWlkZGxlLW1pZGRsZSc6IHJldHVybiB7IHRvcDogdG9wTWlkLCBsZWZ0OiBsZWZ0TWlkIH07XG4gICAgICBjYXNlICdib3R0b20tbGVmdCc6IHJldHVybiB7IHRvcDogdG9wQm90dG9tLCBsZWZ0OiBsZWZ0TGVmdCB9O1xuICAgICAgY2FzZSAnYm90dG9tLW1pZGRsZSc6IHJldHVybiB7IHRvcDogdG9wQm90dG9tLCBsZWZ0OiBsZWZ0TWlkIH07XG4gICAgICBjYXNlICdib3R0b20tcmlnaHQnOiByZXR1cm4geyB0b3A6IHRvcEJvdHRvbSwgbGVmdDogbGVmdFJpZ2h0IH07XG4gICAgfVxuICB9XG5cbiAgLy8gaW5zcGlyZWQgYnkgdGhpcyBTTyBhbnN3ZXIgaHR0cHM6Ly9zdGFja292ZXJmbG93LmNvbS9hLzM5NDMwMjNcbiAgcHJpdmF0ZSBkZWZhdWx0SWNvbkNvbG9yKCk6IHN0cmluZyB7XG4gICAgY29uc3QgY29sb3JIZXggPSB0aGlzLnN0YW5kYXJkaXplQ29sb3IodGhpcy5iYWNrZ3JvdW5kQ29sb3IpO1xuXG4gICAgaWYgKCFjb2xvckhleCB8fCBjb2xvckhleC5sZW5ndGggIT09IDcpIHtcbiAgICAgIHJldHVybiAnd2hpdGUnO1xuICAgIH1cblxuICAgIGNvbnN0IHNSR0JzID0gWyBjb2xvckhleC5zdWJzdHJpbmcoMSwgMyksIGNvbG9ySGV4LnN1YnN0cmluZygzLCA1KSwgY29sb3JIZXguc3Vic3RyaW5nKDUsIDcpIF0ubWFwKHMgPT4gcGFyc2VJbnQocywgMTYpKTtcbiAgICByZXR1cm4gKHNSR0JzWzBdICogMC4yOTkgKyBzUkdCc1sxXSAqIDAuNTg3ICsgc1JHQnNbMl0gKiAwLjExNCkgPiAxODYgPyAnYmxhY2snIDogJ3doaXRlJztcbiAgfVxuXG4gIC8vIGluc3BpcmVkIGJ5IEpheUIncyBjb21tZW50IG9uIHRoaXMgU08gYW5zd2VyIGh0dHBzOi8vc3RhY2tvdmVyZmxvdy5jb20vYS8yNDM5MDkxMFxuICBwcml2YXRlIHN0YW5kYXJkaXplQ29sb3IoY29sb3JOYW1lKTogc3RyaW5nIHtcbiAgICBjb25zdCBjdHggPSBkb2N1bWVudC5jcmVhdGVFbGVtZW50KCdjYW52YXMnKS5nZXRDb250ZXh0KCcyZCcpO1xuICAgIGN0eC5maWxsU3R5bGUgPSBjb2xvck5hbWU7XG4gICAgcmV0dXJuIGN0eC5maWxsU3R5bGUudG9TdHJpbmcoKTtcbiAgfVxufVxuXG5leHBvcnQgdHlwZSBGYWJQb3NpdGlvbiA9ICd0b3AtbGVmdCcgfCAndG9wLW1pZGRsZScgfCAndG9wLXJpZ2h0JyB8ICdtaWRkbGUtbGVmdCcgfCAnbWlkZGxlLW1pZGRsZScgfCAnbWlkZGxlLXJpZ2h0JyB8ICdib3R0b20tbGVmdCcgfCAnYm90dG9tLW1pZGRsZScgfCAnYm90dG9tLXJpZ2h0JztcblxuZXhwb3J0IHR5cGUgRmFiU2l6ZSA9ICdzbWFsbCcgfCAnbWVkaXVtJyB8ICdsYXJnZSc7XG4iLCJpbXBvcnQgeyBOZ01vZHVsZSB9IGZyb20gJ0Bhbmd1bGFyL2NvcmUnO1xuaW1wb3J0IHsgTmd4RmFiQ29tcG9uZW50IH0gZnJvbSAnLi9uZ3gtZmFiLmNvbXBvbmVudCc7XG5pbXBvcnQgeyBDb21tb25Nb2R1bGUgfSBmcm9tICdAYW5ndWxhci9jb21tb24nO1xuXG5ATmdNb2R1bGUoe1xuICBpbXBvcnRzOiBbXG4gICAgQ29tbW9uTW9kdWxlXG4gIF0sXG4gIGRlY2xhcmF0aW9uczogW1xuICAgIE5neEZhYkNvbXBvbmVudCxcbiAgXSxcbiAgZXhwb3J0czogW1xuICAgIE5neEZhYkNvbXBvbmVudCxcbiAgXVxufSlcbmV4cG9ydCBjbGFzcyBOZ3hGYWJNb2R1bGUgeyB9XG4iXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6Ijs7Ozs7OztBQUFBO0lBcUNFLHlCQUFvQixVQUFzQjtRQUF0QixlQUFVLEdBQVYsVUFBVSxDQUFZOytCQXBCaEIsU0FBUzt1QkFHaEIsUUFBUTt5QkFHUCxZQUFZO3dCQU1SLGNBQWM7MkJBR2hCLENBQUM7MkJBR0QsQ0FBQztLQUV3Qjs7OztJQUUvQyxvQ0FBVTs7O0lBQVY7UUFDRSxPQUFPO1lBQ0wsa0JBQWtCLEVBQUUsSUFBSSxDQUFDLGVBQWU7WUFDeEMsY0FBYyxFQUFFLElBQUksQ0FBQyxlQUFlO1NBQ3JDLENBQUM7S0FDSDs7OztJQUVELDhCQUFJOzs7SUFBSjtRQUNFLE9BQVUsSUFBSSxDQUFDLFNBQVMsVUFBSSxJQUFJLENBQUMsT0FBTyxLQUFLLE9BQU8sR0FBRyxPQUFPLEdBQUcsSUFBSSxDQUFDLE9BQU8sS0FBSyxRQUFRLEdBQUcsT0FBTyxHQUFHLEVBQUUsQ0FBRSxDQUFDO0tBQzdHOzs7O0lBRUQsK0JBQUs7OztJQUFMO1FBQ0UsT0FBTyxJQUFJLENBQUMsU0FB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

/***/ }),

/***/ "./src/app/layout/report-list/reports-routing.module.ts":
/*!**************************************************************!*\
  !*** ./src/app/layout/report-list/reports-routing.module.ts ***!
  \**************************************************************/
/*! exports provided: ReportsRoutingModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "ReportsRoutingModule", function() { return ReportsRoutingModule; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
/* harmony import */ var _reports_component__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ./reports.component */ "./src/app/layout/report-list/reports.component.ts");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};



var routes = [
    {
        path: '',
        component: _reports_component__WEBPACK_IMPORTED_MODULE_2__["ReportsComponent"]
    }
];
var ReportsRoutingModule = /** @class */ (function () {
    function ReportsRoutingModule() {
    }
    ReportsRoutingModule = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["NgModule"])({
            imports: [_angular_router__WEBPACK_IMPORTED_MODULE_1__["RouterModule"].forChild(routes)],
            exports: [_angular_router__WEBPACK_IMPORTED_MODULE_1__["RouterModule"]]
        })
    ], ReportsRoutingModule);
    return ReportsRoutingModule;
}());



/***/ }),

/***/ "./src/app/layout/report-list/reports.component.html":
/*!***********************************************************!*\
  !*** ./src/app/layout/report-list/reports.component.html ***!
  \***********************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<mat-form-field>\r\n    <input matInput (keyup)=\"applyFilter($event.target.value)\" placeholder=\"Filter\">\r\n</mat-form-field>\r\n\r\n<div class=\"mat-elevation-z8\">\r\n    <table mat-table [dataSource]=\"dataSource\" matSort>\r\n\r\n        <!-- ID Column -->\r\n        <ng-container matColumnDef=\"id\">\r\n            <th mat-header-cell *matHeaderCellDef mat-sort-header> ID </th>\r\n            <td mat-cell *matCellDef=\"let row\"> {{row.id}} </td>\r\n        </ng-container>\r\n\r\n        <!-- Progress Column -->\r\n        <ng-container matColumnDef=\"title\">\r\n            <th mat-header-cell *matHeaderCellDef mat-sort-header> Report Title </th>\r\n            <td mat-cell *matCellDef=\"let row\"> {{row.title}} </td>\r\n        </ng-container>\r\n\r\n        <!-- Name Column -->\r\n        <ng-container matColumnDef=\"name\">\r\n            <th mat-header-cell *matHeaderCellDef mat-sort-header> Patient Name </th>\r\n            <td mat-cell *matCellDef=\"let row\"> {{row.patientName}} </td>\r\n        </ng-container>\r\n\r\n        <!-- Color Column -->\r\n        <ng-container matColumnDef=\"doc_name\">\r\n            <th mat-header-cell *matHeaderCellDef mat-sort-header> Document Name </th>\r\n            <td mat-cell *matCellDef=\"let row\" [style.color]=\"row.color\"> {{row.fileName}} </td>\r\n        </ng-container>\r\n\r\n        <tr mat-header-row *matHeaderRowDef=\"displayedColumns\"></tr>\r\n        <tr mat-row *matRowDef=\"let row; columns: displayedColumns;\" (click)=\"getRecord(row)\"></tr>\r\n        \r\n    </table>\r\n\r\n    <mat-paginator [pageSize]=\"10\" [pageSizeOptions]=\"[5, 10, 25, 100]\"></mat-paginator>\r\n\r\n</div>\r\n\r\n<div>\r\n        <ngx-fab [backgroundColor]=\"'#3f51b5'\"[fabSize]=\"'medium'\" [position]=\"'top-right'\" [iconClass]=\"'fas fa-plus'\" [iconColor]=\"'#ffffff'\" (click)=\"addReport()\">\r\n            Add report\r\n        </ngx-fab>\r\n</div>\r\n"

/***/ }),

/***/ "./src/app/layout/report-list/reports.component.scss":
/*!***********************************************************!*\
  !*** ./src/app/layout/report-list/reports.component.scss ***!
  \***********************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "table {\n  width: 100%; }\n\n.mat-form-field {\n  font-size: 14px;\n  width: 100%; }\n\ntd,\nth {\n  width: 25%; }\n\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbInNyYy9hcHAvbGF5b3V0L3JlcG9ydC1saXN0L0M6XFxVc2Vyc1xcSGFtaWRcXERlc2t0b3BcXEFMU0RLXFxleGFtcGxlc1xcc2ItYWRtaW4tbWF0ZXJpYWwvc3JjXFxhcHBcXGxheW91dFxccmVwb3J0LWxpc3RcXHJlcG9ydHMuY29tcG9uZW50LnNjc3MiXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IkFBQUE7RUFDSSxXQUFXLEVBQUE7O0FBR2Y7RUFDSSxlQUFlO0VBQ2YsV0FBVyxFQUFBOztBQUdmOztFQUVJLFVBQVUsRUFBQSIsImZpbGUiOiJzcmMvYXBwL2xheW91dC9yZXBvcnQtbGlzdC9yZXBvcnRzLmNvbXBvbmVudC5zY3NzIiwic291cmNlc0NvbnRlbnQiOlsidGFibGUge1xyXG4gICAgd2lkdGg6IDEwMCU7XHJcbn1cclxuXHJcbi5tYXQtZm9ybS1maWVsZCB7XHJcbiAgICBmb250LXNpemU6IDE0cHg7XHJcbiAgICB3aWR0aDogMTAwJTtcclxufVxyXG5cclxudGQsXHJcbnRoIHtcclxuICAgIHdpZHRoOiAyNSU7XHJcbn1cclxuIl19 */"

/***/ }),

/***/ "./src/app/layout/report-list/reports.component.ts":
/*!*********************************************************!*\
  !*** ./src/app/layout/report-list/reports.component.ts ***!
  \*********************************************************/
/*! exports provided: ReportsComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "ReportsComponent", function() { return ReportsComponent; });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_material__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/material */ "./node_modules/@angular/material/esm5/material.es5.js");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
/* harmony import */ var src_app_helper_ledgerhelper__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! src/app/helper/ledgerhelper */ "./src/app/helper/ledgerhelper.ts");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (undefined && undefined.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};





var ReportsComponent = /** @class */ (function () {
    function ReportsComponent(ledgerHelper, router, snackBar) {
        this.ledgerHelper = ledgerHelper;
        this.router = router;
        this.snackBar = snackBar;
        this.displayedColumns = ['id', 'name', 'title', 'doc_name'];
        this.message = 'Snack Bar opened.';
        this.actionButtonLabel = 'Close';
        this.action = true;
        this.setAutoHide = true;
        this.autoHide = 2000;
        this.horizontalPosition = 'center';
        this.verticalPosition = 'bottom';
        this.addExtraClass = false;
        this.reports = [];
    }
    ReportsComponent.prototype.ngOnInit = function () {
        console.log('reports component--- ' + this.ledgerHelper.reports);
        this.reports = JSON.parse(this.ledgerHelper.reports);
        this.dataSource = new _angular_material__WEBPACK_IMPORTED_MODULE_1__["MatTableDataSource"](this.reports);
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;
    };
    ReportsComponent.prototype.addReport = function () {
        this.router.navigate(['/add-report'], { replaceUrl: true });
    };
    ReportsComponent.prototype.getRecord = function (row) {
        console.log(row);
        src_app_helper_ledgerhelper__WEBPACK_IMPORTED_MODULE_3__["LedgerHelper"].report = JSON.stringify(row);
        // localStorage.setItem('report', '' + JSON.stringify(row));
        this.router.navigate(['/report']);
    };
    ReportsComponent.prototype.applyFilter = function (filterValue) {
        filterValue = filterValue.trim();
        filterValue = filterValue.toLowerCase();
        this.dataSource.filter = filterValue;
        if (this.dataSource.paginator) {
            this.dataSource.paginator.firstPage();
        }
    };
    ReportsComponent.prototype.open = function () {
        var config = new _angular_material__WEBPACK_IMPORTED_MODULE_1__["MatSnackBarConfig"]();
        config.verticalPosition = this.verticalPosition;
        config.horizontalPosition = this.horizontalPosition;
        config.duration = this.setAutoHide ? this.autoHide : 0;
        this.snackBar.open(this.message, this.action ? this.actionButtonLabel : undefined, config);
    };
    __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["ViewChild"])(_angular_material__WEBPACK_IMPORTED_MODULE_1__["MatPaginator"]),
        __metadata("design:type", _angular_material__WEBPACK_IMPORTED_MODULE_1__["MatPaginator"])
    ], ReportsComponent.prototype, "paginator", void 0);
    __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["ViewChild"])(_angular_material__WEBPACK_IMPORTED_MODULE_1__["MatSort"]),
        __metadata("design:type", _angular_material__WEBPACK_IMPORTED_MODULE_1__["MatSort"])
    ], ReportsComponent.prototype, "sort", void 0);
    ReportsComponent = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_0__["Component"])({
            selector: 'app-reports',
            template: __webpack_require__(/*! ./reports.component.html */ "./src/app/layout/report-list/reports.component.html"),
            styles: [__webpack_require__(/*! ./reports.component.scss */ "./src/app/layout/report-list/reports.component.scss")]
        }),
        __metadata("design:paramtypes", [src_app_helper_ledgerhelper__WEBPACK_IMPORTED_MODULE_3__["LedgerHelper"], _angular_router__WEBPACK_IMPORTED_MODULE_2__["Router"],
            _angular_material__WEBPACK_IMPORTED_MODULE_1__["MatSnackBar"]])
    ], ReportsComponent);
    return ReportsComponent;
}());



/***/ }),

/***/ "./src/app/layout/report-list/reports.module.ts":
/*!******************************************************!*\
  !*** ./src/app/layout/report-list/reports.module.ts ***!
  \******************************************************/
/*! exports provided: ReportsModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "ReportsModule", function() { return ReportsModule; });
/* harmony import */ var _angular_common__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/common */ "./node_modules/@angular/common/fesm5/common.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_material__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/material */ "./node_modules/@angular/material/esm5/material.es5.js");
/* harmony import */ var _reports_routing_module__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./reports-routing.module */ "./src/app/layout/report-list/reports-routing.module.ts");
/* harmony import */ var _reports_component__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ./reports.component */ "./src/app/layout/report-list/reports.component.ts");
/* harmony import */ var ngx_fab__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! ngx-fab */ "./node_modules/ngx-fab/fesm5/ngx-fab.js");
var __decorate = (undefined && undefined.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};









var ReportsModule = /** @class */ (function () {
    function ReportsModule() {
    }
    ReportsModule = __decorate([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["NgModule"])({
            imports: [
                _angular_common__WEBPACK_IMPORTED_MODULE_0__["CommonModule"],
                _reports_routing_module__WEBPACK_IMPORTED_MODULE_3__["ReportsRoutingModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_2__["MatTableModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_2__["MatFormFieldModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_2__["MatPaginatorModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_2__["MatInputModule"],
                _angular_material__WEBPACK_IMPORTED_MODULE_2__["MatSnackBarModule"],
                ngx_fab__WEBPACK_IMPORTED_MODULE_5__["NgxFabModule"]
            ],
            declarations: [_reports_component__WEBPACK_IMPORTED_MODULE_4__["ReportsComponent"]]
        })
    ], ReportsModule);
    return ReportsModule;
}());



/***/ })

}]);
//# sourceMappingURL=report-list-reports-module.js.map