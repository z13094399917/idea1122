import com.wxm.service.BookService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wxm
 * @site www.wxm.com
 * @company xxx公司
 * @create 2019-11-17 22:05
 */
public class BookServiceImplTest extends BaseTestCase {

    @Autowired
    private BookService bookService;
    @Test
    public void insert(){
        book.setBid(121236);
         book.setBname("红楼梦");
         book.setPrice(123121.0f);
        bookService.insert(book);
        System.out.println(bookService.insert(book));
    }
    @Test
    public void selectByPrimaryKey() {
        System.out.println(bookService.selectByPrimaryKey(11));
        System.out.println(bookService.selectByPrimaryKey(11));

    }


    @Test
    public void listPager(){
        Map map=new HashMap();
//        map.put("bname","圣墟");
        PageBean.setPagination(true);
        PageBean.setPage(1);
        PageBean.setRows(5);
        List<Map> books=this.bookService.listPager(map,PageBean);
        for (Map book1 : books) {
            System.out.println(book1);
        }
        System.out.println(PageBean);


    }
}
