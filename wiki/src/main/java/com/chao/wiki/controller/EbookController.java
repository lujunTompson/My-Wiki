package com.chao.wiki.controller;

import com.chao.wiki.req.EbookQueryReq;
import com.chao.wiki.req.EbookSaveReq;
import com.chao.wiki.resp.CommonResp;
import com.chao.wiki.resp.EbookQueryResp;
import com.chao.wiki.resp.PageResp;
import com.chao.wiki.service.EbookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ebook")
public class EbookController {

    @Autowired
    private EbookService ebookService;

    @GetMapping("/list1")
    //参数名字一样会自动映射
    public CommonResp list(EbookQueryReq ebookQueryReq) {
        CommonResp<PageResp<EbookQueryResp>> resp = new CommonResp<>();
        PageResp<EbookQueryResp> list = ebookService.list(ebookQueryReq);
        resp.setContent(list);
        return resp;
    }

    @PostMapping("/save")
    public CommonResp save(@RequestBody EbookSaveReq ebookSaveReq) {
        CommonResp resp = new CommonResp<>();
        ebookService.save(ebookSaveReq);
        return resp;
    }

    @DeleteMapping("/delete/{id}")
    public CommonResp delete(@PathVariable long id) {
        CommonResp resp = new CommonResp<>();
        ebookService.delete(id);
        return resp;
    }
}
