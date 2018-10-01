package cn.itcast.pojo.vo;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

public class BaseQueryVO {

    //当前页
    @Min(0)
    private int page;

    //每页条数
    @Min(0)
    private int rows;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }
}
