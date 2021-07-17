package com.codeman.blog0703.domain;

import cn.hutool.core.collection.CollectionUtil;
import com.codeman.blog0703.entity.User;
import com.codeman.blog0703.enums.PasswordEncoderTypeEnum;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

import static com.codeman.blog0703.constants.GlobalConstants.STATUS_YES;


/**
 * 登录用户信息
 */
@Data
@NoArgsConstructor
public class OAuthUserDetails implements UserDetails {

    private Long id;

    private String username;

    private String password;

    private Boolean enabled;

    private String clientId;

    private Collection<SimpleGrantedAuthority> authorities;

    public OAuthUserDetails(User user) {
        this.setId(Long.valueOf(user.getUserId()));
        this.setUsername(user.getName());
        this.setPassword(PasswordEncoderTypeEnum.BCRYPT.getPrefix() + user.getPassword());
        this.setEnabled(user.getUseabled());
        if (CollectionUtil.isNotEmpty(user.getRoles())) {
            authorities = new ArrayList<>();
            user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getRoleCd())));
        }
    }

    /*public OAuthUserDetails(AuthMemberDTO member) {
        this.setId(member.getId());
        this.setUsername(member.getUsername());
        this.setPassword(PasswordEncoderTypeEnum.BCRYPT.getPrefix() + member.getPassword());
        this.setEnabled(STATUS_YES.equals(member.getStatus()));
    }*/


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
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
        return this.enabled;
    }
}
