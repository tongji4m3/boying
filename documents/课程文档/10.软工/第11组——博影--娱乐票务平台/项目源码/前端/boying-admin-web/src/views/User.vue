<template>
     
    <div class="app-container">
        <el-card class="operate-container" shadow="never" style="text-align: left">
            <i class="el-icon-tickets"></i>
            <span>数据列表</span>
            <el-button
                    size="mini"
                    class="btn-add"
                    @click="handleAdd()"
                    style="float: right"
            >添加
            </el-button
            >
        </el-card>
        <div class="table-container">
            <el-table
                    ref="adminTable"
                    :key="key"
                    :data="
          list.filter(
            (data) =>
              !search ||
              data.username.toLowerCase().includes(search.toLowerCase())
          )
        "
                    style="width: 100%"
                    v-loading="listLoading"
                    border
            >
                <el-table-column label="用户id" width="100" align="center">
                    <template slot-scope="scope">{{ scope.row.id }}</template>
                </el-table-column>
                <el-table-column label="用户名" align="center">
                    <template slot-scope="scope">{{ scope.row.username }}</template>
                </el-table-column>
                <el-table-column label="电话号码" align="center">
                    <template slot-scope="scope">{{ scope.row.phone }}</template>
                </el-table-column>
                <el-table-column label="真实姓名" align="center">
                    <template slot-scope="scope">{{ scope.row.realName }}</template>
                </el-table-column>
                <el-table-column label="邮箱" width="160" align="center">
                    <template slot-scope="scope">{{ scope.row.email }}</template>
                </el-table-column>
                <el-table-column label="账号创建时间" width="160" align="center">
                    <template slot-scope="scope">{{
                        scope.row.createTime | formatDateTime
                        }}
                    </template>
                </el-table-column>
                <el-table-column label="是否启用" width="140" align="center">
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
                <el-table-column align="center">
                    <template slot="header" slot-scope="scope">
                        <el-input
                                v-model="search"
                                size="mini"
                                placeholder="按用户名关键字搜索"
                        />
                    </template>
                </el-table-column>
            </el-table>
        </div>
        <el-dialog
                :title="isEdit ? '编辑用户' : '添加用户'"
                :visible.sync="dialogVisible"
                width="40%"
        >
            <el-form :model="user" ref="adminForm" label-width="150px" size="small">
                <el-form-item label="用户名：">
                    <el-input v-model="user.username" style="width: 250px"></el-input>
                </el-form-item>
                <el-form-item label="电话号码">
                    <el-input v-model="user.phone" style="width: 250px"></el-input>
                </el-form-item>
                <el-form-item label="真实姓名：">
                    <el-input v-model="user.realName" style="width: 250px"></el-input>
                </el-form-item>
                <el-form-item label="邮箱：">
                    <el-input v-model="user.email" style="width: 250px"></el-input>
                </el-form-item>
                <el-form-item label="密码：">
                    <el-input
                            v-model="user.password"
                            type="password"
                            style="width: 250px"
                    ></el-input>
                </el-form-item>
                <el-form-item label="是否启用：">
                    <el-radio-group v-model="user.admin_delete">
                        <el-radio :label="true">是</el-radio>
                        <el-radio :label="false">否</el-radio>
                    </el-radio-group>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false" size="small">取 消</el-button>
        <el-button type="primary" @click="handleDialogConfirm()" size="small"
        >确 定</el-button
        >
      </span>
        </el-dialog>
    </div>
</template>
<script>
    import axios from "axios";
    import api from "@/assets/api.js";
    import {formatDate} from "@/utils/date";

    const defaultListQuery = {
        pageNum: 1,
        pageSize: 10,
        keyword: null,
    };
    const defaultUser = {
        id: null,
        username: null,
        phone: null,
        realName: null,
        password: null,
        email: null,
        createTime: null,
        admin_delete: true,
    };
    export default {
        name: "adminList",
        data()
        {
            return {
                key: 1, // table key
                search: "",
                listQuery: Object.assign({}, defaultListQuery),
                list: [],
                total: null,
                listLoading: false,
                dialogVisible: false,
                user: Object.assign({}, defaultUser),
                isEdit: false,
                allocDialogVisible: false,
                allocRoleIds: [],
                allRoleList: [],
                allocAdminId: null,
            };
        },
        created()
        {
            this.getList();
            this.getAllRoleList();
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
        },
        methods: {
            handleResetSearch()
            {
                this.listQuery = Object.assign({}, defaultListQuery);
            },
            handleSearchList()
            {
                this.listQuery.pageNum = 1;
                this.getList();
            },
            handleSizeChange(val)
            {
                this.listQuery.pageNum = 1;
                this.listQuery.pageSize = val;
                this.getList();
            },
            handleCurrentChange(val)
            {
                this.listQuery.pageNum = val;
                this.getList();
            },

            async addUser(user)
            {
                user.createTime = new Date();
                try
                {
                    const res = await axios.post(`${api.API_URL}/user/add`, user, {
                        headers: {
                            Authorization: "Bearer " + sessionStorage.getItem("token"),
                        },
                    });
                    console.log(res);
                    if (res.data.code == 200)
                    {
                        this.$message.success("添加成功");
                        this.getList();
                    } else
                    {
                        this.$message.error("添加失败");
                    }
                } catch (err)
                {
                    console.log(err);
                    this.$message.error("添加失败");
                }
            },
            handleAdd()
            {
                this.dialogVisible = true;
                this.isEdit = false;
                this.user = Object.assign({}, defaultUser);
            },

            async updateStatus(row)
            {
                console.log("row", row);
                try
                {
                    const res = await axios.post(
                        `${api.API_URL}/user/ChangeUserStatus/` + row.id,
                        {
                            headers: {
                                Authorization: "Bearer " + sessionStorage.getItem("token"),
                            },
                        }
                    );
                    console.log(res);
                    if (res.data.code == 200)
                    {
                        this.$message.success("修改成功");
                    }
                } catch (err)
                {
                    console.log(err);
                    this.$message.error("修改失败");
                }
                this.getList();
            },

            async handleStatusChange(index, row)
            {
                this.$confirm("是否要修改该状态?", "提示", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning",
                })
                    .then(() =>
                    {
                        this.updateStatus(row);
                    })
                    .catch(() =>
                    {
                        this.$message.info("取消修改");
                        console.log("catch");
                        this.getList();
                    });
            },


            handleUpdate(index, row)
            {
                this.dialogVisible = true;
                this.isEdit = true;
                this.user = Object.assign({}, row);
            },
            async updateUser(id, user)
            {
                try
                {
                    const res = await axios.post(`${api.API_URL}/user/update/` + id, user, {
                        headers: {
                            Authorization: "Bearer " + sessionStorage.getItem("token"),
                        },
                    });
                    console.log(res);
                    if (res.data.code == 200)
                    {
                        this.$message.success("更新信息成功");
                        this.getList();
                    }
                } catch (err)
                {
                    console.log(err);
                    this.$message.error("更新信息失败");
                }
            },
            handleDialogConfirm()
            {
                this.$confirm("是否要确认?", "提示", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning",
                }).then(() =>
                {
                    if (this.isEdit)
                    {
                        this.updateUser(this.user.userId, this.user);
                        this.dialogVisible = false;
                    } else
                    {
                        this.addUser(this.user);
                        this.dialogVisible = false;
                    }
                });
            },
            handleAllocDialogConfirm()
            {
                this.$confirm("是否要确认?", "提示", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning",
                }).then(() =>
                {
                    let params = new URLSearchParams();
                    params.append("adminId", this.allocAdminId);
                    params.append("roleIds", this.allocRoleIds);
                    allocRole(params).then((response) =>
                    {
                        this.$message({
                            message: "分配成功！",
                            type: "success",
                        });
                        this.allocDialogVisible = false;
                    });
                });
            },
            //暂时无用
            handleSelectRole(index, row)
            {
                this.allocAdminId = row.id;
                this.allocDialogVisible = true;
                this.getRoleListByAdmin(row.id);
            },
            async getList()
            {
                this.listLoading = true;
                try
                {
                    const res = await axios.get(`${api.API_URL}/user/list`, {
                        headers: {
                            Authorization: "Bearer " + sessionStorage.getItem("token"),
                        },
                    });
                    console.log(res);
                    if (res.data.message == "不存在任何用户")
                    {
                        this.list = null;
                    }
                    if (res.data.code == 200)
                    {
                        this.list = res.data.data;
                    }
                    setTimeout(() =>
                    {
                        this.listLoading = false;
                    }, 500);
                } catch (err)
                {
                    console.log(err);
                }
            },
            getAllRoleList()
            {
            },
            getRoleListByAdmin(adminId)
            {
                getRoleByAdmin(adminId).then((response) =>
                {
                    let allocRoleList = response.data;
                    this.allocRoleIds = [];
                    if (allocRoleList != null && allocRoleList.length > 0)
                    {
                        for (let i = 0; i < allocRoleList.length; i++)
                        {
                            this.allocRoleIds.push(allocRoleList[i].id);
                        }
                    }
                });
            },
        },
    };
</script>
<style scoped>
</style>


