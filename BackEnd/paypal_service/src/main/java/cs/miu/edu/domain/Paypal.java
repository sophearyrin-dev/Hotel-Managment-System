package cs.miu.edu.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Document
public class Paypal {

    @Id
    private String id;
    private String userName;

    private String emailAddress;
    private String secureKey;
    private Double balance;


}
