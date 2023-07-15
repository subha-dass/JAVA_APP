package com.red.rediswithspringboot.repo;

import com.red.rediswithspringboot.Model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Repository
public class UserRepo {
    @Autowired
    RedisTemplate<String, UserModel> redisTemplate;

    @Autowired
    EntityManager entityManager;
    private static final Integer PERSON_VALUE_EXPIRY = 1;

    private static final String PERSON_KEY_PREFIX = "person::";



    @Transactional
    public void set(UserModel userModel){
        entityManager.persist(userModel);
        int id=(userModel.getId());
        System.out.println("user model id is"+id);
        this.redisTemplate.opsForValue().set(getKey(String.valueOf(id)), userModel, PERSON_VALUE_EXPIRY, TimeUnit.DAYS);
    }
    private String getKey(String personId){
        return PERSON_KEY_PREFIX + personId;
    }

    public Set<String> getAllkeys(){
        return this.redisTemplate.keys(PERSON_KEY_PREFIX + "*");
    }

    public UserModel getByKey(String key) {
        return this.redisTemplate.opsForValue().get(key);
    }


    public void deleteKeys(int id) {
        String key=PERSON_KEY_PREFIX+String.valueOf(id);
        System.out.println(key);
        redisTemplate.delete(key);
    }
}
