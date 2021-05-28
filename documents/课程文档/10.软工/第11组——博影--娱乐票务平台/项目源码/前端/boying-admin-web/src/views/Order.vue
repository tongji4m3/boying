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

                <el-table-column type="expand">
                    <template slot-scope="props">
                        <el-form label-position="left" inline class="demo-table-expand">
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
                            <el-form-item label="该订单对用户是否可见:">
                                <span>{{ props.row.userDelete | formatUserDelete }}</span>
                            </el-form-item>
                            <el-form-item label="订单所含票数:">
                                <span>{{ props.row.ticketCount }}</span>
                            </el-form-item>
                        </el-form>
                    </template>
                </el-table-column>
                <el-table-column label="用户账号" prop="userId"></el-table-column>
                <el-table-column label="演出编号" prop="showId"></el-table-column>
                <el-table-column label="订单状态" prop="realStatus"></el-table-column>
                <el-table-column label="订单提交时间" width="160" align="center">
                    <template slot-scope="scope">{{
                        scope.row.time | formatDateTime
                        }}
                    </template>
                </el-table-column>

                <el-table-column label="订单总金额" prop="money" align="center">
                </el-table-column>
                <el-table-column label="操作" align="center">
                    <template slot-scope="scope">
                        <el-button
                                type="primary"
                                size="small"
                                @click="listTickets(scope.row)"
                        >查看
                        </el-button
                        >
                    </template
                    >
                </el-table-column>

                <el-table-column align="center">
                    <template slot="header" slot-scope="scope">
                        <el-input
                                v-model="search"
                                size="mini"
                                placeholder="按订单状态关键字搜索"
                        />
                    </template>
                    <template slot-scope="scope">
                        <el-switch
                                @change="handleStatusChange(scope.$index, scope.row)"
                                :active-value="false"
                                :inactive-value="true"
                                v-model="scope.row.adminDelete"
                        >
                        </el-switch>
                    </template>
                </el-table-column>
            </el-table>
        </div>
        <div class="block">
            <el-pagination
                    layout="prev, pager, next"
                    :page-size="pageSize"
                    :current-page.sync="pageNum"
                    :total="this.total"
                    @current-change="handleCurrentChange"
            >
            </el-pagination>
        </div>
        <el-dialog title="提示" :visible.sync="listTicketsVislble" width="90%">
            <template>
                <el-table :data="listTicketData" style="width: 100%">
                    <el-table-column prop="name" label="名称" width="180">
                    </el-table-column>
                    <el-table-column prop="price" label="价格" width="180">
                    </el-table-column>
                    <el-table-column prop="capacity" label="容量"></el-table-column>
                    <el-table-column prop="qrCodeUrl" label="二维码">
                        <template slot-scope="scope">
                            <el-image
                                    style="width: 50px; height: 50px"
                                    :src="scope.row.qrCodeUrl"
                            >
                            </el-image>
                        </template>
                    </el-table-column>
                </el-table>
            </template>
            <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="closeTickete()">确 定</el-button>
      </span>
        </el-dialog>
    </div>
</template>

<script>
    import axios from "axios";
    import api from "@/assets/api.js";
    import {formatDate} from "@/utils/date";

    const fields = [
        //   { label: "订单编号", prop: "orderId" },
        {label: "用户账号", prop: "userId"},
        {label: "演出编号", prop: "showId"},
        {label: "订单状态编号", prop: "status"},
        {label: "订单状态", prop: "realStatus"},
        {label: "订单提交时间", prop: "time"},
        {label: "订单总金额", prop: "money"},

        //展开行功能多出的内容
        {label: "演出场次编号", prop: "showSessionId"},
        {label: "订单支付方式", prop: "payment"},
        {label: "用户是否已经删除了该订单", prop: "userDelete"},
        {label: "订单所含票数", prop: "ticketCount"},
    ];

    export default {
        name: "",
        props: [""],
        data()
        {
            return {
                listTicketData: [],
                listTicketsVislble: false,
                pageSize: 10,
                pageNum: 1,
                total: 100,
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

        beforeMount()
        {
        },

        mounted()
        {
            this.reload();
        },

        methods: {
            closeTickete()
            {
                this.listTicketData = [];
                this.listTicketsVislble = false;
            },
            async listTickets(info)
            {
                console.log("listTickets.info", info);
                this.listTicketsVislble = true;
                const res = await axios.post(
                    `${api.API_URL}/ticket/listTickets`,
                    {
                        orderId: info.id,
                        pageNum: 1,
                        pageSize: 10000,
                        seatId: 0,
                    },
                    {
                        headers: {
                            Authorization: "Bearer " + sessionStorage.getItem("token"),
                        },
                    }
                );
                console.log("here", res);
                if (res.data.code == 200)
                {
                    this.listTicketData = res.data.data.list;
                    console.log("this.listTicketData", this.listTicketData);
                    // this.$message.success("");
                } else if (res.data.message == "不存在任何票！")
                {
                    this.$message.info("不存在任何票！");
                    console.log("不存在任何票！");
                }
            },
            async handleStatusChange(index, row)
            {
                this.$confirm("是否要修改该状态?", "提示", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning",
                }).then(async () =>
                {
                    console.log(index, row);
                    if (row.adminDelete == true)
                    {
                        const res = await axios.post(
                            `${api.API_URL}/order/deleteOrder` + "/" + row.id,
                            {
                                headers: {
                                    Authorization: "Bearer " + sessionStorage.getItem("token"),
                                },
                            }
                        );
                        if (res.data.code == 200)
                        {
                            this.$message.success("订单关闭成功");
                        }
                        console.log(res);
                    } else
                    {
                        const res = await axios.post(
                            `${api.API_URL}/order/recoverOrder` + "/" + row.id,
                            {
                                headers: {
                                    Authorization: "Bearer " + sessionStorage.getItem("token"),
                                },
                            }
                        );
                        if (res.data.code == 200)
                        {
                            this.$message.success("订单启用成功");
                        }
                        console.log(res);
                    }
                });
            },
            async handleCurrentChange()
            {
            },
            Delete(index, row)
            {
                this.$confirm("此操作将删除该订单, 是否继续?", "提示", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning",
                })
                    .then(() =>
                    {
                        this.handleDelete(row.orderId);
                    })
                    .catch(() =>
                    {
                        this.$message.info("已取消删除");
                    });
            },
            async handleDelete(id)
            {
                try
                {
                    console.log("mounted");
                    const res = await axios.post(`${api.API_URL}/user/deleteOrder/` + id);
                    console.log("test");
                    console.log(res);
                    if (res.data.code == 200)
                    {
                        this.$message.success("删除成功");
                        this.reload();
                    }
                } catch (err)
                {
                    console.log(err);
                }
            },

            async reload()
            {
                try
                {
                    console.log("mounted");
                    const res = await axios.post(
                        `${api.API_URL}/order/listOrders`,
                        {
                            pageNum: this.pageNum,
                            pageSize: this.pageSize,
                            showId: 0,
                            userId: 0,
                        },
                        {
                            headers: {
                                Authorization: "Bearer " + sessionStorage.getItem("token"),
                            },
                        }
                    );
                    console.log("res", res);
                    if (res.data.message == "不存在任何订单")
                    {
                        this.tableData = [];
                    }
                    if (res.data.code == 200)
                    {
                        this.total = res.data.data.total;
                        this.tableData = res.data.data.list;
                        console.log(res.data.data);
                        for (var i = 0; i < this.tableData.length; i++)
                        {
                            if (this.tableData[i].status == 1)
                            {
                                this.tableData[i].realStatus = "待观看";
                            } else if (this.tableData[i].status == 2)
                            {
                                this.tableData[i].realStatus = "已完成";
                            } else
                            {
                                this.tableData[i].realStatus = "已退订单";
                            }
                        }
                    }
                    console.log("tableData", this.tableData);
                    setTimeout(() =>
                    {
                        this.loading = false;
                    }, 500);
                } catch (err)
                {
                    console.log(err);
                }
            },
        },
        filters: {
            formatDateTime(time)
            {
                if (time == null || time === "")
                {
                    return "N/A";
                }
                let date = new Date(time);
                return formatDate(date, "yyyy-MM-dd hh:mm:ss");
            },

            formatUserDelete(value)
            {
                if (value == true)
                {
                    return "用户已删除";
                } else
                {
                    return "用户未删除";
                }
            },
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
