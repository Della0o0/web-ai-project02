package top.annieholo.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.annieholo.pojo.Clazz;
import top.annieholo.pojo.ClazzQueryDTO;

import java.util.List;

@Mapper
public interface ClazzMapper {

    List<Clazz> list(ClazzQueryDTO clazzQueryDTO);

    void insert(Clazz clazz);

    Clazz getById(Integer id);

    void update(Clazz clazz);

    void deleteById(Integer id);
}
