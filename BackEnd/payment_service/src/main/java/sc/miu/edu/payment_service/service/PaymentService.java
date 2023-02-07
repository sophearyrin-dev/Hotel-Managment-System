package sc.miu.edu.payment_service.service;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import sc.miu.edu.payment_service.clientfeign.BankAccountFeignClient;
import sc.miu.edu.payment_service.clientfeign.CreditCardFeignClient;
import sc.miu.edu.payment_service.clientfeign.PaypalFeignClient;
import sc.miu.edu.payment_service.domain.AccountType;
import sc.miu.edu.payment_service.domain.PaymentInformation;
import sc.miu.edu.payment_service.dto.*;
import sc.miu.edu.payment_service.repository.PaymentRepo;
import sc.miu.edu.payment_service.utils.GeneratorClass;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class PaymentService {

    @Autowired
    private PaymentRepo paymentRepo;
    @Autowired
    private PaypalFeignClient paypalFeignClient;
    @Autowired
    private CreditCardFeignClient creditCardFeignClient;
    @Autowired
    private BankAccountFeignClient bankAccountFeignClient;
    @Autowired
    private GeneratorClass generatorClass;

    private PaymentInformationResponse returnSuccessPaymentResponse(PaymentInformation paymentInformation) {
        return PaymentInformationResponse.builder()
                .status(Status.SUCCESS)
                .paymentInformationDto(PaymentInformationDto.builder()
                        .accountType(paymentInformation.getAccountType())
                        .customerId(paymentInformation.getCustomerId())
                        .totalPayment(paymentInformation.getTotalPayment())
                        .paymentId(paymentInformation.getPaymentId())
                        .transactionDate(LocalDateTime.now())
                        .transactionCode(paymentInformation.getTransactionCode())
                        .build()).build();

    }

    private PaymentInformationResponse returnPaypalFailureMessage() {
        return PaymentInformationResponse.builder()
                .status(Status.FAILURE)
                .errorMessage("Something is wrong with your paypal Account").build();
    }

    private PaymentInformationResponse returnCreditCardFailureMessage() {
        return PaymentInformationResponse.builder()
                .status(Status.FAILURE)
                .errorMessage("Something is wrong with your Credit Card Account").build();
    }

    private PaymentInformationResponse returnBankAccountFailureMessage() {
        return PaymentInformationResponse.builder()
                .status(Status.FAILURE)
                .errorMessage("Something is wrong with your Bank Account").build();
    }


    public PaymentInformationResponse verifyPaypalAndSavePayment(PaypalDto paypalDto) {
        ResponseEntity<PaymentInformationResponse> response = paypalFeignClient.verifyPurchase(paypalDto);
        if (response.getBody().getStatus() == Status.FAILURE)
            return returnPaypalFailureMessage();

        PaymentInformation toSave = PaymentInformation.builder()
                .transactionCode(generatorClass.transactionCodeGenerator())
                .accountType(AccountType.PAYPAL)
                .customerId(paypalDto.getCustomerId())
                .totalPayment(paypalDto.getBalance())
                .transactionDate(LocalDateTime.now())
                .build();
        PaymentInformation paymentInformation = paymentRepo.save(toSave);
        return returnSuccessPaymentResponse(paymentInformation);
    }

    public PaymentInformationResponse verifyCreditCardAndSavePayment(CreditCardDto creditCardDto) {
        ResponseEntity<PaymentInformationResponse> response = creditCardFeignClient.check(creditCardDto);
        if (response.getBody().getStatus() == Status.FAILURE) return returnCreditCardFailureMessage();
        PaymentInformation paymentInformation = paymentRepo.save(PaymentInformation.builder()
                .accountType(AccountType.CREDITCARD)
                .customerId(creditCardDto.getCustomerId())
                .totalPayment(creditCardDto.getBalance())
                .transactionDate(LocalDateTime.now())
                .transactionCode(generatorClass.transactionCodeGenerator())
                .build());
        return returnSuccessPaymentResponse(paymentInformation);
    }


    public PaymentInformationResponse verifyBankAccountAndSavePayment(BankAccountDto bankAccountDto) {
        ResponseEntity<PaymentInformationResponse> response = bankAccountFeignClient.verifyPayment(bankAccountDto);
        if (response.getBody().getStatus() == Status.FAILURE)
            return returnBankAccountFailureMessage();
        PaymentInformation paymentInformation = paymentRepo.save(PaymentInformation.builder()
                .accountType(AccountType.BANKACCOUNT)
                .customerId(bankAccountDto.getCustomerId())
                .totalPayment(bankAccountDto.getBalance())
                .transactionCode(generatorClass.transactionCodeGenerator())
                .transactionDate(LocalDateTime.now())
                .build());
        return returnSuccessPaymentResponse(paymentInformation);
    }

    public PaymentInformationResponse deleteAlldata() {
        paymentRepo.deleteAll();
        return PaymentInformationResponse.builder()
                .status(Status.SUCCESS).build();
    }

    public PaymentInformationResponse getPaymentInformationByTransactionCode(String transactionCode) {
        PaymentInformation paymentInformation = paymentRepo.getPaymentInformationByTransactionCode(transactionCode);
        if (paymentInformation == null) {
            return PaymentInformationResponse.builder()
                    .status(Status.FAILURE)
                    .errorMessage("there is no any payment Information with this transaction code")
                    .build();
        }
        return PaymentInformationResponse.builder()
                .paymentInformationDto(PaymentInformationDto.builder()
                        .transactionCode(paymentInformation.getTransactionCode())
                        .totalPayment(paymentInformation.getTotalPayment())
                        .accountType(paymentInformation.getAccountType())
                        .transactionDate(paymentInformation.getTransactionDate())
                        .customerId(paymentInformation.getCustomerId()).build())
                .build();
    }
}
