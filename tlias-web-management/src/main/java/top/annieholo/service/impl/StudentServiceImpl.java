package top.annieholo.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.annieholo.mapper.StudentMapper;
import top.annieholo.pojo.PageResult;
import top.annieholo.pojo.Student;
import top.annieholo.pojo.StudentQueryParam;
import top.annieholo.service.StudentService;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public PageResult<Student> page(StudentQueryParam studentQueryParam) {
        PageHelper.startPage(studentQueryParam.getPage(), studentQueryParam.getPageSize());
        List<Student> list = studentMapper.list(studentQueryParam);
        PageInfo<Student> pageInfo = new PageInfo<>(list);
        return new PageResult<>(pageInfo.getTotal(), pageInfo.getList());
    }

    @Override
    public void save(Student student) {
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());
        student.setViolationCount(0);
        student.setViolationScore(0);
        studentMapper.insert(student);
    }

    @Override
    public void delete(List<Integer> ids) {
        studentMapper.delete(ids);
    }

    @Override
    public Student getById(Integer id) {
        return studentMapper.getById(id);
    }

    @Override
    public void update(Student student) {
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.update(student);
    }

    /**
     * 违纪扣分
     * @param id
     * @param score
     */
    @Override
    public void violation(Integer id, Integer score) {
        // 不是最主流、也不是最简洁。但主流项目里更推荐“数据库直接累加”，更简洁，也更安全。
        // // 根据id查当前学生违纪次数和违纪分数
        // Student student = studentMapper.getById(id);
        // // 累加
        // student.setViolationScore(student.getViolationScore()+score);
        // student.setViolationCount(student.getViolationCount()+1);
        // // update
        // studentMapper.update(student);
        studentMapper.updateViolation(id, score);
    }
}
