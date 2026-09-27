package org.example.clients;

import org.example.models.Board;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TrelloApi {

    @POST("boards")
    Call<Board> createBoard(
            @Query("key") String key,
            @Query("token") String token,
            @Query("name") String name
    );

    @GET("boards/{id}")
    Call<Board> getBoard(
            @Path("id") String boardId,
            @Query("key") String key,
            @Query("token") String token
    );

    @PUT("boards/{id}")
    Call<Board> updateBoard(
            @Path("id") String boardId,
            @Query("key") String key,
            @Query("token") String token,
            @Query("name") String name
    );

    @DELETE("boards/{id}")
    Call<Board> deleteBoard(
            @Path("id") String boardId,
            @Query("key") String key,
            @Query("token") String token
    );
}