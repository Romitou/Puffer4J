package fr.romitou.puffer4j;

import fr.romitou.puffer4j.requests.PufferAuth;
import fr.romitou.puffer4j.responses.PufferNode;
import fr.romitou.puffer4j.responses.PufferServer;
import fr.romitou.puffer4j.responses.PufferSession;
import okhttp3.OkHttpClient;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Puffer4J {

    private final PufferService pufferService;
    private final PufferCookie pufferCookie;
    private final PufferAuth pufferAuth;
    private final Logger logger;
    private final URI uri;
    private LocalDateTime sessionExpiration;
    private List<String> userScopes;

    /**
     * Create the PufferPanel API client.
     *
     * @param uri        The URL of your panel
     * @param pufferAuth The PufferAuth used to login the user
     * @param level      The Java Logger level
     * @throws PufferException An exception can be thrown if the login is unsuccessful.
     */
    public Puffer4J(URI uri, PufferAuth pufferAuth, Level level) throws PufferException {

        // Store these informations in order to renew the token when needed.
        this.pufferAuth = pufferAuth;
        this.uri = uri;

        // Initialize the Java Logger
        this.logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
        this.logger.setLevel(level);

        // Initialize a default PufferCookie
        this.pufferCookie = new PufferCookie(null, getDomain());

        // Build the OkHttpClient used by Retrofit, including PufferCookie to authenticate the user
        OkHttpClient client = new OkHttpClient.Builder()
                .cookieJar(this.pufferCookie)
                .addInterceptor(new PufferHeader())
                .build();

        // Build the Retrofit client.
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(this.uri.toString())
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Store the PufferService to easily access in the methods.
        this.pufferService = retrofit.create(PufferService.class);

        // Try to authenticate the current user and get a session.
        this.authenticate();
    }

    public String getDomain() {
        String domain = this.uri.getHost();
        return domain.startsWith("www.") ? domain.substring(4) : domain;
    }

    public void checkRenewToken() throws PufferException {
        if (this.sessionExpiration == null || this.sessionExpiration.compareTo(LocalDateTime.now()) > 0) return;
        this.logger.info("The session has expired. Renewing...");
        authenticate();
    }

    public void authenticate() throws PufferException {
        String domain = getDomain();

        if (!(domain.equals("127.0.0.1") || domain.equals("localhost")) && !(this.uri.getScheme().equals("https")))
            this.logger.severe("You are using Puffer4J with a remote server in an insecure mode. You are strongly encouraged to use SSL by changing the Puffer4J constructor's useSSL parameter.");

        PufferSession pufferSession = getSession(this.pufferAuth);
        this.userScopes = pufferSession.getScopes();
        this.pufferCookie.setSession(pufferSession.getSession());

        // Define the session expiration in order to request a new session when needed.
        this.sessionExpiration = LocalDateTime.now().plusHours(2L);
    }

    public List<String> getUserScopes() {
        return this.userScopes;
    }

    public PufferNode createNode(PufferNode pufferNode) throws PufferException {
        checkRenewToken(); // Check if the token has expired, and renew it.
        Response<PufferNode> node;
        try {
            node = this.pufferService.createNode(pufferNode).execute();
        } catch (IOException e) {
            throw new PufferException("creating a node", e);
        }
        return parse(node);
    }

    public List<PufferNode> getNodes() throws PufferException {
        checkRenewToken(); // Check if the token has expired, and renew it.
        Response<List<PufferNode>> nodes;
        try {
            nodes = this.pufferService.getNodes().execute();
        } catch (IOException e) {
            throw new PufferException("getting nodes", e);
        }
        return parse(nodes);
    }

    public List<PufferServer> getServers() throws PufferException {
        checkRenewToken(); // Check if the token has expired, and renew it.
        Response<List<PufferServer>> servers;
        try {
            servers = this.pufferService.getServers().execute();
        } catch (IOException e) {
            throw new PufferException("getting nodes", e);
        }
        return parse(servers);
    }

    public PufferSession getSession(PufferAuth pufferAuth) throws PufferException {
        // Don't check the renew token...
        Response<PufferSession> session;
        try {
            session = this.pufferService.getSession(pufferAuth).execute();
        } catch (IOException e) {
            throw new PufferException("getting session", e);
        }
        return parse(session);
    }

    public <T> T parse(Response<T> response) throws PufferException {
        if (!response.isSuccessful())
            throw new PufferException(response);
        T responseBody = response.body();
        if (responseBody == null)
            throw new PufferException(response);
        return responseBody;
    }

}
