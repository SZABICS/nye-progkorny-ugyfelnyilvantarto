package hu.nye.progkorny.usermanagement.model.entities;

import lombok.EqualsAndHashCode;
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
        testUser = new Users(1, "Elek", "Teszt", "teszt@teszt.hu", "+36705130022", "12345678-9-12", "Tesz Cég", 1);
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
    public void testIsStillPartnerGivesBackRightValue() {
        Assertions.assertEquals(testUser.getIs_still_partner(), 1);
    }

    @Test
    public void testNoArgsContructor() {
        Users users1 = new Users();
        Assertions.assertNotNull(users1);
    }

    @Test
    public void testGettersAndSetters() {
        testUser.setId(2);
        testUser.setFirst_name("Teszt");
        testUser.setLast_name("Másik teszt");
        testUser.setE_mail("t@b.c");
        testUser.setPhone("abc");
        testUser.setIs_still_partner(0);
        testUser.setTax_code("111");
        testUser.setCompany("T Kft");

        Assertions.assertEquals("Teszt", testUser.getFirst_name());
        Assertions.assertEquals("Másik teszt", testUser.getLast_name());
        Assertions.assertEquals("t@b.c", testUser.getE_mail());
        Assertions.assertEquals("abc", testUser.getPhone());
        Assertions.assertEquals(0, testUser.getIs_still_partner());
        Assertions.assertEquals("111", testUser.getTax_code());
        Assertions.assertEquals("T Kft", testUser.getCompany());
    }
}
