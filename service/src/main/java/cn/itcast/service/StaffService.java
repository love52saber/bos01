package cn.itcast.service;

import cn.itcast.pojo.Staff;
import cn.itcast.pojo.vo.BaseQueryVO;
import cn.itcast.pojo.vo.PageBean;

public interface StaffService {
    boolean insert(Staff staff);

    PageBean<Staff> selectByPage(BaseQueryVO baseQueryVO);

    boolean logicDeleteBatch(String[] ids);

    boolean edit(Staff staff);
}
