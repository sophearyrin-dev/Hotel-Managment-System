package cs.miu.edu.controller;

import cs.miu.edu.domain.Paypal;
import cs.miu.edu.service.PaypalService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class PaypalControllerTest {
    private PaypalController paypalController;
    @Mock
    private PaypalService paypalService;
    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeEach
    void setUp() {
        paypalController = new PaypalController(paypalService);
    }
    @Captor
    private ArgumentCaptor<String> stringArgumentCaptor;
    @Captor
    private ArgumentCaptor<Paypal> paypalArgumentCaptor;


    @Test
    void savePaypal() {

    }

    @Test
    void should_return_paypal_object_findPaypalById_is_called_by_id() {
      Paypal paypal=  Paypal.builder()
                .balance(20000.0)
                .emailAddress("giri@gmail.com")
                .secureKey("1111")
                .id("62ab81206ecf2333f9f3cf63")
                .userName("admin11")
                .build();
      String paypalId= "62ab81206ecf2333f9f3cf63";
        when(paypalService.paypalById(stringArgumentCaptor.capture())).thenReturn(paypal);
        Paypal response= paypalController.findPaypalById(paypalId);
        assertThat(response).isNotNull();
        assertThat(response.getUserName()).isEqualTo("admin11");

    }

    @Test
    void should_return_updated_paypal_when_updatePaypal_is_called_by_id() {
        Paypal paypal=  Paypal.builder()
                .balance(20000.0)
                .emailAddress("giri@gmail.com")
                .secureKey("1111")
                .id("62ab81206ecf2333f9f3cf63")
                .userName("admin11")
                .build();
        String paypalId= "62ab81206ecf2333f9f3cf63";
        Paypal paypal1=  Paypal.builder()
                .balance(30000.0)
                .emailAddress("giri@gmail.com")
                .secureKey("1111")
                .id("62ab81206ecf2333f9f3cf63")
                .userName("admin11")
                .build();
        when(paypalService.updatePaypal(paypalArgumentCaptor.capture(),stringArgumentCaptor.capture())).thenReturn(paypal1);
        Paypal response= paypalController.updatePaypal(paypalId,paypal);
        assertThat(response).isNotNull();
        assertThat(response.getBalance()).isEqualTo(30000.0);
    }
//
//    @Test
//    void updatePaypalServiceLevel() {
//    }
}