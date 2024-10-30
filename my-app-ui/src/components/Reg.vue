<template>
  <div>
    <h3 style="text-align: center">高校志愿者排班系统</h3>
    <div class="regFormDiv">
      <h2 class="reg-title color-main">注册</h2>
      <!--status-icon就能用于表示状态 -->
      <el-card class="reg-form-layout">
        <el-form autoComplete="on"
                 :model="regForm"
                 :rules="regRules"
                 status-icon
                 ref="regForm"
                 label-width="80px"
                 label-position="left">
          <!--          <div style="text-align: center">-->
          <!--            &lt;!&ndash;          <svg-icon icon-class="reg-mall" style="width: 56px;height: 56px;color: #409EFF"></svg-icon>&ndash;&gt;-->
          <!--            <i class="fa fa-heartbeat" aria-hidden="true" style="width: 56px;height: 56px;color: #409EFF"></i>-->
          <!--          </div>-->
          <el-form-item label="手机号" prop="phone">
            <el-input name="phone"
                      type="text"
                      v-model="regForm.phone"
                      autoComplete="on"
                      placeholder="请输入手机号">
          <span slot="prefix">
                <i class="fa fa-mobile-phone" aria-hidden="true"></i>
          </span>
            </el-input>
          </el-form-item>

          <el-form-item label="密码" prop="password" >
            <el-input type="password" v-model="regForm.password" autocomplete="off" placeholder="请输入密码">
                <span slot="prefix">
                  <i class="fa fa-key" aria-hidden="true"></i>
                </span>
            </el-input>
          </el-form-item>
          <el-form-item label="确认密码" prop="password2">
            <el-input type="password" v-model="regForm.password2" autocomplete="off" placeholder="请确认密码">
                <span slot="prefix">
                  <i class="fa fa-key" aria-hidden="true"></i>
                </span>
            </el-input>
          </el-form-item>
          <el-form-item
            prop="email"
            label="邮箱">
            <el-input v-model="regForm.email" placeholder="请输入邮箱">
                <span slot="prefix">
                  <i class="fa fa-envelope-square" aria-hidden="true"></i>
                </span>
            </el-input>
          </el-form-item>

          <el-form-item label="姓名" prop="name">
            <el-input name="name"
                      type="text"
                      v-model="regForm.name"
                      autoComplete="on"
                      placeholder="请输入姓名">
          <span slot="prefix">
                <i class="fa fa-user" aria-hidden="true"></i>
          </span>
            </el-input>
          </el-form-item>

          <el-form-item label="身份证号" prop="idCard">
            <el-input name="idCard"
                      type="text"
                      v-model="regForm.idCard"
                      autoComplete="on"
                      placeholder="请输入身份证号">
          <span slot="prefix">
                <i class="fa fa-id-card" aria-hidden="true"></i>
          </span>
            </el-input>
          </el-form-item>
          <el-form-item style="margin-bottom: 60px;text-align: center">
            <el-button style="width: 45%" type="primary" @click.native.prevent="handleReg">
              注册
            </el-button>
            <el-button style="width:45%" type="info" @click.native.prevent="toLogin">
              去登录
            </el-button>
          </el-form-item>
        </el-form>
      </el-card>
    </div>
    <div></div>
  </div>
</template>

<script>

import {isvalidUsername} from '@/utils/validate';

export default {
  name: 'Reg',
  data() {
    //验证用户名
    const validateUsername = (rule, value, callback) => {
      if (!isvalidUsername(value)) {
        callback(new Error('请输入正确的姓名'))
      } else {
        callback()
      }
    };
    const validatePhone = (rule, value, callback) => {
      if (!isvalidUsername(value)) {
        callback(new Error('请输入正确的手机号'))
      } else {
        callback()
      }
    };
    //验证密码
    var validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'));
      } else {
        if (this.regForm.password !== '') {
          // this.$refs.regForm.validateField('password');是错误写法
          this.$refs.regForm.validateField('password');
        }
        callback();
      }
    };
    //验证第二次输入的密码
    var validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'));
      } else if (value !== this.regForm.password) {
        callback(new Error('两次输入密码不一致!'));
      } else {
        callback();
      }
    };
    return {
      regForm: {
        phone: '',
        email: '',
        name: '',
        idCard: '',
        password: '',
        password2: '',
      },
      regRules: {
        phone: [{required: true, trigger: 'blur', validator: validatePhone}],
        password: [{required: true, trigger: 'blur', validator: validatePass}],
        password2: [{required: true, trigger: 'blur', validator: validatePass2}],
        email: [
          {required: true, message: '请输入邮箱地址', trigger: 'blur'},
          {type: 'email', message: '请输入正确的邮箱地址', trigger: ['blur', 'change']}
        ],
        name: [{required: true, trigger: 'blur', validator: validateUsername}],
        idCard: [{required: true, trigger: 'blur', validator: this.validID}],
      },
      pwdType: 'password'
    }
  },
  methods: {
    // 身份证验证
    async validID(rule, value, callback) {
      // 身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X
      let reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
      if (reg.test(value)) {
        await this.go(value.length);
        callback()
      } else {
        callback(new Error('身份证号码不正确'))
      }
    },
    // 根据身份证自动生成生日，性别，年龄:默认身份证位数为18位
    go() {
      let iden = this.regForm.idCard;
      let sex = null;
      let birth = null;
      let myDate = new Date();
      let month = myDate.getMonth() + 1;
      let day = myDate.getDate();
      let age = 0;

      //默认身份证位数为18位
      let val = 18;
      age = myDate.getFullYear() - iden.substring(6, 10) - 1;
      sex = iden.substring(16, 17);
      birth = iden.substring(6, 10) + "-" + iden.substring(10, 12) + "-" + iden.substring(12, 14);
      if (iden.substring(10, 12) < month || iden.substring(10, 12) == month && iden.substring(12, 14) <= day) age++;

      if (sex % 2 === 0)
        sex = '女';
      else
        sex = '男';
      this.regForm.gender = sex;
      this.regForm.age = age;
      console.log(this.regForm)
    },
    showPwd() {
      if (this.pwdType === 'password') {
        this.pwdType = ''
      } else {
        this.pwdType = 'password'
      }
    },
    handleReg() {
      this.$refs.regForm.validate(valid => {
        if (valid) {
          this.$store.dispatch('Reg', this.regForm).then(message => {
            // setCookie("phone", this.regForm.phone, 15);
            console.log("执行dispatch之后返回到dispatch，下面执行转发到/login");
            this.$router.push({path: '/login'});
          }).catch(() => {
          })
        }
      })
    },
    toLogin() {
      this.$router.push("/login")
    }
  }
}
</script>


<style scoped>

.reg-form-layout {
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
  border-radius: 15px;
  text-align: center;

  background-image: url("./back.png");

}

.reg-title {
  text-align: center;
}

</style>
