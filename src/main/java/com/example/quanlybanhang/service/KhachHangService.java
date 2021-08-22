package com.example.quanlybanhang.service;

import com.example.quanlybanhang.entities.KhachHang;

import java.util.Optional;

public interface KhachHangService {

    Optional<KhachHang> findById(Integer id);
}
