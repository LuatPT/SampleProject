package me.springsecurity;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import me.springsecurity.jwt.JwtTokenProvider;
import me.springsecurity.payload.LoginRequest;
import me.springsecurity.payload.LoginResponse;
import me.springsecurity.payload.RandomStuff;
import me.springsecurity.user.CustomUserDetail;

@RestController
@RequestMapping("/api")
public class WebController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@PostMapping("/login")
	public LoginResponse authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		// Xac thuc voi username password
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		// Neu ko xay ra exception thi la login ok
		SecurityContextHolder.getContext().setAuthentication(authentication);

		// Tao jwt
		String jwt = jwtTokenProvider.generateToken((CustomUserDetail) authentication.getPrincipal());

		return new LoginResponse(jwt);
	}

	@GetMapping("/random")
	public RandomStuff randomStuff() {
		return new RandomStuff("JWT Hợp lệ mới có thể thấy được message này");
	}
	
	
	//=============================basic==========================//

	@GetMapping(value = { "/home" })
	public String homepage() {
		return "home"; // Trả về home.html
	}

	@GetMapping("/hello")
	public String hello() {
		return "hello"; // Trả về hello.html
	}

}
