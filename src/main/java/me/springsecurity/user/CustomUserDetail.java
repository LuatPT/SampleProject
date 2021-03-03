package me.springsecurity.user;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomUserDetail implements UserDetails {

	User user;
	
	// Dùng anonation nên ko cần khai báo này
//	public CustomUserDetail(User user) {
//		this.user = user;
//	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		// Default role is user
		return Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
	}

	public String getPassword() {
		return user.getPassword();
	}

	public String getUsername() {
		return user.getUsername();
	}

	public boolean isAccountNonExpired() {
		return true;
	}

	public boolean isAccountNonLocked() {
		return true;
	}

	public boolean isCredentialsNonExpired() {
		return true;
	}

	public boolean isEnabled() {
		return true;
	}
	
}
