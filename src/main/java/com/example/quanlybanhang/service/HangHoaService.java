package com.example.quanlybanhang.service;

import com.example.quanlybanhang.entities.HangHoa;
import com.example.quanlybanhang.entities.NhanVien;

import java.util.Optional;

public interface HangHoaService {

    Optional<HangHoa> findById(Integer id);
}
