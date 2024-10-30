<template>
  <div class="loginPage">
    <h3 style="text-align: center">高校志愿者排班系统</h3>
    <el-card class="login-form-layout">
      <el-form autoComplete="on"
               :model="loginForm"
               :rules="loginRules"
               ref="loginForm"
               label-position="left">
        <div style="text-align: center">
          <!--                      <svg-icon icon-class="login-mall" style="width: 56px;height: 56px;color: #409EFF"></svg-icon>-->
          <i class="fa fa-heartbeat" aria-hidden="true" style="width: 56px;height: 56px;color: #409EFF"></i>
        </div>
        <h2 class="login-title color-main">登录</h2>
        <el-form-item prop="phone">
          <el-input name="phone"
                    type="text"
                    v-model="loginForm.phone"
                    autoComplete="on"
                    placeholder="请输入手机号，邮箱或者身份证">
          <span slot="prefix">
                <i class="fa fa-user" aria-hidden="true"></i>
          </span>
          </el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input name="password"
                    :type="pwdType"
                    @keyup.enter.native="handleLogin"
                    v-model="loginForm.password"
                    autoComplete="on"
                    placeholder="请输入密码">
          <span slot="prefix">
            <i class="fa fa-key" aria-hidden="true"></i>
          </span>
            <span slot="suffix" @click="showPwd">
          <i class="fa fa-eye" aria-hidden="true"></i>
          </span>
          </el-input>
        </el-form-item>
        <el-form-item style="margin-bottom: 60px;text-align: center">
          <el-button style="width: 45%" type="primary" @click.native.prevent="handleLogin">
            登录
          </el-button>
          <el-button style="width:45%" type="info" @click.native.prevent="toReg">
            去注册
          </el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script>

import {isvalidUsername} from '@/utils/validate';
import {setSupport, getSupport, setCookie, getCookie} from '@/utils/support';

export default {
  name: 'Login',
  data() {
    const validateUsername = (rule, value, callback) => {
      if (!isvalidUsername(value)) {
        callback(new Error('请输入正确的手机号码、邮箱或者身份证号'))
      } else {
        callback()
      }
    };
    const validatePass = (rule, value, callback) => {
      if (value.length < 3) {
        callback(new Error('密码不能小于3位'))
      } else {
        callback()
      }
    };
    return {
      loginForm: {
        phone: '',
        password: '',
      },
      loginRules: {
        phone: [{required: true, trigger: 'blur', validator: validateUsername}],
        password: [{required: true, trigger: 'blur', validator: validatePass}]
      },
      pwdType: 'password'
    }
  },
  methods: {
    showPwd() {
      if (this.pwdType === 'password') {
        this.pwdType = ''
      } else {
        this.pwdType = 'password'
      }
    },
    handleLogin() {
      this.$refs.loginForm.validate(valid => {
        if (valid) {
          this.$store.dispatch('Login', this.loginForm).then(message => {
            setCookie("phone", this.loginForm.phone, 15);
            //前往别把密码放在cookie
            // setCookie("password", this.loginForm.password, 15);
            console.log("执行dispatch之后返回到dispatch，下面执行转发到/home");
            // alert("Room里面寻找存入的tokenStr", tokenStr);
            // this.headers['Authorization'] = localStorage.getItem("token");
            this.$router.push({path: '/home'});
          }).catch(() => {
            // this.loading = false
          })
        } else {
          console.log('参数验证不合法！');
          return false
        }
      })
    },
    toReg() {
      this.$router.push("/reg")
    }
  }
}
</script>


<style scoped>

.login-form-layout {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  /*实现块元素百分比下居中*/
  width: 450px;
  padding: 50px;
  background: #DCDFE6;
  box-sizing: border-box;
  /*box-shadow: 0px 15px 25px rgb(171, 255, 2);*/
  /*border-radius: 15px;*/
  text-align: center;
  background-image: url("./back.png");
  /*background-size: length|percentage|cover|contain;*/
  /*透明度*/
}

</style>
