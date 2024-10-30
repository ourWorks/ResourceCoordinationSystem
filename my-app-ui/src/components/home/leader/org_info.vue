<template>
  <el-main>
    <el-form :model="form" :rules="callRules" ref="form" label-width="80px" size="mini">
      <el-form-item label="组织号">
        <el-input v-model="form.orgId" :disabled="true"></el-input>
      </el-form-item>
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
        <el-button type="primary" @click.native.prevent="onSubmit">修改</el-button>
<!--        <el-button>取消</el-button>-->
      </el-form-item>
    </el-form>
  </el-main>
</template>

<script>
import {addOrg} from "../../../api/leader";
import {setLocalOrg, updateOrg} from "../../../api/leader";
import {orgInfo} from "../../../api/volunteer";

export default {
  name: "org_info",
  mounted() {
    //记录一下组织信息
    orgInfo({orgId:localStorage.getItem("l_orgId")}).then(response => {
      setLocalOrg(response)
      this.form=response.data
    })
  },
  data() {
    return {
      form: {
        longitude: localStorage.getItem("org_longitude"),
        latitude: localStorage.getItem("org_latitude"),
        orgName: localStorage.getItem("org_orgName"),
        address: localStorage.getItem("org_address"),
        //用来修改信息时用的
        orgId: localStorage.getItem("l_orgId"),
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
            updateOrg(this.form).then(response=>{
              if(response.data!=null){
                setLocalOrg(response)
                this.$message.success("成功修改组织信息！")
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
