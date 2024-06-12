<template>
  <el-descriptions :model="ruleForm" :rules="rules" ref="ruleForm" class="margin-top" title="个人信息" :column="2" border labelWidth="100px">
    <template slot="extra">
      <el-button size="small" @click="edit(ruleForm)">修改信息</el-button>
    </template>
    <el-descriptions-item label="用户名">{{ruleForm.username}}</el-descriptions-item>
    <el-descriptions-item label="姓名">{{ruleForm.name}}</el-descriptions-item>
    <el-descriptions-item label="性别">{{ruleForm.gender}}</el-descriptions-item>
    <el-descriptions-item label="学院">
      <el-tag size="small">{{ruleForm.major}}</el-tag>
    </el-descriptions-item>
    <el-descriptions-item label="联系电话">{{ruleForm.telephone}}</el-descriptions-item>
  </el-descriptions>
</template>

<script>
export default {
  data () {
    return {
      ruleForm: {
        username:'',
        password:'',
        name: '',
        gender:'男',
        major:'',
        telephone:''
      },
      rules:[]
    };
  },
  methods:{
    edit(ruleForm) {
      this.$router.push('/teacherUpdateT?id='+ruleForm.id)
    }
  },
  created() {
    let ruleForm = JSON.parse(localStorage.getItem('teacher'))
    this.ruleForm = ruleForm
    let _this = this
    axios.get('http://localhost:8082/teacher/findById/' + _this.ruleForm.id).then(function (resp) {
        _this.ruleForm = resp.data.data
      })
  }
}
</script>

<style scoped>
.margin-top{
  margin-top: 50px;
  margin-left: 170px;
  margin-right: 220px;
  position: relative;
}
</style>
