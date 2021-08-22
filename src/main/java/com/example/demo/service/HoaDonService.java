package com.example.demo.service;


import com.example.demo.entities.HoaDon;
import com.example.demo.entities.HoaDonDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Optional;

@Service
public interface HoaDonService {
Page<HoaDon> search(Date ngayBatDau, Date ngayKetThuc, Integer trangThai, Pageable pageable);
Optional<HoaDon> save(HoaDonDTO hoaDonDTO);
Optional<HoaDon> updateTrangThai(HoaDonDTO hoaDonDTO, Integer trangThai);

}
