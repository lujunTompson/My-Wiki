package com.chao.wiki.resp;

/*
封装分页返回参数
 */

import java.util.List;

public class PageResp<E> {
    //总条数
    private long total;

    //元素为泛型类的列表数据
    private List<E> list;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<E> getList() {
        return list;
    }

    public void setList(List<E> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "PageResp{" +
                "total=" + total +
                ", list=" + list +
                '}';
    }
}