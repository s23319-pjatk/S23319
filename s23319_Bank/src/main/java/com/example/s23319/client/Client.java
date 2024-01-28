package com.example.s23319.client;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class Client {

    private Integer id;
    private String pesel;
    private BigDecimal balance;
    private Currency currency;
    private String Name;
    private String lastName;

    public Client(BigDecimal balance) { this.balance = balance; }
    public Client(Integer id, String pesel, int balance, Currency currency, String Name, String lastName){
        this.id = id;
        this.pesel = pesel;
        this.balance = BigDecimal.valueOf(balance);
        this.currency = currency;
        this.Name = Name;
        this.lastName = lastName;
    }


}
