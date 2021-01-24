package fr.romitou.puffer4j;

import fr.romitou.puffer4j.objects.AuthRequest;
import fr.romitou.puffer4j.objects.PufferSession;
import okhttp3.*;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Puffer4J {

    private final Retrofit retrofit;
    private PufferService pufferService;
    private String sessionToken;
    private List<String> scopes;

    public Puffer4J(String baseUrl, String user, String password, Boolean useSSL) throws PufferException {
        OkHttpClient client = new OkHttpClient.Builder()
                .cookieJar(new CookieJar() {
                    @Override
                    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) { }

                    @Override
                    public List<Cookie> loadForRequest(HttpUrl url) {
                        final ArrayList<Cookie> cookies = new ArrayList<>(1);
                        if (sessionToken != null)
                            cookies.add(new Cookie.Builder()
                                    .domain(baseUrl)
                                    .path("/")
                                    .name("puffer_auth")
                                    .value(sessionToken)
                                    .httpOnly()
                                    .secure()
                                    .build()
                            );
                        return cookies;
                    }
                })
                .addInterceptor(chain -> {
                    Request request = chain.request().newBuilder()
                            .addHeader("Content-Type", "application/json")
                            .addHeader("Accept", "application/json")
                            .build();
                    return chain.proceed(request);
                })
                .build();
        this.retrofit = new Retrofit.Builder()
                .baseUrl((useSSL ? "https://" : "http://") + baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.pufferService = this.retrofit.create(PufferService.class);

        PufferSession pufferSession = authenticate(new AuthRequest(user, password));
        this.sessionToken = pufferSession.getSessionToken();
        this.scopes = pufferSession.getScopes();
    }

    public List<String> getScopes() {
        return scopes;
    }

    public PufferSession authenticate(AuthRequest authRequest) throws PufferException {
        Response<PufferSession> session;
        try {
            System.out.println(this.pufferService.getSession(authRequest).request().toString());
            session = this.pufferService.getSession(authRequest).execute();
        } catch (IOException e) {
            throw new PufferException("Error while getting session ", e);
        }
        return parse(session);
    }

    public <T> T parse(Response<T> response) throws PufferException {
        try {
            if (response.isSuccessful()) {
                T responseBody = response.body();
                if (responseBody != null)
                    return responseBody;
                else
                    throw new PufferException("Received response null response body");
            } else {
                throw new PufferException("Request on " + response.raw().request().url() + " was not successful and returned " + response.code() + " HTTP code.");
            }
        } catch (PufferException e) {
            throw new PufferException("Cannot read body." + response.raw());
        }
    }

}
