package ma.enset.chatapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ChatService {
    @GET("/get")
    Call<ChatResponse> sendMessage(
            @Query("bid") String bid,
            @Query("key") String key,
            @Query("uid") String uid,
            @Query("msg") String message
    );
}

