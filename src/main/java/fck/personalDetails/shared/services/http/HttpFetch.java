package fck.personalDetails.shared.services.http;

import fck.personalDetails.shared.converters.IJsonDeserializer;
import org.springframework.stereotype.Service;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import static java.time.temporal.ChronoUnit.SECONDS;

@Service
public class HttpFetch implements IHttpFetch {
    private final IJsonDeserializer _deserializer;

    public HttpFetch(IJsonDeserializer _deserializer) {
        this._deserializer = _deserializer;
    }

    @Override
    public <T> Task<T> fetch(String uri, Class<T> descriptor) throws Exception{
        var future = getRequest(uri);
        return new Task<>(future, descriptor, _deserializer);
    }

    private CompletableFuture<HttpResponse<String>> getRequest(String uri) throws ExecutionException, InterruptedException {
        var req = HttpRequest.newBuilder(URI.create(uri))
                .timeout(Duration.of(10, SECONDS))
                .build();
        return HttpClient.newHttpClient().sendAsync(req, BodyHandlers.ofString());
    }
}
