package com.sdunews.model.vo.response.table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sdunews.model.bo.SduNewsBO;
import lombok.Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class SduNewsItemVO {
    private String title;

    @JsonFormat(pattern = "yyyy/MM/dd")
    private Date date;

    private String url;

    private static SduNewsItemVO fromSduNewsBO(SduNewsBO bo) {
        SduNewsItemVO vo = new SduNewsItemVO();
        vo.setTitle(bo.getTitle());
        vo.setUrl(bo.getUrl());

        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
            vo.setDate(format.parse(bo.getDate()));
        } catch (ParseException ex) {
            ex.printStackTrace();
        }

        return vo;
    }

    public static List<SduNewsItemVO> fromSduNewsBOList(List<SduNewsBO> boList) {
        List<SduNewsItemVO> voList = new ArrayList<>();
        for (SduNewsBO bo : boList) {
            voList.add(SduNewsItemVO.fromSduNewsBO(bo));
        }

        return voList;
    }

}
