package edu.hw3.task8;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class BackwardIteratorTest {
    @Test
    @DisplayName("Тест BackwardIterator")
    public void backwardIterator_shouldOutInReverseOrder() {
        List<Integer> list = List.of(1, 2, 3);
        BackwardIterator<Integer> iterator = new BackwardIterator<>(list);
        assertThat(iterator.next()).isEqualTo(3);
        assertThat(iterator.next()).isEqualTo(2);
        assertThat(iterator.next()).isEqualTo(1);
        assertThat(iterator.hasNext()).isFalse();
    }
}
