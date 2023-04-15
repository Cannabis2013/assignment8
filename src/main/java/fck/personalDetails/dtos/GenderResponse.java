package fck.personalDetails.dtos;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GenderResponse {
    private String name;
    private String gender;
    private int genderPropability;
    private int age;
    private int ageCount;
    private String country;
    private int countryProbability;
}
