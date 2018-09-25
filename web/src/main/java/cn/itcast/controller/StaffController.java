package cn.itcast.controller;

import cn.itcast.pojo.Result;
import cn.itcast.pojo.Staff;
import cn.itcast.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("staff")
public class StaffController extends BaseController {

    @Autowired
    private StaffService staffService;

    @RequestMapping("add")
    public Result add(@Valid Staff staff) {
        return staffService.insert(staff)?success():error();
    }
}
