package com.nowcoder.community.controller;

import com.nowcoder.community.service.AlphaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@Controller
@RequestMapping("/alpha")
public class HelloController {
    @Autowired
    private AlphaService alphaService;

    @RequestMapping("/data")
    @ResponseBody
    public String Data(){
        return alphaService.find();
    }

    @RequestMapping("/http")
    public void Http(HttpServletRequest request, HttpServletResponse response){
        System.out.println(request.getMethod());
        System.out.println(request.getServletPath());
        Enumeration<String> enumeration = request.getHeaderNames();
        while(enumeration.hasMoreElements()) {
            String name = enumeration.nextElement();
            String value = request.getHeader(name);
            System.out.println(name + ": " + value);
        }
        System.out.println(request.getParameter("code"));

        // 返回相应数据类型
        response.setContentType("text/html;charset=utf-8");
        try(PrintWriter writer = response.getWriter();) {
            writer.write("<h1>牛客网<h1>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Get
    @RequestMapping(path = "/students",method = RequestMethod.GET)
    @ResponseBody
    public String getStudent(
            //required 可以不穿 不穿默认为1
            @RequestParam(name = "current",required = false,defaultValue = "1") int current,
            @RequestParam(name = "limit",required = false,defaultValue = "10") int limit){
        System.out.println(current + " " + limit);
        return "some students";
    }

    // student/123
    @RequestMapping(path = "/student/{id}",method = RequestMethod.GET)
    @ResponseBody
    public String getStudent(@PathVariable("id") int id){
        System.out.println(id);
        return "a student";
    }

    //post
    @RequestMapping(path = "/student", method = RequestMethod.POST)
    @ResponseBody
    public  String saveStudent(String name,int age){
        System.out.println(name + " " + age);
        return "success";
    }

    // 相应Html数据
    @RequestMapping(path = "/teacher", method = RequestMethod.GET)
    public ModelAndView getTeacher(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("name","张三");
        modelAndView.addObject("age",30);
        modelAndView.setViewName("/demo/view");

        return modelAndView;
    }

    @RequestMapping(path = "/school", method = RequestMethod.GET)
    public String getSchool(Model model){
        model.addAttribute("name","山动工商学院");
        model.addAttribute("age","80");
        return "/demo/view";
    }

    // 响应JSON数据（异步请求）
    // Java对象 -> JSON字符串 ->　JS对象
    @RequestMapping(path = "/emp", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getEmp(){
        Map<String, Object> emp = new HashMap<>();
        emp.put("name", "张三");
        emp.put("age", 19);
        emp.put("salary", 8000.00);
        return emp;
    }

    @RequestMapping(path = "/emps", method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String, Object>> getEmps(){
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> emp = new HashMap<>();
        emp.put("name", "张三");
        emp.put("age", 19);
        emp.put("salary", 8000.00);
        list.add(emp);

        emp.put("name", "李四");
        emp.put("age", 28);
        emp.put("salary", 10000.00);
        list.add(emp);

        emp.put("name", "王五");
        emp.put("age", 40);
        emp.put("salary", 20000.00);
        list.add(emp);

        return list;
    }
}
