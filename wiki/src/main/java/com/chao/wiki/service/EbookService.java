package com.chao.wiki.service;

import com.chao.wiki.domain.Ebook;
import com.chao.wiki.domain.EbookExample;
import com.chao.wiki.mapper.EbookMapper;
import com.chao.wiki.req.EbookQueryReq;
import com.chao.wiki.req.EbookSaveReq;
import com.chao.wiki.resp.EbookQueryResp;
import com.chao.wiki.resp.PageResp;
import com.chao.wiki.util.CopyUtil;
import com.chao.wiki.util.SnowFlake;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EbookService {

//    private static final Logger LOG = LoggerFactory.getLogger(EbookService.class);

    @Resource
    private EbookMapper ebookMapper;

    @Resource
    private SnowFlake snowFlake;

    /*
    查询电子书信息
     */
    public PageResp<EbookQueryResp> list(EbookQueryReq ebookQueryReq) {
        EbookExample ebookExample = new EbookExample();
        EbookExample.Criteria criteria = ebookExample.createCriteria();

        //如果指定名字，就模糊查询
        if (!ObjectUtils.isEmpty(ebookQueryReq.getName())) {
            criteria.andNameLike("%" + ebookQueryReq.getName() + "%");
        }

        //分页查询，前端指定查询页码和每页条数
        PageHelper.startPage(ebookQueryReq.getPage(), ebookQueryReq.getSize());

        //查询得到结果
        List<Ebook> ebookList = ebookMapper.selectByExample(ebookExample);

        //得到总的行数
        PageInfo<Ebook> pageInfo = new PageInfo<>(ebookList);
        long totalPageNum = pageInfo.getTotal();
//        ArrayList<EbookResp> respList = new ArrayList<>();
//        for (Ebook ebook : ebookList) {
//            EbookResp ebookResp = new EbookResp();
//            BeanUtils.copyProperties(ebook, ebookResp);
//            respList.add(ebookResp);
//        }

        //列表复制
        //将得到的实体类复制到ebook的返回参数中
        List<EbookQueryResp> ebookQueryResps = CopyUtil.copyList(ebookList, EbookQueryResp.class);

        //定义一个分页返回参数，传入得到的值后返回
        PageResp<EbookQueryResp> pageResp = new PageResp<>();
        pageResp.setList(ebookQueryResps);
        pageResp.setTotal(totalPageNum);
        return pageResp;
    }

    /*
    保存电子书
     */
    public void save(EbookSaveReq ebookSaveReq) {
        Ebook ebook = CopyUtil.copy(ebookSaveReq, Ebook.class);

        //新增一个记录
        if (ObjectUtils.isEmpty(ebook.getId())) {
            //雪花算法生成一个新id
            ebook.setId(snowFlake.nextId());
            ebookMapper.insertSelective(ebook);
        } else {
            //更新现有记录
            ebookMapper.updateByPrimaryKey(ebook);
        }
    }

    /*
    删除电子书
     */
    public void delete(long id) {
        ebookMapper.deleteByPrimaryKey(id);
    }
}
