package cs.miu.edu;

import cs.miu.edu.domain.Paypal;
import cs.miu.edu.repository.PaypalRepo;
import cs.miu.edu.service.PaypalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication()

//@EnableDiscoveryClient
public class PaypalServiceApplication  {
    @Autowired
    private PaypalRepo paypalRepo;

    public static void main(String[] args) {
        SpringApplication.run(PaypalServiceApplication.class, args);
    }




}
