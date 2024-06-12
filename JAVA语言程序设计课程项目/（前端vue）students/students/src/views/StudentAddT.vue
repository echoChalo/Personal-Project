<template>
  <div style="margin-top: 60px;margin-left: 450px;width: 300px;height: 500px;border: 0">
    <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
      <el-form-item label="姓名" prop="name">
        <el-input v-model="ruleForm.name"></el-input>
      </el-form-item>
      <el-form-item label="性别">
        <el-radio-group v-model="ruleForm.gender">
          <el-radio label="男"></el-radio>
          <el-radio label="女"></el-radio>
        </el-radio-group>
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
        <el-button type="primary" @click="submitForm('ruleForm')">创建</el-button>
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
        gender:'男',
        age:'',
        grade:'',
        major:'',
        classes:'',
        gpa:'',
        state: '',
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
          axios.post('http://localhost:8082/student/save', _this.ruleForm).then(function (resp){
            if(resp.data.code == 0){
              _this.$alert(_this.ruleForm.name+'已添加成功!','',{
                confirmButtonText:'确定',
                callback:action => {
                  _this.$router.push('/studentManager')
                }
              });
            }
          })
        }
      });
    },
    resetForm(formName) {//重置按钮
      this.$refs[formName].resetFields();
    }
  }
}
</script>
