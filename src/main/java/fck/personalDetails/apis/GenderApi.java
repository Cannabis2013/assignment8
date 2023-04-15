package fck.personalDetails.apis;

import fck.personalDetails.dtos.GenderResponse;
import fck.personalDetails.services.genderInfo.IGenderInformations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;
import java.util.List;
import java.util.stream.Stream;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestController
@RequestMapping("/gender")
public class GenderApi {
    private final IGenderInformations _genderInformation;

    public GenderApi(IGenderInformations genderInformation) {
        _genderInformation = genderInformation;
    }

    @GetMapping
    public List<GenderResponse> genderInfo(String[] names){
        return Stream.of(names)
                .map(name -> {
                    var info = _genderInformation.information(name);
                    if(info == null)
                        throw new HttpServerErrorException(INTERNAL_SERVER_ERROR);
                    return info;
                }).toList();
    }

    @GetMapping("/")
    public GenderResponse genderInfo(String name){
        var info = _genderInformation.information(name);
        if(info == null)
            throw new HttpServerErrorException(INTERNAL_SERVER_ERROR);
        return info;
    }
}
