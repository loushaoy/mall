package com.loushy.mall.controller;



import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.loushy.mall.action.MailUtil;
import com.loushy.mall.action.MyThread;
import com.loushy.mall.model.User;
import com.loushy.mall.service.FileService;
import com.loushy.mall.service.UserService;
import com.mysql.jdbc.log.LogUtils;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/login")
public class LoginController {
    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Resource
    private UserService userService;

    @Resource
    private FileService fileService;

    @RequestMapping("/toLogin")
    @ResponseBody
    public JSONArray toLogin(){
        List<User> list = userService.selectUser();
        System.out.println(list);
        JSONArray  jo =  JSONArray.parseArray(JSON.toJSONString(list));
        return jo;
    }

    @RequestMapping("/toIndex")
    public ModelAndView toIndex(HttpServletRequest req){
        int codes = (int) (Math.random()*1000);
        String code = String.valueOf(codes);
        Thread mailUtil = new Thread(new MailUtil("626025932@QQ.com",code));
        mailUtil.run();
        return new ModelAndView("login");
    }

    @RequestMapping("/toText")
    public void toText(){
        List<String> list = new ArrayList<String>();
       for (int i=0;i<1000;i++){
           list.add(String.valueOf(i));
       }
        MyThread my0 = new MyThread(list,0);
        MyThread my1 = new MyThread(list,1);
        MyThread my2 = new MyThread(list,2);
        MyThread my3 = new MyThread(list,3);

        Thread t0 = new Thread(my0,"线程1");
        Thread t1 = new Thread(my1,"线程2");
        Thread t2 = new Thread(my2,"线程3");
        Thread t3 = new Thread(my3,"线程4");

        t0.start();
        t1.start();
        t2.start();
        t3.start();
    }

    @RequestMapping("/toAddFile")
    public ModelAndView toAddFile(){
        ModelAndView mv = new ModelAndView("file");
        return mv;
    }

    //addFile
    @RequestMapping(value="/addFile",method=RequestMethod.POST)
    @ResponseBody
    public String addFile(MultipartFile file ,HttpServletRequest request) {
        if(!file.isEmpty()) {
            //上传文件路径
            String url = "G:\\IdeaProjects\\mallPdf\\";
            logger.info(url);
            String path = url;
            //上传文件名
            String filename = file.getOriginalFilename();
            File filepath = new File(path,filename);
            //判断路径是否存在，如果不存在就创建一个
            if (!filepath.getParentFile().exists()) {
                filepath.getParentFile().mkdirs();
            }
            //将上传文件保存到一个目标文件当中
            try{
                file.transferTo(new File(path + filename));
                com.loushy.mall.model.File f = new com.loushy.mall.model.File();
                f.setName(filename);
                f.setUrl(path + filename);
                System.out.println(f.getUrl());
                fileService.addFile(f);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
            return "success";
        } else {
            return "error";
        }
    }

    @RequestMapping("/fileList")
    public ModelAndView fileList(){
        //MD5加密的
        String str = DigestUtils.md5DigestAsHex("Loushao1216".getBytes(Charset.forName("UTF-8")));
        System.out.println(str);
        ModelAndView mv = new ModelAndView("fileList");
        mv.addObject("list",fileService.selectFile());
        return mv;
    }
    @RequestMapping("/showFile")
    public ResponseEntity<byte[]> showFile(Long id) throws IOException{
        com.loushy.mall.model.File f = fileService.selectFileById(id);
        File file = null;
        if (f!=null){
            file = new File(f.getUrl());
            HttpHeaders httpHeaders = new HttpHeaders();
            String name = new String(f.getName().getBytes("UTF-8"),"iso-8859-1");
            httpHeaders.setContentDispositionFormData("attachment",name);
            httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            return new ResponseEntity<>(FileUtils.readFileToByteArray(file),httpHeaders,HttpStatus.CREATED);
        }
        return null;
    }

    @RequestMapping("/toTestThread")
    public void testThread(){

        List<Integer> list = new ArrayList<>();
        for (int i=0;i<150;i++){
            list.add(i);
        }
        Long tims = new Date().getTime();
        logger.info("开始遍历集合，大小为{}", list.size());
        for (Integer i:list) {
            userService.testThread(i);
        }
    }
}
