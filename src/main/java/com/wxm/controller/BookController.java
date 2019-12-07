package com.wxm.controller;

import com.sun.javafx.collections.MappingChange;
import com.wxm.model.Book;
import com.wxm.service.BookService;
import com.wxm.util.PageBean;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wxm
 * @site www.wxm.com
 * @company xxx公司
 * @create 2019-11-24 16:38
 */
@Controller
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;

    @RequestMapping("/list")
    public String list(Book book, HttpServletRequest req) {
        PageBean pageBean = new PageBean();
        pageBean.setRequest(req);
        List<Map> list = bookService.listPager1(book, pageBean);
        req.setAttribute("bookList", list);
        req.setAttribute("pagebean", pageBean);
        return "bookList";
    }

    @RequestMapping("/add")
    public String add(Book book) {
        bookService.insertSelective(book);
        return "redirect:/book/list";
    }

    @RequestMapping("/edit")
    public String edit(Book book) {
        bookService.updateByPrimaryKeySelective(book);
        return "redirect:/book/list";
    }

    @RequestMapping("/del/{bid}")
    public String del(@PathVariable("bid") Integer bid) {
        bookService.deleteByPrimaryKey(bid);
        return "redirect:/book/list";
    }

    @RequestMapping("/preSave")
    public String preSave(Book book, HttpServletRequest req) {
        if (book == null || book.getBid() == null || book.getBid() == 0) {
            return "bookEdit";
        }
        Map map = bookService.listPager1(book, null).get(0);
        System.out.println(map);
        req.setAttribute("b", map);
        return "bookEdit";
    }

    @RequestMapping("/upload")
    public String upload(HttpServletRequest req, MultipartFile myfile) {
        String fileName = myfile.getOriginalFilename();
        String contentType = myfile.getContentType();
        try {
            FileUtils.copyInputStreamToFile(myfile.getInputStream(), new File("E:/images/" + fileName));
//            FileUtils.copyInputStreamToFile(myfile.getInputStream());("E:/images/"+fileName
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/book/list";
    }

    @ResponseBody
    @RequestMapping("/json1")
    public Map json1(HttpServletRequest req) {
        Map map = new HashMap();
        PageBean pageBean = new PageBean();
        pageBean.setRequest(req);
        Book book = new Book();
        book.setBname("");
        List<Map> list = bookService.listPager1(book, pageBean);
        map.put("total", pageBean.getTotal());
        map.put("data", list);


        return map;
    }

    @ResponseBody
    @RequestMapping("/json2")
    public List<Map> json2(HttpServletRequest req) {
        PageBean pageBean = new PageBean();
        pageBean.setRequest(req);
        Book book = new Book();
        book.setBname("");
        List<Map> list = bookService.listPager1(book, pageBean);


        return list;
    }
    @ResponseBody
    @RequestMapping("/json3")
    public String json3() {
        return "springmvc string to json";
    }
}