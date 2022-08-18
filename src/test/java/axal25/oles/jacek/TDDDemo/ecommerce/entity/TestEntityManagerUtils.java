package axal25.oles.jacek.TDDDemo.ecommerce.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.stream.IntStream;

public class TestEntityManagerUtils {

    @Autowired
    private TestEntityManager testEntityManager;

    public Integer getLowestAvailableIntegerId(Class<?> clazz) {
        return IntStream.range(0, Integer.MAX_VALUE)
                .boxed()
                .filter(id -> null == testEntityManager.getEntityManager().find(clazz, id))
                .findFirst().orElse(null);
    }
}
