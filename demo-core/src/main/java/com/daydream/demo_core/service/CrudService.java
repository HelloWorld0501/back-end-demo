package com.daydream.demo_core.service;

import com.daydream.demo_core.page.PageRequest;
import com.daydream.demo_core.page.PageResult;

import java.util.List;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: 0839d
 * \* Date: 2019/9/11
 * \* Time: 18:52
 * \* To change this template use File | Settings | File Templates.
 * \* Description: 通用的Crud接口
 * \
 */
public interface CrudService<T> {
    /**
     * 保存操作
     *
     * @param record
     * @return
     */
    int save(T record);

    /**
     * 删除操作
     *
     * @param record
     * @return
     */
    int delete(T record);

    /**
     * 批量删除操作
     *
     * @param records
     * @return
     */
    int delete(List<T> records);

    /**
     * 根据Id查找
     *
     * @param id
     * @return
     */
    T findById(Long id);

    /**
     * @param pageRequest 统一的分页查询请求
     * @return PageResult 统一的分页查询结果
     */
    PageResult findPage(PageRequest pageRequest);
}