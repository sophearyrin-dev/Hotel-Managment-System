package cs.miu.edu.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;


import java.time.LocalDate;
import java.util.Date;

//@Entity
@NoArgsConstructor
@AllArgsConstructor
//@Builder
@Document
@Data
@Builder
public class    CreditCard {
    @Id

    private String creditCardId;
   //private String roomId;
    private String userName;
    private String cardNumber;
    private String cardName;
    private String ccv;
    private Double cardLimit;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date expiryDate;
    private Double balance;
}
