package com.wxm.tag;

import com.wxm.util.PageBean;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
/**
 * @author wxm
 * @site www.wxm.com
 * @company xxx公司
 * @create 2019-11-24 16:30
 */
public class PageTag extends BodyTagSupport {

    private static final long serialVersionUID = 383258790166308355L;

    private PageBean pageBean;

    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        try {
            out.print(toHTML());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return SKIP_BODY;
    }

    private String toHTML() {
        StringBuilder sb = new StringBuilder();
//     拼接下一次分页请求搜提交的分页表单
        sb.append("<form id='pageBeanForm' action='"+pageBean.getUrl()+"' method='post'>");
        sb.append("<input type='hidden' name='page'> ");
        Map<String, String[]> paMap = pageBean.getPaMap();
        if(paMap != null && paMap.size()>0) {
            Set<Entry<String, String[]>> entrySet = paMap.entrySet();

            for (Entry<String, String[]> entry : entrySet) {
                for(String value :entry.getValue() ) {
                    if(!"page".equals(entry.getKey())) {
                        sb.append("<input type='hidden' name='"+entry.getKey()+"' value='"+value+"'>");
                    }
                }
            }
        }
        sb.append("</form>");
//     拼接分页条
        sb.append("<div style='text-align: right; font-size: 12px;'>");
        sb.append(" 每页"+pageBean.getRows()+"条，共"+pageBean.getTotal()+"条，第"+pageBean.getPage()+"页，共"+pageBean.getMaxPage()+"页&nbsp;&nbsp;");
        sb.append(" <a href='javascript:gotoPage(1)'>首页</a>&nbsp;&nbsp;");
        sb.append(" <a href='javascript:gotoPage("+pageBean.getPreviousPage()+")'>上一页</a>&nbsp;&nbsp;");
        sb.append(" <a href='javascript:gotoPage("+pageBean.getNextPage()+")'>下一页</a>&nbsp;&nbsp;");
        sb.append(" <a href='javascript:gotoPage("+pageBean.getMaxPage()+")'>尾页</a>&nbsp;&nbsp;");
        sb.append(" <input type='text' id='skipPage' style='text-align: center; font-size: 12px; width: 50px;'>&nbsp;&nbsp;");
        sb.append(" <a href='javascript:skipPage()'>Go</a>");
        sb.append(" </div>");
//     拼接分页的javascript 代码
        sb.append("<script type='text/javascript'>");
        sb.append("  function gotoPage(page) {");
        sb.append("     document.getElementById('pageBeanForm').page.value = page;");
        sb.append("     document.getElementById('pageBeanForm').submit();");
        sb.append(" }");
        sb.append(" function skipPage() { ");
        sb.append("   var page = document.getElementById('skipPage').value;");
        sb.append("   if(!page || isNaN(page) || parseInt(page)<1 || parseInt(page)>"+pageBean.getMaxPage()+"){");
        sb.append("       alert('请输入1~N的数字');");
        sb.append("        return;");
        sb.append("  }");
        sb.append("  gotoPage(page);");
        sb.append(" }");
        sb.append("</script> ");
        return sb.toString();
    }

    public PageBean getPageBean() {
        return pageBean;
    }

    public void setPageBean(PageBean pageBean) {
        this.pageBean = pageBean;
    }

}
