package org.resliv.dto;

import org.resliv.model.City;

import java.util.List;

public class CityPageDto {
    private List<CityDto> cities;
    private int currentPage;
    private int totalPage;

    public CityPageDto() {
    }

    public CityPageDto(List<CityDto> cities, int currentPage, int totalPage) {
        this.cities = cities;
        this.currentPage = currentPage;
        this.totalPage = totalPage;
    }

    public List<CityDto> getCities() {
        return cities;
    }

    public void setCities(List<CityDto> cities) {
        this.cities = cities;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
}
