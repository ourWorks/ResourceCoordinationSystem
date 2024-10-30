webpackJsonp([25],{0:function(e,n){},"0PTi":function(e,n){},"1lxJ":function(e,n){},FsGg:function(e,n){},IcnI:function(e,n,t){"use strict";var o=t("ih0I"),a=t("2/01"),l=t("K5T/"),r=t.n(l),i=t("Y0bB"),c=t.n(i),u=t("vLgD");var s=r()([""],[""]),m={state:{token:localStorage.getItem("token"),name:"",roles:[]},mutations:{SET_TOKEN:function(e,n){e.token=n},SET_NAME:function(e,n){e.name=n}},actions:{Login:function(e,n){e.commit;console.log("触发patch，进入名为Login的Action");var t=n.phone.trim();return new c.a(function(e,o){(function(e,n){return Object(u.a)({url:"/login",method:"post",data:{phone:e,password:n}})})(t,n.password).then(function(n){console.log("进入名为Login的Action，进入发送login的post请求后的response"),console.log("response是：",n),console.log("response.data是:",n.data),console.log("msg是：",n.msg),console.log("response.data.id是:",n.data.id),console.log("response.data.token是:",n.data.token),localStorage.setItem("id",n.data.id),localStorage.setItem("citizenName",n.data.name),localStorage.setItem("token",n.data.token),e()}).catch(function(e){o(e)})})},Reg:function(e,n){e.commit;return console.log("触发patch，进入名为Reg的Action"),new c.a(function(e,t){var o;(o=n,Object(u.a)({url:"/register",method:"post",data:o})).then(function(n){console.log("进入名为Reg的Action，进入发送reg的post请求后的response"),console.log("response是：",n),console.log("response.data是:",n.data),console.log("msg是：",n.msg),e()}).catch(function(e){t(e)})})},LogOut:function(e){e.commit;var n=e.state;return console.log("进入citizen.js的一个action，名为LogOut!"),new c.a(function(e,t){(n.token,Object(u.a)({url:"/logout",method:"post"})).then(function(){localStorage.clear(),console.log("前端的token成功移除！"),e()(s)}).catch(function(e){t(e)})})}}};o.default.use(a.a);var p=new a.a.Store({state:{count:0},mutations:{increment:function(e){e.count++}},modules:{app:app,citizen:m}});console.log("第一次："+p.state.count),p.commit("increment"),console.log("第二次："+p.state.count);n.a=p},NHnr:function(e,n,t){"use strict";Object.defineProperty(n,"__esModule",{value:!0});var o=t("ih0I"),a={render:function(){var e=this.$createElement,n=this._self._c||e;return n("div",{staticClass:"appDiv",staticStyle:{height:"100%"},attrs:{id:"app"}},[n("router-view")],1)},staticRenderFns:[]};var l=t("e/h9")({name:"App"},a,!1,function(e){t("1lxJ")},null,null).exports,r=t("daBc");o.default.use(r.a);var i=new r.a({mode:"history",routes:[{path:"/",name:"Login",component:function(){return t.e(3).then(t.bind(null,"xJsL"))},meta:{title:"登录"}},{path:"/reg",name:"Reg",component:function(){return t.e(1).then(t.bind(null,"W0Op"))},meta:{title:"注册"}},{path:"/room",name:"Room",component:function(){return Promise.all([t.e(0),t.e(2)]).then(t.bind(null,"t3u0"))},children:[{path:"/home",name:"Home",component:function(){return Promise.all([t.e(0),t.e(22)]).then(t.bind(null,"RRqX"))},meta:{title:"主页"}},{path:"/home/help/call_help_a",name:"call_help_a",component:function(){return t.e(17).then(t.bind(null,"bHjZ"))},meta:{title:"求助a"}},{path:"/home/help/call_help_b",name:"call_help_b",component:function(){return Promise.all([t.e(0),t.e(10)]).then(t.bind(null,"B1r+"))},meta:{title:"求助b"}},{path:"/home/help/my_call_help",name:"my_call_help",component:function(){return Promise.all([t.e(0),t.e(15)]).then(t.bind(null,"/FeW"))},meta:{title:"我的求助"}},{path:"/home/help/call_help_edit",name:"call_help_edit",component:function(){return Promise.all([t.e(0),t.e(19)]).then(t.bind(null,"d2tI"))},meta:{title:"求助编辑"}},{path:"/home/mass/org_supervise",name:"org_supervise",component:function(){return t.e(4).then(t.bind(null,"XB6K"))},meta:{title:"组织监督"}},{path:"/home/volunteer/add_volunteer_a",name:"add_volunteer_a",component:function(){return t.e(13).then(t.bind(null,"lyt3"))}},{path:"/home/volunteer/add_volunteer_b",name:"add_volunteer_b",component:function(){return Promise.all([t.e(0),t.e(8)]).then(t.bind(null,"8MgZ"))}},{path:"/home/volunteer/volunteer_resource",name:"volunteer_resource",component:function(){return Promise.all([t.e(0),t.e(21)]).then(t.bind(null,"Gj2V"))},meta:{title:"公布资源"}},{path:"/home/volunteer/volunteer_info",name:"volunteer_info",component:function(){return Promise.all([t.e(0),t.e(18)]).then(t.bind(null,"P9sg"))},meta:{title:"志愿资料"}},{path:"/home/volunteer/volunteer_select",name:"volunteer_select",component:function(){return Promise.all([t.e(0),t.e(16)]).then(t.bind(null,"c8x+"))},meta:{title:"选取任务"}},{path:"/home/volunteer/volunteer_task",name:"volunteer_task",component:function(){return Promise.all([t.e(0),t.e(9)]).then(t.bind(null,"Wak7"))},meta:{title:"我的任务"}},{path:"/home/volunteer/volunteer_findPosition",name:"volunteer_findPosition",component:function(){return t.e(12).then(t.bind(null,"8SwJ"))},meta:{title:"展示位置"}},{path:"/home/leader/add_org_a",name:"add_org_a",component:function(){return t.e(14).then(t.bind(null,"5j/B"))},meta:{title:"创建组织a"}},{path:"/home/leader/add_org_b",name:"add_org_b",component:function(){return Promise.all([t.e(0),t.e(7)]).then(t.bind(null,"LY8R"))},meta:{title:"创建组织b"}},{path:"/home/leader/org_info",name:"org_info",component:function(){return Promise.all([t.e(0),t.e(6)]).then(t.bind(null,"xpRX"))},meta:{title:"组织资料"}},{path:"/home/leader/manage_leader",name:"manage_leader",component:function(){return Promise.all([t.e(0),t.e(5)]).then(t.bind(null,"8QOW"))},meta:{title:"领导管理"}},{path:"/home/leader/manage_volunteer",name:"manage_volunteer",component:function(){return Promise.all([t.e(0),t.e(20)]).then(t.bind(null,"okp2"))},meta:{title:"成员管理"}},{path:"/home/arrange/resource_display",name:"resource_display",component:function(){return Promise.all([t.e(0),t.e(23)]).then(t.bind(null,"gBWK"))},meta:{title:"资源展示"}},{path:"/home/arrange/arrange_resource",name:"arrange_resource",component:function(){return Promise.all([t.e(0),t.e(11)]).then(t.bind(null,"1Yc8"))}}]}]}),c=["/","/reg"];i.beforeEach(function(e,n,t){localStorage.getItem("token")?"/"===e.path?t({path:"/home"}):t():-1!==c.indexOf(e.path)?t():t("/")});var u=i,s=(t("0PTi"),t("xsKG"),t("FsGg"),t("XQT0")),m=t.n(s),p=t("B6vw"),d=t.n(p),h=t("+Fom"),g=t("y/BF"),f=t("BtP+"),_=t("IcnI");o.default.config.productionTip=!1,o.default.config.debug=!0,o.default.use(m.a),o.default.use(d.a),o.default.component("downloadExcel",h.a),o.default.prototype.$echarts=g,o.default.use(f.a),new o.default({el:"#app",store:_.a,router:u,components:{App:l},template:"<App/>"})},vLgD:function(e,n,t){"use strict";var o=t("Y0bB"),a=t.n(o),l=t("EYn+"),r=t.n(l),i=t("B6vw"),c=(t.n(i),t("IcnI")),u=r.a.create({baseURL:"https://181adeb1.cpolar.cn",timeout:15e3});u.interceptors.request.use(function(e){return console.log("进入前端request的拦截器！"),localStorage.getItem("token")&&(e.headers.Authorization=localStorage.getItem("token")),localStorage.getItem("id")&&(e.headers.id=localStorage.getItem("id")),console.log("到达这里，应该不是获取token出问题"),e},function(e){console.log(e),a.a.reject(e)}),u.interceptors.response.use(function(e){var n=e.data;return 200!==n.code?(Object(i.Message)({message:n.msg,type:"error",duration:3e3}),401===n.code&&i.MessageBox.confirm("登录已经过期，您可以继续留在该页面，或者重新登录","确定登出",{confirmButtonText:"重新登录",cancelButtonText:"留在此页",type:"warning"}).then(function(){c.a.dispatch("FedLogOut").then(function(){location.reload()})}),a.a.reject("error")):e.data},function(e){return console.log("err"+e),Object(i.Message)({message:e.message,type:"error",duration:3e3}),a.a.reject(e)}),n.a=u},xsKG:function(e,n){}},["NHnr"]);
//# sourceMappingURL=app.95b6d2356515b268f87b.js.map