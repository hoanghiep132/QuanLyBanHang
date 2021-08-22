package com.example.quanlybanhang.serviceImpl;

import com.example.quanlybanhang.entities.HangHoa;
import com.example.quanlybanhang.entities.NhanVien;
import com.example.quanlybanhang.repository.HangHoaRepo;
import com.example.quanlybanhang.service.HangHoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class HangHoaServiceImpl implements HangHoaService {

    @Autowired
    HangHoaRepo hangHoaRepo;

    @Override
    public Optional<HangHoa> findById(Integer id) {
        try{
            return hangHoaRepo.findById(id);
        }
        catch (Exception ex){
            return Optional.empty();
        }
    }
}
