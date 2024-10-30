<template>
  <!--  <div >-->
  <el-row :gutter="24" class="header_class">
    <!--        这里用来放导航栏-->
    <el-col :span="24">
      <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect">
        <el-menu-item style="width:fit-content" index="1" @click="toHome">
          <i class="el-icon-s-home" aria-hidden="true"></i>
          系统概况展示
        </el-menu-item>
<!--        设置不能点击-->
        <el-menu-item style="width:auto;pointer-events:none;">
<!--          <span style="font-size: larger;font-weight: bolder">广金志愿者排班管理系统</span>-->
        </el-menu-item>
        <el-menu-item style="width:fit-content;float: right">
          <el-dropdown class="my_dropdown">
            <span class="el-dropdown-link">
              <el-avatar class="md_headpic" :size="45" style="font-size: smaller">{{ citizenName }}</el-avatar>
            </span>
            <el-dropdown-menu slot="dropdown" @command="clickImg">
              <el-dropdown-item command="b"><a style="color: red" @click="logout">退出登录</a></el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </el-menu-item>
      </el-menu>
    </el-col>
    <div class="line"></div>
  </el-row>
</template>

<script>

export default {
  name: "UpMenu",
  data() {
    return {
      activeIndex: '1',
      citizenName: localStorage.getItem("citizenName")
      // fits: ['fill', 'contain', 'cover', 'none', 'scale-down'],
      // fit: 'contain',

    };
  },
  mounted() {
    //根据路由来选中激活的el-menu-item
    // console.log("$$$$$$$$$$$$$$$$$")
    // console.log(this.$router.currentRoute.path)
    //自己测试出来的
    if (this.$router.currentRoute.path == "/home") {
      this.activeIndex = '1'
    } else if (this.$router.currentRoute.path == "/home/help/call_help_a") {
      this.activeIndex = '2'
    }else{
      this.activeIndex = '3'
    }
  },
  methods: {
    handleSelect(key, keyPath) {
      console.log(key, keyPath);
    },
    clickImg(command) {
      console.log("点击了头像")
    },
    increment() {
      this.$store.commit('increment')
      console.log(this.$store.state.count)
    },
    logout() {
      //发出请求,通知后端删除token或登录状态
      console.log("准备退出登录！")
      this.$store.dispatch('LogOut').then(() => {
        console.log("dispatch执行完毕")
        //前端进行token的删除和跳转
        this.$router.push({path: '/'}).then(() => {
          location.reload()   // 重新实例化vue-router对象,减少出现bug的可能
          console.log("push执行完毕")
        }).catch(err => {
        })
        //上面的catch只是为了减少控制台的重定向报错
      })
    },
    toHome() {
      this.$router.push("/home")
    },
    toCall() {
      const url = '/home/help/call_help_a';
      // this.$router.push({path: '/home/help/call_help_a'})
      // if (this.$route.path !== url) {
      this.$router.push({path: url}).catch(error => {
      })
    }
  }
}
</script>

<style scoped>
.el-dropdown-link {
  cursor: pointer;
  /*color: #409EFF;*/
}

.header_class {
  background: rgba(214, 214, 214, 0.11);
}

.el-menu-demo {
  background: rgba(214, 214, 214, 0.11);
}

.my_dropdown {
  line-height: 10px;
}


.md_headpic {
  background: #409EFF;
  border: none;
  vertical-align: middle;
  margin-left: 10px;
}

</style>
