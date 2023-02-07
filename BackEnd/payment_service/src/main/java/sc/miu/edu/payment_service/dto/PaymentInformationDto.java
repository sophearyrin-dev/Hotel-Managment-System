package sc.miu.edu.payment_service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sc.miu.edu.payment_service.domain.AccountType;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentInformationDto {

    private Long  paymentId;
    private Integer customerId;
    private String fullName;
    private AccountType accountType;
    private String transactionCode;
    private LocalDateTime transactionDate;
    private Double totalPayment;
}
