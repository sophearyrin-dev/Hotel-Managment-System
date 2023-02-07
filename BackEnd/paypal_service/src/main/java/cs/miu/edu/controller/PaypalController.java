package cs.miu.edu.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cs.miu.edu.domain.Paypal;

import cs.miu.edu.security.jwt.JwtUtils;
import cs.miu.edu.service.PaypalService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/paypals")
public class PaypalController {

    @Autowired
    JwtUtils jwtUtils;

    private  final PaypalService paypalService;

    public PaypalController(PaypalService paypalService){
        this.paypalService=paypalService;
    }



    @PostMapping
    public Paypal savePaypal(@RequestBody Paypal paypal, HttpServletRequest request) {
        Cookie cookie = WebUtils.getCookie(request, "subo8");
        if (cookie != null) {
            String jwt = cookie.getValue();
            String username = jwtUtils.getUserNameFromJwtToken(jwt);
            paypal.setUserName(username);
             return paypalService.savePaypal(paypal, username);
        }
       return null;
    }

    @GetMapping("/{paypalId}")
    public Paypal findPaypalById(@PathVariable String paypalId) {
        return paypalService.paypalById(paypalId);
    }

    @PutMapping("/{paypalId}")
    public Paypal updatePaypal(@PathVariable String paypalId, @RequestBody Paypal paypal) {
        return paypalService.updatePaypal(paypal,paypalId);
    }

    @PutMapping("/")
    public  Paypal updatePaypalServiceLevel(@RequestBody String paypal){
        ObjectMapper objectMapper=new ObjectMapper();
        Paypal paypal1=null;
        try {
            paypal1=objectMapper.readValue(paypal,Paypal.class);
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return paypalService.updatePayPalCommunticateion(paypal1);
    }
}