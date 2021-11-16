package ru.vivt.corpapp.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/homepage").hasAnyAuthority("STAFF","ADMIN","USER")
                .antMatchers("/homepage/").hasAnyAuthority("STAFF","ADMIN","USER")
                .antMatchers("/homepage/incidents/takeToJob/").hasAuthority("STAFF")
                .antMatchers("/homepage/incidents/setWorkFlow/**").hasAnyAuthority("STAFF","ADMIN")
                .antMatchers("/homepage/incidents/create/").hasAuthority("USER")
                .antMatchers("/").permitAll()
                .and().formLogin()
                .and()
                .logout().invalidateHttpSession(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/logout-success").permitAll();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder
        () {
        return NoOpPasswordEncoder.getInstance();
    }
}
