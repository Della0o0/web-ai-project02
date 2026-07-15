package top.annieholo.service;

import top.annieholo.pojo.Clazz;
import top.annieholo.pojo.ClazzQueryDTO;
import top.annieholo.pojo.PageResult;

public interface ClazzService {

    PageResult<Clazz> getList(ClazzQueryDTO clazzQueryDTO);

    void add(Clazz clazz);

    Clazz getById(Integer id);

    void update(Clazz clazz);

    void deleteById(Integer id);
}
