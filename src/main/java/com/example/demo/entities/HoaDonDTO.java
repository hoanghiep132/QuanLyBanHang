package com.example.demo.entities;

import lombok.Data;

import java.sql.Date;
import java.util.List;

@Data
public class HoaDonDTO {
    public Integer id;

    public String maHoaDon;

    public Date thoiGian;

    public KhachHang khachHang;

    public NhanVien nhanVien;

    public Integer tongTien;

    public Integer trangThai;

    public Integer tienKhachTra;

    public Integer tienTraLaiKhach;

    public List<HoaDonChiTiet> danhSachHoaDonChiTiet;
}
