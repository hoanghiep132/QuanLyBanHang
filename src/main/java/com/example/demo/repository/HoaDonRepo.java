package com.example.demo.repository;

import com.example.demo.entities.HoaDon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.Optional;

@Repository
public interface HoaDonRepo  extends JpaRepository<HoaDon,Integer> {


    @Query(value = "from HoaDon  hd where  1=1 and(?1 is null or hd.thoiGian>=?1) " +
            "and(?2 is null or hd.thoiGian<=?2) " +
            "and(?3 is null or hd.trangThai=?3) and hd.xoa=false")
    Page<HoaDon> search(Date ngayBatDau, Date ngayKetThuc, Integer trangThai, Pageable pageable);


    @Query(value = "from HoaDon  hd where hd.id =?1 and hd.xoa=false")
    Optional<HoaDon> findById(Integer id);





}
