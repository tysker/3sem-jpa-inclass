package dk.lyngby.model;

import jakarta.persistence.Embeddable;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
public class ContactPerson {

    private String firstName;

    private String lastName;

    private String phone;

    public ContactPerson(String firstName, String lastName, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }
}
