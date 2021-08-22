package com.example.quanlybanhang.service;

import com.example.quanlybanhang.entities.HoaDon;
import com.example.quanlybanhang.entities.request.CreateHoaDonRequest;
import com.example.quanlybanhang.response.MyResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.Optional;

public interface HoaDonService {

    Page<HoaDon> search(String ngayBatDau, String ngayKetThuc, Integer trangThai, Pageable pageable);

    Page<HoaDon> search2(LocalDate ngayBatDau, LocalDate ngayKetThuc, Integer trangThai, Pageable pageable);

    Optional<HoaDon>findById(Integer id);

    Optional<HoaDon> save(HoaDon hoaDon);

    MyResponse save2(CreateHoaDonRequest request);

    Boolean updateTrangThai(int hoaDonId, int trangThai);
}
