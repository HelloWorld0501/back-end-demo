package com.daydream.demo_core.page;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: 0839d
 * \* Date: 2019/9/13
 * \* Time: 5:41
 * \* To change this template use File | Settings | File Templates.
 * \* Description: 分页请求封装
 * \
 */
public class PageRequest {

    /**
     * 当前页码
     */
    private int pageNum = 1;

    /**
     * 每页数据数量
     */
    private int pageSize = 10;

    /**
     * 分页数据
     */
    private List<?> content;
    /**
     * 查询的参数
     */
    private Map<String, Object> params = new HashMap<>();

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    public Object getParam(String key) {
        return getParams().get(key);
    }

    public List<?> getContent() {
        return content;
    }

    public void setContent(List<?> content) {
        this.content = content;
    }
}