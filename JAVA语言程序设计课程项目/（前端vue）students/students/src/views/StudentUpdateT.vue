<template>
  <div style="margin-top: 60px;margin-left: 450px;width: 300px;height: 500px;border: 0">
    <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
      <el-form-item label="ID">
        <el-input v-model="ruleForm.id" readonly></el-input>
      </el-form-item>
      <el-form-item label="姓名" prop="name">
        <el-input v-model="ruleForm.name"></el-input>
      </el-form-item>
      <el-form-item label="年龄" prop="age">
        <el-input v-model="ruleForm.age"></el-input>
      </el-form-item>
      <el-form-item label="年级" prop="grade">
        <el-input v-model="ruleForm.grade"></el-input>
      </el-form-item>
      <el-form-item label="学院" prop="major">
        <el-input v-model="ruleForm.major"></el-input>
      </el-form-item>
      <el-form-item label="班级" prop="classes">
        <el-input v-model="ruleForm.classes"></el-input>
      </el-form-item>
      <el-form-item label="绩点" prop="gpa">
        <el-input v-model="ruleForm.gpa"></el-input>
      </el-form-item>
      <el-form-item label="状态" prop="state">
        <el-input v-model="ruleForm.state"></el-input>
      </el-form-item>
      <el-form-item label="联系电话" prop="telephone">
        <el-input v-model="ruleForm.telephone"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm('ruleForm')">修改</el-button>
        <el-button @click="resetForm('ruleForm')">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      category:null,
      ruleForm: {
        name: '',
        age:'',
        grade:'',
        major:'',
        classes:'',
        gpa:'',
        state:'',
        telephone:''
      },
      rules: {
        name: [
          { required: true, message: '请输入姓名', trigger: 'blur' }
        ],
        age: [
          { required: true, message: '请输入年龄', trigger: 'blur' }
        ],
        grade: [
          { required: true, message: '请输入年级', trigger: 'blur' }
        ],
        major: [
          { required: true, message: '请输入学院', trigger: 'blur' }
        ],
        classes: [
          { required: true, message: '请输入班级', trigger: 'blur' }
        ],
        gpa: [
          { required: true, message: '请输入绩点', trigger: 'blur' }
        ],
        state: [
          { required: true, message: '请输入状态', trigger: 'blur' }
        ],
        telephone: [
          { required: true, message: '请输入联系电话', trigger: 'blur' }
        ],
      }
    };
  },
  methods: {
    submitForm(formName) {
      const _this = this
      this.$refs[formName].validate((valid) => {
        if (valid) {
          axios.put('http://localhost:8082/student/update', _this.ruleForm).then(function (resp){
            if(resp.data.code == 0){
              _this.$alert(_this.ruleForm.name+'修改已成功!','',{
                confirmButtonText:'确定',
                callback:action => {
                  _this.$router.push('/studentManagerT')
                }
              });
            }
          })
        }
        else {
          console.log('上传错误!!');
          return false;
        }
      });
    },
    resetForm(formName) {//重置按钮
      this.$refs[formName].resetFields();
    }
  },
  created() {
    const _this = this
    axios.get('http://localhost:8082/student/findById/'+_this.$route.query.id).then(function (resp){
      _this.ruleForm = resp.data.data
    })
  }
}
</script>
