package fck.personalDetails.gender.services;

import fck.personalDetails.gender.converters.GenderResponseConverter;
import fck.personalDetails.gender.dtos.AgifyResponse;
import fck.personalDetails.gender.dtos.GenderResponse;
import fck.personalDetails.gender.dtos.GenderizeResponse;
import fck.personalDetails.gender.dtos.NationalizeResponse;
import fck.personalDetails.shared.services.http.IHttpFetch;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GenderInformations implements IGenderInformations {
    private final IHttpFetch _httpFetch;
    private final IGenderApiInfo _apiInfo;
    private final GenderResponseConverter _genderConverter;

    public GenderInformations(IHttpFetch httpFetch, IGenderApiInfo apiInfo, GenderResponseConverter genderConverter) {
        _httpFetch = httpFetch;
        _apiInfo = apiInfo;
        _genderConverter = genderConverter;
    }

    @Override
    public GenderResponse information(String name){
        GenderTaskCollection tasks;
        try {
            tasks = fetchInformations(name);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return _genderConverter.fromTaskCollection(tasks);
    }

    @Override
    public List<GenderResponse> information(List<String> names) {
        return names.stream()
                .map(this::fetchInformations)
                .map(_genderConverter::fromTaskCollection)
                .toList();
    }

    private GenderTaskCollection fetchInformations(String name){
        try {
            var agifyTask = _httpFetch.fetch(_apiInfo.agifyUrl(name), AgifyResponse.class);
            var genderizeTask = _httpFetch.fetch(_apiInfo.genderize(name),GenderizeResponse.class);
            var nationalizeTask = _httpFetch.fetch(_apiInfo.nationalize(name), NationalizeResponse.class);
            return new GenderTaskCollection(agifyTask,genderizeTask,nationalizeTask);
        } catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}

