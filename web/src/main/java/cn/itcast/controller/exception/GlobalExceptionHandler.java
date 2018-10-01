package cn.itcast.controller.exception;

import cn.itcast.pojo.Result;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class GlobalExceptionHandler implements HandlerExceptionResolver {

    private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        Result<?> result = new Result<>();
        ModelAndView modelAndView = new ModelAndView();

        result = getResultFromException(e);
        if (httpServletRequest.getHeader("X-Requested-With") != null) {
            try {
                //ajax请求
                String s = JSONObject.toJSONString(result);
                httpServletResponse.setContentType("text/json;charset=utf-8");
                PrintWriter writer = httpServletResponse.getWriter();
                writer.write(s);
                writer.flush();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } else {
            //非ajax请求
            modelAndView.setViewName("error");
        }
        return modelAndView;
    }

    private Result<?> getResultFromException(Exception e) {
        Result<?> result = new Result<>();
        if (e instanceof ParamException) {
            result.setCode(701);
            result.setMsg(e.getMessage());
        }else{
            result.setCode(400);
            result.setMsg("未知错误");
            logger.error("未知异常",e);
        }
        return result;
    }
}
