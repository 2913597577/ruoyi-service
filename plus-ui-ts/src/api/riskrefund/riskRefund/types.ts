export interface RiskRefundVO {
  /**
   * 主键ID
   */
  id: string | number;

  /**
   * 客户ID（dc_customer.id）
   */
  customerId: string | number;

  /**
   * 退费金额
   */
  refundAmount: number;

  /**
   * 退费原因
   */
  refundReason: string;

  /**
   * 退费时间
   */
  refundTime: string;

  /**
   * 审批人（sys_user.user_id）
   */
  approveBy: number;

}

export interface RiskRefundForm extends BaseEntity {
  /**
   * 主键ID
   */
  id?: string | number;

  /**
   * 客户ID（dc_customer.id）
   */
  customerId?: string | number;

  /**
   * 退费金额
   */
  refundAmount?: number;

  /**
   * 退费原因
   */
  refundReason?: string;

  /**
   * 退费时间
   */
  refundTime?: string;

  /**
   * 审批人（sys_user.user_id）
   */
  approveBy?: number;

}

export interface RiskRefundQuery extends PageQuery {

  /**
   * 客户ID（dc_customer.id）
   */
  customerId?: string | number;

  /**
   * 退费金额
   */
  refundAmount?: number;

  /**
   * 退费原因
   */
  refundReason?: string;

  /**
   * 退费时间
   */
  refundTime?: string;

  /**
   * 审批人（sys_user.user_id）
   */
  approveBy?: number;

    /**
     * 日期范围参数
     */
    params?: any;
}



