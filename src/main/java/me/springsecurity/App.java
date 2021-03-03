package me.springsecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.password.PasswordEncoder;

import me.springsecurity.user.User;
import me.springsecurity.user.UserRepository;

@SpringBootApplication
@EnableAutoConfiguration(exclude=ErrorMvcAutoConfiguration.class)
@ComponentScan({"me.springsecurity"})
public class App extends SpringBootServletInitializer implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    
    public void run(String... args) throws Exception {
        // Khi chương trình chạy
        // Insert vào csdl một user.
    	// Nhưng ko lưu vào db
        User user = new User();
        user.setUsername("loda1");
        user.setPassword(passwordEncoder.encode("loda1"));
        userRepository.save(user);
        System.out.println(user);
    }
}