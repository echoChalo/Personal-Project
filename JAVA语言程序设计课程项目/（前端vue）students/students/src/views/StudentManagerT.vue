<template>
  <div style="margin-top: 60px;margin-left: 80px;border: 0 solid red;">

    <!--字段搜索功能-->
    <el-form style="margin-left: -40px" :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
      <el-form-item label="搜索字段：" prop="key">
        <el-select v-model="ruleForm.key" style="width: 150px;float: left" placeholder="请选择字段">
          <el-option label="学生绩点" value="gpa"></el-option>
          <el-option label="学生姓名" value="name"></el-option>
          <el-option label="学生性别" value="gender"></el-option>
          <el-option label="学生学院" value="major"></el-option>
          <el-option label="学生电话" value="telephone"></el-option>
        </el-select>
      </el-form-item>
      <div style="border: 0 solid red;width: 400px;height: 60px;position: relative;top: -64px;left: 270px">
        <el-form-item label="字段值：">
          <el-input v-model="ruleForm.value" placeholder="请输入关键字" style="width: 150px;"></el-input>
          <el-button type="primary" icon="el-icon-search" style="position: relative;left: 10px;" @click="submitForm('ruleForm')">搜索</el-button>
        </el-form-item>
      </div>
    </el-form>

    <!--表格展示-->
    <el-table
        :data="tableData"
        border
        stripe
        style="width: 100%;position: relative;top: -30px">
      <el-table-column
          fixed
          prop="id"
          label="ID"
          width="100">
      </el-table-column>
      <el-table-column
          prop="name"
          label="姓名"
          width="120">
      </el-table-column>
      <el-table-column
          prop="age"
          label="年龄"
          width="120">
      </el-table-column>
      <el-table-column
          prop="grade"
          label="年级"
          width="120">
      </el-table-column>
      <el-table-column
          prop="major"
          label="学院"
          width="120">
      </el-table-column>
      <el-table-column
          prop="classes"
          label="班级"
          width="120">
      </el-table-column>
      <el-table-column
          prop="gpa"
          label="绩点"
          width="120">
      </el-table-column>
      <el-table-column
          prop="state"
          label="状态"
          width="120">
      </el-table-column>
      <el-table-column
          prop="telephone"
          label="联系电话"
          width="120">
      </el-table-column>
      <el-table-column label="操作">
        <template v-slot="scope">
          <el-button
              size="mini"
              @click="edit(scope.row)">编辑</el-button>
          <el-button
              size="mini"
              type="danger"
              @click="del(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!--换页-->
    <el-pagination style="margin-top: 20px"
                   background
                   layout="prev, pager, next"
                   :page-size="pageSize"
                   :total="total"
                   :current-page.sync="currentPage"
                   @current-change="page">
    </el-pagination>

  </div>
</template>

<script>
export default {
  data(){
    return{
      tableData: null,
      currentPage: 1,
      pageSize: 3,
      total: '',
      key: '',
      value: '',
      ruleForm:{
        key: '',
        value: '',
        page: '',
        size: 3
      },
      rules:{
        key:[
          { required: true, message: '请选择字段', trigger: 'change'}
        ]
      }
    }
  },
  methods:{
    submitForm(formName) {
      const _this = this
      //每次查询让翻页复原
      _this.currentPage = 1
      this.$refs[formName].validate((valid) => {
        if(valid){
          const _this = this
          _this.ruleForm.page = _this.currentPage
          axios.get('http://localhost:8082/student/search',{params:_this.ruleForm}).then(function (resp){
            _this.tableData = resp.data.data.data
            _this.total = resp.data.data.total
          })
        }
      })
    },
    page(currentPage){
      const _this = this
      if(_this.ruleForm.value == ''){
        axios.get('http://localhost:8082/student/list/'+currentPage+'/'+this.pageSize).then(function (resp){
          _this.tableData = resp.data.data.data
          _this.total = resp.data.data.total
        })
      }
      else {
        _this.ruleForm.page = _this.currentPage
        axios.get('http://localhost:8082/student/search',{params:_this.ruleForm}).then(function (resp){
          _this.tableData = resp.data.data.data
          _this.total = resp.data.data.total
        })
      }
    },
    edit(row){
      this.$router.push('/studentUpdateT?id='+row.id)
    },
    del(row){
      const _this = this
      this.$confirm('确认删除 '+row.name+' 这位学生吗？','提示',{
        confirmButtonText:'确定',
        cancelButtonText:'取消',
        type:'warning'
      }).then(()=>{
        axios.delete('http://localhost:8082/student/deleteById/'+row.id).then(function (resp){
          if(resp.data.code == 0){
            _this.$alert(row.name + '已删除成功！','',{
              confirmButtonText:'确定',
              callback:action => {
                location.reload()//刷新页面
              }
            });
          }
        });
      }).catch(()=>{
        this.$message({
          type:'info',message:'已取消操作',center:true
        });
      });
    }
  },
  created(){
    const _this = this
    axios.get('http://localhost:8082/student/list/1/'+this.pageSize).then(function (resp){
      _this.tableData = resp.data.data.data
      _this.total = resp.data.data.total
    })
  }
}
</script>
