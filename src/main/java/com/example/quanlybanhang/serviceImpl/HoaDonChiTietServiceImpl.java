package com.example.quanlybanhang.serviceImpl;

import com.example.quanlybanhang.entities.HoaDonChiTiet;
import com.example.quanlybanhang.repository.HoaDonChiTietRepo;
import com.example.quanlybanhang.service.HoaDonChiTietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HoaDonChiTietServiceImpl implements HoaDonChiTietService {
    @Autowired
    HoaDonChiTietRepo hoaDonChiTietRepo;

    @Override
    public Optional<HoaDonChiTiet> findById(Integer id) {
        try {
            return hoaDonChiTietRepo.findById(id);
        }
        catch (Exception ex){
            return Optional.empty();
        }
    }

    @Override
    public List<HoaDonChiTiet> findByHoaDonId(Integer hoaDonId) {
        try {
            return hoaDonChiTietRepo.findByHoaDonId(hoaDonId);
        }
        catch (Exception ex){
            return new ArrayList<>();
        }
    }

    @Override
    public List<HoaDonChiTiet> saveAll(List<HoaDonChiTiet> hoaDonChiTiets) {
        try {
            return hoaDonChiTietRepo.saveAll(hoaDonChiTiets);
        }
        catch (Exception ex){
            return new ArrayList<>();
        }
    }
}
