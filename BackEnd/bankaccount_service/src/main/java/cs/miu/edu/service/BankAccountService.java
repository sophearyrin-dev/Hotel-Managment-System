package cs.miu.edu.service;

import cs.miu.edu.domain.BankAccount;


import cs.miu.edu.repository.BankAccountRepo;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.util.List;


@Service
public class BankAccountService {


@Autowired
    private final BankAccountRepo bankAccountRepo;





    public BankAccountService(BankAccountRepo bankAccountRepo) {
        this.bankAccountRepo = bankAccountRepo;
    }



    public BankAccount saveBankAccount(BankAccount bankAccount, String username) {

            bankAccount.setUserName(username);
            return bankAccountRepo.save(bankAccount);


    }

    public List<BankAccount> getCreditCards(){
        return  bankAccountRepo.findAll();
    }
    public BankAccount getBankAccountById(String creditCardId) {
        return bankAccountRepo.findById(creditCardId).get();
    }

    public BankAccount updateBankAccount(BankAccount bankAccount,String bankAccountId) {
      BankAccount bankAccount1= getBankAccountById(bankAccountId);
      if(bankAccount.getBalance() != null){
          bankAccount1.setBalance(bankAccount.getBalance()+bankAccount1.getBalance());
      }
      return bankAccountRepo.save(bankAccount);
    }

    public  BankAccount updateBankAccountCommunication(BankAccount bankAccount){

        return  bankAccountRepo.save(bankAccount);
    }
}
