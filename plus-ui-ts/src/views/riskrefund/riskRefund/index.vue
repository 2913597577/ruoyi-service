<template>
  <div class="p-2">
    <transition :enter-active-class="proxy?.animate.searchAnimate.enter" :leave-active-class="proxy?.animate.searchAnimate.leave">
      <div v-show="showSearch" class="mb-[10px]">
        <el-card shadow="hover">
          <el-form ref="queryFormRef" :model="queryParams" :inline="true">
            <el-form-item label="客户ID" prop="customerId">
              <el-input v-model="queryParams.customerId" placeholder="请输入客户ID" clearable @keyup.enter="handleQuery" />
            </el-form-item>
            <el-form-item label="退费金额" prop="refundAmount">
              <el-input v-model="queryParams.refundAmount" placeholder="请输入退费金额" clearable @keyup.enter="handleQuery" />
            </el-form-item>
            <el-form-item label="退费原因" prop="refundReason">
              <el-input v-model="queryParams.refundReason" placeholder="请输入退费原因" clearable @keyup.enter="handleQuery" />
            </el-form-item>
            <el-form-item label="退费时间" prop="refundTime">
              <el-date-picker clearable
                v-model="queryParams.refundTime"
                type="date"
                value-format="YYYY-MM-DD"
                placeholder="请选择退费时间"
              />
            </el-form-item>
            <el-form-item label="审批人" prop="approveBy">
              <el-input v-model="queryParams.approveBy" placeholder="请输入审批人" clearable @keyup.enter="handleQuery" />
            </el-form-item>
            <el-form-item>
              <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
              <el-button icon="Refresh" @click="resetQuery">重置</el-button>
            </el-form-item>
          </el-form>
        </el-card>
      </div>
    </transition>

    <el-card shadow="never">
      <template #header>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" plain icon="Plus" @click="handleAdd" v-hasPermi="['riskrefund:riskRefund:add']">新增</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="success" plain icon="Edit" :disabled="single" @click="handleUpdate()" v-hasPermi="['riskrefund:riskRefund:edit']">修改</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" plain icon="Delete" :disabled="multiple" @click="handleDelete()" v-hasPermi="['riskrefund:riskRefund:remove']">删除</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="warning" plain icon="Download" @click="handleExport" v-hasPermi="['riskrefund:riskRefund:export']">导出</el-button>
          </el-col>
          <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
        </el-row>
      </template>

      <el-table v-loading="loading" border :data="riskRefundList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column label="主键ID" align="center" prop="id" v-if="true" />
        <el-table-column label="客户ID" align="center" prop="customerId" />
        <el-table-column label="退费金额" align="center" prop="refundAmount" />
        <el-table-column label="退费原因" align="center" prop="refundReason" />
        <el-table-column label="退费时间" align="center" prop="refundTime" width="180">
          <template #default="scope">
            <span>{{ parseTime(scope.row.refundTime, '{y}-{m}-{d}') }}</span>
          </template>
        </el-table-column>
        <el-table-column label="审批人" align="center" prop="approveBy" />
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
          <template #default="scope">
            <el-tooltip content="修改" placement="top">
              <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['riskrefund:riskRefund:edit']"></el-button>
            </el-tooltip>
            <el-tooltip content="删除" placement="top">
              <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['riskrefund:riskRefund:remove']"></el-button>
            </el-tooltip>
          </template>
        </el-table-column>
      </el-table>

      <pagination v-show="total > 0" :total="total" v-model:page="queryParams.pageNum" v-model:limit="queryParams.pageSize" @pagination="getList" />
    </el-card>
    <!-- 添加或修改风险退费客户对话框 -->
    <el-dialog :title="dialog.title" v-model="dialog.visible" width="500px" append-to-body>
      <el-form ref="riskRefundFormRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="客户ID" prop="customerId">
          <el-input v-model="form.customerId" placeholder="请输入客户ID" />
        </el-form-item>
        <el-form-item label="退费金额" prop="refundAmount">
          <el-input v-model="form.refundAmount" placeholder="请输入退费金额" />
        </el-form-item>
        <el-form-item label="退费原因" prop="refundReason">
            <el-input v-model="form.refundReason" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="退费时间" prop="refundTime">
          <el-date-picker clearable
            v-model="form.refundTime"
            type="datetime"
            value-format="YYYY-MM-DD HH:mm:ss"
            placeholder="请选择退费时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="审批人" prop="approveBy">
          <el-input v-model="form.approveBy" placeholder="请输入审批人" />
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button :loading="buttonLoading" type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script setup name="RiskRefund" lang="ts">
import { listRiskRefund, getRiskRefund, delRiskRefund, addRiskRefund, updateRiskRefund } from '@/api/riskrefund/riskRefund';
import { RiskRefundVO, RiskRefundQuery, RiskRefundForm } from '@/api/riskrefund/riskRefund/types';

const { proxy } = getCurrentInstance() as ComponentInternalInstance;

const riskRefundList = ref<RiskRefundVO[]>([]);
const buttonLoading = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref<Array<string | number>>([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);

const queryFormRef = ref<ElFormInstance>();
const riskRefundFormRef = ref<ElFormInstance>();

const dialog = reactive<DialogOption>({
  visible: false,
  title: ''
});

const initFormData: RiskRefundForm = {
  id: undefined,
  customerId: undefined,
  refundAmount: undefined,
  refundReason: undefined,
  refundTime: undefined,
  approveBy: undefined,
}
const data = reactive<PageData<RiskRefundForm, RiskRefundQuery>>({
  form: {...initFormData},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    customerId: undefined,
    refundAmount: undefined,
    refundReason: undefined,
    refundTime: undefined,
    approveBy: undefined,
    params: {
    }
  },
  rules: {
    id: [
      { required: true, message: "主键ID不能为空", trigger: "blur" }
    ],
    customerId: [
      { required: true, message: "客户ID不能为空", trigger: "blur" }
    ],
    refundAmount: [
      { required: true, message: "退费金额不能为空", trigger: "blur" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询风险退费客户列表 */
const getList = async () => {
  loading.value = true;
  const res = await listRiskRefund(queryParams.value);
  riskRefundList.value = res.rows;
  total.value = res.total;
  loading.value = false;
}

/** 取消按钮 */
const cancel = () => {
  reset();
  dialog.visible = false;
}

/** 表单重置 */
const reset = () => {
  form.value = {...initFormData};
  riskRefundFormRef.value?.resetFields();
}

/** 搜索按钮操作 */
const handleQuery = () => {
  queryParams.value.pageNum = 1;
  getList();
}

/** 重置按钮操作 */
const resetQuery = () => {
  queryFormRef.value?.resetFields();
  handleQuery();
}

/** 多选框选中数据 */
const handleSelectionChange = (selection: RiskRefundVO[]) => {
  ids.value = selection.map(item => item.id);
  single.value = selection.length != 1;
  multiple.value = !selection.length;
}

/** 新增按钮操作 */
const handleAdd = () => {
  reset();
  dialog.visible = true;
  dialog.title = "添加风险退费客户";
}

/** 修改按钮操作 */
const handleUpdate = async (row?: RiskRefundVO) => {
  reset();
  const _id = row?.id || ids.value[0]
  const res = await getRiskRefund(_id);
  Object.assign(form.value, res.data);
  dialog.visible = true;
  dialog.title = "修改风险退费客户";
}

/** 提交按钮 */
const submitForm = () => {
  riskRefundFormRef.value?.validate(async (valid: boolean) => {
    if (valid) {
      buttonLoading.value = true;
      if (form.value.id) {
        await updateRiskRefund(form.value).finally(() =>  buttonLoading.value = false);
      } else {
        await addRiskRefund(form.value).finally(() =>  buttonLoading.value = false);
      }
      proxy?.$modal.msgSuccess("操作成功");
      dialog.visible = false;
      await getList();
    }
  });
}

/** 删除按钮操作 */
const handleDelete = async (row?: RiskRefundVO) => {
  const _ids = row?.id || ids.value;
  await proxy?.$modal.confirm('是否确认删除风险退费客户编号为"' + _ids + '"的数据项？').finally(() => loading.value = false);
  await delRiskRefund(_ids);
  proxy?.$modal.msgSuccess("删除成功");
  await getList();
}

/** 导出按钮操作 */
const handleExport = () => {
  proxy?.download('riskrefund/riskRefund/export', {
    ...queryParams.value
  }, `riskRefund_${new Date().getTime()}.xlsx`)
}

onMounted(() => {
  getList();
});
</script>
