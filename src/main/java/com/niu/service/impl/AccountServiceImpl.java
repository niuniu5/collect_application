package com.niu.service.impl;

import com.niu.entity.AccountEntity;
import com.niu.model.Account;
import com.niu.repository.AccountRepository;
import com.niu.service.AccountService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountRepository accountRepository;


    @Override
    public Account findByUsername(String username) {
        AccountEntity accountEntity =  accountRepository.findByUsername(username);

        return Account.builder()
                .id(accountEntity.getId())
                .username(accountEntity.getUsername())
                .password(accountEntity.getPassword())
                .salt(accountEntity.getSalt())
                .created(accountEntity.getCreated())
                .lastLogin(accountEntity.getLastLogin())
                .build();
    }

    @Override
    public void saveAccount(Account account) {
        AccountEntity accountEntity =  new AccountEntity();
        accountEntity.setId(account.getId());
        accountEntity.setUsername(account.getUsername());
        accountEntity.setPassword(account.getPassword());
        accountEntity.setSalt(account.getSalt());
        accountEntity.setCreated(account.getCreated());
        accountRepository.save(accountEntity);
    }


    @Override
    public void updateLastTime(Long id, LocalDateTime lastLoginTime) {
        accountRepository.updateLastTime(id, lastLoginTime);
    }

}
