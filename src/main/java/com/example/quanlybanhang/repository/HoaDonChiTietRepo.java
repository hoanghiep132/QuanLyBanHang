package com.example.quanlybanhang.repository;


import com.example.quanlybanhang.entities.HoaDonChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HoaDonChiTietRepo extends JpaRepository<HoaDonChiTiet,Integer> {

    @Query(value = "from HoaDonChiTiet hdct where hdct.id = ?1 and hdct.xoa = false")
    Optional<HoaDonChiTiet> findById(Integer id);

    @Query(value = "from HoaDonChiTiet  hdct where  hdct.hoaDon.id = ?1")
    List<HoaDonChiTiet> findByHoaDonId(Integer hoaDonId);
}
