package com.hkk.springboot.datasource.service;

import com.hkk.springboot.datasource.modul.Account;

import java.util.List;
import java.util.Map;

public interface AccountService {

    /**
     * @description 获取一个Account对象集合
     * @param paramMap
     * @return
     */
    List<Account> getObjByPage(Map<String, Object> paramMap);

    Account getObjById(Long id);
    /**
     * @description 获取分页总数
     * @return
     */
    int getAccountByTotal();

    int save(Account account);

    public int update(Account account);

    public int delete(Long id);
}
