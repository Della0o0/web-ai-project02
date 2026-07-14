package top.annieholo.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.annieholo.pojo.EmpExpr;

import java.util.List;

/**
 * 员工工作经历信息
 */
@Mapper
public interface EmpExprMapper {

    void deleteByIds(List<Integer> deleteIds);

    void insertBatch(List<EmpExpr> exprList);

    List<Integer> listIdsByEmpId(Integer empId);

    void insert(EmpExpr empExpr);

    void update(EmpExpr empExpr);
}
