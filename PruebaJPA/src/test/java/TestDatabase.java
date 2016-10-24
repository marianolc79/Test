/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import com.framework.ApplicationContextProvider;
import com.framework.data.MyEntityInfo;
import com.framework.data.MySort;
import com.framework.entities.Cliente;
import com.framework.services.GenericService;
import com.framework.services.GenericServicesFactory;
import java.util.List;
import java.util.Set;
import javax.sql.DataSource;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author mariano
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/applicationContext.xml"})
public class TestDatabase {

    public TestDatabase() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        SimpleNamingContextBuilder builder = new SimpleNamingContextBuilder();
        DataSource ds = new DriverManagerDataSource(
                "com.mysql.jdbc.Driver",
                "jdbc:mysql://localhost:3306/prueba_perf",
                "root",
                "root");
        builder.bind("java:comp/env/jdbc/tutorialDS", ds);
        builder.activate();
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //

    @Test
    public void testAllClientes() {
        ApplicationContext ctx = ApplicationContextProvider.getApplicationContext();
        GenericServicesFactory fac = (GenericServicesFactory) ctx.getBean("genericServiceFactory");
        GenericService s = fac.getServiceForEntity(Cliente.class.getCanonicalName());
        Page<Cliente> page = s.findAllPage(1, 50, "id", "asc");
        assert (page.getContent().size() == 50);

//        MyEntityInfo entInfo = (MyEntityInfo) ctx.getBean("Cliente");
//        Set<String> keys = entInfo.getMapping().keySet();
//        for (String key : keys) {
//            System.out.println(key + "=" + entInfo.getMapping().get(key).getQueryText());
//        }
        
        List<Cliente> list = s.findNamedQuery(Cliente.FINDALL, new MySort[] {new MySort("2",MySort.DIRECTION_DESC)});
        for(Cliente c: list) {
            System.out.println(c);
        } 
    }
}