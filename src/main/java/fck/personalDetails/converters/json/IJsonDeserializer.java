package fck.personalDetails.converters.json;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface IJsonDeserializer {
    <T> T deserialize(String json, Class<T> descriptor) throws Exception;
}
