package com.example.quanlybanhang.controller;

import com.example.quanlybanhang.entities.NhanVien;
import com.example.quanlybanhang.service.NhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/nhan-vien")
public class NhanVienController {

    @Autowired
    NhanVienService nhanVienService;

    @GetMapping("/find/{id}")
    ResponseEntity<?> findById(@PathVariable(name = "id") Integer id){
        Optional<NhanVien> nhanVienOptional = nhanVienService.findById(id);
        if(nhanVienOptional.isPresent()){
            return ResponseEntity.ok(nhanVienOptional.get());
        }
        else{
            return ResponseEntity.ok(Optional.empty());
        }
    }

}
