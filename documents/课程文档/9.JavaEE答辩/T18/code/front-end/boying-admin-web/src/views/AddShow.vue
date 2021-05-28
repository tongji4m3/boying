<template>
  <el-card class="form-container" shadow="never" v-loading="loading">
    <!-- <div class="procedures" v-loading="loading">
      <el-steps :space="400" :active="0" finish-status="success">
        <el-step title="进行中"></el-step>
        <el-step title="步骤 2"></el-step>
        <el-step title="步骤 3"></el-step>
      </el-steps>
    </div> -->
    <el-form
      :model="ruleForm"
      :rules="rules"
      ref="ruleForm"
      label-width="120px"
      class="demo-ruleForm"
    >
      <el-form-item label="演出名称" prop="name">
        <el-input v-model="ruleForm.name"></el-input>
      </el-form-item>
      <el-form-item
        label="演出目录编号"
        prop="categoryId"
        style="text-align: left"
      >
        <el-select v-model="ruleForm.categoryId" placeholder="请选择演出目录">
          <el-option label="编号一" value="1"></el-option>
          <el-option label="编号二" value="2"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="演出具体信息" prop="details">
        <el-input v-model="ruleForm.details"></el-input>
      </el-form-item>

      <el-form-item label="演出海报图片" prop="poster">
        <!--    上传个人头像组件-->
        <el-upload
          class="upload"
          action
          :drag="true"
          :multiple="true"
          :file-list="images"
          :http-request="uploadHttp"
          :before-upload="beforeAvatarUpload"
          :on-remove="handleRemove"
          style="text-align: left"
        >
          <i class="el-icon-plus avatar-uploader-icon"></i>
          <p id="img-context">上传演出图片</p>
          <div class="el-upload__tip" slot="tip">
            只能上传jpg/jpeg/png文件，且不超过5MB
          </div>
        </el-upload>
      </el-form-item>

      <el-form-item label="演出最低价格" prop="minPrice" style="width: 50%">
        <el-input v-model="ruleForm.minPrice"></el-input>
      </el-form-item>
      <el-form-item label="演出最高价格" prop="maxPrice" style="width: 50%">
        <el-input v-model="ruleForm.maxPrice"></el-input>
      </el-form-item>
      <!-- <el-form-item label="演出展示优先级" prop="region">
        <el-select v-model="ruleForm.region" placeholder="请选择演出展示优先级">
          <el-option label="0" value="shanghai"></el-option>
          <el-option label="1" value="beijing"></el-option>
        </el-select>
      </el-form-item> -->

      <el-form-item
        label="演出展示优先级"
        prop="weight"
        style="text-align: left"
      >
        <el-radio-group v-model="ruleForm.weight">
          <el-radio label="0"></el-radio>
          <el-radio label="1"></el-radio>
          <el-radio label="2"></el-radio>
          <el-radio label="3"></el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="演出城市" prop="city">
        <el-input v-model="ruleForm.city"></el-input>
      </el-form-item>
      <el-form-item label="具体演出地址" prop="address">
        <el-input type="textarea" v-model="ruleForm.address"></el-input>
      </el-form-item>
      <el-form-item label="演出开始时间" required>
        <el-col :span="11">
          <el-form-item prop="dayStart">
            <el-date-picker
              type="date"
              placeholder="选择日期"
              v-model="ruleForm.dayStart"
              style="width: 100%"
              format="yyyy 年 MM 月 dd 日 HH 小时 mm 分钟 ss 秒"
              value-format="yyyy-MM-dd HH:mm:ss"
            ></el-date-picker>
          </el-form-item>
        </el-col>
      </el-form-item>
      <el-form-item label="演出结束时间" required>
        <el-col :span="11">
          <el-form-item prop="dayEnd">
            <el-date-picker
              type="date"
              placeholder="选择日期"
              v-model="ruleForm.dayEnd"
              style="width: 100%"
              format="yyyy 年 MM 月 dd 日 HH 小时 mm 分钟 ss 秒"
              value-format="yyyy-MM-dd HH:mm:ss"
            ></el-date-picker>
          </el-form-item>
        </el-col>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm('ruleForm')"
          >立即创建</el-button
        >
        <el-button @click="resetForm('ruleForm')">重置</el-button>
      </el-form-item>
    </el-form>
  </el-card>
</template>

<script>
import ossClient from "../assets/config/aliyun.oss.client";
import axios from "axios";
import api from "@/assets/api.js";
import qs from "qs";

export default {
  name: "",
  props: [""],
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
      ruleForm: {
        //更改这部分和prop相匹配
        name: "",
        categoryId: "",
        poster: "",
        details: "",
        minPrice: "",
        maxPrice: "",
        weight: -1,
        city: "",
        address: "",
        dayStart: "",
        dayEnd: "",
      },
      rules: {
        name: [
          { required: true, message: "请输入活动名称", trigger: "blur" },
          { min: 3, max: 5, message: "长度在 3 到 5 个字符", trigger: "blur" },
        ],
        categoryId: [
          { required: true, message: "请选择演出目录", trigger: "change" },
        ],
        details: [
          { required: true, message: "请输入演出具体信息", trigger: "blur" },
        ],
        poster: [
          { required: true, message: "请放入演出海报图片", trigger: "blur" },
        ],
        minPrice: [
          { required: true, message: "请输入演出最低价格", trigger: "blur" },
        ],
        maxPrice: [
          { required: true, message: "请输入演出最高价格", trigger: "blur" },
        ],
        city: [{ required: true, message: "请输入演出城市", trigger: "blur" }],
        dayStart: [
          {
            type: "string",
            required: true,
            message: "请选择日期",
            trigger: "change",
          },
        ],
        dayEnd: [
          {
            type: "string",
            required: true,
            message: "请选择日期",
            trigger: "change",
          },
        ],

        weight: [
          {
            required: true,
            message: "请选择一个展示优先级",
            trigger: "change",
          },
        ],
        address: [
          { required: true, message: "请填写具体地址", trigger: "blur" },
        ],
      },
    };
  },

  components: {},

  computed: {},

  beforeMount() {},

  mounted() {
    setTimeout(() => {
      this.loading = false;
    }, 500);
  },

  methods: {
    submitForm(formName) {
      this.$refs[formName].validate(async (valid) => {
        if (!valid || this.ruleForm.poster == "") {
          console.log(this.ruleForm.poster);
          console.log("error submit!!");
          this.$message.error("添加失败，请完整填写表单");
          return false;
        } else {
          try {
            console.log(this.ruleForm.dayStart);
            console.log("mounted");
            const res = await axios.post(
              `${api.API_URL}/show/create`,
              this.ruleForm, 
            );
            console.log(res);
            if (res.data.code == 200) {
              this.$message.success("添加演出成功,即将跳转演出界面");
              // 等待不起作用
               setTimeout(() => {
                this.loading = false;
              }, 500);
              console.log(res.data.data);
              this.$router.push('/show')
            }
          } catch (err) {
            console.log(err);
            this.$message.error("添加失败，数据填写错误");
          }
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },

    /**
     * 初始化
     */
    async init() {
      //获取阿里云token  这里是后台返回来的数据
      this.uploadConf.region = "oss-cn-shanghai";
      this.uploadConf.accessKeyId = "LTAI4FzMDhgBN9LMBr71T3Ny";
      this.uploadConf.accessKeySecret = "hTPgQQSyBgEDnfMNe06RPf8ecDafpz";
      this.uploadConf.bucket = "tongji-boying";
    },
    /**
     * 阿里云OSS上传
     */
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
            this.ruleForm.poster = url;
          }
        })
        .catch((err) => {
          console.log(`阿里云OSS上传图片失败回调`, err);
        });
    },

    /**
     * 图片限制
     */
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

    /**
     * 移除图片
     */
    handleRemove(file, fileList) {
      console.log(`移除图片回调`, fileList);
    },
  },

  watch: {},
};
</script>
<style scoped>
/* .procedures {
  text-align: left;
  margin: 50px;
  margin-left: 200px;
} */

.form-container {
  margin: 50px;
  margin-left: 200px;
  width: 800px;
}
</style>