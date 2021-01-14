<template>
    <div>
        <el-menu
                :default-active="activeIndex"
                class="el-menu-demo"
                mode="horizontal"
                @select="handleSelect"
        >
            <el-menu-item index="1">所有订单</el-menu-item>
            <el-menu-item index="2">待观看</el-menu-item>
            <el-menu-item index="3">已完成</el-menu-item>
            <el-menu-item index="4">已退订单</el-menu-item>
        </el-menu>
        <div>
            <el-card
                    class="operate-container"
                    shadow="never"
                    style="text-align: left"
            >
                <i class="el-icon-tickets"></i>
                <span>订单列表</span>
            </el-card>
            <div class="table-container">
                <el-table
                        :key="key"
                        :data="tableData"
                        v-loading="loading"
                        style="width: 100%"
                >
                    <el-table-column label="订单编号" prop="id"></el-table-column>
                    <el-table-column label="用户账号" prop="userId"></el-table-column>
                    <el-table-column label="演出名称" prop="name" align="center"></el-table-column>
                    <el-table-column label="订单状态" prop="realStatus">
                    </el-table-column>
                    <el-table-column label="订单提交时间" align="center">
                        <template slot-scope="scope">{{
                            scope.row.time | formatDateTime
                            }}
                        </template>
                    </el-table-column>

                    <el-table-column label="订单总金额" prop="money" align="center">
                    </el-table-column>
                    <el-table-column align="center" label="操作">
                        <template slot-scope="scope">
                            <el-button
                                    size="mini"
                                    type="info"
                                    @click="handleInfo(scope.$index, scope.row)"
                                    style="float: left; margin-left: 10px"
                            >查看订单
                            </el-button
                            >
                            <el-button
                                    size="mini"
                                    type="danger"
                                    @click="Delete(scope.$index, scope.row)"
                                    style="float: right; margin-right: 10px"
                                    v-show="scope.row.status !== 1"
                            >删除订单
                            </el-button
                            >
                            <el-button
                                    size="mini"
                                    type="warning"
                                    @click="Refund(scope.$index, scope.row)"
                                    style="float: right; margin-right: 10px"
                                    v-show="scope.row.status === 1"
                            >取消订单
                            </el-button
                            >
                        </template>
                    </el-table-column>
                </el-table>
            </div>
        </div>
        <el-pagination
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                :current-page="page.pageNum"
                :page-sizes="[1, 2, 5, 10]"
                :page-size="page.pageSize"
                layout="total, sizes, prev, pager, next, jumper"
                :total="page.totalRecord"
        >
        </el-pagination>
    </div>
</template>

<script>
    import axios from "axios";
    import {formatDate} from "@/utils/date";

    const fields = [
        {label: "订单编号", prop: "orderId"},
        {label: "用户账号", prop: "userId"},
        {label: "演出编号", prop: "showId"},
        {label: "演出名称", prop: "name"},
        {label: "订单状态编号", prop: "status"},
        {label: "订单状态", prop: "realStatus"},
        {label: "订单提交时间", prop: "time"},
        {label: "订单总金额", prop: "money"},
    ];
    export default {
        name: "",
        props: [""],
        data()
        {
            return {
                activeIndex: "1",
                tableData: [],
                search: "",
                key: 1, // table key
                formThead: fields, // 默认表头 Default header
                loading: true,
                OrderState: ["待观看", "已完成", "已退订单"],
                page: {
                    pageNum: 1,
                    pageSize: 5,
                    totalRecord: 0,
                },
            };
        },

        components: {},

        computed: {},

        beforeMount()
        {
        },

        async mounted()
        {
            await this.reload();
        },

        methods: {
            handleSizeChange(val)
            {
                this.page.pageSize = val;
                this.page.pageNum = 1;
                if (this.activeIndex !== 1)
                {
                    this.reload(this.activeIndex - 1);
                } else
                {
                    this.reload();
                }
            },
            handleCurrentChange(val)
            {
                this.page.pageNum = val;
                if (this.activeIndex !== 1)
                {
                    this.reload(this.activeIndex - 1);
                } else
                {
                    this.reload();
                }
            },
            //选择不同类型的订单显示在订单列表
            handleSelect(key, keyPath)
            {
                console.log(key, keyPath);
                this.activeIndex = key;
                var orders = [];
                if (key !== 1)
                {
                    this.reload(key - 1);
                } else
                {
                    this.reload();
                }
            },

            handleInfo(index, row)
            {
                this.$router.push({
                    path: "/orderDetails",
                    query: {orderId: row.id},
                });
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
                        this.handleDelete(index, row);
                    })
                    .catch(() =>
                    {
                        this.$message.info("已取消删除");
                    });
            },
            async handleDelete(index, row)
            {
                try
                {
                    const res = await axios.post(
                        this.$api.deleteUserOrder + "/" + row.id
                    );
                    console.log("删除订单");
                    console.log(res);
                    if (res.data.code === 200)
                    {
                        this.$message.success("删除订单成功");
                        if (this.activeIndex !== 1)
                        {
                            this.reload(this.activeIndex - 1);
                        } else
                        {
                            this.reload();
                        }
                    }
                } catch (err)
                {
                    console.log(err);
                }
            },

            Refund(index, row)
            {
                this.$confirm("此操作将取消该订单, 是否继续?", "提示", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning",
                })
                    .then(() =>
                    {
                        this.handleRefund(index, row);
                    })
                    .catch(() =>
                    {
                        this.$message.info("未取消订单");
                    });
            },

            async handleRefund(index, row)
            {
                try
                {
                    const res = await axios.post(
                        this.$api.refundUserOrder + "/" + row.id
                    );
                    console.log("取消订单");
                    console.log(res);
                    if (res.data.code === 200)
                    {
                        this.$message.success("取消订单成功");
                        this.reload();
                    }
                } catch (err)
                {
                    console.log(err);
                }
            },

            async getShowName(id, i)
            {
                try
                {
                    console.log("for循环内根据showId查演出名称");
                    const res = await axios.post(this.$api.getShowName + "/" + id);
                    console.log(res);
                    if (res.data.code === 200)
                    {
                        this.tableData[i].name = res.data.data.name;
                        this.$set(this.tableData, i, this.tableData[i]);
                    }
                } catch (err)
                {
                    console.log(err);
                }
            },

            async reload(status = 0)
            {
                console.log(status)
                try
                {
                    console.log("mounted");
                    const res = await axios.post(this.$api.getOrderListUrl, {
                        status: status,
                        pageNum: this.page.pageNum,
                        pageSize: this.page.pageSize,
                    });
                    console.log(res);
                    if (res.data.code === 200)
                    {
                        this.tableData = res.data.data.list;
                        this.page.totalRecord = res.data.data.total;
                        for (var i = 0; i < this.tableData.length; i++)
                        {
                            if (this.tableData[i].status === 1)
                            {
                                this.tableData[i].realStatus = "待观看";
                            } else if (this.tableData[i].status === 2)
                            {
                                this.tableData[i].realStatus = "已完成";
                            } else
                            {
                                this.tableData[i].realStatus = "已退订单";
                            }
                            this.getShowName(this.tableData[i].showId, i);
                        }
                    } else
                    {
                        this.tableData = [];
                        this.page.totalRecord = 0;
                        this.$forceUpdate();
                    }
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

            dataFilter(data)
            {
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
