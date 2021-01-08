<template>
    <div>
        <!--        个人信息相关-->
        <el-card class="el-card" style="width: 80%; margin:auto;">
            <div>
                <div>
                    <h2>个人信息：</h2>
                </div>
                <br><br><br>

                <el-form ref="form" :model="form" label-width="80px">
                    <el-row :gutter="40">
                        <el-col :span="12">
                            <el-form-item label="头像">
                                <img
                                    width="100"
                                    height="100"
                                    :src="form.icon"
                                    class="image"
                                    style="border-radius: 50%"
                                />
                                <el-upload
                                    class="upload"
                                    action
                                    :drag="true"
                                    :multiple="true"
                                    :file-list="images"
                                    :http-request="uploadHttp"
                                    :before-upload="beforeAvatarUpload"
                                    :on-remove="handleRemove">
                                    <i class="el-icon-upload"></i>
                                    <div class="el-upload__text">将文件拖到此处，或<em>点击上传个人头像</em></div>
<!--                                    <i class="el-icon-plus avatar-uploader-icon"></i>-->
<!--                                    <p id="img-context">上传个人头像</p>-->
                                    <div class="el-upload__tip" slot="tip">
                                        只能上传jpg/jpeg/png文件，且不超过5MB
                                    </div>
                                </el-upload>
                            </el-form-item>
                            <el-form-item>
                                <el-button type="primary" @click="updateUserInfo()">更 新</el-button>
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">
                            <el-form-item label="账号名称">
                                <el-input v-model="form.name" style="width: 30%" :readonly="true"></el-input>
                            </el-form-item>
                            <el-form-item label="联系方式">
                                <el-input v-model="form.phone" style="width: 30%" :readonly="true"></el-input>
                            </el-form-item>
                            <el-form-item label="邮箱">
                                <el-input v-model="form.email" style="width: 50%"></el-input>
                            </el-form-item>
                            <el-form-item label="年龄">
                                <el-input v-model="form.age" style="width: 50%"></el-input>
                            </el-form-item>
                            <el-form-item label="身份证号">
                                <el-input v-model="form.identityNumber" style="width: 50%"></el-input>
                            </el-form-item>
                            <el-form-item label="真实姓名">
                                <el-input v-model="form.realName" style="width: 50%"></el-input>
                            </el-form-item>
                            <el-form-item label="姓别">
                                <el-select v-model="form.gender" placeholder="请选择" style="width: 40%">
                                    <el-option label="男" value="man"></el-option>
                                    <el-option label="女" value="woman"></el-option>
                                </el-select>
                            </el-form-item>

                        </el-col>
                    </el-row>


                </el-form>
            </div>
        </el-card>
        <br>
        <el-card class="el-card" style="width: 80%; margin:auto;">
            <div>
                <div>
                    <h2>常用联系人：</h2>
                </div>
                姓名：{{ defaultFrequentList.name }}
                <br>
                身份证号：{{ defaultFrequentList.identityNumber }}
                <br>
                联系方式：{{ defaultFrequentList.phone }}
                <br>
                <br>
                联系人：
                <el-button type="primary" @click="showAddFrequent()">添加联系人</el-button>
                <el-table :data="frequentList" style="width: 100%" :row-class-name="tableRowClassName2">
                    <!--                            <el-table-column prop="frequentId" label="联系人编号"></el-table-column>-->
                    <el-table-column prop="identityNumber" label="身份证号"></el-table-column>
                    <el-table-column prop="name" label="姓名"></el-table-column>
                    <el-table-column prop="phone" label="联系方式"></el-table-column>
                    <el-table-column label="操作" width="200px">
                        <template slot-scope="scope">
                            <el-button type="text" @click="deleteFrequent(scope.row.frequentId)">删除</el-button>
                            <el-button type="text" @click="showEditFrequent(scope.row.frequentId)">编辑</el-button>
                            <el-button type="text" @click="setDefaultFrequentList(scope.row.frequentId)">设为默认</el-button>
                        </template>
                    </el-table-column>
                </el-table>
                <!--        添加联系人对话框-->
                <el-dialog title="添加联系人" :visible.sync="addDialogVisible2" width="630px" top="60px" center>
                    <!--            内容主体区域 放置一个表单-->
                    <!--绑定到addForm中，绑定验证规则对象addFormRules 表单校验项的引用为addFormRef-->
                    <el-form :model="addForm2" :rules="addFormRules2" ref="addFormRef2" label-width="100px">
                        <!-- prop属性指定验证规则-->
                        <el-form-item label="身份证号:" prop="identityNumber">
                            <!--v-model双向绑定-->
                            <el-input style="width: 82%;" v-model="addForm2.identityNumber"></el-input>
                        </el-form-item>
                        <el-form-item label="姓名:" prop="name">
                            <el-input style="width: 82%;" v-model="addForm2.name"></el-input>
                        </el-form-item>
                        <el-form-item label="联系方式:" prop="phone">
                            <el-input style="width: 82%;" v-model="addForm2.phone"></el-input>
                        </el-form-item>
                    </el-form>
                    <!--            底部区域-->
                    <span slot="footer" class="dialog-footer">
                                <el-button style="margin-right:20px" @click="cancelAdd2()">取 消</el-button>
                                <el-button style="margin-left:20px" type="primary" @click="addFrequent()">确 定</el-button>
                            </span>
                </el-dialog>
                <!--        展示联系人对话框-->
                <el-dialog title="联系人详情" :visible.sync="showDialogVisible2" width="630px" top="60px" center>
                    <!--            内容主体区域 放置一个表单-->
                    <el-form :model="showForm2" ref="addFormRef" label-width="100px">
                        <!-- prop属性指定验证规则-->
                        <el-form-item label="收货人:" prop="receiver">
                            <!--v-model双向绑定-->
                            <el-input style="width: 82%;" v-model="showForm2.receiver" :readonly="true"></el-input>
                        </el-form-item>
                        <el-form-item label="联系方式:" prop="phone">
                            <el-input style="width: 82%;" v-model="showForm2.phone" :readonly="true"></el-input>
                        </el-form-item>
                        <el-form-item label="省:" prop="province">
                            <el-input style="width: 82%;" v-model="showForm2.province" :readonly="true"></el-input>
                        </el-form-item>
                        <el-form-item label="市:" prop="city">
                            <el-input style="width: 82%;" v-model="showForm2.city" :readonly="true"></el-input>
                        </el-form-item>
                        <el-form-item label="区:" prop="region">
                            <el-input style="width: 82%;" v-model="showForm2.region" :readonly="true"></el-input>
                        </el-form-item>
                        <el-form-item label="街道:" prop="street">
                            <el-input style="width: 82%;" v-model="showForm2.street" :readonly="true"></el-input>
                        </el-form-item>
                        <el-form-item label="详细地址:" prop="details">
                            <el-input style="width: 82%;" type="textarea"
                                      :autosize="{ minRows: 3, maxRows: 4}" v-model="showForm2.details" :readonly="true"></el-input>
                        </el-form-item>
                    </el-form>
                </el-dialog>
                <!--        编辑联系人对话框-->
                <el-dialog title="编辑联系人" :visible.sync="editDialogVisible2" width="630px" top="60px" center>
                    <!--            内容主体区域 放置一个表单-->
                    <!--绑定到addForm中，绑定验证规则对象addFormRules 表单校验项的引用为addFormRef-->
                    <el-form :model="editForm2" :rules="editFormRules2" ref="editFormRef2" label-width="100px">
                        <!-- prop属性指定验证规则-->
                        <el-form-item label="身份证号:" prop="identityNumber">
                            <!--v-model双向绑定-->
                            <el-input style="width: 82%;" v-model="editForm2.identityNumber"></el-input>
                        </el-form-item>
                        <el-form-item label="姓名:" prop="name">
                            <el-input style="width: 82%;" v-model="editForm2.name"></el-input>
                        </el-form-item>
                        <el-form-item label="联系方式:" prop="phone">
                            <el-input style="width: 82%;" v-model="editForm2.phone"></el-input>
                        </el-form-item>
                    </el-form>
                    <!--            底部区域-->
                    <span slot="footer" class="dialog-footer">
                    <el-button style="margin-right:20px" @click="cancelEdit2()">取 消</el-button>
                    <el-button style="margin-left:20px" type="primary" @click="editFrequent()">修 改</el-button>
                </span>
                </el-dialog>


                <el-divider></el-divider>
                <!--            分页区域-->
                <el-pagination
                    @size-change="handleSizeChange2"
                    @current-change="handleCurrentChange2"
                    :current-page="pageNumber2"
                    :page-sizes="[1, 2, 5, 10]"
                    :page-size="pageSize2"
                    layout="total, sizes, prev, pager, next, jumper"
                    :total="totalCount2">
                </el-pagination>
            </div>
        </el-card>
        <br>
        <el-card class="el-card" style="width: 80%; margin:auto;">
            <div>
                <div>
                    <h2>收货地址：</h2>
                </div>

                默认收获地址：
                {{ defaultAddressList.province }}省(市)  {{ defaultAddressList.city }}市  {{ defaultAddressList.region }}县(区)  {{ defaultAddressList.street }}街道 {{ defaultAddressList.details }}
                <br>
                收货人：{{ defaultAddressList.receiver }}
                <br>
                联系方式：{{ defaultAddressList.phone }}
                <br><br>
                收获地址：
                <el-button type="primary" @click="showAddAddress()">添加收货地址</el-button>
                <el-table :data="addressList" style="width: 100%" :row-class-name="tableRowClassName">
                    <el-table-column prop="receiver" label="收货人"></el-table-column>
                    <el-table-column prop="phone" label="联系方式"></el-table-column>
                    <el-table-column label="地址">
                        <el-table-column prop="province" label="省份"></el-table-column>
                        <el-table-column prop="city" label="城市"></el-table-column>
                        <el-table-column prop="region" label="区"></el-table-column>
                        <el-table-column prop="street" label="街道"></el-table-column>
                    </el-table-column>
                    <el-table-column label="操作" width="250px">
                        <template slot-scope="scope">
                            <el-button type="text" @click="showAddress(scope.row.addressId)">查看详情</el-button>
                            <el-button type="text" @click="deleteAddress(scope.row.addressId)">删除</el-button>
                            <el-button type="text" @click="showEditAddress(scope.row.addressId)">编辑</el-button>
                            <el-button type="text" @click="setDefaultAddress(scope.row.addressId)">设为默认</el-button>
                        </template>
                    </el-table-column>
                </el-table>
                <!--        添加收获地址对话框-->
                <el-dialog title="添加收货地址" :visible.sync="addDialogVisible" width="630px" top="60px" center>
                    <!--            内容主体区域 放置一个表单-->
                    <!--绑定到addForm中，绑定验证规则对象addFormRules 表单校验项的引用为addFormRef-->
                    <el-form :model="addForm" :rules="addFormRules" ref="addFormRef" label-width="100px">
                        <!-- prop属性指定验证规则-->
                        <el-form-item label="收货人:" prop="receiver">
                            <!--v-model双向绑定-->
                            <el-input style="width: 82%;" v-model="addForm.receiver"></el-input>
                        </el-form-item>
                        <el-form-item label="联系方式:" prop="phone">
                            <el-input style="width: 82%;" v-model="addForm.phone"></el-input>
                        </el-form-item>
                        <el-form-item label="省:" prop="province">
                            <el-input style="width: 82%;" v-model="addForm.province"></el-input>
                        </el-form-item>
                        <el-form-item label="市:" prop="city">
                            <el-input style="width: 82%;" v-model="addForm.city"></el-input>
                        </el-form-item>
                        <el-form-item label="区:" prop="region">
                            <el-input style="width: 82%;" v-model="addForm.region"></el-input>
                        </el-form-item>
                        <el-form-item label="街道:" prop="street">
                            <el-input style="width: 82%;" v-model="addForm.street"></el-input>
                        </el-form-item>
                        <el-form-item label="详细地址:" prop="details">
                            <el-input style="width: 82%;" type="textarea"
                                      :autosize="{ minRows: 3, maxRows: 4}" v-model="addForm.details"></el-input>
                        </el-form-item>
                    </el-form>
                    <!--            底部区域-->
                    <span slot="footer" class="dialog-footer">
                    <el-button style="margin-right:20px" @click="cancelAdd()">取 消</el-button>
                    <el-button style="margin-left:20px" type="primary" @click="addAddress()">确 定</el-button>
                </span>
                </el-dialog>
                <!--        展示收获地址对话框-->
                <el-dialog title="收货地址详情" :visible.sync="showDialogVisible" width="630px" top="60px" center>
                    <!--            内容主体区域 放置一个表单-->
                    <el-form :model="showForm" label-width="100px">
                        <!-- prop属性指定验证规则-->
                        <el-form-item label="收货人:" prop="receiver">
                            <!--v-model双向绑定-->
                            <el-input style="width: 82%;" v-model="showForm.receiver" :readonly="true"></el-input>
                        </el-form-item>
                        <el-form-item label="联系方式:" prop="phone">
                            <el-input style="width: 82%;" v-model="showForm.phone" :readonly="true"></el-input>
                        </el-form-item>
                        <el-form-item label="省:" prop="province">
                            <el-input style="width: 82%;" v-model="showForm.province" :readonly="true"></el-input>
                        </el-form-item>
                        <el-form-item label="市:" prop="city">
                            <el-input style="width: 82%;" v-model="showForm.city" :readonly="true"></el-input>
                        </el-form-item>
                        <el-form-item label="区:" prop="region">
                            <el-input style="width: 82%;" v-model="showForm.region" :readonly="true"></el-input>
                        </el-form-item>
                        <el-form-item label="街道:" prop="street">
                            <el-input style="width: 82%;" v-model="showForm.street" :readonly="true"></el-input>
                        </el-form-item>
                        <el-form-item label="详细地址:" prop="details">
                            <el-input style="width: 82%;" type="textarea"
                                      :autosize="{ minRows: 3, maxRows: 4}" v-model="showForm.details" :readonly="true"></el-input>
                        </el-form-item>
                    </el-form>
                </el-dialog>
                <!--        编辑收获地址对话框-->
                <el-dialog title="编辑收货地址" :visible.sync="editDialogVisible" width="630px" top="60px" center>
                    <!--            内容主体区域 放置一个表单-->
                    <!--绑定到addForm中，绑定验证规则对象addFormRules 表单校验项的引用为addFormRef-->
                    <el-form :model="editForm" :rules="editFormRules" ref="editFormRef" label-width="100px">
                        <!-- prop属性指定验证规则-->
                        <el-form-item label="收货人:" prop="receiver">
                            <!--v-model双向绑定-->
                            <el-input style="width: 82%;" v-model="editForm.receiver"></el-input>
                        </el-form-item>
                        <el-form-item label="联系方式:" prop="phone">
                            <el-input style="width: 82%;" v-model="editForm.phone"></el-input>
                        </el-form-item>
                        <el-form-item label="省:" prop="province">
                            <el-input style="width: 82%;" v-model="editForm.province"></el-input>
                        </el-form-item>
                        <el-form-item label="市:" prop="city">
                            <el-input style="width: 82%;" v-model="editForm.city"></el-input>
                        </el-form-item>
                        <el-form-item label="区:" prop="region">
                            <el-input style="width: 82%;" v-model="editForm.region"></el-input>
                        </el-form-item>
                        <el-form-item label="街道:" prop="street">
                            <el-input style="width: 82%;" v-model="editForm.street"></el-input>
                        </el-form-item>
                        <el-form-item label="详细地址:" prop="details">
                            <el-input style="width: 82%;" type="textarea"
                                      :autosize="{ minRows: 3, maxRows: 4}" v-model="editForm.details"></el-input>
                        </el-form-item>
                    </el-form>
                    <!--            底部区域-->
                    <span slot="footer" class="dialog-footer">
                    <el-button style="margin-right:20px" @click="cancelEdit()">取 消</el-button>
                    <el-button style="margin-left:20px" type="primary" @click="editAddress()">修 改</el-button>
                </span>
                </el-dialog>


                <el-divider></el-divider>
                <!--            分页区域-->
                <el-pagination
                    @size-change="handleSizeChange"
                    @current-change="handleCurrentChange"
                    :current-page="pageNumber"
                    :page-sizes="[1, 2, 5, 10]"
                    :page-size="pageSize"
                    layout="total, sizes, prev, pager, next, jumper"
                    :total="totalCount">
                </el-pagination>
            </div>
        </el-card>
    </div>
</template>


<script>
import ossClient from "@/assets/config/aliyun.oss.client";

export default {
    name: "SelfInformation",
    data() {
        return {
            loading: true,
            //上传图片相关
            images: [],
            uploadConf: {
                region: null,
                accessKeyId: null,
                accessKeySecret: null,
                bucket: null,
            },
            form: {
                age: '',
                email: '',
                gender: '',
                icon: '',
                identityNumber: '',
                name: '',
                realName: '',
                phone: '',
            },


            totalCount: 0,
            pageNumber: 1,
            pageSize: 5,

            totalCount2: 0,
            pageNumber2: 1,
            pageSize2: 10,

            addressList: [],
            defaultAddressList: [],
            frequentList: [],
            defaultFrequentList: [],

            addDialogVisible: false,
            editDialogVisible: false,
            showDialogVisible: false,
            addFormRules: {
                receiver: [
                    {required: true, message: '请输入收货人', trigger: 'blur'},
                ],
                phone: [
                    {required: true, message: '请输入联系方式', trigger: 'blur'},
                ],
                province: [
                    {required: true, message: '请输入省份', trigger: 'blur'},
                ],
                city: [
                    {required: true, message: '请输入城市', trigger: 'blur'},
                ],
                region: [
                    {required: true, message: '请输入区', trigger: 'blur'},
                ],
                street: [
                    {required: true, message: '请输入街道', trigger: 'blur'},
                ],
            },
            editFormRules: {
                receiver: [
                    {required: true, message: '请输入收货人', trigger: 'blur'},
                ],
                phone: [
                    {required: true, message: '请输入联系方式', trigger: 'blur'},
                ],
                province: [
                    {required: true, message: '请输入省份', trigger: 'blur'},
                ],
                city: [
                    {required: true, message: '请输入城市', trigger: 'blur'},
                ],
                region: [
                    {required: true, message: '请输入区', trigger: 'blur'},
                ],
                street: [
                    {required: true, message: '请输入街道', trigger: 'blur'},
                ],
            },
            addForm: {
                receiver: '',
                phone: '',
                province: '',
                city: '',
                region: '',
                street: '',
                details: '',
            },
            showForm: {
                receiver: '',
                phone: '',
                province: '',
                city: '',
                region: '',
                street: '',
                details: '',
            },
            editForm: {
                receiver: '',
                phone: '',
                province: '',
                city: '',
                region: '',
                street: '',
                details: '',
                addressId: '',
            },

            addDialogVisible2: false,
            editDialogVisible2: false,
            showDialogVisible2: false,
            addFormRules2: {
                identityNumber: [
                    {required: true, message: '请输入身份证号', trigger: 'blur'},
                    {min: 17, max: 17, message: '身份证号必须17位', trigger: 'blur'}
                ],
                name: [
                    {required: true, message: '请输入姓名', trigger: 'blur'},
                ],
                phone: [
                    {required: true, message: '请输入电话号码', trigger: 'blur'},
                    {min: 11, max: 11, message: '电话号码必须11位', trigger: 'blur'}
                ],
            },
            editFormRules2: {
                identityNumber: [
                    {required: true, message: '请输入身份证号', trigger: 'blur'},
                    {min: 17, max: 17, message: '身份证号必须17位', trigger: 'blur'}
                ],
                name: [
                    {required: true, message: '请输入姓名', trigger: 'blur'},
                ],
                phone: [
                    {required: true, message: '请输入电话号码', trigger: 'blur'},
                    {min: 11, max: 11, message: '电话号码必须11位', trigger: 'blur'}
                ],
            },
            addForm2: {
                identityNumber: '',
                name: '',
                phone: '',
                frequentId: '',
            },
            showForm2: {
                identityNumber: '',
                name: '',
                phone: '',
                frequentId: '',
            },
            editForm2: {
                identityNumber: '',
                name: '',
                phone: '',
                frequentId: '',
            },
        };
    },
    created() {
        this.getUserInfo();

        this.getFrequentList();
        this.getDefaultFrequentList();

        this.getAddressList();
        this.getDefaultAddressList();
    },
    methods: {

        tableRowClassName({row, rowIndex}) {
            if (this.addressList[rowIndex].addressId === this.defaultAddressList.addressId) {
                return 'warning-row';
            }
            return '';
        },

        tableRowClassName2({row, rowIndex}) {
            if (this.frequentList[rowIndex].frequentId === this.defaultFrequentList.frequentId) {
                return 'warning-row';
            }
            return '';
        },

        onSubmit() {
            console.log('submit!');
        },

        // 初始化
        async init() {
            //获取阿里云token  这里是后台返回来的数据
            this.uploadConf.region = "oss-cn-shanghai";
            this.uploadConf.accessKeyId = "LTAI4FzMDhgBN9LMBr71T3Ny";
            this.uploadConf.accessKeySecret = "hTPgQQSyBgEDnfMNe06RPf8ecDafpz";
            this.uploadConf.bucket = "tongji-boying";
        },
        // 阿里云OSS上传
        uploadHttp({ file }) {
            this.init();
            const { imgName } = "ALIOSS_IMG_";
            const fileName = `${imgName}/${Date.parse(new Date())}`; //定义唯一的文件名
            ossClient(this.uploadConf)
                .put(fileName, file, {
                    ContentType: "image/jpeg",
                })
                .then(({ res, url, name }) => {
                    if (res && res.status === 200) {
                        console.log(`阿里云OSS上传图片成功回调`, res, url, name);
                        console.log(url);
                        this.form.icon = url;
                    }
                })
                .catch((err) => {
                    console.log(`阿里云OSS上传图片失败回调`, err);
                });
        },
        // 图片限制
        beforeAvatarUpload(file) {
            const isJPEG = file.name.split(".")[1] === "jpeg";
            const isJPG = file.name.split(".")[1] === "jpg";
            const isPNG = file.name.split(".")[1] === "png";
            const isLt500K = file.size / 1024 / 1024 / 5 < 2;
            if (!isJPG && !isJPEG && !isPNG) {
                this.$message.error("上传图片只能是 JPEG/JPG/PNG 格式!");
            }
            if (!isLt500K) {
                this.$message.error("单张图片大小不能超过 5MB!");
            }
            return (isJPEG || isJPG || isPNG) && isLt500K;
        },
        // 移除图片
        handleRemove(file, fileList) {
            console.log(`移除图片回调`, fileList);
        },

        // 获取用户信息
        async getUserInfo(){
            var result = await this.$http.post(this.$api.getUserInfoUrl);
            this.form.name=result.data.data.username;
            this.form.realName=result.data.data.realName;
            this.form.icon=result.data.data.icon;
            this.form.gender=result.data.data.gender===true?'男':'女';
            this.form.age=result.data.data.age;
            this.form.identityNumber=result.data.data.identityNumber;
            this.form.email=result.data.data.email;
            this.form.phone=result.data.data.phone;

            // console.log("修改前"+this.form.icon);
        },
        async updateUserInfo(){
            // console.log("修改后"+this.form.icon);
            console.log(this.form.gender);
            let gender;
            if(this.form.gender==='man')
                gender=true;
            else if(this.form.gender==='woman')
                gender=false;
            let result = await this.$http.post(this.$api.updateUserInfoUrl,{
                realName: this.form.realName,
                icon: this.form.icon,
                gender: gender,
                age: this.form.age,
                identityNumber: this.form.identityNumber,
                email: this.form.email,
            });
            // console.log(result);
            await this.getUserInfo();
        },

        // 联系人相关
        //监听pageSize改变的事件
        async handleSizeChange2(newSize)
        {
            this.pageSize2 = newSize;
            this.pageNumber2 = 1;
            // console.log("pageSize:"+this.pageSize);
            await this.getFrequentList();
            await this.getDefaultFrequentList();
        },
        //监听pageNum改变的事件
        async handleCurrentChange2(newPage)
        {
            this.pageNumber2 = newPage;
            // console.log("pageNumber:"+this.pageNumber);
            await this.getFrequentList();
            await this.getDefaultFrequentList();
        },
        async getFrequentList(){
            let result = await this.$http.post(this.$api.getFrequentListUrl,{
                pageNum: this.pageNumber2,
                pageSize: this.pageSize2,
            });
            // console.log(result.data.data.list);
            if(result.data.code===200){
                this.frequentList=result.data.data.list;
            }
            else{
                this.frequentList=[];
            }
            this.totalCount2=result.data.data.total;
        },
        async getDefaultFrequentList(){
            let result = await this.$http.post(this.$api.getDefaultFrequentUrl);
            // console.log(result);
            if(result.data.code===200){
                this.defaultFrequentList=result.data.data;
            }
            else{
                this.defaultFrequentList=[];
            }
        },
        async setDefaultFrequentList(id){
            await this.$http.post(this.$api.setDefaultFrequentUrl + "/" + id);
            await this.getFrequentList();
            await this.getDefaultFrequentList();
        },
        async showAddFrequent(){
            this.addDialogVisible2=true;
        },
        async addFrequent(){
            this.$refs.addFormRef2.validate(
                async valid =>
                {
                    if (!valid) return;
                    await this.$http.post(this.$api.addFrequentUrl,{
                        identityNumber: this.addForm2.identityNumber,
                        name: this.addForm2.name,
                        phone: this.addForm2.phone,
                    });
                    await this.getFrequentList();
                    await this.getDefaultFrequentList();
                    this.addDialogVisible2=false;
                    this.addForm2.identityNumber='';
                    this.addForm2.name='';
                    this.addForm2.phone='';
                    this.$message.info("添加联系人成功!");
                }
            );
        },
        async cancelAdd2(){
            this.addDialogVisible2=false;
            this.addForm2.identityNumber='';
            this.addForm2.name='';
            this.addForm2.phone='';
            // console.log(this.addForm);
            this.$message.info("取消添加联系人!");
        },
        async deleteFrequent(id)
        {
            // console.log(id);
            let result = await this.$http.post(this.$api.deleteFrequentUrl + "/" + id);
            // console.log(result);
            await this.getFrequentList();
            await this.getDefaultFrequentList();
            this.$message.info("删除联系人成功!");
        },
        async showEditFrequent(id){
            let result = await this.$http.post(this.$api.getFrequentUrl + "/" + id);
            // console.log(result);
            this.editForm2=result.data.data;
            this.editDialogVisible2=true;
        },
        async cancelEdit2(){
            this.editDialogVisible2=false;
            this.$message.info("取消编辑联系人!");
        },
        async editFrequent(){
            this.$refs.editFormRef2.validate(
                async valid =>
                {
                    if (!valid) return;
                    // console.log(this.editForm.addressId);
                    await this.$http.post(this.$api.updateFrequentUrl + "/" + this.editForm2.frequentId, this.editForm2);
                    await this.getFrequentList();
                    await this.getDefaultFrequentList();
                    this.editDialogVisible2=false;
                    this.$message.info("编辑联系人成功!");
                }
            );
        },

        // 收货地址相关
        // 获取收货地址
        async getAddressList(){

            var result = await this.$http.post(this.$api.getAddressListUrl,
                {
                    pageNum: this.pageNumber,
                    pageSize: this.pageSize,
                });

            if(result.data.code===200){
                this.addressList=result.data.data.list;
            }
            else{
                this.addressList=[];
            }
            this.totalCount=result.data.data.total;
            // console.log(this.addressList);
        },
        // 获取默认收货地址
        async getDefaultAddressList(){
            var result = await this.$http.post(this.$api.getDefaultAddressUrl);
            // console.log(result.data.data);
            if(result.data.code===200){
                this.defaultAddressList=result.data.data;
            }
            else{
                this.defaultAddressList=[];
            }
            // console.log(this.defaultAddressList);
        },
        //监听pageSize改变的事件
        async handleSizeChange(newSize)
        {
            this.pageSize = newSize;
            this.pageNumber = 1;
            // console.log("pageSize:"+this.pageSize);
            await this.getAddressList();
            this.getDefaultAddressList();
        },
        //监听pageNum改变的事件
        async handleCurrentChange(newPage)
        {
            this.pageNumber = newPage;
            // console.log("pageNumber:"+this.pageNumber);
            await this.getAddressList();
            this.getDefaultAddressList();
        },
        async deleteAddress(id)
        {
            // console.log(id);
            await this.$http.post(this.$api.deleteAddressUrl + "/" + id);
            this.getAddressList();
            this.getDefaultAddressList();
            this.$message.info("删除收货地点成功!");
        },
        async showAddAddress(){
            this.addDialogVisible=true;
        },
        async addAddress(){
            this.$refs.addFormRef.validate(
                async valid =>
                {
                    if (!valid) return;
                    await this.$http.post(this.$api.addAddressUrl,{
                        receiver: this.addForm.receiver,
                        phone: this.addForm.phone,
                        province: this.addForm.province,
                        city: this.addForm.city,
                        region: this.addForm.region,
                        street: this.addForm.street,
                        details: this.addForm.details,
                    });
                    this.getAddressList();
                    this.getDefaultAddressList();
                    this.addDialogVisible=false;

                    this.addForm.receiver='';
                    this.addForm.phone='';
                    this.addForm.province='';
                    this.addForm.city='';
                    this.addForm.region='';
                    this.addForm.street='';
                    this.addForm.details='';

                    // console.log(this.addForm);
                    this.$message.info("添加收货地点成功!");
                }
            );
        },
        async cancelAdd(){
            this.addDialogVisible=false;

            this.addForm.receiver='';
            this.addForm.phone='';
            this.addForm.province='';
            this.addForm.city='';
            this.addForm.region='';
            this.addForm.street='';
            this.addForm.details='';

            // console.log(this.addForm);
            this.$message.info("取消添加收货地点!");
        },
        async setDefaultAddress(id){
            await this.$http.post(this.$api.setDefaultAddressUrl + "/" + id);
            await this.getAddressList();
            await this.getDefaultAddressList();
        },
        async showAddress(id){
            let result = await this.$http.post(this.$api.getAddressUrl + "/" + id);
            this.showForm=result.data.data;
            // console.log(this.showForm);
            this.showDialogVisible=true;
        },
        async showEditAddress(id){
            let result = await this.$http.post(this.$api.getAddressUrl + "/" + id);
            this.editForm=result.data.data;
            this.editForm.addressId=id;
            this.editDialogVisible=true;
        },
        async editAddress(){
            this.$refs.editFormRef.validate(
                async valid =>
                {
                    if (!valid) return;
                    // console.log(this.editForm.addressId);
                    await this.$http.post(this.$api.updateAddressUrl + "/" + this.editForm.addressId, this.editForm);
                    this.getAddressList();
                    this.getDefaultAddressList();
                    this.editDialogVisible=false;
                    this.$message.info("编辑收货地点成功!");
                }
            );

        },
        async cancelEdit(){
            this.editDialogVisible=false;
            this.$message.info("取消编辑收货地点!");
        },
    },

};
</script>



<style scoped>

.el-row {
    margin-bottom: 20px;
    display:flex;
    flex-wrap: wrap;
}

.el-row .el-card {
    min-width: 100%;
    height: 100%;
    margin-right: 20px;
    transition: all .5s;
}

body {
    color: rgba(255, 255, 255, 0.65);
    background-color: #24292e;
    /*background-image: url(../../assets/img/star-bg.svg),*/
    /*linear-gradient(#191c20, #24292e 15%);*/
    background-repeat: repeat-x;
    background-position: center 0, 0 0, 0 0;
    margin-left: 0;
    margin-top: 1;
}

#img-context {
    text-align: center;
    font-size: 17px;
    color: #b0b0b0;
    margin-top: 50px;
}

/*.el-upload__tip {*/
/*    text-align: center;*/
/*    font-size: 8px;*/
/*    color: rgba(52, 52, 52, 0.7);*/
/*}*/

.el-table .warning-row {
    background: oldlace;
}

.el-table .success-row {
    background: #f0f9eb;
}
</style>


<style>
.el-table .warning-row {
    background: oldlace;
}

.el-table .success-row {
    background: #f0f9eb;
}
</style>
