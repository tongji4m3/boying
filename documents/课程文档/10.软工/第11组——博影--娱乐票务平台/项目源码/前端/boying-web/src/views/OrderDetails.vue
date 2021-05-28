<template>
    <div class="detail-container">
        <el-button @click="back">返回订单列表</el-button>
        <el-card shadow="never" style="margin-top: 15px" v-loading="loading">
            <div class="operate-container">
                <i
                        class="el-icon-error color-danger"
                        style="margin-left: 20px"
                        v-show="order.status == 3"
                ></i>
                <i
                        class="el-icon-success color-success"
                        style="margin-left: 20px"
                        v-show="order.status == 2"
                ></i>
                <i
                        class="el-icon-warning color-warning"
                        style="margin-left: 20px"
                        v-show="order.status == 1"
                ></i>
                <span class="color-danger" v-show="order.status == 3"
                >当前订单状态：{{ order.status | formatStatus }}</span
                >
                <span class="color-success" v-show="order.status == 2"
                >当前订单状态：{{ order.status | formatStatus }}</span
                >
                <span class="color-warning" v-show="order.status == 1"
                >当前订单状态：{{ order.status | formatStatus }}</span
                >
                <div
                        class="operate-button-container"
                        v-show="order.status === 2 || order.status === 3"
                >
                    <el-button size="mini" @click="Delete">删除订单</el-button>
                </div>
                <div class="operate-button-container" v-show="order.status === 1">
                    <el-button size="mini" @click="Cancel" type="warning"
                    >取消订单
                    </el-button
                    >
                </div>
            </div>
            <div style="margin-top: 20px">
                <span class="font-small">基本信息</span>
            </div>
            <div class="table-layout">
                <el-row>
                    <el-col :span="6" class="table-cell-title">订单编号</el-col>
                    <el-col :span="6" class="table-cell-title">用户账号</el-col>
                    <el-col :span="6" class="table-cell-title">演出名称</el-col>
                    <el-col :span="6" class="table-cell-title">演出类别</el-col>
                    <!-- <el-col :span="6" class="table-cell-title">观影人编号</el-col> -->
                    <!-- <el-col :span="6" class="table-cell-title">购买票数</el-col> -->
                </el-row>
                <el-row>
                    <el-col :span="6" class="table-cell">{{ orderId }}</el-col>
                    <el-col :span="6" class="table-cell">{{ order.userId }}</el-col>
                    <el-col :span="6" class="table-cell">{{ show.name }}</el-col>
                    <el-col :span="6" class="table-cell">{{ category }}</el-col>
                    <!-- <el-col :span="6" class="table-cell">{{ order.frequentId }}</el-col> -->
                    <!-- <el-col :span="6" class="table-cell">{{ order.ticketCount }}</el-col> -->
                </el-row>
                <el-row>
                    <el-col :span="6" class="table-cell-title">订单提交时间</el-col>
                    <el-col :span="6" class="table-cell-title">票种</el-col>
                    <el-col :span="6" class="table-cell-title">支付方式</el-col>
                    <el-col :span="6" class="table-cell-title">订单总金额/￥</el-col>
                </el-row>
                <el-row>
                    <el-col :span="6" class="table-cell">{{
                        order.time | formatDateTime
                        }}
                    </el-col>
                    <el-col :span="6" class="table-cell">{{
                        isTicket | formatTicket
                        }}
                    </el-col>
                    <el-col :span="6" class="table-cell">
                        {{ order.payment }}
                    </el-col>
                    <el-col :span="6" class="table-cell">{{ order.money }}</el-col>
                </el-row>
            </div>
            <div style="margin-top: 20px" v-show="isTicket">
                <span class="font-small">收货人信息</span>
            </div>
            <div class="table-layout" v-show="isTicket">
                <el-row>
                    <el-col :span="8" class="table-cell-title">收货人</el-col>
                    <el-col :span="8" class="table-cell-title">手机号码</el-col>
                    <el-col :span="8" class="table-cell-title">收货地址</el-col>
                </el-row>
                <el-row>
                    <el-col :span="8" class="table-cell">{{ receiver.receiver }}</el-col>
                    <el-col :span="8" class="table-cell">{{ receiver.phone }}</el-col>
                    <el-col :span="8" class="table-cell">{{ address }}</el-col>
                </el-row>
            </div>

            <div style="margin-top: 20px">
                <span class="font-small">演出信息</span>
            </div>
            <div class="table-layout">
                <el-row>
                    <el-col :span="6" class="table-cell-title">演出城市</el-col>
                    <el-col :span="6" class="table-cell-title">演出地址</el-col>
                    <el-col :span="6" class="table-cell-title">单价/￥</el-col>
                    <el-col :span="6" class="table-cell-title">演出时间</el-col>
                </el-row>
                <el-row>
                    <el-col :span="6" class="table-cell">{{ show.city }}</el-col>
                    <el-col :span="6" class="table-cell">{{ show.address }}</el-col>
                    <el-col :span="6" class="table-cell">
                        {{ order.money }}
                        <!--                        ￥{{ show.minPrice }}~￥{{ show.maxPrice }}-->
                    </el-col>
                    <el-col :span="6" class="table-cell" style="font-size:10px"
                    >{{ show.startTime | formatDateTime }}-{{
                        show.endTime | formatDateTime
                        }}
                    </el-col>
                </el-row>
                <el-row></el-row>
                <el-row></el-row>
            </div>
            <div>
                <el-row>
                    <el-col :span="6" class="table-cell-title">演出海报</el-col>
                    <el-col :span="6" class="table-cell-title">订单二维码</el-col>
                </el-row>
                <el-row>
                    <el-col :span="6" class="my-table-cell">
                        <el-image
                                style="width: 71px; height: 100px"
                                :src="show.poster"
                                :preview-src-list="[show.poster]"
                        >
                        </el-image>
                    </el-col>
                    <el-col :span="6" class="my-table-cell">
                        <qrcode-vue :value="show.poster"></qrcode-vue>
                    </el-col>
                </el-row>
            </div>

            <div style="margin-top: 20px">
                <span class="font-small">票务信息</span>
            </div>
            <el-row>
                <el-col :span="6" class="table-cell-title">序号</el-col>
                <el-col :span="6" class="table-cell-title">座位容量</el-col>
                <el-col :span="6" class="table-cell-title">座位名称</el-col>
                <el-col :span="6" class="table-cell-title">二维码</el-col>
            </el-row>
            <div v-for="(s,index) in seat">
                <el-row>
                    <el-col :span="6" class="my-table-cell">
                        {{index+1}}
                    </el-col>
                    <el-col :span="6" class="my-table-cell">
                        {{s.capacity}}
                    </el-col>
                    <el-col :span="6" class="my-table-cell">
                        {{s.name}}
                    </el-col>
                    <el-col :span="6" class="my-table-cell">
                        <qrcode-vue :value="s.qrCodeUrl"></qrcode-vue>
                    </el-col>
                </el-row>
            </div>
        </el-card>

    </div>
</template>

<script>
    import axios from "axios";
    import {formatDate} from "@/utils/date";
    import QrcodeVue from "qrcode.vue";

    export default {
        name: "",
        props: [""],
        components: {QrcodeVue},
        data()
        {
            return {
                loading: true,
                isTicket: false, //是不是实体票,默认为电子票
                orderId: "",
                address: "",
                category: "",
                seat: [],
                order: {},
                receiver: {},
                show: {},
                markOrderDialogVisible: false,
                markInfo: {note: ""},
            };
        },

        async created()
        {
            this.orderId = this.$route.query.orderId;
            await this.getOrderDetails(this.orderId);
            await this.getOrderSeat(this.orderId);
            await this.getReceiver(this.order.addressId);
            await this.getShowInfo(this.order.showId);
            await this.getCategory(this.show.categoryId);
            setTimeout(() =>
            {
                this.loading = false;
            }, 500);
        },

        computed: {},

        beforeMount()
        {
        },

        mounted()
        {
        },

        methods: {

            back()
            {
                this.$router.push("/order");
            },
            Delete()
            {
                this.$confirm("此操作将删除该订单, 是否继续?", "提示", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning",
                })
                    .then(() =>
                    {
                        this.handleDelete();
                    })
                    .catch(() =>
                    {
                        this.$message.info("已取消删除");
                    });
            },
            async handleDelete()
            {
                try
                {
                    const res = await axios.post(
                        this.$api.deleteUserOrder + "/" + this.orderId
                    );
                    console.log("删除订单");
                    console.log(res);
                    if (res.data.code === 200)
                    {
                        this.$message.success("删除成功");
                        this.$router.push("/order");
                    }
                } catch (err)
                {
                    console.log(err);
                }
            },

            Cancel()
            {
                this.$confirm("此操作将取消该订单, 是否继续?", "提示", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning",
                })
                    .then(() =>
                    {
                        this.handleCancel();
                    })
                    .catch(() =>
                    {
                        this.$message.info("未取消订单");
                    });
            },
            async handleCancel()
            {
                try
                {
                    const res = await axios.post(
                        this.$api.refundUserOrder + "/" + this.orderId
                    );
                    console.log("取消订单");
                    console.log(res);
                    if (res.data.code === 200)
                    {
                        this.$message.success("取消订单成功");
                        this.$router.push("/order");
                    }
                } catch (err)
                {
                    console.log(err);
                }
            },

            async getOrderSeat(id)
            {
                try
                {
                    console.log("座位详情");
                    const res = await axios.post(this.$api.getOrderSeatUrl + "/" + id);
                    console.log(res);
                    if (res.data.code === 200)
                    {
                        console.log(res.data.data);
                        this.seat = res.data.data;
                        console.log(this.seat);
                    }
                } catch (err)
                {
                    console.log(err);
                }
            },

            async getOrderDetails(id)
            {
                try
                {
                    console.log("订单详情");
                    const res = await axios.post(this.$api.getOrderDetailsUrl + "/" + id);
                    console.log(res);
                    if (res.data.code === 200)
                    {
                        console.log(res.data.data);
                        this.order = res.data.data;
                        console.log(this.order);
                    }
                } catch (err)
                {
                    console.log(err);
                }
            },

            async getReceiver(id)
            {
                if (id === undefined || id === null || id === "")
                {
                    this.isTicket = false;
                } else
                {
                    this.isTicket = true;
                    try
                    {
                        // console.log("收货人信息");
                        const res = await axios.post(this.$api.getAddressUrl + "/" + id);
                        // console.log(res);
                        if (res.data.code === 200)
                        {
                            // console.log(res.data.data);
                            this.receiver = res.data.data;
                            // console.log(this.receiver);
                            this.address =
                                this.receiver.province +
                                " " +
                                this.receiver.city +
                                " " +
                                this.receiver.region +
                                " " +
                                this.receiver.street +
                                " " +
                                this.receiver.details;
                        }
                    } catch (err)
                    {
                        console.log(err);
                    }
                }
            },

            async getShowInfo(id)
            {
                try
                {
                    // console.log("演出详情");
                    const res = await axios.post(this.$api.getShowDetails + "/" + id);
                    console.log(res);
                    if (res.data.code === 200)
                    {
                        // console.log(res.data.data);
                        this.show = res.data.data;
                        // console.log(this.show);
                    }
                } catch (err)
                {
                    console.log(err);
                }
            },

            async getCategory(id)
            {
                try
                {
                    // console.log("演出目录详情");
                    // console.log(id);
                    const res = await axios.post(this.$api.getCategoryNameUrl + '/' + id);
                    // console.log(res);
                    if (res.data.code === 200)
                    {
                        this.category = this.category + res.data.data.name + " ";
                        console.log(this.category);
                    }
                } catch (err)
                {
                    console.log(err);
                }
            },

        },

        watch: {},

        filters: {
            formatStatus(value)
            {
                if (value === 1)
                {
                    return "待观看";
                } else if (value === 2)
                {
                    return "已完成";
                } else if (value === 3)
                {
                    return "已退订单";
                }
            },
            formatTicket(value)
            {
                if (value === false)
                {
                    return "电子票";
                } else if (value === true)
                {
                    return "实体票";
                }
            },
            formatDateTime(time)
            {
                if (time == null || time === "")
                {
                    return "N/A";
                }
                let date = new Date(time);
                return formatDate(date, "yyyy-MM-dd hh:mm:ss");
            },
        },
    };
</script>
<style scoped>
    .detail-container {
        width: 80%;
        padding: 20px 20px 20px 20px;
        margin: 20px auto;
    }

    .operate-container {
        background: #f2f6fc;
        height: 80px;
        margin: -20px -20px 0;
        line-height: 80px;
    }

    .operate-button-container {
        float: right;
        margin-right: 20px;
    }

    .table-layout {
        margin-top: 20px;
        border-left: 1px solid #dcdfe6;
        border-top: 1px solid #dcdfe6;
    }

    .table-cell {
        height: 60px;
        line-height: 40px;
        border-right: 1px solid #dcdfe6;
        border-bottom: 1px solid #dcdfe6;
        padding: 10px;
        font-size: 14px;
        color: #606266;
        text-align: center;
        overflow: hidden;
    }

    .my-table-cell {
        height: 130px;
        line-height: 40px;
        border-right: 1px solid #dcdfe6;
        border-bottom: 1px solid #dcdfe6;
        padding: 10px;
        font-size: 14px;
        color: #606266;
        text-align: center;
        overflow: hidden;
    }

    .table-cell-title {
        border-right: 1px solid #dcdfe6;
        border-bottom: 1px solid #dcdfe6;
        padding: 10px;
        background: #f2f6fc;
        text-align: center;
        font-size: 14px;
        color: #303133;
    }

    .color-danger {
        color: #f56c6c;
    }

    .color-success {
        color: #67c23a;
    }

    .color-warning {
        color: #e6a23c;
    }
</style>
