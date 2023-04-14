package fck.personalDetails.shared.converters;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface IJsonDeserializer {
    <T> T deserialize(String json, Class<T> descriptor) throws JsonProcessingException;
}
