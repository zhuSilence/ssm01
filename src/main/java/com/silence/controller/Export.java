package com.silence.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by zhuxiang on 2016/5/25.
 * Desc :
 */
@Controller
public class Export extends HttpServlet {

    @RequestMapping(value = "/download.action")
    public void download(HttpServletRequest request, HttpServletResponse response) throws Exception {

        //获得请求文件名
        String filename = request.getParameter("filename");
        System.out.println(filename);

        //设置文件MIME类型
        response.setContentType(getServletContext().getMimeType(filename));
        response.setContentType(getServletContext().getMimeType(filename));
        //设置Content-Disposition
        response.setHeader("Content-Disposition", "attachment;filename=" + filename);
        //读取目标文件，通过response将目标文件写到客户端
        //获取目标文件的绝对路径
        String fullFileName = getServletContext().getRealPath("/download/" + filename);
        //System.out.println(fullFileName);
        //读取文件
        InputStream in = new FileInputStream(fullFileName);
        OutputStream out = response.getOutputStream();

        //写文件
        int b;
        while ((b = in.read()) != -1) {
            out.write(b);
        }

        in.close();
        out.close();
        System.out.println("sfsdf");
    }
}