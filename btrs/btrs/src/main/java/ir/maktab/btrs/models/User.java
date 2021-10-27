package ir.maktab.btrs.models;

import ir.maktab.btrs.models.base.BaseEntity;
import ir.maktab.btrs.models.enumeration.UserGender;
import ir.maktab.btrs.models.enumeration.UserType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = User.TABLE_NAME)
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity<Long> {
    public static final String TABLE_NAME = "user_table";
    private static final String FIRST_NAME = "first_name";
    private static final String LAST_NAME = "last_name";
    private static final String USER_NAME = "user_name";
    private static final String USER_TYPE = "user_type";

    @Column(name = User.FIRST_NAME)
    private String firstName;

    @Column(name = User.LAST_NAME)
    private String lastName;

    @Column(name = User.USER_NAME, unique = true)
    private String username;

    private String password;

    @Column(name = User.USER_TYPE)
    @Enumerated(EnumType.STRING)
    private UserType userType;


    private String email;

    @Enumerated(EnumType.STRING)
    private UserGender gender;


}
