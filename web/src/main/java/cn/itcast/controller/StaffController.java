package cn.itcast.controller;

import cn.itcast.pojo.Result;
import cn.itcast.pojo.Staff;
import cn.itcast.pojo.vo.BaseQueryVO;
import cn.itcast.pojo.vo.PageBean;
import cn.itcast.service.StaffService;
import cn.itcast.service.UserService;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.org.mozilla.javascript.internal.IdScriptableObject;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("staff")
public class StaffController extends BaseController {

    @Autowired
    private StaffService staffService;

    @RequestMapping("add")
    public @ResponseBody Result add(@Valid Staff staff) {
        staff.setDeltag("0");
        return staffService.insert(staff)?success():error();
    }

    @RequestMapping("list")
    public @ResponseBody PageBean<Staff> list(@Valid BaseQueryVO baseQueryVO) {
        PageBean<Staff> staffPageBean = staffService.selectByPage(baseQueryVO);
        return staffPageBean;
    }

    @RequestMapping("delete")
    public @ResponseBody Result delete(@RequestParam("ids[]") @Size(min = 0) String[] ids) {
        logger.info("参数为"+Arrays.toString(ids));
        return staffService.logicDeleteBatch(ids)?success():error();
    }

    @RequestMapping("edit")
    public @ResponseBody Result edit(@Valid Staff staff) {
        logger.info("参数为"+ staff.toString());
        return staffService.edit(staff)?success():error();
    }
}
