<template>
  <el-container class="home_container">
    <el-header class="home_header">
      <div class="home_title">STDS学生信息管理系统-老师</div>
      <div class="home_userinfoContainer">
        <el-dropdown>
          <span class="el-dropdown-link home_userinfo">
            {{admin.name}}<i class="el-icon-arrow-down el-icon--right home_userinfo"></i>
          </span>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item @click.native="logout">退出登录</el-dropdown-item>
            </el-dropdown-menu>
        </el-dropdown>
      </div>
    </el-header>

    <el-container>

      <el-aside class="home_aside" width="200px">

        <el-menu router>
            <el-menu-item index="/teacherMessage">
              <i class="el-icon-document-copy" :class="$route.path=='/teacherMessage'?'is-active':''"></i>老师个人信息
            </el-menu-item>
          <el-menu-item index="/studentAddT" :class="$router.path=='/studentAddT'?'is-active':''">
            <i class="el-icon-folder-add"></i>添加学生信息
          </el-menu-item>
          <el-menu-item index="/studentManagerT" :class="$route.path=='/studentManagerT'?'is-active':''">
            <i class="el-icon-document-copy"></i>学生信息
          </el-menu-item>
        </el-menu>

      </el-aside>

      <el-container>
        <el-main>
          <el-breadcrumb separator-class="el-icon-arrow-right">
            <el-breadcrumb-item :to="{ path: '/'}">首页</el-breadcrumb-item>
            <el-breadcrumb-item v-text="this.$router.currentRoute.name"></el-breadcrumb-item>
          </el-breadcrumb>
          <router-view></router-view>
        </el-main>
        <el-footer class="home_footer">2022 ©学生管理系统</el-footer>
      </el-container>

    </el-container>

  </el-container>
</template>

<script>
export default {
  methods:{
    logout(){
      let _this = this;
      this.$confirm('确定退出？','提示',{
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(function (){
        localStorage.removeItem('teacher')
        _this.$router.replace({path:'/'})
      })
    }
  },
  data(){
    return{
      admin:''
    }
  },
  created() {
    let admin = JSON.parse(localStorage.getItem('teacher'))
    this.admin = admin
  }
}
</script>

<style scoped>
.home_container{
  height: 100%;
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
}
.home_header{
  background-color: #2B2B2B;
  text-align: center;
  display: flex;
  align-items: center;
  justify-content: space-between;
}
.home_title{
  color: #C2C2C2;
  margin-bottom: 10px;
  line-height: 50px;
  font-weight: bolder;
  font-size: 22px;
  display: inline;
}
.home_userinfo{
  color: #fff;
  cursor: pointer;
}
.home_userinfoContainer{
  display: inline;
  margin-right: 20px;
}
.home_aside{
  background-color: #FFFFFF;
}
.home_footer{
  background-color: #FFFFFF;
  color: #000000;
  font-size: 18px;
  line-height: 60px;
  text-align: -webkit-center;
}
</style>
