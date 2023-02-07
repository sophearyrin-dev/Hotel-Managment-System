package com.example.rateservice.controller;
import com.example.rateservice.jwt.JwtUtils;
import com.example.rateservice.model.Rate;
import com.example.rateservice.repository.RateRepository;
import com.example.rateservice.service.RateService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@ApiResponse(description = "Rating service for rating room of MIU hotel")
public class RateController {

    @Autowired
    RateRepository raterepository;

    @Autowired
    RateService rateService;

    @Autowired
    JwtUtils jwtUtils;

//    @GetMapping("/")
//    public ResponseEntity<List<Rate>> findAll() {
//        try{
//            List<Rate> rates = rateService.findAll();
//            return new ResponseEntity<List<Rate>>(rates, HttpStatus.FOUND);
//        }
//        catch (Exception e){
//            return new ResponseEntity<List<Rate>>(HttpStatus.NOT_FOUND);
//        }
//    }

    @GetMapping("/")
    public List<Rate> findAll(){
        return raterepository.findAll();
    }

    @PostMapping("/")
    public ResponseEntity<?> addRate(@RequestBody Rate rate, HttpServletRequest request)
            throws JsonProcessingException {
        Cookie cookie = WebUtils.getCookie(request, "subo8");
        if (cookie != null) {
            String jwt = cookie.getValue();
            String userId = jwtUtils.getUserIdFromJwtToken(jwt);
            rate.setUserId(userId);
            rateService.addRate(rate);
            return new ResponseEntity<>("Rate has been added successfully.", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Please Login first.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{rateID}")
    public ResponseEntity<?> updateRate(@PathVariable("rateID") String rateID,
            @RequestBody @Valid Rate rate, HttpServletRequest request) {
        Cookie cookie = WebUtils.getCookie(request, "subo8");
        if (cookie != null) {
            String jwt = cookie.getValue();
            String userId = jwtUtils.getUserIdFromJwtToken(jwt);
            rate.setUserId(userId);
            List<Rate> rates = raterepository.findAll();
            for (int i = 0; i < rates.size(); i++) {
                if (rates.get(i).getUserId().compareTo(userId) == 0 && rates.get(i).getId().compareTo(rateID) == 0) {
                    rateService.updateRate(rateID, rate);
                    return new ResponseEntity<>("Rate has been updated successfully.", HttpStatus.OK);
                }
            }
            return new ResponseEntity<>("Rate fail to update. You cannot update other user rating.",
                    HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>("Please Login first.", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("/{rateID}")
    public ResponseEntity<?> deleteRate(@PathVariable("rateID") String rateID, HttpServletRequest request, Rate rate) {
        Cookie cookie = WebUtils.getCookie(request, "subo8");
        if (cookie != null) {
            String jwt = cookie.getValue();
            String userId = jwtUtils.getUserIdFromJwtToken(jwt);
            rate.setUserId(userId);
            rateService.deleteRate(rateID);
            return new ResponseEntity<>("Rate has been deleted successfully.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Please Login first.", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
