package be.technifutur.exoSecu.config;

import be.technifutur.exoSecu.services.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true, // enable pre/post annotations
        securedEnabled = true, // enable @Secured
        jsr250Enabled = true // use of @RoleAllowed
)
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration source = new CorsConfiguration();
        source.addAllowedOrigin("http://localhost:4200");
        source.addAllowedMethod("*");
        source.addAllowedHeader("*");
        source.addExposedHeader("Authorization");
        source.setAllowCredentials(true);

        return request -> source;

    }

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    public SecurityConfig(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        // Chargement en mémoire d'utilisateurs hardcodés
//        auth
//                .inMemoryAuthentication()
//                .withUser("greg")
//                .password(passwordEncoder().encode("1234"))
//                .roles("USER")
//                .and()
//                .withUser("admin")
//                .password(passwordEncoder().encode("1234"))
//                .roles("ADMIN");

        // Chargement depuis une DB
        auth.userDetailsService(this.userService).passwordEncoder(passwordEncoder);

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .formLogin().loginPage("/login").permitAll()
                .and()
                .authorizeRequests()
                // Tjs les + spécialisées avant les + générales
                .antMatchers( "/h2-console/**").permitAll()
                .antMatchers(HttpMethod.POST, "/users/connected").permitAll()
//                .antMatchers(HttpMethod.POST, "/products").hasRole("ADMIN")
//                .antMatchers(HttpMethod.PUT, "/products").hasRole("ADMIN")
                .antMatchers("/voyages/**").authenticated()
//                .antMatchers("/users/**").authenticated()
                .anyRequest().authenticated()

                .and()
                .exceptionHandling()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(new JwtAuthenticationFilter(authenticationManager()))
                .addFilter(new JwtAuthorizationFilter(authenticationManager(), userService))
                .cors()
                // Spécial H2 CONFIG
                .and()
                .headers().frameOptions().disable();

    }
}
