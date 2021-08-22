package com.example.demo.serviceImpl;



import com.example.demo.entities.HoaDon;
import com.example.demo.entities.HoaDonDTO;
import com.example.demo.entities.KhachHang;
import com.example.demo.entities.NhanVien;
import com.example.demo.repository.HoaDonRepo;
import com.example.demo.repository.KhachHangRepo;
import com.example.demo.repository.NhanVienRepo;
import com.example.demo.service.HoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Optional;

@Service
public class HoaDonServiceImpl implements HoaDonService {

    @Autowired
    HoaDonRepo hoaDonRepo;

    @Autowired
    KhachHangRepo khachHangRepo;

    @Autowired
    NhanVienRepo nhanVienRepo;


    @Override
    public Page<HoaDon> search(Date ngayBatDau, Date ngayKetThuc, Integer trangThai, Pageable pageable) {
        try{
            return  hoaDonRepo.search(ngayBatDau,ngayKetThuc,trangThai,pageable);
        }catch (Exception exception){
            return  Page.empty();
        }
    }

    @Override
    public Optional<HoaDon> save(HoaDonDTO hoaDonDTO) {

        Optional<KhachHang> khachHangOptional=khachHangRepo.findById(hoaDonDTO.getKhachHang().getId());
    if(khachHangOptional.isPresent()){
        Optional<NhanVien> nhanVienOptional=nhanVienRepo.findById(hoaDonDTO.getNhanVien().getId());
        if (nhanVienOptional.isPresent()){

                HoaDon hoaDon=new HoaDon();
                hoaDon.setKhachHang(hoaDonDTO.getKhachHang());
                hoaDon.setNhanVien(hoaDonDTO.getNhanVien());     // DTO chi set cac field duoc cho phep
                hoaDon.setTrangThai(hoaDonDTO.getTrangThai());
                hoaDon.setXoa(false);
                return  Optional.ofNullable( hoaDonRepo.save(hoaDon));

        }
    }
        return  Optional.empty();
    }

    @Override
    public Optional<HoaDon> updateTrangThai(HoaDonDTO hoaDonDTO, Integer trangThai) {

        Optional<HoaDon> hoaDonOptional=hoaDonRepo.findById(hoaDonDTO.getId());
        if(hoaDonOptional.isPresent()){
            hoaDonOptional.get().setTrangThai(trangThai);
            return  Optional.ofNullable( hoaDonRepo.save( hoaDonOptional.get()));
        }
        return  Optional.empty();

    }

}
