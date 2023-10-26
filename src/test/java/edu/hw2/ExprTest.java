package edu.hw2;

import edu.hw2.task1.Expr;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExprTest {
    @DisplayName("Тест метода evaluate у Expr.Constant")
    @ParameterizedTest(name = "{index} - для числа {0} был возвращён результат {0} - верно")
    @ValueSource(doubles = {3, 1.2})
    public void evaluate_fromConstant_shouldReturnCorrectAnswer(double val) {
        Expr constant = new Expr.Constant(val);
        assertEquals(constant.evaluate(), val);
    }

    @DisplayName("Тест метода evaluate у Expr.Negate")
    @ParameterizedTest(name = "{index} - для числа {0} был возвращён результат -{0} - верно")
    @ValueSource(doubles = {4, 10.1})
    public void evaluate_fromNegate_shouldReturnCorrectAnswer(double val) {
        Expr negate1 = new Expr.Negate(new Expr.Constant(val));
        Expr negate2 = new Expr.Negate(val);
        assertEquals(negate1.evaluate(), -val);
        assertEquals(negate2.evaluate(), -val);
    }

    @DisplayName("Тест метода evaluate у Expr.Exponent")
    @ParameterizedTest(name = "{index} - для основания {0} и степени {1} был возвращён результат {2} - верно")
    @CsvSource({
        "3, 2, 9",
        "1.5, 3, 3.375"
    })
    public void evaluate_fromExponent_shouldReturnCorrectAnswer(double base, double power, double res) {
        Expr exp1 = new Expr.Exponent(base, power);
        Expr exp2 = new Expr.Exponent(new Expr.Constant(base), power);
        Expr exp3 = new Expr.Exponent(base, new Expr.Constant(power));
        Expr exp4 = new Expr.Exponent(new Expr.Constant(base), new Expr.Constant(power));
        assertEquals(exp1.evaluate(), res);
        assertEquals(exp2.evaluate(), res);
        assertEquals(exp3.evaluate(), res);
        assertEquals(exp4.evaluate(), res);
    }

    @DisplayName("Тест метода evaluate у Expr.Addition")
    @ParameterizedTest(name = "{index} - для первого слагаемого равного {0} и для второго слагаемого равного {1} был возвращён результат {2} - верно")
    @CsvSource({
        "3, 2, 5",
        "2.1, 3.1, 5.2"
    })
    public void evaluate_fromAddition_shouldReturnCorrectAnswer(double val1, double val2, double res) {
        Expr add1 = new Expr.Addition(val1, val2);
        Expr add2 = new Expr.Addition(new Expr.Constant(val1), val2);
        Expr add3 = new Expr.Addition(val1, new Expr.Constant(val2));
        Expr add4 = new Expr.Addition(new Expr.Constant(val1), new Expr.Constant(val2));
        assertEquals(add1.evaluate(), res);
        assertEquals(add2.evaluate(), res);
        assertEquals(add3.evaluate(), res);
        assertEquals(add4.evaluate(), res);
    }

    @DisplayName("Тест метода evaluate у Expr.Multiplication")
    @ParameterizedTest(name = "{index} - для первого множителя равного {0} и для второго множителя равного {1} был возвращён результат {2} - верно")
    @CsvSource({
        "3, 2, 6",
        "2.3, 4.2, 9.66"
    })
    public void evaluate_fromMultiplication_shouldReturnCorrectAnswer(double val1, double val2, double res) {
        Expr mult1 = new Expr.Multiplication(val1, val2);
        Expr mult2 = new Expr.Multiplication(new Expr.Constant(val1), val2);
        Expr mult3 = new Expr.Multiplication(val1, new Expr.Constant(val2));
        Expr mult4 = new Expr.Multiplication(new Expr.Constant(val1), new Expr.Constant(val2));
        assertEquals(mult1.evaluate(), res);
        assertEquals(mult2.evaluate(), res);
        assertEquals(mult3.evaluate(), res);
        assertEquals(mult4.evaluate(), res);
    }
    @Test
    @DisplayName("Тест взаимодействия разных record из Expr между собой")
    public void evaluate_generalTest_shouldReturnCorrectAnswer() {
        var two = new Expr.Constant(2);
        var four = new Expr.Constant(4);
        var negOne = new Expr.Negate(new Expr.Constant(1));
        var sumTwoFour = new Expr.Addition(two, four);
        var mult = new Expr.Multiplication(sumTwoFour, negOne);
        var exp = new Expr.Exponent(mult, 2);
        var res = new Expr.Addition(exp, new Expr.Constant(1));
        assertEquals(res.evaluate(), 37);
    }
}
