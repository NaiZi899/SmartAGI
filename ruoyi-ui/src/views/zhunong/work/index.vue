<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="标题" prop="title">
        <el-input
          v-model="queryParams.title"
          placeholder="请输入标题"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="服务价格" prop="price">
        <el-input
          v-model="queryParams.price"
          placeholder="请输入服务价格"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="所属分类" prop="catgory">
        <el-input
          v-model="queryParams.catgory"
          placeholder="请输入所属分类"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="摘要" prop="resume">
        <el-input
          v-model="queryParams.resume"
          placeholder="请输入摘要"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="联系电话" prop="phone">
        <el-input
          v-model="queryParams.phone"
          placeholder="请输入联系电话"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="微信号码" prop="weChat">
        <el-input
          v-model="queryParams.weChat"
          placeholder="请输入微信号码"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="服务商名称" prop="providerName">
        <el-input
          v-model="queryParams.providerName"
          placeholder="请输入服务商名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="发布时间" prop="pubdate">
        <el-date-picker clearable
          v-model="queryParams.pubdate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择发布时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="发布人id" prop="pubAccountId">
        <el-input
          v-model="queryParams.pubAccountId"
          placeholder="请输入发布人id"
          clearable
          @keyup.enter.native="handleQuery"
        />
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
          v-hasPermi="['zhunong:work:add']"
        >新增</el-button>
      </el-col>

<!--      <el-col :span="1.5">-->
<!--      <el-button @click="batchPublish" size="small">批量上架</el-button>-->
<!--      </el-col>-->
<!--      <el-col :span="1.5">-->
<!--          <el-button @click="revokeBatchPublish" size="small"-->
<!--            >批量撤销上架</el-button>-->
<!--          </el-col>-->
<!--          <el-col :span="1.5">-->
<!--          <el-button @click="batchRecommended" size="small">批量推荐</el-button>-->
<!--        </el-col>-->
<!--        <el-col :span="1.5">-->
<!--          <el-button @click="revokeBatchRecommended" size="small"-->
<!--            >批量取消推荐</el-button-->
<!--          >-->
<!--        </el-col>-->
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['zhunong:work:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="workList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主键" align="center"  >
        <template slot-scope="scope">
          <span>{{ scope.$index+1 }}</span>
        </template>
        </el-table-column>
      <el-table-column label="标题" align="center" prop="title" />
      <el-table-column label="服务价格" align="center" prop="price" />
      <el-table-column label="所属分类" align="center" prop="catgory" />
      <el-table-column label="联系电话" align="center" prop="phone" />
      <el-table-column label="微信号码" align="center" prop="weChat" />
      <el-table-column label="服务商名称" align="center" prop="providerName" />
      <el-table-column label="发布时间" align="center" prop="pubdate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.pubdate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="上架状态" align="center" prop="pushStatus" >
        <template slot-scope="scope">
            {{ scope.row.pushStatus===1?'已上架':'未上架'}}
          </template>
        </el-table-column>

      <el-table-column label="是否推荐" align="center" prop="recommend" >
        <template slot-scope="scope">
            {{ scope.row.recommend===1?'推荐':'不推荐'}}
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['zhunong:work:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['zhunong:work:remove']"
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

    <!-- 添加或修改农事服务对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="标题" prop="title">
          <el-input v-model="form.title" placeholder="请输入标题" />
        </el-form-item>
        <el-form-item label="图片文件id" prop="image">
          <image-upload v-model="form.image"/>
        </el-form-item>
        <el-form-item label="服务价格" prop="price">
          <el-input v-model="form.price" placeholder="请输入服务价格" />
        </el-form-item>
        <el-form-item label="所属分类" prop="catgory">
          <el-input v-model="form.catgory" placeholder="请输入所属分类" />
        </el-form-item>
        <el-form-item label="摘要" prop="resume">
          <el-input v-model="form.resume" placeholder="请输入摘要" />
        </el-form-item>
        <el-form-item label="联系电话" prop="phone">
          <el-input v-model="form.phone" placeholder="请输入联系电话" />
        </el-form-item>
        <el-form-item label="微信号码" prop="weChat">
          <el-input v-model="form.weChat" placeholder="请输入微信号码" />
        </el-form-item>
        <el-form-item label="服务商名称" prop="providerName">
          <el-input v-model="form.providerName" placeholder="请输入服务商名称" />
        </el-form-item>
        <el-form-item label="描述" prop="description">
          <el-input v-model="form.description" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="发布时间" prop="pubdate">
          <el-date-picker clearable
            v-model="form.pubdate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择发布时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="发布人id" prop="pubAccountId">
          <el-input v-model="form.pubAccountId" placeholder="请输入发布人id" />
        </el-form-item>
        <el-form-item label="是否推荐：1.推荐；2.不推荐" prop="recommend">
          <el-input v-model="form.recommend" placeholder="请输入是否推荐：1.推荐；2.不推荐" />
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
import { listWork, getWork, delWork, addWork, updateWork,
  pushFarmWork,
  unpushFarmWork,
  recommendFarmWork,
  unrecommendFarmWork
 } from "@/api/zhunong/work";

export default {
  name: "Work",
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
      // 农事服务表格数据
      workList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        title: null,
        image: null,
        price: null,
        catgory: null,
        resume: null,
        phone: null,
        weChat: null,
        providerName: null,
        description: null,
        pubdate: null,
        pubAccountId: null,
        pushStatus: null,
        recommend: null,
        browseNum: null,
        createdTime: null,
        updatedTime: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询农事服务列表 */
    getList() {
      this.loading = true;
      listWork(this.queryParams).then(response => {
        this.workList = response.rows;
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
        image: null,
        price: null,
        catgory: null,
        resume: null,
        phone: null,
        weChat: null,
        providerName: null,
        description: null,
        pubdate: null,
        pubAccountId: null,
        pushStatus: null,
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
      this.title = "添加农事服务";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getWork(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改农事服务";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateWork(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addWork(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除农事服务编号为"' + ids + '"的数据项？').then(function() {
        return delWork(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('zhunong/work/export', {
        ...this.queryParams
      }, `work_${new Date().getTime()}.xlsx`)
    },
      // 上架
      async publish(row) {
      this.$confirm(`确认上架?`).then(() => {
        pushFarmWork([row.ids]).then((res) => {
          this.$message({
            type: "success",
            message: "成功",
          });
          this.query();
        });
      });
    },
    // 取消上架
    async revokePublish(row) {
      unpushFarmWork([row.id]).then((res) => {
        this.$message({
          type: "success",
          message: "成功",
        });
        this.query();
      });
    },
    // 批量上架
    async batchPublish() {
      let list = this.ids

      if (!list || list.length == 0) {
        this.$message({
          type: "warning",
          message: "请勾选数据",
        });
        return;
      }
      this.$confirm(`确认批量上架?`).then(() => {
        pushFarmWork(list).then((res) => {
          this.$message({
            type: "success",
            message: "批量上架成功",
          });
          this.query();
        });
      });
    },
    // 批量取消上架
    async revokeBatchPublish() {
      let list = this.ids

      if (!list || list.length == 0) {
        this.$message({
          type: "warning",
          message: "请勾选数据",
        });
        return;
      }
      this.$confirm(`确认批量取消上架?`).then(() => {
        unpushFarmWork(list).then((res) => {
          this.$message({
            type: "success",
            message: "批量取消上架成功",
          });
          this.query();
        });
      });
    },

    // 推荐
    async recommended(row) {
      this.$confirm(`确认推荐?`).then(() => {
        recommendFarmWork([row.id]).then((res) => {
          this.$message({
            type: "success",
            message: "成功",
          });
          this.query();
        });
      });
    },
    // 取消推荐
    async revokeRecommended(row) {
      unrecommendFarmWork([row.id]).then((res) => {
        this.$message({
          type: "success",
          message: "成功",
        });
        this.query();
      });
    },
    // 批量推荐
    async batchRecommended() {
      let list = this.ids

      if (!list || list.length == 0) {
        this.$message({
          type: "warning",
          message: "请勾选数据",
        });
        return;
      }
      this.$confirm(`确认批量推荐?`).then(() => {
        recommendFarmWork(list).then((res) => {
          this.$message({
            type: "success",
            message: "批量推荐成功",
          });
          this.query();
        });
      });
    },
    // 批量取消推荐
    async revokeBatchRecommended() {
      let list =this.ids

      if (!list || list.length == 0) {
        this.$message({
          type: "warning",
          message: "请勾选数据",
        });
        return;
      }
      this.$confirm(`确认批量取消推荐?`).then(() => {
        unrecommendFarmWork(list).then((res) => {
          this.$message({
            type: "success",
            message: "批量取消推荐成功",
          });
          this.query();
        });
      });
    }
  }
};
</script>
