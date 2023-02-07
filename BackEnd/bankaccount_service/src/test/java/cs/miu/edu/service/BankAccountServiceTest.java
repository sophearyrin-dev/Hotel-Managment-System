package cs.miu.edu.service;

import cs.miu.edu.domain.AccountType;
import cs.miu.edu.domain.BankAccount;
import cs.miu.edu.repository.BankAccountRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import java.awt.image.BaseMultiResolutionImage;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
class BankAccountServiceTest {

    private BankAccountService bankAccountService;
    @Mock
    private BankAccountRepo bankAccountRepo;
    @Captor
    ArgumentCaptor<BankAccount> bankAccountArgumentCaptor;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeEach
    void setUp(){

    }

    @Test
    void return_bank_account_object_saveBankAccount_is_called() {
        bankAccountService= new BankAccountService(bankAccountRepo);
       String username= "priya";

        BankAccount bankAccount= BankAccount.builder()
                .id("62ab55fe7255fa4f2e123d73")
                .accountNumber("11111111")
                .balance(8000.0)
                .emailAddress("giriPriya@gamil.com")
                .routingNumber(1111)
                .type(AccountType.CHECKING)
                .userName(username)
                .build();
        when(bankAccountRepo.save(bankAccountArgumentCaptor.capture())).thenReturn(bankAccount);
        BankAccount bankAccount1= BankAccount.builder()
                .accountNumber("11111111")
                .balance(8000.0)
                .emailAddress("giriPriya@gamil.com")
                .routingNumber(1111)
                .type(AccountType.CHECKING)
                .build();
        BankAccount response = bankAccountService.saveBankAccount(bankAccount1,username);
        assertThat(response.getUserName()).isEqualTo("priya");
    }

    @Test
    void return_all_list_of_bankaccounts_if_getCreditCard_is_called() {
        bankAccountService=new BankAccountService(bankAccountRepo);
        List<BankAccount> bankAccounts= Arrays.asList(
                BankAccount.builder()
                        .id("62ab55fe7255fa4f2e123d73")
                        .accountNumber("11111111")
                        .balance(8000.0)
                        .emailAddress("giriPriya@gamil.com")
                        .routingNumber(1111)
                        .type(AccountType.CHECKING)
                        .userName("admin12")
                        .build(),
                BankAccount.builder()
                        .id("62ab55fe7255fa4f2e123d74")
                        .accountNumber("22222222")
                        .balance(7000.0)
                        .emailAddress("sufi@gamil.com")
                        .routingNumber(2222)
                        .type(AccountType.SAVING)
                        .userName("admin13")
                        .build()
        );
        when(bankAccountRepo.findAll()).thenReturn(bankAccounts);
        List<BankAccount> response= bankAccountService.getCreditCards();
        assertThat(response).isNotNull();
        assertThat(response).isEqualTo(bankAccounts);

    }

    @Test
    void return_bank_account_object_with_bank_account_Id_getBankAccountById_is_call_with_id() {
     Optional<BankAccount> bankAccount= Optional.of(BankAccount.builder()
             .id("62ab55fe7255fa4f2e123d73")
             .accountNumber("11111111")
             .balance(8000.0)
             .emailAddress("giriPriya@gamil.com")
             .routingNumber(1111)
             .type(AccountType.CHECKING)
             .userName("admin12")
             .build()) ;
     bankAccountService= new BankAccountService(bankAccountRepo);
        String bankAccountId="62ab55fe7255fa4f2e123d73";
        when(bankAccountRepo.findById(bankAccountId)).thenReturn(bankAccount);
        BankAccount response= bankAccountService.getBankAccountById(bankAccountId);
        assertThat(response).isNotNull();
        assertThat(response.getUserName()).isEqualTo("admin12");
        assertThat(response.getBalance()).isEqualTo(8000.0);

    }

    @Test
    void should_return_updated_bank_account_object_when_updateBankAccount_with_bank_account_id() {
        Optional<BankAccount> bankAccount=Optional.of( BankAccount.builder()
                .id("62ab55fe7255fa4f2e123d73")
                .accountNumber("11111111")
                .balance(8000.0)
                .emailAddress("giriPriya@gamil.com")
                .routingNumber(1111)
                .type(AccountType.CHECKING)
                .userName("admin12")
                .build()) ;
        BankAccount updatedBankAccount= BankAccount.builder()
                .id("62ab55fe7255fa4f2e123d73")
                .accountNumber("11111111")
                .balance(7000.0)
                .emailAddress("giriPriya@gamil.com")
                .routingNumber(1111)
                .type(AccountType.CHECKING)
                .userName("admin12")
                .build() ;
        BankAccount updatedBankAccount1= BankAccount.builder()
                .id("62ab55fe7255fa4f2e123d73")
                .accountNumber("11111111")
                .balance(15000.0)
                .emailAddress("giriPriya@gamil.com")
                .routingNumber(1111)
                .type(AccountType.CHECKING)
                .userName("admin12")
                .build() ;
        String bankAccountId="62ab55fe7255fa4f2e123d73";
        bankAccountService= new BankAccountService(bankAccountRepo);
        when(bankAccountRepo.findById(bankAccountId)).thenReturn(bankAccount);
        when(bankAccountRepo.save(bankAccountArgumentCaptor.capture())).thenReturn(updatedBankAccount1);
        BankAccount response= bankAccountService.updateBankAccount(updatedBankAccount,bankAccountId);
        assertThat(response).isNotNull();
        assertThat(response.getBalance()).isEqualTo(15000.0);

    }

    @Test
    void should_return_updated_account_object_when_updateBankAccountCommunication_is_called(){
        bankAccountService=new BankAccountService(bankAccountRepo);
       BankAccount bankAccount= BankAccount.builder()
                .id("62ab55fe7255fa4f2e123d73")
                .accountNumber("11111111")
                .balance(8000.0)
                .emailAddress("giriPriya@gamil.com")
                .routingNumber(1111)
                .type(AccountType.CHECKING)
                .userName("admin12")
                .build();
       when(bankAccountRepo.save(bankAccountArgumentCaptor.capture())).thenReturn(bankAccount);
       BankAccount response= bankAccountService.updateBankAccountCommunication(bankAccount);
       assertThat(response).isNotNull();
       assertThat(response.getId()).isEqualTo("62ab55fe7255fa4f2e123d73");
        assertThat(response.getUserName()).isEqualTo("admin12");
        assertThat(response.getType()).isEqualTo(AccountType.CHECKING);



    }
}