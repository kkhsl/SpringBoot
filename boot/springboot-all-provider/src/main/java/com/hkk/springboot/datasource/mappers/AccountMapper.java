package com.hkk.springboot.datasource.mappers;

import com.hkk.springboot.datasource.modul.Account;

import java.util.List;
import java.util.Map;

import com.hkk.springboot.datasource.domain.AccountExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface AccountMapper {
    long countByExample(AccountExample example);

    int deleteByExample(AccountExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Account record);

    int insertSelective(Account record);

    List<Account> selectByExample(AccountExample example);

    Account selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Account record, @Param("example") AccountExample example);

    int updateByExample(@Param("record") Account record, @Param("example") AccountExample example);

    int updateByPrimaryKeySelective(Account record);

    int updateByPrimaryKey(Account record);

    List<Account> selectByPage(Map<String, Object> paramMap);

    int selectAccountByTatol();
}