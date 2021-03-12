package com.chao.wiki.service;

import com.chao.wiki.domain.Ebook;
import com.chao.wiki.domain.EbookExample;
import com.chao.wiki.mapper.EbookMapper;
import com.chao.wiki.req.EbookReq;
import com.chao.wiki.resp.EbookResp;
import com.chao.wiki.util.CopyUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EbookService {

    @Resource
    private EbookMapper ebookMapper;

    public List<EbookResp> list(EbookReq ebookReq) {
        EbookExample ebookExample = new EbookExample();
        EbookExample.Criteria criteria = ebookExample.createCriteria();
        if (!ObjectUtils.isEmpty(ebookReq.getName())) {
            criteria.andNameLike("%" + ebookReq.getName() + "%");
        }
        List<Ebook> ebookList = ebookMapper.selectByExample(ebookExample);

//        ArrayList<EbookResp> respList = new ArrayList<>();
//        for (Ebook ebook : ebookList) {
//            EbookResp ebookResp = new EbookResp();
//            BeanUtils.copyProperties(ebook, ebookResp);
//            respList.add(ebookResp);
//        }

        //列表复制
        List<EbookResp> ebookResps = CopyUtil.copyList(ebookList, EbookResp.class);

        return ebookResps;
    }
}
