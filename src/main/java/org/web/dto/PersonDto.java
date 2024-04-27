package org.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.web.db.Persons;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonDto {

    private String gender;
    private String nat;
    private NameDto name;

    public static PersonDto fromDb(Persons persons) {
        return PersonDto.builder()
                .nat(persons.getNat())
                .gender(persons.getGender())
                .name(
                        NameDto.builder()
                                .first(persons.getFirstName())
                                .last(persons.getLastName())
                                .title(persons.getTitle())
                                .build()
                )
                .build();
    }
}
