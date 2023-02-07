package cs.miu.edu;

import cs.miu.edu.domain.AccountType;
import cs.miu.edu.domain.BankAccount;


import cs.miu.edu.repository.BankAccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import java.util.Arrays;
import java.util.List;

@SpringBootApplication
//@EnableDiscoveryClient
public class BankAccountServiceApplication  {
    @Autowired
    private BankAccountRepo bankAccountRepo;

    public static void main(String[] args) {
        SpringApplication.run(BankAccountServiceApplication.class, args);
    }
}



