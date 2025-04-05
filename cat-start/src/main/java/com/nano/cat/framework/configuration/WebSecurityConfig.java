package com.nano.cat.framework.configuration;

import com.nano.cat.framework.configuration.JwtTokenFilterConfigure;
import com.nano.cat.framework.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Disable CSRF (cross site request forgery)
        http.csrf().disable();

        // No session will be created or used by spring security
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // Entry points
        http.authorizeRequests()
                .antMatchers("/user/register").permitAll()
                .antMatchers("/user/login").permitAll()
                .antMatchers("/api/v3/api-docs/swagger-config").permitAll()
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/v3/api-docs/**").permitAll()
                .antMatchers("/api/v3/api-docs/**").permitAll()
                .antMatchers("/api/doc.html").permitAll()
                .antMatchers("/home").permitAll()
                .antMatchers("/doc.html").permitAll()
                .antMatchers("/swagger-ui/**").permitAll()
                .antMatchers("/h2-console/**").permitAll()
                // Disallow everything else
                .anyRequest().authenticated();

        // If a user try to access a resource without having enough permissions
        http.exceptionHandling().accessDeniedPage("/login");

        // Apply JWT
        http.apply(new JwtTokenFilterConfigure(jwtTokenProvider));

        // Optional, if you want to test the API from a browser
        // http.httpBasic();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // Allow swagger to be accessed without authentication
        web.ignoring().antMatchers("/v2/api-docs")//
                .antMatchers("**/user/register")
                .antMatchers("/user/signup")
                .antMatchers("/swagger-resources/**")//
                .antMatchers("/api/v3/api-docs/swagger-config")//
                .antMatchers("/swagger-ui.html")//
                .antMatchers("/configuration/**")//
                .antMatchers("/webjars/**")//
                .antMatchers("/v3/api-docs/**")
                .antMatchers("/api/v3/api-docs/**")
                .antMatchers("/js/**")//
                .antMatchers("/css/**")//
                .antMatchers("/v2/api-docs")
                .antMatchers("/swagger-resources/**")
                .antMatchers("/swagger-ui/**")  // 添加这个
                .antMatchers("/swagger-ui.html")
                .antMatchers("/configuration/**")
                .antMatchers("/webjars/**")
                .antMatchers("/public")
                .antMatchers("/doc.html")
                .antMatchers("/v2/api-docs") // Swagger v2 API
                .antMatchers("/v3/api-docs/**") // OpenAPI v3 API
                .antMatchers("/swagger-resources/**") // Swagger 资源
                .antMatchers("/webjars/**") // Swagger UI 依赖的静态资源
                .antMatchers("/favicon.ico") // 图标
                // 其他静态资源
                .antMatchers("/css/**")
                .antMatchers("/js/**")
                .antMatchers("/h2-console/**")
                .and()
                .ignoring()
                .antMatchers("/h2-console/**/**");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
