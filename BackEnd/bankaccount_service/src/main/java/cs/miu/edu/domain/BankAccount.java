package cs.miu.edu.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Document
public class BankAccount {
    @Id
    private String id;
    private String userName;
    private Integer routingNumber;
    private String accountNumber;
    private String emailAddress;
    private Double balance;
    @Enumerated(EnumType.STRING)
    private AccountType type;

}
