package com.ftn.e2.isa.blood_simple.config;

import com.ftn.e2.isa.blood_simple.security.TokenUtils;
import com.ftn.e2.isa.blood_simple.security.authentication.RestAuthenticationEntryPoint;
import com.ftn.e2.isa.blood_simple.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@SuppressWarnings("deprecation")
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    // Servis koji se koristi za citanje podataka o korisnicima aplikacije
    @Autowired
    private CustomUserDetailsService jwtUserDetailsService;
    // Handler za vracanje 401 kada klijent sa neodogovarajucim korisnickim imenom i lozinkom pokusa da pristupi resursu
    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;
    // Injektujemo implementaciju iz TokenUtils klase kako bismo mogli da koristimo njene metode za rad sa JWT u TokenAuthenticationFilteru
    @Autowired
    private TokenUtils tokenUtils;

    // Implementacija PasswordEncoder-a koriscenjem BCrypt hashing funkcije.
    // BCrypt po defalt-u radi 10 rundi hesiranja prosledjene vrednosti.
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Registrujemo authentication manager koji ce da uradi autentifikaciju korisnika za nas
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    // Definisemo uputstvo za authentication managera koji servis da koristi da izvuce podatke o korisniku koji zeli da se autentifikuje,
    //kao i kroz koji enkoder da provuce lozinku koju je dobio od klijenta u zahtevu da bi adekvatan hash koji dobije kao rezultat bcrypt algoritma uporedio sa onim koji se nalazi u bazi (posto se u bazi ne cuva plain lozinka)
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
    }


    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/*", configuration);
        return source;
    }

}