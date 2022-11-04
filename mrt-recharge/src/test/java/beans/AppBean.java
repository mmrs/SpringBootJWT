package beans;

import com.mmrs.mrt.recharge.util.JwtUtil;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppBean {

    @Bean
    public JwtUtil jwtUtil(){
        return Mockito.mock(JwtUtil.class);
    }
}
