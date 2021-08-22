package com.example.quanlybanhang.controller;


import com.example.quanlybanhang.entities.HoaDon;
import com.example.quanlybanhang.entities.HoaDonChiTiet;
import com.example.quanlybanhang.service.HoaDonChiTietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/hoa-don-chi-tiet")
public class HoaDonChiTietController {

    @Autowired
    HoaDonChiTietService hoaDonChiTietService;

    @GetMapping("/find/{id}")
    ResponseEntity<?> findById(@PathVariable(name = "id") Integer id){

        Optional<HoaDonChiTiet> hoaDonChiTietOptional = hoaDonChiTietService.findById(id);
        if(hoaDonChiTietOptional.isPresent()){
            return ResponseEntity.ok(hoaDonChiTietOptional.get());
        }
        else{
            return ResponseEntity.ok(Optional.empty());
        }
    }
}
