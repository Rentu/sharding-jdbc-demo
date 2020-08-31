package com.example.demo.controller;

import com.example.demo.mapper.StudentMapper;
import com.example.demo.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    StudentMapper studentMapper;

    @RequestMapping("/insert")
    public String insert(){

        for(int i = 1; i <= 100; i++){
            Student student = new Student();
            student.setName("占三");
            student.setUserId(i);
            studentMapper.insert(student);
        }
        return "success";
    }
}
