package com.example.quanlybanhang.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataResponse<T> extends MyResponse{
    private T data;

    public DataResponse(int code, String message, T data) {
        super(code, message);
        this.data = data;
    }

    public DataResponse(int code, String message) {
        super(code, message);
    }
}
