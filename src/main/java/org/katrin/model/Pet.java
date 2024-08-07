package org.katrin.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Pet {
    private int id;
    private String name;
    private String type;
    private String breed;
    private int age;
    private Gender gender;

    public String toString() {
        return "Pet №" + id + ":" + "\n - name - " + this.getName() + "\n - type - " + this.getType() + "\n - breed - " + this.getBreed() + "\n - age - " +
                this.getAge() + "\n - gender - " + this.getGender().getMessage();
    }
}
