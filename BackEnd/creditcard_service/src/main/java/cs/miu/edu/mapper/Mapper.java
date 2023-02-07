//package cs.miu.edu.mapper;
//
//import cs.miu.edu.domain.CreditCard;
//import cs.miu.edu.dto.CreditCardDto;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDate;
//
//@Component
//public class Mapper {
//    public CreditCardDto mapToDto(CreditCard creditCard) {
//        CreditCardDto creditCardDto = CreditCardDto.builder()
//                .firstName(creditCard.getFirstName())
//                .lastName(creditCard.getLastName())
//                .cardNumber(creditCard.getCardNumber())
//                .ccv(creditCard.getCcv())
//                .expiryDate(creditCard.getExpiryDate())
//                .build();
//        return creditCardDto;
//    }
//
//    public CreditCard mapToTransaction(CreditCardDto creditCardDto) {
//        CreditCard creditCard = CreditCard.builder()
//                .firstName(creditCardDto.getFirstName())
//                .lastName(creditCardDto.getLastName())
//                .cardNumber(creditCardDto.getCardNumber())
//                .ccv(creditCardDto.getCcv())
//                .expiryDate(creditCardDto.getExpiryDate())
//                .build();
//        return creditCard;
//    }
//}
