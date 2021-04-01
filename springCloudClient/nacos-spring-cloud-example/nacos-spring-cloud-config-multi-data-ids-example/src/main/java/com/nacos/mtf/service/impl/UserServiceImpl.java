package com.nacos.mtf.service.impl;

import com.nacos.mtf.model.User;
import com.nacos.mtf.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

/**
 * @author hexu.hxy
 * @date 2019/1/7
 */

@Service
@RefreshScope //通过 Spring Cloud 原生注解 @RefreshScope 实现配置自动更新：
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

//    private final UserRepository userRepository;
//
//    private final RedisTemplate redisTemplate;

    @Value("${app.user.cache}")
    private boolean cache;

//    @Autowired
//    public UserServiceImpl(UserRepository userRepository, RedisTemplate redisTemplate) {
//        this.userRepository = userRepository;
//        this.redisTemplate = redisTemplate;
//    }

    @Override
    public User findById(Long id) {
        LOGGER.info("cache: {}", cache);
        User user = new User();
        user.setName("张三:"+cache);
        user.setId(1234L);
//        if (cache) {
//            Object obj = redisTemplate.opsForValue().get(key(id));
//            if (obj != null) {
//                LOGGER.info("get user from cache, id: {}", id);
//                return (User)obj;
//            }
//        }
//
//        User user = userRepository.findById(id).orElse(null);
//        if (user != null) {
//            if (cache) {
//                LOGGER.info("set cache for user, id: {}", id);
//                redisTemplate.opsForValue().set(key(id), user);
//            }
//        }

        return user;
    }

    private String key(Long id) {
        return String.format("nacos-spring-cloud-config-multi-data-ids-example:user:%d", id);
    }

}