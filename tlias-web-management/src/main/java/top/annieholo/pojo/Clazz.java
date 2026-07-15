package top.annieholo.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * JavaDoc 注释的主要好处是：
 * IDE 鼠标悬停字段时可以显示说明。
 * 能被 JavaDoc 等文档工具读取。
 * 一些接口文档插件配置后，可以读取 JavaDoc 作为字段描述。
 * 多行描述和格式化内容更清晰。
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Clazz {
    /** ID，主键 */
    private Integer id;

    /** 班级名称 */
    private String name;

    /** 班级教室 */
    private String room;

    /** 开课时间 */
    private LocalDate beginDate;

    /** 结课时间 */
    private LocalDate endDate;

    /** 班主任ID，关联员工表 */
    private Integer masterId;

    /**
     * 学科：
     * 1-Java，2-前端，3-大数据，
     * 4-Python，5-Go，6-嵌入式
     */
    private Integer subject;

    /** 创建时间 */
    private LocalDateTime createTime;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    /** 班主任姓名 */
    private String masterName;

    /** 结课状态 未开班、已结课、在读中 */
    private String status;

}
