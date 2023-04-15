package fck.personalDetails.services.genderInfo;

import fck.personalDetails.dtos.agify.AgifyResponse;
import fck.personalDetails.dtos.genderize.GenderizeResponse;
import fck.personalDetails.dtos.nationalize.NationalizeResponse;
import fck.personalDetails.dtos.restCountries.RestCountryResponse;
import fck.personalDetails.services.apiInfo.IGenderApiInfo;
import fck.personalDetails.services.http.GenderTaskCollection;
import fck.personalDetails.services.http.IHttpFetch;
import fck.personalDetails.services.http.Task;
import org.springframework.stereotype.Service;

@Service
public class GenderInformationHttpFetch {
    private final IHttpFetch _httpFetch;
    private final IGenderApiInfo _apiInfo;

    public GenderInformationHttpFetch(IHttpFetch _httpFetch, IGenderApiInfo _apiInfo) {
        this._httpFetch = _httpFetch;
        this._apiInfo = _apiInfo;
    }

    public GenderTaskCollection fetchInformations(String name){
        var tasks = new GenderTaskCollection();
        try {
            tasks.agify = _httpFetch.fetch(_apiInfo.agifyUrl(name), AgifyResponse.class);
            tasks.genderize = _httpFetch.fetch(_apiInfo.genderize(name), GenderizeResponse.class);
            tasks.nationalize = _httpFetch.fetch(_apiInfo.nationalize(name), NationalizeResponse.class);
            tasks.restCountriesTask = requestCountryName(tasks);
        } catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
        return tasks;
    }

    private Task<RestCountryResponse> requestCountryName(GenderTaskCollection tasks) throws Exception {
        var code = countryCode(tasks);
        return _httpFetch.fetch(_apiInfo.restCountry(code), RestCountryResponse.class);
    }

    private String countryCode(GenderTaskCollection tasks) throws Exception {
        var nationalizeResponse = tasks.nationalizeResult();
        var countries = nationalizeResponse.getCountry();
        var first = countries.stream().findFirst().orElseThrow(Exception::new);
        return first.getCountry_id();
    }
}
