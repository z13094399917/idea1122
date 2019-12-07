package com.wxm.aspect;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wxm.util.PageBean;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author wxm
 * @site www.wxm.com
 * @company xxx公司
 * @create 2019-11-17 11:13
 */
@Component
@Aspect
public class PagerAspect {
//拦截Peger 结尾的方法 做分页

    @Around("execution(* *..*Service.*Pager(..))")
    public Object invoke(ProceedingJoinPoint JoinPoint) throws Throwable {
//获取产生的列表
        Object[] args = JoinPoint.getArgs();
        PageBean pageBean = null;
        //找pegeBean对象
        for (Object arg : args) {
            if (arg instanceof PageBean) {
                pageBean = (PageBean) arg;
                break;
            }
        }
//如果pageBean对象不为空就开启分页
        if (pageBean != null && pageBean.isPagination())
            PageHelper.startPage(pageBean.getPage(), pageBean.getRows());

//                    执行方法并且获取了结果集
        Object list = JoinPoint.proceed(args);
//            将分页 结果信息放入到pageBean对象中
        if (null != pageBean && pageBean.isPagination()) {
            PageInfo PageInfo = new PageInfo((List) list);
            pageBean.setTotal(PageInfo.getTotal() + "");

        }
        return list;
    }
}


