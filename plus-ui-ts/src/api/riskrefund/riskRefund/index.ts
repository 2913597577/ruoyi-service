import request from '@/utils/request';
import { AxiosPromise } from 'axios';
import { RiskRefundVO, RiskRefundForm, RiskRefundQuery } from '@/api/riskrefund/riskRefund/types';

/**
 * 查询风险退费客户列表
 * @param query
 * @returns {*}
 */

export const listRiskRefund = (query?: RiskRefundQuery): AxiosPromise<RiskRefundVO[]> => {
  return request({
    url: '/riskrefund/riskRefund/list',
    method: 'get',
    params: query
  });
};

/**
 * 查询风险退费客户详细
 * @param id
 */
export const getRiskRefund = (id: string | number): AxiosPromise<RiskRefundVO> => {
  return request({
    url: '/riskrefund/riskRefund/' + id,
    method: 'get'
  });
};

/**
 * 新增风险退费客户
 * @param data
 */
export const addRiskRefund = (data: RiskRefundForm) => {
  return request({
    url: '/riskrefund/riskRefund',
    method: 'post',
    data: data
  });
};

/**
 * 修改风险退费客户
 * @param data
 */
export const updateRiskRefund = (data: RiskRefundForm) => {
  return request({
    url: '/riskrefund/riskRefund',
    method: 'put',
    data: data
  });
};

/**
 * 删除风险退费客户
 * @param id
 */
export const delRiskRefund = (id: string | number | Array<string | number>) => {
  return request({
    url: '/riskrefund/riskRefund/' + id,
    method: 'delete'
  });
};
