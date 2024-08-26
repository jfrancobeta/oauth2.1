package com.jfranco.springboot.client.jwt.clientappoauth.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class securityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{
        http
            .authorizeHttpRequests((authHttp) -> authHttp
                    .requestMatchers("/authorized").permitAll() //es permite all ya que es el que genera el token 
                    .requestMatchers(HttpMethod.GET,"/list").hasAnyAuthority("SCOPE_read","SCOPE_write")//las protecciones a los endpoints que queremos proteger y que scope debe tener 
                    .requestMatchers(HttpMethod.POST, "/create").hasAuthority("SCOPE_write")
                    .anyRequest().authenticated())
                    .csrf(csrf -> csrf.disable()) // esto es solo para formularios
                    .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))// el inicio de sesion se manejara en el token
                    .oauth2Login(login -> login.loginPage("/oauth2/authorization/client-app"))//defecto
                    .oauth2Client(withDefaults())//defecto
                    .oauth2ResourceServer(resourceServer -> resourceServer.jwt(withDefaults()));//defecto
        
        return http.build();
                
    }
    
}
