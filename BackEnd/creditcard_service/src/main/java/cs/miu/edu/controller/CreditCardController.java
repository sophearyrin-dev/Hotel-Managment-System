package cs.miu.edu.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cs.miu.edu.domain.CreditCard;
import cs.miu.edu.jwt.JwtUtils;
import cs.miu.edu.service.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/creditcards")
public class CreditCardController {

    @Autowired
    JwtUtils jwtUtils;

    private final CreditCardService creditCardService;

    public CreditCardController(CreditCardService creditCardService) {
        this.creditCardService = creditCardService;
    }
@PostMapping
@CrossOrigin("http://localhost:3000")
public  CreditCard saveCreditCard(@RequestBody CreditCard creditCard , HttpServletRequest request) {

    Cookie cookie = WebUtils.getCookie(request, "subo8");
    if (cookie != null) {
        String jwt = cookie.getValue();
        String username = jwtUtils.getUserNameFromJwtToken(jwt);
        return creditCardService.saveCreditCard(creditCard, username);

    }
    return null;
}

@GetMapping("/{creditCardId}")
@CrossOrigin("http://localhost:3000")
    public   CreditCard findCardById(@PathVariable String creditCardId){
        return  creditCardService.getCreditCard(creditCardId);
}
@GetMapping
@CrossOrigin("http://localhost:3000")
    public List<CreditCard> getCreditCards(){
        return creditCardService.getCreditCards();
}

    @PutMapping("/{creditCardId}")
    public   CreditCard updateCreditCard(@PathVariable String creditCardId, @RequestBody CreditCard creditCard){
        return  creditCardService.updateCreditCardLocal(creditCard, creditCardId);
    }

    @PutMapping("/")
    public CreditCard updateCreditCard(@RequestBody String creditCard) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        CreditCard room1 = new CreditCard();
        try {
            room1 = objectMapper.readValue(creditCard, CreditCard.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return creditCardService.updateCreditCard(room1);

    }




}
