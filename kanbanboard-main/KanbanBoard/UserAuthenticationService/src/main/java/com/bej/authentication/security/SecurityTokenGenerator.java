package com.bej.authentication.security;



import com.bej.authentication.domain.User;

import java.util.Map;


public interface SecurityTokenGenerator {
    String createToken(User user);
}
