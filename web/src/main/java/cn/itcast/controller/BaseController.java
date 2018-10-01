package cn.itcast.controller;

import cn.itcast.pojo.Result;
import cn.itcast.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class BaseController {

    private static ThreadLocal<HttpServletRequest> requestThreadLocal = new ThreadLocal<HttpServletRequest>();
    private static ThreadLocal<HttpServletResponse> responseThreadLocal = new ThreadLocal<HttpServletResponse>();
    private static ThreadLocal<HttpSession> sessionThreadLocal = new ThreadLocal<HttpSession>();

    public static Logger logger = LoggerFactory.getLogger(BaseController.class);

    @ModelAttribute
    public void init(HttpServletResponse response,HttpServletRequest request,HttpSession session) {
        requestThreadLocal.set(request);
        responseThreadLocal.set(response);
        sessionThreadLocal.set(session);
    }

    public HttpServletRequest getRequest() {
        return requestThreadLocal.get();
    }
    public HttpServletResponse getResponse() {
        return responseThreadLocal.get();
    }

    public HttpSession getSession() {
        return sessionThreadLocal.get();
    }

    @RequestMapping("page/{page1}/{page2}")
    public String toPage(@PathVariable("page1")String page1,@PathVariable("page2")String page2) {
        return "pages/"+page1+"/"+page2;
    }

    public Result success() {
        return new Result(200,"操作成功");
    }

    public <T>  Result success(T t) {
        return new Result(200,"操作成功",t);
    }

    public Result error() {
        return new Result(400,"操作失败");
    }

    public User getLoginUser() {
        Object user = getSession().getAttribute("user");
        if (user != null) {
            return (User) user;
        }
        throw new RuntimeException("无登录信息");
    }
}
