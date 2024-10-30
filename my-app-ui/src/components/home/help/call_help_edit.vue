<template>
  <el-form :model="editForm" ref="editForm" v-if="showTable" :rules="callRules" label-width="80px" size="mini">
    <el-form-item label="经度">
      <el-input v-model="editForm.longitude" :disabled="true"></el-input>
    </el-form-item>
    <el-form-item label="纬度">
      <el-input v-model="editForm.latitude" :disabled="true"></el-input>
    </el-form-item>
    <el-form-item label="救助人数" prop="peopleNum">
      <el-input v-model="editForm.peopleNum" required></el-input>
    </el-form-item>
    <el-form-item label="联系电话" prop="phone">
      <el-input v-model="editForm.phone"></el-input>
    </el-form-item>
    <el-form-item label="约定时间" prop="deadline">
      <div class="block">
        <span class="demonstration"></span>
        <el-date-picker
          v-model="editForm.deadline"
          type="datetime"
        >
        </el-date-picker>
      </div>
    </el-form-item>
    <el-form-item label="医疗需求">
      <el-checkbox-group v-model="editForm.medicalResource">
        <el-checkbox label="口罩"></el-checkbox>
        <el-checkbox label="绷带"></el-checkbox>
        <el-checkbox label="担架"></el-checkbox>
        <el-checkbox label="降压药"></el-checkbox>
        <el-checkbox label="抗过敏药"></el-checkbox>
      </el-checkbox-group>
    </el-form-item>
    <el-form-item label="日用资源">
      <el-checkbox-group v-model="editForm.dailyResource">
        <el-checkbox label="饮用水" name="dailyResource"></el-checkbox>
        <el-checkbox label="充饥食品" name="dailyResource"></el-checkbox>
        <el-checkbox label="帐篷" name="dailyResource"></el-checkbox>
        <el-checkbox label="车位" name="dailyResource"></el-checkbox>
        <el-checkbox label="船位" name="dailyResource"></el-checkbox>
      </el-checkbox-group>
    </el-form-item>
    <el-form-item label="备注信息">
      <el-input v-model="editForm.comment"></el-input>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="onSubmit">确认修改</el-button>
      <el-button @click="backToHome">取消</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
import {editHelp, updateHelp} from "../../../api/call_help";

export default {
  name: "help_edit",
  data() {
    return {
      showTable: false,
      editForm: {
        longitude: localStorage.getItem("longitude"),
        latitude: localStorage.getItem("latitude"),
        peopleNum: '',
        phone: '',
        deadline: '',
        medicalResource: ["口罩", "绷带"],
        dailyResource: [],
        comment: ''
      },
      callRules: {
        peopleNum: [
          {required: true, message: '请输入数字', trigger: 'blur'},
          // {min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur'}
        ],
        phone: [{required: true, message: '请输入联系电话', trigger: 'blur'}],
        deadline: [
          {required: true, message: '请选择日期', trigger: 'change'}
          // {type: 'date', required: true, message: '请选择日期', trigger: 'change'}
        ]
      }//rules
    }
  },
  mounted() {
    return new Promise((resolve, reject) => {
      editHelp(localStorage.getItem("edit_cid")).then(response => {
        ////////////////////////////////////////////////////////////////
        console.log(response.data)
        this.editForm = response.data
        //"TypeError: this.model is undefined"与数据的预加载
        this.showTable = true;
        // alert(response.data.dailyResource)
        // var arrDR=response.data.dailyResource.split(",");
        // var newDR = [];
        // for(var i=0;i<arrDR.length;i++){
        //   newDR[i]='"'+arrDR[i]+'"';
        // }
        // alert(newDR)
        ////////////////////////////////////////////////////////////////
        this.editForm.dailyResource = response.data.dailyResource.split(",");
        this.editForm.medicalResource = response.data.medicalResource.split(",");
        //问题：要学会区分js中的==和===
        if(this.editForm.dailyResource=="---"){
          this.editForm.dailyResource=[]
        }
        if(this.editForm.medicalResource=="---"){
          this.editForm.medicalResource=[]
        }
        // for(var one in response.data.medicalResource.split(",")){
        //   alert(one)
        // }
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },
  methods: {
    onSubmit() {
      this.$refs.editForm.validate(valid => {
        if (valid) {
          console.log(this.editForm)
          this.editForm.dailyResource=this.editForm.dailyResource.toString();
          this.editForm.medicalResource=this.editForm.medicalResource.toString();
          if(this.editForm.dailyResource==""){
            this.editForm.dailyResource="---"
          }
          if(this.editForm.medicalResource=="---"){
            this.editForm.medicalResource="---"
          }
          updateHelp(this.editForm);
          //
          this.$message({
            showClose: true,
            message: '修改成功',
            type: 'success'
          });
          //返回刚刚的主页
          this.$router.push({path: '/home'});
        }
      })
    },
    backToHome(){
      //返回刚刚的主页
      localStorage.setItem("helpDetails",[])
      this.$router.push({path: '/home'}).then(() => {
      }).catch(err => {
      })
    }
  }
}
</script>

<style scoped>

</style>
