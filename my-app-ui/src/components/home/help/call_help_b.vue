<template>
  <el-main>
    <el-page-header @back="goBack" content="步骤2 确认求救信息"></el-page-header>
    <el-form :model="form" :rules="callRules" ref="form" label-width="80px" size="mini">
      <el-form-item label="经度">
        <el-input v-model="form.longitude" :disabled="true"></el-input>
      </el-form-item>
      <el-form-item label="纬度">
        <el-input v-model="form.latitude" :disabled="true"></el-input>
      </el-form-item>
      <el-form-item label="救助人数" prop="peopleNum">
        <el-input v-model="form.peopleNum"></el-input>
      </el-form-item>
      <el-form-item label="联系电话" prop="phone">
        <el-input v-model="form.phone"></el-input>
      </el-form-item>
      <el-form-item label="约定时间" prop="deadline">
        <div class="block">
          <span class="demonstration"></span>
          <el-date-picker
            v-model="form.deadline"
            type="datetime"
            value-format="yyyy-MM-ddTHH:mm:ss.000Z"
          >
          </el-date-picker>
        </div>
      </el-form-item>
      <el-form-item label="医疗需求">
        <el-checkbox-group v-model="form.medicalResource">
          <el-checkbox label="口罩"></el-checkbox>
          <el-checkbox label="绷带"></el-checkbox>
          <el-checkbox label="担架"></el-checkbox>
          <el-checkbox label="降压药"></el-checkbox>
          <el-checkbox label="抗过敏药"></el-checkbox>
          <!--            <el-checkbox label="抗过敏药" name="medical_resource_str"></el-checkbox>-->
        </el-checkbox-group>
      </el-form-item>
      <el-form-item label="日用资源">
        <el-checkbox-group v-model="form.dailyResource">
          <el-checkbox label="饮用水" name="water"></el-checkbox>
          <el-checkbox label="充饥食品" name="food"></el-checkbox>
          <el-checkbox label="帐篷" name="daily_resource_str"></el-checkbox>
          <el-checkbox label="车位" name="daily_resource_str"></el-checkbox>
          <el-checkbox label="船位" name="daily_resource_str"></el-checkbox>
        </el-checkbox-group>
      </el-form-item>
      <el-form-item label="备注信息">
        <el-input v-model="form.comment"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click.native.prevent="onSubmit">确认提交</el-button>
        <el-button>取消</el-button>
      </el-form-item>
    </el-form>
  </el-main>
</template>

<script>
import {handleHelp} from "../../../api/call_help";

export default {
  name: "Call_Sent",
  data() {
    // const validateUsername = (rule, value, callback) => {
    //   if (!isvalidUsername(value)) {
    //     callback(new Error('请输入正确的手机号码、邮箱或者身份证号'))
    //   } else {
    //     callback()
    //   }
    // };
    return {
      form: {
        longitude: localStorage.getItem("longitude"),
        latitude: localStorage.getItem("latitude"),
        peopleNum: '',
        phone: '',
        deadline: '',
        medicalResource: [],
        dailyResource: [],
        comment: ''
      },
      callRules: {
        peopleNum: [
          {required: true, message: '请输入救助人数', trigger: 'blur'},
        ],
        phone: [{required: true, message: '请输入联系电话', trigger: 'blur'},
          {min: 7, max: 11, message: '长度在 7 到 11 个字符', trigger: 'blur'}
        ],
        deadline: [
          {required: true, message: '请选择日期', trigger: 'change'}
          //不去掉type会出错
          // {type: 'date', required: true, message: '请选择日期', trigger: 'change'}
        ]
      }//rules
    }
  },
  methods:
    {
      goBack() {
        this.$router.push({path: '/home/help/call_help_a'});
      },
      onSubmit() {
        this.$refs.form.validate(valid => {
          if (valid) {
            this.form.medicalResource = this.form.medicalResource.toString()
            this.form.dailyResource = this.form.dailyResource.toString()
            if(this.form.dailyResource==""){
              this.form.dailyResource="---"
            }
            if(this.form.medicalResource==""){
              this.form.medicalResource="---"
            }
            console.log(this.form)
            handleHelp(this.form).then(response=>{
              this.$message.success("已发起求助！")
              this.$router.push("/home")
            })
          }
        })
      }
    }
}
</script>

<style scoped>

.el-main {
  line-height: 30px;
}

.form_div {
  /*margin-left: auto;*/
  /*margin-right: auto;*/
  /*margin-top: auto;*/
  /*max-width: 500px;*/
  /*background: #D2E9FF;*/
  /*padding: 20px 20px 20px 20px;*/
  /*!*font: 12px Arial, Helvetica, sans-serif;*!*/
  /*!*color: #666;*!*/

  /*margin-left: auto;*/
  /*margin-right: auto;*/
  /*!*实现块元素百分比下居中*!*/
  /*width: 650px;*/
  /*padding: 50px;*/
  /*background: #DCDFE6;*/
  /*box-sizing: border-box;*/
  /*border-radius: 15px;*/
  /*text-align: center;*/
}

.el-table__header-wrapper {
  height: 30px;
}

</style>
