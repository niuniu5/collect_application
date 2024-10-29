package com.niu.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 登录账号
 * @author 牛亚平
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Account implements Serializable {

    /** 雪花ID **/
    private Long id;

    /**用户名**/
    private String username;

    /**密码**/
    private String password;

    /**盐值**/
    private String salt;

    /**注册时间**/
    private LocalDateTime created;

    /**最后时间**/
    private LocalDateTime lastLogin;

}
