package com.example.apigateway.Filter;

import com.example.apigateway.Util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;

import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {
    @Autowired
    private RouteValidator validator;
    /*@Autowired
    private RestTemplate template;*/
    @Autowired
    private JwtUtil jwtUtil;

    public AuthenticationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (((exchange, chain) -> {
            if(validator.isSecured.test(exchange.getRequest())){
                //header contains the token or not
                if(!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION))
                    throw new RuntimeException("Authorization header is missing");

                String authHeader=exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                if(authHeader!=null && authHeader.startsWith("Bearer "))
                    authHeader = authHeader.substring(7);
                try {
                    //REST call to Auth service
                    /*template.getForObject("http://IDENTITY-SERVICE//validate?token"+authHeader,String.class);*/
                    jwtUtil.validateToken(authHeader);

                }catch (Exception e){
                    throw new RuntimeException("unauthorized access to application");
                }
            }
            return chain.filter(exchange);

        }));
    }
    public static class Config {
    }
}
