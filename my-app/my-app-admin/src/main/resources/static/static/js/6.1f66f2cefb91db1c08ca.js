webpackJsonp([6],{lkfG:function(e,t){},xpRX:function(e,t,r){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var o=r("qI/l"),l=r("tnU5"),a={name:"org_info",mounted:function(){var e=this;Object(l.i)({orgId:localStorage.getItem("l_orgId")}).then(function(t){Object(o.h)(t),e.form=t.data})},data:function(){return{form:{longitude:localStorage.getItem("org_longitude"),latitude:localStorage.getItem("org_latitude"),orgName:localStorage.getItem("org_orgName"),address:localStorage.getItem("org_address"),orgId:localStorage.getItem("l_orgId")},callRules:{orgName:[{required:!0,message:"组织名称",trigger:"blur"}],address:[{required:!0,message:"请输入组织地址",trigger:"blur"}]}}},methods:{goBack:function(){this.$router.push({path:this.$route.params.prePath})},onSubmit:function(){var e=this;this.$refs.form.validate(function(t){t&&(console.log(e.form),Object(o.i)(e.form).then(function(t){null!=t.data&&(Object(o.h)(t),e.$message.success("成功修改组织信息！"))}))})}}},n={render:function(){var e=this,t=e.$createElement,r=e._self._c||t;return r("el-main",[r("el-form",{ref:"form",attrs:{model:e.form,rules:e.callRules,"label-width":"80px",size:"mini"}},[r("el-form-item",{attrs:{label:"组织号"}},[r("el-input",{attrs:{disabled:!0},model:{value:e.form.orgId,callback:function(t){e.$set(e.form,"orgId",t)},expression:"form.orgId"}})],1),e._v(" "),r("el-form-item",{attrs:{label:"经度"}},[r("el-input",{attrs:{disabled:!0},model:{value:e.form.longitude,callback:function(t){e.$set(e.form,"longitude",t)},expression:"form.longitude"}})],1),e._v(" "),r("el-form-item",{attrs:{label:"纬度"}},[r("el-input",{attrs:{disabled:!0},model:{value:e.form.latitude,callback:function(t){e.$set(e.form,"latitude",t)},expression:"form.latitude"}})],1),e._v(" "),r("el-form-item",{attrs:{label:"组织名称",prop:"orgName"}},[r("el-input",{model:{value:e.form.orgName,callback:function(t){e.$set(e.form,"orgName",t)},expression:"form.orgName"}})],1),e._v(" "),r("el-form-item",{attrs:{label:"组织地址",prop:"address"}},[r("el-input",{model:{value:e.form.address,callback:function(t){e.$set(e.form,"address",t)},expression:"form.address"}})],1),e._v(" "),r("el-form-item",[r("el-button",{attrs:{type:"primary"},nativeOn:{click:function(t){return t.preventDefault(),e.onSubmit.apply(null,arguments)}}},[e._v("修改")])],1)],1)],1)},staticRenderFns:[]};var s=r("e/h9")(a,n,!1,function(e){r("lkfG")},"data-v-c2f4ee34",null);t.default=s.exports}});
//# sourceMappingURL=6.1f66f2cefb91db1c08ca.js.map