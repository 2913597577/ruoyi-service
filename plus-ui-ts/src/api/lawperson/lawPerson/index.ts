import request from '@/utils/request';
import { AxiosPromise } from 'axios';
import { LawPersonVO, LawPersonForm, LawPersonQuery } from '@/api/lawperson/lawPerson/types';

/**
 * 查询法务支持人员列表
 * @param query
 * @returns {*}
 */

export const listLawPerson = (query?: LawPersonQuery): AxiosPromise<LawPersonVO[]> => {
  return request({
    url: '/lawperson/lawPerson/list',
    method: 'get',
    params: query
  });
};

/**
 * 查询法务支持人员详细
 * @param id
 */
export const getLawPerson = (id: string | number): AxiosPromise<LawPersonVO> => {
  return request({
    url: '/lawperson/lawPerson/' + id,
    method: 'get'
  });
};

/**
 * 新增法务支持人员
 * @param data
 */
export const addLawPerson = (data: LawPersonForm) => {
  return request({
    url: '/lawperson/lawPerson',
    method: 'post',
    data: data
  });
};

/**
 * 修改法务支持人员
 * @param data
 */
export const updateLawPerson = (data: LawPersonForm) => {
  return request({
    url: '/lawperson/lawPerson',
    method: 'put',
    data: data
  });
};

/**
 * 删除法务支持人员
 * @param id
 */
export const delLawPerson = (id: string | number | Array<string | number>) => {
  return request({
    url: '/lawperson/lawPerson/' + id,
    method: 'delete'
  });
};
