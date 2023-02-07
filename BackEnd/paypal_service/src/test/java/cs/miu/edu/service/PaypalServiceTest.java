package cs.miu.edu.service;

import cs.miu.edu.domain.Paypal;
import cs.miu.edu.repository.PaypalRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.awt.print.Paper;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
class PaypalServiceTest {

    private PaypalService paypalService;
    @Mock
    private PaypalRepo paypalRepo;
    @Captor
    ArgumentCaptor<Paypal> paypalArgumentCaptor;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeEach
    void setUp() {
        paypalService = new PaypalService(paypalRepo);
    }

    @Test
    void should_return_paypal_object_when_savePaypal_is_called() {

        String userName = "admin12";
        Paypal paypal = Paypal.builder()
                .balance(1000.0)
                .emailAddress("giriPriya@gmail.com")
                .secureKey("1111")
                .build();
        Paypal savedPaypal = Paypal.builder()
                .balance(1000.0)
                .emailAddress("giriPriya@gmail.com")
                .secureKey("1111")
                .id("62ab81206ecf2333f9f3cf60")
                .userName("admin12")
                .build();
        when(paypalRepo.save(paypal)).thenReturn(savedPaypal);
        Paypal response = paypalService.savePaypal(paypal, userName);
        assertThat(response).isNotNull();
        assertThat(response.getUserName()).isEqualTo("admin12");


    }

    @Test
    void should_return_paypal_object_when_getPaypals_is_called() {
//        paypalService=new PaypalService(paypalRepo);

        List<Paypal> paypalList = Arrays.asList(Paypal.builder()
                .balance(1000.0)
                .emailAddress("giriPriya@gmail.com")
                .secureKey("1111")
                .id("62ab81206ecf2333f9f3cf60")
                .userName("admin12")
                .build(), Paypal.builder()
                .balance(1111.1)
                .emailAddress("giri@gmail.com")
                .secureKey("1111")
                .id("62ab81206ecf2333f9f3cf63")
                .userName("admin11")
                .build());
        when(paypalRepo.findAll()).thenReturn(paypalList);
        List<Paypal> response = paypalService.getPaypals();
        assertThat(response).isNotNull();
        assertThat(response).isEqualTo(paypalList);

    }

    @Test
    void should_return_paypal_object_when_paypalById_is_called_by_paypal_id() {
      Optional<Paypal> paypal=  Optional.of(Paypal.builder()
                .balance(1111.1)
                .emailAddress("giri@gmail.com")
                .secureKey("1111")
                .id("62ab81206ecf2333f9f3cf63")
                .userName("admin11")
                .build());
      String paypalId="62ab81206ecf2333f9f3cf63";
        when(paypalRepo.findById(paypalId)).thenReturn(paypal);
        Paypal response= paypalService.paypalById(paypalId);
        assertThat(response).isNotNull();
        assertThat(response.getUserName()).isEqualTo("admin11");
    }

    @Test
    void should_return_updated_paypal_object_when_updatePaypal_is_called_by_paypal_id() {
        Optional<Paypal> paypalOptional=  Optional.of(Paypal.builder()
                .balance(1111.0)
                .emailAddress("giri@gmail.com")
                .secureKey("1111")
                .id("62ab81206ecf2333f9f3cf63")
                .userName("admin11")

                .build());
        Paypal updatedPaypal=Paypal.builder()
                .balance(20000.0)
                .emailAddress("giri@gmail.com")
                .secureKey("1111")
                .id("62ab81206ecf2333f9f3cf63")
                .userName("admin11")
                .build();
        Paypal updatedPaypal1=Paypal.builder()
                .balance(21111.0)
                .emailAddress("giri@gmail.com")
                .secureKey("1111")
                .id("62ab81206ecf2333f9f3cf63")
                .userName("admin11")
                .build();


        String paypalId= "62ab81206ecf2333f9f3cf63";
        when(paypalRepo.findById(paypalId)).thenReturn(paypalOptional);
        when(paypalRepo.save(paypalArgumentCaptor.capture())).thenReturn(updatedPaypal1);
        Paypal response= paypalService.updatePaypal(updatedPaypal,paypalId);

        assertThat(response).isNotNull();
        assertThat(response.getBalance()).isEqualTo(21111.0);
    }

    @Test
    void should_return_paypal_object_when_updatepaypalcommunicaiton_is_called() {
        Paypal paypal=Paypal.builder()
                .balance(20000.0)
                .emailAddress("giri@gmail.com")
                .secureKey("1111")
                .id("62ab81206ecf2333f9f3cf63")
                .userName("admin11")
                .build();
        when(paypalRepo.save(paypalArgumentCaptor.capture())).thenReturn(paypal);
        Paypal response= paypalService.updatePayPalCommunticateion(paypal);
        assertThat(response).isNotNull();
        assertThat(response.getId()).isEqualTo("62ab81206ecf2333f9f3cf63");

    }
}