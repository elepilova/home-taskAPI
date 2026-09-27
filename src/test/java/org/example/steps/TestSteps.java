package org.example.steps;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.example.models.Board;
import org.testng.Assert;
import java.io.IOException;
import static org.assertj.core.api.Assertions.assertThat;

public class TestSteps {
    private final ObjectMapper objectMapper = new ObjectMapper();

    public Board BuildRequest(String name) {
        return new Board(name);
    }

    public Board PrepareExpectedResponse(String nameExpected){
        return new Board(nameExpected);
    }

    public void CheckResponseIsValid(Response response, int expectedCode) {
        Assert.assertEquals(response.getStatusCode(), expectedCode);
    }

    public void CheckResponseIsValid(retrofit2.Response<?> response, int expectedCode) {
        Assert.assertEquals(response.code(), expectedCode);
    }

    public void CheckResponseIsValid(CloseableHttpResponse response, int expectedCode) throws IOException {
        try {
            Assert.assertEquals(response.getStatusLine().getStatusCode(), expectedCode, "Apache: Code mismatch!");
        } finally {
            if (expectedCode == 404 || response.getEntity() == null) {
                response.close();
            }
        }
    }

    public Board PrepareActualResponse(Response response) {
        return response.as(Board.class);
    }

    public Board PrepareActualResponse(retrofit2.Response<Board> response) {
        return response.body();
    }

    public Board PrepareActualResponse(CloseableHttpResponse response) throws IOException {
        try {
            String json = EntityUtils.toString(response.getEntity());
            return objectMapper.readValue(json, Board.class);
        } finally {
            response.close();
        }
    }

    public void CheckActualVsExpectedResponses(Board actual, Board expected) {
        assertThat(actual.getName()).isEqualTo(expected.getName());
    }
}