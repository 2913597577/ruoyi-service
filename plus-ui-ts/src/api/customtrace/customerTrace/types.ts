export interface CustomerTraceVO {
  /**
   * 主键ID
   */
  id: string | number;

  /**
   * 客户ID（dc_customer.id）
   */
  customerId: string | number;

  /**
   * 跟进类型（PHONE电话/VISIT拜访/EMAIL邮件）
   */
  followType: string;

  /**
   * 跟进内容
   */
  followContent: string;

  /**
   * 下次跟进时间
   */
  nextFollowTime: string;

  /**
   * 跟进人（sys_user.user_id）
   */
  followBy: number;

}

export interface CustomerTraceForm extends BaseEntity {
  /**
   * 主键ID
   */
  id?: string | number;

  /**
   * 客户ID（dc_customer.id）
   */
  customerId?: string | number;

  /**
   * 跟进类型（PHONE电话/VISIT拜访/EMAIL邮件）
   */
  followType?: string;

  /**
   * 跟进内容
   */
  followContent?: string;

  /**
   * 下次跟进时间
   */
  nextFollowTime?: string;

  /**
   * 跟进人（sys_user.user_id）
   */
  followBy?: number;

}

export interface CustomerTraceQuery extends PageQuery {

  /**
   * 客户ID（dc_customer.id）
   */
  customerId?: string | number;

  /**
   * 跟进类型（PHONE电话/VISIT拜访/EMAIL邮件）
   */
  followType?: string;

  /**
   * 跟进内容
   */
  followContent?: string;

  /**
   * 下次跟进时间
   */
  nextFollowTime?: string;

  /**
   * 跟进人（sys_user.user_id）
   */
  followBy?: number;

    /**
     * 日期范围参数
     */
    params?: any;
}



