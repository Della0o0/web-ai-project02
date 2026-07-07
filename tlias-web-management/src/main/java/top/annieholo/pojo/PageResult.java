package top.annieholo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResult<T> {

    // 总数
    private Long total;
    // 列表
    private List<T> rows;


    // public static Result success(Object object, Long total){
    //     PageResult pageResult = new PageResult();
    //     pageResult.rows = object;
    //     pageResult.total = total;
    //     return Result.success(pageResult);
    // }
}
