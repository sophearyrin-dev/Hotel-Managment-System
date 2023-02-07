package cs.miu.edu.service;

import cs.miu.edu.domain.Paypal;

import cs.miu.edu.repository.PaypalRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service

public class PaypalService {
    @Autowired
    private final PaypalRepo paypalRepo;


    public PaypalService(PaypalRepo paypalRepo) {
        this.paypalRepo = paypalRepo;
    }


    public Paypal savePaypal(Paypal paypal, String username) {
       paypal.setUserName(username);
        return paypalRepo.save(paypal);


    }

    public List<Paypal> getPaypals() {
        return paypalRepo.findAll();
    }


    public Paypal paypalById(String paypalId) {
        return paypalRepo.findById(paypalId).get();
    }

    public Paypal updatePaypal(Paypal paypal, String paypalId) {
        Paypal paypal1 = paypalById(paypalId);
        if (paypal.getBalance() != null) ;
        paypal1.setBalance(paypal.getBalance());
        return paypalRepo.save(paypal1);
    }

    public Paypal updatePayPalCommunticateion(Paypal paypal) {
        return paypalRepo.save(paypal);
    }
}
