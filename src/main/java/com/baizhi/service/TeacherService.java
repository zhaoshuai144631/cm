package com.baizhi.service;

import com.baizhi.entity.Teacher;
import org.apache.catalina.LifecycleState;

import java.util.List;
import java.util.Map;

public interface TeacherService {
    Map<String,Object> selectAll(Integer page,Integer rows);
    String add(Teacher teacher);
    void delete(Teacher teacher);
    void update(Teacher teacher);
    List<Teacher> queryAll();
}
