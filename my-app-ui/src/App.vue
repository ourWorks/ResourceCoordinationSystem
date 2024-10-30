<template>
  <div id="app" class="appDiv" style="height: 100%">
<!--    没有name属性,那就是默认-->
    <router-view></router-view>
  </div>
</template>

<script>
  // //第一步引入
  // import login from './components/Login.vue'
  // import left_aside from './components/menu/LeftAside.vue'

  export default {
    name: 'App',
    //第二步注册
    // components: {login}
  }
</script>

<style>
.appDiv{
  background-color: #E9EEF3;
}
</style>
