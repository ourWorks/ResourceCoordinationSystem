webpackJsonp([23],{gBWK:function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var o=a("Pwhg"),n={name:"resource_display",data:function(){return{totalDemand:[2,4.9,7,23.2,25.6,76.7,135.6,162.2,32.6,20,6.4,3.3],orgResource:[2.6,5.9,9,26.4,28.7,70.7,175.6,182.2,48.7,18.8,6,2.3],maxNum:250,minNum:0,betweenSpace:50}},mounted:function(){this.updatePicture()},methods:{updatePicture:function(){var e=this;Object(o.d)().then(function(t){console.log("methodA方法"),console.log("&&&&&&&&&&&"),console.log(t.data),e.totalDemand=t.data;for(var a=0;a<t.data.length;a++)e.maxNum=e.maxNum>t.data[a]?e.maxNum:t.data[a];Object(o.c)({orgId:localStorage.getItem("l_orgId")}).then(function(t){e.orgResource=t.data;for(var a=0;a<t.data.length;a++)e.maxNum=e.maxNum>t.data[a]?e.maxNum:t.data[a];e.maxNum<100?e.maxNum=100:e.maxNum<500?e.maxNum=500:e.maxNum<1e3?e.maxNum=1e3:e.maxNum<1500?e.maxNum=1500:e.maxNum<2e3&&(e.maxNum=2e3),e.betweenSpace=e.maxNum/5,console.log("methodB方法"),e.generatePic()})})},generatePic:function(){var e,t=this.$echarts.init(document.getElementById("main"));(e={tooltip:{trigger:"axis",axisPointer:{type:"cross",crossStyle:{color:"#999"}}},toolbox:{feature:{dataView:{show:!0,readOnly:!1},magicType:{show:!0,type:["line","bar"]},restore:{show:!0},saveAsImage:{show:!0}}},legend:{data:["“待救援”需求","本组织总供应"]},xAxis:[{type:"category",data:["口罩","绷带","担架","降压药","抗过敏药","饮用水","充饥食品","帐篷","车位","船位"],axisPointer:{type:"shadow"}}],yAxis:[{type:"value",name:"份数",min:this.minNum,max:this.maxNum,interval:this.betweenSpace,axisLabel:{formatter:"{value} /人"}}],series:[{name:"“待救援”需求",type:"bar",tooltip:{valueFormatter:function(e){return e+" ml"}},data:this.totalDemand},{name:"本组织总供应",type:"bar",tooltip:{valueFormatter:function(e){return e+" ml"}},data:this.orgResource}]})&&t.setOption(e)}}},m={render:function(){var e=this.$createElement,t=this._self._c||e;return t("div",{staticStyle:{width:"100%",height:"100%"},attrs:{id:"charts"}},[t("div",{style:{width:"100%",height:"100%"},attrs:{id:"main"}})])},staticRenderFns:[]},r=a("e/h9")(n,m,!1,null,null,null);t.default=r.exports}});
//# sourceMappingURL=23.4875e9827cd076096d2e.js.map