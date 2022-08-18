package axal25.oles.jacek.TDDDemo.config;

import axal25.oles.jacek.TDDDemo.ecommerce.entity.TestEntityManagerUtils;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@BeanProvider(type = BeanProvider.Types.TEST)
@TestConfiguration
public class TestEntityManagerUtilsBeanProvider {

    @Bean
    public TestEntityManagerUtils testEntityManagerUtils() {
        return new TestEntityManagerUtils();
    }
}
