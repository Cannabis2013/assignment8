package fck.personalDetails.gender.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NationalizeResponse {
    private List<NationalizeCountry> country;
    private String name;
}
