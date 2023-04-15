package fck.personalDetails.services.genderInfo;

import fck.personalDetails.dtos.GenderResponse;

import java.util.List;

public interface IGenderInformations {
    GenderResponse information(String name);
    List<GenderResponse> information(List<String> name);
}
