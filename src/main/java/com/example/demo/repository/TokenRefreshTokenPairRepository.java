package com.example.demo.repository;

import com.example.demo.entity.TokenRefreshTokenPairEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TokenRefreshTokenPairRepository extends MongoRepository<TokenRefreshTokenPairEntity, String> {
    TokenRefreshTokenPairEntity findTokenRefreshTokenPairByJti(String jti);
}
