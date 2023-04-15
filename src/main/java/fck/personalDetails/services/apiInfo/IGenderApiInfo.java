package fck.personalDetails.services.apiInfo;

public interface IGenderApiInfo {
    String agifyUrl(String param);

    String genderize(String param);

    String nationalize(String param);
    String restCountry(String code);
}
