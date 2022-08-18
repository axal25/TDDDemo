package axal25.oles.jacek.TDDDemo.config;

import org.springframework.context.ApplicationContext;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

class BeanProviderAssertions {

    static void assertBeanProvided(Object bean, ApplicationContext applicationContext, String beanName, Class<?> beanClass) {
        assertThat(bean, is(notNullValue()));
        assertThat(applicationContext.containsBean(beanName), is(equalTo(true)));
        assertThat(applicationContext.getBean(beanClass), is(notNullValue()));
    }
}
