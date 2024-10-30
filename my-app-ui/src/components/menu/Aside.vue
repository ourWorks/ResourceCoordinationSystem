<template>
  <el-menu :default-openeds="['']" class="el-menu-vertical-demo" @open="handleOpen" @close="handleClose"
           @select="handleSelect"
           :collapse='isCollapse'>
    <el-submenu index="help">
      <template slot="title">
<!--        <i class="fa fa-street-view" aria-hidden="true"></i>-->
        <i class="fa fa-phone" aria-hidden="true"></i>
        <span slot="title">求助中心</span>
      </template>
      <el-menu-item-group>
        <el-menu-item index="/help/call_help_a">快速求助</el-menu-item>
      </el-menu-item-group>
      <el-menu-item-group>
        <el-menu-item index="/help/my_call_help">我的求助</el-menu-item>
      </el-menu-item-group>
    </el-submenu>
    <el-submenu index="volunteer">
      <template slot="title">
        <i class="fa fa-handshake-o" aria-hidden="true"></i>
        <span slot="title">志愿中心</span>
      </template>
      <el-menu-item-group>
        <el-menu-item index="/volunteer/volunteer_info">志愿资料</el-menu-item>
        <el-menu-item index="/volunteer/volunteer_resource">公布资源</el-menu-item>
        <el-menu-item index="/volunteer/volunteer_select">选取任务</el-menu-item>
        <el-menu-item index="/volunteer/volunteer_task">我的任务</el-menu-item>
      </el-menu-item-group>
    </el-submenu>
    <el-submenu index="leader">
      <template slot="title">
        <i class="fa fa-users" aria-hidden="true"></i>
        <span slot="title">组织管理</span>
      </template>
      <el-menu-item-group>
        <el-menu-item index="/leader/org_info">组织资料</el-menu-item>
      </el-menu-item-group>
      <el-menu-item-group>
        <el-menu-item index="/leader/manage_leader">领导管理</el-menu-item>
      </el-menu-item-group>
      <el-menu-item-group>
        <el-menu-item index="/leader/manage_volunteer">志愿者管理</el-menu-item>
      </el-menu-item-group>
    </el-submenu>
    <el-submenu index="arrange">
      <template slot="title">
        <i class="fa fa-list-alt" aria-hidden="true"></i>
        <span slot="title">排班管理</span>
      </template>
      <el-menu-item-group>
        <el-menu-item index="/arrange/resource_display">资源对比</el-menu-item>
      </el-menu-item-group>
      <el-menu-item-group>
        <el-menu-item index="/arrange/arrange_resource">设置策略</el-menu-item>
      </el-menu-item-group>
    </el-submenu>
    <!--    <div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">-->
    <!--      <i class="ace-icon fa fa-angle-double-right" data-icon1="ace-icon fa fa-angle-double-left" data-icon2="ace-icon fa fa-angle-double-right"></i>-->
    <!--    </div>-->
    <div class="control_left_button" type="info" @click="openOrClose">
      <!--        <i class="fa fa-phone" style="width: 100%" aria-hidden="true"></i>-->
      <!--        <i class="fa fa-phone" aria-hidden="true"></i>-->
      <i v-bind:class='iconData' data-icon1="ace-icon fa fa-angle-double-left"
         data-icon2="ace-icon fa fa-angle-double-right"></i>
    </div>
    <!--      <el-button class="control_left_button" type="info" v-bind:icon='iconData' @click="openOrClose">-->
    <!--        &lt;!&ndash;        <i class="fa fa-phone" style="width: 100%" aria-hidden="true"></i>&ndash;&gt;-->
    <!--        &lt;!&ndash;        <i class="fa fa-phone" aria-hidden="true"></i>&ndash;&gt;-->
    <!--      </el-button>-->
    <!--    </div>-->
  </el-menu>
</template>

<script>
import {isVolunteer} from "../../api/volunteer";
import {isLeader} from "../../api/leader";
import {setLocalVolunteer} from "../../api/volunteer";

export default {
  data() {
    return {
      isCollapse: false,
      iconData: 'ace-icon fa fa-angle-double-left'
    };
  },
  methods: {
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    },
    openOrClose() {
      if (this.isCollapse == true) {
        this.isCollapse = false;
        this.iconData = 'ace-icon fa fa-angle-double-left'
      } else {
        this.isCollapse = true;
        this.iconData = 'ace-icon fa fa-angle-double-right'
      }
    },
    handleSelect(key, keyPath) {
      console.log("######")
      console.log(keyPath)
      //如果是志愿者就能进入志愿模块
      if (keyPath[0] == "volunteer") {
        //记住不要把这个请求方法写在if判断里面
        isVolunteer().then(response => {
          //不是志愿者就拦截，是志愿者就放行
          if (response.data == null) {
            this.addOrgOrQuit("/home" + key)
          } else {
            //放行时候同步一下志愿者的信息
            setLocalVolunteer(response);
            this.$router.push("/home" + key).catch(error => {
            })
          }
        })
      }
      //如果是志愿者就能进入志愿模块
      else if (keyPath[0] == "leader" || keyPath[0] == "arrange") {
        //记住不要把这个请求方法写在if判断里面
        isLeader().then(response => {
          //如果不是志愿者
          if (response.data == null) {
            this.createOrgOrQuit("/home" + key)
          } else {
            //【领导】记录组织orgId，其它的去修改组织信息的页面mounted记录
            localStorage.setItem("l_orgId", response.data.orgId)
            this.$router.push("/home" + key).catch(error => {
            })
          }
        })
      } else {
        //如果有权限就可以跳转
        this.$router.push("/home" + key).catch(error => {
        })
      }
    },
    //当不是志愿者时，就要做志愿中心点击的拦截
    addOrgOrQuit(path) {
      this.$confirm('您还不是任何组织的志愿者, 请问是否要加入新组织?', '提示', {
        confirmButtonText: '加入组织 ',
        cancelButtonText: '返回',
        type: 'warning'
      }).then(() => {
        ////////////////////////////////////加入志愿者组织的跳转/////////////////////////////////////////
        this.$router.push({
          name: "add_volunteer_a",
          params: {prePath: this.$route.path, destinationPath: path}
        }).catch(error => {
        })
      }).catch(() => {
        //二、加入失败的提示
        this.$message({
          type: 'info',
          message: '已取消加入组织'
        });
      });
      ////////////////////////////////////加入志愿者组织的弹框/////////////////////////////////////////
      // })
    },
    //当不是领导时，就要拦截
    createOrgOrQuit(path) {
      this.$confirm('您还不是任何组织的领导, 请问是否要创建新组织?', '提示', {
        confirmButtonText: '去创建',
        cancelButtonText: '返回',
        type: 'warning'
      }).then(() => {
        ////////////////////////////////////创建组织的弹框/////////////////////////////////////////
        //由于动态路由也是传递params的，所以在 this.$router.push() 方法中path不能和params一起使用，否则params将无效。需要用name来指定页面。
        this.$router.push({
          name: "add_org_a",
          params: {prePath: this.$route.path, destinationPath: path}
        }).catch(error => {
        })
        // this.$router.push("/home/leader/add_org",{prePath:"aaa"}).catch(error => {})
        ////////////////////////////////////创建组织的跳转/////////////////////////////////////////
      }).catch(() => {
        // this.$message({
        //   type: 'info',
        //   message: '已取消跳转',
        // });
      });
    }
  }
}
</script>

<style scoped>


.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 150px;
  min-height: 400px;
  border-color: #d6d6d6;
  background-color: rgba(214, 214, 214, 0.2);
  height: 100%;
}

.el-menu--collapse {
  width: 100%;
  height: 100%;
  background: rgba(214, 214, 214, 0.11);
}

.el-menu-item is-active{
  width: 150px;
}

.control_left_button {
  margin: 15px 0 0 0;
  padding: 0;
  width: 100%;
  height: 20px;
  /*box-shadow: 0 1px 0 #d6d6d6;*/
  text-align: center;
}

.sidebar .sidebar-toggle {
  position: relative;
  padding: 3px 0;
  border-style: solid;
  border-width: 0 0 1px;
  text-align: center;
}


</style>
