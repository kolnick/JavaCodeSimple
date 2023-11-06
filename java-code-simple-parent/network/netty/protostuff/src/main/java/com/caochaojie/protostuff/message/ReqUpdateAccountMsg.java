package com.caochaojie.protostuff.message;

import java.util.List;

/**
 * @Author: caochaojie
 * @Date: 2019/5/14
 */
public class ReqUpdateAccountMsg extends AbstractMessage {

    AccountInfoVO accountInfoVO;

    List<AccountInfoVO> list;


    public AccountInfoVO getAccountInfoVO() {
        return accountInfoVO;
    }

    public void setAccountInfoVO(AccountInfoVO accountInfoVO) {
        this.accountInfoVO = accountInfoVO;
    }

    public List<AccountInfoVO> getList() {
        return list;
    }

    public void setList(List<AccountInfoVO> list) {
        this.list = list;
    }
}
