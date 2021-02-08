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
     * @param uri        The URL of your panel.
     * @param pufferAuth The PufferAuth used to login the user.
     * @param level      The Java Logger level.
     * @throws PufferException An exception can be thrown if there is an error.
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
        this.authenticate(this.pufferAuth);
    }

    /**
     * Get the domain of the given URI.
     *
     * @return The domain of the given URI.
     */
    private String getDomain() {
        String domain = this.uri.getHost();
        return domain.startsWith("www.") ? domain.substring(4) : domain;
    }

    /**
     * Check if the session has expired, and renew it if needed.
     *
     * @throws PufferException An exception can be thrown if there is an error.
     */
    public void checkRenewToken() throws PufferException {
        if (this.sessionExpiration == null || this.sessionExpiration.compareTo(LocalDateTime.now()) > 0) return;
        this.logger.info("The session has expired. Renewing...");
        authenticate(this.pufferAuth);
    }

    /**
     * Authenticate the Puffer4J client user or update it.
     * The user session will be updated as well as the scopes.
     * If you want simply login your user, this method is the best for you.
     *
     * @param pufferAuth The PufferAuth used to login the user.
     * @throws PufferException An exception can be thrown if there is an error.
     */
    public void authenticate(PufferAuth pufferAuth) throws PufferException {
        String domain = getDomain();

        if (!(domain.equals("127.0.0.1") || domain.equals("localhost")) && !(this.uri.getScheme().equals("https")))
            this.logger.severe("You are using Puffer4J with a remote server in an insecure mode. You are strongly encouraged to use SSL by changing the Puffer4J constructor's useSSL parameter.");

        PufferSession pufferSession = getSession(pufferAuth);
        this.userScopes = pufferSession.getScopes();
        this.pufferCookie.setSession(pufferSession.getSession());

        // Define the session expiration in order to request a new session when needed.
        this.sessionExpiration = LocalDateTime.now().plusHours(2L);
    }

    /**
     * Get a session for the given user.
     * If you want simply login your user, please use Puffer4J#authenticate instead.
     * @see Puffer4J#authenticate(PufferAuth)
     *
     * @param pufferAuth The PufferAuth used to login the user.
     * @return A PufferSession object containing the authorized user session.
     * @throws PufferException An exception can be thrown if there is an error.
     */
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

    /**
     * Get the current user PufferPanel scopes.
     *
     * @return The current user's PufferPanel scopes.
     */
    public List<String> getUserScopes() {
        return this.userScopes;
    }

    /**
     * Create a new PufferPanel node.
     *
     * @param pufferNode The node that will be created.
     * @return The created node.
     * @throws PufferException An exception can be thrown if there is an error.
     */
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

    /**
     * Get all the PufferPanel nodes.
     *
     * @return The PufferPanel nodes.
     * @throws PufferException An exception can be thrown if there is an error.
     */
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

    /**
     * Get all the PufferPanel servers.
     *
     * @return The PufferPanel servers.
     * @throws PufferException An exception can be thrown if there is an error.
     */
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

    /**
     * Parse the Retrofit response into the requested response object.
     *
     * @param response The Retrofit Response.
     * @param <T> The requested response object.
     * @return The requested response object.
     * @throws PufferException An exception can be thrown if there is an error.
     */
    private  <T> T parse(Response<T> response) throws PufferException {
        if (!response.isSuccessful())
            throw new PufferException(response);
        T responseBody = response.body();
        if (responseBody == null)
            throw new PufferException(response);
        return responseBody;
    }

}
