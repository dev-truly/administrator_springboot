package com.web.relocation.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto implements UserDetails {
    @NotBlank
    private int userNo;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9_-]{6,30}$")
    private String userId;

    @JsonIgnore
    @NotBlank
    @Pattern(regexp="^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[*.!@$%^&(){}:<>,?/~_+-=|])[A-Za-z\\d*.!@$%^&(){}:<>,?/~_+-=|]{8,30}$")
    private String password;

    @JsonIgnore
    @NotBlank
    private String auth;

    @NotBlank
    private String menuCode;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<GrantedAuthority> authList = new ArrayList<>();
        authList.add(new SimpleGrantedAuthority(this.auth));
        return authList;
    }

    @Override
    public String getUsername() {
        return this.getUserId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
/*

    public UserEntity toEntity() {
        return UserEntity.builder()
                .userNo(userNo)
                .userId(userId)
                .password(password)
                .build();
    }
*/

}
