<template>
  <div>
    <div style="margin-top: 20px;text-align: center">
      <!--      <el-button @click="toggleSelection([tableData[1], tableData[2]])">切换第二、第三行的选中状态</el-button>-->
      <el-button @click="toggleSelection()">取消选择</el-button>
      <el-button @click="deleteSelectItem()">批量删除</el-button>
      <el-button @click="dialogFormVisible = true">添加新领导</el-button>
    </div>
    <!--      max-height="100%" 是下面table标签的一个独立属性-->
    <!--    ref="filterTable"       :data="tableData"-->
    <el-table
      ref="filterTable"
      :data="tableData.slice((currentPage-1)*pageSize,currentPage*pageSize)"
      style="width: 100%"
      @selection-change="handleSelectionChange">
      <el-table-column
        type="selection"
        width="55">
      </el-table-column>
<!--      <el-table-column-->
<!--        fixed-->
<!--        prop="id"-->
<!--        label="市民号"-->
<!--        width="180">-->
<!--      </el-table-column>-->
      <el-table-column
        prop="name"
        sortable
        label="姓名"
        width="120">
      </el-table-column>
      <el-table-column
        prop="gender"
        sortable
        label="性别"
        width="120">
      </el-table-column>
      <el-table-column
        prop="age"
        sortable
        label="年龄"
        width="120">
      </el-table-column>
      <el-table-column
        prop="email"
        label="邮箱"
        width="190">
      </el-table-column>
      <el-table-column
        prop="phone"
        label="手机号"
        width="190">
      </el-table-column>
      <el-table-column
        prop="idCard"
        label="身份证号码"
        width="220">
      </el-table-column>
      <el-table-column
        fixed="right"
        label="操作"
        width="120">
        <template slot-scope="scope">
          <el-button
            @click.native.prevent="deleteRow(scope.$index, tableData)"
            type="danger"
            size="small">
            删除
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
        :page-sizes="[8, 16, 24, 32, 100]"
        :page-size="pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="tableData.length"
      ></el-pagination>
    </div>
    <!--打开增加新领导的表单-->
    <el-dialog title="添加组织的领导" :visible.sync="dialogFormVisible">
      <el-form :model="addLeaderForm" :ref="addLeaderForm">
        <el-form-item label="输入市民号" :label-width="formLabelWidth"
                      :rules="[
          { required: true, message: '市民id不能为空'},
          { type: 'number', message: '市民id必须为数字值'}
    ]"
        >
          <el-input v-model="addLeaderForm.id" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click.native.prevent="addLeader">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {addLeader, getOrgLeaders, quitOrgAsLeader} from "../../../api/leader";

export default {
  name: "manage_leader",
  data() {
    return {
      tableData: [{
        id: '',
        name: '',
        gender: '',
        age: '',
        email: '',
        phone: '',
        idCard: ''
      }],
      addLeaderForm: {id: '', orgId: localStorage.getItem("l_orgId")},
      dialogFormVisible: false,
      formLabelWidth: '120px',
      pageSize: 8, //每页多少条
      currentPage: 1, // 当前页
    }
  },
  mounted() {
    getOrgLeaders({orgId: localStorage.getItem("l_orgId")}).then(response => {
      this.tableData = response.data
      console.log("%%%%%%%%%%%%")
      console.log(this.tableData)
    })
  },
  methods: {
    deleteRow(index, rows) {
      console.log(index, rows)
      console.log(rows[index].id)
      quitOrgAsLeader({id: rows[index].id, orgId: localStorage.getItem("l_orgId")}).then(response => {
        this.$message.success("成功删除该领导")
      })
      //前端删除
      rows.splice(index, 1);
    },
    //后端批量删除，这里直接调用一个一个删除的方法，在生产环境中不算好办法，但是现在的数据量还比较小，区别不太大
    deleteSelectd() {
      // alert("Hi")
      // console.log(this.multipleSelection)
      for (var i = 0; i < this.multipleSelection.length; i++) {
        console.log(this.multipleSelection[i].name)
        quitOrgAsLeader({id: this.multipleSelection[i].id, orgId: localStorage.getItem("l_orgId")}).then(response => {
          //  前端删除成功
        })
      }
      // console.log(this.multipleSelection.index)
      // console.log(this.multipleSelection[0].name)
    },
    //批量删除，前端的用法
    deleteSelectItem() {
      this.$confirm("此操作将删除勾选的领导,是否继续?（特别注意，全选按钮是选择所有页面的数据）", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          //deleteSelectd就是使用for循环删除后端的数据
          this.deleteSelectd();
          //遍历获得多选选中的index值
          this.multipleSelection.forEach((value, index) => {
            //遍历多选框获取的数据
            this.tableData.forEach((v, i) => {
              //遍历数据表，任意一个属性值相同，则说明该数据被选中，其i则为索引值
              if (value.id == v.id) {
                this.tableData.splice(i, 1);
              }
            });
          });
          this.$message({
            type: "success",
            message: "删除成功!",
          });
          console.log("删除成功");
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除",
          });
        });
    },
    toggleSelection(rows) {
      if (rows) {
        rows.forEach(row => {
          this.$refs.filterTable.toggleRowSelection(row);
        });
      } else {
        this.$refs.filterTable.clearSelection();
      }
    },
    handleSelectionChange(val) {
      this.multipleSelection = val;
    },
    addLeader() {
      // this.$refs.addLeaderForm.validate(valid => {
      //   if (valid) {
      //   }
      // })
      //上面的校验不生效
      if (this.addLeaderForm.id == "") {
        this.$message.warning("市民号不能为空")
      } else if (this.addLeaderForm.id.replace(/[^\d]/g, '').length != this.addLeaderForm.id.length) {
        this.$message.warning("市民号只能为纯数字")
      } else {
        addLeader(this.addLeaderForm).then(response => {
          if (response.data != null) {
            this.$message.success("成功添加新领导！")
            //push是推，引申为入栈；pop是出栈
            this.tableData.push(response.data)
            this.dialogFormVisible = false
          }
        })
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
.page_div {
  width: 100%;
  position: center;
  padding: 20px 0 0 0;
  margin: 0 auto;
  text-align: center;
  bottom: 0;
}

</style>
