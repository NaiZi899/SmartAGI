<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="课程标题" prop="title">
        <el-input
          v-model="queryParams.title"
          placeholder="请输入课程标题"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="文章摘要" prop="resume">
        <el-input
          v-model="queryParams.resume"
          placeholder="请输入文章摘要"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="专家Id" prop="expertId">
        <el-input
          v-model="queryParams.expertId"
          placeholder="请输入专家Id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="所属领域" prop="fieldCode">
        <el-input
          v-model="queryParams.fieldCode"
          placeholder="请输入所属领域"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="所属分类" prop="serviceCatgoryCode">
        <el-input
          v-model="queryParams.serviceCatgoryCode"
          placeholder="请输入所属分类"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="所属品类" prop="produceCategoryId">
        <el-input
          v-model="queryParams.produceCategoryId"
          placeholder="请输入所属品类"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="视频文件" prop="video">
        <el-input
          v-model="queryParams.video"
          placeholder="请输入视频文件"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是否推荐 ：1.推荐；2.不推荐" prop="recommend">
        <el-input
          v-model="queryParams.recommend"
          placeholder="请输入是否推荐 ：1.推荐；2.不推荐"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="发布时间" prop="publishTime">
        <el-date-picker clearable
          v-model="queryParams.publishTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择发布时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="浏览数量" prop="browseNum">
        <el-input
          v-model="queryParams.browseNum"
          placeholder="请输入浏览数量"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="创建时间" prop="createTime">
        <el-date-picker clearable
          v-model="queryParams.createTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择创建时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="更新时间" prop="updateTime">
        <el-date-picker clearable
          v-model="queryParams.updateTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择更新时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['zhunong:course:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['zhunong:course:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['zhunong:course:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['zhunong:course:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="courseList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主键id" align="center" prop="id" />
      <el-table-column label="课程标题" align="center" prop="title" width="150"/>
      <el-table-column label="文章摘要" align="center" prop="resume" width="200"/>
      <el-table-column label="专家Id" align="center" prop="expertId" />
      <el-table-column label="所属领域" align="center" prop="fieldCode" />
      <el-table-column label="课程封面图片" align="center" prop="image" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.image" :width="50" :height="50"/>
        </template>
      </el-table-column>
<!--      <el-table-column label="正文" align="center" prop="content" />-->
      <el-table-column label="所属分类" align="center" prop="serviceCatgoryCode" />
      <el-table-column label="所属品类" align="center" prop="produceCategoryId" />
      <el-table-column label="视频文件" align="center" prop="video" />
      <el-table-column label="课程类型 1.图文课程 2.视频课程 3.短视频课程" align="center" prop="type" />
      <el-table-column label="是否推荐 ：1.推荐；2.不推荐" align="center" prop="recommend" />
      <el-table-column label="发布状态 ：1.已发布；2.未发布" align="center" prop="publishStatus" />
      <el-table-column label="发布时间" align="center" prop="publishTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.publishTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="浏览数量" align="center" prop="browseNum" />
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="更新时间" align="center" prop="updateTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.updateTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['zhunong:course:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['zhunong:course:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改知识课程对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="课程标题" prop="title">
          <el-input v-model="form.title" placeholder="请输入课程标题" />
        </el-form-item>
        <el-form-item label="文章摘要" prop="resume">
          <el-input v-model="form.resume" placeholder="请输入文章摘要" />
        </el-form-item>
        <el-form-item label="专家Id" prop="expertId">
          <el-input v-model="form.expertId" placeholder="请输入专家Id" />
        </el-form-item>
        <el-form-item label="所属领域" prop="fieldCode">
          <el-input v-model="form.fieldCode" placeholder="请输入所属领域" />
        </el-form-item>
        <el-form-item label="课程封面图片" prop="image">
          <image-upload v-model="form.image"/>
        </el-form-item>
<!--        <el-form-item label="正文">-->
<!--          <editor v-model="form.content" :min-height="192"/>-->
<!--        </el-form-item>-->
        <el-form-item label="所属分类" prop="serviceCatgoryCode">
          <el-input v-model="form.serviceCatgoryCode" placeholder="请输入所属分类" />
        </el-form-item>
        <el-form-item label="所属品类" prop="produceCategoryId">
          <el-input v-model="form.produceCategoryId" placeholder="请输入所属品类" />
        </el-form-item>
        <el-form-item label="视频文件" prop="video">
          <el-input v-model="form.video" placeholder="请输入视频文件" />
        </el-form-item>
        <el-form-item label="是否推荐 ：1.推荐；2.不推荐" prop="recommend">
          <el-input v-model="form.recommend" placeholder="请输入是否推荐 ：1.推荐；2.不推荐" />
        </el-form-item>
        <el-form-item label="发布时间" prop="publishTime">
          <el-date-picker clearable
            v-model="form.publishTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择发布时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="浏览数量" prop="browseNum">
          <el-input v-model="form.browseNum" placeholder="请输入浏览数量" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listCourse, getCourse, delCourse, addCourse, updateCourse } from "@/api/zhunong/course";

export default {
  name: "Course",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 知识课程表格数据
      courseList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        title: null,
        resume: null,
        expertId: null,
        fieldCode: null,
        image: null,
        content: null,
        serviceCatgoryCode: null,
        produceCategoryId: null,
        video: null,
        type: null,
        recommend: null,
        publishStatus: null,
        publishTime: null,
        browseNum: null,
        createTime: null,
        updateTime: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        title: [
          { required: true, message: "课程标题不能为空", trigger: "blur" }
        ],
        expertId: [
          { required: true, message: "专家Id不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询知识课程列表 */
    getList() {
      this.loading = true;
      listCourse(this.queryParams).then(response => {
        this.courseList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        title: null,
        resume: null,
        expertId: null,
        fieldCode: null,
        image: null,
        content: null,
        serviceCatgoryCode: null,
        produceCategoryId: null,
        video: null,
        type: null,
        recommend: null,
        publishStatus: null,
        publishTime: null,
        browseNum: null,
        createTime: null,
        updateTime: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加知识课程";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getCourse(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改知识课程";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateCourse(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addCourse(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除知识课程编号为"' + ids + '"的数据项？').then(function() {
        return delCourse(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('zhunong/course/export', {
        ...this.queryParams
      }, `course_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
