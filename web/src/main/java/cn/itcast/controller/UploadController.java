package cn.itcast.controller;

import cn.itcast.pojo.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
@RequestMapping("upload")
public class UploadController extends BaseController {

    @RequestMapping("uploadFile")
    public Result uploadFile(MultipartFile multipartFile) throws IOException {

        //设置图片名
        String fileName = UUID.randomUUID().toString();
        //设置图片路径
        String filePath = "C:\\test\\"+fileName+multipartFile.getOriginalFilename();
        //放置图片
        multipartFile.transferTo(new File(filePath));
        return success();
    }
}
