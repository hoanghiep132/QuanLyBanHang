package com.example.quanlybanhang.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyResponse {
    private  int code;
    private String message;


    public void setResult(int code, String message){
        this.code = code;
        this.message = message;
    }

    public void success(){
        this.code = 0;
        this.message = "success";
    }
}
