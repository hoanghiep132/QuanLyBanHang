package com.example.demo.repository;

import com.example.demo.entities.NhanVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NhanVienRepo extends JpaRepository<NhanVien,Integer> {


    @Query(value = "from NhanVien  nv where nv.id =?1 and nv.xoa=false")
    Optional<NhanVien> findById(Integer id);

}
