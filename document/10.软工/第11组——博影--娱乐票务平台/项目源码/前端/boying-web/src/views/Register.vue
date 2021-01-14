<!--有前端验证注册信息是否合理-->
<template>
    <body id="poster">
    <el-scrollbar style="height: 100%">
        <el-form
                ref="registerFormRef"
                :model="registerForm"
                :rules="registerFormRules"
                class="register-container"
                label-position="left"
                label-width="0px"
                v-loading="loading"
                size="medium"
        >
            <h3 class="login_title">注册</h3>
            <el-form-item prop="username">
                <el-input
                        v-model="registerForm.username"
                        placeholder="用户名"
                ></el-input>
            </el-form-item>
            <el-form-item prop="password">
                <el-input
                        type="password"
                        v-model="registerForm.password"
                        placeholder="密码"
                ></el-input>
            </el-form-item>
            <el-form-item prop="confirmPassword">
                <el-input
                        type="password"
                        v-model="registerForm.confirmPassword"
                        placeholder="确认密码"
                ></el-input>
            </el-form-item>
            <el-form-item prop="telephone">
                <el-input
                        v-model="registerForm.telephone"
                        placeholder="手机号"
                ></el-input>
            </el-form-item>
            <el-form-item prop="authCode">
                <el-input
                        v-model="registerForm.authCode"
                        placeholder="验证码"
                        class="authInput"
                ></el-input>
                <el-button class="authButton" v-on:click="getAuthCode"
                >获取验证码
                </el-button
                >
            </el-form-item>

            <el-upload
                    class="upload"
                    action
                    :drag="true"
                    :multiple="true"
                    :file-list="images"
                    :http-request="uploadHttp"
                    :before-upload="beforeAvatarUpload"
                    :on-remove="handleRemove"
            >
                <i class="el-icon-plus avatar-uploader-icon"></i>
                <p id="img-context">上传个人头像</p>
                <div class="el-upload__tip" slot="tip">
                    只能上传jpg/jpeg/png文件，且不超过5MB
                </div>
            </el-upload>
            <el-form-item style="width: 100%">
                <el-button
                        type="primary"
                        style="width: 100%; background: #505458; border: none"
                        v-on:click="register"
                >注册
                </el-button
                >
            </el-form-item>
        </el-form>
    </el-scrollbar>
    </body>
</template>

<script>
    import ossClient from "../assets/config/aliyun.oss.client";
    import axios from "axios";

    export default {
        name: "Upload",
        data()
        {
            let checkPassword = (rule, value, cb) =>
            {
                const regPassword = /^\w{6,50}$/;
                if (regPassword.test(value))
                {
                    //合法密码
                    return cb();
                }
                cb(new Error("密码必须在6-15个字符之间,只能由大小写字母数字下划线组成"));
            };
            let checkConfirmPassword = (rule, value, cb) =>
            {
                const regPassword = this.registerForm.password;
                if (regPassword === value)
                {
                    //合法密码
                    return cb();
                }
                cb(new Error("前后两次输入的密码必须一致!"));
            };
            let checktelephone = (rule, value, cb) =>
            {
                const regNumber = /^1(?:3\d|4[4-9]|5[0-35-9]|6[67]|7[013-8]|8\d|9\d)\d{8}$/;
                if (regNumber.test(value))
                {
                    //合法手机号
                    return cb();
                }
                cb(new Error("输入的手机号不合法!"));
            };
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
                //登录表单数据绑定
                registerForm: {
                    username: "",
                    password: "",
                    confirmPassword: "",
                    telephone: "",
                    authCode: "",
                    icon: "",
                    // imgUrl: "https://tongji4m3.oss-cn-beijing.aliyuncs.com/OIP.jpg",
                },
                //表单的验证规则
                registerFormRules: {
                    //    验证用户名是否合法
                    username: [
                        {required: true, message: "请输入用户名", trigger: "blur"},
                        {
                            min: 3,
                            max: 10,
                            message: "用户名必须在3-10个字符之间",
                            trigger: "blur",
                        },
                    ],
                    //    验证密码是否合法
                    password: [
                        {required: true, message: "请输入密码", trigger: "blur"},
                        {validator: checkPassword, trigger: "blur"},
                    ],
                    confirmPassword: [
                        {required: true, message: "请再次确认密码", trigger: "blur"},
                        {validator: checkConfirmPassword, trigger: "blur"},
                    ],
                    //    验证手机号是否合法
                    telephone: [
                        {required: true, message: "请输入手机号", trigger: "blur"},
                        {validator: checktelephone, trigger: "blur"},
                    ],
                    //    验证验证码是否合法
                    authCode: [{required: true, message: "请验证码", trigger: "blur"}],
                },
            };
        },
        //回车注册操作
        created()
        {
            //创建后挂载
            let _this = this;
            setTimeout(() =>
            {
                this.loading = false;
            }, 500);
            document.onkeydown = function (e)
            {
                let key = window.event.keyCode;

                if (key === 13)
                {
                    _this.register(); //注册
                }
            };
        },
        methods: {
            async register()
            {
                console.log(this.$api.registerUrl);
                //点击注册先进行表单预验证失败，直接返回不发起请求
                this.$refs.registerFormRef.validate((valid) =>
                {
                    if (!valid) return;
                });

                try
                {
                    const res = await axios.post(
                        this.$api.registerUrl,
                        JSON.stringify({
                            username: this.registerForm.username,
                            password: this.registerForm.password,
                            telephone: this.registerForm.telephone,
                            authCode: this.registerForm.authCode,
                            icon: this.registerForm.icon,
                        })
                    );

                    console.log(res);
                    if (res.data.code == 200)
                    {
                        this.$message.success("注册成功，即将跳转登录界面");

                        this.$router.push("/login");
                    } else
                    {
                        this.$message.error("注册失败");
                    }
                } catch (err)
                {
                    console.log(err);
                    this.$message.error("注册失败");
                }
            },

            async getAuthCode()
            {
                console.log(this.registerForm.telephone);
                console.log(this.$api.getAuthCodeUrl);
                try
                {
                    const res = await axios.post(
                        this.$api.getAuthCodeUrl,
                        this.registerForm.telephone
                    );

                    console.log(res);
                    if (res.data.code == 200)
                    {
                        this.$message.success("验证码已成功发送至手机，请注意查收");
                    }
                } catch (err)
                {
                    console.log(err);
                    this.$message.error("验证码发送失败");
                }
            },

            /**
             * 初始化
             */
            async init()
            {
                //获取阿里云token  这里是后台返回来的数据
                this.uploadConf.region = "oss-cn-shanghai";
                this.uploadConf.accessKeyId = "LTAI4FzMDhgBN9LMBr71T3Ny";
                this.uploadConf.accessKeySecret = "hTPgQQSyBgEDnfMNe06RPf8ecDafpz";
                this.uploadConf.bucket = "tongji-boying";
            },
            /**
             * 阿里云OSS上传
             */
            uploadHttp({file})
            {
                this.init();
                const {imgName} = "ALIOSS_IMG_";
                const fileName = `${imgName}/${Date.parse(new Date())}`; //定义唯一的文件名
                ossClient(this.uploadConf)
                    .put(fileName, file, {
                        ContentType: "image/jpeg",
                    })
                    .then(({res, url, name}) =>
                    {
                        if (res && res.status === 200)
                        {
                            console.log(`阿里云OSS上传图片成功回调`, res, url, name);
                            console.log(url);
                            this.registerForm.icon = url;
                        }
                    })
                    .catch((err) =>
                    {
                        console.log(`阿里云OSS上传图片失败回调`, err);
                    });
            },

            /**
             * 图片限制
             */
            beforeAvatarUpload(file)
            {
                const isJPEG = file.name.split(".")[1] === "jpeg";
                const isJPG = file.name.split(".")[1] === "jpg";
                const isPNG = file.name.split(".")[1] === "png";
                const isLt500K = file.size / 1024 / 1024 / 5 < 2;
                if (!isJPG && !isJPEG && !isPNG)
                {
                    this.$message.error("上传图片只能是 JPEG/JPG/PNG 格式!");
                }
                if (!isLt500K)
                {
                    this.$message.error("单张图片大小不能超过 5MB!");
                }
                return (isJPEG || isJPG || isPNG) && isLt500K;
            },

            /**
             * 移除图片
             */
            handleRemove(file, fileList)
            {
                console.log(`移除图片回调`, fileList);
            },
        },
    };
</script>
<style scoped>
    #poster {
        height: 100%;
        width: 100%;
        background-size: cover;
        position: fixed;
        padding: 0;
        margin: 0;
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

    .register-container {
        border-radius: 15px;
        background-clip: padding-box;
        margin: 10px auto;
        width: 350px;
        padding: 35px 35px 15px 35px;
        background: #fff;
        border: 1px solid #eaeaea;
    }

    .login_title {
        letter-spacing: 10px;
        margin: -30px auto 10px auto;
        text-align: center;
        color: #505458;
    }

    #img-context {
        text-align: center;
        font-size: 17px;
        color: #b0b0b0;
        margin-top: 50px;
    }

    .el-upload__tip {
        text-align: center;
        font-size: 8px;
        color: rgba(52, 52, 52, 0.7);
    }

    /* 一下是肯定要用的样式 */
    .authInput {
        width: 55%;
    }

    .authButton {
        margin-left: 45px;
    }
</style>
