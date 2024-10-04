package com.example.demo.configs;

import java.security.Principal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

import com.example.demo.JWTValidator;
import com.example.demo.models.User;

@Component
public class WebSocketJWTValidator {

    @Autowired
    private JWTValidator jwtValidator;

    public Principal setPrincipal(ServerHttpRequest request){
        String url = request.getURI().toString();
        Pattern p = Pattern.compile("[&?]token=([^&\\r\\n]*)");
        Matcher matcher = p.matcher(url);
        if (matcher.find()) {
            User user = new User(13L, "asd",
                    "asd", "12345", "dolbaeb@example.com",
                    "dolbaeb");
            List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(user.getRole());
            return new UsernamePasswordAuthenticationToken(user.getName(), null, grantedAuthorities);
        };
        return null;
    }

}
