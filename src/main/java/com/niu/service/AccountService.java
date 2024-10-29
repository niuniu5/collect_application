package com.niu.service;

import com.niu.model.Account;

import java.time.LocalDateTime;

public interface AccountService {
    Account findByUsername(String username);
    void saveAccount(Account account);
    void updateLastTime(Long id, LocalDateTime lastLoginTime);
}
