package com.example.quanlybanhang.controller;

import com.example.quanlybanhang.entities.KhachHang;
import com.example.quanlybanhang.service.KhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/khach-hang")
public class KhachHangController {

    @Autowired
    KhachHangService khachHangService;

    @GetMapping("/find/{id}")
    ResponseEntity<?> findById(@PathVariable(name = "id") Integer id){
        Optional<KhachHang> khachHangOptional = khachHangService.findById(id);
        if(khachHangOptional.isPresent()){
            return ResponseEntity.ok(khachHangOptional.get());
        }
        else{
            return ResponseEntity.ok(Optional.empty());
        }
    }
}
