package fck.personalDetails.dtos.restCountries;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RestCountryResponse {
    @JsonProperty("name")
    private RestCountriesName countryDetails;
}
