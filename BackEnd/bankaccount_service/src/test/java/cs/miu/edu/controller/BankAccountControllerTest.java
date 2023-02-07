package cs.miu.edu.controller;

import cs.miu.edu.domain.AccountType;
import cs.miu.edu.domain.BankAccount;
import cs.miu.edu.service.BankAccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class BankAccountControllerTest {
    private BankAccountController bankAccountController;
    @Mock
    private BankAccountService bankAccountService;
    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeEach
    void setUp() {
        bankAccountController = new BankAccountController(bankAccountService);
    }
    @Captor
    private ArgumentCaptor<String> stringArgumentCaptor;
    @Captor
    private ArgumentCaptor<BankAccount> bankAccountArgumentCaptor;

    @Test
    void saveCreditCard() {

    }

    @Test
    void findCardById() {
        BankAccount bankAccount=BankAccount.builder()
                .id("62ab55fe7255fa4f2e123d73")
                .accountNumber("11111111")
                .balance(8000.0)
                .emailAddress("giriPriya@gamil.com")
                .routingNumber(1111)
                .type(AccountType.CHECKING)
                .userName("admin12")
                .build();
        String bankAccountId= "62ab55fe7255fa4f2e123d73";
        when(bankAccountController.findCardById(stringArgumentCaptor.capture())).thenReturn(bankAccount);
        BankAccount response= bankAccountController.findCardById(bankAccountId);
        assertThat(response).isNotNull();
        assertThat(response.getType()).isEqualTo(AccountType.CHECKING);

    }

    @Test
    void should_return_update_bank_account_when_updateCreditCard_is_called_by_id() {
        BankAccount bankAccount=BankAccount.builder()
                .id("62ab55fe7255fa4f2e123d73")
                .accountNumber("11111111")
                .balance(8000.0)
                .emailAddress("giriPriya@gamil.com")
                .routingNumber(1111)
                .type(AccountType.CHECKING)
                .userName("admin12")
                .build();
        BankAccount updatedBankAccount=BankAccount.builder()
                .id("62ab55fe7255fa4f2e123d73")
                .accountNumber("11111111")
                .balance(9000.0)
                .emailAddress("giriPriya@gamil.com")
                .routingNumber(1111)
                .type(AccountType.CHECKING)
                .userName("admin12")
                .build();
        String bankAccountId= "62ab55fe7255fa4f2e123d73";
        when(bankAccountService.updateBankAccount(bankAccountArgumentCaptor.capture(),stringArgumentCaptor.capture()))
                .thenReturn(updatedBankAccount);
        BankAccount response = bankAccountController.updateCreditCard(bankAccountId,bankAccount);
        assertThat(response).isNotNull();
        assertThat(response.getBalance()).isEqualTo(9000.0);

    }

    @Test
    void testUpdateCreditCard() {
    }
}