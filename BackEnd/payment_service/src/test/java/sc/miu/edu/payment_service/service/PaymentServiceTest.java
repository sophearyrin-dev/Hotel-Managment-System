package sc.miu.edu.payment_service.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
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

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
class PaymentServiceTest {

    private PaymentService paymentService;

    @Mock
    private GeneratorClass generatorClass;

    @Mock
    private PaymentRepo paymentRepo;

    @Captor
    ArgumentCaptor<PaymentInformation> paymentInformationArgumentCaptor;

    private PaypalFeignClientFailureMock paypalFeignClientFailureMock = new PaypalFeignClientFailureMock();

    private PaypalFeignClientSuccessMock paypalFeignClientSuccessMock = new PaypalFeignClientSuccessMock();

    private BankAccountFeignClientSuccessMock bankAccountFeignClientSuccessMock=new BankAccountFeignClientSuccessMock();

    private BankAccountFeignClientFailureMock bankAccountFeignClientFailureMock=new BankAccountFeignClientFailureMock();

    private CreditCardFeignClientFailureMock creditCardFeignClientFailureMock=new CreditCardFeignClientFailureMock();

    private CreditCardClientSuccessMock creditCardClientSuccessMock=new CreditCardClientSuccessMock();


    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeEach
    void setUp(){

    }

    @Test
    void should_return_failure_when_verifyPaypalAndSavePayment_is_not_valid() {
        when(generatorClass.transactionCodeGenerator()).thenReturn("P101");
        paymentService = new PaymentService(paymentRepo, paypalFeignClientFailureMock,
                null, null, null);
        PaypalDto paypalDto = PaypalDto.builder()
                .secureKey("1111")
                .emailAddress("giripriya@gmail.com")
                .balance(200.0)
                .customerId(1)
                .build();
        PaymentInformationResponse response= paymentService.verifyPaypalAndSavePayment(paypalDto);
        assertThat(response.getStatus()).isEqualTo(Status.FAILURE);

    }
    @Test
    void should_return_success_when_verifyPaypalAndSavePayment_is_valid() {
        when(generatorClass.transactionCodeGenerator()).thenReturn("P101");
        PaymentInformation paymentInformation = PaymentInformation.builder()
                .customerId(1)
                .totalPayment(200.0)
                .accountType(AccountType.PAYPAL)
                .transactionCode("P101")
                .transactionDate(LocalDateTime.now())
                .build();

        when(paymentRepo.save(paymentInformationArgumentCaptor.capture())).thenReturn(paymentInformation);
        paymentService = new PaymentService(paymentRepo, paypalFeignClientSuccessMock,
                null, null, generatorClass);
        PaypalDto paypalDto = PaypalDto.builder()
                .secureKey("1111")
                .emailAddress("giripriya@gmail.com")
                .balance(200.0)
                .customerId(1)
                .build();
        PaymentInformationResponse response = paymentService.verifyPaypalAndSavePayment(paypalDto);

        //matching with the capture element
        PaymentInformation paymentInformationToSave = paymentInformationArgumentCaptor.getValue();
        assertThat(paymentInformationToSave).isNotNull();
        assertThat(paymentInformationToSave.getTransactionCode()).isEqualTo("P101");
        assertThat(paymentInformationToSave.getAccountType()).isEqualTo(AccountType.PAYPAL);
        assertThat(paymentInformationToSave.getTotalPayment()).isEqualTo(200.0);
        assertThat(paymentInformationToSave.getCustomerId()).isEqualTo(1);
        //matching with the response
        assertThat(response).isNotNull();
        assertThat(response.getStatus()).isEqualTo(Status.SUCCESS);
        assertThat(response.getPaymentInformationDto()).isNotNull();
        assertThat(response.getErrorMessage()).isNull();

    }
    @Test
    void should_return_failure_when_verifyBankAccountAndSavePayment_is_not_valid() {
        paymentService= new PaymentService(null,null,null,bankAccountFeignClientFailureMock,null);
        BankAccountDto bankAccountDto= BankAccountDto.builder()
                .customerId(1)
                .bankAccountNumber("22222222")
                .balance(30.9)
                .emailAddress("giri@gmail.com")
                .routingNumber(1111)
                .type(BankAccountType.SAVING).build();
        PaymentInformationResponse response= paymentService.verifyBankAccountAndSavePayment(bankAccountDto);
        assertThat(response.getStatus()).isEqualTo(Status.FAILURE);
        assertThat(response.getErrorMessage()).isEqualTo("Something is wrong with your Bank Account");

    }
    @Test
    void should_return_success_when_verifyBankAccountAndSavePayment_is_valid() {
        when(generatorClass.transactionCodeGenerator()).thenReturn("P101");
        PaymentInformation paymentInformation = PaymentInformation.builder()
                .customerId(1)
                .totalPayment(200.0)
                .accountType(AccountType.BANKACCOUNT)
                .transactionCode("P101")
                .transactionDate(LocalDateTime.now())
                .build();

        when(paymentRepo.save(paymentInformationArgumentCaptor.capture())).thenReturn(paymentInformation);
        paymentService = new PaymentService(paymentRepo, null,
                null, bankAccountFeignClientSuccessMock, generatorClass);
        BankAccountDto bankAccountDto= BankAccountDto.builder()
                .customerId(1)
                .bankAccountNumber("11111111")
                .balance(30.9)
                .emailAddress("giri@gmail.com")
                .routingNumber(1111)
                .type(BankAccountType.SAVING).build();
        PaymentInformationResponse response = paymentService.verifyBankAccountAndSavePayment(bankAccountDto);

        //matching with the capture element
        PaymentInformation paymentInformationToSave = paymentInformationArgumentCaptor.getValue();
        assertThat(paymentInformationToSave).isNotNull();
        assertThat(paymentInformationToSave.getTransactionCode()).isEqualTo("P101");
        assertThat(paymentInformationToSave.getAccountType()).isEqualTo(AccountType.BANKACCOUNT);
        assertThat(paymentInformationToSave.getTotalPayment()).isEqualTo(30.9);
        assertThat(paymentInformationToSave.getCustomerId()).isEqualTo(1);
        //matching with the response
        assertThat(response).isNotNull();
        assertThat(response.getStatus()).isEqualTo(Status.SUCCESS);
        assertThat(response.getPaymentInformationDto()).isNotNull();
        assertThat(response.getErrorMessage()).isNull();

    }
    @Test
    void should_return_failure_when_verifyCreditCardAndSavePayment_is_not_valid() {
        paymentService= new PaymentService(null,null,creditCardFeignClientFailureMock,null,null);
        CreditCardDto creditCardDto= CreditCardDto.builder()
                .cardNumber("11111111")
                .balance(120.0)
                .customerId(1)
                .ccv("2222")
                .expiryDate(LocalDate.now())
                .build();
        PaymentInformationResponse response= paymentService.verifyCreditCardAndSavePayment(creditCardDto);
        assertThat(response.getStatus()).isEqualTo(Status.FAILURE);
        assertThat(response.getErrorMessage()).isEqualTo("Something is wrong with your Credit Card Account");

    }
    @Test
    void should_return_success_when_verifyCreditCardAndSavePayment_is_valid() {
        when(generatorClass.transactionCodeGenerator()).thenReturn("P101");
        PaymentInformation paymentInformation = PaymentInformation.builder()
                .customerId(1)
                .totalPayment(200.0)
                .accountType(AccountType.BANKACCOUNT)
                .transactionCode("P101")
                .transactionDate(LocalDateTime.now())
                .build();

        when(paymentRepo.save(paymentInformationArgumentCaptor.capture())).thenReturn(paymentInformation);
        paymentService = new PaymentService(paymentRepo, null,
                creditCardClientSuccessMock, null, generatorClass);
        CreditCardDto creditCardDto= CreditCardDto.builder()
                .cardNumber("11111111")
                .balance(120.0)
                .customerId(1)
                .ccv("1111")
                .expiryDate(LocalDate.now())
                .build();
        PaymentInformationResponse response = paymentService.verifyCreditCardAndSavePayment(creditCardDto);

        //matching with the capture element
        PaymentInformation paymentInformationToSave = paymentInformationArgumentCaptor.getValue();
        assertThat(paymentInformationToSave).isNotNull();
        assertThat(paymentInformationToSave.getTransactionCode()).isEqualTo("P101");
        assertThat(paymentInformationToSave.getAccountType()).isEqualTo(AccountType.CREDITCARD);
        assertThat(paymentInformationToSave.getTotalPayment()).isEqualTo(120.0);
        assertThat(paymentInformationToSave.getCustomerId()).isEqualTo(1);
        //matching with the response
        assertThat(response).isNotNull();
        assertThat(response.getStatus()).isEqualTo(Status.SUCCESS);
        assertThat(response.getPaymentInformationDto()).isNotNull();
        assertThat(response.getErrorMessage()).isNull();

    }
    @Test
    void should_return_success_when_deleteAlldata_is_called() {
       paymentService= new PaymentService(paymentRepo,null,null,null,null);
        PaymentInformationResponse response= paymentService.deleteAlldata();
        assertThat(response.getStatus()).isEqualTo(Status.SUCCESS);

    }

    @Test
    void should_return_failure_when_getPaymentInformationByTransactionCode_value_is_not_obtained() {
        paymentService= new PaymentService(paymentRepo,null,null,null,null);
        String transactionCode= "1111";
        when(paymentRepo.getPaymentInformationByTransactionCode(transactionCode)).thenReturn(null);
        PaymentInformationResponse response= paymentService.getPaymentInformationByTransactionCode(transactionCode);
        assertThat(response.getErrorMessage()).isEqualTo("there is no any payment Information with this transaction code");
        assertThat(response.getStatus()).isEqualTo(Status.FAILURE);

    }
    @Test
    void should_return_success_when_getPaymentInformationByTransactionCode_value_is_obtained() {
        paymentService= new PaymentService(paymentRepo,null,null,null,null);
        String transactionCode= "P101";
        PaymentInformation paymentInformation = PaymentInformation.builder()
                .customerId(1)
                .totalPayment(200.0)
                .accountType(AccountType.BANKACCOUNT)
                .transactionCode("P101")
                .transactionDate(LocalDateTime.now())
                .build();
        when(paymentRepo.getPaymentInformationByTransactionCode(transactionCode)).thenReturn(paymentInformation);
        PaymentInformationResponse response= paymentService.getPaymentInformationByTransactionCode(transactionCode);
        assertThat(response.getPaymentInformationDto().getCustomerId()).isEqualTo(1);
        assertThat(response.getPaymentInformationDto().getTotalPayment()).isEqualTo(200.0);
        assertThat(response.getPaymentInformationDto().getAccountType()).isEqualTo(AccountType.BANKACCOUNT);

    }


    //for the failure of paypal feign
    class PaypalFeignClientFailureMock implements PaypalFeignClient {

        @Override
        public ResponseEntity<PaymentInformationResponse> verifyPurchase(PaypalDto paypalDto) {
            return new ResponseEntity<>(PaymentInformationResponse.builder()
                    .status(Status.FAILURE).build(), HttpStatus.OK);
        }
    }
    //for the success of paypal feign
    class PaypalFeignClientSuccessMock implements PaypalFeignClient {

        @Override
        public ResponseEntity<PaymentInformationResponse> verifyPurchase(PaypalDto paypalDto) {
            return new ResponseEntity<>(PaymentInformationResponse.builder()
                    .status(Status.SUCCESS).build(), HttpStatus.OK);
        }
    }
    //for the failure of bankAccount feign
    class BankAccountFeignClientFailureMock implements BankAccountFeignClient {


        @Override
        public ResponseEntity<PaymentInformationResponse> verifyPayment(BankAccountDto bankAccountDto) {
            return new ResponseEntity<>(PaymentInformationResponse.builder()
                    .status(Status.FAILURE).build(), HttpStatus.OK);
        }
    }
    //for the success of bankAccount feign
    class BankAccountFeignClientSuccessMock implements BankAccountFeignClient {


        @Override
        public ResponseEntity<PaymentInformationResponse> verifyPayment(BankAccountDto bankAccountDto) {
            return new ResponseEntity<>(PaymentInformationResponse.builder()
                    .status(Status.SUCCESS).build(), HttpStatus.OK);
        }
    }
    //for the failure of creditCard feign
    class CreditCardFeignClientFailureMock implements CreditCardFeignClient {


        @Override
        public ResponseEntity<PaymentInformationResponse> check(CreditCardDto creditCardDto) {
            return new ResponseEntity<>(PaymentInformationResponse.builder()
                    .status(Status.FAILURE).build(), HttpStatus.OK);
        }
    }
    //for the success of creditCard feign
    class CreditCardClientSuccessMock implements CreditCardFeignClient {


        @Override
        public ResponseEntity<PaymentInformationResponse> check(CreditCardDto creditCardDto) {
            return new ResponseEntity<>(PaymentInformationResponse.builder()
                    .status(Status.SUCCESS).build(), HttpStatus.OK);
        }
    }

}