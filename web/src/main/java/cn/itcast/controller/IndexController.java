package cn.itcast.controller;

import cn.itcast.pojo.Result;
import cn.itcast.pojo.User;
import cn.itcast.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.constraints.NotNull;

@RequestMapping("index")
@Controller
public class IndexController extends BaseController {

    @Autowired
    private UserService userService;

    @RequestMapping("changePsw")
    public @ResponseBody
    Result changePsw(@NotNull String password) {
        User loginUser = getLoginUser();
        User user = new User();
        user.setPassword(password);
        user.setId(loginUser.getId());
        logger.info(password);
        boolean isChangeSuccess = userService.changePsw(user);
        return isChangeSuccess?success():error();
    }
}
