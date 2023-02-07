package sc.miu.edu.payment_service.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BankAccountDto {
    private Integer customerId;
    private Integer routingNumber;
    private String bankAccountNumber;
    private String emailAddress;
    private Double balance;
    private BankAccountType type;
}
