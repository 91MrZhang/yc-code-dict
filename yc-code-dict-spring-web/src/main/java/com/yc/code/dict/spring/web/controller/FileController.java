package com.yc.code.dict.spring.web.controller;

import com.yc.code.dict.spring.web.model.http.Request;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * FileController
 *
 * 这里主要记录一下文件前端输入输出的基本格式
 *
 * @author zhangyuting
 * @WeChat&Tel 18686838039
 */
@Api(tags = "文件")
@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
public class FileController {

    @ApiOperation(value = "上传")
    @PostMapping("/upload")
    @ResponseBody
    public void upload(@RequestPart("file") MultipartFile file) {
        osIn(file);
    }


    @ApiOperation(value = "下载")
    @PostMapping("/download")
    @ResponseBody
    public void downLoad(@RequestBody Request<String> req, HttpServletResponse response) {
        osOut(response);
    }


    public void osOut(HttpServletResponse response) {
        try {
            response.reset();
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-disposition", "attachment;filename=file.txt");
            ServletOutputStream os = response.getOutputStream();
            os.write("和谐社会".getBytes());
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void osIn(MultipartFile file){
        try {
            byte[] bytes = file.getBytes();
            String rs = new String(bytes, StandardCharsets.UTF_8);
            System.out.println(rs);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
