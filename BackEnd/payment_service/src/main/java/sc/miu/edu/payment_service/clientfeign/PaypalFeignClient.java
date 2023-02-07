package sc.miu.edu.payment_service.clientfeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import sc.miu.edu.payment_service.dto.PaymentInformationResponse;
import sc.miu.edu.payment_service.dto.PaypalDto;

@FeignClient(name = "paypal-service", url = "paypal-service.default.svc.cluster.local:9002/paypals")
// @FeignClient(name = "paypal-service", url = "localhost:9002/paypals")
public interface PaypalFeignClient {
    @PutMapping("/verify-purchase")
    public ResponseEntity<PaymentInformationResponse> verifyPurchase(@RequestBody PaypalDto paypalDto);
}
