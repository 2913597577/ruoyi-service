<template>
  <div class="p-2">
    <transition :enter-active-class="proxy?.animate.searchAnimate.enter" :leave-active-class="proxy?.animate.searchAnimate.leave">
      <div v-show="showSearch" class="mb-[10px]">
        <el-card shadow="hover">
          <el-form ref="queryFormRef" :model="queryParams" :inline="true">
            <el-form-item label="用户ID" prop="userId">
              <el-input v-model="queryParams.userId" placeholder="请输入用户ID" clearable @keyup.enter="handleQuery" />
            </el-form-item>
            <el-form-item label="接单优先级" prop="supportLevel">
              <el-input v-model="queryParams.supportLevel" placeholder="请输入接单优先级" clearable @keyup.enter="handleQuery" />
            </el-form-item>
            <el-form-item label="最大同时处理案件数" prop="maxCaseCount">
              <el-input v-model="queryParams.maxCaseCount" placeholder="请输入最大同时处理案件数" clearable @keyup.enter="handleQuery" />
            </el-form-item>
            <el-form-item label="当前处理案件数" prop="currentCaseCount">
              <el-input v-model="queryParams.currentCaseCount" placeholder="请输入当前处理案件数" clearable @keyup.enter="handleQuery" />
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
            <el-button type="primary" plain icon="Plus" @click="handleAdd" v-hasPermi="['lawperson:lawPerson:add']">新增</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="success" plain icon="Edit" :disabled="single" @click="handleUpdate()" v-hasPermi="['lawperson:lawPerson:edit']">修改</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" plain icon="Delete" :disabled="multiple" @click="handleDelete()" v-hasPermi="['lawperson:lawPerson:remove']">删除</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="warning" plain icon="Download" @click="handleExport" v-hasPermi="['lawperson:lawPerson:export']">导出</el-button>
          </el-col>
          <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
        </el-row>
      </template>

      <el-table v-loading="loading" border :data="lawPersonList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column label="主键ID" align="center" prop="id" v-if="true" />
        <el-table-column label="用户ID" align="center" prop="userId" />
        <el-table-column label="接单优先级" align="center" prop="supportLevel" />
        <el-table-column label="最大同时处理案件数" align="center" prop="maxCaseCount" />
        <el-table-column label="当前处理案件数" align="center" prop="currentCaseCount" />
        <el-table-column label="状态" align="center" prop="status" />
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
          <template #default="scope">
            <el-tooltip content="修改" placement="top">
              <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['lawperson:lawPerson:edit']"></el-button>
            </el-tooltip>
            <el-tooltip content="删除" placement="top">
              <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['lawperson:lawPerson:remove']"></el-button>
            </el-tooltip>
          </template>
        </el-table-column>
      </el-table>

      <pagination v-show="total > 0" :total="total" v-model:page="queryParams.pageNum" v-model:limit="queryParams.pageSize" @pagination="getList" />
    </el-card>
    <!-- 添加或修改法务支持人员对话框 -->
    <el-dialog :title="dialog.title" v-model="dialog.visible" width="500px" append-to-body>
      <el-form ref="lawPersonFormRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="用户ID" prop="userId">
          <el-input v-model="form.userId" placeholder="请输入用户ID" />
        </el-form-item>
        <el-form-item label="接单优先级" prop="supportLevel">
          <el-input v-model="form.supportLevel" placeholder="请输入接单优先级" />
        </el-form-item>
        <el-form-item label="最大同时处理案件数" prop="maxCaseCount">
          <el-input v-model="form.maxCaseCount" placeholder="请输入最大同时处理案件数" />
        </el-form-item>
        <el-form-item label="当前处理案件数" prop="currentCaseCount">
          <el-input v-model="form.currentCaseCount" placeholder="请输入当前处理案件数" />
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

<script setup name="LawPerson" lang="ts">
import { listLawPerson, getLawPerson, delLawPerson, addLawPerson, updateLawPerson } from '@/api/lawperson/lawPerson';
import { LawPersonVO, LawPersonQuery, LawPersonForm } from '@/api/lawperson/lawPerson/types';

const { proxy } = getCurrentInstance() as ComponentInternalInstance;

const lawPersonList = ref<LawPersonVO[]>([]);
const buttonLoading = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref<Array<string | number>>([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);

const queryFormRef = ref<ElFormInstance>();
const lawPersonFormRef = ref<ElFormInstance>();

const dialog = reactive<DialogOption>({
  visible: false,
  title: ''
});

const initFormData: LawPersonForm = {
  id: undefined,
  userId: undefined,
  supportLevel: undefined,
  maxCaseCount: undefined,
  currentCaseCount: undefined,
  status: undefined,
}
const data = reactive<PageData<LawPersonForm, LawPersonQuery>>({
  form: {...initFormData},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    userId: undefined,
    supportLevel: undefined,
    maxCaseCount: undefined,
    currentCaseCount: undefined,
    status: undefined,
    params: {
    }
  },
  rules: {
    id: [
      { required: true, message: "主键ID不能为空", trigger: "blur" }
    ],
    userId: [
      { required: true, message: "用户ID不能为空", trigger: "blur" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询法务支持人员列表 */
const getList = async () => {
  loading.value = true;
  const res = await listLawPerson(queryParams.value);
  lawPersonList.value = res.rows;
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
  lawPersonFormRef.value?.resetFields();
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
const handleSelectionChange = (selection: LawPersonVO[]) => {
  ids.value = selection.map(item => item.id);
  single.value = selection.length != 1;
  multiple.value = !selection.length;
}

/** 新增按钮操作 */
const handleAdd = () => {
  reset();
  dialog.visible = true;
  dialog.title = "添加法务支持人员";
}

/** 修改按钮操作 */
const handleUpdate = async (row?: LawPersonVO) => {
  reset();
  const _id = row?.id || ids.value[0]
  const res = await getLawPerson(_id);
  Object.assign(form.value, res.data);
  dialog.visible = true;
  dialog.title = "修改法务支持人员";
}

/** 提交按钮 */
const submitForm = () => {
  lawPersonFormRef.value?.validate(async (valid: boolean) => {
    if (valid) {
      buttonLoading.value = true;
      if (form.value.id) {
        await updateLawPerson(form.value).finally(() =>  buttonLoading.value = false);
      } else {
        await addLawPerson(form.value).finally(() =>  buttonLoading.value = false);
      }
      proxy?.$modal.msgSuccess("操作成功");
      dialog.visible = false;
      await getList();
    }
  });
}

/** 删除按钮操作 */
const handleDelete = async (row?: LawPersonVO) => {
  const _ids = row?.id || ids.value;
  await proxy?.$modal.confirm('是否确认删除法务支持人员编号为"' + _ids + '"的数据项？').finally(() => loading.value = false);
  await delLawPerson(_ids);
  proxy?.$modal.msgSuccess("删除成功");
  await getList();
}

/** 导出按钮操作 */
const handleExport = () => {
  proxy?.download('lawperson/lawPerson/export', {
    ...queryParams.value
  }, `lawPerson_${new Date().getTime()}.xlsx`)
}

onMounted(() => {
  getList();
});
</script>
