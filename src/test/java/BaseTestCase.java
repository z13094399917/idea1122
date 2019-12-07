import com.wxm.model.Book;
import com.wxm.util.PageBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.junit.Before;
import org.junit.runner.RunWith;


/**
 * @author wxm
 * @site www.wxm.com
 * @company xxx公司
 * @create 2019-11-17 21:54
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class BaseTestCase {
    protected Book book;
    protected PageBean PageBean;

    @Before
    public void setUp(){
        book = new Book();
        PageBean = new PageBean();
    }
}
