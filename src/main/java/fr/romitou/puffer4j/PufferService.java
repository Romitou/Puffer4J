package fr.romitou.puffer4j;

import fr.romitou.puffer4j.requests.PufferAuth;
import fr.romitou.puffer4j.requests.PufferSelfUpdate;
import fr.romitou.puffer4j.responses.*;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface PufferService {

    // -- Authentication --

    @POST("/auth/login")
    Call<PufferSession> getSession(@Body PufferAuth pufferAuth);

    @GET("/api/self")
    Call<PufferUser> getSelf();

    @POST("/api/self")
    Call<PufferUser> updateSelf(@Body PufferSelfUpdate pufferSelfUpdate);


    // -- Nodes --

    @GET("/api/nodes")
    Call<List<PufferNode>> getNodes();

    @GET("/api/nodes/{id}")
    Call<PufferNode> getNode(@Path("id") String nodeId);

    @POST("/api/nodes")
    Call<PufferNode> createNode(@Body PufferNode pufferNode);

    @PUT("/api/nodes/{id}")
    Call<Void> editNode(@Path("id") String nodeId, @Body PufferNode pufferNode);

    @DELETE("/api/nodes/{id}")
    Call<Void> deleteNode(@Path("id") String nodeId);

    @GET("/api/nodes/{id}/deploy")
    Call<PufferDeploy> getNodeDeploy(@Path("id") String nodeId);


    // -- Servers --

    @GET("/api/servers")
    Call<PufferPagedServers> getServers();

    @GET("/api/servers/{id}")
    Call<PufferSingleServer> getServer(@Path("id") String id);

}
