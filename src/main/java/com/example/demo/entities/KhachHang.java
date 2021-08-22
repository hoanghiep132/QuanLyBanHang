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
@Table(name="khach_hang" ,schema = "quan_ly_ban_hang")
public class KhachHang implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    public Integer id;

    @Column(name="ho_ten")
    public String hoTen;

    @Column(name="ten_khach_hang")
    public String tenKhachHang;

    @Column(name="so_dien_thoai")
    public String soDienThoai;

    @Column(name="email")
    public String emai;

    @Column(name="xoa")
    public boolean xoa;



}
