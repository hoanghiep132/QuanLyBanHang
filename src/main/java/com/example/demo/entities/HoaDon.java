package com.example.demo.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;


@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="hoa_don", schema = "quan_ly_ban_hang")
public class HoaDon implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    public Integer id;

    @Column(name="ma")
    public String maHoaDon;

    @Column(name="thoi_gian")
    public Date thoiGian;

    @ManyToOne
    @JoinColumn(name="khach_hang_id")
    public KhachHang khachHang;

    @ManyToOne
    @JoinColumn(name="nhan_vien_id")
    public NhanVien nhanVien;

    @Column(name="trang_thai")
    public Integer trangThai;


    @Column(name="tong_tien")
    public Integer tongTien;

    @Column(name="tien_khach_tra")
    public Integer tienKhachTra;


    @Column(name="tien_tra_lai_khach")
    public Integer tienTraLaiKhach;

    @Column(name="xoa")
    public boolean xoa;



}
