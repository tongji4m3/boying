<template>
<body id="poster">
  <el-container>
    <el-form
      ref="form"
      :model="form"
      :rules="formRules"
      class="login-container"
      label-position="left"
      label-width="0px"
    >
      <h3 class="login_title">登录</h3>
      <el-form-item prop="username">
        <el-input type="text" v-model="form.username" auto-complete="off" placeholder="账号" v-on:keyup.enter.native="onSubmit"></el-input>
      </el-form-item>

      <el-form-item prop="password">
        <el-input type="password" v-model="form.password" auto-complete="off" placeholder="密码" v-on:keyup.enter.native="onSubmit"></el-input>
      </el-form-item>

      <el-form-item style="width: 100%">
        <el-button
          type="primary"
          style="width: 100%;background: #505458;border: none"
          v-on:click="onSubmit"
        >登录</el-button>
      </el-form-item>
    </el-form>
  </el-container>
</body>
</template>

<script>
import api from "@/assets/api.js";
import axios from "axios";
import qs from "qs";
export default {
  data() {
    return {
      //登录表单的数据绑定对象
      form: {
        username: "",
        password: "",
      },
      //这是表单的验证规则
      formRules: {
        //验证用户名是否合法
        username: [
          { required: true, message: "请输入登录账号", trigger: "blur" },
        ],
        //验证密码是否合法
        password: [
          { required: true, message: "请输入登录密码", trigger: "blur" },
        ],
      },
    };
  },
  methods: {
    async onSubmit() {
      //点击登录先进行表单预验证失败，直接返回不发起请求
      this.$refs.form.validate((valid) => {
        if (!valid) return;
      });

      try {
        const res = await axios.post(
          `${api.API_URL}/login/login`,
          qs.stringify(this.form)
        );
        if (res.status == 200) {
          window.sessionStorage.setItem("token", res.data.data["token"]);
          this.$router.push("/home");
          this.$message.success("登录成功");
        }
      } catch (err) {
        console.log(err);
        this.$message.error("登录失败");
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
  margin: 150px auto;
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
</style>
