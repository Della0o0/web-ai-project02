package top.annieholo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginInfo {
    private Integer id;
    private String username;
    private String name; //姓名
    private String token; //令牌
}
