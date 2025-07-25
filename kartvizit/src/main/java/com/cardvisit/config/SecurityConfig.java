package com.cardvisit.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.ldap.authentication.BindAuthenticator;
import org.springframework.security.ldap.authentication.LdapAuthenticationProvider;
import org.springframework.security.ldap.userdetails.DefaultLdapAuthoritiesPopulator;
import org.springframework.security.web.SecurityFilterChain;
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/admin/**").authenticated()
                .anyRequest().permitAll()
            )
            .formLogin(form -> form
            		.loginPage("/login")
                .defaultSuccessUrl("/admin/employees/create", true)
                .permitAll()
            )
            
            .logout(logout -> logout.permitAll());

        return http.build();
    }

    @Bean
    LdapContextSource contextSource() {
        LdapContextSource contextSource = new LdapContextSource();
        contextSource.setUrl("ldap://localhost:389");
        contextSource.setBase("dc=mycompany,dc=com");
        contextSource.setUserDn("cn=admin,dc=mycompany,dc=com");
        contextSource.setPassword("adminpassword");
        contextSource.afterPropertiesSet();
        return contextSource;
    }

    @Bean
    LdapAuthenticationProvider ldapAuthenticationProvider() {
        BindAuthenticator bindAuthenticator = new BindAuthenticator(contextSource());
        bindAuthenticator.setUserDnPatterns(new String[]{"uid={0},ou=users"});
        
        DefaultLdapAuthoritiesPopulator authoritiesPopulator =
                new DefaultLdapAuthoritiesPopulator(contextSource(), null);
        authoritiesPopulator.setIgnorePartialResultException(true);
        authoritiesPopulator.setSearchSubtree(true);

        return new LdapAuthenticationProvider(bindAuthenticator, authoritiesPopulator);
    }
}