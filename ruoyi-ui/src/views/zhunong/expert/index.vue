<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="专家名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入专家名称"
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
      <el-form-item label="专家简介" prop="description">
        <el-input
          v-model="queryParams.description"
          placeholder="请输入专家简介"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="所属机构" prop="organization">
        <el-input
          v-model="queryParams.organization"
          placeholder="请输入所属机构"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="专家等级" prop="levelCode">
        <el-input
          v-model="queryParams.levelCode"
          placeholder="请输入专家等级"
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
<!--      <el-form-item label="是否推荐(1推荐;2不推荐)" prop="recommend">-->
<!--        <el-input-->
<!--          v-model="queryParams.recommend"-->
<!--          placeholder="请输入是否推荐(1推荐;2不推荐)"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
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
          v-hasPermi="['zhunong:expert:add']"
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
          v-hasPermi="['zhunong:expert:edit']"
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
          v-hasPermi="['zhunong:expert:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['zhunong:expert:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="expertList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主键" align="center" prop="id" />
      <el-table-column label="专家名称" align="center" prop="name" />
      <el-table-column label="所属领域" align="center" prop="fieldCode" />
      <el-table-column label="专家简介" align="center" prop="description" />
<!--      <el-table-column label="头像图片id" align="center" prop="image" width="100">-->
<!--        <template slot-scope="scope">-->
<!--          <image-preview :src="scope.row.image" :width="50" :height="50"/>-->
<!--        </template>-->
<!--      </el-table-column>-->
<!--      <el-table-column label="所属机构" align="center" prop="organization" />-->
      <el-table-column label="专家等级" align="center" prop="levelCode" />
      <el-table-column label="发布时间" align="center" prop="publishTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.publishTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="发布状态(1已发布；2未发布)" align="center" prop="publishStatus" />
      <el-table-column label="是否推荐(1推荐;2不推荐)" align="center" prop="recommend" />
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
            v-hasPermi="['zhunong:expert:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['zhunong:expert:remove']"
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

    <!-- 添加或修改知识专家对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="专家名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入专家名称" />
        </el-form-item>
        <el-form-item label="所属领域" prop="fieldCode">
          <el-input v-model="form.fieldCode" placeholder="请输入所属领域" />
        </el-form-item>
        <el-form-item label="专家简介" prop="description">
          <el-input v-model="form.description" placeholder="请输入专家简介" />
        </el-form-item>
<!--        <el-form-item label="头像图片id" prop="image">-->
<!--          <image-upload v-model="form.image"/>-->
<!--        </el-form-item>-->
<!--        <el-form-item label="所属机构" prop="organization">-->
<!--          <el-input v-model="form.organization" placeholder="请输入所属机构" />-->
<!--        </el-form-item>-->
        <el-form-item label="专家等级" prop="levelCode">
          <el-input v-model="form.levelCode" placeholder="请输入专家等级" />
        </el-form-item>
        <el-form-item label="发布时间" prop="publishTime">
          <el-date-picker clearable
            v-model="form.publishTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择发布时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="是否推荐(1推荐;2不推荐)" prop="recommend">
          <el-input v-model="form.recommend" placeholder="请输入是否推荐(1推荐;2不推荐)" />
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
import { listExpert, getExpert, delExpert, addExpert, updateExpert } from "@/api/zhunong/expert";

export default {
  name: "Expert",
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
      // 知识专家表格数据
      expertList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        name: null,
        fieldCode: null,
        description: null,
        image: null,
        organization: null,
        levelCode: null,
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
        name: [
          { required: true, message: "专家名称不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询知识专家列表 */
    getList() {
      this.loading = true;
      listExpert(this.queryParams).then(response => {
        this.expertList = response.rows;
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
        name: null,
        fieldCode: null,
        description: null,
        image: null,
        organization: null,
        levelCode: null,
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
      this.title = "添加知识专家";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getExpert(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改知识专家";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateExpert(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addExpert(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除知识专家编号为"' + ids + '"的数据项？').then(function() {
        return delExpert(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('zhunong/expert/export', {
        ...this.queryParams
      }, `expert_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
