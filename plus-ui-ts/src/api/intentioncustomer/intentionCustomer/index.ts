import request from '@/utils/request';
import { AxiosPromise } from 'axios';
import { IntentionCustomerVO, IntentionCustomerForm, IntentionCustomerQuery } from '@/api/intentioncustomer/intentionCustomer/types';

/**
 * 查询客户意向登记列表
 * @param query
 * @returns {*}
 */

export const listIntentionCustomer = (query?: IntentionCustomerQuery): AxiosPromise<IntentionCustomerVO[]> => {
  return request({
    url: '/intentioncustomer/intentionCustomer/list',
    method: 'get',
    params: query
  });
};

/**
 * 查询客户意向登记详细
 * @param id
 */
export const getIntentionCustomer = (id: string | number): AxiosPromise<IntentionCustomerVO> => {
  return request({
    url: '/intentioncustomer/intentionCustomer/' + id,
    method: 'get'
  });
};

/**
 * 新增客户意向登记
 * @param data
 */
export const addIntentionCustomer = (data: IntentionCustomerForm) => {
  return request({
    url: '/intentioncustomer/intentionCustomer',
    method: 'post',
    data: data
  });
};

/**
 * 修改客户意向登记
 * @param data
 */
export const updateIntentionCustomer = (data: IntentionCustomerForm) => {
  return request({
    url: '/intentioncustomer/intentionCustomer',
    method: 'put',
    data: data
  });
};

/**
 * 删除客户意向登记
 * @param id
 */
export const delIntentionCustomer = (id: string | number | Array<string | number>) => {
  return request({
    url: '/intentioncustomer/intentionCustomer/' + id,
    method: 'delete'
  });
};
