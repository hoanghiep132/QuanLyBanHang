package com.example.demo.repository;


import com.example.demo.entities.HoaDonChiTiet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HoaDonChiTietRepo extends JpaRepository<HoaDonChiTiet,Integer> {

    @Query(value = "from HoaDonChiTiet  hdct where hdct.id =?1 and hdct.xoa=false")
    Optional<HoaDonChiTiet> findById(Integer id);


    @Query(value = "from HoaDonChiTiet  hdct where hdct.hoaDon.id =?1 and hdct.xoa=false")
    Page<HoaDonChiTiet> findByHoaDonId(Integer hoaDonId,Pageable pageable);

}
