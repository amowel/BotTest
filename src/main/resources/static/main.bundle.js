webpackJsonp([1,4],{

/***/ 100:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return environment; });
// The file contents for the current environment will overwrite these during build.
// The build system defaults to the dev environment which uses `environment.ts`, but if you do
// `ng build --env=prod` then `environment.prod.ts` will be used instead.
// The list of which env maps to which file can be found in `..angular-cli.json`.
// The file contents for the current environment will overwrite these during build.
var environment = {
    production: false
};
//# sourceMappingURL=environment.js.map

/***/ }),

/***/ 154:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(15)();
// imports


// module
exports.push([module.i, ".soc-el {\n    border: 5px solid #40515e;\n    border-radius: 50px;\n    display: inline-block;\n    width: 200px;\n    height: 200px;\n    margin-right: 30px;\n    padding: 30px;\n    cursor: pointer;\n}\n.soc-el img {\n    width: 100%;\n}\n.logined {\n    background-color: #13CE66;\n}", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),

/***/ 155:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(15)();
// imports


// module
exports.push([module.i, "#main-screen {\n    float: left;\n    width: calc(100% - 350px);\n    text-align: left;\n    padding-left: 40px;\n}", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),

/***/ 156:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(15)();
// imports


// module
exports.push([module.i, "form {\n    width: 90%;\n    max-width: 800px;\n}\ntextarea {\n    height: 300px;\n    width: 100%;\n    padding: 10px;\n    border-radius: 10px;\n    box-sizing: border-box;\n}\n.buttons-group {\n    width: 100%;\n    margin-top: 15px;\n    margin-bottom: 30px;\n}\n.buttons-group .btn-def {\n    float: right;\n}\n.buttons-group .btn-def:first-child {\n    float: left;\n}\n#socials-switch {\n    margin-top: 40px;\n}\n#socials-switch img {\n    width: 70px;\n    margin-right: 50px;\n    float: left;\n    margin-top: -15px;\n}\n.switch-el {\n    margin-top: 70px;\n}\n/*toggle switch*/\n.switch {\n    position: relative;\n    display: inline-block;\n    width: 60px;\n    height: 34px;\n}\n\n/* Hide default HTML checkbox */\n.switch input {display:none;}\n\n/* The slider */\n.slider {\n    position: absolute;\n    cursor: pointer;\n    top: 0;\n    left: 0;\n    right: 0;\n    bottom: 0;\n    background-color: #ccc;\n    -webkit-transition: .4s;\n    transition: .4s;\n}\n\n.slider:before {\n    position: absolute;\n    content: \"\";\n    height: 26px;\n    width: 26px;\n    left: 4px;\n    bottom: 4px;\n    background-color: white;\n    -webkit-transition: .4s;\n    transition: .4s;\n}\n\ninput:checked + .slider {\n    background-color: #2196F3;\n}\n\ninput:focus + .slider {\n    box-shadow: 0 0 1px #2196F3;\n}\n\ninput:checked + .slider:before {\n    -webkit-transform: translateX(26px);\n    transform: translateX(26px);\n}\n\n/* Rounded sliders */\n.slider.round {\n    border-radius: 34px;\n}\n\n.slider.round:before {\n    border-radius: 50%;\n}", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),

/***/ 157:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(15)();
// imports


// module
exports.push([module.i, "#left-menu {\n    background-color: #D9DDE3;\n    float: left;\n    width: 16%;\n    min-width: 230px;\n    height: 100vh;\n    position: relative;\n}\nul {\n    margin: 0;\n    padding: 0;\n    list-style: none;\n}\nul hr {\n    margin-top: 30px;\n}\n.btn-menu {\n    width: 150px;\n    border-radius: 20px;\n    margin-top: 30px;\n}\n.btn-logout {\n    background-color: #553535;\n    width: 150px;\n    margin-top: 10px;\n}\n#menu-bottom {\n    position: absolute;\n    bottom: 20px;\n    width: 100%;\n}\n#user-info {\n    padding-top: 20px;\n}\n#user-info img {\n    width: 200px;\n    border-radius: 100px;\n}", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),

/***/ 159:
/***/ (function(module, exports) {

module.exports = "<div id=\"add-accounts\">\n  <h1>{{title}}</h1>\n  <h3>{{subtitle}}</h3>\n\n  <div id=\"socials-set\">\n    <div class=\"soc-el\" *ngFor = \"let social of socials\" (click) = \"socialClick(social)\" [ngClass]=\"{ 'logined': social.logInStatus }\">\n      <img src=\"{{social.imgSrc}}\" alt=\"\">\n    </div>\n  </div>\n</div>"

/***/ }),

/***/ 160:
/***/ (function(module, exports) {

module.exports = "<left-menu></left-menu>\n<div id=\"main-screen\">\n    <router-outlet></router-outlet>\n</div>\n"

/***/ }),

/***/ 161:
/***/ (function(module, exports) {

module.exports = "<div id=\"create-post\">\n  <h1>{{title}}</h1>\n  <h3>{{subtitle}}</h3>\n\n  <form action=\"\">\n    <textarea name=\"message\" placeholder=\"What's new?\"></textarea>\n    <div class=\"buttons-group\">\n      <a href=\"#\" class=\"btn-def\">Attach</a>\n      <a href=\"#\" class=\"btn-def\">Send</a>\n      <div class=\"clr\"></div>\n    </div>\n\n    <span>Choose social networks you want to post</span>\n    <div id=\"socials-switch\">\n      <div class=\"switch-el\">\n        <img src=\"/assets/create-post/fb.png\" alt=\"\">\n\n        <label class=\"switch\">\n          <input type=\"checkbox\">\n          <div class=\"slider round\"></div>\n        </label>\n      </div>\n\n      <div class=\"switch-el\">\n        <img src=\"/assets/create-post/vk.png\" alt=\"\">\n\n        <label class=\"switch\">\n          <input type=\"checkbox\">\n          <div class=\"slider round\"></div>\n        </label>\n      </div>\n    </div>\n  </form>\n</div>\n"

/***/ }),

/***/ 162:
/***/ (function(module, exports) {

module.exports = "<div id=\"left-menu\">\n  <div id=\"user-info\">\n    <img src=\"{{ userInfo.imgSource }}\" alt=\"\">\n    <h3>{{ userInfo.name }}</h3>\n  </div>\n  <hr />\n  <ul>\n    <li>\n      <a routerLink=\"messages\" class=\"btn-def btn-menu\">Messages</a>\n    </li>\n    <li>\n      <a routerLink=\"new-post\" class=\"btn-def btn-menu\">Create a post</a>\n    </li>\n    <li>\n      <a routerLink=\"old-post\" class=\"btn-def btn-menu\">View old posts</a>\n    </li>\n    <li>\n      <hr />\n      <a routerLink=\"add-accounts\" class=\"btn-def btn-menu\">Add accounts</a>\n    </li>\n  </ul>\n  <div id=\"menu-bottom\">\n  <hr />\n  <a href=\"#\" class=\"btn-def btn-logout\">Logout</a>\n  </div>\n</div>"

/***/ }),

/***/ 193:
/***/ (function(module, exports, __webpack_require__) {

module.exports = __webpack_require__(84);


/***/ }),

/***/ 33:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(3);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__userInfo__ = __webpack_require__(99);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__socials__ = __webpack_require__(98);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return CommonService; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};



var CommonService = (function () {
    function CommonService() {
        this.userInfo = __WEBPACK_IMPORTED_MODULE_1__userInfo__["a" /* userInfo */];
        this.socials = __WEBPACK_IMPORTED_MODULE_2__socials__["a" /* socials */];
    }
    CommonService.prototype.getUserInfo = function () {
        return this.userInfo;
    };
    CommonService.prototype.getSocials = function () {
        return this.socials;
    };
    return CommonService;
}());
CommonService = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["e" /* Injectable */])(),
    __metadata("design:paramtypes", [])
], CommonService);

//# sourceMappingURL=common.service.js.map

/***/ }),

/***/ 83:
/***/ (function(module, exports) {

function webpackEmptyContext(req) {
	throw new Error("Cannot find module '" + req + "'.");
}
webpackEmptyContext.keys = function() { return []; };
webpackEmptyContext.resolve = webpackEmptyContext;
module.exports = webpackEmptyContext;
webpackEmptyContext.id = 83;


/***/ }),

/***/ 84:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(3);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_platform_browser_dynamic__ = __webpack_require__(89);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__app_app_module__ = __webpack_require__(93);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__environments_environment__ = __webpack_require__(100);




if (__WEBPACK_IMPORTED_MODULE_3__environments_environment__["a" /* environment */].production) {
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["a" /* enableProdMode */])();
}
__webpack_require__.i(__WEBPACK_IMPORTED_MODULE_1__angular_platform_browser_dynamic__["a" /* platformBrowserDynamic */])().bootstrapModule(__WEBPACK_IMPORTED_MODULE_2__app_app_module__["a" /* AppModule */]);
//# sourceMappingURL=main.js.map

/***/ }),

/***/ 91:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(3);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__shared_common_service__ = __webpack_require__(33);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__angular_http__ = __webpack_require__(58);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return AddAccountsComponent; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};



var AddAccountsComponent = (function () {
    function AddAccountsComponent(service, http) {
        this.service = service;
        this.http = http;
        this.title = "Add accounts";
        this.subtitle = "The more accounts you add, the more comfortable it will be for you";
    }
    AddAccountsComponent.prototype.ngOnInit = function () {
        this.socials = this.service.getSocials();
    };
    AddAccountsComponent.prototype.socialClick = function (social) {
        window.location.href = "https://oauth.vk.com/authorize?client_id=5901447&scope=ads,audio,docs,email,friends,groups,pages,wall,notes,notifications,offline,notify,stats,status&redirect_uri=http://162.243.194.41/generatecode&display=page&response_type=code&v=5.62";
    };
    return AddAccountsComponent;
}());
AddAccountsComponent = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["_3" /* Component */])({
        selector: 'app-add-accounts',
        template: __webpack_require__(159),
        styles: [__webpack_require__(154)]
    }),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_1__shared_common_service__["a" /* CommonService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1__shared_common_service__["a" /* CommonService */]) === "function" && _a || Object, typeof (_b = typeof __WEBPACK_IMPORTED_MODULE_2__angular_http__["b" /* Http */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_2__angular_http__["b" /* Http */]) === "function" && _b || Object])
], AddAccountsComponent);

var _a, _b;
//# sourceMappingURL=add-accounts.component.js.map

/***/ }),

/***/ 92:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(3);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return AppComponent; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};

var AppComponent = (function () {
    function AppComponent() {
        this.title = 'app works!';
    }
    return AppComponent;
}());
AppComponent = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["_3" /* Component */])({
        selector: 'app-root',
        template: __webpack_require__(160),
        styles: [__webpack_require__(155)]
    })
], AppComponent);

//# sourceMappingURL=app.component.js.map

/***/ }),

/***/ 93:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_platform_browser__ = __webpack_require__(16);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_core__ = __webpack_require__(3);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__angular_forms__ = __webpack_require__(88);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__angular_http__ = __webpack_require__(58);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__angular_router__ = __webpack_require__(90);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5__angular_common__ = __webpack_require__(23);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_6__shared_common_service__ = __webpack_require__(33);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_7__app_component__ = __webpack_require__(92);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_8__left_menu_left_menu_component__ = __webpack_require__(95);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_9__create_post_create_post_component__ = __webpack_require__(94);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_10__add_accounts_add_accounts_component__ = __webpack_require__(91);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return AppModule; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};











var router = [
    { path: '', redirectTo: 'new-post', pathMatch: 'full' },
    { path: 'new-post', component: __WEBPACK_IMPORTED_MODULE_9__create_post_create_post_component__["a" /* CreatePostComponent */] },
    { path: 'add-accounts', component: __WEBPACK_IMPORTED_MODULE_10__add_accounts_add_accounts_component__["a" /* AddAccountsComponent */] }
];
var routes = __WEBPACK_IMPORTED_MODULE_4__angular_router__["a" /* RouterModule */].forRoot(router);
var AppModule = (function () {
    function AppModule() {
    }
    return AppModule;
}());
AppModule = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_1__angular_core__["b" /* NgModule */])({
        declarations: [
            __WEBPACK_IMPORTED_MODULE_7__app_component__["a" /* AppComponent */],
            __WEBPACK_IMPORTED_MODULE_8__left_menu_left_menu_component__["a" /* LeftMenuComponent */],
            __WEBPACK_IMPORTED_MODULE_9__create_post_create_post_component__["a" /* CreatePostComponent */],
            __WEBPACK_IMPORTED_MODULE_10__add_accounts_add_accounts_component__["a" /* AddAccountsComponent */]
        ],
        imports: [
            __WEBPACK_IMPORTED_MODULE_0__angular_platform_browser__["a" /* BrowserModule */],
            __WEBPACK_IMPORTED_MODULE_2__angular_forms__["a" /* FormsModule */],
            __WEBPACK_IMPORTED_MODULE_3__angular_http__["a" /* HttpModule */],
            routes
        ],
        providers: [__WEBPACK_IMPORTED_MODULE_6__shared_common_service__["a" /* CommonService */], { provide: __WEBPACK_IMPORTED_MODULE_5__angular_common__["a" /* APP_BASE_HREF */], useValue: '/' }],
        bootstrap: [__WEBPACK_IMPORTED_MODULE_7__app_component__["a" /* AppComponent */]]
    })
], AppModule);

//# sourceMappingURL=app.module.js.map

/***/ }),

/***/ 94:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(3);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return CreatePostComponent; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};

var CreatePostComponent = (function () {
    function CreatePostComponent() {
        this.title = "Create a post";
        this.subtitle = "Create one post and send it to defferent socials network";
    }
    CreatePostComponent.prototype.ngOnInit = function () {
    };
    return CreatePostComponent;
}());
CreatePostComponent = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["_3" /* Component */])({
        selector: 'create-post',
        template: __webpack_require__(161),
        styles: [__webpack_require__(156)]
    }),
    __metadata("design:paramtypes", [])
], CreatePostComponent);

//# sourceMappingURL=create-post.component.js.map

/***/ }),

/***/ 95:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__(3);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__shared_common_service__ = __webpack_require__(33);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return LeftMenuComponent; });
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};


var LeftMenuComponent = (function () {
    function LeftMenuComponent(commonService) {
        this.commonService = commonService;
    }
    LeftMenuComponent.prototype.ngOnInit = function () {
        this.userInfo = this.commonService.getUserInfo();
    };
    return LeftMenuComponent;
}());
LeftMenuComponent = __decorate([
    __webpack_require__.i(__WEBPACK_IMPORTED_MODULE_0__angular_core__["_3" /* Component */])({
        selector: 'left-menu',
        template: __webpack_require__(162),
        styles: [__webpack_require__(157)]
    }),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_1__shared_common_service__["a" /* CommonService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1__shared_common_service__["a" /* CommonService */]) === "function" && _a || Object])
], LeftMenuComponent);

var _a;
//# sourceMappingURL=left-menu.component.js.map

/***/ }),

/***/ 96:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return Social; });
var Social = (function () {
    function Social(id, name, src, logInStatus) {
        if (logInStatus === void 0) { logInStatus = false; }
        this.id = id;
        this.name = name;
        this.imgSrc = src;
        this.logInStatus = logInStatus;
    }
    return Social;
}());

//# sourceMappingURL=CSocial.js.map

/***/ }),

/***/ 97:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return UserInfo; });
var UserInfo = (function () {
    function UserInfo(name, src) {
        this.name = name;
        this.imgSource = src;
    }
    return UserInfo;
}());

//# sourceMappingURL=CUserInfo.js.map

/***/ }),

/***/ 98:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__CSocial__ = __webpack_require__(96);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return socials; });

var socials = [
    new __WEBPACK_IMPORTED_MODULE_0__CSocial__["a" /* Social */](0, "vk", "/assets/add-accounts/vk.png"),
    new __WEBPACK_IMPORTED_MODULE_0__CSocial__["a" /* Social */](1, "instagram", "/assets/add-accounts/insta.png")
];
//# sourceMappingURL=socials.js.map

/***/ }),

/***/ 99:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__CUserInfo__ = __webpack_require__(97);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return userInfo; });

var userInfo = new __WEBPACK_IMPORTED_MODULE_0__CUserInfo__["a" /* UserInfo */]("Oleksiy Sadliak", "/assets/img.png");
//# sourceMappingURL=userInfo.js.map

/***/ })

},[193]);
//# sourceMappingURL=main.bundle.js.map