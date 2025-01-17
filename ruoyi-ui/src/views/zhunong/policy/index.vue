<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="文章标题" prop="title">
        <el-input
          v-model="queryParams.title"
          placeholder="请输入文章标题"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="作者/来源" prop="author">
        <el-input
          v-model="queryParams.author"
          placeholder="请输入作者/来源"
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
      <el-form-item label="发布时间" prop="publishTime">
        <el-date-picker clearable
          v-model="queryParams.publishTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择发布时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="是否推荐(1推荐2不推荐)" prop="recommend">
        <el-input
          v-model="queryParams.recommend"
          placeholder="请输入是否推荐(1推荐2不推荐)"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="浏览数量" prop="browseNum">
        <el-input
          v-model="queryParams.browseNum"
          placeholder="请输入浏览数量"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="创建时间" prop="createdTime">
        <el-date-picker clearable
          v-model="queryParams.createdTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择创建时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="更新时间" prop="updatedTime">
        <el-date-picker clearable
          v-model="queryParams.updatedTime"
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
          v-hasPermi="['zhunong:policy:add']"
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
          v-hasPermi="['zhunong:policy:edit']"
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
          v-hasPermi="['zhunong:policy:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['zhunong:policy:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="policyList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主键" align="center" prop="id" />
      <el-table-column label="文章标题" align="center" prop="title" />
      <el-table-column label="作者/来源" align="center" prop="author" />
      <el-table-column label="文章摘要" align="center" prop="resume" width="200" />
      <el-table-column label="正文内容" align="center" prop="content" width="250">
        <template slot-scope="scope">
        <div v-html="sanitize(scope.row.content)"></div>
        </template>
      </el-table-column>

      <el-table-column label="发布时间" align="center" prop="publishTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.publishTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="发布状态(1已发布2.未发布)" align="center" prop="publishStatus" />
      <el-table-column label="是否推荐(1推荐2不推荐)" align="center" prop="recommend" />
      <el-table-column label="浏览数量" align="center" prop="browseNum" />
      <el-table-column label="创建时间" align="center" prop="createdTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createdTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="更新时间" align="center" prop="updatedTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.updatedTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['zhunong:policy:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['zhunong:policy:remove']"
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

    <!-- 添加或修改补贴政策对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="文章标题" prop="title">
          <el-input v-model="form.title" placeholder="请输入文章标题" />
        </el-form-item>
        <el-form-item label="作者/来源" prop="author">
          <el-input v-model="form.author" placeholder="请输入作者/来源" />
        </el-form-item>
        <el-form-item label="文章摘要" prop="resume">
          <el-input v-model="form.resume" placeholder="请输入文章摘要" />
        </el-form-item>
        <el-form-item label="正文内容">
          <editor v-model="form.content" :min-height="192"/>
        </el-form-item>
        <el-form-item label="发布时间" prop="publishTime">
          <el-date-picker clearable
            v-model="form.publishTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择发布时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="是否推荐(1推荐2不推荐)" prop="recommend">
          <el-input v-model="form.recommend" placeholder="请输入是否推荐(1推荐2不推荐)" />
        </el-form-item>
        <el-form-item label="浏览数量" prop="browseNum">
          <el-input v-model="form.browseNum" placeholder="请输入浏览数量" />
        </el-form-item>
        <el-form-item label="创建时间" prop="createdTime">
          <el-date-picker clearable
            v-model="form.createdTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择创建时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="更新时间" prop="updatedTime">
          <el-date-picker clearable
            v-model="form.updatedTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择更新时间">
          </el-date-picker>
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
import { listPolicy, getPolicy, delPolicy, addPolicy, updatePolicy } from "@/api/zhunong/policy";
import DOMPurify from "quill/formats/link";

export default {
  name: "Policy",
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
      // 补贴政策表格数据
      policyList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        title: null,
        author: null,
        resume: null,
        content: null,
        publishTime: null,
        publishStatus: null,
        recommend: null,
        browseNum: null,
        createdTime: null,
        updatedTime: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        title: [
          { required: true, message: "文章标题不能为空", trigger: "blur" }
        ],
        author: [
          { required: true, message: "作者/来源不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询补贴政策列表 */
    getList() {
      this.loading = true;
      listPolicy(this.queryParams).then(response => {
        this.policyList = response.rows;
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
        author: null,
        resume: null,
        content: null,
        publishTime: null,
        publishStatus: null,
        recommend: null,
        browseNum: null,
        createdTime: null,
        updatedTime: null
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
      this.title = "添加补贴政策";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getPolicy(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改补贴政策";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          const formData = { ...this.form, id: null }; // 确保 id 字段为空
          if (this.form.id != null) {
            updatePolicy(formData).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addPolicy(formData).then(response => {
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
      this.$modal.confirm('是否确认删除补贴政策编号为"' + ids + '"的数据项？').then(function() {
        return delPolicy(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('zhunong/policy/export', {
        ...this.queryParams
      }, `policy_${new Date().getTime()}.xlsx`)
    },
    /** 网页渲染 **/
    sanitize(content) {
      return DOMPurify.sanitize(content);
    }
  }
};
</script>
