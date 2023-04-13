package fck.personalDetails.shared.services.http;

import java.util.concurrent.ExecutionException;

public interface IHttpFetch {
    <T> Task<T> fetch(String uri, Class<T> descriptor) throws Exception;
}
