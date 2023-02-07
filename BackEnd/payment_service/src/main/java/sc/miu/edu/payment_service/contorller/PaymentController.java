package sc.miu.edu.payment_service.contorller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sc.miu.edu.payment_service.dto.BankAccountDto;
import sc.miu.edu.payment_service.dto.CreditCardDto;
import sc.miu.edu.payment_service.dto.PaymentInformationResponse;
import sc.miu.edu.payment_service.dto.PaypalDto;
import sc.miu.edu.payment_service.service.PaymentService;


@RestController
@RequestMapping("api/v1/payments")
@EnableAutoConfiguration(exclude= DataSourceAutoConfiguration.class)

@AllArgsConstructor
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/paypal")
    public ResponseEntity<PaymentInformationResponse> savePaymentPaypal(@RequestBody PaypalDto paypalDto){
        ResponseEntity<PaymentInformationResponse> response= new ResponseEntity<>(paymentService.verifyPaypalAndSavePayment(paypalDto), HttpStatus.OK);
        return  response;
    }
    @PostMapping("/creditcard")
    public ResponseEntity<PaymentInformationResponse> savePaymentCreditCard(@RequestBody CreditCardDto creditCardDto){
        ResponseEntity<PaymentInformationResponse> response= new ResponseEntity<>(paymentService.verifyCreditCardAndSavePayment(creditCardDto), HttpStatus.OK);
        return  response;
    }
    @PostMapping("/bankaccount")
    public ResponseEntity<PaymentInformationResponse> savePaymentBankAccount(@RequestBody BankAccountDto bankAccountDto){
        ResponseEntity<PaymentInformationResponse> response= new ResponseEntity<>(paymentService.verifyBankAccountAndSavePayment(bankAccountDto), HttpStatus.OK);
        return  response;
    }

    @DeleteMapping
    public ResponseEntity<PaymentInformationResponse> deleteAllPaymentInformation(){
        ResponseEntity<PaymentInformationResponse> response= new ResponseEntity<>(paymentService.deleteAlldata(),HttpStatus.OK);
        return response;
    }




    @GetMapping("/{transactionCode}")
    public ResponseEntity<PaymentInformationResponse> getPaymentInformatinFromTransactioncode(@PathVariable(value = "transactionCode") String transactionCode){
        ResponseEntity<PaymentInformationResponse> response= new ResponseEntity<>(paymentService.getPaymentInformationByTransactionCode(transactionCode),HttpStatus.OK);
        return response;
    }

}
