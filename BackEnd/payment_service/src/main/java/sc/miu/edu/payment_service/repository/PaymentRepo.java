package sc.miu.edu.payment_service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import sc.miu.edu.payment_service.domain.PaymentInformation;
import sc.miu.edu.payment_service.dto.PaypalDto;

@Repository
public interface PaymentRepo extends MongoRepository<PaymentInformation,Integer> {

    public PaymentInformation getPaymentInformationByTransactionCode(String code);
    public PaymentInformation findTopByOrderByTransactionDateDesc();

}
