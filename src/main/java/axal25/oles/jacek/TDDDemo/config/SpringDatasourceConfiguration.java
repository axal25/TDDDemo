package axal25.oles.jacek.TDDDemo.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.validation.constraints.Pattern;

@BeanProvider
@Configuration
@PropertySource("classpath:application.properties")
@ConfigurationProperties(prefix = "spring.datasource")
@ConfigurationPropertiesScan
@Setter
@Getter
public class SpringDatasourceConfiguration {

    //    This or @ConfigurationProperties(prefix = "spring.datasource") and @ConfigurationPropertiesScan
    //    @Value("${spring.datasource.url}")
    @Pattern(regexp = "^[a-z]+\\:[a-z]+\\:[a-z]+\\:\\@[a-z]+\\:[0-9]{4}\\/[a-z]+\\.localdomain$")
    private String url;

    @Pattern(regexp = "^[a-z]+$")
    private String username;

    @Pattern(regexp = "^[a-z]+$")
    private String password;

    @Pattern(regexp = "^([a-z]+\\.)+[A-Za-z]+$")
    private String driverClassName;

    // TODO: is it even needed? what does it achieve?
//    @Bean
//    public DriverManagerDataSource driverManagerDataSource() {
//        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource(url, username, password);
//        driverManagerDataSource.setDriverClassName(driverClassName);
//        return driverManagerDataSource;
//    }

//    @Bean(name = "springDriverManagerDataSource")
//    public DriverManagerDataSource springDriverManagerDataSource() {
//        return new DriverManagerDataSource(url, username, password);
//    }
//
//    @Bean(name = "springDataSource")
//    public DataSource produceSpringDataSource() {
//        DriverManagerDataSource driverManagerDataSource = produceSpringDriverManagerDataSource();
//        driverManagerDataSource.setDriverClassName(driverClassName);
//        return driverManagerDataSource;
//    }
}
