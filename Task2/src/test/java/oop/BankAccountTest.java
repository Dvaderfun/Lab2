package oop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

@DisplayName("Тестирование класса BankAccount")
public class BankAccountTest {

    private BankAccount account;

    @BeforeEach
    public void setUp() {
        account = new BankAccount();
    }

    @DisplayName("Тестирование метода deposit()")
    @ParameterizedTest(name = "account.deposit({0}).deposit({1})")
    @MethodSource(value = "depositMethodTestArgumentsProvider")
    @Order(0)
    public void testDepositMethod(double amount1, double amount2, double expectedAmount, String errorMessage) {
        account.deposit(amount1);
        account.deposit(amount2);
        Assertions.assertEquals(expectedAmount, account.getBalance(), errorMessage);
    }

    @DisplayName("Тестирование метода withdraw()")
    @ParameterizedTest(name = "account.withdraw({1})")
    @MethodSource(value = "withdrawMethodTestArgumentsProvider")
    @Order(1)
    public void testWithdrawMethod(double amount1, double amount2, double fee, double expectedAmount, String errorMessage) {
        account.setTransactionFee(fee);
        account.deposit(amount1);
        account.withdraw(amount2);
        Assertions.assertEquals(expectedAmount, account.getBalance(), 0.01, errorMessage);
    }

    @DisplayName("Тестирование метода transfer()")
    @ParameterizedTest(name = "account.transfer(account2, {1})")
    @MethodSource(value = "transferMethodTestArgumentsProvider")
    @Order(1)
    public void testTransferMethod(double amount1, double amount2, double fee, double expectedAmount, String errorMessage) {
        account.setTransactionFee(fee);
        account.deposit(amount1);
        BankAccount other = new BankAccount();
        account.transfer(other, amount2);
        Assertions.assertEquals(expectedAmount, account.getBalance(), 0.01, errorMessage);
    }

    // region Провайдеры

    private static Stream<Arguments> transferMethodTestArgumentsProvider() {
        return Stream.of(
                Arguments.of(20.00, 16.00, 5.0, 20.0, "Ожидаемое значение баланса не равно фактическому значению"),
                Arguments.of(20.00, 16.00, 1.0, 3.0, "Ожидаемое значение баланса не равно фактическому значению"),
                Arguments.of(20.00, -2.00, 1.0, 20.00, "Ожидаемое значение баланса не равно фактическому значению"),
                Arguments.of(20.00, 22.00, 1.0, 20.00, "Ожидаемое значение баланса не равно фактическому значению")
        );
    }

    private static Stream<Arguments> withdrawMethodTestArgumentsProvider() {
        return Stream.of(
                Arguments.of(-50.50, -10.50, 10.0, 0.0, "Ожидаемое значение баланса не равно фактическому значению"),
                Arguments.of(-50.50, 20.00, 10.0, 0.0, "Ожидаемое значение баланса не равно фактическому значению"),
                Arguments.of(50.50, -20.00, 10.0, 50.50, "Ожидаемое значение баланса не равно фактическому значению"),
                Arguments.of(50.50, 50.00, 1.0, 50.50, "Ожидаемое значение баланса не равно фактическому значению"),
                Arguments.of(50.50, 50.00, 0.49, 0.01, "Ожидаемое значение баланса не равно фактическому значению")
        );
    }

    private static Stream<Arguments> depositMethodTestArgumentsProvider() {
        return Stream.of(
                Arguments.of(50.50, 10.50, 61.0, "Ожидаемое значение баланса не равно фактическому значению"),
                Arguments.of(-50.50, 20.00, 20.0, "Ожидаемое значение баланса не равно фактическому значению"),
                Arguments.of(-50.50, -20.00, 0.0, "Ожидаемое значение баланса не равно фактическому значению")
        );
    }

    // endregion

}
