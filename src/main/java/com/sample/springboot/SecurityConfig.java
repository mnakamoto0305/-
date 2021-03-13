package com.sample.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.sample.springboot.Service.DatabaseUserDetailsService;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DatabaseUserDetailsService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService)
			.passwordEncoder(passwordEncoder());
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/webjars/**","/css/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		//直リンクの禁止&ログイン不要ページの設定
		http
			.authorizeRequests()
				.antMatchers("/webjars/**").permitAll()
				.antMatchers("/css/**").permitAll()
				.antMatchers("/login").permitAll()//アクセス許可
				.antMatchers("/login/session").permitAll()//セッションエラー
				.anyRequest().authenticated();//それ以外は直リンク禁止

		//httpsにリダイレクト
		http
			.requiresChannel()
			.antMatchers("/login*").requiresSecure();

		//ログイン処理
		http
			.formLogin()
				.loginProcessingUrl("/login")
				.loginPage("/login")
				.failureUrl("/login")
				.usernameParameter("userId")
				.passwordParameter("password")
				.defaultSuccessUrl("/", true);

		//ログアウト処理
		http
			.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutUrl("/logout")
				.logoutSuccessUrl("/login")
				.deleteCookies("JSESSIONID");

		//レスポンスヘッダーの設定
//		http
//			.headers()
//			.contentSecurityPolicy("default-src 'self");

		//セッション管理
		http.sessionManagement()
			.invalidSessionUrl("/error/session");//セッションエラーの遷移先

		//Remember Me設定
		http.rememberMe()
			.key("uniqueKeyAndSecret")//トークン識別のキー
			.rememberMeParameter("remember-me")//checkboxのname属性
			.rememberMeCookieName("remember-me")//Cookie名
			.tokenValiditySeconds(86400)//有効期限(秒数指定)
			.useSecureCookie(true);//HTTPS接続のみ使用可能

		//CSRF対策を無効に設定
		http.csrf().disable();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();

	}


}
