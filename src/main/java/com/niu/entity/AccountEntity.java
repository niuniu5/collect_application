package com.niu.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@Entity
@Table(name = "acl_account")
public class AccountEntity implements Serializable {

    /**
     * 雪花ID
     */
    @Id
    private Long id;

    /**用户名**/
    @Column(length = 32)
    private String username;

    /**密码**/
    @Column(length = 64)
    private String password;

    /**盐值**/
    @Column(length = 64)
    private String salt;

    /**注册时间**/
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime created;

    /**注册时间**/
    @Column(name ="last_login" )
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime lastLogin;

    /**是否锁定，锁定后不能登录**/
    private Boolean locked;
}
