<template>
  <div style="width:100%;height:100%">
    <div
      style="width: 100%;height:auto;text-align: center;font-family: 'Microsoft YaHei UI',serif;font-size: larger;margin-bottom: 1%">
      设置策略
    </div>
    <div
      style="font-family: 'Times New Roman',serif; font-size: x-small;text-align: center;font-weight: lighter;color:red;margin-bottom: 1%">
      *每次排班最多给志愿者安排一个"待救援"任务，安排之后任务状态变更为“已分配”
    </div>
    <div style="width: 100%;height: 10%">
      <el-row :gutter="24">
        <el-col :span="22" :offset="2">
          <el-form :inline="true" :model="formInline" class="demo-form-inline">
            <el-form-item label="考虑时间">
              <el-select v-model="formInline.time" placeholder="选择条件" style="width: 105px">
                <el-option label="是" value="time"></el-option>
                <el-option label="否" value=""></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="考虑资源">
              <el-select v-model="formInline.resource" placeholder="选择条件" style="width: 105px">
                <el-option label="是" value="resource"></el-option>
                <el-option label="否" value=""></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="考虑位置">
              <el-select v-model="formInline.position" placeholder="选择条件" style="width: 105px">
                <el-option label="5公里内" value="position"></el-option>
                <el-option label="否" value=""></el-option>
              </el-select>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="onSubmit">一键排班</el-button>
            </el-form-item>
            <el-form-item>
              <download-excel
                :data="tableData"
                :fields="json_fields"
                head="排班表"
                type="xls"
                name="排班信息">
                <el-button type="success"> 导出排班信息</el-button>
              </download-excel>
            </el-form-item>
          </el-form>
        </el-col>
      </el-row>
    </div>
    <div style="width: 100%">
      <el-row>
        <el-col :span="22" :offset="1">
          <el-table
            fit
            size="small"
            border
            :data="tableData.filter(data => !search || data.volunteerName.toLowerCase().includes(search.toLowerCase()))
.slice((currentPage-1)*pageSize,currentPage*pageSize)"
            style="width: 100%">
            <el-table-column
              label="志愿者"
              prop="volunteerName">
            </el-table-column>
            <el-table-column
              label="志愿者手机号"
              prop="volunteerPhone">
            </el-table-column>
            <el-table-column
              label="值班状态"
              prop="ondueState">
            </el-table-column>
            <el-table-column
              label="求助市民"
              prop="citizenName">
            </el-table-column>
            <el-table-column
              label="市民手机号"
              prop="citizenPhone">
            </el-table-column>
            <el-table-column
              label="安排救援时间"
              :formatter="dateFormat"
              prop="time">
            </el-table-column>
            <el-table-column
              label="状态"
              prop="state"
              sortable
              width="100">
              <!--        :filters="[{ text: '待救援', value: '待救援' }, { text: '已救援', value: '已救援' }]"-->
              <!--        :filter-method="filterTag"-->
              <!--      filter-placement="bottom-end">-->
              <template slot-scope="scope">
                <el-tag
                  :type="(scope.row.state === '已分配') ? 'success' :((scope.row.state === '救援中')? 'info':'white')"
                  disable-transitions>{{ scope.row.state }}
                </el-tag>
              </template>
            </el-table-column>
            <el-table-column
              align="center">
              <!--              align="right">-->
              <template slot="header" slot-scope="scope">
                <el-input
                  v-model="search"
                  size="small"
                  placeholder="搜志愿者"/>
              </template>
              <template slot-scope="scope">
                <!--                <el-button-->
                <!--                  size="mini"-->
                <!--                  @click="handleEdit(scope.$index, scope.row)">编辑-->
                <!--                </el-button>-->
                <el-button
                  size="mini"
                  type="danger"
                  @click="handleDelete(scope.$index, scope.row)">撤销排班
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
        </el-col>
      </el-row>
    </div>
    <!--使用title和:title的绑定效果是不一样的-->
    <el-dialog :title="addTitle" :visible.sync="dialogFormVisible">
      <el-table
        fit
        size="small"
        border
        :data="addFormData.slice((currentPage-1)*pageSize,currentPage*pageSize)"
        style="width: 100%">
        <el-table-column
          label="志愿者"
          prop="volunteerName">
        </el-table-column>
        <el-table-column
          label="志愿者手机号"
          prop="volunteerPhone">
        </el-table-column>
        <el-table-column
          label="值班状态"
          prop="ondueState">
        </el-table-column>
        <el-table-column
          label="求助市民"
          prop="citizenName">
        </el-table-column>
        <el-table-column
          label="市民手机号"
          prop="citizenPhone">
        </el-table-column>
        <el-table-column
          label="安排救援时间"
          :formatter="dateFormat"
          prop="time">
        </el-table-column>
        <el-table-column
          label="状态"
          prop="state"
          sortable
          width="100">
          <template slot-scope="scope">
            <el-tag
              :type="(scope.row.state !== '') ?'success':''"
              disable-transitions>{{ scope.row.state }}
            </el-tag>
          </template>
        </el-table-column>
      </el-table>
      <div slot="footer" class="dialog-footer">
        <!--        <el-button @click="dialogFormVisible = false">取 消</el-button>-->
        <el-button type="primary" @click="handleChangeState">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {beginArrange, showArrange} from "../../../api/arrange";
import {changeHelpState} from "../../../api/volunteer";

export default {
  data() {
    return {
      tableData: [],
      search: '',
      formInline: {
        time: '',
        resource: '',
        position: ''
      },
      pageSize: 8, //每页多少条
      currentPage: 1, // 当前页
      dialogFormVisible: false,
      //为了导出而定义的信息
      json_fields: {
        '志愿者': 'volunteerName',
        '志愿者手机号': 'volunteerPhone',
        '值班状态': 'ondueState',
        '求助市民': 'citizenName',
        '市民手机号': 'citizenPhone',
        '安排时间': 'time',
        '状态': 'state'
      },
      addFormData: [],
      addTitle: "排班结果"
    }
  },
  mounted() {
    this.updateArrangeForm()
  },
  methods: {
    updateArrangeForm() {
      showArrange({orgId: localStorage.getItem("l_orgId")}).then(response => {
        this.tableData = response.data
      })
    },
    handleEdit(index, row) {
      console.log(index, row);
    },
    handleDelete(index, row) {
      // console.log(index, row);
      console.log(row.cid)
      //和我的任务volunteer_task里面的标记操作一样
      if (row.citizenName == null) {
        this.$message.info("这个志愿者还没有排班！")
      } else {
        changeHelpState({cid: row.cid, state: "待救援"}).then(response => {
          if (response.data != null) {
            this.$message.success("成功撤销排班！")
            //更新一下汇总表格的信息
            this.updateArrangeForm()
          }
        })
      }
    },
    onSubmit() {
      console.log('submit!');
      let strategy = ""
      let chineseStrategy = ""
      if (this.formInline.time != "") {
        strategy = strategy + this.formInline.time;
        chineseStrategy = chineseStrategy + "时间";
      }
      if (this.formInline.resource != "") {
        strategy = strategy + "," + this.formInline.resource
        chineseStrategy = chineseStrategy + "+" + "资源"
      }
      if (this.formInline.position != "") {
        strategy = strategy + "," + this.formInline.position
        chineseStrategy = chineseStrategy + "+" + "5公里内"
      }
      if (strategy.substr(0, 1) == ",") {
        strategy = strategy.substr(1, strategy.length)
        chineseStrategy = chineseStrategy.substr(1, strategy.length)
      }
      console.log(strategy)
      beginArrange({strategy: strategy, orgId: localStorage.getItem("l_orgId")}).then(response => {
        if (response.data != null) {
          this.addTitle = "排班策略【" + chineseStrategy + "】下，新增排班信息：" + response.data.length + "条"
          this.addFormData = response.data
          this.dialogFormVisible = true;
          //更新一下汇总表格的信息
          this.updateArrangeForm()
        } else {
          this.$message.info("当前条件下已无法进行更多排班~")
        }
      })
    },
    handleSelectionChange(val) {
      this.multipleSelection = val;
    },
    // 每页多少条
    handleSizeChange(val) {
      this.pageSize = val;
    },
    // 当前页
    handleCurrentChange(val) {
      this.currentPage = val;
    },
    //时间的格式化
    dateFormat(row, column, cellValue, index) {
      // alert(cellValue)
      if (cellValue != null) {
        return cellValue.split("-")[1] + "-" + cellValue.split("-")[1] + " " + cellValue.split("T")[1]
      } else {
        return null;
      }
    },
    handleChangeState() {
      this.dialogFormVisible = false
    }
  },
}
</script>

<style scoped>
.page_div {
  width: 100%;
  position: center;
  padding: 10px 0 0 0;
  margin: 0 auto;
  text-align: center;
  bottom: 0;
}
</style>
