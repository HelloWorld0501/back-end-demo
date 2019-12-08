package com.daydream.demo_core.page;

import com.daydream.demo_common.utils.ReflectionUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;


/**
 * \* Created with IntelliJ IDEA.
 * \* User: 0839d
 * \* Date: 2019/9/13
 * \* Time: 5:56
 * \* To change this template use File | Settings | File Templates.
 * \* Description:分页查询工具
 * \
 */
public class MybatisPageHelper {
    public static final String findPage = "findPage";


    /**
     * 分页查询,约定查询方法名为"findPage"
     *
     * @param pageRequest 分页请求
     * @param mapper      Dao对象,Mybatis的Mapper
     * @return
     */
    public static PageResult findPage(PageRequest pageRequest, Object mapper) {
        return findPage(pageRequest, mapper, findPage);
    }

    /**
     * 调用分页插件进行分页查询
     *
     * @param pageRequest     分页请求
     * @param mapper          Dao对象,Mybatis的Mapper
     * @param queryMethodName 分页的查询方法名
     * @param args            方法参数
     * @return
     */
//  抑制编译器产生警告信息。
    @SuppressWarnings({"unchecked", "rawtypes"})
    public static PageResult findPage(PageRequest pageRequest, Object mapper, String queryMethodName, Object... args) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        //反射查询
        Object result = ReflectionUtils.invoke(mapper, queryMethodName, args);
        return getPageResult(pageRequest, new PageInfo((List) result));
    }

    /**
     * 将分页信息分装到统一的接口
     *
     * @param pageRequest
     * @param pageInfo
     * @return
     */
    private static PageResult getPageResult(PageRequest pageRequest, PageInfo<?> pageInfo) {
        PageResult pageResult = new PageResult();
        pageResult.setPageNum(pageInfo.getPageNum());
        pageResult.setPageSize(pageInfo.getPageSize());
        pageResult.setTotalSize(pageInfo.getTotal());
        pageResult.setTotalPages(pageInfo.getPages());
        pageResult.setContent(pageInfo.getList());
        return pageResult;
    }
}