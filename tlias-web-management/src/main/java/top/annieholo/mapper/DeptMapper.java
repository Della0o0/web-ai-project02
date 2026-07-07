package top.annieholo.mapper;

import org.apache.ibatis.annotations.*;
import top.annieholo.pojo.Dept;

import java.util.List;

@Mapper
public interface DeptMapper {

    /**
     * 查询所有部门
     * @return
     */
    @Select("select id, name, create_time, update_time from dept order by update_time desc;")
    List<Dept> findAll();

    /**
     * 根据id删除部门
     * @param id
     */
    @Delete("delete from dept where id = #{id}")
    void deleteById(Integer id);

    /**
     * 新增部门
     * @param dept
     */
    @Insert("INSERT INTO dept(name, create_time, update_time) values(#{name}, #{createTime}, #{updateTime})")
    void insert(Dept dept);

    /**
     * 根据id获取部门
     * @param id
     * @return
     */
    @Select("select id, name, create_time, update_time from dept where id = #{id}")
    Dept getById(Integer id);

    /**
     * 修改部门数据
     * @param dept
     */
    @Update("UPDATE dept SET name=#{name},update_time=#{updateTime} where id = #{id}")
    void update(Dept dept);
}
