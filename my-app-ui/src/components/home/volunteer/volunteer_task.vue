<template>
  <div class="task_div">
    <div style="margin-top: 20px;text-align: left">
      <!--      <el-button @click="toggleSelection([tableData[1], tableData[2]])">切换第二、第三行的选中状态</el-button>-->
      <!--      <el-button @click="toggleSelection()">取消选择</el-button>-->
      <!--      <el-button @click="deleteSelectItem()">批量删除</el-button>-->
    </div>
    <el-table
      ref="filterTable"
      :data="tableData.slice((currentPage-1)*pageSize,currentPage*pageSize)"
      style="width: 100%">
      <el-table-column
        prop="state"
        label="状态"
        sortable
        width="100">
        <!--        :filters="[{ text: '待救援', value: '待救援' }, { text: '已救援', value: '已救援' }]"-->
        <!--        :filter-method="filterTag"-->
        <!--      filter-placement="bottom-end">-->
        <template slot-scope="scope">
          <el-tag
            :type="scope.row.state === '已完成' ? 'success' : 'danger'"
            disable-transitions>{{ scope.row.state }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="常用操作" width="180">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="success"
            @click="showPosition(scope.$index, scope.row)">位置
          </el-button>
          <el-button
            size="mini"
            @click="changeState(scope.$index, scope.row)">标记状态
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
      <!--      <el-table-column>-->
      <!--        prop="longitude"-->
      <!--        label="经度约数"-->
      <!--        width="165">-->
      <!--      </el-table-column>-->
      <!--      <el-table-column-->
      <!--        prop="latitude"-->
      <!--        label="纬度约数"-->
      <!--        width="165">-->
      <!--      </el-table-column>-->
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
      <el-table-column label="谨慎操作" width="220">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="danger"
            @click="delTask(scope.$index, scope.row)">删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <div class="page_div">
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
    <el-dialog title="标记状态" :visible.sync="dialogFormVisible">
      <el-form :model="form">
        <el-form-item label="状态" :label-width="formLabelWidth">
          <el-select v-model="form.newState" placeholder="请选择状态">
            <el-option label="待救援" value="待救援"></el-option>
            <el-option label="已完成" value="已完成"></el-option>
            <el-option label="救援中" value="救援中"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="handleChangeState">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>

import {showHelp, delHelp} from "../../../api/call_help";
import {formatTime, parseTime} from "../../../utils/get_time";
import {changeHelpState, myVolunteerTask} from "../../../api/volunteer";

export default {
  name: "volunteer_task",
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
      pageSize: 8, //每页多少条
      currentPage: 1, // 当前页
      dialogFormVisible: false,
      form: {
        name: '',
        newState: '',
        delivery: false,
        type: [],
        resource: '',
        desc: ''
      },
      formLabelWidth: '120px'
    }
  },
  //第一次进入时执行一次
  mounted() {
    return new Promise((resolve, reject) => {
      myVolunteerTask().then(response => {
        for (var i = 0; i < response.data.length; i++) {
          response.data[i].deadline = parseTime(response.data[i].deadline)
        }
        this.tableData = response.data
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
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
    showPosition(index, row) {
      //把经纬度传过去
      this.$router.push({
        name: "volunteer_findPosition",
        params: {task_longitude: row.longitude, task_latitude: row.latitude}
      });
    },
    delTask(index, row) {
      console.log(index, row);
      console.log("点击了删除");
      //【后端】删除求助信息
      delHelp(row.cid);
      this.$message.success("删除成功！")
    },
    changeState(index, row) {
      console.log(index, row);
      console.log("点击了标记");
      localStorage.setItem("mark_row_cid", row.cid)
      this.dialogFormVisible = true
    },
    handleChangeState() {
      console.log(localStorage.getItem("mark_row_cid"))
      if (this.form.newState == "") {
        this.$message.info("必须选择状态才能修改！")
      } else {
        alert(this.form.newState)
        changeHelpState({cid: localStorage.getItem("mark_row_cid"), state: this.form.newState}).then(response => {
          //根据cid来更新前端的值
          for (var i = 0; i < this.tableData.length; i++) {
            if (this.tableData[i].cid == localStorage.getItem("mark_row_cid")) {
              this.tableData[i].state = this.form.newState;
              if(this.tableData[i].state==="待救援"){
                //写页面更新的代码：就是mounted的代码，最好抽出一个单独的方法
                myVolunteerTask().then(response => {
                  for (var i = 0; i < response.data.length; i++) {
                    response.data[i].deadline = parseTime(response.data[i].deadline)
                  }
                  this.tableData = response.data
                  resolve()
                }).catch(error => {
                  reject(error)
                })
                //写页面更新的代码：就是mounted的代码，最好抽出一个单独的方法
              }
            }
          }
        })
        this.$message.success("修改成功！")
        this.dialogFormVisible = false
      }
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

.task_div {
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
