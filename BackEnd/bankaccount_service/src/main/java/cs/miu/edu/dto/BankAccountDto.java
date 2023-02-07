package cs.miu.edu.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import cs.miu.edu.domain.AccountType;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BankAccountDto {
    private Integer routingNumber;
    private String bankAccountNumber;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private Double balance;
    private AccountType type;
}
