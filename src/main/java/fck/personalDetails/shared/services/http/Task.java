package fck.personalDetails.shared.services.http;

import fck.personalDetails.shared.converters.IJsonDeserializer;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public final class Task<T> {
    private final CompletableFuture<HttpResponse<String>> _future;
    private final Class<T> _descriptor;
    private final IJsonDeserializer _deserializer;

    public Task(CompletableFuture<HttpResponse<String>> future, Class<T> descriptor, IJsonDeserializer deserializer) {
        _future = future;
        _descriptor = descriptor;
        _deserializer = deserializer;
    }

    public T result() throws Exception {
        return convert(_future.get().body());
    }

    private T convert(String json) throws Exception {
        return _deserializer.deserialize(json,_descriptor);
    }
}
