<template>
  <el-main>
    <el-page-header @back="goBack" content="步骤2 填写组织信息"></el-page-header>
    <el-form :model="form" :rules="callRules" ref="form" label-width="80px" size="mini">
      <el-form-item label="经度">
        <el-input v-model="form.longitude" :disabled="true"></el-input>
      </el-form-item>
      <el-form-item label="纬度">
        <el-input v-model="form.latitude" :disabled="true"></el-input>
      </el-form-item>
      <el-form-item label="组织名称" prop="orgName">
        <el-input v-model="form.orgName"></el-input>
      </el-form-item>
      <el-form-item label="组织地址" prop="address">
        <el-input v-model="form.address"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click.native.prevent="onSubmit">确认创建</el-button>
        <el-button>取消</el-button>
      </el-form-item>
    </el-form>
  </el-main>
</template>

<script>
import {addOrg} from "../../../api/leader";

export default {
  name: "add_org",
  mounted() {
    // alert(this.$route.params.prePath)
    // alert(this.$route.params.destinationPath)
  },
  data() {
    return {
      form: {
        longitude: localStorage.getItem("longitude"),
        latitude: localStorage.getItem("latitude"),
        orgName: '',
        address: '',
      },
      callRules: {
        orgName: [
          {required: true, message: '组织名称', trigger: 'blur'},
        ],
        address: [{required: true, message: '请输入组织地址', trigger: 'blur'},
          // {min: 7, max: 11, message: '长度在 7 到 11 个字符', trigger: 'blur'}
        ],
      }
    }
  },
  methods:
    {
      goBack() {
        this.$router.push({path:this.$route.params.prePath});
      },
      onSubmit() {
        this.$refs.form.validate(valid => {
          if (valid) {
            console.log(this.form)
            addOrg(this.form).then(response=>{
              if(response.data!=null){
                this.$message.success("您已经成功创建组织！组织号是"+response.data.orgId)
              }
            })
          }
        })
      }
    }
}
</script>

<style scoped>

</style>
