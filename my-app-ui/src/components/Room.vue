<template>
  <div id="home" class="room" style="height: 100%">
    <el-container style="height: 100%">
      <!--如果这里配置了样式style与全局的不同，那么优先按照这里的来展示,style="width: 20%"改动为适应内容-->
      <!--      <el-aside style="height: 100%; width: fit-content" :isCollapse='left_side_is_collapse'>-->
      <el-aside style="height: 100%; width: min-content" :isCollapse='left_side_is_collapse'>
        <!--          Aside-->
        <!--第三步使用-->
        <my_aside></my_aside>
      </el-aside>
      <el-container>
        <el-header>
          <!--        Header-->
          <!--设置margin: 5px这样图片就能对齐中间-->
          <!--          <up_menu style="margin: 5px"></up_menu>-->
          <my_header></my_header>
        </el-header>
        <!--        <el-main>-->
        <router-view></router-view>
        <!--        </el-main>-->
        <!--        <el-footer>Copyright © 2022 Su Xiaolong.</el-footer>-->
      </el-container>
    </el-container>
    <!--    </el-container>-->
  </div>
</template>

<script>
//第一步引入,不能起名字为header或aside
import my_header from './menu/Header.vue'
import my_aside from './menu/Aside.vue'

export default {
  name: 'Room',
  data() {
    return {
      left_side_is_collapse: false
    }
  },
  //第二步注册
  components: {my_header, my_aside}
}
</script>

<style>

.el-table__header th, .el-table__header tr {
  background-color: #17B3A3;
  color: black;
}

.el-aside {
  color: #333;
}

.el-footer {
  background-color: #979696;
  color: #ffffff;
  text-align: center;
  line-height: 60px;
}

.el-main {
  background-color: #E9EEF3;
  color: #333;
  text-align: center;
  line-height: 160px;
}

body > .el-container {
  margin-bottom: 40px;
}

.el-container:nth-child(5) .el-aside,
.el-container:nth-child(6) .el-aside {
  line-height: 260px;
}

.el-container:nth-child(7) .el-aside {
  line-height: 320px;
}

.room {
  background-color: white;
  /*透明度*/
}

</style>
