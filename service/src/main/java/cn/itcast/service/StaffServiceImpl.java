package cn.itcast.service;

import cn.itcast.dao.StaffMapper;
import cn.itcast.pojo.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StaffServiceImpl implements StaffService {

    @Autowired
    private StaffMapper staffMapper;

    @Override
    public boolean insert(Staff staff) {
        return staffMapper.insertSelective(staff)<1?false:true;
    }
}
