export interface CustomertotalVO {
  /**
   * 主键ID
   */
  id: string | number;

  /**
   * 意向客户ID（dc_intention_customer.id）
   */
  intentionId: string | number;

  /**
   * 客户类型（EXCELLENT优秀/RISK风险/INACTIVE未维护）
   */
  customerType: string;

  /**
   * 最后跟进时间
   */
  lastFollowTime: string;

  /**
   * 法务状态（PENDING待分配/PROCESSING处理中/COMPLETED已完成）
   */
  legalStatus: string;

  /**
   * 合同存储路径（sys_oss.url）
   */
  contractUrl: string;

  /**
   * 归档时间
   */
  archiveTime: string;

  /**
   * 归档人（sys_user.user_id）
   */
  archiveBy: number;

  /**
   * 归档/审批意见
   */
  archiveRemark: string;

  /**
   * 归档结果
   */
  archiveResult: string;

  /**
   * 归档人岗位ID
   */
  archivePost: number;

  /**
   * 归档人角色ID
   */
  archiveRole: number;

  /**
   * 归档来源
   */
  archiveSource: string;

}

export interface CustomertotalForm extends BaseEntity {
  /**
   * 主键ID
   */
  id?: string | number;

  /**
   * 意向客户ID（dc_intention_customer.id）
   */
  intentionId?: string | number;

  /**
   * 客户类型（EXCELLENT优秀/RISK风险/INACTIVE未维护）
   */
  customerType?: string;

  /**
   * 最后跟进时间
   */
  lastFollowTime?: string;

  /**
   * 法务状态（PENDING待分配/PROCESSING处理中/COMPLETED已完成）
   */
  legalStatus?: string;

  /**
   * 合同存储路径（sys_oss.url）
   */
  contractUrl?: string;

  /**
   * 归档时间
   */
  archiveTime?: string;

  /**
   * 归档人（sys_user.user_id）
   */
  archiveBy?: number;

  /**
   * 归档/审批意见
   */
  archiveRemark?: string;

  /**
   * 归档结果
   */
  archiveResult?: string;

  /**
   * 归档人岗位ID
   */
  archivePost?: number;

  /**
   * 归档人角色ID
   */
  archiveRole?: number;

  /**
   * 归档来源
   */
  archiveSource?: string;

}

export interface CustomertotalQuery extends PageQuery {

  /**
   * 意向客户ID（dc_intention_customer.id）
   */
  intentionId?: string | number;

  /**
   * 客户类型（EXCELLENT优秀/RISK风险/INACTIVE未维护）
   */
  customerType?: string;

  /**
   * 最后跟进时间
   */
  lastFollowTime?: string;

  /**
   * 法务状态（PENDING待分配/PROCESSING处理中/COMPLETED已完成）
   */
  legalStatus?: string;

  /**
   * 合同存储路径（sys_oss.url）
   */
  contractUrl?: string;

  /**
   * 归档时间
   */
  archiveTime?: string;

  /**
   * 归档人（sys_user.user_id）
   */
  archiveBy?: number;

  /**
   * 归档/审批意见
   */
  archiveRemark?: string;

  /**
   * 归档结果
   */
  archiveResult?: string;

  /**
   * 归档人岗位ID
   */
  archivePost?: number;

  /**
   * 归档人角色ID
   */
  archiveRole?: number;

  /**
   * 归档来源
   */
  archiveSource?: string;

    /**
     * 日期范围参数
     */
    params?: any;
}



