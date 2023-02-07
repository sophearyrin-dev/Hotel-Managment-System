package cs.miu.edu.service;

import cs.miu.edu.domain.CreditCard;
import cs.miu.edu.repository.CreditCardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
class CreditCardServiceTest {
    private CreditCardService creditCardService;
    @Mock
    private CreditCardRepository creditCardRepository;
    @Captor
    ArgumentCaptor<CreditCard> creditCardArgumentCaptor;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeEach
    void setUp() {
        creditCardService = new CreditCardService(creditCardRepository);
    }

    @Test
    void should_return_creditcard_object_when_savecreditcard_is_called() {
        CreditCard creditCard = CreditCard.builder()
                .cardLimit(2000.0)
                .cardNumber("11111111")
                .balance(30.0)
                .ccv("1111")
                .build();
        String userName = "admin12";
        CreditCard savedCreditCard = CreditCard.builder()
                .cardLimit(2000.0)
                .cardNumber("11111111")
                .userName("admin12")
                .creditCardId("62ab8fc1b379c861c190520b")
                .balance(30.0)
                .ccv("1111")
                .build();
        when(creditCardRepository.save(creditCardArgumentCaptor.capture())).thenReturn(savedCreditCard);
        CreditCard response = creditCardService.saveCreditCard(creditCard, userName);
        assertThat(response).isNotNull();
        assertThat(response.getCreditCardId()).isEqualTo("62ab8fc1b379c861c190520b");

    }

    @Test
    void should_return_list_of_creditcards_when_getallcreditcards_is_called() {
        List<CreditCard> creditCards = Arrays.asList(CreditCard.builder()
                        .cardLimit(2000.0)
                        .cardNumber("11111111")
                        .userName("admin12")
                        .creditCardId("62ab8fc1b379c861c190520b")
                        .balance(30.0)
                        .ccv("1111")
                        .build(),
                CreditCard.builder()
                        .cardLimit(2000.0)
                        .cardNumber("22222222")
                        .userName("admin12")
                        .creditCardId("62ab8fc1b379c861c190520e")
                        .balance(30.0)
                        .ccv("2222")
                        .build());
        when(creditCardRepository.findAll()).thenReturn(creditCards);
        List<CreditCard> response = creditCardService.getCreditCards();
        assertThat(response).isNotNull();
        assertThat(response).isEqualTo(creditCards);
    }

    @Test
    void should_return_creditcard_when_getcreditcards_is_called_by_creditcard_id() {
        CreditCard creditCard = CreditCard.builder()
                .cardLimit(2000.0)
                .cardNumber("22222222")
                .userName("admin12")
                .creditCardId("62ab8fc1b379c861c190520e")
                .balance(30.0)
                .ccv("2222")
                .build();
        when(creditCardRepository.findById("62ab8fc1b379c861c190520e")).thenReturn(Optional.of(creditCard));
        CreditCard response = creditCardService.getCreditCard("62ab8fc1b379c861c190520e");
        assertThat(response).isNotNull();
        assertThat(response.getCreditCardId()).isEqualTo("62ab8fc1b379c861c190520e");

    }

    @Test
    void should_return_creditcard_when_updatecreditcard_is_called() {
        CreditCard creditCard = CreditCard.builder()
                .cardLimit(2000.0)
                .cardNumber("11111111")
                .balance(30.0)
                .ccv("1111")
                .build();
        CreditCard updateCard = CreditCard.builder()
                .cardLimit(2000.0)
                .cardNumber("11111111")
                .userName("admin12")
                .creditCardId("62ab8fc1b379c861c190520b")
                .balance(50.0)
                .ccv("1111")
                .build();
        when(creditCardRepository.save(creditCardArgumentCaptor.capture())).thenReturn(updateCard);
        CreditCard response = creditCardService.updateCreditCard(creditCard);
        assertThat(response).isNotNull();
        assertThat(response.getBalance()).isEqualTo(50.0);
    }

    @Test
    void should_return_update_creditcard_by_id_updateCreditCardLocal_when_it_is_called() {
        CreditCard creditCard1 = CreditCard.builder()
                .cardLimit(2000.0)
                .cardNumber("22222222")
                .userName("admin12")
                .creditCardId("62ab8fc1b379c861c190520e")
                .balance(30.0)
                .ccv("2222")
                .build();
        String creditCardId = "62ab8fc1b379c861c190520e";
        when(creditCardRepository.findById(creditCardId)).thenReturn(Optional.of(creditCard1));
        CreditCard creditCard = CreditCard.builder()
                .cardLimit(2000.0)
                .cardNumber("22222222")
                .userName("admin12")
                .creditCardId("62ab8fc1b379c861c190520e")
                .balance(1000.0)
                .ccv("2222")
                .build();
        CreditCard savedCreditCard = CreditCard.builder()
                .cardLimit(2000.0)
                .cardNumber("22222222")
                .userName("admin12")
                .creditCardId("62ab8fc1b379c861c190520e")
                .balance(1030.0)
                .ccv("2222")
                .build();
        when(creditCardRepository.save(creditCardArgumentCaptor.capture())).thenReturn(savedCreditCard);
        CreditCard response = creditCardService.updateCreditCardLocal(creditCard, creditCardId);
        assertThat(response).isNotNull();
        assertThat(response.getBalance()).isEqualTo(1030.0);

    }

    @Test
    void should_return_creditcard_when_getcreditcard_is_called_by_creditcard_id() {
        CreditCard creditCard = CreditCard.builder()
                .cardLimit(2000.0)
                .cardNumber("22222222")
                .userName("admin12")
                .creditCardId("62ab8fc1b379c861c190520e")
                .balance(30.0)
                .ccv("2222")
                .build();
        when(creditCardRepository.findById("62ab8fc1b379c861c190520e")).thenReturn(Optional.of(creditCard));
        CreditCard response = creditCardService.getCreditCards("62ab8fc1b379c861c190520e");
        assertThat(response).isNotNull();
        assertThat(response.getCreditCardId()).isEqualTo("62ab8fc1b379c861c190520e");

    }
}