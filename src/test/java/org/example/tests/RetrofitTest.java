package org.example.tests;

import org.example.clients.RetrofitClient;
import org.example.models.Board;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import retrofit2.Response;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class RetrofitTest extends BaseTest {

    private RetrofitClient client;

    @BeforeClass
    public void setup() {
        client = new RetrofitClient(key, token);
    }

    @Test
    public void createBoardTest() throws IOException {
        Board expected = steps.BuildRequest("Retrofit Board");

        Response<Board> response = client.createBoard(expected);
        steps.CheckResponseIsValid(response, 200);

        Board actual = steps.PrepareActualResponse(response);
        steps.CheckActualVsExpectedResponses(actual, expected);
        assertThat(actual.getId()).isNotBlank();

        client.deleteBoard(actual.getId());
    }

    @Test
    public void getBoardTest() throws IOException {
        Board expected = steps.BuildRequest("Retrofit GET Board");
        Board created = steps.PrepareActualResponse(client.createBoard(expected));

        Response<Board> response = client.getBoard(created.getId());
        steps.CheckResponseIsValid(response, 200);

        Board actual = steps.PrepareActualResponse(response);
        steps.CheckActualVsExpectedResponses(actual, expected);

        client.deleteBoard(created.getId());
    }

    @Test
    public void updateBoardTest() throws IOException {
        Board created = steps.PrepareActualResponse(client.createBoard(steps.BuildRequest("Old Retrofit")));
        Board expected = steps.BuildRequest("New Retrofit");

        Response<Board> response = client.updateBoard(created.getId(), expected);
        steps.CheckResponseIsValid(response, 200);

        Board actual = steps.PrepareActualResponse(response);
        steps.CheckActualVsExpectedResponses(actual, expected);

        client.deleteBoard(created.getId());
    }

    @Test
    public void deleteBoardTest() throws IOException {
        Board created = steps.PrepareActualResponse(client.createBoard(steps.BuildRequest("Retrofit Delete")));

        Response<Board> deleteResponse = client.deleteBoard(created.getId());
        steps.CheckResponseIsValid(deleteResponse, 200);

        Response<Board> getResponse = client.getBoard(created.getId());
        steps.CheckResponseIsValid(getResponse, 404);
    }
}