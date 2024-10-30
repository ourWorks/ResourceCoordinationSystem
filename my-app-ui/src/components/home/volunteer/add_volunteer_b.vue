<template>
  <el-main>
    <el-page-header @back="goBack" content="步骤2 填写志愿信息"></el-page-header>
    <el-form :model="form" :rules="callRules" ref="form" label-width="220px" size="mini" class="volunteerForm">
          <el-form-item label="经度">
            <el-input v-model="form.longitude" :disabled="true"></el-input>
          </el-form-item>
          <el-form-item label="纬度">
            <el-input v-model="form.latitude" :disabled="true"></el-input>
          </el-form-item>
          <el-form-item label="组织号" prop="orgId">
            <el-input v-model="form.orgId"></el-input>
          </el-form-item>
      <el-row>
        <el-col :span="12">
          <el-form-item label="【日常的空闲时间段】">
          </el-form-item>
        </el-col>
      </el-row>
          <el-form-item label="开始时间" prop="startTime">
            <!--这个value-format的书写讲究，不加的话传输时间就会出差错！https://blog.csdn.net/niaonao/article/details/88377352-->
            <el-time-picker placeholder="开始时间" v-model="form.startTime" style="width: 100%;"
                            type="datetime"
                            value-format="yyyy-MM-ddTHH:mm:ss"
                            prop="startTime"></el-time-picker>
          </el-form-item>
          <!--        </el-col>-->
          <!--        <el-col :span="12">-->
          <el-form-item label="结束时间" prop="endTime">
            <el-time-picker placeholder="终止时间" v-model="form.endTime" style="width: 100%;"
                            type="datetime"
                            value-format="yyyy-MM-ddTHH:mm:ss"
                            prop="endTime"></el-time-picker>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click.native.prevent="onSubmit">确认加入</el-button>
          </el-form-item>
    </el-form>
  </el-main>
</template>
<script>

import {addOrUpdateVolunteer} from "../../../api/volunteer";
import {parseTime} from "../../../utils/get_time";

export default {
  name: "add_volunteer_b",
  data() {
    return {
      form: {
        longitude: localStorage.getItem("longitude"),
        latitude: localStorage.getItem("latitude"),
        orgId: '',
        startTime: '',
        endTime: ''
      },
      callRules: {
        orgId: [{required: true, message: '请输入组织号', trigger: 'blur'},],
        startTime: [{required: true, message: '请输入开始时间', trigger: 'blur'}],
        endTime: [{required: true, message: '请输入结束时间', trigger: 'blur'}],
      }
    }
  },
  methods:
    {
      goBack() {
        this.$router.push({path: this.$route.params.prePath});
      },
      onSubmit() {
        this.$refs.form.validate(valid => {
          if (valid) {
            console.log(this.form)
            //需要传输id、组织号orgId、startTime、endTime
            this.form.id = localStorage.getItem("id");
            // console.log("小时",parseTime(this.form.startTime,"{h}:{i}:{s}"))
            // console.log("小时",this.form.startTime.getHours())
            // console.log("分钟",this.form.startTime.getMinutes())
            // console.log("秒",this.form.startTime.getSeconds())
            console.log("***********")
            addOrUpdateVolunteer(this.form).then(response => {
              console.log("发出请求")
              if (response.data != null) {
                this.$message.success("您已经成为组织【"+ response.data.orgName+"】的志愿者！")
              }
            })
          }
        })
      }
    }
}
</script>

<style scoped>

.volunteerForm {
  height: max-content;
  margin-left: auto;
  margin-right: auto;
  margin-top: auto;
}

</style>
