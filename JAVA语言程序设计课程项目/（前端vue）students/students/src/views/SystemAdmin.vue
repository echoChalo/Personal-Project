<template>
  <el-container class="home_container">
    <el-header class="home_header">
      <div class="home_title">STDS学生信息管理系统-系统管理员</div>
      <div class="home_userinfoContainer">
        <el-dropdown>
          <span class="el-dropdown-link home_userinfo">
            {{admin.sysname}}<i class="el-icon-arrow-down el-icon--right home_userinfo"></i>
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
          <el-submenu index="1">
            <template slot="title">
              <i class="el-icon-s-platform"></i>
              <span>系统管理员模块</span>
            </template>
            <el-menu-item index="/systemAdminAdd" :class="$route.path=='/systemAdminAdd'?'is-active':''">
              <i class="el-icon-folder-add"></i>添加管理员
            </el-menu-item>
            <el-menu-item index="/systemAdminManager" :class="$route.path=='/systemAdminManager'?'is-active':''">
              <i class="el-icon-document-copy"></i>管理员信息
            </el-menu-item>
          </el-submenu>

          <el-submenu index="2">
            <template slot="title">
              <i class="el-icon-s-custom"></i>
              <span>老师模块</span>
            </template>
            <el-menu-item index="/teacherAdd" :class="$route.path=='/teacherAdd'?'is-active':''">
              <i class="el-icon-folder-add"></i>添加老师
            </el-menu-item>
            <el-menu-item index="/teacherManager">
              <i class="el-icon-document-copy" :class="$route.path=='/teacherManager'?'is-active':''"></i>老师信息
            </el-menu-item>
          </el-submenu>

          <el-menu-item index="/studentAdd" :class="$router.path=='/studentAdd'?'is-active':''">
            <i class="el-icon-folder-add"></i>添加学生信息
          </el-menu-item>
          <el-menu-item index="/studentManager" :class="$route.path=='/studentManager'?'is-active':''">
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
          localStorage.removeItem('systemAdmin')
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
      let admin = JSON.parse(localStorage.getItem('systemAdmin'))
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
