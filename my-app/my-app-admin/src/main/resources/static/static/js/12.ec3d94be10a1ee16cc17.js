webpackJsonp([12],{"8SwJ":function(t,e,n){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var a={name:"volunteer_findPosition",data:function(){return{preGet:!0,longitude:23.20211561621817,latitude:113.38094520264053}},mounted:function(){this.getPosition()},methods:{goBack:function(){this.$router.push("/home/volunteer/volunteer_task")},getPosition:function(){var t=this;this.longitude=this.$route.params.task_longitude,this.latitude=this.$route.params.task_latitude;var e=document.createElement("script");e.type="text/javascript",e.src="https://map.qq.com/api/gljs?v=1.exp&key=OB4BZ-D4W3U-B7VVO-4PJWW-6TKDJ-WPB77&callback=getPosition",document.body.appendChild(e),this.preGet=!1;var n=new TMap.LatLng(23.20211561621817,113.38094520264053),a=new TMap.Map("container",{zoom:17,center:n}),i=new TMap.MultiMarker({id:"marker-layer",map:a,geometries:[{position:new TMap.LatLng(this.longitude,this.latitude,50),id:"marker"}]});a.on("click",function(e){i.add({position:e.latLng}),t.longitude=e.latLng.getLat(),t.latitude=e.latLng.getLng();var n="经度:"+t.longitude+",纬度:"+t.latitude+";";t.$confirm(n,"确定您的位置信息？",{confirmButtonText:"确定",cancelButtonText:"重选"}).then(function(){t.$message({type:"success",message:"成功选取位置"}),console.log("test"),localStorage.setItem("longitude",t.longitude),localStorage.setItem("latitude",t.latitude),t.$router.push({path:"/home/help/call_help_b"})}).catch(function(){i.setGeometries([])})})}}},i={render:function(){var t=this.$createElement,e=this._self._c||t;return e("div",[e("el-page-header",{attrs:{content:"求助人员定位展示"},on:{back:this.goBack}}),this._v(" "),e("div",{attrs:{id:"container"}})],1)},staticRenderFns:[]};var o=n("e/h9")(a,i,!1,function(t){n("M+rz")},"data-v-6db3fdea",null);e.default=o.exports},"M+rz":function(t,e){}});
//# sourceMappingURL=12.ec3d94be10a1ee16cc17.js.map