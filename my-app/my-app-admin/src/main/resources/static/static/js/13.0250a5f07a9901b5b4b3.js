webpackJsonp([13],{D6xb:function(t,e){},lyt3:function(t,e,a){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var n={name:"add_volunteer_a",data:function(){return{preGet:!0,longitude:23.20211561621817,latitude:113.38094520264053}},mounted:function(){var t=this,e=document.createElement("script");e.type="text/javascript",e.src="https://map.qq.com/api/gljs?v=1.exp&key=OB4BZ-D4W3U-B7VVO-4PJWW-6TKDJ-WPB77&callback=getPosition",document.body.appendChild(e),this.$alert("你好，请等待地图加载完毕，然后单击标记您的常住位置？",{confirmButtonText:"确定",callback:function(e){t.getPosition()}})},methods:{goBack:function(){this.$router.push({path:"/home"})},getPosition:function(){var t=this;this.preGet=!1;var e=new TMap.LatLng(23.20211561621817,113.38094520264053),a=new TMap.Map("container",{zoom:17,center:e}),n=new TMap.MultiMarker({id:"marker-layer",map:a,geometries:[]});a.on("click",function(e){n.add({position:e.latLng}),t.longitude=e.latLng.getLat(),t.latitude=e.latLng.getLng();var a="经度:"+t.longitude+",纬度:"+t.latitude+";";t.$confirm(a,"确定您的位置信息？",{confirmButtonText:"确定",cancelButtonText:"重选"}).then(function(){t.$message({type:"success",message:"成功选取位置"}),console.log("test"),localStorage.setItem("longitude",t.longitude),localStorage.setItem("latitude",t.latitude),t.$router.push({name:"add_volunteer_b",params:{prePath:t.$route.path,destinationPath:t.$route.params.destinationPath}})}).catch(function(){n.setGeometries([])})})}}},o={render:function(){var t=this.$createElement,e=this._self._c||t;return e("div",[e("el-page-header",{attrs:{content:"步骤1 选取您的常住地址"},on:{back:this.goBack}}),this._v(" "),e("div",{attrs:{id:"container"}})],1)},staticRenderFns:[]};var i=a("e/h9")(n,o,!1,function(t){a("D6xb")},"data-v-6c9aaddc",null);e.default=i.exports}});
//# sourceMappingURL=13.0250a5f07a9901b5b4b3.js.map