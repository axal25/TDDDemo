package axal25.oles.jacek.TDDDemo.config;

import axal25.oles.jacek.TDDDemo.ecommerce.entity.TestEntityManagerUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestEntityManagerUtilsBeanProvider.class})
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TestEntityManagerUtilsBeanProviderIntegrationTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private TestEntityManagerUtils testEntityManagerUtils;

    @Test
    public void testEntityManagerUtils_beanProvided() {
        BeanProviderAssertions.assertBeanProvided(
                testEntityManagerUtils, applicationContext,
                "testEntityManagerUtils", TestEntityManagerUtils.class);
    }
}
