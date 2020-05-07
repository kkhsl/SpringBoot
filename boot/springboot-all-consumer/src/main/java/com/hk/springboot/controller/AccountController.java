package com.hk.springboot.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.hkk.springboot.datasource.modul.Account;
import com.hkk.springboot.datasource.service.AccountService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/account")
public class AccountController {

   // protected Integer pageSize = 5;//默认分页查询，一页10条

    @Reference
    private AccountService accountService;

    @RequestMapping("/list")
    public String account(Model model,
                          @RequestParam(name="currentPage", required = false) Integer currentPage){
        if(currentPage == null || currentPage < 1){
            currentPage = 1;
        }

        int pageSize = 5;
        int totalRows = accountService.getAccountByTotal();//查询总数
        int tatolPages = totalRows/pageSize; //计算总页数
        int left = totalRows % pageSize;
        if(left > 0){
            tatolPages = tatolPages + 1;
        }
        if(currentPage > tatolPages){
            currentPage = tatolPages;
        }
        int startRow = (currentPage - 1) * pageSize;//起始行
        Map<String, Object> paramMap =  new HashMap();
        paramMap.put("startRow", startRow);
        paramMap.put("pageSize", pageSize);
        List<Account> accounts = accountService.getObjByPage(paramMap);
        System.out.println(accounts);
        model.addAttribute("accounts", accounts);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalRows", tatolPages);
        //跳转模板页面
        return "index";
    }


    @RequestMapping("/insert")
    public String insert(Model model){
        return "account_insert";
    }

    @RequestMapping("/update")
    public String update(Model model,
                      @RequestParam("id") Integer id){
        Account account = this.accountService.getObjById(id.longValue());
        model.addAttribute("account",account);
        return "account_insert";
    }

    @RequestMapping("/del")
    public String del(Model model,
                      Integer id){
       this.accountService.delete(id.longValue());
        return "redirect:/account/list";
    }

    /**
     * @description add/update
     * @param account
     * @return
     */
    @RequestMapping("/save")
    public String save(Account account){
        if(null == account.getId()){
            this.accountService.save(account);
        }else{
            this.accountService.update(account);
        }
        return "redirect:/account/list";
    }
}
