package com.example.rateservice.controller;

import com.example.rateservice.jwt.JwtUtils;
import com.example.rateservice.model.Rate;
import com.example.rateservice.repository.RateRepository;
import com.example.rateservice.service.RateService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@SpringBootTest(classes = {RateControllerTest.class})
public class RateControllerTest {

    @Mock
    RateService rateService;

    @InjectMocks
    RateController rateController;

    @Mock
    RateRepository raterepository;

    @InjectMocks
    JwtUtils jwtUtils;
    List<Rate> rates;
    Rate rate;

    @Test
    public void getAllRateTest(){
        rates = new ArrayList<Rate>();
        rates.add(new Rate("1","1","1",4));
        rates.add(new Rate("1","1","1",5));
        when(raterepository.findAll()).thenReturn(rates);
        assertThat(2).isEqualTo(rates.size());
    }

    @Test
    public void insertRateTest(){
        Rate rate = new Rate("1","1",4);
        when(raterepository.save(rate)).thenReturn(rate);
        assertEquals(rate, raterepository.save(rate));
    }

    @Test
    public void deleteRate(){
        Rate rate =  new Rate("1", "1", "1", 5);
        raterepository.delete(rate);
        verify(raterepository, times(1)).delete(rate);
    }

//    @Test
//    public void test2(HttpServletRequest request){
//        Cookie cookie = WebUtils.getCookie(request, "subo8");
//        Rate rate =  new Rate("1", "1", "1", 5);
//    }

//    @Test
//    public void test2() throws JsonProcessingException {
//        MockHttpServletRequest request = new MockHttpServletRequest();
//        request.setParameter("1","1");
//        when(request.getParameter("1")).thenReturn("1");
//        Cookie cookie = WebUtils.getCookie(request,"1");
////        Cookie cookie = WebUtils.getCookie(request, "subo8");
////        String jwt = cookie.getValue();
//        String userId = "1";
//
////        cookie.setValue("1");
//        Rate rate =  new Rate("1", "1", "1", 5);
//        when(rateService.addRate(rate)).thenReturn(rate);
//        assertThat(rate).isEqualTo(rateController.addRate(rate, request ));
//    }

//    @Test
//    public void test3() throws Exception{
//            String parm = "test";
//            this.mockMvc.perform();
//    }
}
