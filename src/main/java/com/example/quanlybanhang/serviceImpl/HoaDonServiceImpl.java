package com.example.quanlybanhang.serviceImpl;

import com.example.quanlybanhang.entities.*;
import com.example.quanlybanhang.entities.request.CreateHoaDonRequest;
import com.example.quanlybanhang.entities.request.HoaDonChiTietDTO;
import com.example.quanlybanhang.repository.*;
import com.example.quanlybanhang.response.MyResponse;
import com.example.quanlybanhang.service.HoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@Service
public class HoaDonServiceImpl implements HoaDonService {

    @Autowired
    private HoaDonRepo hoaDonRepo;

    @Autowired
    private KhachHangRepo khachHangRepo;

    @Autowired
    private NhanVienRepo nhanVienRepo;

    @Autowired
    private HoaDonChiTietRepo hoaDonChiTietRepo;

    @Autowired
    private HangHoaRepo hangHoaRepo;

    @Override
    public Page<HoaDon> search(String ngayBatDau, String ngayKetThuc, Integer trangThai, Pageable pageable) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        Date batDau;
        Date ketThuc;
        try {
            batDau = format.parse(ngayBatDau);
        } catch (Exception e) {
            batDau = null;
            System.out.println("Exception parse date : " + e.getMessage());
        }

        try {
            ketThuc = format.parse(ngayKetThuc);
            Calendar c = Calendar.getInstance();
            c.setTime(ketThuc);
            c.add(Calendar.DATE, 1);
            ketThuc = c.getTime();
        }catch (Exception e){
            ketThuc = null;
            System.out.println("Exception parse date : " + e.getMessage());
        }

        try {
            return hoaDonRepo.search(batDau, ketThuc, trangThai, pageable);
        }catch (Exception ex){
            System.out.println("Error : " + ex.getMessage());
            return Page.empty();
        }

    }

    @Override
    public Page<HoaDon> search2(LocalDate ngayBatDau, LocalDate ngayKetThuc, Integer trangThai, Pageable pageable) {
        try {
            Date batDau = Date.from(ngayBatDau.atStartOfDay(ZoneId.systemDefault()).toInstant());
            Date ketThuc = Date.from(ngayKetThuc.atStartOfDay(ZoneId.systemDefault()).toInstant());
            return hoaDonRepo.search(batDau, ketThuc, trangThai, pageable);
        }catch (Exception ex){
            System.out.println("Error : " + ex.getMessage());
            return Page.empty();
        }

    }

    @Override
    public Optional<HoaDon> findById(Integer id) {
        try {
            Optional<HoaDon> hoaDonOptional = hoaDonRepo.findById(id);
            if(!hoaDonOptional.isPresent()){
                return Optional.empty();
            }
            List<HoaDonChiTiet> hoaDonChiTiets = hoaDonChiTietRepo.findByHoaDonId(id);
            hoaDonOptional.get().setHoaDonChiTiet(hoaDonChiTiets);
            return hoaDonOptional;
        } catch (Exception ex) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<HoaDon> save(HoaDon hoaDon) {
        try {
            return Optional.ofNullable(hoaDonRepo.save(hoaDon));
        } catch (Exception ex) {
            return Optional.empty();
        }
    }

    @Override
    public MyResponse save2(CreateHoaDonRequest request) {
        MyResponse response = new MyResponse();

        Optional<NhanVien> nhanVienOptional = nhanVienRepo.findById(request.getNhanVienId());
        if(!nhanVienOptional.isPresent()){
            response.setResult(-1, "nhân viên không tồn tại");
            return response;
        }

        Optional<KhachHang> khachHangOptional = khachHangRepo.findById(request.getKhachHangId());
        if(!khachHangOptional.isPresent()){
            response.setResult(-1, "Khách hàng không tồn tại");
            return response;
        }

        HoaDon hoaDon = new HoaDon();
        hoaDon.setNhanVien(nhanVienOptional.get());
        hoaDon.setKhachHang(khachHangOptional.get());
        hoaDon.setThoiGian(new Date());
        hoaDon.setTongTien(request.getTongTien());
        hoaDon.setTienKhachTra(request.getTienKhachTra());
        hoaDon.setTienTraLaiKhach(request.getTienKhachTra() - request.getTongTien());
        hoaDon.setXoa(false);

        HoaDon hd = hoaDonRepo.save(hoaDon);
        if(hd == null){
            response.setResult(-1,"Lưu hóa đơn không thành công");
            return response;
        }

        List<HoaDonChiTiet> hoaDonChiTiets = new ArrayList<>();
        for(HoaDonChiTietDTO hdctDTO: request.getHoaDonChiTietDTOs()){
            HoaDonChiTiet hdct = new HoaDonChiTiet();
            hdct.setHoaDon(hd);
            hdct.setSoLuong(hdct.getSoLuong());

            Optional<HangHoa> hangHoaOptional = hangHoaRepo.findById(hdctDTO.getHangHoaId());
            if(!hangHoaOptional.isPresent()){
                response.setResult(-1,"Hàng hóa không tồn tại");
                return response;
            }
            hdct.setHangHoa(hangHoaOptional.get());
            hdct.setThanhTien(hangHoaOptional.get().getGia() + hdctDTO.getSoLuong());
            hoaDonChiTiets.add(hdct);
        }

        List<HoaDonChiTiet> hoaDonChiTietList = hoaDonChiTietRepo.saveAll(hoaDonChiTiets);
        if(hoaDonChiTietList.isEmpty()){
            response.setResult(-1,"Lưu danh sách hóa đơn chi tiết không thành công");
        }else {
            response.success();
        }
        return response;
    }

    @Override
    public Boolean updateTrangThai(int hoaDonId, int trangThai) {
        try {
            Integer rs = hoaDonRepo.updateTrangThai(hoaDonId, trangThai);
            System.out.println("Update status : " + rs);
            return rs > 0;
        }catch (Exception ex){
            System.out.println("Error : " + ex.getMessage());
            return false;
        }
    }
}
