import request from '@/utils/request';
import { AxiosPromise } from 'axios';
import { CustomerTraceVO, CustomerTraceForm, CustomerTraceQuery } from '@/api/customtrace/customerTrace/types';

/**
 * 查询客户跟进记录列表
 * @param query
 * @returns {*}
 */

export const listCustomerTrace = (query?: CustomerTraceQuery): AxiosPromise<CustomerTraceVO[]> => {
  return request({
    url: '/customtrace/customerTrace/list',
    method: 'get',
    params: query
  });
};

/**
 * 查询客户跟进记录详细
 * @param id
 */
export const getCustomerTrace = (id: string | number): AxiosPromise<CustomerTraceVO> => {
  return request({
    url: '/customtrace/customerTrace/' + id,
    method: 'get'
  });
};

/**
 * 新增客户跟进记录
 * @param data
 */
export const addCustomerTrace = (data: CustomerTraceForm) => {
  return request({
    url: '/customtrace/customerTrace',
    method: 'post',
    data: data
  });
};

/**
 * 修改客户跟进记录
 * @param data
 */
export const updateCustomerTrace = (data: CustomerTraceForm) => {
  return request({
    url: '/customtrace/customerTrace',
    method: 'put',
    data: data
  });
};

/**
 * 删除客户跟进记录
 * @param id
 */
export const delCustomerTrace = (id: string | number | Array<string | number>) => {
  return request({
    url: '/customtrace/customerTrace/' + id,
    method: 'delete'
  });
};
