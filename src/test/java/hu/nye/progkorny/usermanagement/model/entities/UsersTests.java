package hu.nye.progkorny.usermanagement.model.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class UsersTests {

    private List<Users> users;
    private Users testUser;

    @BeforeEach
    public void init() {
        users = Users.getExists();
        testUser = new Users(1, "Elek", "Teszt", "teszt@teszt.hu", "+36705130022", "12345678-9-12", "Tesz Cég", 1);
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
}
