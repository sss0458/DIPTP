package com.sdunews.service;

import com.sdunews.manager.SduNewsManager;
import com.sdunews.model.vo.response.ResultVO;
import com.sdunews.model.vo.response.table.SduNewsItemVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SduNewsService extends BaseService {
    private final SduNewsManager manager;

    public SduNewsService(SduNewsManager manager) {
        this.manager = manager;
    }

    public ResultVO getAllNews() {
        List<SduNewsItemVO> voList = SduNewsItemVO.fromSduNewsBOList(manager.getAllNews());
        voList.sort((a, b) -> b.getDate().compareTo(a.getDate()));

        return result(voList);
    }
}
