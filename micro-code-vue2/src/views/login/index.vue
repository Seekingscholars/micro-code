<template>
  <div class="login-container">
    <el-form
      ref="loginForm"
      :model="loginForm"
      :rules="loginRules"
      class="login-form"
      label-position="left"
    >

      <div class="title-container">
        <h3 class="title">MICRO CODE</h3>
      </div>

      <el-form-item prop="username">
        <el-input
          v-model="loginForm.username"
          name="username"
          placeholder="用户名"
          prefix-icon="el-icon-user"
          type="text"
        />
      </el-form-item>

      <el-form-item prop="password">
        <el-input
          v-model="loginForm.password"
          :type="passwordType"
          name="password"
          placeholder="密码"
          prefix-icon="el-icon-lock"
        />
      </el-form-item>
      <el-form-item prop="captcha">
        <el-input
          v-model="loginForm.captcha"
          name="captcha"
          placeholder="验证码"
          prefix-icon="el-icon-key"
          style="width: 70%"
        />
        <div class="login-code">
          <img
            v-if="codeForm.imgUrl"
            :src="codeForm.imgUrl"
            class="login-code-img"
            @click="handleChangeCode"
          >
          <img
            v-else
            class="login-code-img"
            src="../../assets/images/checkcode.png"
            @click="handleChangeCode"
          >
        </div>
      </el-form-item>

      <el-button
        :loading="loading"
        style="width:100%;margin-bottom:30px;"
        type="primary"
        @click.native.prevent="handleLogin"
      >登录
      </el-button>
    </el-form>
  </div>
</template>

<script>
import { getCodeInfo } from '@/api/user'

export default {
  name: 'Login',
  data() {
    return {
      codeForm: {
        imgUrl: '',
        checkKey: 0

      },
      loginForm: {
        username: 'admin',
        password: '123456',
        captcha: ''
      },
      loginRules: {
        username: [{ required: true, trigger: 'blur' }],
        password: [{ required: true, trigger: 'blur' }]
      },
      loading: false,
      passwordType: 'password',
      redirect: undefined
    }
  },
  created() {
    this.handleChangeCode()
  },
  methods: {
    handleChangeCode() {
      this.codeForm.checkKey = new Date().getTime()
      getCodeInfo(this.codeForm.checkKey).then(res => this.codeForm.imgUrl = res)
    },
    handleLogin() {
      this.$refs.loginForm.validate(valid => {
        if (valid) {
          this.loading = true
          this.$store.dispatch('user/login', { ...this.loginForm, checkKey: this.codeForm.checkKey }).then(() => {
            this.$router.push('/')
            this.loading = false
          }).catch(() => {
            this.handleChangeCode()
            this.loading = false
          })
        } else {
          return false
        }
      })
    }
  }
}
</script>

<style lang="scss" scoped>
$bg: #fff;
$light_gray: #999;

.login-container {
  min-height: 100%;
  width: 100%;
  background-color: $bg;
  overflow: hidden;

  .login-form {
    position: relative;
    width: 520px;
    max-width: 100%;
    padding: 160px 35px 0;
    margin: 0 auto;
    overflow: hidden;
  }

  .tips {
    font-size: 14px;
    color: #fff;
    margin-bottom: 10px;

    span {
      &:first-of-type {
        margin-right: 16px;
      }
    }
  }

  .title-container {
    position: relative;

    .title {
      font-size: 26px;
      color: $light_gray;
      margin: 0px auto 40px auto;
      text-align: center;
      font-weight: bold;
    }
  }

  .show-pwd {
    position: absolute;
    right: 10px;
    top: 7px;
    font-size: 16px;
    cursor: pointer;
    user-select: none;
  }
}

.login-code {
  width: 30%;
  height: 38px;
  float: right;

  img {
    cursor: pointer;
    vertical-align: middle;
  }
}

.login-code-img {
  height: 38px;
}
</style>
