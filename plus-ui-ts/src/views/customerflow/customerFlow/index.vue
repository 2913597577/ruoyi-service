<template>
  <div class="p-2">
    <transition :enter-active-class="proxy?.animate.searchAnimate.enter" :leave-active-class="proxy?.animate.searchAnimate.leave">
      <div v-show="showSearch" class="mb-[10px]">
        <el-card shadow="hover">
          <el-form ref="queryFormRef" :model="queryParams" :inline="true">
            <el-form-item label="客户ID" prop="customerId">
              <el-input v-model="queryParams.customerId" placeholder="请输入客户ID" clearable @keyup.enter="handleQuery" />
            </el-form-item>
            <el-form-item label="意向客户ID" prop="intentionId">
              <el-input v-model="queryParams.intentionId" placeholder="请输入意向客户ID" clearable @keyup.enter="handleQuery" />
            </el-form-item>
            <el-form-item label="流程实例ID" prop="flowInstanceId">
              <el-input v-model="queryParams.flowInstanceId" placeholder="请输入流程实例ID" clearable @keyup.enter="handleQuery" />
            </el-form-item>
            <el-form-item label="提交人ID" prop="submitBy">
              <el-input v-model="queryParams.submitBy" placeholder="请输入提交人ID" clearable @keyup.enter="handleQuery" />
            </el-form-item>
            <el-form-item label="提交时间" prop="submitTime">
              <el-date-picker clearable
                v-model="queryParams.submitTime"
                type="date"
                value-format="YYYY-MM-DD"
                placeholder="请选择提交时间"
              />
            </el-form-item>
            <el-form-item label="确认人ID" prop="confirmBy">
              <el-input v-model="queryParams.confirmBy" placeholder="请输入确认人ID" clearable @keyup.enter="handleQuery" />
            </el-form-item>
            <el-form-item label="确认时间" prop="confirmTime">
              <el-date-picker clearable
                v-model="queryParams.confirmTime"
                type="date"
                value-format="YYYY-MM-DD"
                placeholder="请选择确认时间"
              />
            </el-form-item>
            <el-form-item label="归档人ID" prop="archiveBy">
              <el-input v-model="queryParams.archiveBy" placeholder="请输入归档人ID" clearable @keyup.enter="handleQuery" />
            </el-form-item>
            <el-form-item label="归档时间" prop="archiveTime">
              <el-date-picker clearable
                v-model="queryParams.archiveTime"
                type="date"
                value-format="YYYY-MM-DD"
                placeholder="请选择归档时间"
              />
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
            <el-button type="primary" plain icon="Plus" @click="handleAdd" v-hasPermi="['customerflow:customerFlow:add']">新增</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="success" plain icon="Edit" :disabled="single" @click="handleUpdate()" v-hasPermi="['customerflow:customerFlow:edit']">修改</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" plain icon="Delete" :disabled="multiple" @click="handleDelete()" v-hasPermi="['customerflow:customerFlow:remove']">删除</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="warning" plain icon="Download" @click="handleExport" v-hasPermi="['customerflow:customerFlow:export']">导出</el-button>
          </el-col>
          <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
        </el-row>
      </template>

      <el-table v-loading="loading" border :data="customerFlowList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column label="主键ID" align="center" prop="id" v-if="true" />
        <el-table-column label="客户ID" align="center" prop="customerId" />
        <el-table-column label="意向客户ID" align="center" prop="intentionId" />
        <el-table-column label="流程实例ID" align="center" prop="flowInstanceId" />
        <el-table-column label="流转状态" align="center" prop="flowStatus" />
        <el-table-column label="流转类型" align="center" prop="flowType" />
        <el-table-column label="提交人ID" align="center" prop="submitBy" />
        <el-table-column label="提交时间" align="center" prop="submitTime" width="180">
          <template #default="scope">
            <span>{{ parseTime(scope.row.submitTime, '{y}-{m}-{d}') }}</span>
          </template>
        </el-table-column>
        <el-table-column label="确认人ID" align="center" prop="confirmBy" />
        <el-table-column label="确认时间" align="center" prop="confirmTime" width="180">
          <template #default="scope">
            <span>{{ parseTime(scope.row.confirmTime, '{y}-{m}-{d}') }}</span>
          </template>
        </el-table-column>
        <el-table-column label="归档人ID" align="center" prop="archiveBy" />
        <el-table-column label="归档时间" align="center" prop="archiveTime" width="180">
          <template #default="scope">
            <span>{{ parseTime(scope.row.archiveTime, '{y}-{m}-{d}') }}</span>
          </template>
        </el-table-column>
        <el-table-column label="备注" align="center" prop="remark" />
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
          <template #default="scope">
            <el-tooltip content="修改" placement="top">
              <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['customerflow:customerFlow:edit']"></el-button>
            </el-tooltip>
            <el-tooltip content="删除" placement="top">
              <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['customerflow:customerFlow:remove']"></el-button>
            </el-tooltip>
          </template>
        </el-table-column>
      </el-table>

      <pagination v-show="total > 0" :total="total" v-model:page="queryParams.pageNum" v-model:limit="queryParams.pageSize" @pagination="getList" />
    </el-card>
    <!-- 添加或修改待提交流转单对话框 -->
    <el-dialog :title="dialog.title" v-model="dialog.visible" width="500px" append-to-body>
      <el-form ref="customerFlowFormRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="客户ID" prop="customerId">
          <el-input v-model="form.customerId" placeholder="请输入客户ID" />
        </el-form-item>
        <el-form-item label="意向客户ID" prop="intentionId">
          <el-input v-model="form.intentionId" placeholder="请输入意向客户ID" />
        </el-form-item>
        <el-form-item label="流程实例ID" prop="flowInstanceId">
          <el-input v-model="form.flowInstanceId" placeholder="请输入流程实例ID" />
        </el-form-item>
        <el-form-item label="提交人ID" prop="submitBy">
          <el-input v-model="form.submitBy" placeholder="请输入提交人ID" />
        </el-form-item>
        <el-form-item label="提交时间" prop="submitTime">
          <el-date-picker clearable
            v-model="form.submitTime"
            type="datetime"
            value-format="YYYY-MM-DD HH:mm:ss"
            placeholder="请选择提交时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="确认人ID" prop="confirmBy">
          <el-input v-model="form.confirmBy" placeholder="请输入确认人ID" />
        </el-form-item>
        <el-form-item label="确认时间" prop="confirmTime">
          <el-date-picker clearable
            v-model="form.confirmTime"
            type="datetime"
            value-format="YYYY-MM-DD HH:mm:ss"
            placeholder="请选择确认时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="归档人ID" prop="archiveBy">
          <el-input v-model="form.archiveBy" placeholder="请输入归档人ID" />
        </el-form-item>
        <el-form-item label="归档时间" prop="archiveTime">
          <el-date-picker clearable
            v-model="form.archiveTime"
            type="datetime"
            value-format="YYYY-MM-DD HH:mm:ss"
            placeholder="请选择归档时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
            <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
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

<script setup name="CustomerFlow" lang="ts">
import { listCustomerFlow, getCustomerFlow, delCustomerFlow, addCustomerFlow, updateCustomerFlow } from '@/api/customerflow/customerFlow';
import { CustomerFlowVO, CustomerFlowQuery, CustomerFlowForm } from '@/api/customerflow/customerFlow/types';

const { proxy } = getCurrentInstance() as ComponentInternalInstance;

const customerFlowList = ref<CustomerFlowVO[]>([]);
const buttonLoading = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref<Array<string | number>>([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);

const queryFormRef = ref<ElFormInstance>();
const customerFlowFormRef = ref<ElFormInstance>();

const dialog = reactive<DialogOption>({
  visible: false,
  title: ''
});

const initFormData: CustomerFlowForm = {
  id: undefined,
  customerId: undefined,
  intentionId: undefined,
  flowInstanceId: undefined,
  flowStatus: undefined,
  flowType: undefined,
  submitBy: undefined,
  submitTime: undefined,
  confirmBy: undefined,
  confirmTime: undefined,
  archiveBy: undefined,
  archiveTime: undefined,
  remark: undefined,
}
const data = reactive<PageData<CustomerFlowForm, CustomerFlowQuery>>({
  form: {...initFormData},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    customerId: undefined,
    intentionId: undefined,
    flowInstanceId: undefined,
    flowStatus: undefined,
    flowType: undefined,
    submitBy: undefined,
    submitTime: undefined,
    confirmBy: undefined,
    confirmTime: undefined,
    archiveBy: undefined,
    archiveTime: undefined,
    params: {
    }
  },
  rules: {
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询待提交流转单列表 */
const getList = async () => {
  loading.value = true;
  const res = await listCustomerFlow(queryParams.value);
  customerFlowList.value = res.rows;
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
  customerFlowFormRef.value?.resetFields();
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
const handleSelectionChange = (selection: CustomerFlowVO[]) => {
  ids.value = selection.map(item => item.id);
  single.value = selection.length != 1;
  multiple.value = !selection.length;
}

/** 新增按钮操作 */
const handleAdd = () => {
  reset();
  dialog.visible = true;
  dialog.title = "添加待提交流转单";
}

/** 修改按钮操作 */
const handleUpdate = async (row?: CustomerFlowVO) => {
  reset();
  const _id = row?.id || ids.value[0]
  const res = await getCustomerFlow(_id);
  Object.assign(form.value, res.data);
  dialog.visible = true;
  dialog.title = "修改待提交流转单";
}

/** 提交按钮 */
const submitForm = () => {
  customerFlowFormRef.value?.validate(async (valid: boolean) => {
    if (valid) {
      buttonLoading.value = true;
      if (form.value.id) {
        await updateCustomerFlow(form.value).finally(() =>  buttonLoading.value = false);
      } else {
        await addCustomerFlow(form.value).finally(() =>  buttonLoading.value = false);
      }
      proxy?.$modal.msgSuccess("操作成功");
      dialog.visible = false;
      await getList();
    }
  });
}

/** 删除按钮操作 */
const handleDelete = async (row?: CustomerFlowVO) => {
  const _ids = row?.id || ids.value;
  await proxy?.$modal.confirm('是否确认删除待提交流转单编号为"' + _ids + '"的数据项？').finally(() => loading.value = false);
  await delCustomerFlow(_ids);
  proxy?.$modal.msgSuccess("删除成功");
  await getList();
}

/** 导出按钮操作 */
const handleExport = () => {
  proxy?.download('customerflow/customerFlow/export', {
    ...queryParams.value
  }, `customerFlow_${new Date().getTime()}.xlsx`)
}

onMounted(() => {
  getList();
});
</script>
