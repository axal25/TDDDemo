package axal25.oles.jacek.TDDDemo.ecommerce.entity;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Customer Entity Tests")
public class CustomerEntityUnitTest {

    @BeforeAll
    public void beforeAll() {
    }

    @AfterAll
    public void afterAll() {
    }

    @BeforeEach
    void beforeEach() {
    }

    @AfterEach
    void afterEach() {
    }

    @Test
    @Order(1)
    @DisplayName("Default Constructor Test")
    public void defaultConstructor() {
        CustomerEntity customerEntity = new CustomerEntity();
        assertNotNull(customerEntity, "new CustomerEntity() cannot be null.");

        CustomerEntity customerEntity2 = new CustomerEntity();
        assertEquals(customerEntity2, customerEntity, "new CustomerEntity() must be equal new CustomerEntity().");
    }

    @Test
    @Order(2)
    @DisplayName("Getters Test")
    public void getters() {
        CustomerEntity customerEntity = new CustomerEntity();
        assertNull(customerEntity.getId(), "new CustomerEntity().getId() must be null.");
        assertNull(customerEntity.getIdPerson(), "new CustomerEntity().getIdPerson() must be null.");
        assertNull(customerEntity.getIdOrganization(), "new CustomerEntity().getIdOrganization() must be null.");

        CustomerEntity customerEntity2 = new CustomerEntity();
        assertEquals(customerEntity2.getId(), customerEntity.getId(), "new CustomerEntity().getId() must be equal new CustomerEntity().getId().");
        assertEquals(customerEntity2.getIdPerson(), customerEntity.getIdPerson(), "new CustomerEntity().getIdPerson() must be equal new CustomerEntity().getIdPerson().");
        assertEquals(customerEntity2.getIdOrganization(), customerEntity.getIdOrganization(), "new CustomerEntity().getIdOrganization() must be equal new CustomerEntity().getIdOrganization().");
    }

    @Test
    @Order(3)
    @DisplayName("Setters (and Getters) Test")
    public void setters() {
        CustomerEntity customerEntity = new CustomerEntity();

        customerEntity.setId(1);
        assertEquals(customerEntity.getId(), 1, "Must be 1.");
        customerEntity.setIdPerson(2);
        assertEquals(customerEntity.getIdPerson(), 2, "Must be 2.");
        customerEntity.setIdOrganization(3);
        assertEquals(customerEntity.getIdOrganization(), 3, "Must be 3.");
    }

    @Test
    @Order(4)
    @DisplayName("CompareTo Test")
    public void compareToTest() {
        CustomerEntity customerEntity = new CustomerEntity();
        assertNotEquals(customerEntity.compareTo(null), 0, "new CustomerEntity().compareTo(null) cannot be 0.");
        CustomerEntity customerEntity2 = new CustomerEntity();
        assertEquals(customerEntity2.compareTo(customerEntity), 0, "new CustomerEntity().compareTo(new CustomerEntity()) must be 0.");
    }

    @Test
    @Order(5)
    @DisplayName("Equals Test")
    public void equalsTest() {
        CustomerEntity customerEntity = new CustomerEntity();
        assertFalse(customerEntity.equals(null), "new CustomerEntity().equals(null) cannot be true.");

        final String msgReflexive = "Equals must be Reflexive";
        assertTrue(customerEntity.equals(customerEntity), msgReflexive);

        CustomerEntity customerEntity2 = new CustomerEntity();

        assertTrue(customerEntity.equals(customerEntity2), "new CustomerEntity().equals(new CustomerEntity()) must be true.");

        final String msgSymmetric = "Equals must be Symmetric";
        assertEquals(customerEntity2.equals(customerEntity), customerEntity.equals(customerEntity2), msgSymmetric);

        final String msgTransitive = "Equals must be Transitive";
        CustomerEntity customerEntity3 = new CustomerEntity();
        assertEquals(customerEntity2.equals(customerEntity) && customerEntity3.equals(customerEntity), customerEntity2.equals(customerEntity3), msgTransitive);

        final String msgConsistent = "Equals must be Consistent";
        assertEquals(customerEntity.equals(customerEntity), customerEntity.equals(customerEntity), msgConsistent);

        assertEquals(customerEntity2.equals(customerEntity2), customerEntity.equals(customerEntity), msgConsistent);

        customerEntity.setId(1);
        assertTrue(customerEntity.equals(customerEntity), msgConsistent);
        assertFalse(customerEntity2.equals(customerEntity), msgConsistent);
        assertEquals(customerEntity2.equals(customerEntity), customerEntity.equals(customerEntity2), msgConsistent);
        customerEntity2.setId(1);
        assertTrue(customerEntity2.equals(customerEntity), msgConsistent);
        assertEquals(customerEntity2.equals(customerEntity), customerEntity.equals(customerEntity2), msgConsistent);

        customerEntity.setIdPerson(2);
        assertTrue(customerEntity.equals(customerEntity), msgConsistent);
        assertFalse(customerEntity2.equals(customerEntity), msgConsistent);
        assertEquals(customerEntity2.equals(customerEntity), customerEntity.equals(customerEntity2), msgConsistent);
        customerEntity2.setIdPerson(2);
        assertTrue(customerEntity2.equals(customerEntity), msgConsistent);
        assertEquals(customerEntity2.equals(customerEntity), customerEntity.equals(customerEntity2), msgConsistent);

        customerEntity.setIdOrganization(3);
        assertTrue(customerEntity.equals(customerEntity), msgConsistent);
        assertFalse(customerEntity2.equals(customerEntity), msgConsistent);
        assertEquals(customerEntity2.equals(customerEntity), customerEntity.equals(customerEntity2), msgConsistent);
        customerEntity2.setIdOrganization(3);
        assertTrue(customerEntity2.equals(customerEntity), msgConsistent);
        assertEquals(customerEntity2.equals(customerEntity), customerEntity.equals(customerEntity2), msgConsistent);

    }

    @Test
    @Order(6)
    @DisplayName("HashCode Test")
    public void hashCodeTest() {
        final String msgConsistent = "HashCode must be consistent";
        CustomerEntity customerEntity = new CustomerEntity();

        assertEquals(customerEntity.hashCode(), customerEntity.hashCode(), msgConsistent);

        CustomerEntity customerEntity1 = new CustomerEntity();
        assertEquals(customerEntity1.hashCode(), customerEntity.hashCode(), msgConsistent);

        customerEntity.setId(1);
        assertEquals(customerEntity.hashCode(), customerEntity.hashCode(), msgConsistent);
        assertNotEquals(customerEntity1.hashCode(), customerEntity.hashCode(), msgConsistent);
        customerEntity1.setId(1);
        assertEquals(customerEntity1.hashCode(), customerEntity.hashCode(), msgConsistent);

        customerEntity.setIdPerson(2);
        assertEquals(customerEntity.hashCode(), customerEntity.hashCode(), msgConsistent);
        assertNotEquals(customerEntity1.hashCode(), customerEntity.hashCode(), msgConsistent);
        customerEntity1.setIdPerson(2);
        assertEquals(customerEntity1.hashCode(), customerEntity.hashCode(), msgConsistent);

        customerEntity.setIdOrganization(3);
        assertEquals(customerEntity.hashCode(), customerEntity.hashCode(), msgConsistent);
        assertNotEquals(customerEntity1.hashCode(), customerEntity.hashCode(), msgConsistent);
        customerEntity1.setIdOrganization(3);
        assertEquals(customerEntity1.hashCode(), customerEntity.hashCode(), msgConsistent);
    }

    @Test
    @Order(7)
    @DisplayName("Equals and HashCode relation Test")
    public void equalsAndHashCodeTest() {
        final String msgEqualsConsistency = "customerEntity.equals(customerEntity2) => customerEntity.hashCode() == customerEntity2.hashCode()";
        CustomerEntity customerEntity = new CustomerEntity();
        assertEquals(customerEntity.hashCode() == customerEntity.hashCode(), customerEntity.equals(customerEntity), msgEqualsConsistency);

        CustomerEntity customerEntity2 = new CustomerEntity();
        assertEquals(customerEntity2.hashCode() == customerEntity.hashCode(), customerEntity.equals(customerEntity2), msgEqualsConsistency);

        final String msgInternalConsistency = "Property in Equals changes => HashCode can change.";
        customerEntity2.setId(1);
        assertEquals(customerEntity2.hashCode() == customerEntity.hashCode(), customerEntity.equals(customerEntity2), msgInternalConsistency);

        customerEntity.setId(1);
        assertEquals(customerEntity2.hashCode() == customerEntity.hashCode(), customerEntity.equals(customerEntity2), msgInternalConsistency);

        customerEntity2.setIdPerson(2);
        assertEquals(customerEntity2.hashCode() == customerEntity.hashCode(), customerEntity.equals(customerEntity2), msgInternalConsistency);

        customerEntity.setIdPerson(2);
        assertEquals(customerEntity2.hashCode() == customerEntity.hashCode(), customerEntity.equals(customerEntity2), msgInternalConsistency);

        customerEntity2.setIdOrganization(3);
        assertEquals(customerEntity2.hashCode() == customerEntity.hashCode(), customerEntity.equals(customerEntity2), msgInternalConsistency);

        customerEntity.setIdOrganization(3);
        assertEquals(customerEntity2.hashCode() == customerEntity.hashCode(), customerEntity.equals(customerEntity2), msgInternalConsistency);

        final String msgCollisions = "UnEqual objects => HashCodes can be same.";
    }

    @Test
    @Order(8)
    @DisplayName("Equals and CompareTo relation Test")
    public void equalsAndCompareTo() {
        final String msg = "If customerEntity.equals(customerEntity2) then customerEntity.compareTo(customerEntity2) == 0";

        CustomerEntity customerEntity = new CustomerEntity();
        assertEquals(customerEntity.compareTo(customerEntity) == 0, customerEntity.equals(customerEntity), msg);

        CustomerEntity customerEntity2 = new CustomerEntity();
        assertEquals(customerEntity.compareTo(customerEntity2) == 0, customerEntity.equals(customerEntity2), msg);
    }

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @DisplayName("Nested Class Tests")
    public class NestedClassTest {

        @BeforeAll
        public void beforeAll() {
        }

        @AfterAll
        public void afterAll() {
        }

        @BeforeEach
        void beforeEach() {
        }

        @AfterEach
        void afterEach() {
        }

        @Test
        @Order(1)
        @DisplayName("Constructor Test")
        public void constructor() {
        }
    }
}
