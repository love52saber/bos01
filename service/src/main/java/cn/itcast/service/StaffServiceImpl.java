package cn.itcast.service;

import cn.itcast.dao.StaffMapper;
import cn.itcast.pojo.Staff;
import cn.itcast.pojo.vo.BaseQueryVO;
import cn.itcast.pojo.vo.PageBean;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@Service
public class StaffServiceImpl implements StaffService {

    @Autowired
    private StaffMapper staffMapper;

    @Override
    public boolean insert(Staff staff) {
        staff.setId(UUID.randomUUID().toString().replace("-",""));
        return staffMapper.insertSelective(staff)<1?false:true;
    }

    @Override
    public PageBean<Staff> selectByPage(BaseQueryVO baseQueryVO) {
        Staff staff = new Staff();
        BeanUtils.copyProperties(baseQueryVO,staff);
        PageHelper.startPage(baseQueryVO.getPage(),baseQueryVO.getRows());
        List<Staff> staffList = staffMapper.selectSelective(staff);
        PageInfo<Staff> staffPageInfo = new PageInfo<>(staffList);
        return new PageBean<>((int) staffPageInfo.getTotal(), staffPageInfo.getList());
    }

    @Override
    public boolean logicDeleteBatch(String[] ids) {
        return staffMapper.logicDeleteBatch(ids)<1?false:true;
    }

    @Override
    public boolean edit(Staff staff) {
        return staffMapper.updateByPrimaryKeySelective(staff)<1?false:true;
    }


}
