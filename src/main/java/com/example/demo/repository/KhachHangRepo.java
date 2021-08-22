package com.example.demo.repository;

import com.example.demo.entities.KhachHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface KhachHangRepo extends JpaRepository<KhachHang,Integer> {


   @Query(value = "from KhachHang  kh where kh.id =?1 and kh.xoa=false")
   Optional<KhachHang> findById(Integer id);

}
