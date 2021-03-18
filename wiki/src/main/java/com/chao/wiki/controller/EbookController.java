package com.chao.wiki.controller;

import com.chao.wiki.req.EbookReq;
import com.chao.wiki.resp.CommonResp;
import com.chao.wiki.resp.EbookResp;
import com.chao.wiki.resp.PageResp;
import com.chao.wiki.service.EbookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ebook")
public class EbookController {

    @Autowired
    private EbookService ebookService;


    @GetMapping("/list1")
    //参数名字一样会自动映射
    public CommonResp list(EbookReq ebookReq) {
        CommonResp<PageResp<EbookResp>> resp = new CommonResp<>();
        PageResp<EbookResp> list = ebookService.list(ebookReq);
        resp.setContent(list);
        return resp;
    }
}
