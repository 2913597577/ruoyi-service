import request from '@/utils/request';
import { AxiosPromise } from 'axios';
import { CustomertotalVO, CustomertotalForm, CustomertotalQuery } from '@/api/customertotal/customertotal/types';

/**
 * 查询全部客户列表
 * @param query
 * @returns {*}
 */

export const listCustomertotal = (query?: CustomertotalQuery): AxiosPromise<CustomertotalVO[]> => {
  return request({
    url: '/customertotal/customertotal/list',
    method: 'get',
    params: query
  });
};

/**
 * 查询全部客户详细
 * @param id
 */
export const getCustomertotal = (id: string | number): AxiosPromise<CustomertotalVO> => {
  return request({
    url: '/customertotal/customertotal/' + id,
    method: 'get'
  });
};

/**
 * 新增全部客户
 * @param data
 */
export const addCustomertotal = (data: CustomertotalForm) => {
  return request({
    url: '/customertotal/customertotal',
    method: 'post',
    data: data
  });
};

/**
 * 修改全部客户
 * @param data
 */
export const updateCustomertotal = (data: CustomertotalForm) => {
  return request({
    url: '/customertotal/customertotal',
    method: 'put',
    data: data
  });
};

/**
 * 删除全部客户
 * @param id
 */
export const delCustomertotal = (id: string | number | Array<string | number>) => {
  return request({
    url: '/customertotal/customertotal/' + id,
    method: 'delete'
  });
};
