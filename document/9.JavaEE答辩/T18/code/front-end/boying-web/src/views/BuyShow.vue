<template>
  <div>
    <el-card v-loading="loading">
      <el-backtop :bottom="60" :right="60"> </el-backtop>
      <el-card
        style="width: 80%; margin: auto"
        :body-style="{ padding: '30px' }"
      >
        <el-row :gutter="20">
          <el-col :span="8">
            <div>
              <img width="300" height="430" :src="show.poster" class="image" />
            </div>
          </el-col>
          <el-col :span="16">
            <div class="text">
              <div class="showName">
                <h2>{{ show.name }}</h2>
              </div>
              <br />
              <div
                class="minor-text"
                v-if="show.dayStart != undefined && show.dayEnd != undefined"
              >
                <div class="showAddress">
                  <i
                    class="myicon myiconchengshi"
                    style="padding-right: 7px"
                  ></i
                  >演出城市:{{ show.city }}
                </div>
                <br /><i
                  class="myicon myiconshijian"
                  style="padding-right: 3px"
                ></i>
                演出时间:{{ show.dayStart.substring(0, 10) }}~{{
                  show.dayEnd.substring(0, 10)
                }}
              </div>
              <div class="tip">
                <i class="el-icon-info" style="padding-right: 3px"></i>
                演出时间和场次时间均为演出当地时间
              </div>
              <br />
              <div>
                <i class="myicon myiconchangci"></i>
                场次
                <el-radio-group
                  v-model="sessionSelected"
                  @change="sessionChange()"
                  class="sessionGroup"
                  v-for="session in sessionList"
                  :key="session.showSessionId"
                >
                  <el-radio-button
                    :label="session.showSessionId"
                    class="sessionRadioButton"
                    >{{ session.startTime | formatDateTime }}-{{
                      session.endTime | formatDateTime
                    }}</el-radio-button
                  >
                  <br />
                </el-radio-group>
              </div>
              <!-- <div class="fundText">¥基础票价:{{ show.minPrice }}</div> -->
              <div v-show="this.classList != [] && this.classList.length != 0">
                <div class="fundText">
                  <i
                    class="myicon myiconpiaozhong"
                    style="padding-right: 3px"
                  ></i
                  >票种
                </div>
                <el-radio-group
                  v-model="showClassSelected"
                  @change="classChange()"
                  class="classGroup"
                  v-for="showclass in classList"
                  :key="showclass.showClassId"
                >
                  <el-radio-button
                    :label="showclass.showClassId"
                    class="classRadioButton"
                    >{{ showclass.name }} 票价:{{ showclass.price }} 票量：{{
                      showclass.stock
                    }}
                    / {{ showclass.capacity }}
                  </el-radio-button>
                  <br />
                </el-radio-group>
                <div v-show="this.priceSelected != null" class="finalPrice">
                  最终价格: ¥{{ this.priceSelected }}
                </div>
              </div>

              <div class="Buy" v-show="this.priceSelected != null">
                <el-button
                  type="danger"
                  icon="myicon myicontubiaozhizuomoEban"
                  @click="buyTicket"
                >
                  购票</el-button
                >
              </div>
            </div>
          </el-col>
        </el-row>
      </el-card>
      <br />
      <el-card style="width: 80%; margin: auto">
        <h1><i class="myicon myiconxiangqing"></i>演出详情</h1>
        <div>{{ this.show.details }}</div>
      </el-card>
    </el-card>
    <el-dialog
      title="提示"
      :visible.sync="dialogVisible"
      width="30%"
      :before-close="handleClose"
    >
      <span>请选择支付方式</span>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="buyTicketByAli">支付宝支付</el-button>
        <el-button type="primary" @click="buyTicketByWechat"
          >微信支付</el-button
        >
      </span>
    </el-dialog>
  </div>
</template>

<script>
import axios from "axios";
import { formatDate } from "@/utils/date";
export default {
  name: "",
  props: [""],
  data() {
    return {
      loading: true,
      showId: "",
      show: {},
      sessionList: [],
      sessionSelected: "",
      classList: [],
      showClassSelected: "",
      priceSelected: null,
      finalPrice: null,
      currentUser: {},
      dialogVisible: false,
    };
  },

  async created() {
    this.showId = this.$route.query.showId;
    await this.getShow();
    await this.getShowSession();
    await this.getShowClass();
    await this.getUser();
    console.log(this.showId);
    setTimeout(() => {
      this.loading = false;
    }, 500);
    console.log(this.classList);
  },

  components: {},

  computed: {},

  beforeMount() {},

  mounted() {},

  methods: {
    sessionChange() {
      console.log(this.sessionSelected);
      this.loading = true;
      this.getShowClass();
      setTimeout(() => {
        this.loading = false;
      }, 500);
    },
    classChange() {
      console.log(this.showClassSelected);
      for (var i of this.classList) {
        if (i.showClassId == this.showClassSelected) {
          this.priceSelected = i.price;
        }
      }
      // this.priceSelected = this.classList[this.showClassSelected - 1].price;
      this.finalPrice = this.priceSelected;
    },
    async getShow() {
      try {
        const res = await axios.post(
          this.$api.getShowDetails + "/" + this.showId
        );
        console.log(res);
        if (res.data.code === 200) {
          this.show = res.data.data;
        }
      } catch (err) {
        console.log(err);
        this.$message.error("获取演出信息失败");
      }
    },

    async getShowSession() {
      try {
        const res = await axios.post(this.$api.getShowSessionUrl, {
          showId: this.showId,
          pageNum: 1,
          pageSize: 10,
        });
        console.log(res);
        if (res.data.code === 200) {
          this.sessionList = res.data.data.list;
          this.sessionSelected = this.sessionList[0].showSessionId;
        }
      } catch (err) {
        console.log(err);
        this.$message.error("获取演出场次信息失败");
      }
    },

    async getShowClass() {
      try {
        const res = await axios.post(this.$api.getShowClassUrl, {
          sessionId: this.sessionSelected,
          pageNum: 1,
          pageSize: 10,
        });
        console.log(res);
        if (res.data.code === 200) {
          this.classList = res.data.data.list;
          this.showClassSelected = this.classList[0].showClassId;
          this.priceSelected = this.classList[0].price;
          this.finalPrice = this.priceSelected;
        } else {
          this.classList = [];
          this.showClassSelected = null;
        }
      } catch (err) {
        console.log(err);
        this.$message.error("获取演出票种失败");
      }
    },

    async getUser() {
      try {
        const res = await axios.post(this.$api.getUserUrl);
        console.log(res);
        if (res.data.code === 200) {
          this.currentUser = res.data.data;
        } else {
          this.currentUser.userId = "";
        }
      } catch (err) {
        console.log(err);
        this.$message.error("获取用户信息失败");
      }
    },

    async buyTicket() {
      this.dialogVisible = true;
      //   try {
      //     const res = await axios.post(this.$api.buyTicketUrl, {
      //       showClassIds: [this.showClassSelected],
      //       showSessionId: this.sessionSelected,
      //     });
      //     console.log(res);
      //     if (res.data.code === 200) {
      //       this.$message.success("购票成功!可以前往订单界面查看订单");
      //     } else {
      //       this.$message.warning("已经购买过该场次的票了，不允许多次抢票");
      //     }
      //   } catch (err) {
      //     console.log(err);
      //     this.$message.error("因未知错误购票失败");
      //   }
    },
    async buyTicketByAli() {
      try {
        const res = await axios.post(this.$api.buyTicketUrl, {
          showClassIds: [this.showClassSelected],
          showSessionId: this.sessionSelected,
          payment: "支付宝",
        });
        console.log(res);
        if (res.data.code === 200) {
          this.$message.success("购票成功!可以前往订单界面查看订单");
          await this.getShow();
          await this.getShowSession();
          await this.getShowClass();
          await this.getUser();
          this.dialogVisible = false;
          return true;
        } else {
          this.$message.warning("已经购买过该场次的票了，不允许多次抢票");
          return false;
        }
      } catch (err) {
        console.log(err);
        this.$message.error("因未知错误购票失败");
        return false;
      }
    },
    async buyTicketByWechat() {
      try {
        const res = await axios.post(this.$api.buyTicketUrl, {
          showClassIds: [this.showClassSelected],
          showSessionId: this.sessionSelected,
          payment: "微信",
        });
        console.log(res);
        if (res.data.code === 200) {
          this.$message.success("购票成功!可以前往订单界面查看订单");
          this.dialogVisible = false;
          return true;
        } else {
          this.$message.warning("已经购买过该场次的票了，不允许多次抢票");
          return false;
        }
      } catch (err) {
        console.log(err);
        this.$message.error("因未知错误购票失败");
        return false;
      }
    },
  },

  watch: {},

  filters: {
    formatDateTime(time) {
      if (time == null || time === "") {
        return "N/A";
      }
      let date = new Date(time);
      return formatDate(date, "yyyy-MM-dd hh:mm:ss");
    },
  },
};
</script>
<style scoped>
.firstCard {
  margin-left: 230px;
  margin-right: 230px;
}

.image {
  float: left;
}

.text {
  float: right;
  padding-right: 200px;
}

.minor-text {
  color: #909399;
}

.tip {
  padding-top: 20px;
  color: #c0c4cc;
  font-size: 5px;
}

.sessionRadioButton {
  margin: 10px;
}

.el-radio-button {
  margin-top: 10px;
  margin-bottom: 10px;
}

.el-radio-group {
  display: flex;
  flex-flow: row nowrap;
  justify-content: space-around;
  margin-right: 120px;
}

.classGroup {
  margin-right: 290px;
}

.fundText {
  color: #606266;
  margin-top: 10px;
}

.finalPrice {
  color: #f56c6c;
  font-size: 20px;
  margin: 10px;
}

.Buy {
  margin: 30px;
}

.myiconxiangqing {
  font-size: 25px;
  padding-right: 5px;
}
</style>