package sc.miu.edu.payment_service.utils;


import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sc.miu.edu.payment_service.domain.PaymentInformation;
import sc.miu.edu.payment_service.repository.PaymentRepo;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Component
public class GeneratorClass {
    @Autowired
    private PaymentRepo paymentRepo;


    public String transactionCodeGenerator() {
        try{
            PaymentInformation paymentInformation= paymentRepo.findTopByOrderByTransactionDateDesc();

            String transactionCode =  paymentInformation.getTransactionCode();
            System.out.println(transactionCode);
            String subString = transactionCode.substring(1);
            int integerValue = Integer.parseInt(subString);
            integerValue++;
            String newBadgeCode = "P" + integerValue;
            return newBadgeCode;
        }catch (NullPointerException ex){
            return "P101";
        }

    }


}
