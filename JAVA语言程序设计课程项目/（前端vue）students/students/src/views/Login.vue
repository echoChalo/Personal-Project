<template>
  <div class="login-container">
    <div class="login-data">
      <div class="title"><h3>学生信息管理系统登录</h3></div>
      <div class="login-form">
        <el-form :model="ruleForm" :rules="rules"
               status-icon
               ref="ruleForm"
               class="demo-ruleForm-login-page">
          <el-form-item prop="username">
            <el-input type="text"
                    v-model="ruleForm.username"
                    placeholder="账号"
            ><el-button slot="prepend" icon="el-icon-user"></el-button>
            </el-input>
          </el-form-item>
          <el-form-item prop="password">
            <el-input type="password"
                    v-model="ruleForm.password"
                    placeholder="密码"
            ><el-button slot="prepend" icon="el-icon-lock"></el-button>
            </el-input>
          </el-form-item>
        </el-form>
      </div>
      <div class="tool">
        <el-form>
            <el-radio style="position: relative;left: 50px" v-model="type" label="teacher">老师</el-radio>
            <el-radio style="position: relative;left: 125px" v-model="type" label="systemAdmin">系统管理员</el-radio>
        </el-form>
      </div>
      <div class="login-btn">
        <el-form>
          <el-button type="primary" style="..." @click="handleSubmit" :loading="logining" >登录</el-button>
          <audio controls="controls" hidden src="./audio/niganma.mp3" ref="audio"></audio>
        </el-form>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Login",
  data() {
    return {
      logining: false,
      ruleForm: {
        username: 'pxw',
        password: '20071030'
      },
      type: 'teacher',
      rules: {
        username: [{ required: true, message: '请输入账号', trigger: 'blur' }],
        password: [{ required: true, message: '请输入密码', trigger: 'blur' }],
      },
    };
  },
  methods: {
    handleSubmit(){
      this.$refs.audio.currentTime = 0
      this.$refs.audio.play()
      this.$refs.ruleForm.validate((valid)=>{
        if(valid){
          this.logining = true
          let _this = this
          if(_this.type === 'systemAdmin'){
            axios.get(' http://localhost:8082/systemAdmin/login', {params:_this.ruleForm}).then(function (resp){
              _this.logining = false
              if(resp.data.code == -1){
                _this.$alert('用户名不存在','提示',{
                  confirmButtonText: '确定'
                })
              }
              if(resp.data.code == -2){
                _this.$alert('密码错误','提示',{
                  confirmButtonText: '确定'
                })
              }
              if(resp.data.code == 0){
                //跳转界面
                localStorage.setItem('systemAdmin', JSON.stringify(resp.data.data));
                _this.$router.replace({path:'/systemAdmin'})
                _this.$alert('系统管理员登陆成功!','提示',{
                  confirmButtonText: '确定'
                })
              }
            })
          }
          if(_this.type === 'teacher'){
            axios.get(' http://localhost:8082/teacher/login', {params:_this.ruleForm}).then(function (resp){
              _this.logining = false
              if(resp.data.code == -1){
                _this.$alert('用户名不存在','提示',{
                  confirmButtonText: '确定'
                })
              }
              if(resp.data.code == -2){
                _this.$alert('密码错误','提示',{
                  confirmButtonText: '确定'
                })
              }
              if(resp.data.code == 0){
                //跳转界面
                localStorage.setItem('teacher', JSON.stringify(resp.data.data));
                _this.$router.replace({path:'/teacher'})
                _this.$alert('老师登陆成功!','提示',{
                  confirmButtonText: '确定'
                })
              }
            })
          }
        }
      })
    }
  }
}
</script>

<style scoped>
  .login-container {
    top: -25px;
    left: 0;
    width: 100%;
    height: 100%;
    background-image: url("./picture/25时.jpg");
    background-size: 100% 100%;
    background-position: center center;
    overflow: auto;
    background-repeat: no-repeat;
    position: fixed;
    padding-top: 200px;
    opacity: 90%;
  }
  .title {
    margin-bottom: 20px;
    line-height: 50px;
    text-align: center;
    font-size: 30px;
    font-weight: bolder;
    color: white;
    text-shadow: 2px 2px 4px #000000;
  }
  .demo-ruleForm-login-page{
    padding: 30px 30px;
  }
  .login-data {/*登录框的样式*/
    position: absolute;/*position 属性规定元素的定位类型；absolute生成绝对定位的因素*/
    left: 50%;
    top: 35%;
    width: 380px;
    margin: -190px 0 0 -190px;
    border-radius: 15px;/*角边弧度*/
    background: rgba(36, 255, 238, 0.68);
    overflow: hidden;
  }
  .tool {
    display: flex;
    justify-content: space-between;
    color: rgb(250, 0, 159);
  }
  .login-btn {
    margin-top: 25px;
    text-align: center;
    height: 35px;
    margin-bottom: 50px;
   }
</style>


