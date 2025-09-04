export interface LawOrderVO {
  /**
   * 主键ID
   */
  id: string | number;

  /**
   * 客户ID（dc_customer.id）
   */
  customerId: string | number;

  /**
   * 法务人员ID（dc_legal_support.id）
   */
  legalSupportId: string | number;

  /**
   * 接单时间
   */
  acceptTime: string;

  /**
   * 服务截止时间
   */
  deadline: string;

  /**
   * 完成时间
   */
  completeTime: string;

  /**
   * 实际服务时长（单位：分钟）
   */
  serviceDuration: number;

  /**
   * 状态（PENDING待处理/PROCESSING处理中/COMPLETED已完成）
   */
  orderStatus: string;

}

export interface LawOrderForm extends BaseEntity {
  /**
   * 主键ID
   */
  id?: string | number;

  /**
   * 客户ID（dc_customer.id）
   */
  customerId?: string | number;

  /**
   * 法务人员ID（dc_legal_support.id）
   */
  legalSupportId?: string | number;

  /**
   * 接单时间
   */
  acceptTime?: string;

  /**
   * 服务截止时间
   */
  deadline?: string;

  /**
   * 完成时间
   */
  completeTime?: string;

  /**
   * 实际服务时长（单位：分钟）
   */
  serviceDuration?: number;

  /**
   * 状态（PENDING待处理/PROCESSING处理中/COMPLETED已完成）
   */
  orderStatus?: string;

}

export interface LawOrderQuery extends PageQuery {

  /**
   * 客户ID（dc_customer.id）
   */
  customerId?: string | number;

  /**
   * 法务人员ID（dc_legal_support.id）
   */
  legalSupportId?: string | number;

  /**
   * 接单时间
   */
  acceptTime?: string;

  /**
   * 服务截止时间
   */
  deadline?: string;

  /**
   * 完成时间
   */
  completeTime?: string;

  /**
   * 实际服务时长（单位：分钟）
   */
  serviceDuration?: number;

  /**
   * 状态（PENDING待处理/PROCESSING处理中/COMPLETED已完成）
   */
  orderStatus?: string;

    /**
     * 日期范围参数
     */
    params?: any;
}



