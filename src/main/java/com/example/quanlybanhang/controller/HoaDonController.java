package com.example.quanlybanhang.controller;

import com.example.quanlybanhang.common.Constant;
import com.example.quanlybanhang.entities.HangHoa;
import com.example.quanlybanhang.entities.HoaDon;
import com.example.quanlybanhang.entities.HoaDonChiTiet;
import com.example.quanlybanhang.entities.request.HoaDonChiTietDTO;
import com.example.quanlybanhang.entities.request.CreateHoaDonRequest;
import com.example.quanlybanhang.entities.request.UpdateHoaDonRequest;
import com.example.quanlybanhang.response.MyResponse;
import com.example.quanlybanhang.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/api/v1/hoa-don")
public class HoaDonController {

    @Autowired
    private HoaDonService hoaDonService;

    @Autowired
    private HoaDonChiTietService hoaDonChiTietService;

    @Autowired
    private KhachHangService khachHangService;

    @Autowired
    private NhanVienService nhanVienService;

    @Autowired
    private HangHoaService hangHoaService;

    @GetMapping("/search")
    ResponseEntity<?> findByDay(//@RequestParam(name = "ngay_bat_dau", defaultValue = "1970-01-01T00:00:00+00:00", required = false)@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate ngayBatDau,
                                //@RequestParam(name = "ngay_ket_thuc", defaultValue = "2099-12-31T00:00:00+00:00", required = false)@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate ngayKetThuc,
                                @RequestParam(name = "ngay_bat_dau", required = false)String ngayBatDau,
                                @RequestParam(name = "ngay_ket_thuc", required = false)String ngayKetThuc,
                               // @RequestParam(name = "ngay_bat_dau", required = false, defaultValue = "1900-01-01 00:00:00") Date ngayBatDau,
                                //@RequestParam(name = "ngay_ket_thuc", required = false, defaultValue = "2099-01-01") Date ngayKetThuc,
                                @RequestParam(name = "trang_thai", required = false) Integer trangThai,
                                @RequestParam(name = "page" ,required = false, defaultValue = "0") Integer page,
                                @RequestParam(name = "size",required = false,defaultValue = "10") Integer size){
        Pageable pageable = PageRequest.of(page,size);
        Page<HoaDon> hoaDons = hoaDonService.search(ngayBatDau,ngayKetThuc,trangThai,pageable);
//        Page<HoaDon> hoaDons = hoaDonService.search2(ngayBatDau,ngayKetThuc,trangThai,pageable);
        return ResponseEntity.ok(hoaDons);
    }

    @GetMapping("/find/ma}")
    ResponseEntity<?> findById(@PathVariable(name = "ma") Integer ma){
        Optional<HoaDon> hoaDonOptional = hoaDonService.findById(ma);
        if(hoaDonOptional.isPresent()){
            return ResponseEntity.ok(hoaDonOptional.get());
        }
        else{
            return ResponseEntity.ok("Hóa đơn không tồn tại");
        }
    }

    @PostMapping("/save")
    ResponseEntity<?> save(@RequestBody CreateHoaDonRequest request){
        String validate = request.validate();
        if(validate != null){
            return ResponseEntity.ok(validate);
        }
        HoaDon hoaDon = new HoaDon();
        List<HoaDonChiTiet> hoaDonChiTiets = new ArrayList<>();
        return nhanVienService.findById(request.getNhanVienId())
                .map(nhanVien -> {
                    hoaDon.setNhanVien(nhanVien);
                    return khachHangService.findById(request.getKhachHangId())
                            .map(khachHang -> {
                                hoaDon.setKhachHang(khachHang);
//                                hoaDon.setThoiGian(new Date());
                                hoaDon.setTongTien(request.getTongTien());
                                hoaDon.setTienKhachTra(request.getTienKhachTra());
                                hoaDon.setTienTraLaiKhach(request.getTienKhachTra() - request.getTongTien());
                                hoaDon.setTrangThai(Constant.TrangThai.CREATE);
                                hoaDon.setXoa(false);
                                
                                String uuid = UUID.randomUUID().toString();
                                hoaDon.setMa(uuid);

                                Optional<HoaDon> hoaDonOptional = hoaDonService.save(hoaDon);
                                if(!hoaDonOptional.isPresent()){
                                    return ResponseEntity.ok("Lưu hóa đơn không thành công");
                                }
                                HoaDon hd = hoaDonOptional.get();
                                for(HoaDonChiTietDTO hdctDTO: request.getHoaDonChiTietDTOs()){
                                    HoaDonChiTiet hdct = new HoaDonChiTiet();
                                    hdct.setHoaDon(hd);
                                    hdct.setSoLuong(hdctDTO.getSoLuong());
                                    hdct.setXoa(false);
                                    Optional<HangHoa> hangHoaOptional = hangHoaService.findById(hdctDTO.getHangHoaId());
                                    if(!hangHoaOptional.isPresent()){
                                        return ResponseEntity.ok("Hàng hóa không tồn tại");
                                    }
                                    hdct.setHangHoa(hangHoaOptional.get());
                                    hdct.setThanhTien(hangHoaOptional.get().getGia() * hdctDTO.getSoLuong());
                                    hoaDonChiTiets.add(hdct);
                                }
        
                                List<HoaDonChiTiet> hoaDonChiTietList = hoaDonChiTietService.saveAll(hoaDonChiTiets);
                                if(hoaDonChiTietList.isEmpty()){
                                    return ResponseEntity.ok("failed");
                                }else {
                                    return ResponseEntity.ok("success");
                                }
                            }).orElse(ResponseEntity.ok("Khách hàng không tồn tại"));
                }).orElse(ResponseEntity.ok("Nhân viên không tồn tại"));
    }

    @PostMapping("/save2")
    ResponseEntity<?> save2(@RequestBody CreateHoaDonRequest request){
        String validate = request.validate();
        if(validate != null){
            return ResponseEntity.ok(validate);
        }
        MyResponse response = hoaDonService.save2(request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update-trang-thai")
    ResponseEntity<?> update(@RequestBody UpdateHoaDonRequest request){

        String validate = request.validate();
        if(validate != null){
            return ResponseEntity.ok(validate);
        }
        Boolean rs = hoaDonService.updateTrangThai(request.getId(), request.getTrangThai());
        if(rs){
            return ResponseEntity.ok("success");
        }else {
            return ResponseEntity.ok("failed");
        }
    }
}
