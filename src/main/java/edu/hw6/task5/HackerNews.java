package edu.hw6.task5;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class HackerNews {
    private static final int SECONDS_BEFORE_TIMEOUT = 30;
    private static final String TOP_STORIES_ID_REQUESTED_RESOURCE = "https://hacker-news.firebaseio.com/v0/topstories.json";
    private static final Pattern FORMAT_OF_RESPONSE_BY_ID = Pattern.compile(".*\"title\":\"(.*?)\".*");

    private HackerNews() {}

    public static long[] hackerNewsTopStories() {
        try (HttpClient client = HttpClient.newHttpClient()) {
            HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(TOP_STORIES_ID_REQUESTED_RESOURCE))
                .build();
            String responseBody = client.send(request, HttpResponse.BodyHandlers.ofString()).body();
            if (!responseBody.startsWith("[")) {
                return null;
            }
            return Arrays.stream(responseBody.substring(1, responseBody.length() - 1)
                .split(","))
                .mapToLong(Long::parseLong)
                .toArray();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String news(long id) {
        try (HttpClient client = HttpClient.newHttpClient()) {
            String resource = TOP_STORIES_ID_REQUESTED_RESOURCE;
            HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(
                        resource.substring(0, resource.indexOf("top")) + "item/" + id + ".json"))
                .build();
            String response = client.send(request, HttpResponse.BodyHandlers.ofString()).body();
            Matcher matcher = FORMAT_OF_RESPONSE_BY_ID.matcher(response);
            if (matcher.find()) {
                return matcher.group(1);
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
