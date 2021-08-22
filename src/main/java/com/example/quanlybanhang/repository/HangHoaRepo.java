package com.example.quanlybanhang.repository;

import com.example.quanlybanhang.entities.HangHoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HangHoaRepo extends JpaRepository<HangHoa,Integer> {

    @Query(value = "from HangHoa hh where hh.id  = ?1 and hh.xoa = false")
    Optional<HangHoa> findById(Integer id);

}
