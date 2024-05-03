package com.javaweb.enums;

import java.util.HashMap;
import java.util.Map;

public enum TransactionType {
    CSKH("Chăm sóc khách hàng"),
    DDX("Dẫn đi xem");

    private String value;
    TransactionType(String value) {this.value = value;}

    public static Map<String,String> transactionType(){
        Map<String,String> transactionType = new HashMap<String,String>();
        for(TransactionType it : TransactionType.values()){
            transactionType.put(it.toString(), it.value);
        }
        return transactionType;
    }
}
