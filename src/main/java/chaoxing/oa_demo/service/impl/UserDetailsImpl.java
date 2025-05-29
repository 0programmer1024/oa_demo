package chaoxing.oa_demo.service.impl;

import chaoxing.oa_demo.entity.User;
import chaoxing.oa_demo.enums.UserType;
import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsImpl implements UserDetails{
 
    private User user;
 
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(
                StrUtil.format("ROLE_{}",Objects.requireNonNull(UserType.fromCode(user.getType())).toString())));
    }
 
    @Override
    public String getPassword() {
        return user.getPassword();
    }
 
    @Override
    public String getUsername() {
        return user.getUsername();
    }
 
    @Override
    public boolean isAccountNonExpired() {  // 检查账户是否 没过期。
        return true;
    }
 
    @Override
    public boolean isAccountNonLocked() {   // 检查账户是否 没有被锁定。
        return true;
    }
 
    @Override
    public boolean isCredentialsNonExpired() {  //检查凭据（密码）是否 没过期。
        return true;
    }
 
    @Override
    public boolean isEnabled() {    // 检查账户是否启用。
        return true;
    }
    
    public User getUser() {
        return user;
    }
}