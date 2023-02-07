package sc.miu.edu.payment_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sc.miu.edu.payment_service.domain.AccountType;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaypalDto {

    private String emailAddress;
    private String secureKey;
    private Double balance;
    private Integer customerId;
    private String fullName;


}
