package fck.personalDetails.gender.services;

import org.springframework.stereotype.Service;

@Service
public class GenderApiInformations implements IGenderApiInfo {
    @Override
    public String agifyUrl(String name){
        return "https://api.agify.io?name=" + name;
    }

    @Override
    public String genderize(String name){
        return "https://api.genderize.io?name=" + name;
    }

    @Override
    public String nationalize(String name){
        return "https://api.nationalize.io?name=" + name;
    }
}
