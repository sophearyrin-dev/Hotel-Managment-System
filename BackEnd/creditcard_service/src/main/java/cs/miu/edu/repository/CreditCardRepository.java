package cs.miu.edu.repository;

import cs.miu.edu.domain.CreditCard;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardRepository  extends MongoRepository<CreditCard, String> {
}
