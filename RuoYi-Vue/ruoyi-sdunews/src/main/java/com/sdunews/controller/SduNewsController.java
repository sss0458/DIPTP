package com.sdunews.controller;

import com.sdunews.model.vo.response.ResultVO;
import com.sdunews.service.SduNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sduNews")
public class SduNewsController{
    @Autowired
    private final SduNewsService service;

    public SduNewsController(SduNewsService service) {
        this.service = service;
    }

    @GetMapping("/getNews")
    public ResultVO get() {
        return service.getAllNews();
    }
}
