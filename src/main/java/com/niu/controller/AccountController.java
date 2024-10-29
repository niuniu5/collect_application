package com.niu.controller;

import com.niu.common.AccountUtil;
import com.niu.common.SnowflakeIdWorker;
import com.niu.model.Account;
import com.niu.service.AccountService;
import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@RestController
public class AccountController {

    @Resource
    private AccountService accountService;

    /**注册 **/
    @PostMapping("/user/register")
    public ResponseEntity<String> register(@RequestBody Account account){
        //盐值加密
        String username = account.getUsername();
        String password = account.getPassword();
        String salt = AccountUtil.generateSalt();
        String hashPassword = AccountUtil.hashPassword(password, salt);
        Long accountId = SnowflakeIdWorker.newAccountId();
        account.setUsername(username);
        account.setPassword(hashPassword);
        account.setId(accountId);
        account.setSalt(salt);
        account.setCreated(LocalDateTime.now());
        accountService.saveAccount(account);
        return new ResponseEntity<>("Registration successful", HttpStatus.CREATED);

    }

    /**登录*/
    @PostMapping("/user/login")
    public boolean login(@RequestBody Account account) {
        boolean islogin = false;
        try {
            Account foundUser = accountService.findByUsername(account.getUsername());
            if (foundUser == null) {
                return islogin;
            }
            islogin = AccountUtil.checkPassword(account.getPassword(),foundUser.getSalt(),foundUser.getPassword());

            // 转换为特定时区的时间
            ZoneId zoneId = ZoneId.of("Asia/Shanghai");
            ZonedDateTime zonedDateTime = LocalDateTime.now().atZone(zoneId);
            foundUser.setLastLogin(zonedDateTime.toLocalDateTime());
            // System.out.println(foundUser.getLastLogin());
            accountService.updateLastTime(foundUser.getId(),foundUser.getLastLogin());
        }catch (Exception e) {
            e.printStackTrace();
        }
        return islogin;
    }


    /**
     * 忘记密码
     */
    @PostMapping("/account/retrievePassword")
    public ResponseEntity<String> retrievePassword(@RequestBody Account account){
        return new ResponseEntity<>("Retrieve password successful", HttpStatus.OK);
    }




}
