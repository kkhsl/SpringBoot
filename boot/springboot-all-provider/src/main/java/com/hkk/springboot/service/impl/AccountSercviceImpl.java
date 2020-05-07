package com.hkk.springboot.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.hkk.springboot.datasource.mappers.AccountMapper;
import com.hkk.springboot.datasource.modul.Account;
import com.hkk.springboot.datasource.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 将服务提供者注册到注册中心
 *  1）导入dubbo依赖
 *
 * 让服务消费者去注册中心订阅服务提供者的服务地址
 */
@Service//使用dubbo @Service注解 + spring @Component
@Component//使用spring注解注册bean到spring容器中
public class AccountSercviceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Override
    public List<Account> getObjByPage(Map<String, Object> paramMap) {

        return accountMapper.selectByPage(paramMap);
    }

    @Override
    public Account getObjById(Long id) {
        return this.accountMapper.selectByPrimaryKey(id);
    }

    @Override
    public int getAccountByTotal() {
        //设置key的序列化方式，采用字符串方式，可读性较好
        RedisSerializer redisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(redisSerializer);

        //获取总数
        Integer totalRows = (Integer) redisTemplate.opsForValue().get("totalRows");
        if(null == totalRows) {
            synchronized(this){
                totalRows = (Integer) redisTemplate.opsForValue().get("totalRows");
                if(null == totalRows){
                    totalRows = accountMapper.selectAccountByTatol();
                    redisTemplate.opsForValue().set("totalRows", totalRows);
                }
            }
        }
        return totalRows;
    }

    @Override
    public int save(Account account) {
       int add =  this.accountMapper.insertSelective(account);
       if(add > 0){
           redisTemplate.setKeySerializer(new StringRedisSerializer());
            //更新缓存
           Integer totalRows = accountMapper.selectAccountByTatol();
           redisTemplate.opsForValue().set("totalRows", totalRows);
       }
        return  add;
    }

    @Override
    public int update(Account account) {
        return this.accountMapper.updateByPrimaryKeySelective(account);
    }

    @Override
    public int delete(Long id) {
        int delete = this.accountMapper.deleteByPrimaryKey(id);
        if(delete > 0){
            redisTemplate.setKeySerializer(new StringRedisSerializer());
            //更新缓存
            Integer totalRows = accountMapper.selectAccountByTatol();
            redisTemplate.opsForValue().set("totalRows", totalRows);
        }
        return delete;
    }
}
