package com.example.demo.controller;

import com.example.demo.entities.HoaDon;
import com.example.demo.entities.HoaDonDTO;
import com.example.demo.service.HoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/quanlyhanghoa")
public class HoaDonController {
    @Autowired
    HoaDonService hoaDonService;


    @GetMapping("/search")
public ResponseEntity<?> search(@RequestParam("ngay_bat_dau") Date ngayBatDau,
                                @RequestParam("ngay_ket_thuc") Date ngayKetThuc,
                                @RequestParam("trang_thai") Integer trangThai){

Pageable pageable = PageRequest.of(0,10);
    Page<HoaDon> hoaDons=hoaDonService.search(ngayBatDau,ngayKetThuc,trangThai,pageable);
    return ResponseEntity.ok(hoaDons);

}

@PostMapping("/save")
public ResponseEntity<?> save(@RequestBody HoaDonDTO hoaDonDTO) {
    Optional<HoaDon> hoaDonOptional=hoaDonService.save(hoaDonDTO);
    if(hoaDonOptional.isPresent()) {
        return ResponseEntity.ok("success");
    }
    return  ResponseEntity.ok("failed");
}

    @PutMapping("/update")
public ResponseEntity<?> update(@RequestBody HoaDonDTO hoaDonDTO,
                                @RequestParam Integer trangThai){
       Optional<HoaDon> hoaDonOptional=hoaDonService.updateTrangThai(hoaDonDTO,trangThai);
       if(hoaDonOptional.isPresent()){
           return ResponseEntity.ok("success");
       }
       return ResponseEntity.ok("failed");


}





}
