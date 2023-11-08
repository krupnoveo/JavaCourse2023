package edu.hw4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static edu.hw4.ValidationError.TypeOfError.AGE;
import static edu.hw4.ValidationError.TypeOfError.HEIGHT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class AnimalTest {
    @Test
    @DisplayName("Тест Animal.ascendingSortByHeight()")
    public void ascendingSortByHeight_shouldReturnCorrectAnswer() {
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("a", Animal.Type.CAT, Animal.Sex.M, 2, 10, 2, true));
        animals.add(new Animal("a", Animal.Type.CAT, Animal.Sex.M, 2, 9, 2, true));
        animals.add(new Animal("a", Animal.Type.CAT, Animal.Sex.M, 2, 8, 2, true));
        List<Animal> expected = new ArrayList<>(animals);
        expected.sort(Comparator.comparingInt(Animal::height));
        assertEquals(expected, Animal.ascendingSortByHeight(animals));
    }
    @Test
    @DisplayName("Тест Animal.descendingSortByWeight()")
    public void descendingSortByWeight_shouldReturnCorrectAnswer() {
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("a", Animal.Type.CAT, Animal.Sex.M, 2, 10, 2, true));
        animals.add(new Animal("a", Animal.Type.CAT, Animal.Sex.M, 2, 9, 2, true));
        animals.add(new Animal("a", Animal.Type.CAT, Animal.Sex.M, 2, 8, 2, true));
        List<Animal> expected = new ArrayList<>(animals);
        expected.sort(Comparator.comparingInt(Animal::weight).reversed());
        expected.remove(2);
        assertEquals(expected, Animal.descendingSortByWeight(animals, 2));
    }
    @Test
    @DisplayName("Тест Animal.countEachType()")
    public void countEachType_shouldReturnCorrectAnswer() {
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("a", Animal.Type.CAT, Animal.Sex.M, 2, 10, 2, true));
        animals.add(new Animal("a", Animal.Type.DOG, Animal.Sex.M, 2, 9, 2, true));
        animals.add(new Animal("a", Animal.Type.CAT, Animal.Sex.M, 2, 8, 2, true));
        Map<Animal.Type, Long> expected = new HashMap<>();
        expected.put(Animal.Type.CAT, 2L);
        expected.put(Animal.Type.DOG, 1L);
        assertEquals(expected, Animal.countEachType(animals));
    }
    @Test
    @DisplayName("Тест Animal.getAnimalWithLongestName()")
    public void getAnimalWithLongestName_shouldReturnCorrectAnswer() {
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("a", Animal.Type.CAT, Animal.Sex.M, 2, 10, 2, true));
        animals.add(new Animal("abc", Animal.Type.DOG, Animal.Sex.M, 2, 9, 2, true));
        animals.add(new Animal("ac", Animal.Type.CAT, Animal.Sex.M, 2, 8, 2, true));
        Animal expected = animals.get(1);
        assertEquals(expected, Animal.getAnimalWithLongestName(animals));
    }
    @Test
    @DisplayName("Тест Animal.getMostPopularGender()")
    public void getMostPopularGender_shouldReturnCorrectAnswer() {
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("a", Animal.Type.CAT, Animal.Sex.M, 2, 10, 2, true));
        animals.add(new Animal("a", Animal.Type.DOG, Animal.Sex.F, 2, 9, 2, true));
        animals.add(new Animal("a", Animal.Type.CAT, Animal.Sex.M, 2, 8, 2, true));
        Animal.Sex expected = Animal.Sex.M;
        assertEquals(expected, Animal.getMostPopularGender(animals));
    }
    @Test
    @DisplayName("Тест Animal.getHeaviestAnimalOfEachType()")
    public void getHeaviestAnimalOfEachType_shouldReturnCorrectAnswer() {
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("a", Animal.Type.CAT, Animal.Sex.M, 2, 10, 4, true));
        animals.add(new Animal("abc", Animal.Type.DOG, Animal.Sex.M, 2, 9, 2, true));
        animals.add(new Animal("ac", Animal.Type.CAT, Animal.Sex.M, 2, 8, 2, true));
        animals.add(new Animal("a", Animal.Type.DOG, Animal.Sex.F, 2, 11, 5, true));
        Map<Animal.Type, Animal> expected = new HashMap<>();
        expected.put(Animal.Type.CAT, animals.get(0));
        expected.put(Animal.Type.DOG, animals.get(3));
        assertEquals(expected, Animal.getHeaviestAnimalOfEachType(animals));
    }
    @Test
    @DisplayName("Тест Animal.getTheOldestAnimalWithNumberK()")
    public void getTheOldestAnimalWithNumberK_shouldReturnCorrectAnswer() {
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("a", Animal.Type.CAT, Animal.Sex.M, 5, 10, 4, true));
        animals.add(new Animal("abc", Animal.Type.DOG, Animal.Sex.M, 4, 9, 2, true));
        animals.add(new Animal("ac", Animal.Type.CAT, Animal.Sex.M, 3, 8, 2, true));
        animals.add(new Animal("a", Animal.Type.DOG, Animal.Sex.F, 2, 11, 5, true));
        Animal expected = animals.get(2);
        assertEquals(expected, Animal.getTheOldestAnimalWithNumberK(animals, 3));
    }
    @Test
    @DisplayName("Тест Animal.getHeaviestAnimalAmongSmallerThanK()")
    public void getHeaviestAnimalAmongSmallerThanK_shouldReturnCorrectAnswer() {
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("a", Animal.Type.CAT, Animal.Sex.M, 5, 10, 4, true));
        animals.add(new Animal("abc", Animal.Type.DOG, Animal.Sex.M, 4, 9, 3, true));
        animals.add(new Animal("ac", Animal.Type.CAT, Animal.Sex.M, 3, 8, 2, true));
        animals.add(new Animal("a", Animal.Type.DOG, Animal.Sex.F, 2, 11, 5, true));
        Optional<Animal> expected = Optional.ofNullable(animals.get(1));
        assertEquals(expected, Animal.getHeaviestAnimalAmongSmallerThanK(animals, 10));
    }
    @Test
    @DisplayName("Тест Animal.countAllPawsOfAnimals()")
    public void countAllPawsOfAnimals_shouldReturnCorrectAnswer() {
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("a", Animal.Type.CAT, Animal.Sex.M, 5, 10, 4, true));
        animals.add(new Animal("abc", Animal.Type.DOG, Animal.Sex.M, 4, 9, 3, true));
        animals.add(new Animal("ac", Animal.Type.SPIDER, Animal.Sex.M, 3, 8, 2, true));
        animals.add(new Animal("a", Animal.Type.DOG, Animal.Sex.F, 2, 11, 5, true));
        assertEquals(20, Animal.countAllPawsOfAnimals(animals));
    }
    @Test
    @DisplayName("Тест Animal.animalListWhichAgeNotEqualsPawsCount()")
    public void animalListWhichAgeNotEqualsPawsCount_shouldReturnCorrectAnswer() {
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("a", Animal.Type.CAT, Animal.Sex.M, 5, 10, 4, true));
        animals.add(new Animal("abc", Animal.Type.DOG, Animal.Sex.M, 4, 9, 3, true));
        animals.add(new Animal("ac", Animal.Type.SPIDER, Animal.Sex.M, 3, 8, 2, true));
        animals.add(new Animal("a", Animal.Type.DOG, Animal.Sex.F, 2, 11, 5, true));
        List<Animal> expected = new ArrayList<>(animals);
        expected.remove(1);
        assertEquals(expected, Animal.animalListWhichAgeNotEqualsPawsCount(animals));
    }
    @Test
    @DisplayName("Тест Animal.animalListWhichCanBite()")
    public void animalListWhichCanBite_shouldReturnCorrectAnswer() {
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("a", Animal.Type.CAT, Animal.Sex.M, 5, 101, 4, true));
        animals.add(new Animal("abc", Animal.Type.DOG, Animal.Sex.M, 4, 9, 3, false));
        animals.add(new Animal("ac", Animal.Type.SPIDER, Animal.Sex.M, 3, 8, 2, false));
        animals.add(new Animal("a", Animal.Type.DOG, Animal.Sex.F, 2, 11, 5, true));
        List<Animal> expected = new ArrayList<>();
        expected.add(animals.get(0));
        assertEquals(expected, Animal.animalListWhichCanBite(animals));
    }
    @Test
    @DisplayName("Тест Animal.countAnimalsWhichWeightMoreThanHeight()")
    public void countAnimalsWhichWeightMoreThanHeight_shouldReturnCorrectAnswer() {
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("a", Animal.Type.CAT, Animal.Sex.M, 5, 101, 400, true));
        animals.add(new Animal("abc", Animal.Type.DOG, Animal.Sex.M, 4, 9, 30, false));
        animals.add(new Animal("ac", Animal.Type.SPIDER, Animal.Sex.M, 3, 8, 2, false));
        animals.add(new Animal("a", Animal.Type.DOG, Animal.Sex.F, 2, 11, 5, true));
        assertEquals(2, Animal.countAnimalsWhichWeightMoreThanHeight(animals));
    }
    @Test
    @DisplayName("Тест Animal.animalListWhichNamesLongerThanTwoLetters()")
    public void animalListWhichNamesLongerThanTwoLetters_shouldReturnCorrectAnswer() {
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("a a", Animal.Type.CAT, Animal.Sex.M, 5, 101, 400, true));
        animals.add(new Animal("abc d f", Animal.Type.DOG, Animal.Sex.M, 4, 9, 30, false));
        animals.add(new Animal("ac d c", Animal.Type.SPIDER, Animal.Sex.M, 3, 8, 20, false));
        animals.add(new Animal("a", Animal.Type.DOG, Animal.Sex.F, 2, 11, 5, true));
        List<Animal> expected = new ArrayList<>();
        expected.add(animals.get(1));
        expected.add(animals.get(2));
        assertEquals(expected, Animal.animalListWhichNamesLongerThanTwoLetters(animals));
    }
    @Test
    @DisplayName("Тест Animal.isPresentDogWhichHeightIsMoreThanK()")
    public void isPresentDogWhichHeightIsMoreThanK_shouldReturnCorrectAnswer() {
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("a a", Animal.Type.CAT, Animal.Sex.M, 5, 101, 400, true));
        animals.add(new Animal("abc d f", Animal.Type.DOG, Animal.Sex.M, 4, 9, 30, false));
        animals.add(new Animal("ac d c", Animal.Type.SPIDER, Animal.Sex.M, 3, 8, 20, false));
        animals.add(new Animal("a", Animal.Type.DOG, Animal.Sex.F, 2, 11, 5, true));
        assertTrue(Animal.isPresentDogWhichHeightIsMoreThanK(animals, 9));
    }
    @Test
    @DisplayName("Тест Animal.totalWeightOfEachType()")
    public void totalWeightOfEachType_shouldReturnCorrectAnswer() {
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("a a", Animal.Type.CAT, Animal.Sex.M, 5, 101, 400, true));
        animals.add(new Animal("abc d f", Animal.Type.DOG, Animal.Sex.M, 4, 9, 30, false));
        animals.add(new Animal("ac d c", Animal.Type.SPIDER, Animal.Sex.M, 3, 8, 20, false));
        animals.add(new Animal("a", Animal.Type.DOG, Animal.Sex.F, 2, 11, 5, true));
        Map<Animal.Type, Integer> expected = new HashMap<>();
        expected.put(Animal.Type.CAT, 400);
        expected.put(Animal.Type.DOG, 30);
        assertEquals(expected, Animal.totalWeightOfEachType(animals, 4, 6));
    }
    @Test
    @DisplayName("Тест Animal.sortedAnimalListByTypeThanGenderThanName()")
    public void sortedAnimalListByTypeThanGenderThanName_shouldReturnCorrectAnswer() {
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("a a", Animal.Type.CAT, Animal.Sex.M, 5, 101, 400, true));
        animals.add(new Animal("abc d f", Animal.Type.DOG, Animal.Sex.M, 4, 9, 30, false));
        animals.add(new Animal("a a", Animal.Type.CAT, Animal.Sex.F, 3, 8, 20, false));
        animals.add(new Animal("a", Animal.Type.DOG, Animal.Sex.F, 2, 11, 5, true));
        List<Animal> expected = new ArrayList<>();
        expected.add(animals.get(0));
        expected.add(animals.get(2));
        expected.add(animals.get(1));
        expected.add(animals.get(3));
        assertEquals(expected, Animal.sortedAnimalListByTypeThanGenderThanName(animals));
    }
    @Test
    @DisplayName("Тест Animal.isSpidersBiteMoreOftenThanDogs()")
    public void isSpidersBiteMoreOftenThanDogs_shouldReturnCorrectAnswer() {
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("a a", Animal.Type.DOG, Animal.Sex.M, 5, 101, 400, true));
        animals.add(new Animal("abc d f", Animal.Type.DOG, Animal.Sex.M, 4, 9, 30, true));
        animals.add(new Animal("a a", Animal.Type.SPIDER, Animal.Sex.F, 3, 8, 20, false));
        animals.add(new Animal("a", Animal.Type.SPIDER, Animal.Sex.F, 2, 11, 5, true));
        animals.add(new Animal("a", Animal.Type.SPIDER, Animal.Sex.F, 2, 11, 5, true));
        assertFalse(Animal.isSpidersBiteMoreOftenThanDogs(animals));
    }
    @Test
    @DisplayName("Тест Animal.getTheHeaviestFish()")
    public void getTheHeaviestFish_shouldReturnCorrectAnswer() {
        List<Animal> list1 = new ArrayList<>();
        list1.add(new Animal("a a", Animal.Type.FISH, Animal.Sex.M, 5, 101, 400, true));
        list1.add(new Animal("a a", Animal.Type.DOG, Animal.Sex.M, 5, 101, 300, true));
        List<Animal> list2 = new ArrayList<>();
        list2.add(new Animal("a a", Animal.Type.DOG, Animal.Sex.M, 5, 101, 500, true));
        list2.add(new Animal("a a", Animal.Type.FISH, Animal.Sex.M, 5, 101, 500, true));
        Animal expected = list2.get(1);
        assertEquals(expected, Animal.getTheHeaviestFish(list1, list2));
    }
    @Test
    @DisplayName("Тест Animal.checkAnimalsForErrors()")
    public void checkAnimalsForErrors_shouldReturnCorrectAnswer() {
        List<Animal> list = new ArrayList<>();
        list.add(new Animal("a", Animal.Type.DOG, Animal.Sex.F, -1, -100, -2, true));
        list.add(new Animal(null, Animal.Type.CAT, Animal.Sex.M, 0, 2, -1, true));
        Map<String, Set<ValidationError>> expected = new HashMap<>();
        Set<ValidationError> errors1 = new HashSet<>();
        Set<ValidationError> errors2 = new HashSet<>();
        errors1.add(new ValidationError(AGE, AGE.getText()));
        errors1.add(new ValidationError(HEIGHT, HEIGHT.getText()));
        errors1.add(new ValidationError(ValidationError.TypeOfError.WEIGHT, ValidationError.TypeOfError.WEIGHT.getText()));
        errors2.add(new ValidationError(ValidationError.TypeOfError.NAME, ValidationError.TypeOfError.NAME.getText()));
        errors2.add(new ValidationError(ValidationError.TypeOfError.WEIGHT, ValidationError.TypeOfError.WEIGHT.getText()));
        expected.put(list.get(0).name(), errors1);
        expected.put(list.get(1).name(), errors2);
        assertEquals(expected, Animal.checkAnimalsForErrors(list));
    }
    @Test
    @DisplayName("Тест Animal.checkAnimalsForErrorsWithMessages()")
    public void checkAnimalsForErrorsWithMessages_shouldReturnCorrectAnswer() {
        List<Animal> list = new ArrayList<>();
        list.add(new Animal("a", Animal.Type.DOG, Animal.Sex.F, -1, 100, -2, true));
        list.add(new Animal(null, Animal.Type.CAT, Animal.Sex.M, 0, 2, -1, true));
        Map<String, String> actual = Animal.checkAnimalsForErrorsWithMessages(list);
        assertThat(actual)
            .hasEntrySatisfying("a", val -> assertThat(val).isIn("WEIGHT, AGE", "AGE, WEIGHT"));
        assertThat(actual)
            .hasEntrySatisfying(null, val -> assertThat(val).isIn("WEIGHT, NAME", "NAME, WEIGHT"));

    }
}
