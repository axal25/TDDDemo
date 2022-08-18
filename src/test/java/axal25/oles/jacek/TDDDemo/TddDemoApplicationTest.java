package axal25.oles.jacek.TDDDemo;

import org.assertj.core.api.Assertions;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

@SpringBootTest
class TddDemoApplicationTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void applicationContext_IsNotNull() {
        MatcherAssert.assertThat(applicationContext, is(notNullValue()));
        Assertions.assertThat(applicationContext).isNotNull();
    }
}
