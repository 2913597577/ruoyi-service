<template>
  <div class="p-2">
    <transition :enter-active-class="proxy?.animate.searchAnimate.enter" :leave-active-class="proxy?.animate.searchAnimate.leave">
      <div v-show="showSearch" class="mb-[10px]">
        <el-card shadow="hover">
          <el-form ref="queryFormRef" :model="queryParams" :inline="true">
            <el-form-item label="客户名称" prop="customerName">
              <el-input v-model="queryParams.customerName" placeholder="请输入客户名称" clearable @keyup.enter="handleQuery" />
            </el-form-item>
            <el-form-item label="联系方式" prop="contactPhone">
              <el-input v-model="queryParams.contactPhone" placeholder="请输入联系方式" clearable @keyup.enter="handleQuery" />
            </el-form-item>
            <el-form-item label="意向签约类型" prop="intentionType">
              <el-select v-model="queryParams.intentionType" placeholder="请选择意向签约类型" clearable >
                <el-option v-for="dict in intention_type" :key="dict.value" :label="dict.label" :value="dict.value"/>
              </el-select>
            </el-form-item>
            <el-form-item label="预计金额" prop="expectedAmount">
              <el-input v-model="queryParams.expectedAmount" placeholder="请输入预计金额" clearable @keyup.enter="handleQuery" />
            </el-form-item>
            <el-form-item label="城市" prop="city">
              <el-input v-model="queryParams.city" placeholder="请输入城市" clearable @keyup.enter="handleQuery" />
            </el-form-item>
            <el-form-item label="转介绍来源" prop="referralSource">
              <el-input v-model="queryParams.referralSource" placeholder="请输入转介绍来源" clearable @keyup.enter="handleQuery" />
            </el-form-item>
            <el-form-item label="流程实例ID" prop="flowInstanceId">
              <el-input v-model="queryParams.flowInstanceId" placeholder="请输入流程实例ID" clearable @keyup.enter="handleQuery" />
            </el-form-item>
            <el-form-item label="创建者" prop="createBy">
              <el-input v-model="queryParams.createBy" placeholder="请输入创建者" clearable @keyup.enter="handleQuery" />
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
            <el-button type="primary" plain icon="Plus" @click="handleAdd" v-hasPermi="['intentioncustomer:intentionCustomer:add']">新增</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="success" plain icon="Edit" :disabled="single" @click="handleUpdate()" v-hasPermi="['intentioncustomer:intentionCustomer:edit']">修改</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" plain icon="Delete" :disabled="multiple" @click="handleDelete()" v-hasPermi="['intentioncustomer:intentionCustomer:remove']">删除</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="warning" plain icon="Download" @click="handleExport" v-hasPermi="['intentioncustomer:intentionCustomer:export']">导出</el-button>
          </el-col>
          <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
        </el-row>
      </template>

      <el-table v-loading="loading" border :data="intentionCustomerList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column label="主键ID" align="center" prop="id" v-if="true" />
        <el-table-column label="客户名称" align="center" prop="customerName" />
        <el-table-column label="联系方式" align="center" prop="contactPhone" />
        <el-table-column label="意向签约类型" align="center" prop="intentionType">
          <template #default="scope">
            <dict-tag :options="intention_type" :value="scope.row.intentionType"/>
          </template>
        </el-table-column>
        <el-table-column label="预计金额" align="center" prop="expectedAmount" />
        <el-table-column label="城市" align="center" prop="city" />
        <el-table-column label="转介绍来源" align="center" prop="referralSource" />
        <el-table-column label="备注信息" align="center" prop="remark" />
        <el-table-column label="流程实例ID" align="center" prop="flowInstanceId" />
        <el-table-column label="创建者" align="center" prop="createBy" />
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
          <template #default="scope">
            <el-tooltip content="修改" placement="top">
              <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['intentioncustomer:intentionCustomer:edit']"></el-button>
            </el-tooltip>
            <el-tooltip content="删除" placement="top">
              <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['intentioncustomer:intentionCustomer:remove']"></el-button>
            </el-tooltip>
          </template>
        </el-table-column>
      </el-table>

      <pagination v-show="total > 0" :total="total" v-model:page="queryParams.pageNum" v-model:limit="queryParams.pageSize" @pagination="getList" />
    </el-card>
    <!-- 添加或修改客户意向登记对话框 -->
    <el-dialog :title="dialog.title" v-model="dialog.visible" width="500px" append-to-body>
      <el-form ref="intentionCustomerFormRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="客户名称" prop="customerName">
          <el-input v-model="form.customerName" placeholder="请输入客户名称" />
        </el-form-item>
        <el-form-item label="联系方式" prop="contactPhone">
          <el-input v-model="form.contactPhone" placeholder="请输入联系方式" />
        </el-form-item>
        <el-form-item label="意向签约类型" prop="intentionType">
          <el-select v-model="form.intentionType" placeholder="请选择意向签约类型">
            <el-option
                v-for="dict in intention_type"
                :key="dict.value"
                :label="dict.label"
                :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="预计金额" prop="expectedAmount">
          <el-input v-model="form.expectedAmount" placeholder="请输入预计金额" />
        </el-form-item>
        <el-form-item label="城市" prop="city">
          <el-input v-model="form.city" placeholder="请输入城市" />
        </el-form-item>
        <el-form-item label="转介绍来源" prop="referralSource">
          <el-input v-model="form.referralSource" placeholder="请输入转介绍来源" />
        </el-form-item>
        <el-form-item label="备注信息" prop="remark">
            <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="流程实例ID" prop="flowInstanceId">
          <el-input v-model="form.flowInstanceId" placeholder="请输入流程实例ID" />
        </el-form-item>
        <el-form-item label="创建者" prop="createBy">
          <el-input v-model="form.createBy" placeholder="请输入创建者" />
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

<script setup name="IntentionCustomer" lang="ts">
import { listIntentionCustomer, getIntentionCustomer, delIntentionCustomer, addIntentionCustomer, updateIntentionCustomer } from '@/api/intentioncustomer/intentionCustomer';
import { IntentionCustomerVO, IntentionCustomerQuery, IntentionCustomerForm } from '@/api/intentioncustomer/intentionCustomer/types';

const { proxy } = getCurrentInstance() as ComponentInternalInstance;
const { intention_type } = toRefs<any>(proxy?.useDict('intention_type'));

const intentionCustomerList = ref<IntentionCustomerVO[]>([]);
const buttonLoading = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref<Array<string | number>>([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);

const queryFormRef = ref<ElFormInstance>();
const intentionCustomerFormRef = ref<ElFormInstance>();

const dialog = reactive<DialogOption>({
  visible: false,
  title: ''
});

const initFormData: IntentionCustomerForm = {
  id: undefined,
  customerName: undefined,
  contactPhone: undefined,
  intentionType: undefined,
  expectedAmount: undefined,
  city: undefined,
  referralSource: undefined,
  remark: undefined,
  flowInstanceId: undefined,
  createBy: undefined,
}
const data = reactive<PageData<IntentionCustomerForm, IntentionCustomerQuery>>({
  form: {...initFormData},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    customerName: undefined,
    contactPhone: undefined,
    intentionType: undefined,
    expectedAmount: undefined,
    city: undefined,
    referralSource: undefined,
    flowInstanceId: undefined,
    createBy: undefined,
    params: {
    }
  },
  rules: {
    customerName: [
      { required: true, message: "客户名称不能为空", trigger: "blur" }
    ],
    contactPhone: [
      { required: true, message: "联系方式不能为空", trigger: "blur" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询客户意向登记列表 */
const getList = async () => {
  loading.value = true;
  const res = await listIntentionCustomer(queryParams.value);
  intentionCustomerList.value = res.rows;
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
  intentionCustomerFormRef.value?.resetFields();
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
const handleSelectionChange = (selection: IntentionCustomerVO[]) => {
  ids.value = selection.map(item => item.id);
  single.value = selection.length != 1;
  multiple.value = !selection.length;
}

/** 新增按钮操作 */
const handleAdd = () => {
  reset();
  dialog.visible = true;
  dialog.title = "添加客户意向登记";
}

/** 修改按钮操作 */
const handleUpdate = async (row?: IntentionCustomerVO) => {
  reset();
  const _id = row?.id || ids.value[0]
  const res = await getIntentionCustomer(_id);
  Object.assign(form.value, res.data);
  dialog.visible = true;
  dialog.title = "修改客户意向登记";
}

/** 提交按钮 */
const submitForm = () => {
  intentionCustomerFormRef.value?.validate(async (valid: boolean) => {
    if (valid) {
      buttonLoading.value = true;
      if (form.value.id) {
        await updateIntentionCustomer(form.value).finally(() =>  buttonLoading.value = false);
      } else {
        await addIntentionCustomer(form.value).finally(() =>  buttonLoading.value = false);
      }
      proxy?.$modal.msgSuccess("操作成功");
      dialog.visible = false;
      await getList();
    }
  });
}

/** 删除按钮操作 */
const handleDelete = async (row?: IntentionCustomerVO) => {
  const _ids = row?.id || ids.value;
  await proxy?.$modal.confirm('是否确认删除客户意向登记编号为"' + _ids + '"的数据项？').finally(() => loading.value = false);
  await delIntentionCustomer(_ids);
  proxy?.$modal.msgSuccess("删除成功");
  await getList();
}

/** 导出按钮操作 */
const handleExport = () => {
  proxy?.download('intentioncustomer/intentionCustomer/export', {
    ...queryParams.value
  }, `intentionCustomer_${new Date().getTime()}.xlsx`)
}

onMounted(() => {
  getList();
});
</script>
