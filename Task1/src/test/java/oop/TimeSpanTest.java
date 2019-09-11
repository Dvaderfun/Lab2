package oop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

@DisplayName("Тестирование класса TimeSpan")
public class TimeSpanTest {

    @DisplayName("Тестирование конструктора с 2 параметрами")
    @ParameterizedTest(name = "new TimeSpan({0}, {1});")
    @MethodSource(value = "constructorTestArgumentsProvider")
    @Order(1)
    public void testConstructor(int hours, int minutes, int expectedMinutes, String errorMessage) {
        TimeSpan span = new TimeSpan(hours, minutes);
        Assertions.assertEquals(expectedMinutes, span.getTotalMinutes(), errorMessage);
    }

    @DisplayName("Тестирование метода add(hours, minutes)")
    @ParameterizedTest(name = "span.add({2}, {3});")
    @MethodSource(value = "addMethodTestArgumentsProvider")
    @Order(2)
    public void testAddMethod(int startHours, int startMinutes, int hours, int minutes, int expectedMinutes, String errorMessage) {
        TimeSpan span = new TimeSpan(startHours, startMinutes);
        span.add(hours, minutes);
        Assertions.assertEquals(expectedMinutes, span.getTotalMinutes(), errorMessage);
    }

    @DisplayName("Тестирование метода addTimeSpan(timespan)")
    @ParameterizedTest(name = "span.addTimeSpan(new TimeSpan({2}, {3}));")
    @MethodSource(value = "addMethodTestArgumentsProvider")
    @Order(3)
    public void testAddTimeSpanMethod(int startHours, int startMinutes, int hours, int minutes, int expectedMinutes, String errorMessage) {
        TimeSpan span = new TimeSpan(startHours, startMinutes);
        span.addTimeSpan(new TimeSpan(hours, minutes));
        Assertions.assertEquals(expectedMinutes, span.getTotalMinutes(), errorMessage);
    }

    @DisplayName("Тестирование метода getHours()")
    @ParameterizedTest(name = "new TimeSpan({0}, {1}).getHours();")
    @MethodSource(value = "getHoursTestArgumentsProvider")
    @Order(4)
    public void testGetHoursMethod(int startHours, int startMinutes, int expectedHours, String errorMessage) {
        TimeSpan span = new TimeSpan(startHours, startMinutes);
        Assertions.assertEquals(expectedHours, span.getHours(), errorMessage);
    }

    @DisplayName("Тестирование метода getMinutes()")
    @ParameterizedTest(name = "new TimeSpan({0}, {1}).getMinutes();")
    @MethodSource(value = "getMinutesTestArgumentsProvider")
    @Order(5)
    public void testGetMinutesMethod(int startHours, int startMinutes, int expectedMinutes, String errorMessage) {
        TimeSpan span = new TimeSpan(startHours, startMinutes);
        Assertions.assertEquals(expectedMinutes, span.getMinutes(), errorMessage);
    }

    @DisplayName("Тестирование метода getTotalHours()")
    @ParameterizedTest(name = "new TimeSpan({0}, {1}).getTotalHours();")
    @MethodSource(value = "getTotalHoursTestArgumentsProvider")
    @Order(6)
    public void testGetTotalHoursMethod(int startHours, int startMinutes, double expectedHours, String errorMessage) {
        TimeSpan span = new TimeSpan(startHours, startMinutes);
        Assertions.assertEquals(expectedHours, span.getTotalHours(), errorMessage);
    }

    @DisplayName("Тестирование метода getTotalMinutes()")
    @ParameterizedTest(name = "new TimeSpan({0}, {1}).getTotalMinutes();")
    @MethodSource(value = "getTotalMinutesTestArgumentsProvider")
    @Order(7)
    public void testGetTotalMinutesMethod(int startHours, int startMinutes, int expectedMinutes, String errorMessage) {
        TimeSpan span = new TimeSpan(startHours, startMinutes);
        Assertions.assertEquals(expectedMinutes, span.getTotalMinutes(), errorMessage);
    }

    @DisplayName("Тестирование метода subtract(timespan)")
    @ParameterizedTest(name = "span.subtract(new TimeSpan({2}, {3}));")
    @MethodSource(value = "subtractTestArgumentsProvider")
    @Order(8)
    public void testSubtractMethod(int startHours, int startMinutes, int hours, int minutes, int expectedMinutes, String errorMessage) {
        TimeSpan span = new TimeSpan(startHours, startMinutes);
        span.subtract(new TimeSpan(hours, minutes));
        Assertions.assertEquals(expectedMinutes, span.getTotalMinutes(), errorMessage);
    }

    @DisplayName("Тестирование метода scale(timespan)")
    @ParameterizedTest(name = "span.scale({2})")
    @MethodSource(value = "scaleTestArgumentsProvider")
    @Order(9)
    public void testScaleMethod(int startHours, int startMinutes, int factor, int expectedMinutes, String errorMessage) {
        TimeSpan span = new TimeSpan(startHours, startMinutes);
        span.scale(factor);
        Assertions.assertEquals(expectedMinutes, span.getTotalMinutes(), errorMessage);
    }

    // region Провайдеры

    private static Stream<Arguments> scaleTestArgumentsProvider() {
        return Stream.of(
                Arguments.of(1, 0, 2, 120, "Ожидаемый временной промежуток не равен фактическому"),
                Arguments.of(1, 30, 1, 90, "Ожидаемый временной промежуток не равен фактическому"),
                Arguments.of(1, 40, 0, 0, "Ожидаемый временной промежуток не равен фактическому"),
                Arguments.of(1, 0, -1, 60, "Множитель не может быть отрицательным")
        );
    }

    private static Stream<Arguments> subtractTestArgumentsProvider() {
        return Stream.of(
                Arguments.of(1, 0, 0, 15, 45, "Ожидаемый временной промежуток не равен фактическому"),
                Arguments.of(1, 0, 1, 0, 0, "Ожидаемый временной промежуток не равен фактическому"),
                Arguments.of(0, 30, 0, 40, 30, "Входящий временной промежуток не может быть больше текущего"),
                Arguments.of(1, 30, 1, 31, 90, "Входящий временной промежуток не может быть больше текущего")
        );
    }

    private static Stream<Arguments> getTotalMinutesTestArgumentsProvider() {
        return Stream.of(
                Arguments.of(1, 0, 60, "Ожидаемое количество минут не равно фактическому"),
                Arguments.of(0, 30, 30, "Ожидаемое количество минут не равно фактическому"),
                Arguments.of(1, 45, 105, "Ожидаемое количество минут не равно фактическому")
        );
    }

    private static Stream<Arguments> getTotalHoursTestArgumentsProvider() {
        return Stream.of(
                Arguments.of(1, 0, 1.0, "Ожидаемое количество часов не равно фактическому"),
                Arguments.of(0, 30, 0.5, "Ожидаемое количество часов не равно фактическому"),
                Arguments.of(0, 45, 0.75, "Ожидаемое количество часов не равно фактическому"),
                Arguments.of(1, 45, 1.75, "Ожидаемое количество часов не равно фактическому")
        );
    }

    private static Stream<Arguments> getMinutesTestArgumentsProvider() {
        return Stream.of(
                Arguments.of(0, 59, 59, "Ожидаемое количество минут не равно фактическому"),
                Arguments.of(1, 59, 59, "Ожидаемое количество минут не равно фактическому"),
                Arguments.of(1, 0, 0, "Ожидаемое количество минут не равно фактическому")
        );
    }

    private static Stream<Arguments> getHoursTestArgumentsProvider() {
        return Stream.of(
                Arguments.of(0, 59, 0, "Ожидаемое количество часов не равно фактическому"),
                Arguments.of(1, 59, 1, "Ожидаемое количество часов не равно фактическому"),
                Arguments.of(1, 0, 1, "Ожидаемое количество часов не равно фактическому")
        );
    }

    private static Stream<Arguments> addMethodTestArgumentsProvider() {
        return Stream.of(
                Arguments.of(0, 0, 0, 15, 15, "Ожидаемый временной промежуток не равен фактическому"),
                Arguments.of(0, 0, 1, 15, 75, "Неверный пересчет часов в минуты"),
                Arguments.of(0, 0, -1, 0, 0, "Значение часов не может быть отрицательным"),
                Arguments.of(0, 0, 1, -1, 0, "Значение минут не может быть отрицательным"),
                Arguments.of(0, 0, -1, -1, 0, "Значение часов и минут не может быть отрицательным")
        );
    }

    private static Stream<Arguments> constructorTestArgumentsProvider() {
        return Stream.of(
                Arguments.of(0, 2, 2, "Ожидаемый временной промежуток не равен фактическому"),
                Arguments.of(2, 30, 150, "Ошибка при пересчете количества минут"),
                Arguments.of(2, 70, 0, "Значение минут не может быть больше 60"),
                Arguments.of(-1, 0, 0, "Значение часов не может быть отрицательным"),
                Arguments.of(1, -1, 0, "Значение минут не может быть отрицательным"),
                Arguments.of(-1, -1, 0, "Значение часов и минут не может быть отрицательным")
        );
    }

    // endregion
}