package com.sdunews.model.bo;

import lombok.Data;

@Data
public class SduNewsBO {
    private String title;
    private String url;
    private String date;

    public SduNewsBO(String title, String date, String url) {
        this.title = title;
        this.date = date;
        this.url = url;
    }
}
