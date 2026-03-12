package com.solvd.bookingcompany.domain;

import com.solvd.bookingcompany.domain.BaseEntity;

public class Photo extends BaseEntity {

    private String url;

    public Photo() {
    }

    public Photo(Long id, String url) {
        super(id);
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String getEntityName() {
        return "Photo";
    }
}
