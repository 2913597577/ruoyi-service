<template>
  <div class="p-2">
    <transition :enter-active-class="proxy?.animate.searchAnimate.enter" :leave-active-class="proxy?.animate.searchAnimate.leave">
      <div v-show="showSearch" class="mb-[10px]">
        <el-card shadow="hover">
          <el-form ref="queryFormRef" :model="queryParams" :inline="true">
            <el-form-item label="意向客户ID" prop="intentionId">
              <el-input v-model="queryParams.intentionId" placeholder="请输入意向客户ID" clearable @keyup.enter="handleQuery" />
            </el-form-item>
            <el-form-item label="最后跟进时间" prop="lastFollowTime">
              <el-date-picker clearable
                v-model="queryParams.lastFollowTime"
                type="date"
                value-format="YYYY-MM-DD"
                placeholder="请选择最后跟进时间"
              />
            </el-form-item>
            <el-form-item label="合同存储路径" prop="contractUrl">
              <el-input v-model="queryParams.contractUrl" placeholder="请输入合同存储路径" clearable @keyup.enter="handleQuery" />
            </el-form-item>
            <el-form-item label="归档时间" prop="archiveTime">
              <el-date-picker clearable
                v-model="queryParams.archiveTime"
                type="date"
                value-format="YYYY-MM-DD"
                placeholder="请选择归档时间"
              />
            </el-form-item>
            <el-form-item label="归档人" prop="archiveBy">
              <el-input v-model="queryParams.archiveBy" placeholder="请输入归档人" clearable @keyup.enter="handleQuery" />
            </el-form-item>
            <el-form-item label="归档/审批意见" prop="archiveRemark">
              <el-input v-model="queryParams.archiveRemark" placeholder="请输入归档/审批意见" clearable @keyup.enter="handleQuery" />
            </el-form-item>
            <el-form-item label="归档结果" prop="archiveResult">
              <el-input v-model="queryParams.archiveResult" placeholder="请输入归档结果" clearable @keyup.enter="handleQuery" />
            </el-form-item>
            <el-form-item label="归档人岗位ID" prop="archivePost">
              <el-input v-model="queryParams.archivePost" placeholder="请输入归档人岗位ID" clearable @keyup.enter="handleQuery" />
            </el-form-item>
            <el-form-item label="归档人角色ID" prop="archiveRole">
              <el-input v-model="queryParams.archiveRole" placeholder="请输入归档人角色ID" clearable @keyup.enter="handleQuery" />
            </el-form-item>
            <el-form-item label="归档来源" prop="archiveSource">
              <el-input v-model="queryParams.archiveSource" placeholder="请输入归档来源" clearable @keyup.enter="handleQuery" />
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
            <el-button type="primary" plain icon="Plus" @click="handleAdd" v-hasPermi="['customertotal:customertotal:add']">新增</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="success" plain icon="Edit" :disabled="single" @click="handleUpdate()" v-hasPermi="['customertotal:customertotal:edit']">修改</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" plain icon="Delete" :disabled="multiple" @click="handleDelete()" v-hasPermi="['customertotal:customertotal:remove']">删除</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="warning" plain icon="Download" @click="handleExport" v-hasPermi="['customertotal:customertotal:export']">导出</el-button>
          </el-col>
          <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
        </el-row>
      </template>

      <el-table v-loading="loading" border :data="customertotalList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column label="主键ID" align="center" prop="id" v-if="true" />
        <el-table-column label="意向客户ID" align="center" prop="intentionId" />
        <el-table-column label="客户类型" align="center" prop="customerType" />
        <el-table-column label="最后跟进时间" align="center" prop="lastFollowTime" width="180">
          <template #default="scope">
            <span>{{ parseTime(scope.row.lastFollowTime, '{y}-{m}-{d}') }}</span>
          </template>
        </el-table-column>
        <el-table-column label="法务状态" align="center" prop="legalStatus" />
        <el-table-column label="合同存储路径" align="center" prop="contractUrl" />
        <el-table-column label="归档时间" align="center" prop="archiveTime" width="180">
          <template #default="scope">
            <span>{{ parseTime(scope.row.archiveTime, '{y}-{m}-{d}') }}</span>
          </template>
        </el-table-column>
        <el-table-column label="归档人" align="center" prop="archiveBy" />
        <el-table-column label="归档/审批意见" align="center" prop="archiveRemark" />
        <el-table-column label="归档结果" align="center" prop="archiveResult" />
        <el-table-column label="归档人岗位ID" align="center" prop="archivePost" />
        <el-table-column label="归档人角色ID" align="center" prop="archiveRole" />
        <el-table-column label="归档来源" align="center" prop="archiveSource" />
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
          <template #default="scope">
            <el-tooltip content="修改" placement="top">
              <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['customertotal:customertotal:edit']"></el-button>
            </el-tooltip>
            <el-tooltip content="删除" placement="top">
              <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['customertotal:customertotal:remove']"></el-button>
            </el-tooltip>
          </template>
        </el-table-column>
      </el-table>

      <pagination v-show="total > 0" :total="total" v-model:page="queryParams.pageNum" v-model:limit="queryParams.pageSize" @pagination="getList" />
    </el-card>
    <!-- 添加或修改全部客户对话框 -->
    <el-dialog :title="dialog.title" v-model="dialog.visible" width="500px" append-to-body>
      <el-form ref="customertotalFormRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="意向客户ID" prop="intentionId">
          <el-input v-model="form.intentionId" placeholder="请输入意向客户ID" />
        </el-form-item>
        <el-form-item label="最后跟进时间" prop="lastFollowTime">
          <el-date-picker clearable
            v-model="form.lastFollowTime"
            type="datetime"
            value-format="YYYY-MM-DD HH:mm:ss"
            placeholder="请选择最后跟进时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="合同存储路径" prop="contractUrl">
          <el-input v-model="form.contractUrl" placeholder="请输入合同存储路径" />
        </el-form-item>
        <el-form-item label="归档时间" prop="archiveTime">
          <el-date-picker clearable
            v-model="form.archiveTime"
            type="datetime"
            value-format="YYYY-MM-DD HH:mm:ss"
            placeholder="请选择归档时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="归档人" prop="archiveBy">
          <el-input v-model="form.archiveBy" placeholder="请输入归档人" />
        </el-form-item>
        <el-form-item label="归档/审批意见" prop="archiveRemark">
            <el-input v-model="form.archiveRemark" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="归档结果" prop="archiveResult">
          <el-input v-model="form.archiveResult" placeholder="请输入归档结果" />
        </el-form-item>
        <el-form-item label="归档人岗位ID" prop="archivePost">
          <el-input v-model="form.archivePost" placeholder="请输入归档人岗位ID" />
        </el-form-item>
        <el-form-item label="归档人角色ID" prop="archiveRole">
          <el-input v-model="form.archiveRole" placeholder="请输入归档人角色ID" />
        </el-form-item>
        <el-form-item label="归档来源" prop="archiveSource">
          <el-input v-model="form.archiveSource" placeholder="请输入归档来源" />
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

<script setup name="Customertotal" lang="ts">
import { listCustomertotal, getCustomertotal, delCustomertotal, addCustomertotal, updateCustomertotal } from '@/api/customertotal/customertotal';
import { CustomertotalVO, CustomertotalQuery, CustomertotalForm } from '@/api/customertotal/customertotal/types';

const { proxy } = getCurrentInstance() as ComponentInternalInstance;

const customertotalList = ref<CustomertotalVO[]>([]);
const buttonLoading = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref<Array<string | number>>([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);

const queryFormRef = ref<ElFormInstance>();
const customertotalFormRef = ref<ElFormInstance>();

const dialog = reactive<DialogOption>({
  visible: false,
  title: ''
});

const initFormData: CustomertotalForm = {
  id: undefined,
  intentionId: undefined,
  customerType: undefined,
  lastFollowTime: undefined,
  legalStatus: undefined,
  contractUrl: undefined,
  archiveTime: undefined,
  archiveBy: undefined,
  archiveRemark: undefined,
  archiveResult: undefined,
  archivePost: undefined,
  archiveRole: undefined,
  archiveSource: undefined
}
const data = reactive<PageData<CustomertotalForm, CustomertotalQuery>>({
  form: {...initFormData},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    intentionId: undefined,
    customerType: undefined,
    lastFollowTime: undefined,
    legalStatus: undefined,
    contractUrl: undefined,
    archiveTime: undefined,
    archiveBy: undefined,
    archiveRemark: undefined,
    archiveResult: undefined,
    archivePost: undefined,
    archiveRole: undefined,
    archiveSource: undefined,
    params: {
    }
  },
  rules: {
    id: [
      { required: true, message: "主键ID不能为空", trigger: "blur" }
    ],
    intentionId: [
      { required: true, message: "意向客户ID不能为空", trigger: "blur" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询全部客户列表 */
const getList = async () => {
  loading.value = true;
  const res = await listCustomertotal(queryParams.value);
  customertotalList.value = res.rows;
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
  customertotalFormRef.value?.resetFields();
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
const handleSelectionChange = (selection: CustomertotalVO[]) => {
  ids.value = selection.map(item => item.id);
  single.value = selection.length != 1;
  multiple.value = !selection.length;
}

/** 新增按钮操作 */
const handleAdd = () => {
  reset();
  dialog.visible = true;
  dialog.title = "添加全部客户";
}

/** 修改按钮操作 */
const handleUpdate = async (row?: CustomertotalVO) => {
  reset();
  const _id = row?.id || ids.value[0]
  const res = await getCustomertotal(_id);
  Object.assign(form.value, res.data);
  dialog.visible = true;
  dialog.title = "修改全部客户";
}

/** 提交按钮 */
const submitForm = () => {
  customertotalFormRef.value?.validate(async (valid: boolean) => {
    if (valid) {
      buttonLoading.value = true;
      if (form.value.id) {
        await updateCustomertotal(form.value).finally(() =>  buttonLoading.value = false);
      } else {
        await addCustomertotal(form.value).finally(() =>  buttonLoading.value = false);
      }
      proxy?.$modal.msgSuccess("操作成功");
      dialog.visible = false;
      await getList();
    }
  });
}

/** 删除按钮操作 */
const handleDelete = async (row?: CustomertotalVO) => {
  const _ids = row?.id || ids.value;
  await proxy?.$modal.confirm('是否确认删除全部客户编号为"' + _ids + '"的数据项？').finally(() => loading.value = false);
  await delCustomertotal(_ids);
  proxy?.$modal.msgSuccess("删除成功");
  await getList();
}

/** 导出按钮操作 */
const handleExport = () => {
  proxy?.download('customertotal/customertotal/export', {
    ...queryParams.value
  }, `customertotal_${new Date().getTime()}.xlsx`)
}

onMounted(() => {
  getList();
});
</script>
