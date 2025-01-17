<!--搜索栏-->
<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="产品名称" prop="title">
        <el-input
          v-model="queryParams.title"
          placeholder="请输入产品名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="卖点摘要" prop="resume">
        <el-input
          v-model="queryParams.resume"
          placeholder="请输入卖点摘要"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
<!--      <el-form-item label="所属品类" prop="catgory">-->
<!--        <el-input-->
<!--          v-model="queryParams.catgory"-->
<!--          placeholder="请输入所属品类"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
      <el-form-item label="所属商户" prop="providerName">
        <el-input
          v-model="queryParams.providerName"
          placeholder="请输入所属商户"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
<!--      <el-form-item label="价格" prop="price">-->
<!--        <el-input-->
<!--          v-model="queryParams.price"-->
<!--          placeholder="请输入价格"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="上架时间" prop="pushTime">-->
<!--        <el-date-picker clearable-->
<!--          v-model="queryParams.pushTime"-->
<!--          type="date"-->
<!--          value-format="yyyy-MM-dd"-->
<!--          placeholder="请选择上架时间">-->
<!--        </el-date-picker>-->
<!--      </el-form-item>-->
      <el-form-item label="是否推荐" prop="recommend">
        <el-input
          v-model="queryParams.recommend"
          placeholder="1.推荐；2.不推荐"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
<!--      <el-form-item label="创建人id" prop="accountId">-->
<!--        <el-input-->
<!--          v-model="queryParams.accountId"-->
<!--          placeholder="请输入创建人id"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="浏览数量" prop="browseNum">-->
<!--        <el-input-->
<!--          v-model="queryParams.browseNum"-->
<!--          placeholder="请输入浏览数量"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="创建时间" prop="createdTime">-->
<!--        <el-date-picker clearable-->
<!--          v-model="queryParams.createdTime"-->
<!--          type="date"-->
<!--          value-format="yyyy-MM-dd"-->
<!--          placeholder="请选择创建时间">-->
<!--        </el-date-picker>-->
<!--      </el-form-item>-->
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
          v-hasPermi="['zhunong:produce:add']"
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
          v-hasPermi="['zhunong:produce:edit']"
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
          v-hasPermi="['zhunong:produce:remove']"
        >删除</el-button>
      </el-col>

      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="multiple"
          @click="handleBatchPush"
          v-hasPermi="['zhunong:produce:publish']"
        >批量上架</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleBatchUnpush"
          v-hasPermi="['zhunong:produce:unpublish']"
        >批量取消上架</el-button
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
          v-hasPermi="['zhunong:produce:recommend']"
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
          v-hasPermi="['zhunong:produce:unrecommend']"
        >批量取消推荐</el-button
        ></el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['zhunong:produce:export']"
        >导出</el-button>
      </el-col>

      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="produceList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主键" align="center" prop="id" />
      <el-table-column label="农产品名称" align="center" prop="title" />
      <el-table-column label="图片文件id" align="center" prop="image" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.image" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="卖点摘要" align="center" prop="resume" />
<!--      <el-table-column label="所属品类" align="center" prop="catgory" />-->
<!--      <el-table-column label="农产品类型" align="center" prop="produceType" />-->
      <el-table-column label="所属商户" align="center" prop="providerName" />
      <el-table-column label="价格" align="center" prop="price" />
      <el-table-column label="上架时间" align="center" prop="pushTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.pushTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="上架状态" align="center" prop="pushStatus">
        <!-- <template slot-scope="scope">
          {{ scope.row.pushStatus === 1 ? "已上架" : "未上架" }}
        </template> -->
      </el-table-column>
      <el-table-column label="是否推荐" align="center" prop="recommend">
        <!-- <template slot-scope="scope">
          {{ scope.row.recommend === 1 ? "推荐" : "不推荐" }}
        </template> -->
      </el-table-column>
<!--      <el-table-column label="产品详情" align="center" prop="description" />-->
<!--      <el-table-column label="创建人id" align="center" prop="accountId" />-->
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
            v-hasPermi="['zhunong:produce:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['zhunong:produce:remove']"
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

    <!-- 添加或修改农产品1对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="农产品名称" prop="title">
          <el-input v-model="form.title" placeholder="请输入农产品名称" />
        </el-form-item>
        <el-form-item label="图片文件" prop="image">
          <image-upload v-model="form.image"/>
        </el-form-item>
        <el-form-item label="卖点摘要" prop="resume">
          <el-input v-model="form.resume" placeholder="请输入卖点摘要" />
        </el-form-item>
<!--        <el-form-item label="所属品类" prop="catgory">-->
<!--          <el-input v-model="form.catgory" placeholder="请输入所属品类" />-->
<!--        </el-form-item>-->
        <el-form-item label="所属商户" prop="providerName">
          <el-input v-model="form.providerName" placeholder="请输入所属商户名称" />
        </el-form-item>
        <el-form-item label="价格" prop="price">
          <el-input v-model="form.price" placeholder="请输入价格" />
        </el-form-item>
        <el-form-item label="上架时间" prop="pushTime">
          <el-date-picker clearable
            v-model="form.pushTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择上架时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="是否推荐" prop="recommend">
          <el-input v-model="form.recommend" placeholder="请输入推荐或不推荐" />
        </el-form-item>
<!--        <el-form-item label="产品详情" prop="description">-->
<!--          <el-input v-model="form.description" type="textarea" placeholder="请输入内容" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="创建人id" prop="accountId">-->
<!--          <el-input v-model="form.accountId" placeholder="请输入创建人id" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="浏览数量" prop="browseNum">-->
<!--          <el-input v-model="form.browseNum" placeholder="请输入浏览数量" />-->
<!--        </el-form-item>-->
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
import {
  listProduce,
  getProduce,
  delProduce,
  addProduce,
  updateProduce ,
  batchPushNbFarmProduce,
  revokeBatchPushNbFarmProduce,
  recommendNbFarmProduce,
  unRecommendNbFarmProduce,
} from "@/api/zhunong/produce";

export default {
  name: "Produce",
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
      // 农产品1表格数据
      produceList: [],
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
        catgory: null,
        produceType: null,
        providerName: null,
        price: null,
        pushTime: null,
        pushStatus: null,
        recommend: null,
        description: null,
        accountId: null,
        browseNum: null,
        createdTime: null,
        updatedTime: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        title: [
          { required: true, message: "农产品名称不能为空", trigger: "blur" }
        ],
        price: [
          { required: true, message: "价格不能为空", trigger: "blur" }
        ],
        pushStatus: [
          { required: true, message: "上架状态不能为空", trigger: "change" }
        ],
        recommend: [
          { required: true, message: "是否推荐不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询农产品列表 */
    getList() {
      this.loading = true;
      listProduce(this.queryParams).then(response => {
        this.produceList = response.rows;
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
        resume: null,
        catgory: null,
        produceType: null,
        providerName: null,
        price: null,
        pushTime: null,
        pushStatus: null,
        recommend: null,
        description: null,
        accountId: null,
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
      this.title = "添加农产品1";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getProduce(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改农产品1";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateProduce(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addProduce(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除农产品1编号为"' + ids + '"的数据项？').then(function() {
        return delProduce(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('zhunong/produce/export', {
        ...this.queryParams
      }, `produce_${new Date().getTime()}.xlsx`)
    },
    /** 批量上架按钮操作 */
    handleBatchPush(row) {
      const ids = row.id || this.ids;
      this.$modal
        .confirm('是否确认上架农产品编号为"' + ids + '"的数据项？')
        .then(function () {
          return batchPushNbFarmProduce(ids);
        })
        .then(() => {
          console.log('批量上架成功，重新获取数据');
          this.getList();
          this.$modal.msgSuccess("上架成功");
        })
        .catch(() => {});
    },

    /** 批量取消上架按钮操作 */
    handleBatchUnpush(row) {
      const ids = row.id || this.ids;
      this.$modal
        .confirm('是否确认取消上架农产品编号为"' + ids + '"的数据项？')
        .then(function () {
          return revokeBatchPushNbFarmProduce(ids);
        })
        .then(() => {
          this.getList();
          this.$modal.msgSuccess("取消上架成功");
        })
        .catch(() => {});
    },
    /** 批量推荐按钮操作 */
    handleBatchRecommend(row) {
      const ids = row.id || this.ids;
      this.$modal
        .confirm('是否确认取消推荐农产品编号为"' + ids + '"的数据项？')
        .then(function () {
          return recommendNbFarmProduce(ids);
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
        .confirm('是否确认取消推荐农产品编号为"' + ids + '"的数据项？')
        .then(function () {
          return unRecommendNbFarmProduce(ids);
        })
        .then(() => {
          this.getList();
          this.$modal.msgSuccess("取消推荐成功");
        })
        .catch(() => {});
    },
  }
};
</script>
