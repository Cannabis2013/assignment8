package fck.personalDetails.services.genderInfo;

import fck.personalDetails.converters.gender.GenderResponseAssembler;
import fck.personalDetails.dtos.GenderResponse;
import fck.personalDetails.services.apiInfo.IGenderApiInfo;
import fck.personalDetails.services.http.IHttpFetch;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GenderInformations implements IGenderInformations {
    private final GenderResponseAssembler _genderConverter;
    private final GenderInformationHttpFetch _informations;

    public GenderInformations(IHttpFetch httpFetch, IGenderApiInfo apiInfo, GenderResponseAssembler genderConverter, GenderInformationHttpFetch requestCountryName, GenderInformationHttpFetch informations) {
        _genderConverter = genderConverter;
        _informations = informations;
    }

    @Override
    public GenderResponse information(String name){
        var tasks = _informations.fetchInformations(name);
        return _genderConverter.fromTaskCollection(tasks);
    }

    @Override
    public List<GenderResponse> information(List<String> names) {
        return names.stream()
                .map(_informations::fetchInformations)
                .map(_genderConverter::fromTaskCollection)
                .toList();
    }
}

