<template>
  <div>
    <el-card class="operate-container" shadow="never" style="text-align: left">
      <i class="el-icon-tickets"></i>
      <span>数据列表</span>
    </el-card>
    <div class="table-container">
      <el-table
        :key="key"
        :data="
          tableData.filter(
            (data) =>
              !search ||
              data.realStatus.toLowerCase().includes(search.toLowerCase())
          )
        "
        v-loading="loading"
        style="width: 100%"
      >
        <!-- <el-table-column type="expand">
          <template slot-scope="scope">
            <el-form
              label-position="left"
              inline
              class="demo-table-expand"
              v-for="fruit in formThead"
              :key="fruit.prop"
              :width="fruit.width"
              show-overflow-tooltip
            >
            <el-form-item >
              <span>{{scope.row[fruit.label]}}</span>
              <span>{{ scope.row[fruit.prop] }}</span>
            </el-form-item>
            </el-form>
          </template>
        </el-table-column> -->
        <el-table-column type="expand">
          <template slot-scope="props">
            <el-form label-position="left" inline class="demo-table-expand">
              <el-form-item label="订单编号:">
                <span>{{ props.row.orderId }}</span>
              </el-form-item>
              <el-form-item label="用户账号:">
                <span>{{ props.row.userId }}</span>
              </el-form-item>
              <el-form-item label="演出编号:">
                <span>{{ props.row.showId }}</span>
              </el-form-item>
              <el-form-item label="订单状态:">
                <span>{{ props.row.realStatus }}</span>
              </el-form-item>
              <el-form-item label="订单提交时间:">
                <span>{{ props.row.time | formatDateTime }}</span>
              </el-form-item>
              <el-form-item label="订单总金额:">
                <span>￥{{ props.row.money }}</span>
              </el-form-item>
              <el-form-item label="演出场次编号:">
                <span>{{ props.row.showSessionId }}</span>
              </el-form-item>
              <el-form-item label="订单支付方式:">
                <span>{{ props.row.payment }}</span>
              </el-form-item>
              <el-form-item label="该订单对用户是否可见:">
                <span>{{ props.row.userDelete | formatUserDelete }}</span>
              </el-form-item>
              <el-form-item label="订单所含票数:">
                <span>{{ props.row.ticketCount }}</span>
              </el-form-item>
            </el-form>
          </template>
        </el-table-column>
        <el-table-column label="订单编号" prop="orderId"> </el-table-column>
        <el-table-column label="用户账号" prop="userId"> </el-table-column>
        <el-table-column label="演出编号" prop="showId"> </el-table-column>
        <el-table-column label="订单状态" prop="realStatus"> </el-table-column>
        <el-table-column label="订单提交时间" width="160" align="center">
          <template slot-scope="scope">{{
            scope.row.time | formatDateTime
          }}</template>
        </el-table-column>

        <el-table-column label="订单总金额" prop="money" align="center"> </el-table-column>
        <el-table-column align="center">
          <template slot="header" slot-scope="scope">
            <el-input
              v-model="search"
              size="mini"
              placeholder="按订单状态关键字搜索"
            />
          </template>
          <template slot-scope="scope">
            <el-button
              size="mini"
              type="danger"
              @click="Delete(scope.$index, scope.row)"
              >删除</el-button
            >
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import api from "@/assets/api.js";
import qs from "qs";
import { formatDate } from "@/utils/date";

const fields = [
  { label: "订单编号", prop: "orderId" },
  { label: "用户账号", prop: "userId" },
  { label: "演出编号", prop: "showId" },
  { label: "订单状态编号", prop: "status" },
  { label: "订单状态", prop: "realStatus" },
  { label: "订单提交时间", prop: "time" },
  { label: "订单总金额", prop: "money" },

  //展开行功能多出的内容
  { label: "演出场次编号", prop: "showSessionId" },
  { label: "订单支付方式", prop: "payment" },
  { label: "用户是否已经删除了该订单", prop: "userDelete" },
  { label: "订单所含票数", prop: "ticketCount" },
];

export default {
  name: "",
  props: [""],
  data() {
    return {
      tableData: [],
      search: "",
      key: 1, // table key
      formThead: fields, // 默认表头 Default header
      loading: true,
      OrderState: ["待观看", "已完成", "已退订单"],
    };
  },

  components: {},

  computed: {},

  beforeMount() {},

  mounted() {
    this.reload();
  },

  methods: {
    Delete(index,row) {
      this.$confirm("此操作将删除该订单, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          this.handleDelete(row.orderId)
        }).catch(() => {
          this.$message.info("已取消删除");
        });
    },
    async handleDelete(id) {
      try {
        console.log("mounted");
        const res = await axios.post(`${api.API_URL}/user/deleteOrder/` + id);
        console.log("test");
        console.log(res);
        if (res.data.code == 200) {
          this.$message.success("删除成功");
          this.reload();
        }
      } catch (err) {
        console.log(err);
      }
    },

    async reload() {
      try {
        console.log("mounted");
        const res = await axios.get(`${api.API_URL}/user/listOrders`, {
          headers: {
            Authorization: "Bearer " + sessionStorage.getItem("token"),
          },
        });
        console.log(res);
        if(res.data.message == "不存在任何订单"){
          this.tableData=[];
        }
        if (res.data.code == 200) {
          this.tableData = res.data.data;
          for (var i = 0; i < this.tableData.length; i++) {
            if (this.tableData[i].status == 1) {
              this.tableData[i].realStatus = "待观看";
            } else if (this.tableData[i].status == 2) {
              this.tableData[i].realStatus = "已完成";
            } else {
              this.tableData[i].realStatus = "已退订单";
            }
          }
        }
        setTimeout(() => {
          this.loading = false;
        }, 500);
      } catch (err) {
        console.log(err);
      }
    },
  },
  filters: {
    formatDateTime(time) {
      if (time == null || time === "") {
        return "N/A";
      }
      let date = new Date(time);
      return formatDate(date, "yyyy-MM-dd hh:mm:ss");
    },

    formatUserDelete(value){
      if(value==true){
        return "用户已删除";
      }
      else{
        return "用户未删除";
      }
    }
  },

  watch: {},
};
</script>

<style scoped>
.demo-table-expand {
  font-size: 0;
}
/* 这个css未生效 也可能这三个都未生效*/
.demo-table-expand .label {
  width: 90px;
  color: #99a9bf;
}
.demo-table-expand .el-form-item {
  margin-right: 0;
  margin-bottom: 0;
  width: 50%;
}
</style>