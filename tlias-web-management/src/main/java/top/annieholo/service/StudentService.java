package top.annieholo.service;

import top.annieholo.pojo.PageResult;
import top.annieholo.pojo.Student;
import top.annieholo.pojo.StudentQueryParam;

import java.util.List;

public interface StudentService {

    PageResult<Student> page(StudentQueryParam studentQueryParam);

    void save(Student student);

    void delete(List<Integer> ids);

    Student getById(Integer id);

    void update(Student student);

    void violation(Integer id, Integer score);
}
