package mybatisStudy;

import com.liu.common.GameUser;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.Reader;

/**
 * Created by liuzhilei on 2017/9/11.
 * mybatis的测试类
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:base/spring-config.xml"})
public class MybatisTest {

    @Test
    public void startMybatis() throws Exception {
        //配置文件名
        String resource = "mybatisStudy/mybatis.xml";
        //封装文件
        Reader reader = Resources.getResourceAsReader(resource);
        //开始启动
        SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(reader);

        SqlSession sqlSession = ssf.openSession();
        GameUser user = (GameUser) sqlSession.selectOne("selectById", 89);
        System.out.println("第一个用户：" + user);

        GameUser user1 = (GameUser) sqlSession.selectOne("selectById", 89);
        System.out.println("第二次查询第一个用户：" + user);

    }

}
