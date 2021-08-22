package com.example.quanlybanhang.entities.request;


import lombok.Data;

@Data
public class UpdateHoaDonRequest {

    private Integer id;

    private Integer trangThai;

    public String validate(){
        if(id == null || id <= 0){
            return "Id không hợp lệ";
        }
        if(trangThai == null || trangThai < 0 || trangThai > 4){
            return "Trạng thái không hợp lệ";
        }
        return null;
    }
}
