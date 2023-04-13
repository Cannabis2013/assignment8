package fck.personalDetails.gender.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GenderizeResponse {
    private int count;
    private String name;
    private String gender;
    private double probability;
}
