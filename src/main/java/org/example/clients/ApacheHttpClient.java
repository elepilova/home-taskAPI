package org.example.clients;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import org.example.models.Board;

import java.io.IOException;

public class ApacheHttpClient {

    private final CloseableHttpClient client;

    private final String key;
    private final String token;

    public ApacheHttpClient(String key, String token) {

        this.key = key;
        this.token = token;

        client = HttpClients.createDefault();
    }

    public CloseableHttpResponse createBoard(Board board)
            throws Exception {

        String url = new URIBuilder("https://api.trello.com/1/boards")
                .addParameter("key", key)
                .addParameter("token", token)
                .addParameter("name", board.getName())
                .build()
                .toString();

        HttpPost request = new HttpPost(url);

        return client.execute(request);
    }

    public CloseableHttpResponse getBoard(String boardId)
            throws Exception {

        String url = new URIBuilder(
                "https://api.trello.com/1/boards/" + boardId)
                .addParameter("key", key)
                .addParameter("token", token)
                .build()
                .toString();

        HttpGet request = new HttpGet(url);

        return client.execute(request);
    }

    public CloseableHttpResponse updateBoard(String boardId, Board board) throws Exception {
        String url = new URIBuilder("https://api.trello.com/1/boards/" + boardId)
                .addParameter("key", key)
                .addParameter("token", token)
                .addParameter("name", board.getName())
                .build()
                .toString();

        HttpPut request = new HttpPut(url);

        return client.execute(request);
    }

    public CloseableHttpResponse deleteBoard(
            String boardId)
            throws Exception {

        String url = new URIBuilder(
                "https://api.trello.com/1/boards/" + boardId)
                .addParameter("key", key)
                .addParameter("token", token)
                .build()
                .toString();

        HttpDelete request = new HttpDelete(url);

        return client.execute(request);
    }
}