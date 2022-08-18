package axal25.oles.jacek.TDDDemo.config.actuator;

import axal25.oles.jacek.TDDDemo.config.BeanProvider;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.jdbc.DataSourceHealthIndicator;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import javax.validation.constraints.Pattern;

@BeanProvider
@Configuration
@ConfigurationProperties(prefix = "spring.datasource")
@ConfigurationPropertiesScan
@Setter
@Getter
public class CustomDatabaseHealthActuator {

    @Pattern(regexp = "^(A-Z\\s)+$")
    private String validationQuery;

    @Bean("customDatabaseHealthIndicator")
    public HealthIndicator customDatabaseHealthIndicator(DataSource dataSource) {
        return new DataSourceHealthIndicator(dataSource, validationQuery);
    }
}
