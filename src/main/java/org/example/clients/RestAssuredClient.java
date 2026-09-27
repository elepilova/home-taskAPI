package org.example.clients;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.example.models.Board;

public class RestAssuredClient {

    private String key;
    private String token;

    public RestAssuredClient(String key, String token) {
        this.key = key;
        this.token = token;

        RestAssured.baseURI = "https://api.trello.com/1";
    }

    public Response createBoard(Board board) {

        return RestAssured
                .given()
                .queryParam("key", key)
                .queryParam("token", token)
                .queryParam("name", board.getName())
                .post("/boards");
    }

    public Response getBoard(String boardId) {

        return RestAssured
                .given()
                .queryParam("key", key)
                .queryParam("token", token)
                .get("/boards/" + boardId);
    }

    public Response updateBoard(String boardId, Board board) {

        return RestAssured
                .given()
                .queryParam("key", key)
                .queryParam("token", token)
                .queryParam("name", board.getName())
                .put("/boards/" + boardId);
    }

    public Response deleteBoard(String boardId) {

        return RestAssured
                .given()
                .queryParam("key", key)
                .queryParam("token", token)
                .delete("/boards/" + boardId);
    }
}