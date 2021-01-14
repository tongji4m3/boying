<template>
    <div v-loading="loading" style="padding: 50px">
        <el-card>
            <el-table
                    :data="tableData"
                    border
                    style="width: 80%; margin-left: 130px; margin-bottom: 30px"
                    row-key="categoryId"
                    :tree-props="{ children: 'children', hasChildren: 'hasChildren' }"
            >
                <!-- <el-table-column prop="categoryId" label="演出目录编号" width="150"> -->
                <el-table-column prop="name" label="目录名">
                </el-table-column>
                <!-- <el-table-column prop="setting" label="设置" width="200">
              </el-table-column> -->
                <el-table-column prop="address" label="操作">
                    <template slot-scope="scope">
                        <el-button
                                size="mini"
                                type="info"
                                @click="handleUpdate(scope.$index, scope.row)"
                        >
                            编辑
                        </el-button>
                        <el-switch
                                @change="handleStatusChange(scope.$index, scope.row)"
                                :active-value="false"
                                :inactive-value="true"
                                v-model="scope.row.adminDelete"
                        >
                        </el-switch>
                    </template>
                </el-table-column
                >
            </el-table>
            <el-button type="primary" @click="handleAdd()">添加目录</el-button>
            <el-dialog
                    :title="isEdit ? '编辑目录' : '添加目录'"
                    :visible.sync="dialogVisible"
                    width="40%"
            >
                <el-form :model="categorySelected" label-width="150px" size="small">
                    <el-form-item label="目录名称:">
                        <el-input
                                v-model="categorySelected.name"
                                style="width: 250px"
                        ></el-input>

                    </el-form-item>
                    <el-form-item label="目录排序:">
                        <el-input
                                v-model="categorySelected.weight"
                                style="width: 250px"
                        ></el-input>

                    </el-form-item>
                </el-form>
                <span slot="footer" class="dialog-footer">
          <el-button @click="dialogVisible = false" size="small"
          >取 消</el-button
          >
          <el-button type="primary" @click="handleDialogConfirm()" size="small"
          >确 定</el-button
          >
        </span>
            </el-dialog>
        </el-card>
    </div>
</template>
<script>
    import axios from "axios";
    import api from "@/assets/api.js";

    const defaultCategory = {
        categoryId: "",
        description: "",
        icon: "",
        name: "",
        weight: 1,
    };

    export default {
        data()
        {
            return {
                add: false,
                loading: true,
                dialogVisible: false,
                isEdit: false,
                firstCategory: {
                    categoryId: "",
                    description: "",
                    icon: "",
                    name: "",
                    parentId: 0,
                    weight: 1,
                    children: [],
                },
                categorySelected: Object.assign({}, defaultCategory),
                tableData: [],
            };
        },
        methods: {
            handleUpdate(index, row)
            {
                this.dialogVisible = true;
                this.isEdit = true;
                this.categorySelected = Object.assign({}, row);
            },
            async deleteCategory(category)
            {
                this.loading = true;
                try
                {
                    const res = await axios.post(
                        `${api.API_URL}/category/delete` + "/" + category.id,
                        {
                            headers: {
                                Authorization: "Bearer " + sessionStorage.getItem("token"),
                            },
                        }
                    );
                    console.log(res);
                    if (res.data.code == 200)
                    {
                        this.$message.success("删除成功");
                        setTimeout(() =>
                        {
                            this.loading = false;
                        }, 500);
                    } else
                    {
                        this.$message.error("未知错误删除失败");
                    }
                } catch (err)
                {
                    console.log(err);
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
                            `${api.API_URL}/category/delete` + "/" + row.id,
                            {
                                headers: {
                                    Authorization: "Bearer " + sessionStorage.getItem("token"),
                                },
                            }
                        );
                        if (res.data.code == 200)
                        {
                            this.$message.success("目录关闭成功");
                        }
                        console.log(res);
                    } else
                    {
                        const res = await axios.post(
                            `${api.API_URL}/category/recover` + "/" + row.id,
                            {
                                headers: {
                                    Authorization: "Bearer " + sessionStorage.getItem("token"),
                                },
                            }
                        );
                        if (res.data.code == 200)
                        {
                            this.$message.success("目录启用成功");
                        }
                        console.log(res);
                    }
                });
            },
            handleDelete(index, row)
            {
                this.$confirm("是否要删除该目录?", "提示", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning",
                })
                    .then(async () =>
                    {
                        console.log(row);
                        if (
                            row.children != undefined &&
                            row.children != [] &&
                            row.children != null
                        )
                        {
                            for (var i of row.children)
                            {
                                await this.deleteCategory(i);
                            }
                        }
                        await this.deleteCategory(row);
                        await this.reload();
                    })
                    .catch(() =>
                    {
                        this.$message.info("取消删除");
                        console.log("catch");
                        console.log(row.children);
                        // this.getList();
                    });
            },
            async updateCategory(category)
            {
                this.loading = true;
                console.log("here category", category);
                try
                {
                    const res = await axios.post(
                        `${api.API_URL}/category/update` + "/" + category.id,
                        {
                            name: category.name,
                            weight: category.weight,
                            icon: category.icon,
                            description: category.description,
                        },
                        {
                            headers: {
                                Authorization: "Bearer " + sessionStorage.getItem("token"),
                            },
                        }
                    );
                    console.log(res);
                    if (res.data.code == 200)
                    {
                        this.$message.success("编辑成功");
                        this.reload();
                        setTimeout(() =>
                        {
                            this.loading = false;
                        }, 500);
                    } else
                    {
                        this.$message.error("未知错误编辑失败");
                        this.loading = false;
                    }
                } catch (err)
                {
                    console.log(err);
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
                        console.log("this.categorySelected", this.categorySelected);
                        this.updateCategory(this.categorySelected);
                        this.dialogVisible = false;
                    } else
                    {
                        console.log(this.add);
                        console.log("this.categorySelected", this.categorySelected);
                        this.addCategory(this.categorySelected);
                        this.dialogVisible = false;
                    }
                });
            },
            async handleAdd()
            {
                this.dialogVisible = true;
                this.isEdit = false;
                this.categorySelected = Object.assign({}, defaultCategory);
            },

            async addCategory(category)
            {
                try
                {
                    const res = await axios.post(
                        `${api.API_URL}/category/create`,
                        category
                    );
                    if (res.data.code == 200)
                    {
                        console.log("add", res);
                        this.$message.success("添加成功");
                        this.reload();
                        this.add = false;
                    } else
                    {
                        if ((res.data.message = "目录名称不能重复!"))
                        {
                            this.$message.error("目录名称不能重复");
                        } else
                        {
                            this.$message.error("添加失败");
                        }
                    }
                } catch (err)
                {
                    console.log(err);
                    this.$message.error("添加失败");
                }
            },
            async reload()
            {
                this.loading = true;
                try
                {
                    const res = await axios.get(`${api.API_URL}/category/listAll`, {
                        headers: {
                            Authorization: "Bearer " + sessionStorage.getItem("token"),
                        },
                    });
                    console.log(res);
                    if (res.data.code == 200)
                    {
                        this.tableData = res.data.data;
                        //   for (var i = 0; i < this.tableData.length; i++) {
                        //     this.getChildren(this.tableData[i]);
                        //   }
                        setTimeout(() =>
                        {
                            this.loading = false;
                        }, 500);
                        console.log(this.tableData);
                    }
                } catch (err)
                {
                    console.log(err);
                }

            },
        },

        async mounted()
        {
            this.reload();
        },
    };
</script>

<style scoped>
</style>
