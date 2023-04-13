package fck.personalDetails.gender.services;

import fck.personalDetails.gender.dtos.AgifyResponse;
import fck.personalDetails.gender.dtos.GenderizeResponse;
import fck.personalDetails.gender.dtos.NationalizeResponse;
import fck.personalDetails.shared.services.http.Task;

public class GenderTaskCollection{
    private final Task<AgifyResponse> agifyResponseTask;
    private final Task<GenderizeResponse> genderizeTask;
    private final Task<NationalizeResponse> nationalizeTask;

    public GenderTaskCollection(Task<AgifyResponse> agifyResponseTask, Task<GenderizeResponse> genderizeTask, Task<NationalizeResponse> nationalizeTask) {
        this.agifyResponseTask = agifyResponseTask;
        this.genderizeTask = genderizeTask;
        this.nationalizeTask = nationalizeTask;
    }

    public AgifyResponse agifyResult() throws Exception{
        return agifyResponseTask.result();
    }

    public GenderizeResponse genderizeResult() throws Exception{
        return genderizeTask.result();
    }

    public NationalizeResponse nationalizeResult() throws Exception{
        return nationalizeTask.result();
    }
}
