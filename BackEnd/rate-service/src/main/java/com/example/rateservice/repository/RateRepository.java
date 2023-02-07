package com.example.rateservice.repository;

import com.example.rateservice.model.Rate;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RateRepository extends MongoRepository<Rate, String> {
}
