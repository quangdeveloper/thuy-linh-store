package vn.free.register.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import vn.free.register.jwt.JWTAuthenticationEntryPoint;
import vn.free.register.jwt.JWTAuthenticationFilter;
import vn.free.register.security.CustomUserDetailService;

/**
 * @author quangnv
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        prePostEnabled = true,
        jsr250Enabled = true
)
public class SecurityConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {


    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Autowired
    private JWTAuthenticationEntryPoint authenticationEntryPoint;

    @Bean
    public JWTAuthenticationFilter JwtAuthenticationFilter() {
        return new JWTAuthenticationFilter();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .cors()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint)
                .and()
                .authorizeRequests()
                .antMatchers(
                        "/login/**",
                        "/swagger-ui.html/**",
                        "/swagger-resources",
                        "/swagger-resources/**",
                        "/webjars/**",
                        "/Register/**"
                ).permitAll()
                .antMatchers("/**").authenticated();

        http.addFilterBefore(JwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    /**
     * Cấu hình cho swagger ui
     *
     * @param web
     * @throws Exception
     */

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
                "/v2/api-docs",
                "/configuration/ui",
                "swagger-resources/**",
                "configuration/security",
                "swagger-ui.html",
                "webjar/**"
        );
    }

    /**
     * Cấu hình truy xuất cheo từ domain khác sang
     * <p>
     * addmapping: những URL dc truy xuất
     * allowOrigin: đc phép truy xuất từ domain nào
     * allowMethod: Những thương thúc đc phép truy xuất
     * maxAge: thời gian truy xuất tối đa
     *
     * @param registry
     */

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("POST", "GET", "DELETE", "PUT")
                .maxAge(3600);
    }


}
