package com.example.quanlybanhang.controller;


import com.example.quanlybanhang.entities.HangHoa;
import com.example.quanlybanhang.service.HangHoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/hang-hoa")
public class HangHoaController {

    @Autowired
    HangHoaService hangHoaService;

    @GetMapping("/find/{id}")
    ResponseEntity<?> findById(@PathVariable(name = "id") Integer id){
        Optional<HangHoa> hangHoaOptional = hangHoaService.findById(id);
        if(hangHoaOptional.isPresent()){
            return ResponseEntity.ok(hangHoaOptional.get());
        }
        else {
            return ResponseEntity.ok(Optional.empty());
        }
    }
}
