package sc.miu.edu.payment_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreditCardDto {
    private Integer customerId;

    private String cardNumber;
    private String ccv;
    private LocalDate expiryDate;
    private  Double balance;

}
