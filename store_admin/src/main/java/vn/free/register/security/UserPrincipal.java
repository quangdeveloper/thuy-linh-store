package vn.free.register.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import vn.free.register.entity.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;


public class UserPrincipal implements UserDetails {

    private Long id;

    private String username;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    private Integer active;

    private String email;

    private String phone;

    private Long groupRoleId;

    private String fullName;

    public UserPrincipal() {
    }

    public UserPrincipal(final User user) {

        List<GrantedAuthority> authorities = new ArrayList<>();

        this.id = user.getId();

        this.username = user.getUsername();

        this.password = user.getPassword();

        this.authorities = authorities;

        this.active = user.getStatus();

        this.fullName = user.getFullName();

        this.email = user.getEmail();

        this.phone = user.getPhone();

        this.groupRoleId = user.getGroupRoleId();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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

    @Override
    public boolean equals(Object obj) {

        if (obj == this) return true;

        if (obj == null || obj.getClass() != getClass()) return false;

        UserPrincipal userPrincipal = (UserPrincipal) obj;

        return Objects.equals(id, userPrincipal.id);
    }

    @Override
    public int hashCode() {

        return Objects.hashCode(id);
    }

    public Long getId() {
        return id;
    }

    public Integer getActive() {
        return active;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public Long getGroupRoleId() {
        return groupRoleId;
    }

    public String getFullName() {
        return fullName;
    }
}


