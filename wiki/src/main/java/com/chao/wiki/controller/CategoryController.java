package com.chao.wiki.controller;

import com.chao.wiki.req.CategoryQueryReq;
import com.chao.wiki.req.CategorySaveReq;
import com.chao.wiki.resp.CommonResp;
import com.chao.wiki.resp.CategoryQueryResp;
import com.chao.wiki.resp.PageResp;
import com.chao.wiki.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/all")
    public CommonResp all() {
        CommonResp<List<CategoryQueryResp>> resp = new CommonResp<>();
        List<CategoryQueryResp> list = categoryService.all();
        resp.setContent(list);
        return resp;
    }


    @GetMapping("/list1")
    //参数名字一样会自动映射
    public CommonResp<PageResp<CategoryQueryResp>> list(@Valid CategoryQueryReq categoryQueryReq) {
        CommonResp<PageResp<CategoryQueryResp>> resp = new CommonResp<>();
        PageResp<CategoryQueryResp> list = categoryService.list(categoryQueryReq);
        resp.setContent(list);
        return resp;
    }

    @PostMapping("/save")
    public CommonResp save(@Valid @RequestBody CategorySaveReq categorySaveReq) {
        CommonResp resp = new CommonResp<>();
        categoryService.save(categorySaveReq);
        return resp;
    }

    @DeleteMapping("/delete/{id}")
    public CommonResp delete(@PathVariable long id) {
        CommonResp resp = new CommonResp<>();
        categoryService.delete(id);
        return resp;
    }
}
