import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from "@/views/Login";
import SystemAdmin from "@/views/SystemAdmin";
import SystemAdminAdd from "@/views/SystemAdminAdd";
import SystemAdminManager from "@/views/SystemAdminManager";
import SystemAdminUpdate from "@/views/SystemAdminUpdate";
import TeacherAdd from "@/views/TeacherAdd";
import TeacherManager from "@/views/TeacherManager";
import Teacher from "@/views/Teacher";
import TeacherMessage from "@/views/TeacherMessage";
import StudentAdd from "@/views/StudentAdd";
import StudentManager from "@/views/StudentManager";
import StudentAddT from "@/views/StudentAddT";
import StudentManagerT from "@/views/StudentManagerT";
import TeacherUpdate from "@/views/TeacherUpdate";
import StudentUpdate from "@/views/StudentUpdate";
import TeacherUpdateT from "@/views/TeacherUpdateT";
import StudentUpdateT from "@/views/StudentUpdateT";

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: '登录',
    component: Login
  },
  {
    path: '/teacher',
    name: '老师',
    component: Teacher,
    redirect: '/teacherMessage',
    children: [
      {
        path: '/teacherMessage',
        name: '老师个人信息',
        component: TeacherMessage
      },
      {
        path: '/studentAddT',
        name: '添加学生',
        component: StudentAddT
      },
      {
        path: '/studentManagerT',
        name: '学生信息',
        component: StudentManagerT
      },
      {
        path: '/studentUpdateT',
        name: '修改学生信息',
        component: StudentUpdateT
      },
      {
        path: '/teacherUpdateT',
        name: '修改老师信息',
        component: TeacherUpdateT
      }
    ]
  },
  {
    path: '/systemAdmin',
    name: '系统管理员',
    component: SystemAdmin,
    redirect: '/systemAdminManager',
    children:[
      {
        path:'/systemAdminAdd',
        name:'添加管理员',
        component: SystemAdminAdd
      },
      {
        path: '/systemAdminManager',
        name: '管理员信息',
        component: SystemAdminManager
      },
      {
        path: '/systemAdminUpdate',
        name: '修改管理员信息',
        component: SystemAdminUpdate
      },
      {
        path: '/teacherAdd',
        name: '添加老师信息',
        component: TeacherAdd
      },
      {
        path: '/teacherManager',
        name: '老师信息',
        component: TeacherManager
      },
      {
        path: '/studentAdd',
        name: '添加学生',
        component: StudentAdd
      },
      {
        path: '/studentManager',
        name: '学生信息',
        component: StudentManager
      },
      {
        path: '/teacherUpdate',
        name: '修改老师信息',
        component: TeacherUpdate
      },
      {
        path: '/studentUpdate',
        name: '修改学生信息',
        component: StudentUpdate
      }
    ]
  },

]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
