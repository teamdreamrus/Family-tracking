package com.example.familyTracking.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.LinkedList;
import java.util.List;

import lombok.Data;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {
    private List<Role> authorities;
    private String password;
    private String username;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;

    public void addAuthority(Role userRole){
        if (this.authorities != null){
            this.authorities.add(userRole);
        }
        else{
            this.authorities = new LinkedList<Role>();
            this.authorities.add(userRole);
        }
    }
}
