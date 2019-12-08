package com.daydream.demo_core.page;

import java.util.List;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: 0839d
 * \* Date: 2019/9/13
 * \* Time: 5:47
 * \* To change this template use File | Settings | File Templates.
 * \* Description: 分页结果封装
 * \
 */
public class PageResult {
    /**
     * 当前页码
     */
    private int pageNum;
    /**
     * 每页数据数量
     */
    private int pageSize;
    /**
     * 数据总量
     */
    private Long totalSize;
    /**
     * 页码总数
     */
    private int totalPages;
    /**
     * 分页的数据
     */
    private List<?> content;

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

    public Long getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(Long totalSize) {
        this.totalSize = totalSize;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<?> getContent() {
        return content;
    }

    public void setContent(List<?> content) {
        this.content = content;
    }
}