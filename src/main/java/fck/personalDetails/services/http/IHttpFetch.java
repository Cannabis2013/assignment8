package fck.personalDetails.services.http;

public interface IHttpFetch {
    <T> Task<T> fetch(String uri, Class<T> descriptor) throws Exception;
}
