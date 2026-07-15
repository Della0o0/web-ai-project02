package top.annieholo.pojo;

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
public class ClazzQueryDTO {
    /** 页码 */
    private Integer page = 1;

    /** 每页条数 */
    private Integer pageSize = 10;

    /** 班级名称 */
    private String name;

    /** 开课时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate begin;

    /** 结课时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate end;
}
