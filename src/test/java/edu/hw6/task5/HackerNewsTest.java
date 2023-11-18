package edu.hw6.task5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Objects;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class HackerNewsTest {
    @Test
    @DisplayName("Тест HackerNews.hackerNewsTopStories()")
    public void hackerNewsTopStories_shouldReturnCorrectAnswer() {
        assertNotNull(HackerNews.hackerNewsTopStories());
    }

    @Test
    @DisplayName("Тест HackerNews.news()")
    public void news_shouldReturnCorrectAnswer() {
        assertNotNull(HackerNews.news(Objects.requireNonNull(HackerNews.hackerNewsTopStories())[0]));
    }
}
