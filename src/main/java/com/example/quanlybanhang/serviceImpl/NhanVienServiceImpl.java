package com.example.quanlybanhang.serviceImpl;

import com.example.quanlybanhang.entities.NhanVien;
import com.example.quanlybanhang.repository.NhanVienRepo;
import com.example.quanlybanhang.service.NhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NhanVienServiceImpl implements NhanVienService {

    @Autowired
    NhanVienRepo nhanVienRepo;

    @Override
    public Optional<NhanVien> findById(Integer id) {
        try {
            return nhanVienRepo.findById(id);
        }
        catch (Exception ex){
            return Optional.empty();
        }
    }
}
