package fck.personalDetails.gender.services;

public interface IGenderApiInfo {
    String agifyUrl(String param);

    String genderize(String param);

    String nationalize(String param);
}
