package com.example.quanlybanhang.entities.request;

import com.example.quanlybanhang.entities.KhachHang;
import com.example.quanlybanhang.entities.NhanVien;
import lombok.Data;

import java.util.List;

@Data
public class CreateHoaDonRequest {

    private Integer nhanVienId;

    private Integer khachHangId;

    private Integer tongTien;

    private Integer tienKhachTra;

    private List<HoaDonChiTietDTO> hoaDonChiTietDTOs;


    public String validate(){
        if(nhanVienId == null || nhanVienId <= 0){
            return "Nhân viên id không hợp lệ";
        }
        if(khachHangId == null || khachHangId <= 0){
            return "Khách hàng id không hợp lệ";
        }
        if(tongTien == null || tongTien <= 0){
            return "Tổng tiền không hợp lệ";
        }
        if(tienKhachTra == null || tienKhachTra <= 0 || tienKhachTra < tongTien){
            return "Tiền khách trả không hợp lệ";
        }
        for (HoaDonChiTietDTO hoaDonChiTietDTO: hoaDonChiTietDTOs){
            if(hoaDonChiTietDTO.getHangHoaId() == null || hoaDonChiTietDTO.getHangHoaId() <= 0){
                return "Hàng hóa id không hợp lệ";
            }
            if(hoaDonChiTietDTO.getSoLuong() == null || hoaDonChiTietDTO.getSoLuong() <= 0){
                return "Số lượng không hợp lệ";
            }
        }
        return null;
    }
}
