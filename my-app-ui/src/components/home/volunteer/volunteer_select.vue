<template>
  <div class="help_div">
    <el-table
      ref="filterTable"
      :data="tableData.slice((currentPage-1)*pageSize,currentPage*pageSize)"
      style="width: 100%">
      <el-table-column
        prop="state"
        label="状态"
        sortable
        width="100">
        <template slot-scope="scope">
          <el-tag
            :type="scope.row.state === '待救援' ? 'danger' : 'info'"
            disable-transitions>{{ scope.row.state }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="180">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="success"
            @click="acceptTask(scope.$index, scope.row)">开始救援
          </el-button>
        </template>
      </el-table-column>
      <el-table-column
        prop="deadline"
        label="约定时间"
        sortable
        width="160"
        column-key="date"
        :filters="[{text:'今天', value: today}]"
        :filter-method="filterHandler"
      >
      </el-table-column>
      <el-table-column
        prop="phone"
        label="联系电话"
        width="120">
      </el-table-column>
      <el-table-column
        prop="longitude"
        label="经度约数"
        width="165">
      </el-table-column>
      <el-table-column
        prop="latitude"
        label="纬度约数"
        width="165">
      </el-table-column>
      <el-table-column
        prop="peopleNum"
        label="求助人数"
        width="80" style="text-align: center">
      </el-table-column>
      <el-table-column
        prop="dailyResource"
        label="日用资源"
        width="180">
      </el-table-column>
      <el-table-column
        prop="medicalResource"
        label="医疗资源"
        width="180">
      </el-table-column>
      <el-table-column
        prop="comment"
        label="备注"
        width="180">
      </el-table-column>
    </el-table>
    <div  class="page_div">
      <el-pagination
        background
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="currentPage"
        :page-sizes="[8, 16, 23, 32, 100]"
        :page-size="pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="tableData.length"
      ></el-pagination>
    </div>
  </div>
</template>

<script>
import {parseTime} from "../../../utils/get_time";
import {beginVolunteerTask, getWaitingOrArrangedTask} from "../../../api/volunteer";

export default {
  name: "volunteer_select",
  data() {
    return {
      tableData: [{
        deadline: 0,
        longitude: 0,
        latitude: 0,
        peopleNum: '',
        dailyResource: '',
        medicalResource: '',
        state: "",
      }],
      today: parseTime(new Date(), "{y}-{m}-{d}"),
      //用来控制页面的更新，每次只更新一次
      upDateFlag: true,
      pageSize: 8, //每页多少条
      currentPage: 1, // 当前页
    }
  },
  //第一次进入时执行一次
  mounted() {

    return new Promise((resolve, reject) => {
      getWaitingOrArrangedTask().then(response => {
        for (var i = 0; i < response.data.length; i++) {
          response.data[i].deadline = parseTime(response.data[i].deadline)
        }
        this.tableData = response.data
        // localStorage.setItem("helpDetails",response.data)
        // this.tableData = localStorage.getItem("helpDetails");
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },
  // 后面被push回来时，执行一次,如果使用update()就会失败
  updated() {
    console.log("更新一次")
    //upDateFlag不能写在updated里面
    // var upDateFlag = 1;
    if (this.upDateFlag) {
      console.log("进行表格的更新")
      this.updateForm()
    }
  },
  methods: {
    resetDateFilter() {
      this.$refs.filterTable.clearFilter('date');
    },
    clearFilter() {
      this.$refs.filterTable.clearFilter();
    },
    formatter(row, column) {
      return row.address;
    },
    filterTag(value, row) {
      return row.state === value;
    },
    //筛选日期的方法
    filterHandler(value, row, column) {
      const property = column['property'];
      // alert(row[property].slice(0,10))
      return row[property].slice(0, 10) === value;
    },
    updateForm() {
      getWaitingOrArrangedTask().then(response => {
        for (var i = 0; i < response.data.length; i++) {
          response.data[i].deadline = parseTime(response.data[i].deadline)
        }
        this.tableData = response.data;
      })
      this.upDateFlag = false;
    },
    acceptTask(index, row) {
      console.log(index, row);
      console.log("点击了编辑");
      console.log("要编辑的任务cid是" + row.cid);
      beginVolunteerTask({cid:row.cid,volunteerId: localStorage.getItem("volunteerId")}).then(response=>{
        if(response.data!=null){
          this.$message.success("成功接受任务")
          this.$router.push({path: '/home/volunteer/volunteer_task'})
        }
      })
    },
    // 每页多少条
    handleSizeChange(val) {
      this.pageSize = val;
    },
    // 当前页
    handleCurrentChange(val) {
      this.currentPage = val;
    },
  }
}
</script>

<style scoped>

.help_div {
  height: 100%;
}

.page_div {
  width: 100%;
  position: center;
  padding: 20px 0 0 0;
  margin: 0 auto;
  text-align: center;
  bottom: 0;
}
</style>
