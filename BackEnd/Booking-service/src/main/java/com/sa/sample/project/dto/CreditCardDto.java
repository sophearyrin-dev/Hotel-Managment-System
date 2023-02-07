package com.sa.sample.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditCardDto {
    @Id
    private String creditCardId;
    //    private String firstName;
    private String userName;
    private String cardNumber;
    private String cardName;
    private String ccv;
    private Double cardLimit;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date expiryDate;
    private Double balance;
}
