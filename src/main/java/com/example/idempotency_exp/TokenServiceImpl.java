package com.example.idempotency_exp;

import com.example.idempotency_exp.exception.CannotRemoveTokenException;
import com.example.idempotency_exp.exception.InvalidTokenException;
import com.example.idempotency_exp.exception.TokenNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.UUID;

@Service
public class TokenServiceImpl implements TokenService {

    final
    RedisUtil redisService;

    public TokenServiceImpl(RedisUtil redisService) {
        this.redisService = redisService;
    }

    @Override
    public String createToken(User user) {
        // it also takes the post methods request body
        // considers that request body as the key
        // based on that it generated the key
        //System.out.println(user.toString());
        long startTime = System.currentTimeMillis();

        String shaKey = "Service1_" + DigestUtils.sha256Hex(user.toString());

        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;

        System.out.println("Execution Time: " + executionTime + " milliseconds");

        System.out.println("Shakey " + shaKey);

        try {
            if (redisService.exists(shaKey)) return (String) redisService.get(shaKey);
            redisService.setKey(shaKey, shaKey, 1000L);
            return shaKey;
        } catch (Exception e) {

        }
        return null;
    }

    @Override
    public boolean checkToken(HttpServletRequest request) {
        String token = request.getHeader(RedisPrefixKey.TOKEN_NAME);
        if (StringUtils.isEmpty(token)) {
            throw new TokenNotFoundException("Idempotency-Key is not provided in request header");
        }
        if (!redisService.exists(token)) {
            throw new InvalidTokenException("Idempotency-Key is invalid, please create a new key");
        }
        boolean remove = redisService.remove(token);
        if (!remove) {
            throw new CannotRemoveTokenException("Error while deleting Idempotency-Key");
        }
        return true;
    }
}
