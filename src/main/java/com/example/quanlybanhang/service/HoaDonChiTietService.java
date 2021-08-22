package com.example.quanlybanhang.service;

import com.example.quanlybanhang.entities.HoaDonChiTiet;

import java.util.List;
import java.util.Optional;

public interface HoaDonChiTietService {

    Optional<HoaDonChiTiet> findById(Integer id);

    List<HoaDonChiTiet> findByHoaDonId(Integer hoaDonId);

    List<HoaDonChiTiet> saveAll(List<HoaDonChiTiet> hoaDonChiTiets);

}
