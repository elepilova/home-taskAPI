package org.example.tests;

import io.restassured.response.Response;
import org.example.models.Board;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RestAssuredTest extends BaseTest {

    @Test
    public void createBoardTest() {
        Board expected = steps.BuildRequest("Board");

        Response response = restAssuredClient.createBoard(expected);
        steps.CheckResponseIsValid(response,200);

        Board actual = steps.PrepareActualResponse(response);
        steps.CheckActualVsExpectedResponses(actual,expected);
        assertThat(actual.getId()).isNotBlank();
        restAssuredClient.deleteBoard(actual.getId());
    }

    @Test
    public void getBoardTest() {

        Board expected = steps.BuildRequest("Get Board");
        Board created = steps.PrepareActualResponse(restAssuredClient.createBoard(expected));

        Response response = restAssuredClient.getBoard(created.getId());
        steps.CheckResponseIsValid(response,200);

        Board actual = steps.PrepareActualResponse(response);
        steps.CheckActualVsExpectedResponses(actual,expected);

        restAssuredClient.deleteBoard(created.getId());

    }

    @Test
    public void updateBoardTest() {
        Board created = steps.PrepareActualResponse(restAssuredClient.createBoard(steps.BuildRequest("Old Name")));
        Board expected = steps.BuildRequest("New Name");

        Response response = restAssuredClient.updateBoard(created.getId(), expected);
        steps.CheckResponseIsValid(response, 200);

        Board actual = steps.PrepareActualResponse(response);
        steps.CheckActualVsExpectedResponses(actual, expected);

        restAssuredClient.deleteBoard(created.getId());
    }

    @Test
    public void deleteBoardTest() {

        Board created = steps.PrepareActualResponse(restAssuredClient.createBoard(steps.BuildRequest("Delete")));

        Response deleteResponse = restAssuredClient.deleteBoard(created.getId());
        steps.CheckResponseIsValid(deleteResponse, 200);

        Response getResponse = restAssuredClient.getBoard(created.getId());
        steps.CheckResponseIsValid(getResponse, 404);

    }
}