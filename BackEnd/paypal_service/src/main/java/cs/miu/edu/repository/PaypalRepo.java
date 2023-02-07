package cs.miu.edu.repository;

import cs.miu.edu.domain.Paypal;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Component
public interface PaypalRepo extends MongoRepository<Paypal,String> {

//    @Query("select paypal from Paypal  paypal where paypal.emailAddress=?1 and paypal.secureKey=?2")
//   public Optional<Paypal> getPaypal(String email,String secureKey);
//
//    public Optional<Paypal> getPaypalByUserName(String username);



}
