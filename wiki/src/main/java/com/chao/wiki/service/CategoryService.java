package com.chao.wiki.service;

import com.chao.wiki.domain.Category;
import com.chao.wiki.domain.CategoryExample;
import com.chao.wiki.mapper.CategoryMapper;
import com.chao.wiki.req.CategoryQueryReq;
import com.chao.wiki.req.CategorySaveReq;
import com.chao.wiki.resp.CategoryQueryResp;
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
public class CategoryService {

//    private static final Logger LOG = LoggerFactory.getLogger(CategoryService.class);

    @Resource
    private CategoryMapper categoryMapper;

    @Resource
    private SnowFlake snowFlake;

    public List<CategoryQueryResp> all() {
        CategoryExample categoryExample = new CategoryExample();
        categoryExample.setOrderByClause("sort asc");
        List<Category> categoryList = categoryMapper.selectByExample(categoryExample);

        // 列表复制
        List<CategoryQueryResp> list = CopyUtil.copyList(categoryList, CategoryQueryResp.class);

        return list;
    }

    /*
    查询分类信息
     */
    public PageResp<CategoryQueryResp> list(CategoryQueryReq categoryQueryReq) {
        CategoryExample categoryExample = new CategoryExample();
        categoryExample.setOrderByClause("sort asc");
        //分页查询，前端指定查询页码和每页条数
        PageHelper.startPage(categoryQueryReq.getPage(), categoryQueryReq.getSize());

        //查询得到结果
        List<Category> categoryList = categoryMapper.selectByExample(categoryExample);

        //得到总的行数
        PageInfo<Category> pageInfo = new PageInfo<>(categoryList);
        long totalPageNum = pageInfo.getTotal();

        //列表复制
        //将得到的实体类复制到category的返回参数中
        List<CategoryQueryResp> categoryQueryResps = CopyUtil.copyList(categoryList, CategoryQueryResp.class);

        //定义一个分页返回参数，传入得到的值后返回
        PageResp<CategoryQueryResp> pageResp = new PageResp<>();
        pageResp.setList(categoryQueryResps);
        pageResp.setTotal(totalPageNum);
        return pageResp;
    }

    /*
    保存分类
     */
    public void save(CategorySaveReq categorySaveReq) {
        Category category = CopyUtil.copy(categorySaveReq, Category.class);

        //新增一个记录
        if (ObjectUtils.isEmpty(category.getId())) {
            //雪花算法生成一个新id
            category.setId(snowFlake.nextId());
            categoryMapper.insertSelective(category);
        } else {
            //更新现有记录
            categoryMapper.updateByPrimaryKey(category);
        }
    }

    /*
    删除分类
     */
    public void delete(long id) {
        categoryMapper.deleteByPrimaryKey(id);
    }
}
