package ir.maktab.btrs.models.dto;

import ir.maktab.btrs.models.enumeration.UserGender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {

    private String firstName;

    private String lastName;

    @Enumerated(EnumType.STRING)
    private UserGender gender;

}
