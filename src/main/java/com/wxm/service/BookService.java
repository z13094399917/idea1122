package com.wxm.service;

import com.wxm.model.User;
import com.wxm.util.PageBean;
import org.apache.ibatis.annotations.Param;

import com.wxm.model.Book;
import java.util.List;
import java.util.Map;

/**
 * @author wxm
 * @site www.wxm.com
 * @company xxx公司
 * @create 2019-11-13 18:47
 */

public interface BookService {

    int deleteByPrimaryKey(Integer bid);

    int insert(Book record);

    int insertSelective(Book record);

    Book selectByPrimaryKey(Integer bid);

    int updateByPrimaryKeySelective(Book record);

    int updateByPrimaryKey(Book record);

    /**
     * 升级版分页
     * @param map
     * @param pageBean
     * @return
     */
    List<Map> listPager(Map map, PageBean pageBean);
    List<Map> listPager1(Book book, PageBean pageBean);




}
