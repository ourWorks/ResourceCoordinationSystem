// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from '@/router/index'

Vue.config.productionTip = false

//开启debug模式
Vue.config.debug = true;
import 'element-ui/lib/theme-chalk/index.css';
//能够隐藏布局的插件
import 'element-ui/lib/theme-chalk/display.css';
//引入font-awesome图标
import 'font-awesome/css/font-awesome.min.css'

//引入v-charts,试验证明必须放在ElementUI之前
import VCharts from 'v-charts'
Vue.use(VCharts)

//引入ElementUI
import ElementUI from 'element-ui'
//引入elementUI
Vue.use(ElementUI);

//引入vue-json表格操作的文件
import JsonExcel from 'vue-json-excel'
Vue.component('downloadExcel', JsonExcel)


// import echarts from 'echarts'
import * as echarts from 'echarts'
Vue.prototype.$echarts = echarts

//引入它就可以使用动态数据
import VueResource from 'vue-resource'
Vue.use(VueResource);

//导入store文件夹声明的store
import store from './store'

/* eslint-disable no-new */
new Vue({
  el: '#app',
  //In order to have an access to this.$store property in your Vue components, you need to provide the created store to Vue instance.
  store: store,
  router,
  components: { App },
  template: '<App/>'
})
