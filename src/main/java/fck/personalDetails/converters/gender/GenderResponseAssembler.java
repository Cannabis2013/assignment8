package fck.personalDetails.converters.gender;

import fck.personalDetails.dtos.*;
import fck.personalDetails.dtos.agify.AgifyResponse;
import fck.personalDetails.dtos.genderize.GenderizeResponse;
import fck.personalDetails.dtos.nationalize.NationalizeCountry;
import fck.personalDetails.dtos.nationalize.NationalizeResponse;
import fck.personalDetails.dtos.restCountries.RestCountryResponse;
import fck.personalDetails.services.http.GenderTaskCollection;
import org.springframework.stereotype.Service;

@Service
public class GenderResponseAssembler {
    public GenderResponse fromTaskCollection(GenderTaskCollection taskCollection) {
        if(taskCollection == null)
            return null;
        return toResponse(taskCollection);
    }

    private GenderResponse toResponse(GenderTaskCollection taskCollection){
        AgifyResponse agify;
        GenderizeResponse genderize;
        NationalizeResponse nationalize;
        RestCountryResponse restCountries;
        try {
            agify = taskCollection.agifyResult();
            genderize = taskCollection.genderizeResult();
            nationalize = taskCollection.nationalizeResult();
            restCountries = taskCollection.restCountriesResult();
        } catch (Exception e){
            return null;
        }
        return GenderResponse
                .builder()
                .genderPropability(toPercentage(genderize.getProbability()))
                .age(agify.getAge())
                .ageCount(agify.getCount())
                .countryProbability(countryProbability(nationalize))
                .country(countryInfo(restCountries,nationalize))
                .gender(genderize.getGender())
                .name(agify.getName())
                .build();
    }

    private String countryCode(NationalizeResponse nationalize){
        var first = firstCountry(nationalize);
        if(first == null)
            return "";
        return first.getCountry_id();
    }

    private int countryProbability(NationalizeResponse nationalize){
        var first = firstCountry(nationalize);
        if(first == null)
            return 0;
        return toPercentage(first.getProbability());
    }

    private NationalizeCountry firstCountry(NationalizeResponse nationalize){
        var countries = nationalize.getCountry();
        return countries.stream().findFirst().orElse(null);
    }

    private int toPercentage(double p){
        return (int) (p*100);
    }

    private String countryInfo(RestCountryResponse restCountry, NationalizeResponse nationalize){
        var name = restCountry.getCountryDetails().getCountryName();
        var code = nationalize.getCountry().get(0).getCountry_id();
        return String.format("%s (%s)",name,code);
    }
}
