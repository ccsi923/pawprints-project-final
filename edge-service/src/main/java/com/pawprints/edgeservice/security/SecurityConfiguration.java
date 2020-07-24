package com.pawprints.edgeservice.security;

import com.pawprints.edgeservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.savedrequest.NullRequestCache;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;

    @Bean
    public PasswordEncoder passwordEncoder () {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userService)
                .passwordEncoder(passwordEncoder);
    }

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.httpBasic();

        httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()

                /**
                 * User controller
                 */
                .mvcMatchers(HttpMethod.POST,"/client").permitAll()
                .mvcMatchers(HttpMethod.POST,"/admin").hasAuthority("ROLE_ADMIN")
                .mvcMatchers(HttpMethod.POST,"/animal").hasAuthority("ROLE_CLIENT")
                .mvcMatchers(HttpMethod.GET,"/animals/owner/{id}").hasAuthority("ROLE_CLIENT")
                .mvcMatchers(HttpMethod.DELETE,"/animal/{id}").hasAuthority("ROLE_CLIENT")

                /**
                 * Material controller
                 */
                .mvcMatchers(HttpMethod.GET,"/orders/by/status").hasAuthority("ROLE_ADMIN")
                .mvcMatchers(HttpMethod.PUT,"/update/order/{orderid}/status/{status}").hasAuthority("ROLE_ADMIN")
                .mvcMatchers(HttpMethod.POST,"/order").hasAuthority("ROLE_ADMIN")
                .mvcMatchers(HttpMethod.GET,"/orders").hasAuthority("ROLE_ADMIN")
                .mvcMatchers(HttpMethod.GET,"/orderlines/by/order/{orderId}").hasAuthority("ROLE_ADMIN")
                .mvcMatchers(HttpMethod.GET,"/quantities/by/product/type").hasAuthority("ROLE_ADMIN")
                .mvcMatchers(HttpMethod.GET,"/product/{id}").hasAuthority("ROLE_ADMIN")
                .mvcMatchers(HttpMethod.GET,"/products").hasAuthority("ROLE_ADMIN")
                .mvcMatchers(HttpMethod.PUT,"/update/purchaseunits/by/order/{id}").hasAuthority("ROLE_ADMIN")
                .mvcMatchers(HttpMethod.PUT,"/makekit/by/producttype/{prodtype}").hasAuthority("ROLE_ADMIN")
                .mvcMatchers(HttpMethod.GET,"/providerorders/by/status").hasAuthority("ROLE_ADMIN")
                .mvcMatchers(HttpMethod.GET,"/providerorders").hasAuthority("ROLE_ADMIN")
                .mvcMatchers(HttpMethod.POST,"/providerorder").hasAuthority("ROLE_ADMIN")
                .mvcMatchers(HttpMethod.GET,"/providerorderlines/by/providerorder/{probid}").hasAuthority("ROLE_ADMIN")
                .mvcMatchers(HttpMethod.GET,"/expenses/between").hasAuthority("ROLE_ADMIN")
                .mvcMatchers(HttpMethod.GET,"/expenses/per/date").hasAuthority("ROLE_ADMIN")


                /**
                 * Cart controller
                 */

                .mvcMatchers(HttpMethod.POST,"/cart").hasAuthority("ROLE_CLIENT")
                .mvcMatchers(HttpMethod.GET,"/carts").hasAuthority("ROLE_ADMIN")
                .mvcMatchers(HttpMethod.DELETE,"/cart/{cartid}").hasAuthority("ROLE_ADMIN")
                .mvcMatchers(HttpMethod.GET,"/cartitems").hasAuthority("ROLE_ADMIN")
                .mvcMatchers(HttpMethod.GET,"/cartitem/{id}").hasAuthority("ROLE_ADMIN")
                .mvcMatchers(HttpMethod.GET,"/cartitem/by/cart/{cartid}").hasAuthority("ROLE_ADMIN")
                .mvcMatchers(HttpMethod.GET,"/orders").hasAuthority("ROLE_ADMIN")
                .mvcMatchers(HttpMethod.GET,"/order/{ordid}").hasAuthority("ROLE_CLIENT")
                .mvcMatchers(HttpMethod.PUT,"/order/update/status/{ordid}").hasAuthority("ROLE_ADMIN")
                .mvcMatchers(HttpMethod.GET,"/incomes/perday").hasAuthority("ROLE_ADMIN")
                .mvcMatchers(HttpMethod.GET,"/carts/by/status/{status}").hasAuthority("ROLE_ADMIN")


                .and().requestCache().requestCache(new NullRequestCache()).and().httpBasic().and().cors().and().csrf().disable();
        httpSecurity.authorizeRequests().anyRequest().permitAll();
    }

}
