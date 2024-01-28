package com.example.s23319.service;

import com.example.s23319.client.Client;
import com.example.s23319.client.Currency;
import com.example.s23319.exceptions.ValidationException;
import com.example.s23319.repository.ClientRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ClientServiceTest {

    private ClientService clientService;

    @BeforeEach
    void setUp() {
        clientService = new ClientService(new ClientRepository());
    }


    @Test
    void ShouldCreateNewClient() {
        Client client = new Client(null, "12345678901", new BigDecimal(100), Currency.PLN, "Karol", "Karolak");

        Client result = assertDoesNotThrow(() -> clientService.addClient(client));
        assertEquals(result, client);
    }
    @ParameterizedTest
    @MethodSource("provideInvalidClientCredentials")
    void shouldNotRegisterNewClient(Client client, String message) {
        ValidationException result = Assertions.assertThrows(ValidationException.class, () -> clientService.addClient(client));
        Assertions.assertEquals(result.getErrors().toString(), message);
    }

    public static Stream<Arguments> provideInvalidClientCredentials() {
        return Stream.of(
                Arguments.of(new Client(null, "12345678901", -2, Currency.PLN, "null", "null"), "{balance=Saldo nie może być ujemne}"),
                Arguments.of(new Client(null, "12340", 1000, Currency.PLN, "Karol", "Karolak"), "{pesel=Pesel musi mieć 11 znaków}",
                Arguments.of(new Client(null, "12345678901", 200, Currency.PLN, null, "null"), "{name=Imię nie może być puste}"),
                Arguments.of(new Client(null, "12345678901", 15190, Currency.PLN, "Karol", null), "{second name=Nazwisko nie może być puste}")
                ));
    }

}