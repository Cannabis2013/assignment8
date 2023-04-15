package fck.personalDetails.converters.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.springframework.stereotype.Service;

@Service
public class JacksonJsonDeserializer implements IJsonDeserializer{
    @Override
    public <T> T deserialize(String json, Class<T> descriptor) throws Exception {
        try {
            return fromJsonObject(json,descriptor);
        } catch (JsonProcessingException e){
            return fromJsonArray(json,descriptor);
        }
    }

    private <T> T fromJsonObject(String json, Class<T> descriptor) throws JsonProcessingException {
        return new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false)
                .readValue(json, descriptor);
    }

    private <T> T fromJsonArray(String json, Class<T> descriptor) throws Exception {
        var arr = new JSONArray(json);
        if(arr.isEmpty())
            throw new Exception("Is not array");
        var obj = arr.get(0);
        return fromJsonObject(obj.toString(),descriptor);
    }
}
