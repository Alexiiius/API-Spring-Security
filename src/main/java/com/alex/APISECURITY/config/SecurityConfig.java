package com.alex.APISECURITY.config;

import com.alex.APISECURITY.service.UserDetailServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
//must use enabled method security to permit the use of annotations in the controller endpoints
public class SecurityConfig {

    //Los pasos de la peticion http pasa a traves del security filter chain
    //Seguidamente, se llamara al authentication manager que usara los prooveedores para verificar al usuario
    //El provider usado es DAO, quien usa un encoder de password y el servicio que
    // leera de la db user details service


    //Security Filter Chain
    //object httpSecurity pass over every filter to fulfill the request
/*    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity request) throws Exception {
        return request
                .csrf(csrf -> csrf.disable())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(http -> {
                    //Public endpoints
                    http.requestMatchers(HttpMethod.GET, "/auth/hello").permitAll();

                    //Private endpoints
                    http.requestMatchers(HttpMethod.GET, "/auth/hello-secured").hasAuthority("CREATE");

                    //Any other endpoint
                    http.anyRequest().denyAll();
                    //http.anyRequest().authenticated();
                })
                .build();
    }*/

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity request) throws Exception {
        return request
                .csrf(csrf -> csrf.disable())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                //@PreAuthorize in the routes controller to define endpoints security access
                .build();
    }

    //authentication manager
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


    //-----------------------------START PROVIDER CONFIG -------------------------------------//

    //Dao provider uses 2 components like password encoder and user details service (who obtain the data from db)
    // Injecting the user data from UserDetailsServiceImpl
    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailServiceImpl userDetailsService) {
      DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }

    //don't use noOpPasswordEncoder in production because it does not encrypt the pw
    //use  bcrypt in production
/*    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }*/

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //obtain user data from db like authorities, roles...
/*    @Bean
    public UserDetailsService userDetailsService()  {
        List<UserDetails> userDetailsList = new ArrayList<>();

        userDetailsList.add(User
                .withUsername("alex")
                .password("1234")
                .roles("ADMIN")
                .authorities("READ", "CREATE")
                .build()
        );

        userDetailsList.add(User
                .withUsername("daniel")
                .password("1234")
                .roles("USER")
                .authorities("READ")
                .build()
        );

        return new InMemoryUserDetailsManager(userDetailsList);
    }*/



    //---------------------------END PROVIDER CONFIG---------------------------------------//

}
