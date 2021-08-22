package com.example.quanlybanhang.repository;

import com.example.quanlybanhang.entities.HoaDon;
import com.example.quanlybanhang.entities.NhanVien;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface HoaDonRepo extends JpaRepository<HoaDon,Integer>{

    @Query(value = "from HoaDon hd where (?1 is null or hd.thoiGian >= ?1) " +
            "and (?2 is null or hd.thoiGian <= ?2 ) " +
            "and (?3 is null or hd.trangThai = ?3) " +
            "and hd.xoa = false ")
    Page<HoaDon> search(Date ngayBatDau, Date ngayKetThuc, Integer trangThai, Pageable pageable);

    @Query(value = "from HoaDon hd where hd.ma = ?1 and hd.xoa = false")
    Optional<HoaDon>findById(Integer id);

    @Modifying
    @Transactional
    @Query(value = "update HoaDon hd set hd.trangThai = ?2 where  hd.id = ?1")
    Integer updateTrangThai(int id, int trangThai);

}
