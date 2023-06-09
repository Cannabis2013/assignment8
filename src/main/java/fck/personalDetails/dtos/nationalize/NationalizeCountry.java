package fck.personalDetails.dtos.nationalize;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NationalizeCountry {
    private String country_id;
    private double probability;
}
