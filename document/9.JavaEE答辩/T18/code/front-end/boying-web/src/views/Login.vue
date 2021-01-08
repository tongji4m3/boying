<template>
  <body id="poster">
    <el-container>
      <el-form
        ref="loginFormRef"
        :model="loginForm"
        :rules="loginFormRules"
        class="login-container"
        label-position="left"
        label-width="0px"
        v-loading="loading"
      >
        <h3 class="login_title">登录</h3>
        <div class="login_header_title">
          <span :class="{ on: loginType == 0 }" @click="loginType = 0"
            >账号密码</span
          >
          <span :class="{ on: loginType == 1 }" @click="loginType = 1"
            >手机号密码</span
          >
          <span :class="{ on: loginType == 2 }" @click="loginType = 2"
            >手机号验证码</span
          >
        </div>
        <div v-if="loginType == 0">
          <el-form-item prop="username">
            <el-input
              type="text"
              v-model="loginForm.username"
              auto-complete="off"
              placeholder="账号"
              v-on:keyup.enter.native="login"
            ></el-input>
          </el-form-item>

          <el-form-item prop="password">
            <el-input
              type="password"
              v-model="loginForm.password"
              auto-complete="off"
              placeholder="密码"
              v-on:keyup.enter.native="login"
            ></el-input>
          </el-form-item>

          <el-form-item style="width: 100%">
            <el-button
              type="primary"
              style="width: 100%; background: #505458; border: none"
              v-on:click="login"
              >登录</el-button
            >
          </el-form-item>
        </div>

        <div v-if="loginType == 1">
          <el-form-item prop="telephone">
            <el-input
              type="text"
              v-model="loginTel1Form.telephone"
              auto-complete="off"
              placeholder="手机号"
              v-on:keyup.enter.native="loginTel1"
            ></el-input>
          </el-form-item>

          <el-form-item prop="password">
            <el-input
              type="password"
              v-model="loginTel1Form.password"
              auto-complete="off"
              placeholder="密码"
              v-on:keyup.enter.native="loginTel1"
            ></el-input>
          </el-form-item>

          <el-form-item style="width: 100%">
            <el-button
              type="primary"
              style="width: 100%; background: #505458; border: none"
              v-on:click="loginTel1"
              >登录</el-button
            >
          </el-form-item>
        </div>

        <div v-if="loginType == 2">
          <el-form-item prop="telephone">
            <el-input
              type="text"
              v-model="loginTel2Form.telephone"
              auto-complete="off"
              placeholder="手机号"
              v-on:keyup.enter.native="loginTel2"
            ></el-input>
          </el-form-item>

          <el-form-item prop="authCode">
            <el-input
              type="text"
              v-model="loginTel2Form.authCode"
              auto-complete="off"
              placeholder="验证码"
              class="authInput"
              v-on:keyup.enter.native="loginTel2"
            ></el-input>
            <el-button class="authButton" v-on:click="getAuthCode"
              >获取验证码</el-button
            >
          </el-form-item>

          <el-form-item style="width: 100%">
            <el-button
              type="primary"
              style="width: 100%; background: #505458; border: none"
              v-on:click="loginTel2"
              >登录</el-button
            >
          </el-form-item>
        </div>

        <el-form-item style="width: 30%; margin-left: 200px">
          <el-button style="width: 100%; border: none" v-on:click="goToRegister"
            >立即注册</el-button
          >
        </el-form-item>
      </el-form>
    </el-container>
  </body>
</template>

<script>
import api from "@/assets/config/api.js";
import axios from "axios";
import qs from "qs";
export default {
  data() {
    return {
      loginType: 0,
      loading: true,
      //登录表单数据绑定
      loginForm: {
        username: "",
        password: "",
        // imgUrl: "",
      },
      loginTel1Form: {
        telephone: "",
        password: "",
      },
      loginTel2Form: {
        telephone: "",
        authCode: "",
      },
      show: true,
      //表单的验证规则
      //因为切换登录方式不会切换预验证，暂时不要预验证
      loginFormRules: {
      //   //    验证用户名是否合法
      //   username: [
      //     { required: true, message: "请输入用户名", trigger: "blur" },
      //     {
      //       min: 3,
      //       max: 10,
      //       message: "用户名必须在3-10个字符之间",
      //       trigger: "blur",
      //     },
      //   ],
      //   //    验证密码是否合法
      //   password: [
      //     { required: true, message: "请输入密码", trigger: "blur" },
      //     {
      //       min: 6,
      //       max: 50,
      //       message: "密码必须在6-15个字符之间",
      //       trigger: "blur",
      //     },
      //   ],
      },
    };
  },
  //回车登录操作
  created() {
    //创建后挂载
    let _this = this;
    setTimeout(() => {
      this.loading = false;
    }, 500);
  },
  methods: {
    async login() {
      console.log("login");
      //点击登录先进行表单预验证失败，直接返回不发起请求
      //因为切换登录方式不会切换预验证，暂时不要预验证

      // this.$refs.loginFormRef.validate((valid) => {
      //   if (!valid) return;
      // });

      try {
        console.log(this.loginForm.username);
        const res = await axios.post(
          this.$api.LoginUrl,
          JSON.stringify(this.loginForm)
        );
        console.log(res);
        if (res.data.code == 200) {
          window.sessionStorage.setItem("token", res.data.data["token"]);
          this.$router.push("/home");
          this.$message.success("登录成功");
        } else {
          this.$message.error("登录失败");
        }
      } catch (err) {
        console.log(err);
        this.$message.error("登录失败");
      }
    },
    async loginTel1() {
      console.log("loginTel1");
      try {
        console.log(this.loginTel1Form);
        const res = await axios.post(
          this.$api.LoginTel1Url,
          JSON.stringify(this.loginTel1Form)
        );

        console.log(res);
        if (res.data.code == 200) {
          window.sessionStorage.setItem("token", res.data.data["token"]);
          this.$router.push("/home");
          this.$message.success("登录成功");
        } else {
          this.$message.error("登录失败");
        }
      } catch (err) {
        console.log(err);
        this.$message.error("登录失败");
      }
    },
    async loginTel2() {
      console.log("loginTel2");
      try {
        const res = await axios.post(
          this.$api.LoginTel2Url,
          JSON.stringify(this.loginTel2Form)
        );

        console.log(res);
        if (res.data.code == 200) {
          window.sessionStorage.setItem("token", res.data.data["token"]);
          this.$router.push("/home");
          this.$message.success("登录成功");
        } else {
          this.$message.error("登录失败");
        }
      } catch (err) {
        console.log(err);
        this.$message.error("登录失败");
      }
    },
    async goToRegister() {
      this.$message.success("跳转至注册界面");
      setTimeout(function () {}, 500);
      this.$router.push("/register");
    },
    async getAuthCode() {
      console.log(this.$api.getAuthCodeUrl);
      try {
        const res = await axios.post(
          this.$api.getAuthCodeUrl,
          this.loginTel2Form.telephone
        );

        console.log(res);
        if (res.data.code == 200) {
          this.$message.success("验证码已成功发送至手机，请注意查收");
        }
      } catch (err) {
        console.log(err);
        this.$message.error("验证码发送失败");
      }
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
}

.login-container {
  border-radius: 15px;
  background-clip: padding-box;
  margin: 70px auto;
  width: 300px;
  padding: 35px 35px 15px 35px;
  background: #fff;
  border: 1px solid #eaeaea;
}

.login_title {
  letter-spacing: 10px;
  margin: 0px auto 40px auto;
  text-align: center;
  color: #505458;
}

/* 下面是肯定要用到的样式 */
.authInput {
  width: 47%;
}

.authButton {
  margin-left: 45px;
}

.login_header_title {
  margin-bottom: 40px;
}

.login_header_title span {
  margin-right: 20px;
  cursor: pointer;
  color: black;
  font-size: 16px;
}

.on {
  color: #3f7dff;
  padding-bottom: 10px;
  border-bottom: 3px solid #3f7dff;
}
</style>
