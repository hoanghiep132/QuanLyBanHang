package com.example.quanlybanhang.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "hoa_don")
public class HoaDon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "ma")
    private String ma;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "thoi_gian")
    private Date thoiGian;

    @ManyToOne
    @JoinColumn(name = "khach_hang_id")
    private KhachHang khachHang;

    @ManyToOne
    @JoinColumn(name = "nhan_vien_id")
    private NhanVien nhanVien;

    @Column(name = "trang_thai")
    private Integer trangThai;

    @Column(name = "tong_tien")
    private Integer tongTien;

    @Column(name = "tien_khach_tra")
    private Integer tienKhachTra;

    @Column(name = "tien_tra_lai_khach")
    private Integer tienTraLaiKhach;

    @Column(name = "xoa")
    private Boolean xoa;


    @Transient
    private List<HoaDonChiTiet> hoaDonChiTiet;
}
