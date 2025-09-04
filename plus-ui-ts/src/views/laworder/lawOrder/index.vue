<template>
  <div class="p-2">
    <transition :enter-active-class="proxy?.animate.searchAnimate.enter" :leave-active-class="proxy?.animate.searchAnimate.leave">
      <div v-show="showSearch" class="mb-[10px]">
        <el-card shadow="hover">
          <el-form ref="queryFormRef" :model="queryParams" :inline="true">
            <el-form-item label="客户ID" prop="customerId">
              <el-input v-model="queryParams.customerId" placeholder="请输入客户ID" clearable @keyup.enter="handleQuery" />
            </el-form-item>
            <el-form-item label="法务人员ID" prop="legalSupportId">
              <el-input v-model="queryParams.legalSupportId" placeholder="请输入法务人员ID" clearable @keyup.enter="handleQuery" />
            </el-form-item>
            <el-form-item label="接单时间" prop="acceptTime">
              <el-date-picker clearable
                v-model="queryParams.acceptTime"
                type="date"
                value-format="YYYY-MM-DD"
                placeholder="请选择接单时间"
              />
            </el-form-item>
            <el-form-item label="服务截止时间" prop="deadline">
              <el-date-picker clearable
                v-model="queryParams.deadline"
                type="date"
                value-format="YYYY-MM-DD"
                placeholder="请选择服务截止时间"
              />
            </el-form-item>
            <el-form-item label="完成时间" prop="completeTime">
              <el-date-picker clearable
                v-model="queryParams.completeTime"
                type="date"
                value-format="YYYY-MM-DD"
                placeholder="请选择完成时间"
              />
            </el-form-item>
            <el-form-item label="实际服务时长" prop="serviceDuration">
              <el-input v-model="queryParams.serviceDuration" placeholder="请输入实际服务时长" clearable @keyup.enter="handleQuery" />
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
            <el-button type="primary" plain icon="Plus" @click="handleAdd" v-hasPermi="['laworder:lawOrder:add']">新增</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="success" plain icon="Edit" :disabled="single" @click="handleUpdate()" v-hasPermi="['laworder:lawOrder:edit']">修改</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" plain icon="Delete" :disabled="multiple" @click="handleDelete()" v-hasPermi="['laworder:lawOrder:remove']">删除</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="warning" plain icon="Download" @click="handleExport" v-hasPermi="['laworder:lawOrder:export']">导出</el-button>
          </el-col>
          <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
        </el-row>
      </template>

      <el-table v-loading="loading" border :data="lawOrderList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column label="主键ID" align="center" prop="id" v-if="true" />
        <el-table-column label="客户ID" align="center" prop="customerId" />
        <el-table-column label="法务人员ID" align="center" prop="legalSupportId" />
        <el-table-column label="接单时间" align="center" prop="acceptTime" width="180">
          <template #default="scope">
            <span>{{ parseTime(scope.row.acceptTime, '{y}-{m}-{d}') }}</span>
          </template>
        </el-table-column>
        <el-table-column label="服务截止时间" align="center" prop="deadline" width="180">
          <template #default="scope">
            <span>{{ parseTime(scope.row.deadline, '{y}-{m}-{d}') }}</span>
          </template>
        </el-table-column>
        <el-table-column label="完成时间" align="center" prop="completeTime" width="180">
          <template #default="scope">
            <span>{{ parseTime(scope.row.completeTime, '{y}-{m}-{d}') }}</span>
          </template>
        </el-table-column>
        <el-table-column label="实际服务时长" align="center" prop="serviceDuration" />
        <el-table-column label="状态" align="center" prop="orderStatus" />
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
          <template #default="scope">
            <el-tooltip content="修改" placement="top">
              <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['laworder:lawOrder:edit']"></el-button>
            </el-tooltip>
            <el-tooltip content="删除" placement="top">
              <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['laworder:lawOrder:remove']"></el-button>
            </el-tooltip>
          </template>
        </el-table-column>
      </el-table>

      <pagination v-show="total > 0" :total="total" v-model:page="queryParams.pageNum" v-model:limit="queryParams.pageSize" @pagination="getList" />
    </el-card>
    <!-- 添加或修改法务接单记录对话框 -->
    <el-dialog :title="dialog.title" v-model="dialog.visible" width="500px" append-to-body>
      <el-form ref="lawOrderFormRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="客户ID" prop="customerId">
          <el-input v-model="form.customerId" placeholder="请输入客户ID" />
        </el-form-item>
        <el-form-item label="法务人员ID" prop="legalSupportId">
          <el-input v-model="form.legalSupportId" placeholder="请输入法务人员ID" />
        </el-form-item>
        <el-form-item label="接单时间" prop="acceptTime">
          <el-date-picker clearable
            v-model="form.acceptTime"
            type="datetime"
            value-format="YYYY-MM-DD HH:mm:ss"
            placeholder="请选择接单时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="服务截止时间" prop="deadline">
          <el-date-picker clearable
            v-model="form.deadline"
            type="datetime"
            value-format="YYYY-MM-DD HH:mm:ss"
            placeholder="请选择服务截止时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="完成时间" prop="completeTime">
          <el-date-picker clearable
            v-model="form.completeTime"
            type="datetime"
            value-format="YYYY-MM-DD HH:mm:ss"
            placeholder="请选择完成时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="实际服务时长" prop="serviceDuration">
          <el-input v-model="form.serviceDuration" placeholder="请输入实际服务时长" />
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

<script setup name="LawOrder" lang="ts">
import { listLawOrder, getLawOrder, delLawOrder, addLawOrder, updateLawOrder } from '@/api/laworder/lawOrder';
import { LawOrderVO, LawOrderQuery, LawOrderForm } from '@/api/laworder/lawOrder/types';

const { proxy } = getCurrentInstance() as ComponentInternalInstance;

const lawOrderList = ref<LawOrderVO[]>([]);
const buttonLoading = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref<Array<string | number>>([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);

const queryFormRef = ref<ElFormInstance>();
const lawOrderFormRef = ref<ElFormInstance>();

const dialog = reactive<DialogOption>({
  visible: false,
  title: ''
});

const initFormData: LawOrderForm = {
  id: undefined,
  customerId: undefined,
  legalSupportId: undefined,
  acceptTime: undefined,
  deadline: undefined,
  completeTime: undefined,
  serviceDuration: undefined,
  orderStatus: undefined,
}
const data = reactive<PageData<LawOrderForm, LawOrderQuery>>({
  form: {...initFormData},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    customerId: undefined,
    legalSupportId: undefined,
    acceptTime: undefined,
    deadline: undefined,
    completeTime: undefined,
    serviceDuration: undefined,
    orderStatus: undefined,
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
    legalSupportId: [
      { required: true, message: "法务人员ID不能为空", trigger: "blur" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询法务接单记录列表 */
const getList = async () => {
  loading.value = true;
  const res = await listLawOrder(queryParams.value);
  lawOrderList.value = res.rows;
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
  lawOrderFormRef.value?.resetFields();
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
const handleSelectionChange = (selection: LawOrderVO[]) => {
  ids.value = selection.map(item => item.id);
  single.value = selection.length != 1;
  multiple.value = !selection.length;
}

/** 新增按钮操作 */
const handleAdd = () => {
  reset();
  dialog.visible = true;
  dialog.title = "添加法务接单记录";
}

/** 修改按钮操作 */
const handleUpdate = async (row?: LawOrderVO) => {
  reset();
  const _id = row?.id || ids.value[0]
  const res = await getLawOrder(_id);
  Object.assign(form.value, res.data);
  dialog.visible = true;
  dialog.title = "修改法务接单记录";
}

/** 提交按钮 */
const submitForm = () => {
  lawOrderFormRef.value?.validate(async (valid: boolean) => {
    if (valid) {
      buttonLoading.value = true;
      if (form.value.id) {
        await updateLawOrder(form.value).finally(() =>  buttonLoading.value = false);
      } else {
        await addLawOrder(form.value).finally(() =>  buttonLoading.value = false);
      }
      proxy?.$modal.msgSuccess("操作成功");
      dialog.visible = false;
      await getList();
    }
  });
}

/** 删除按钮操作 */
const handleDelete = async (row?: LawOrderVO) => {
  const _ids = row?.id || ids.value;
  await proxy?.$modal.confirm('是否确认删除法务接单记录编号为"' + _ids + '"的数据项？').finally(() => loading.value = false);
  await delLawOrder(_ids);
  proxy?.$modal.msgSuccess("删除成功");
  await getList();
}

/** 导出按钮操作 */
const handleExport = () => {
  proxy?.download('laworder/lawOrder/export', {
    ...queryParams.value
  }, `lawOrder_${new Date().getTime()}.xlsx`)
}

onMounted(() => {
  getList();
});
</script>
