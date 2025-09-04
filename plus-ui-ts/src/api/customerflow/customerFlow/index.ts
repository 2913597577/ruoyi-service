import request from '@/utils/request';
import { AxiosPromise } from 'axios';
import { CustomerFlowVO, CustomerFlowForm, CustomerFlowQuery } from '@/api/customerflow/customerFlow/types';

/**
 * 查询待提交流转单列表
 * @param query
 * @returns {*}
 */

export const listCustomerFlow = (query?: CustomerFlowQuery): AxiosPromise<CustomerFlowVO[]> => {
  return request({
    url: '/customerflow/customerFlow/list',
    method: 'get',
    params: query
  });
};

/**
 * 查询待提交流转单详细
 * @param id
 */
export const getCustomerFlow = (id: string | number): AxiosPromise<CustomerFlowVO> => {
  return request({
    url: '/customerflow/customerFlow/' + id,
    method: 'get'
  });
};

/**
 * 新增待提交流转单
 * @param data
 */
export const addCustomerFlow = (data: CustomerFlowForm) => {
  return request({
    url: '/customerflow/customerFlow',
    method: 'post',
    data: data
  });
};

/**
 * 修改待提交流转单
 * @param data
 */
export const updateCustomerFlow = (data: CustomerFlowForm) => {
  return request({
    url: '/customerflow/customerFlow',
    method: 'put',
    data: data
  });
};

/**
 * 删除待提交流转单
 * @param id
 */
export const delCustomerFlow = (id: string | number | Array<string | number>) => {
  return request({
    url: '/customerflow/customerFlow/' + id,
    method: 'delete'
  });
};
