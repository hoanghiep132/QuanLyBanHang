package com.example.demo.entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="hoa_don_chi_tiet", schema = "quan_ly_ban_hang")
public class HoaDonChiTiet implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    public Integer id;

    @ManyToOne
    @JoinColumn(name = "hoa_don_id")
    public HoaDon hoaDon;

    @ManyToOne
    @JoinColumn(name = "hang_hoa_id")
    public HangHoa hangHoa;

    @Column(name = "so_luong")
    public Integer soLuong;


    @Column(name = "thanh_tien")
    public Integer thanhTien;


    @Column(name="xoa")
    public boolean xoa;


}
