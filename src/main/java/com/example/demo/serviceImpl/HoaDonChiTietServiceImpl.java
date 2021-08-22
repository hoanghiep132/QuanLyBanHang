package com.example.demo.serviceImpl;

import com.example.demo.entities.HoaDon;
import com.example.demo.entities.HoaDonChiTiet;
import com.example.demo.entities.HoaDonDTO;
import com.example.demo.repository.HoaDonChiTietRepo;
import com.example.demo.repository.HoaDonRepo;
import com.example.demo.service.HoaDonChiTietService;
import com.example.demo.service.HoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class HoaDonChiTietServiceImpl implements HoaDonChiTietService {

    @Autowired
    HoaDonChiTietRepo hoaDonChiTietRepo;

    @Autowired
    HoaDonRepo hoaDonRepo;

    @Override
    public Page<HoaDonChiTiet> findByHoaDonId(Integer hoaDonId, Pageable pageable) {
        try {
            Page<HoaDonChiTiet> hoaDonChiTiets=hoaDonChiTietRepo.findByHoaDonId(hoaDonId,pageable);
            if(hoaDonChiTiets.isEmpty()){
                return  Page.empty();
            }else {
                return  hoaDonChiTiets;
            }

        }catch(Exception e){
            return  Page.empty();
        }
    }

}
