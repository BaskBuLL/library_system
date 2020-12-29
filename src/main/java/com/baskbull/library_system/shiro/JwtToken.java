package com.baskbull.library_system.shiro;


import org.apache.shiro.authc.AuthenticationToken;

/**
 *
 * @author baskbull
 */
public class JwtToken implements AuthenticationToken {

    private String token;

    public JwtToken(String jwt){
        this.token = jwt;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
