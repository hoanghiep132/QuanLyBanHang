package com.example.quanlybanhang.service;

import com.example.quanlybanhang.entities.NhanVien;

import java.util.Optional;

public interface NhanVienService {

    Optional<NhanVien> findById(Integer id);
}
