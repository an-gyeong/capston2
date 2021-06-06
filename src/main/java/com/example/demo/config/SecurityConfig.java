package com.example.demo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.demo.handler.CustomAuthenticationFailureHandler;
import com.example.demo.handler.CustomAuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private AuthenticationSuccessHandler successHandler;
	
	@Autowired
	private AuthenticationFailureHandler failureHandler;
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
            .authorizeRequests() // 각 경로에 따른 권한 지정
                .antMatchers("/" , "/signup/idcheck", "/game/signup", 
                		"/game/main" , "/static/**").permitAll() // 모든 권한
                //.antMatchers("/admin/**").hasRole("ADMIN") // 괄호의 권한을 가진 유저만 접근가능, ROLE_가 붙어서 적용 됨. 즉, 테이블에 ROLE_권한명 으로 저장해야 함.
                //.antMatchers("/staff/**").hasRole("STAFF")
                //.antMatchers("/custom/**").hasRole("CUSTO")
                //.antMatchers("/**").permitAll()
                .anyRequest().authenticated() // 로그인 페이지로 리다이렉션
                .and()
            .csrf()
            	//.ignoringAntMatchers("/")
                .and()
            .formLogin() // 하위에 내가 직접 구현한 로그인 폼, 로그인 성공시 이동 경로 설정 가능. , 로그인 폼의 아이디,패스워드는 username, password로 맞춰야 함
                .loginPage("/game/main") // 로그인이 수행될 경로.
                .loginProcessingUrl("/game/main")// 로그인 form의 action과 일치시켜주어야 함.
                //.defaultSuccessUrl("/loginSuccess") // 로그인 성공 시 이동할 경로.
                //.failureUrl("/login?error=true") // 인증에 실패했을 때 보여주는 화면 url, 로그인 form으로 파라미터값 error=true로 보낸
                .successHandler(successHandler)
                .failureHandler(failureHandler)
                .permitAll()
                .and()
            .logout()
                //.permitAll()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                //.logoutUrl("/logout")
                //.logoutSuccessUrl("/")
                .logoutSuccessUrl("/logout/result")
                .invalidateHttpSession(true)
                .and()
            .exceptionHandling()
              //.accessDeniedPage("/accessDenied_page"); // 권한이 없는 대상이 접속을시도했을 때
                .accessDeniedPage("/denied");
    }
    
    /*
	  @Bean
	  public PasswordEncoder passwordEncoder() {
		log.info("[ BEAN ] : passwordEncoder");
	    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	  }
	*/
    
	@Bean // AccountService에서 비밀번호 암호화가 가능하도록 Bean으로 등록함
	public PasswordEncoder passwordEncoder() {
		log.info("[ BEAN ] : passwordEncoder");
	    //return PasswordEncoderFactories.createDelegatingPasswordEncoder();
		return new BCryptPasswordEncoder();
	}
    
	  // 로그인 성공 처리를 위한 Handler
	@Bean
	public AuthenticationSuccessHandler successHandler() {
		log.info("[ BEAN ] : AuthenticationSuccessHandler");
		// loginIdname, defaultUrl
	    return new CustomAuthenticationSuccessHandler("username", "/loginSuccess");
	}
	  
	// 실패 처리를 위한 Handler
	@Bean
	public AuthenticationFailureHandler failureHandler() {
		log.info("[ BEAN ] : failureHandler");
		return new CustomAuthenticationFailureHandler("username", "password", "loginRedirectUrl" , "exceptionMsgName" , "/login");
	}
}
