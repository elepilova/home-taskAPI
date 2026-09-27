package org.example.clients;

import org.example.models.Board;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;

public class RetrofitClient {

    private TrelloApi api;

    private String key;
    private String token;

    public RetrofitClient(String key, String token) {

        this.key = key;
        this.token = token;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.trello.com/1/")
                .addConverterFactory(
                        JacksonConverterFactory.create()
                )
                .build();

        api = retrofit.create(TrelloApi.class);
    }

    public Response<Board> createBoard(Board board)
            throws IOException {

        return api.createBoard(
                key,
                token,
                board.getName()
        ).execute();
    }

    public Response<Board> getBoard(String boardId)
            throws IOException {

        return api.getBoard(
                boardId,
                key,
                token
        ).execute();
    }

    public Response<Board> updateBoard(
            String boardId,
            Board board)
            throws IOException {

        return api.updateBoard(
                boardId,
                key,
                token,
                board.getName()
        ).execute();
    }

    public Response<Board> deleteBoard(
            String boardId)
            throws IOException {

        return api.deleteBoard(
                boardId,
                key,
                token
        ).execute();
    }
}