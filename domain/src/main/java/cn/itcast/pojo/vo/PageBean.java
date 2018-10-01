package cn.itcast.pojo.vo;

import java.util.List;

public class PageBean<T> {

    //总条数
    private int total;

    //每页数据
    private List<T> rows;

    public PageBean() {
    }

    public PageBean(List<T> list) {
    }

    public PageBean(int total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
