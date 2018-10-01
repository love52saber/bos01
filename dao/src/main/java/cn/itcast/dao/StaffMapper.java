package cn.itcast.dao;

import cn.itcast.pojo.Staff;
import cn.itcast.pojo.vo.BaseQueryVO;

import java.util.List;

public interface StaffMapper {
    int deleteByPrimaryKey(String id);

    int insert(Staff record);

    int insertSelective(Staff record);

    Staff selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Staff record);

    int updateByPrimaryKey(Staff record);

    List<Staff> selectSelective(Staff staff);

    int logicDeleteBatch(String[] ids);
}