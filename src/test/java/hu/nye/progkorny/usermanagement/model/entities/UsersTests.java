package hu.nye.progkorny.usermanagement.model.entities;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class UsersTests {

    private List<Users> users;
    private Users testUser;
    private Users testSecond;

    @BeforeEach
    public void init() {
        users = Users.getExists();
        testUser = new Users(1, "Elek", "Teszt", "teszt@teszt.hu", "+36705130022", "12345678-9-12", "Tesz Cég");
        testSecond = users.get(0);
    }

    @Test
    public void testUserStreamerFunctionWillGiveBackTheRightUser() {
        EqualsVerifier.simple().forClass(Users.class).verify();
        Assertions.assertEquals(testSecond, Users.streamUsersAndGiveBackResultById(users, 1));
        Assertions.assertTrue(testSecond.hashCode() == Users.streamUsersAndGiveBackResultById(users, 1).hashCode());
    }

    @Test
    public void testUsersIsNotEmptyAfterGetExists() {
        Assertions.assertEquals(users.size(), 2);
    }

    @Test
    public void testGetIdGivesBackRightNumber() {
        Assertions.assertEquals(testUser.getId(), 1);
    }

    @Test
    public void testNoArgsContructor() {
        Users users1 = new Users();
        Assertions.assertNotNull(users1);
    }

    @Test
    public void testGettersAndSetters() {
        testUser.setId(2);
        testUser.setFirstName("Teszt");
        testUser.setLastName("Másik teszt");
        testUser.setMailAddress("t@b.c");
        testUser.setPhone("abc");
        testUser.setTaxCode("111");
        testUser.setCompany("T Kft");

        Assertions.assertEquals("Teszt", testUser.getFirstName());
        Assertions.assertEquals("Másik teszt", testUser.getLastName());
        Assertions.assertEquals("t@b.c", testUser.getMailAddress());
        Assertions.assertEquals("abc", testUser.getPhone());
        Assertions.assertEquals("111", testUser.getTaxCode());
        Assertions.assertEquals("T Kft", testUser.getCompany());
    }
}
