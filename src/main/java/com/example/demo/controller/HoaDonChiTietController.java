package com.example.demo.controller;


import com.example.demo.entities.HoaDonChiTiet;
import com.example.demo.service.HoaDonChiTietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/hoa_don_chi_tiet")
public class HoaDonChiTietController {

@Autowired
    HoaDonChiTietService hoaDonChiTietService;

@GetMapping("/findby_hoa_don_id")
    public ResponseEntity<?> findByHoaDonId(@RequestParam Integer hoaDonId){
        Pageable pageable= PageRequest.of(0,10);
        Page<HoaDonChiTiet> hoaDonChiTiets=hoaDonChiTietService.
                findByHoaDonId(hoaDonId, pageable);

        return ResponseEntity.ok(hoaDonChiTiets);
    }



}
