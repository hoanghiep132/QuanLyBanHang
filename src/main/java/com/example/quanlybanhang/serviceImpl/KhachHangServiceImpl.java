package com.example.quanlybanhang.serviceImpl;

import com.example.quanlybanhang.entities.KhachHang;
import com.example.quanlybanhang.repository.KhachHangRepo;
import com.example.quanlybanhang.service.KhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class KhachHangServiceImpl implements KhachHangService {

    @Autowired
    KhachHangRepo khachHangRepo;


    @Override
    public Optional<KhachHang> findById(Integer id) {
        try{
            return khachHangRepo.findById(id);
        }
        catch (Exception ex){
            return Optional.empty();
        }
    }
}
