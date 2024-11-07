package com.example.vuvantrung.repository.blacklistedToken;

import com.example.vuvantrung.entity.BlacklistedToken;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface BlacklistedTokenRepository extends JpaRepository<BlacklistedToken, Long>, BlacklistedTokenRepositoryCustom {
    Optional<BlacklistedToken> findByToken(String token);
    int countTokenRevokedByToken(String token);

    @Modifying
    @Transactional
    @Query(value = "delete from blacklisted_token where blacklisted_at <= NOW() - INTERVAL 1 HOUR;", nativeQuery = true)
    void deleteExpiredTokens();
}
