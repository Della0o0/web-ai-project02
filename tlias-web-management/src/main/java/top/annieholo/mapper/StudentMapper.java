package top.annieholo.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.annieholo.pojo.Student;
import top.annieholo.pojo.StudentQueryParam;

import java.util.List;

@Mapper
public interface StudentMapper {

    List<Student> list(StudentQueryParam studentQueryParam);

    void insert(Student student);

    void delete(List<Integer> ids);

    Student getById(Integer id);

    void update(Student student);

    void updateViolation(Integer id, Integer score);
}
