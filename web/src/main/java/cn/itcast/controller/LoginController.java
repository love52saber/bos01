package cn.itcast.controller;

import cn.itcast.pojo.User;
import cn.itcast.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("login")
@Controller
public class LoginController extends BaseController {

    @Autowired
    private UserService userService;

    @RequestMapping("toLogin")
    public String toLogin() {
        return "login";
    }

    @RequestMapping("login")
    public String login(User user) {
        List<User> userList = userService.login(user);
        if (userList != null && userList.size()==1) {
            getSession().setAttribute("user", userList.get(0));
            return "pages/common/index";
        }
        return "login";
    }

}
