package fck.personalDetails.shared.services.http;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public final class Task<T> {
    private final CompletableFuture<HttpResponse<String>> _future;
    private final Class<T> _descriptor;
    public Task(CompletableFuture<HttpResponse<String>> future, Class<T> descriptor) {
        _future = future;
        _descriptor = descriptor;
    }

    public T result() throws Exception {
        return convert(_future.get().body());
    }

    private T convert(String json) throws Exception {
        return new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false)
                .readValue(json, _descriptor);
    }
}
