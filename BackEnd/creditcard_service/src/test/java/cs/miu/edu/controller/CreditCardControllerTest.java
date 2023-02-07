package cs.miu.edu.controller;

import cs.miu.edu.domain.CreditCard;
import cs.miu.edu.service.CreditCardService;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
class CreditCardControllerTest {
    private CreditCardController creditCardController;
    @Mock
    private  CreditCardService creditCardService;
    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeEach
    void setUp() {
        creditCardController = new CreditCardController(creditCardService);
    }

@Captor
private ArgumentCaptor<String> stringCapture;
    @Captor
    private ArgumentCaptor<CreditCard> creditCardArgumentCaptor;
    @Test
    void saveCreditCard() {
    }

    @Test
    void should_return_creditcard_object_when_findCardById_is_called() {
        CreditCard creditCard= CreditCard.builder()
                .cardLimit(2000.0)
                .cardNumber("11111111")
                .userName("admin12")
                .creditCardId("62ab8fc1b379c861c190520b")
                .balance(30.0)
                .ccv("1111")
                .build();
        String creditCardId="62ab8fc1b379c861c190520b";
        when(creditCardService.getCreditCard(stringCapture.capture())).thenReturn(creditCard);
        CreditCard response= creditCardController.findCardById("62ab8fc1b379c861c190520b");
        assertThat(response).isNotNull();
        assertThat(response.getBalance()).isEqualTo(30.0);

    }

    @Test
    void should_return_list_or_credit_cards_when_getCreditCards_is_called() {
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
        when(creditCardController.getCreditCards()).thenReturn(creditCards);
        List<CreditCard> response=creditCardController.getCreditCards();
        assertThat(response).isNotNull();
        assertThat(response).isEqualTo(creditCards);

    }

    @Test
    void should_return_object_of_updated_credit_card_when_updateCreditCard_is_called_by_id() {
        CreditCard creditCard= CreditCard.builder()
                .cardLimit(2000.0)
                .cardNumber("11111111")
                .userName("admin12")
                .creditCardId("62ab8fc1b379c861c190520b")
                .balance(30.0)
                .ccv("1111")
                .build();
        String creditCardId= "62ab8fc1b379c861c190520b";
        CreditCard updatedCreditCard= CreditCard.builder()
                .cardLimit(2000.0)
                .cardNumber("11111111")
                .userName("admin12")
                .creditCardId("62ab8fc1b379c861c190520b")
                .balance(1000.0)
                .ccv("1111")
                .build();
        when(creditCardService.updateCreditCardLocal(creditCardArgumentCaptor.capture(),stringCapture.capture()))
                .thenReturn(updatedCreditCard);
        CreditCard response= creditCardController.updateCreditCard(creditCardId,creditCard);
        assertThat(response).isNotNull();
        assertThat(response.getBalance()).isEqualTo(1000.0);




    }

    @Test
    void testUpdateCreditCard() {
    }
}