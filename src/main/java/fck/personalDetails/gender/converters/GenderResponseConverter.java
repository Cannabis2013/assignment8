package fck.personalDetails.gender.converters;

import fck.personalDetails.gender.dtos.*;
import fck.personalDetails.gender.services.GenderTaskCollection;
import org.springframework.stereotype.Service;

@Service
public class GenderResponseConverter {
    public GenderResponse fromTaskCollection(GenderTaskCollection taskCollection) {
        AgifyResponse agify;
        GenderizeResponse genderize;
        NationalizeResponse nationalize;
        try {
            agify = taskCollection.agifyResult();
            genderize = taskCollection.genderizeResult();
            nationalize = taskCollection.nationalizeResult();
        } catch (Exception e){
            return null;
        }
        return GenderResponse
                .builder()
                .genderPropability(toPercentage(genderize.getProbability()))
                .age(agify.getAge())
                .ageCount(agify.getCount())
                .countryProbability(countryProbability(nationalize))
                .country(countryCode(nationalize))
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

    int toPercentage(double p){
        return (int) (p*100);
    }
}
