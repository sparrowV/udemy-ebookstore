package ge.odvali.ebookstore.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import ge.odvali.ebookstore.security.jwt.JwtAuthenticationFilter;
import ge.odvali.ebookstore.security.jwt.JwtAuthorizationFilter;
import ge.odvali.ebookstore.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@EnableWebSecurity
@Order(1)
public class JwtSecurityConfig extends WebSecurityConfigurerAdapter {
    @Value("${jwt.username}")
    private String jwtUsername;
    @Value("${jwt.password}")
    private String jwtPassword;
    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private ObjectMapper objectMapper;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().
                withUser(jwtUsername).password("{noop}" + jwtPassword).roles("");

    }


    @Bean
    public FilterRegistrationBean<JwtAuthenticationFilter> jwtAuthFilter() throws Exception {
        FilterRegistrationBean<JwtAuthenticationFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new JwtAuthenticationFilter(authenticationManager(), objectMapper, jwtUtils));
        registrationBean.addUrlPatterns("*");
        return registrationBean;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().
                sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().
                antMatcher("/api/**").
                addFilterAfter(new JwtAuthorizationFilter(jwtUtils), JwtAuthenticationFilter.class).
                authorizeRequests().anyRequest().permitAll();


    }
}
