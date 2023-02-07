package cs.miu.edu.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDate;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreditCardDto {
    private String firstName;
    private String lastName;
    private String cardNumber;
    private String ccv;
    private LocalDate expiryDate;
    private  Double balance;
}
