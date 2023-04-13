package fck.personalDetails.gender.services;

import fck.personalDetails.gender.dtos.GenderResponse;

import java.util.List;

public interface IGenderInformations {
    GenderResponse information(String name);
    List<GenderResponse> information(List<String> name);
}
