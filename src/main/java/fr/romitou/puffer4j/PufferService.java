package fr.romitou.puffer4j;

import fr.romitou.puffer4j.objects.AuthRequest;
import fr.romitou.puffer4j.objects.PufferNode;
import fr.romitou.puffer4j.objects.PufferSession;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

import java.util.List;

public interface PufferService {

    @POST("/auth/login")
    Call<PufferSession> getSession(@Body AuthRequest authRequest);

    @GET("/nodes")
    Call<List<PufferNode>> getNodes();

}
