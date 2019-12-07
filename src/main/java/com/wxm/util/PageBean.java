package com.wxm.util;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wxm
 * @site www.wxm.com
 * @company xxx公司
 * @create 2019-11-16 11:15
 */
/**
 *分页工具
 *
 */
public class PageBean {

    private int page = 1;// 页码

    private int rows = 10;// 页大小

    private int total = 0;// 总记录数


    //保留上一次 的请求地址
    private String url="";
    //保留上一次请求所携带的参数
    private  Map<String, String[]> paMap=new HashMap<>();
    /**
     * pageBean初始化的方法
     * @param req
     */
    public void setRequest(HttpServletRequest req) {
        //改变它第几页的数据
        this.setPage(req.getParameter("page"));
        //改变它每页展示的数据
//    	System.out.println(req.getParameter("rows"));
        if(req.getParameter("rows")!=null) {
            this.setRows(Integer.parseInt(req.getParameter("rows")));
        }else {
            this.setRows(10);
        }
        //控制页面是否分页
        this.setPagination(req.getParameter("pagination"));


        this.setUrl(req.getRequestURL().toString());//上一次的地址
        this.setPaMap(req.getParameterMap());//上一次查询的参数
    }

    private void setPagination(String parameter) {
        // 当你填false就不分页
        if("false".equals(pagination)) {
            this.setPagination(false);
        }
    }
    public void setPage(String page) {
//    	如果不为空的时候
        if(StringUtils.isNotBlank(page)) {
            this.setPage(Integer.valueOf(page));
        }
    }
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }



    public Map<String, String[]> getPaMap() {
        return paMap;
    }

    public void setPaMap(Map<String, String[]> paMap) {
        this.paMap = paMap;
    }



    private boolean pagination = true;// 是否分页

    public PageBean() {
        super();
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setTotal(String total) {
        this.total = Integer.parseInt(total);
    }

    public boolean isPagination() {
        return pagination;
    }

    public void setPagination(boolean pagination) {
        this.pagination = pagination;
    }

    /**
     *获得起始记录的下标
     *
     * @return
     */
    public int getStartIndex() {
        return (this.page - 1) * this.rows;
    }

    @Override
    public String toString() {
        return "PageBean [page=" + page + ", rows=" + rows + ", total=" + total + ", pagination=" + pagination + "]";
    }
    /**
     * 最大页码
     * @return
     */
    public int getMaxPage() {
        return this.total%this.rows==0? this.total/this.rows:this.total/this.rows+1;

    }
    /**
     * 获取下一页
     * @return
     */
    public int getNextPage() {
        return this.page<this.getMaxPage()?this.page+1:this.page;

    }
    public int getPreviousPage() {
        return this.page>1?this.page-1:this.page;

    }
}

