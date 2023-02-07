package cs.miu.edu.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cs.miu.edu.domain.BankAccount;
import cs.miu.edu.dto.BankAccountDto;
import cs.miu.edu.security.jwt.JwtUtils;
import cs.miu.edu.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/bankaccounts")
public class BankAccountController {

    private final BankAccountService bankAccountService;

    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping
    public BankAccount saveCreditCard(@RequestBody BankAccount bankAccount , HttpServletRequest httpServletRequest){
        Cookie cookie = WebUtils.getCookie(httpServletRequest, "subo8");
        if (cookie != null) {
            String jwt = cookie.getValue();
            String username = jwtUtils.getUserNameFromJwtToken(jwt);
            return bankAccountService.saveBankAccount(bankAccount, username);
        }
        return null;
    }

    @GetMapping("/{creditCardId}")
    public   BankAccount findCardById(@PathVariable String creditCardId){
        return  bankAccountService.getBankAccountById(creditCardId);
    }

    @PutMapping("/{bankAccountId}")
    public   BankAccount updateCreditCard(@PathVariable String bankAccountId, @RequestBody BankAccount bankAccount){
        return  bankAccountService.updateBankAccount(bankAccount,bankAccountId);
    }

    @PutMapping("/")
    public   BankAccount updateCreditCard( @RequestBody String bankAccount){
       ObjectMapper objectMapper=new ObjectMapper();
        BankAccount bankAccount1=null;
        try {
            bankAccount1=objectMapper.readValue(bankAccount,BankAccount.class);
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return bankAccountService.updateBankAccountCommunication(bankAccount1);
    }


}
