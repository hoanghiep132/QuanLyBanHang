package com.example.demo.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Table(name="nhan_vien" ,schema = "quan_ly_ban_hang")
public class NhanVien implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    public Integer id;

    @Column(name="ma_nhan_vien")
    public String maNhanVien;

    @Column(name="ten_nhan_vien")
    public String tenNhanVien;

    @Column(name="so_dien_thoai")
    public String soDienThoai;

    @Column(name="email")
    public String emai;

    @Column(name="xoa")
    public boolean xoa;


}
