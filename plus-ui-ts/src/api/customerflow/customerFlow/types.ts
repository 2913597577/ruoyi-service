export interface CustomerFlowVO {
  /**
   * 主键ID
   */
  id: string | number;

  /**
   * 客户ID（dc_customer.id）
   */
  customerId: string | number;

  /**
   * 意向客户ID（dc_intention_customer.id）
   */
  intentionId: string | number;

  /**
   * 流程实例ID（flow_instance.id）
   */
  flowInstanceId: string | number;

  /**
   * 流转状态（PENDING待提交/CONFIRM待确认/ARCHIVED已归档/REJECTED已退回/COMPLETED已完成）
   */
  flowStatus: string;

  /**
   * 流转类型（TRANSFER转交/ARCHIVE归档/RETURN退回等）
   */
  flowType: string;

  /**
   * 提交人ID（sys_user.user_id）
   */
  submitBy: number;

  /**
   * 提交时间
   */
  submitTime: string;

  /**
   * 确认人ID（sys_user.user_id）
   */
  confirmBy: number;

  /**
   * 确认时间
   */
  confirmTime: string;

  /**
   * 归档人ID（sys_user.user_id）
   */
  archiveBy: number;

  /**
   * 归档时间
   */
  archiveTime: string;

  /**
   * 备注
   */
  remark: string;

}

export interface CustomerFlowForm extends BaseEntity {
  /**
   * 主键ID
   */
  id?: string | number;

  /**
   * 客户ID（dc_customer.id）
   */
  customerId?: string | number;

  /**
   * 意向客户ID（dc_intention_customer.id）
   */
  intentionId?: string | number;

  /**
   * 流程实例ID（flow_instance.id）
   */
  flowInstanceId?: string | number;

  /**
   * 流转状态（PENDING待提交/CONFIRM待确认/ARCHIVED已归档/REJECTED已退回/COMPLETED已完成）
   */
  flowStatus?: string;

  /**
   * 流转类型（TRANSFER转交/ARCHIVE归档/RETURN退回等）
   */
  flowType?: string;

  /**
   * 提交人ID（sys_user.user_id）
   */
  submitBy?: number;

  /**
   * 提交时间
   */
  submitTime?: string;

  /**
   * 确认人ID（sys_user.user_id）
   */
  confirmBy?: number;

  /**
   * 确认时间
   */
  confirmTime?: string;

  /**
   * 归档人ID（sys_user.user_id）
   */
  archiveBy?: number;

  /**
   * 归档时间
   */
  archiveTime?: string;

  /**
   * 备注
   */
  remark?: string;

}

export interface CustomerFlowQuery extends PageQuery {

  /**
   * 客户ID（dc_customer.id）
   */
  customerId?: string | number;

  /**
   * 意向客户ID（dc_intention_customer.id）
   */
  intentionId?: string | number;

  /**
   * 流程实例ID（flow_instance.id）
   */
  flowInstanceId?: string | number;

  /**
   * 流转状态（PENDING待提交/CONFIRM待确认/ARCHIVED已归档/REJECTED已退回/COMPLETED已完成）
   */
  flowStatus?: string;

  /**
   * 流转类型（TRANSFER转交/ARCHIVE归档/RETURN退回等）
   */
  flowType?: string;

  /**
   * 提交人ID（sys_user.user_id）
   */
  submitBy?: number;

  /**
   * 提交时间
   */
  submitTime?: string;

  /**
   * 确认人ID（sys_user.user_id）
   */
  confirmBy?: number;

  /**
   * 确认时间
   */
  confirmTime?: string;

  /**
   * 归档人ID（sys_user.user_id）
   */
  archiveBy?: number;

  /**
   * 归档时间
   */
  archiveTime?: string;

    /**
     * 日期范围参数
     */
    params?: any;
}



