package com.controller;

import com.pojo.User;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @ClassName: ResulfController
 * @Description：TODO
 * @author: Administrator
 * @Date 2021/4/1 11:44
 * @Version 1.0
 **/
@Controller
public class ResulfController {

    @RequestMapping(value = {"/resulf/add/{a}/{b}"},method = RequestMethod.GET)
    public String resulfGet(@PathVariable int a, @PathVariable String b , Model model){
        String ret = a +b;
        model.addAttribute("msg","result。。" + ret);
        return "resulfTest";
    }


    @RequestMapping(value = "/toConver")
    public String toConverTest(User user,Model model){
        model.addAttribute("userDate",user.getBirthday());
        System.out.println(user);
        return "success";
    }


    @RequestMapping(value = "/ajax")
    public void ajax(@RequestBody String body){
        System.out.println(body);
    }


    @RequestMapping(value = "/ajaxEntiy")
    @ResponseBody
    public User ajaxEntiy(@RequestBody User user){
        user.setUsername("ajax return ");
        System.out.println(user);
        return user;
    }


    /**
     * @Description 传统文件上传
     * @Author Administrator
     * @param request :
     * @return java.lang.String
     * @throws
     * @Date 2021/4/12 11:00
     */
    @RequestMapping(value = "fileUpload")
    public String fileUpload(HttpServletRequest request){
        System.out.println("文件上传。。。。");

        String path = request.getSession().getServletContext().getRealPath("/uploads");
        File file = new File(path);
        if(!file.exists()){
            //创建文件夹
            file.mkdirs();
        }

        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
        ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
        try {
            List<FileItem> fileItems = servletFileUpload.parseRequest(request);
            if(fileItems!=null && fileItems.size()>0){
                int i=0;
                for (FileItem fileItem : fileItems) {
                    i++;
                    System.out.println("fileItem "+ i +"= " + fileItem);
                    if (fileItem.isFormField()) {
                        //普通表单
                    }else{
                        //获取上传文件的名称
                        String name = fileItem.getName();
                        String uuid = UUID.randomUUID().toString().replace("-", "");
                        name = uuid +"_" + name;
                        try {
                            fileItem.write(new File(path,name));
                            fileItem.delete();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }

        return "success";
    }



    /**
     * @Description springmvc文件上传
     * @Author Administrator
     * @param  :
     * @return java.lang.String
     * @throws
     * @Date 2021/4/12 11:00
     */
    @RequestMapping(value = "fileUpload2")
    public String fileUpload2(HttpServletRequest request, MultipartFile upload){
        System.out.println("springmvc文件上传 文件上传。。。。");
        String path = request.getSession().getServletContext().getRealPath("/uploads");
        File file = new File(path);
        if(!file.exists()){
            //创建文件夹
            file.mkdirs();
        }
        //获取上传文件的名称
        String filename = upload.getOriginalFilename();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        filename = uuid +"_" + filename;
        try {
            upload.transferTo(new File(path,filename));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "success";
    }


    /**
     * @Description 跨服务文件上传
     * @Author Administrator
     * @param  :
     * @return java.lang.String
     * @throws
     * @Date 2021/4/12 11:00
     */
    @RequestMapping(value = "fileUpload3")
    public String fileUpload3(MultipartFile upload,Model model){
        System.out.println("跨服务文件上传。。。。");
        String path ="http://localhost:8081/uploads/";
        //获取上传文件的名称
        String filename = upload.getOriginalFilename();

        filename = filename.replaceAll("%20","");
        filename = filename.replace(" ","");

        String uuid = UUID.randomUUID().toString().replace("-", "");
        filename = uuid +"_" + filename;
        try {
           //跨服务上传  创建客户端
            Client client = Client.create();
            //链接图片服务器
            WebResource webResource = client.resource(path + filename);
            //上传文件
            webResource.put(upload.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        String fullPath = path + filename;
        model.addAttribute("fullPath",fullPath);
        model.addAttribute("filename",filename);
        return "success";

    }

    /**
     * @Description 跨服务文件上传
     * @Author Administrator
     * @param  :
     * @return java.lang.String
     * @throws
     * @Date 2021/4/12 11:00
     */
    @RequestMapping(value = "fileUpload4")
    @ResponseBody
    public Map fileUpload4(MultipartFile upload){
        Map map = new HashMap();
        System.out.println("跨服务文件上传。。。。");
        String path ="http://localhost:8081/uploads/";
        //获取上传文件的名称
        String filename = upload.getOriginalFilename();

        filename = filename.replaceAll("%20","");
        filename = filename.replace(" ","");

        String uuid = UUID.randomUUID().toString().replace("-", "");
        filename = uuid +"_" + filename;
        try {
            //跨服务上传  创建客户端
            Client client = Client.create();
            //链接图片服务器
            WebResource webResource = client.resource(path + filename);
            //上传文件
            webResource.put(upload.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        String fullPath = path + filename;
        map.put("fullPath",fullPath);
        map.put("filename",filename);
        return map;

    }

}
