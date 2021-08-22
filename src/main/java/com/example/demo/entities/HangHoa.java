package com.example.demo.entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="hang_hoa", schema = "quan_ly_ban_hang")
public class HangHoa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    public Integer id;


    @Column(name="ma")
    public String maHangHoa;

    @Column(name="ten_hang_hoa")
    public String tenHangHoa;

    @Column(name="gia")
    public Integer gia;


    @Column(name="xoa")
    public boolean xoa;


}
