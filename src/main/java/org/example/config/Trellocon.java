package org.example.config;

public final class Trellocon {

    private Trellocon() {}

    public static final String BASE_URL = "https://api.trello.com/1";

    public static String getKey() {
        String key = System.getenv("TRELLO_KEY");

        if (key == null || key.isEmpty()) {
            throw new IllegalStateException(
                    "TRELLO_KEY environment variable is not set"
            );
        }

        return key;
    }

    public static String getToken() {
        String token = System.getenv("TRELLO_TOKEN");

        if (token == null || token.isEmpty()) {
            throw new IllegalStateException(
                    "TRELLO_TOKEN environment variable is not set"
            );
        }

        return token;
    }
}