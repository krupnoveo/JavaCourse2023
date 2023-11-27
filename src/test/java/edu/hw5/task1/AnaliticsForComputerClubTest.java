package edu.hw5.task1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.Duration;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AnaliticsForComputerClubTest {
    @Test
    @DisplayName("Тест AnaliticsForComputerClub.calculateAverageDurationOfSession() с корректными данными")
    public void calculateAverageDurationOfSession_shouldReturnCorrectAnswer() {
        List<String> data = List.of("2022-03-12, 20:20 - 2022-03-12, 23:50", "2022-04-01, 21:30 - 2022-04-02, 01:20");
        Duration actual = AnaliticsForComputerClub.calculateAverageDurationOfSession(data);
        Duration expected = Duration.parse("PT3H40M");
        assertThat(actual).isEqualTo(expected);
    }
    @Test
    @DisplayName("Тест AnaliticsForComputerClub.calculateAverageDurationOfSession() с некорректными данными (старт позже начала, неправильный формат даты)")
    public void calculateAverageDurationOfSession_shouldThrowIllegalArgumentException() {
        List<String> data1 = List.of("2022-03-12, 20:20 - 2022-03-11, 23:50");
        IllegalArgumentException thrown1 = assertThrows(IllegalArgumentException.class, () ->{
            AnaliticsForComputerClub.calculateAverageDurationOfSession(data1);
        });
        assertThat(thrown1.getMessage()).isEqualTo("Start of session is after the end of it: 2022-03-12, 20:20 - 2022-03-11, 23:50");

        List<String> data2 = List.of("2022/03/12, 20:20 - 2022-03-12, 23:50");
        IllegalArgumentException thrown2 = assertThrows(IllegalArgumentException.class, () -> {
            AnaliticsForComputerClub.calculateAverageDurationOfSession(data2);
        });
        assertThat(thrown2.getMessage()).isEqualTo("Incorrect format of input: 2022/03/12, 20:20 - 2022-03-12, 23:50");

        List<String> data3 = List.of("2022-02-31, 20:20 - 2022-03-12, 23:50");
        IllegalArgumentException thrown3 = assertThrows(IllegalArgumentException.class, () -> {
            AnaliticsForComputerClub.calculateAverageDurationOfSession(data3);
        });
        assertThat(thrown3.getMessage()).isEqualTo("Incorrect time or date in input data: 2022-02-31, 20:20 - 2022-03-12, 23:50");
    }
}
