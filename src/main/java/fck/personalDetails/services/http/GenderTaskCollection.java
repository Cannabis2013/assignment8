package fck.personalDetails.services.http;

import fck.personalDetails.dtos.agify.AgifyResponse;
import fck.personalDetails.dtos.genderize.GenderizeResponse;
import fck.personalDetails.dtos.nationalize.NationalizeResponse;
import fck.personalDetails.dtos.restCountries.RestCountryResponse;

public class GenderTaskCollection{
    public Task<AgifyResponse> agify;
    public Task<GenderizeResponse> genderize;
    public Task<NationalizeResponse> nationalize;
    public Task<RestCountryResponse> restCountriesTask;

    public AgifyResponse agifyResult() throws Exception{
        return agify.result();
    }

    public GenderizeResponse genderizeResult() throws Exception{
        return genderize.result();
    }

    public NationalizeResponse nationalizeResult() throws Exception{
        return nationalize.result();
    }

    public RestCountryResponse restCountriesResult() throws Exception {
        return restCountriesTask.result();
    }
}
