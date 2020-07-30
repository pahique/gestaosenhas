package atendimento.gestaosenhas;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    	String passwd = passwordEncoder.encode("password");
        auth.inMemoryAuthentication().passwordEncoder(passwordEncoder)
        		.withUser("gerente").password(passwd).roles("GERENTE");    
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.httpBasic()
        	.and()
        	.authorizeRequests()
            .antMatchers("/api/admin/**").hasRole("GERENTE")
            .antMatchers("/").permitAll()
            .and()
            .csrf().disable()
            .formLogin().disable();
    }

}
