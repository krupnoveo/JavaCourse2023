package edu.hw1;

import edu.hw1.task8.KnightOnBoard;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class KnightOnBoardTest {
    @Test
    @DisplayName("Тест KnightOnBoard.canKillInOneMove(byte[][]) с корректными входными данными и ожидаемым результатом true")
    public void canKillInOneMove_shouldReturnTrue() {
        byte[][] board = {
            {0, 0, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 0, 0, 1},
            {0, 0, 0, 0, 1, 0, 0, 0}
        };
        boolean actual = KnightOnBoard.canKillInOneMove(board);
        assertTrue(actual);
    }
    @Test
    @DisplayName("Тест KnightOnBoard.canKillInOneMove(byte[][]) с корректными входными данными и ожидаемым результатом false")
    public void canKillInOneMove_shouldReturnFalse() {
        byte[][] board = {
            {0, 0, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 0, 0, 1},
            {0, 0, 0, 0, 1, 0, 0, 0}
        };
        boolean actual = KnightOnBoard.canKillInOneMove(board);
        assertFalse(actual);
    }
    @Test
    @DisplayName("Тест KnightOnBoard.canKillInOneMove(byte[][]) с некорректными входными данными")
    public void canKillInOneMove_shouldThrowException_1() {
        byte[][] board = {
            {0, 0, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 0, 0, 1},
            {0, 0, 0, 0, 1, 0, 0, 0}
        };
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            KnightOnBoard.canKillInOneMove(board);
        }, "IllegalArgumentException was expected");
        assertEquals("Nested array must not be null and its length must equal to 8", thrown.getMessage());
    }
    @Test
    @DisplayName("Тест KnightOnBoard.canKillInOneMove(byte[][]) с некорректными входными данными")
    public void canKillInOneMove_shouldThrowException_2() {
        byte[][] board = {
            {0, 0, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 0, 0, 1},
            {0, 0, 0, 0, 1, 0, 0, 0}
        };
        board[3] = null;
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            KnightOnBoard.canKillInOneMove(board);
        }, "IllegalArgumentException was expected");
        assertEquals("Nested array must not be null and its length must equal to 8", thrown.getMessage());
    }
    @Test
    @DisplayName("Тест KnightOnBoard.canKillInOneMove(byte[][]) с некорректными входными данными")
    public void canKillInOneMove_shouldThrowException_3() {
        byte[][] board = null;
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            KnightOnBoard.canKillInOneMove(board);
        }, "IllegalArgumentException was expected");
        assertEquals("Array must not be null and its length must be equal to 8", thrown.getMessage());
    }
}
