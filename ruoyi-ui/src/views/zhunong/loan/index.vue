<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryForm"
      size="small"
      :inline="true"
      v-show="showSearch"
      label-width="68px"
    >
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
        <el-date-picker
          clearable
          v-model="queryParams.publishTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择发布时间"
        >
        </el-date-picker>
      </el-form-item>
      <el-form-item label="是否推荐：1.推荐；2.不推荐" prop="recommend">
        <el-input
          v-model="queryParams.recommend"
          placeholder="请输入是否推荐：1.推荐；2.不推荐"
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
        <el-date-picker
          clearable
          v-model="queryParams.createdTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择创建时间"
        >
        </el-date-picker>
      </el-form-item>
      <el-form-item label="更新时间" prop="updatedTime">
        <el-date-picker
          clearable
          v-model="queryParams.updatedTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择更新时间"
        >
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button
          type="primary"
          icon="el-icon-search"
          size="mini"
          @click="handleQuery"
          >搜索</el-button
        >
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery"
          >重置</el-button
        >
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
          v-hasPermi="['zhunong:loan:add']"
          >新增</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['zhunong:loan:edit']"
          >修改</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['zhunong:loan:remove']"
          >删除</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="multiple"
          @click="handleBatchPublish"
          v-hasPermi="['zhunong:loan:publish']"
          >批量发布</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleBatchUnpublish"
          v-hasPermi="['zhunong:loan:unpublish']"
          >批量取消发布</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="multiple"
          @click="handleBatchRecommend"
          v-hasPermi="['zhunong:loan:recommend']"
          >批量推荐</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleBatchUnrecommend"
          v-hasPermi="['zhunong:loan:unrecommend']"
          >批量取消推荐</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['zhunong:loan:export']"
          >导出</el-button
        >
      </el-col>
      <right-toolbar
        :showSearch.sync="showSearch"
        @queryTable="getList"
      ></right-toolbar>
    </el-row>

    <el-table
      v-loading="loading"
      :data="loanList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主键" align="center">
        <template slot-scope="scope">
          <span>{{ scope.$index + 1 }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="文章标题"
        align="center"
        prop="title"
        :show-overflow-tooltip="true"
      />
      <el-table-column
        label="作者/来源"
        align="center"
        prop="author"
        :show-overflow-tooltip="true"
      />
      <el-table-column
        label="文章摘要"
        align="center"
        prop="resume"
        :show-overflow-tooltip="true"
      />
      <el-table-column
        label="正文内容"
        align="center"
        prop="content"
        :show-overflow-tooltip="true"
      />
      <el-table-column
        label="图片文件"
        align="center"
        prop="image"
        width="100"
      >
        <template slot-scope="scope">
          <image-preview :src="scope.row.image" :width="50" :height="50" />
        </template>
      </el-table-column>
      <el-table-column
        label="发布时间"
        align="center"
        prop="publishTime"
        width="180"
      >
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.publishTime, "{y}-{m}-{d}") }}</span>
        </template>
      </el-table-column>
      <el-table-column label="发布状态" align="center" prop="publishStatus">
        <template slot-scope="scope">
          {{ scope.row.publishStatus === 1 ? "已发布" : "未发布" }}
        </template>
      </el-table-column>
      <el-table-column label="是否推荐" align="center" prop="recommend">
        <template slot-scope="scope">
          {{ scope.row.recommend === 1 ? "已推荐" : "未推荐" }}
        </template>
      </el-table-column>
      <el-table-column label="浏览数量" align="center" prop="browseNum" />
      <el-table-column
        label="创建时间"
        align="center"
        prop="createdTime"
        width="180"
      >
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createdTime, "{y}-{m}-{d}") }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="更新时间"
        align="center"
        prop="updatedTime"
        width="180"
      >
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.updatedTime, "{y}-{m}-{d}") }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="操作"
        align="center"
        class-name="small-padding fixed-width"
      >
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['zhunong:loan:edit']"
            >修改</el-button
          >
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['zhunong:loan:remove']"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total > 0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改信贷信息对话框 -->
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
          <editor v-model="form.content" :min-height="192" />
        </el-form-item>
        <el-form-item label="图片文件" prop="image">
          <image-upload v-model="form.image"/>
        </el-form-item>
        <el-form-item label="发布时间" prop="publishTime">
          <el-date-picker
            clearable
            v-model="form.publishTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择发布时间"
          >
          </el-date-picker>
        </el-form-item>
        <el-form-item label="是否推荐：1.推荐；2.不推荐" prop="recommend">
          <el-input
            v-model="form.recommend"
            placeholder="请输入是否推荐：1.推荐；2.不推荐"
          />
        </el-form-item>
        <el-form-item label="浏览数量" prop="browseNum">
          <el-input v-model="form.browseNum" placeholder="请输入浏览数量" />
        </el-form-item>
        <el-form-item label="创建时间" prop="createdTime">
          <el-date-picker
            clearable
            v-model="form.createdTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择创建时间"
          >
          </el-date-picker>
        </el-form-item>
        <el-form-item label="更新时间" prop="updatedTime">
          <el-date-picker
            clearable
            v-model="form.updatedTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择更新时间"
          >
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
import {
  listLoan,
  getLoan,
  delLoan,
  addLoan,
  updateLoan,
  batchPublishNbCreditLoan,
  revokeBatchPublishNbCreditLoan,
  recommendNbCreditLoan,
  unRecommendNbCreditLoan,
} from "@/api/zhunong/loan";

export default {
  name: "Loan",
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
      // 信贷信息表格数据
      loanList: [],
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
        image: null,
        publishTime: null,
        publishStatus: null,
        recommend: null,
        browseNum: null,
        createdTime: null,
        updatedTime: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        title: [
          { required: true, message: "文章标题不能为空", trigger: "blur" },
        ],
        author: [
          { required: true, message: "作者/来源不能为空", trigger: "blur" },
        ],
      },
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询信贷信息列表 */
    getList() {
      this.loading = true;
      listLoan(this.queryParams).then((response) => {
        this.loanList = response.rows;
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
        image: null,
        publishTime: null,
        publishStatus: null,
        recommend: null,
        browseNum: null,
        createdTime: null,
        updatedTime: null,
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
      this.ids = selection.map((item) => item.id);
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加信贷信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids;
      getLoan(id).then((response) => {
        this.form = response.data;
        this.open = true;
        this.title = "修改信贷信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          if (this.form.id != null) {
            updateLoan(this.form).then((response) => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addLoan(this.form).then((response) => {
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
      this.$modal
        .confirm('是否确认删除信贷信息编号为"' + ids + '"的数据项？')
        .then(function () {
          return delLoan(ids);
        })
        .then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        })
        .catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download(
        "zhunong/loan/export",
        {
          ...this.queryParams,
        },
        `loan_${new Date().getTime()}.xlsx`
      );
    },
    /** 批量发布按钮操作 */
    handleBatchPublish(row) {
      const ids = row.id || this.ids;
      this.$modal
        .confirm('是否确认发布信贷信息编号为"' + ids + '"的数据项？')
        .then(function () {
          return batchPublishNbCreditLoan(ids);
        })
        .then(() => {
          this.getList();
          this.$modal.msgSuccess("发布成功");
        })
        .catch(() => {});
    },

    /** 批量取消发布按钮操作 */
    handleBatchUnpublish(row) {
      const ids = row.id || this.ids;
      this.$modal
        .confirm('是否确认取消发布信贷信息编号为"' + ids + '"的数据项？')
        .then(function () {
          return revokeBatchPublishNbCreditLoan(ids);
        })
        .then(() => {
          this.getList();
          this.$modal.msgSuccess("取消发布成功");
        })
        .catch(() => {});
    },
    /** 批量推荐按钮操作 */
    handleBatchRecommend(row) {
      const ids = row.id || this.ids;
      this.$modal
        .confirm('是否确认取消发布信贷信息编号为"' + ids + '"的数据项？')
        .then(function () {
          return recommendNbCreditLoan(ids);
        })
        .then(() => {
          this.getList();
          this.$modal.msgSuccess("推荐成功");
        })
        .catch(() => {});
    },
    /** 批量取消推荐按钮操作 */
    handleBatchUnrecommend(row) {
      const ids = row.id || this.ids;
      this.$modal
        .confirm('是否确认取消推荐信贷信息编号为"' + ids + '"的数据项？')
        .then(function () {
          return unRecommendNbCreditLoan(ids);
        })
        .then(() => {
          this.getList();
          this.$modal.msgSuccess("取消推荐成功");
        })
        .catch(() => {});
    },
  },
};
</script>
