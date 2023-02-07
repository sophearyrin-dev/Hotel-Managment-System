package sc.miu.edu.payment_service.clientfeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import sc.miu.edu.payment_service.dto.CreditCardDto;
import sc.miu.edu.payment_service.dto.PaymentInformationResponse;
import sc.miu.edu.payment_service.dto.PaypalDto;

@FeignClient(name = "creditcard-service", url = "creditcard-service.default.svc.cluster.local:9001/creditcards")
// @FeignClient(name = "creditcard-service" , url =
// "localhost:9001/creditcards")
public interface CreditCardFeignClient {
    @PostMapping("/verify-purchase")
    public ResponseEntity<PaymentInformationResponse> check(@RequestBody CreditCardDto creditCardDto);
}
