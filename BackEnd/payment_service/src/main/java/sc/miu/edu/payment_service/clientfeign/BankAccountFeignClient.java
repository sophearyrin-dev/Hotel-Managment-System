package sc.miu.edu.payment_service.clientfeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import sc.miu.edu.payment_service.dto.BankAccountDto;
import sc.miu.edu.payment_service.dto.CreditCardDto;
import sc.miu.edu.payment_service.dto.PaymentInformationResponse;

@FeignClient(name = "bankaccount-service", url = "bankaccount-service.default.svc.cluster.local:9003/bankaccounts")
// @FeignClient(name = "bankaccount-service" , url =
// "localhost:9003/bankaccounts")
public interface BankAccountFeignClient {
    @PutMapping("/verify-purchase")
    public ResponseEntity<PaymentInformationResponse> verifyPayment(@RequestBody BankAccountDto bankAccountDto);
}
