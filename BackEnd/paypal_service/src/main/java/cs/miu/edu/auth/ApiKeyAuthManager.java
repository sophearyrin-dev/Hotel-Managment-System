///**
// * Copyright 2019 Greg Whitaker
// *
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// *    http://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
//package cs.miu.edu.auth;
//
//import com.github.benmanes.caffeine.cache.CacheLoader;
//import com.github.benmanes.caffeine.cache.Caffeine;
//import com.github.benmanes.caffeine.cache.LoadingCache;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.stereotype.Component;
//
//import java.util.concurrent.TimeUnit;
//
///**
// * Handles authenticating api keys against the database.
// */
//
//@Component
//public class ApiKeyAuthManager implements AuthenticationManager {
//    private static final Logger LOG = LoggerFactory.getLogger(ApiKeyAuthManager.class);
//
//    @Value("${paypal.service.key}")
//    private String serviceKey;
//
//    private final LoadingCache<String, Boolean> keys;
//
//    public ApiKeyAuthManager() {
//        this.keys = Caffeine.newBuilder()
//                .expireAfterAccess(5, TimeUnit.MINUTES)
//                .build(new DatabaseCacheLoader());
//    }
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        String principal = (String) authentication.getPrincipal();
//
//        if (!keys.get(principal)) {
//            throw new BadCredentialsException("The API key was not found or not the expected value.");
//        } else {
//            authentication.setAuthenticated(true);
//            return authentication;
//        }
//    }
//
//    /**
//     * Caffeine CacheLoader that checks the database for the api key if it not found in the cache.
//     */
//    private  class DatabaseCacheLoader implements CacheLoader<String, Boolean> {
//
//
//        @Override
//        public Boolean load(String key) throws Exception {
//            LOG.info("Loading api key from database: [key: {}]", key);
//            return key.equals(serviceKey);
//        }
//    }
//}
