package top.annieholo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import top.annieholo.pojo.*;

import java.util.List;
import java.util.Map;

/**
 * 员工信息
 */
@Mapper
public interface EmpMapper {

    /**
     * 查询所有员工
     * @return
     */
    // @Select("select * from emp order by update_time desc")
    // List<Emp> findAll();

    /**
     * 查询员工总数量
     * @return
     */
    // @Select("select count(*) from emp e left join dept d on e.dept_id = d.id")
    // public Long count();

    /**
     * 查询分页员工list
     * @param start
     * @param pageSize
     * @return
     */
    // @Select("select e.*, d.name deptName from emp e left join dept d on e.dept_id = d.id " +
    //         "order by e.update_time desc limit #{start},#{pageSize}")
    // public List<Emp> list(Integer start, Integer pageSize);

    /**
     * PageHelper分页查询
     */
    // @Select("select e.*, d.name deptName from emp e left join dept d on e.dept_id = d.id order by e.update_time desc")
    // public List<Emp> list(
    //         String name, // 姓名
    //         Integer gender, // 性别
    //         LocalDate begin, // 入职日期起
    //         LocalDate end // 入职日期止
    // );

    List<Emp> list(EmpQueryParam empQueryParam);

    /**
     * 新增员工基本信息
     * @param emp
     */
    // @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Emp emp);

    /**
     * 删除员工
     * @param ids
     */
    void delete(List<Integer> ids);

    void realDelete(List<Integer> ids);

    void deleteExpByEmpId(List<Integer> ids);

    Emp getById(Integer id);

    List<EmpExpr> getExpById(Integer emp_id);

    void update(Emp emp);

    List<JobCount> countEmpJobData();

    List<BarItem> countEmpJobDataBar();

    List<GenderCount> countEmpGenderData();

    @Select("select * from emp where username = #{username} and password = #{password}")
    Emp getUsernameAndPassword(Emp emp);
}
