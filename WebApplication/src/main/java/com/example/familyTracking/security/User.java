package com.example.familyTracking.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Principal;
import java.util.LinkedList;
import java.util.List;

import lombok.Data;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User implements UserDetails {
    @Transient //временно
    private List<Role> authorities;
    private String strRole;
    private String password;
    //временное поле возможно постоянное
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer Id;

    private String username;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;

    public void addAuthority(Role userRole){
        if (this.authorities != null){
            this.authorities.add(userRole);
             this.strRole+=" "+userRole.toString();
        }
        else{
            this.authorities = new LinkedList<Role>();
            this.authorities.add(userRole);
            this.strRole="";
            this.strRole=userRole.toString();
        }
    }
    public void parseAuthority(){
        this.authorities = new LinkedList<>() ;
        String[] arrStr= this.strRole.split(" ");
        for (String s : arrStr) {
            this.authorities.add(Role.valueOf(s));
        }
    }

//    public User getCurrentUser(Principal principal){
//        return ((User )SecurityContextHolder.getContext().getAuthentication().getPrincipal());
//    }

    public static User getCurrentUser(){
        User user = null;
        try{
            org.springframework.security.core.Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            user = (User) auth.getPrincipal();
        }
        catch(ClassCastException ex){

            System.out.println("Trying to get user credentials for non authorized user");
        }
        return user;
    }


}
