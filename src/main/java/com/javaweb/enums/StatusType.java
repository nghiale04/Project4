package com.javaweb.enums;

import java.util.HashMap;
import java.util.Map;

public enum StatusType {
    DXL("Đang xử lý"),
    DDXL("Đã xử lý"),
    CXL("Chưa xử lý");

    private String value;
    StatusType(String value) {this.value = value;}

    public static Map<String,String> statusType(){
        Map<String,String> statusType = new HashMap<String,String>();
        for(StatusType it : StatusType.values()){
            statusType.put(it.toString(), it.value);
        }
        return statusType;
    }
}
