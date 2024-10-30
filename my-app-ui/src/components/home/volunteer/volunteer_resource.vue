<template>
  <!--  <div style="width: fit-content;height: fit-content">-->
  <el-row>
    <!--表头一定要写ref，否则校验不生效，每个item必须要加prop，否则不生效-->
    <el-col :span="12">
      <h5 style="text-align: center">医疗资源</h5>
      <el-form size="mini" :model="medicalForm" ref="medicalForm" label-width="100px">
        <el-form-item
          label="口罩"
          prop="mask"
          :rules=numberRules
        >
          <el-input type="mask" v-model.number="medicalForm.mask" style="width: 55%" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item
          label="绷带"
          prop="bandage"
          :rules=numberRules>
          <el-input type="bandage" v-model.number="medicalForm.bandage" style="width: 55%"
                    autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item
          label="担架"
          prop="stretcher"
          :rules=numberRules>
          <el-input type="stretcher" v-model.number="medicalForm.stretcher" style="width: 55%"
                    autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item
          label="降压药"
          prop="depressor"
          :rules=numberRules>
          <el-input type="depressor" v-model.number="medicalForm.depressor" style="width: 55%"
                    autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item
          label="抗过敏药"
          prop="antihistamine"
          :rules=numberRules>
          <el-input type="antihistamine" v-model.number="medicalForm.antihistamine" style="width: 55%"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click.native.prevent="submitMR">修改</el-button>
          <!--        <el-button>取消</el-button>-->
        </el-form-item>
      </el-form>
    </el-col>
    <el-col :span="12">
      <h5 style="text-align: center">日用资源</h5>
      <el-form size="mini" :model="dailyForm" ref="dailyForm" label-width="100px">
        <el-form-item
          label="饮用水"
          prop="water"
          :rules=numberRules
        >
          <el-input type="water" v-model.number="dailyForm.water" style="width: 55%" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item
          label="充饥食品"
          prop="food"
          :rules=numberRules>
          <el-input type="food" v-model.number="dailyForm.food" style="width: 55%"
                    autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item
          label="帐篷"
          prop="tent"
          :rules=numberRules>
          <el-input type="tent" v-model.number="dailyForm.tent" style="width: 55%"
                    autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item
          label="车位"
          prop="car"
          :rules=numberRules>
          <el-input type="car" v-model.number="dailyForm.car" style="width: 55%"
                    autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item
          label="船位"
          prop="boat"
          :rules=numberRules>
          <el-input type="boat" v-model.number="dailyForm.boat" style="width: 55%"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click.native.prevent="submitDR">修改</el-button>
          <!--        <el-button>取消</el-button>-->
        </el-form-item>
      </el-form>
    </el-col>
  </el-row>
  <!--  </div>-->
</template>

<script>
import {
  getDailyResource,
  getMedicalResource,
  setLocalVDR,
  setLocalVMR, updateDailyResource,
  updateMedicalResource
} from "../../../api/volunteer";
import {setLocalOrg, updateOrg} from "../../../api/leader";

export default {
  name: "volunteer_resource",
  mounted() {
    getMedicalResource().then(response=>{
      setLocalVMR(response),
      this.medicalForm=response.data
    }),
    getDailyResource().then(response=>{
      setLocalVDR(response),
      this.dailyForm=response.data
    })
  },
  data() {
    return {
      medicalForm: {
        mask: localStorage.getItem("v_mask"),
        bandage: localStorage.getItem("v_bandage"),
        stretcher: localStorage.getItem("v_stretcher"),
        depressor: localStorage.getItem("v_depressor"),
        antihistamine: localStorage.getItem("v_antihistamine")
      },
      dailyForm:{
        water: localStorage.getItem("v_water"),
        food: localStorage.getItem("v_food"),
        tent: localStorage.getItem("v_tent"),
        car: localStorage.getItem("v_car"),
        boat: localStorage.getItem("v_boat"),
      },
      numberRules: [
        {required: true, message: '数字不能为空'},
        {type: 'number', message: '物资必须为整数值'}
      ],
    }
  },
  methods: {
    submitMR(){
      this.$refs.medicalForm.validate(valid => {
        if (valid) {
          console.log(this.medicalForm)
          updateMedicalResource(this.medicalForm).then(response=>{
            if(response.data!=null){
              setLocalVMR(response)
              this.$message.success("成功修改医疗资源信息！")
            }
          })
        }
      })
    },
    submitDR(){
      this.$refs.dailyForm.validate(valid => {
        if (valid) {
          console.log(this.dailyForm)
          updateDailyResource(this.dailyForm).then(response=>{
            if(response.data!=null){
              setLocalVDR(response)
              this.$message.success("成功修改日用资源信息！")
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
