package com.niu.repository;
import com.niu.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
    AccountEntity findByUsername(String username);

    @Modifying
    @Query("UPDATE AccountEntity a SET a.lastLogin =?2 WHERE a.id =?1")
    void updateLastTime(Long id, LocalDateTime lastTime);
}
