package sc.miu.edu.payment_service.domain;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;


import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "paymentInformation")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentInformation {
    @Id
    private Long paymentId;
    private Integer customerId;
    private AccountType accountType;
    private Double totalPayment;
    private String transactionCode;
    private LocalDateTime transactionDate;

}
