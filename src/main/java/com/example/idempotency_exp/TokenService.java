package com.example.idempotency_exp;

import jakarta.servlet.http.HttpServletRequest;

public interface TokenService {

    public String createToken(User user);

    public boolean checkToken(HttpServletRequest request) throws Exception;


}
