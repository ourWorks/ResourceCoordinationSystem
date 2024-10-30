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
      <!--        :filters="[{ text: '待救援', value: '待救援' }, { text: '已救援', value: '已救援' }]"-->
<!--        :filter-method="filterTag"-->
<!--      filter-placement="bottom-end">-->
      <template slot-scope="scope">
          <el-tag
            :type="scope.row.state === '待救援' ? 'danger' : 'success'"
            disable-transitions>{{ scope.row.state }}
          </el-tag>
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
      <el-table-column label="操作" width="180">
        <template slot-scope="scope">
          <el-button
            size="mini"
            @click="handleEdit(scope.$index, scope.row)">编辑
          </el-button>
          <el-button
            size="mini"
            type="danger"
            @click="handleDelete(scope.$index, scope.row)">删除
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
  </div>
</template>

<script>

import {showHelp,delHelp} from "../../../api/call_help";
import {formatTime, parseTime} from "../../../utils/get_time";

export default {
  name: "my_call",
  data() {
    return {
      tableData: [{
        deadline: 0,
        longitude: 0,
        latitude: 0,
        peopleNum:'',
        dailyResource: '',
        medicalResource: '',
        state: "",
      }],
      today:parseTime(new Date(),"{y}-{m}-{d}"),
      //用来控制页面的更新，每次只更新一次
      upDateFlag:true,
      pageSize: 8, //每页多少条
      currentPage: 1, // 当前页
    }
  },
  //第一次进入时执行一次
  mounted() {
    return new Promise((resolve, reject) => {
      showHelp().then(response => {
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
      return row[property].slice(0,10) === value;
    },
    updateForm() {
      showHelp().then(response => {
        for (var i = 0; i < response.data.length; i++) {
          response.data[i].deadline = parseTime(response.data[i].deadline)
        }
        this.tableData = response.data;
      })
      this.upDateFlag=false;
    },
    handleEdit(index, row) {
      console.log(index, row);
      console.log("点击了编辑");
      console.log("要编辑的call_help的cid是" + row.cid);
      localStorage.setItem("edit_cid", row.cid);
      this.$router.push({path: '/home/help/call_help_edit'}).then(() => {
      }).catch(err => {
      })
    },
    handleDelete(index, row) {
      console.log(index, row);
      console.log("点击了删除");
      this.$confirm('确定删除此求助？', '确认信息', {
        distinguishCancelAndClose: true,
        confirmButtonText: '删除',
        cancelButtonText: '放弃'
      })
        .then(() => {
          this.$message({
            type: 'success',
            message: '删除成功'
          });
          //【后端】删除求助信息
          delHelp(row.cid);
          {
            //！！！！！！！！！！探究出来的【前端】的删除方法！！！！！！！！！！！！！！！！！！！！
            var arr = document.getElementsByClassName("el-table__row");
            //不要用remove，会发生index索引不更新的错误
            arr[index].style.arrange="none"
          }
          // //更新表格。不知道为什么直接引用那个方法是失效的
          // this.upDateFlag=true;
          // this.updated();
        })
        .catch(action => {
          this.$message({
            type:'info',
            message:'未删除'
          })
          // this.$message({
          //   type: action === 'cancel'
          //     ? 'info'
          //     : 'success',
          //   message: action === 'cancel'
          //     ? '未删除'
          //     : '已删除'
          // })
        });
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
