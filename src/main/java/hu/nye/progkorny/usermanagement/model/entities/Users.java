package hu.nye.progkorny.usermanagement.model.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.*;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
public class Users {

    @Id
    @GeneratedValue
    private Integer id;
    private String first_name;
    private String last_name;
    private String e_mail;
    private String phone;
    private String tax_code;
    private String company;
    private Integer is_still_partner;

    public static List<Users> getExists() {
       List<Users> users = new ArrayList<>();
       users.add(new Users(1, "Elek", "Teszt", "teszt@teszt.hu", "+36705130022", "12345678-9-12", "Tesz Cég", 1));
       users.add(new Users(2, "Issza", "Bornem", "teszt@bornemissza.hu", "06706533122", "87654321-9-12", "Bor-Bor Kft.", 1));
       return users;
    }
}
