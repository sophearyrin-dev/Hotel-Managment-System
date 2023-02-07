package cs.miu.edu.repository;

import cs.miu.edu.domain.BankAccount;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;




@Repository
public interface BankAccountRepo extends MongoRepository<BankAccount,String> {


//    @Query("select bankaccount from BankAccount  bankaccount where bankaccount.accountNumber=?1 and bankaccount.type =?2 and bankaccount.routingNumber=?3")
//  public Optional<BankAccount> getBankaccountByAccountNoAccountTypeRoutingNo(String accountNo, AccountType accountType,Integer routingNo);


}
