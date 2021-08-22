package com.example.quanlybanhang.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListResponse<T> extends com.example.quanlybanhang.response.MyResponse {

    private List<T> datas;
}
