package org.web.db;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.web.dto.PersonDto;

import javax.persistence.*;

@Entity
@Table(name = "Persons")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Persons {

    @Id
    @Column(name = "PersonID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long personId;

    @Column(name = "LastName")
    private String lastName;

    @Column(name = "FirstName")
    private String firstName;

    @Column(name = "Title")
    private String title;

    @Column(name = "Gender")
    private String gender;

    @Column(name = "Nat")
    private String nat;

    public static Persons fromDto(PersonDto dto) {
        return Persons.builder()
                .gender(dto.getGender())
                .nat(dto.getNat())
                .title(dto.getName().getTitle())
                .firstName(dto.getName().getFirst())
                .lastName(dto.getName().getLast())
                .build();
    }
}