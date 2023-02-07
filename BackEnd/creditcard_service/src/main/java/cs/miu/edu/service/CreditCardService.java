package cs.miu.edu.service;

import cs.miu.edu.domain.CreditCard;
//import cs.miu.edu.mapper.Mapper;
import cs.miu.edu.jwt.JwtUtils;
import cs.miu.edu.repository.CreditCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
public class CreditCardService {

    @Autowired
    private final CreditCardRepository creditCardRepository;

    public CreditCardService(CreditCardRepository creditCardRepository) {
        this.creditCardRepository = creditCardRepository;
    }



    public CreditCard saveCreditCard(CreditCard creditCard, String userName) {
        //   CreditCard booking1 = new CreditCard();

        creditCard.setUserName(userName);
        return creditCardRepository.save(creditCard);


    }

    public List<CreditCard> getCreditCards() {
        return creditCardRepository.findAll();

    }

    public CreditCard getCreditCard(String creditCardId) {
        return creditCardRepository.findById(creditCardId).get();
    }

    public CreditCard updateCreditCard(CreditCard creditCard) {
        return creditCardRepository.save(creditCard);
    }

    public CreditCard updateCreditCardLocal(CreditCard creditCard, String creditCardId) {

       CreditCard creditCard1= creditCardRepository.findById(creditCardId).get();
       creditCard1.setBalance(creditCard1.getBalance()+creditCard.getBalance());
        creditCard.setCreditCardId(creditCardId);
        creditCard.setBalance(creditCard1.getBalance());
        return creditCardRepository.save(creditCard);


    }

    public CreditCard getCreditCards(String creditCardId) {
        return creditCardRepository.findById(creditCardId).get();
    }

}









