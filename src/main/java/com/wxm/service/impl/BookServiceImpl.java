package com.wxm.service.impl;

import com.wxm.mapper.BookMapper;
import com.wxm.model.User;
import com.wxm.service.BookService;
import com.wxm.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.wxm.model.Book;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author wxm
 * @site www.wxm.com
 * @company xxx公司
 * @create 2019-11-18 15:24
 */
@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookMapper bookMapper;
    public BookMapper getBookMapper() {
        return bookMapper;
    }

    public void setBookMapper(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    @Override
    public int deleteByPrimaryKey(Integer bid) {
        return bookMapper.deleteByPrimaryKey(bid);
    }

    @Override
    public int insert(Book record) {
        return bookMapper.insert(record);
    }

    @Override
    public int insertSelective(Book record) {
        return bookMapper.insertSelective(record);
    }

    @Override
    public Book selectByPrimaryKey(Integer bid) {
        return bookMapper.selectByPrimaryKey(bid);
    }

    @Override
    public int updateByPrimaryKeySelective(Book record) {
        return bookMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Book record) {
        return bookMapper.updateByPrimaryKey(record);
    }

    /**
     * 升级版分页
     *
     * @param map
     * @param pageBean
     * @return
     */
    @Override
    public List<Map> listPager(Map map, PageBean pageBean) {
        return bookMapper.listPager(map);
    }

    @Override
    public List<Map> listPager1(Book book, PageBean pageBean) {
        return bookMapper.listPager1(book);
    }




}
