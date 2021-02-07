package fr.romitou.puffer4j;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

import java.util.ArrayList;
import java.util.List;

public class PufferCookie implements CookieJar {

    private final String domain;
    private String session;

    public PufferCookie(String session, String domain) {
        this.session = session;
        this.domain = domain;
    }

    public void setSession(String session) {
        this.session = session;
    }

    @Override
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
    }

    @Override
    public List<Cookie> loadForRequest(HttpUrl url) {
        final ArrayList<Cookie> cookies = new ArrayList<>(1);
        if (session != null)
            cookies.add(new Cookie.Builder()
                    .domain(domain)
                    .path("/")
                    .name("puffer_auth")
                    .value(session)
                    .httpOnly()
                    .secure()
                    .build()
            );
        return cookies;
    }
}
