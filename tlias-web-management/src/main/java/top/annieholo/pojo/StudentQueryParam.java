package top.annieholo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentQueryParam {
    /** 页码 */
    private Integer page = 1;

    /** 每页条数 */
    private Integer pageSize = 10;

    /** 姓名 */
    private String name;

    /** 最高学历 */
    private Integer degree;

    /** 所属班级 */
    private Integer clazzId;
}
