package com.baizhi.controller;


import com.alibaba.druid.support.json.JSONUtils;
import com.baizhi.entity.User;
import com.baizhi.mapper.UserMapper;
import com.baizhi.service.UserService;
import io.goeasy.GoEasy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    UserMapper userMapper;


    //分页查询d
    @RequestMapping("findByPage")
    public Map<String, Object> findByPage(Integer page, Integer rows) {
        Map<String, Object> byPage1 = userService.findByPage(page, rows);
        return byPage1;
    }


    @RequestMapping("edit")
    public String edit(User user, String oper, String[] id){
        if (oper.equals("add")){
            String s = UUID.randomUUID().toString();
            user.setId(s);
            user.setCreate_Date(new Date());
            userService.insert(user);
            return s;
        } else if (oper.equals("del")){
            userService.delete(id);
        }else {
            if (user.getImg()==""){
                user.setImg(null);
                userService.update(user);
            }else {
                userService.update(user);
                return user.getId();
            }

        }
        return null;
    }

    @RequestMapping("UserTime")
    public Map UserTime(){
        HashMap hashMap = new HashMap();
        ArrayList manList = new ArrayList();
        manList.add(userMapper.queryUserByTime("1",1));
        manList.add(userMapper.queryUserByTime("1",7));
        manList.add(userMapper.queryUserByTime("1",30));
        manList.add(userMapper.queryUserByTime("1",365));
        System.out.println(manList);
        ArrayList womenList = new ArrayList();
        womenList.add(userMapper.queryUserByTime("0",1));
        womenList.add(userMapper.queryUserByTime("0",7));
        womenList.add(userMapper.queryUserByTime("0",30));
        womenList.add(userMapper.queryUserByTime("0",365));
        hashMap.put("man",manList);
        hashMap.put("women",womenList);
        return hashMap;
    }

    @RequestMapping("getCountByprovince")
    public List<User> getCountByprovince() {
        List<User> countByprovince = userService.getCountByprovince();
        return countByprovince;
    }


    @RequestMapping("upload")
    public void upload(MultipartFile img, String userId, HttpSession session){
        //获取图片存储路径
        String realPath = session.getServletContext().getRealPath("/img");
        //判断文件是否存在
        File file = new File(realPath);
        if (!file.exists()){
            file.mkdirs();
        }

        String originalFilename = img.getOriginalFilename();
        String newFileName=new Date().getTime() +"_"+originalFilename;
        try {
            img.transferTo(new File(realPath,newFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
         User user= new User();
        user.setId(userId);
        user.setImg(newFileName);
        userService.update(user);
    }

}
