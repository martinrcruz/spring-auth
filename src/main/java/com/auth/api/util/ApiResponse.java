package com.auth.api.util;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
public class ApiResponse {
    private List dtoList;
    private String dtoString;
    private Long dtoLong;
    private ArrayList  dtoArrayList;
    private Object dtoObject;

    private int codError;

    private String descError;

    public ApiResponse(String dto, int codError, String descError) {
        this.dtoString = dto;
        this.codError = codError;
        this.descError = descError;
    }

    public ApiResponse(List dto, int codError, String descError) {
        this.dtoList = dto;
        this.codError = codError;
        this.descError = descError;
    }
    public ApiResponse(ArrayList dto, int codError, String descError) {
        this.dtoArrayList = dto;
        this.codError = codError;
        this.descError = descError;
    }

    public ApiResponse(Object dto, int codError, String descError) {
        this.dtoObject = dto;
        this.codError = codError;
        this.descError = descError;
    }

    public ApiResponse(Long dto, int codError, String descError) {
        this.dtoLong = dto;
        this.codError = codError;
        this.descError = descError;
    }

    public ApiResponse(int codError, String descError) {
        this.codError = codError;
        this.descError = descError;
    }


}
