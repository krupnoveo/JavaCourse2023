package edu.project4.models;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class AffineTransformationCoefficientsTest {
    @Test
    @DisplayName("Тест AffineTransformationCoefficients.getRandomCoefficients()")
    public void getRandomCoefficients_shouldWorkCorrectly() {
        AffineTransformationCoeffiecients coefs1 = AffineTransformationCoeffiecients.getRandomCoefficients();
        AffineTransformationCoeffiecients coefs2 = AffineTransformationCoeffiecients.getRandomCoefficients();
        assertThat(coefs1).isNotEqualTo(coefs2);
    }
}
