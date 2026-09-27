package org.example.tests;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.example.clients.ApacheHttpClient;
import org.example.models.Board;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ApacheHttpClientTest extends BaseTest {

    private ApacheHttpClient client;

    @BeforeClass
    public void setup() {
        client = new ApacheHttpClient(key, token);
    }

    @Test
    public void createBoardTest() throws Exception {
        Board expected = steps.BuildRequest("Apache Board");

        CloseableHttpResponse response = client.createBoard(expected);
        steps.CheckResponseIsValid(response, 200);

        Board actual = steps.PrepareActualResponse(response);
        steps.CheckActualVsExpectedResponses(actual, expected);
        assertThat(actual.getId()).isNotBlank();

        CloseableHttpResponse delResp = client.deleteBoard(actual.getId());
        delResp.close();
    }

    @Test
    public void getBoardTest() throws Exception {
        Board expected = steps.BuildRequest("Apache GET Board");
        Board created = steps.PrepareActualResponse(client.createBoard(expected));

        CloseableHttpResponse response = client.getBoard(created.getId());
        steps.CheckResponseIsValid(response, 200);

        Board actual = steps.PrepareActualResponse(response);
        steps.CheckActualVsExpectedResponses(actual, expected);

        CloseableHttpResponse delResp = client.deleteBoard(created.getId());
        delResp.close();
    }

    @Test
    public void updateBoardTest() throws Exception {
        Board created = steps.PrepareActualResponse(client.createBoard(steps.BuildRequest("Old Apache")));
        Board expected = steps.BuildRequest("New Apache");

        CloseableHttpResponse response = client.updateBoard(created.getId(), expected);
        steps.CheckResponseIsValid(response, 200);

        Board actual = steps.PrepareActualResponse(response);
        steps.CheckActualVsExpectedResponses(actual, expected);

        CloseableHttpResponse delResp = client.deleteBoard(created.getId());
        delResp.close();
    }

    @Test
    public void deleteBoardTest() throws Exception {
        Board created = steps.PrepareActualResponse(client.createBoard(steps.BuildRequest("Apache Delete")));

        CloseableHttpResponse deleteResponse = client.deleteBoard(created.getId());
        steps.CheckResponseIsValid(deleteResponse, 200);

        CloseableHttpResponse getResponse = client.getBoard(created.getId());
        steps.CheckResponseIsValid(getResponse, 404);
    }
}