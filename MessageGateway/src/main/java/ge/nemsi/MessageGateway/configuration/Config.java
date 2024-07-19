package ge.nemsi.MessageGateway.configuration;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Luka Nemsitsveridze
 * @role Configuration class for Spring beans.
 *
 * <p>This class defines Spring beans that are used throughout the application.
 */
@Configuration
public class Config {

    @Bean
    public FilterRegistrationBean<LoggerConfig> log4j2Filter() {
        FilterRegistrationBean<LoggerConfig> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new LoggerConfig());
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }
}