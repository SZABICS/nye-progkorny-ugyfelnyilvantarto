package hu.nye.progkorny.usermanagement.model.entities;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Users class for user repository.
 */
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
public class Users {

    private Integer id;
    private String firstName;
    private String lastName;
    private String mailAddress;
    private String phone;
    private String taxCode;
    private String company;
    private Integer isStillPartner;

    /**
     * It's fill a List - Users with some data for tests.
     */
    public static List<Users> getExists() {
        List<Users> users = new ArrayList<>();
        users.add(new Users(1, "Elek", "Teszt", "teszt@teszt.hu", "+36705130022", "12345678-9-12", "Tesz Cég", 1));
        users.add(new Users(2, "Issza", "Bornem", "teszt@bornemissza.hu", "06706533122", "87654321-9-12", "Bor-Bor Kft.", 1));
        return users;
    }

    /**
     * Stream a List - Users object for a specific Users class by and existed id. Returns Null if not found
     */
    public static Users streamUsersAndGiveBackResultById(List<Users> users, Integer id) {
        return users.stream()
                .filter(customer -> id.equals(customer.getId()))
                .findAny()
                .orElse(null);
    }
}
