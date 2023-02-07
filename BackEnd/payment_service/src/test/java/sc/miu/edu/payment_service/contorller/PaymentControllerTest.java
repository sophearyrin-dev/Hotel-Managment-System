package sc.miu.edu.payment_service.contorller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sc.miu.edu.payment_service.domain.AccountType;
import sc.miu.edu.payment_service.dto.*;
import sc.miu.edu.payment_service.service.PaymentService;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
class PaymentControllerTest {

    private PaymentController paymentController;
    @Mock
    private PaymentService paymentService;
    @Captor
    ArgumentCaptor<PaypalDto> paypalDtoArgumentCaptor;
    @Captor
    ArgumentCaptor<CreditCardDto> creditCardDtoArgumentCaptor;
    @Captor
    ArgumentCaptor<BankAccountDto> bankAccountDtoArgumentCaptor;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeEach
    void setUp() {

    }

    //test through paypal

    @Test
    void savePaymentPaypal() {
        paymentController = new PaymentController(paymentService);
        PaymentInformationResponse paymentInformationResponse = PaymentInformationResponse.builder()
                .status(Status.SUCCESS)
                .paymentInformationDto(PaymentInformationDto.builder()
                        .totalPayment(210.0)
                        .customerId(12)
                        .accountType(AccountType.PAYPAL)
                        .transactionCode("1111")
                        .build()).build();
        PaypalDto paypalDto = PaypalDto.builder()
                .emailAddress("giripriya@gmail.com")
                .secureKey("1111")
                .balance(122.0)
                .customerId(12)
                .build();
        when(paymentService.verifyPaypalAndSavePayment(paypalDtoArgumentCaptor.capture())).thenReturn(paymentInformationResponse);

        ResponseEntity<PaymentInformationResponse> response = paymentController.savePaymentPaypal(paypalDto);
        assertThat(response.getBody().getStatus()).isEqualTo(Status.SUCCESS);
        assertThat(response.getBody().getPaymentInformationDto().getCustomerId()).isEqualTo(12);

    }

//test through credit card
    @Test
    void savePaymentCreditCard() {
        paymentController = new PaymentController(paymentService);
        PaymentInformationResponse paymentInformationResponse = PaymentInformationResponse.builder()
                .status(Status.SUCCESS)
                .paymentInformationDto(PaymentInformationDto.builder()
                        .totalPayment(210.0)
                        .customerId(12)
                        .accountType(AccountType.CREDITCARD)
                        .transactionCode("1111")
                        .build()).build();
        CreditCardDto creditCardDto = CreditCardDto.builder()
                .cardNumber("11111111")
                .ccv("1111")
                .expiryDate(LocalDate.now())
                .customerId(2)
                .balance(33.3)
                .build();
        when(paymentService.verifyCreditCardAndSavePayment(creditCardDtoArgumentCaptor.capture())).thenReturn(paymentInformationResponse);

        ResponseEntity<PaymentInformationResponse> response = paymentController.savePaymentCreditCard(creditCardDto);
        assertThat(response.getBody().getStatus()).isEqualTo(Status.SUCCESS);
        assertThat(response.getBody().getPaymentInformationDto().getCustomerId()).isEqualTo(12);
        assertThat(response.getBody().getPaymentInformationDto().getAccountType()).isEqualTo(AccountType.CREDITCARD);

    }
//test through bank account
    @Test
    void savePaymentBankAccount() {
        paymentController = new PaymentController(paymentService);
        PaymentInformationResponse paymentInformationResponse = PaymentInformationResponse.builder()
                .status(Status.SUCCESS)
                .paymentInformationDto(PaymentInformationDto.builder()
                        .totalPayment(210.0)
                        .customerId(12)
                        .accountType(AccountType.BANKACCOUNT)
                        .transactionCode("1111")
                        .build()).build();
        BankAccountDto bankAccountDto = BankAccountDto.builder()
                .type(BankAccountType.SAVING)
                .bankAccountNumber("11111111")
                .balance(12.0)
                .routingNumber(1111)
                .build();
        when(paymentService.verifyBankAccountAndSavePayment(bankAccountDtoArgumentCaptor.capture())).thenReturn(paymentInformationResponse);

        ResponseEntity<PaymentInformationResponse> response = paymentController.savePaymentBankAccount(bankAccountDto);
        assertThat(response.getBody().getStatus()).isEqualTo(Status.SUCCESS);
        assertThat(response.getBody().getPaymentInformationDto().getCustomerId()).isEqualTo(12);
        assertThat(response.getBody().getPaymentInformationDto().getAccountType()).isEqualTo(AccountType.BANKACCOUNT);
    }

    @Test
    void deleteAllPaymentInformation() {
        paymentController = new PaymentController(paymentService);
        when(paymentService.deleteAlldata()).thenReturn(PaymentInformationResponse.builder()
                .status(Status.SUCCESS).build());
         ResponseEntity<PaymentInformationResponse> response= paymentController.deleteAllPaymentInformation();
         assertThat(response.getBody().getStatus()).isEqualTo(Status.SUCCESS);
    }

    @Test
    void getPaymentInformatinFromTransactioncode() {
        paymentController = new PaymentController(paymentService);
        PaymentInformationResponse paymentInformationResponse = PaymentInformationResponse.builder()
                .paymentInformationDto(PaymentInformationDto.builder()
                        .totalPayment(210.0)
                        .customerId(12)
                        .accountType(AccountType.BANKACCOUNT)
                        .transactionCode("P101")
                        .build()).build();
        when(paymentService.getPaymentInformationByTransactionCode("P101")).thenReturn(paymentInformationResponse);
        ResponseEntity<PaymentInformationResponse> response= paymentController.getPaymentInformatinFromTransactioncode("P101");
        assertThat(response.getBody().getPaymentInformationDto().getTransactionCode()).isEqualTo("P101");
        assertThat(response.getBody().getPaymentInformationDto().getTotalPayment()).isEqualTo(210.0);

    }


}