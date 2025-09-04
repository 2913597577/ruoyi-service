export interface LawPersonVO {
  /**
   * 主键ID
   */
  id: string | number;

  /**
   * 用户ID（sys_user.user_id）
   */
  userId: string | number;

  /**
   * 接单优先级（数字越小优先级越高）
   */
  supportLevel: number;

  /**
   * 最大同时处理案件数
   */
  maxCaseCount: number;

  /**
   * 当前处理案件数
   */
  currentCaseCount: number;

  /**
   * 状态（0可用 1停用）
   */
  status: string;

}

export interface LawPersonForm extends BaseEntity {
  /**
   * 主键ID
   */
  id?: string | number;

  /**
   * 用户ID（sys_user.user_id）
   */
  userId?: string | number;

  /**
   * 接单优先级（数字越小优先级越高）
   */
  supportLevel?: number;

  /**
   * 最大同时处理案件数
   */
  maxCaseCount?: number;

  /**
   * 当前处理案件数
   */
  currentCaseCount?: number;

  /**
   * 状态（0可用 1停用）
   */
  status?: string;

}

export interface LawPersonQuery extends PageQuery {

  /**
   * 用户ID（sys_user.user_id）
   */
  userId?: string | number;

  /**
   * 接单优先级（数字越小优先级越高）
   */
  supportLevel?: number;

  /**
   * 最大同时处理案件数
   */
  maxCaseCount?: number;

  /**
   * 当前处理案件数
   */
  currentCaseCount?: number;

  /**
   * 状态（0可用 1停用）
   */
  status?: string;

    /**
     * 日期范围参数
     */
    params?: any;
}



