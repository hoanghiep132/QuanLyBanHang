package com.example.demo.service;


import com.example.demo.entities.HoaDonChiTiet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;



@Service
public interface HoaDonChiTietService {

    Page<HoaDonChiTiet> findByHoaDonId(Integer hoaDonId, Pageable pageable);


}
