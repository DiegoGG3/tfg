package app.block5crudvalidation.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/", "/*","/*/*","/*/*/*","/api/campeonatos/{id}","/liga","/verequipos", "/verjugadores", "/vercampeonatos", "/api/equipos", "/api/jugadores",  "/users/register", "/login").permitAll() // Permitir acceso público a la landing page y rutas de registro/login
                        .requestMatchers("/resources/**", "/Static/**", "/js/**", "/css/**", "/Images/**").permitAll() // Permitir acceso público a recursos estáticos como JavaScript, CSS e imágenes
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")  // Página de inicio de sesión personalizada
                        .defaultSuccessUrl("/admin", true)  // Redirigir después del login
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
