export interface IntentionCustomerVO {
  /**
   * 主键ID
   */
  id: string | number;

  /**
   * 客户名称
   */
  customerName: string;

  /**
   * 联系方式
   */
  contactPhone: string;

  /**
   * 意向签约类型
   */
  intentionType: string;

  /**
   * 预计金额
   */
  expectedAmount: number;

  /**
   * 城市
   */
  city: string;

  /**
   * 转介绍来源
   */
  referralSource: string;

  /**
   * 备注信息
   */
  remark: string;

  /**
   * 流程实例ID（flow_instance.id）
   */
  flowInstanceId: string | number;

  /**
   * 创建者（sys_user.user_id）
   */
  createBy: number;

}

export interface IntentionCustomerForm extends BaseEntity {
  /**
   * 主键ID
   */
  id?: string | number;

  /**
   * 客户名称
   */
  customerName?: string;

  /**
   * 联系方式
   */
  contactPhone?: string;

  /**
   * 意向签约类型
   */
  intentionType?: string;

  /**
   * 预计金额
   */
  expectedAmount?: number;

  /**
   * 城市
   */
  city?: string;

  /**
   * 转介绍来源
   */
  referralSource?: string;

  /**
   * 备注信息
   */
  remark?: string;

  /**
   * 流程实例ID（flow_instance.id）
   */
  flowInstanceId?: string | number;

  /**
   * 创建者（sys_user.user_id）
   */
  createBy?: number;

}

export interface IntentionCustomerQuery extends PageQuery {

  /**
   * 客户名称
   */
  customerName?: string;

  /**
   * 联系方式
   */
  contactPhone?: string;

  /**
   * 意向签约类型
   */
  intentionType?: string;

  /**
   * 预计金额
   */
  expectedAmount?: number;

  /**
   * 城市
   */
  city?: string;

  /**
   * 转介绍来源
   */
  referralSource?: string;

  /**
   * 流程实例ID（flow_instance.id）
   */
  flowInstanceId?: string | number;

  /**
   * 创建者（sys_user.user_id）
   */
  createBy?: number;

    /**
     * 日期范围参数
     */
    params?: any;
}



