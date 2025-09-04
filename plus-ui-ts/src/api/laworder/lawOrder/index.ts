import request from '@/utils/request';
import { AxiosPromise } from 'axios';
import { LawOrderVO, LawOrderForm, LawOrderQuery } from '@/api/laworder/lawOrder/types';

/**
 * 查询法务接单记录列表
 * @param query
 * @returns {*}
 */

export const listLawOrder = (query?: LawOrderQuery): AxiosPromise<LawOrderVO[]> => {
  return request({
    url: '/laworder/lawOrder/list',
    method: 'get',
    params: query
  });
};

/**
 * 查询法务接单记录详细
 * @param id
 */
export const getLawOrder = (id: string | number): AxiosPromise<LawOrderVO> => {
  return request({
    url: '/laworder/lawOrder/' + id,
    method: 'get'
  });
};

/**
 * 新增法务接单记录
 * @param data
 */
export const addLawOrder = (data: LawOrderForm) => {
  return request({
    url: '/laworder/lawOrder',
    method: 'post',
    data: data
  });
};

/**
 * 修改法务接单记录
 * @param data
 */
export const updateLawOrder = (data: LawOrderForm) => {
  return request({
    url: '/laworder/lawOrder',
    method: 'put',
    data: data
  });
};

/**
 * 删除法务接单记录
 * @param id
 */
export const delLawOrder = (id: string | number | Array<string | number>) => {
  return request({
    url: '/laworder/lawOrder/' + id,
    method: 'delete'
  });
};
