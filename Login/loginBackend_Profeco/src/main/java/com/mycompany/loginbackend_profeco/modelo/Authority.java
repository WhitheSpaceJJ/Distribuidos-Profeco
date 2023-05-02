package com.mycompany.loginbackend_profeco.modelo;

import org.springframework.security.core.GrantedAuthority;

/**
 *
 * @author Jarol
 */

public class Authority implements GrantedAuthority {

    private String authority;

    public Authority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }

}
