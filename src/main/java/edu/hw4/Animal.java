package edu.hw4;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collectors;

public record Animal(
                     String name,
                     Type type,
                     Sex sex,
                     int age,
                     int height,
                     int weight,
                     boolean bites
) {
    public enum Type {
        CAT, DOG, BIRD, FISH, SPIDER
    }

    public enum Sex {
        M, F
    }

    @SuppressWarnings("checkstyle:MagicNumber")
    public int paws() {
        return switch (type) {
            case CAT, DOG -> 4;
            case BIRD -> 2;
            case FISH -> 0;
            case SPIDER -> 8;
        };
    }

    public static List<Animal> ascendingSortByHeight(List<Animal> animals) {
        return animals.stream()
            .sorted(Comparator.comparingInt(Animal::height))
            .toList();
    }

    public static List<Animal> descendingSortByWeight(List<Animal> animals, int k) {
        return animals.stream()
            .sorted((a, b) -> b.weight() - a.weight())
            .limit(k)
            .toList();
    }

    public static Map<Animal.Type, Long> countEachType(List<Animal> animals) {
        return animals.stream()
            .collect(Collectors.groupingBy(Animal::type, Collectors.counting()));
    }

    public static Animal getAnimalWithLongestName(List<Animal> animals) {
        return animals.stream()
            .max(Comparator.comparing(animal -> animal.name().length()))
            .orElse(null);
    }

    public static Animal.Sex getMostPopularGender(List<Animal> animals) {
        return animals.stream()
            .collect(Collectors.groupingBy(Animal::sex, Collectors.counting()))
            .entrySet()
            .stream()
            .max(Comparator.comparingLong(Map.Entry::getValue))
            .map(Map.Entry::getKey)
            .orElse(null);
    }

    public static Map<Animal.Type, Animal> getHeaviestAnimalOfEachType(List<Animal> animals) {
        return animals.stream()
            .collect(Collectors.groupingBy(Animal::type, Collectors.collectingAndThen(
                Collectors.maxBy(Comparator.comparingLong(Animal::weight)),
                Optional::orElseThrow
            )));

    }

    public static Animal getTheOldestAnimalWithNumberK(List<Animal> animals, int k) {
        return animals.stream()
            .sorted(Comparator.comparingLong(Animal::age).reversed())
            .limit(k)
            .min(Comparator.comparingLong(Animal::age))
            .orElse(null);
    }

    public static Optional<Animal> getHeaviestAnimalAmongSmallerThanK(List<Animal> animals, int k) {
        return animals.stream()
                .filter(animal -> animal.height() < k)
                .max(Comparator.comparingLong(Animal::weight));
    }

    public static Integer countAllPawsOfAnimals(List<Animal> animals) {
        return animals.stream()
            .mapToInt(Animal::paws)
            .sum();
    }

    public static List<Animal> animalListWhichAgeNotEqualsPawsCount(List<Animal> animals) {
        return animals.stream()
            .filter(animal -> animal.age() != animal.paws())
            .toList();
    }

    public static List<Animal> animalListWhichCanBite(List<Animal> animals) {
        final int minHeight = 100;
        return animals.stream()
            .filter(animal -> animal.bites() && animal.height() > minHeight)
            .toList();
    }

    public static Long countAnimalsWhichWeightMoreThanHeight(List<Animal> animals) {
        return animals.stream()
            .filter(animal -> animal.weight() > animal.height())
            .count();
    }

    public static List<Animal> animalListWhichNamesLongerThanTwoLetters(List<Animal> animals) {
        return animals.stream()
            .filter(animal -> animal.name().split(" ").length > 2)
            .toList();
    }

    public static boolean isPresentDogWhichHeightIsMoreThanK(List<Animal> animals, int k) {
        return animals.stream()
            .anyMatch(animal -> animal.type() == Type.DOG && animal.height() > k);
    }

    public static Map<Animal.Type, Integer> totalWeightOfEachType(List<Animal> animals, int l, int k) {
        return animals.stream()
            .filter(animal -> animal.age() >= l && animal.age() < k)
            .collect(Collectors.groupingBy(Animal::type, Collectors.summingInt(Animal::weight)));
    }

    public static List<Animal> sortedAnimalListByTypeThanGenderThanName(List<Animal> animals) {
        return animals.stream()
            .sorted(Comparator.comparing(Animal::type)
                .thenComparing(Animal::sex)
                .thenComparing(Animal::name))
            .toList();
    }

    public static boolean isSpidersBiteMoreOftenThanDogs(List<Animal> animals) {
        ToDoubleFunction<Animal> bites = animal -> (animal.bites() ? 1 : 0);
        return animals.stream()
            .filter(animal -> animal.type() == Type.DOG || animal.type() == Type.SPIDER)
            .collect(Collectors.collectingAndThen(
                Collectors.groupingBy(Animal::type, Collectors.averagingDouble(bites)),
                result -> result.get(Type.SPIDER) > result.get(Type.DOG)
            ));
    }

    @SafeVarargs
    public static Animal getTheHeaviestFish(List<Animal>... animals) {
        return Arrays.stream(animals).flatMap(List::stream)
            .filter(animal -> animal.type() == Type.FISH)
            .max(Comparator.comparing(Animal::weight))
            .orElse(null);

    }

    public static Map<String, Set<ValidationError>> checkAnimalsForErrors(List<Animal> animals) {
        return animals.stream()
            .collect(Collectors.collectingAndThen(
                Collectors.toMap(Animal::name, Animal::checkAnimalData),
                map -> {
                    map.values().removeIf(Set::isEmpty);
                    return map;
                }
            ));
    }

    public static Map<String, String> checkAnimalsForErrorsWithMessages(List<Animal> animals) {
        return animals.stream()
            .collect(Collectors.collectingAndThen(
                Collectors.toMap(Animal::name, animal -> checkAnimalData(animal).stream().map(a -> a.error().toString())
                    .collect(Collectors.joining(", "))
                ),
                map -> {
                    map.values().removeIf(String::isEmpty);
                    return map;
                }
            ));
    }

    public static Set<ValidationError> checkAnimalData(Animal animal) {
        Set<ValidationError> errors = new HashSet<>();
        if (animal.age() < 0) {
            errors.add(new ValidationError(
                ValidationError.TypeOfError.AGE,
                ValidationError.TypeOfError.AGE.getText()));
        }
        if (animal.weight() < 0) {
            errors.add(new ValidationError(
                ValidationError.TypeOfError.WEIGHT,
                ValidationError.TypeOfError.WEIGHT.getText()));
        }
        if (animal.height() < 0) {
            errors.add(new ValidationError(
                ValidationError.TypeOfError.HEIGHT,
                ValidationError.TypeOfError.HEIGHT.getText()));
        }
        if (animal.name() == null || animal.name().isEmpty()) {
            errors.add(new ValidationError(
                ValidationError.TypeOfError.NAME,
                ValidationError.TypeOfError.NAME.getText()));
        }
        return errors;
    }
}
