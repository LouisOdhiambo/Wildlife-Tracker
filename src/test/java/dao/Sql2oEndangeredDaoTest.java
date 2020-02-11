package dao;

import models.Endangered;
import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class Sql2oEndangeredDaoTest {
    private Sql2oEndangeredDao endangeredDao;
    private Connection conn;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        endangeredDao = new Sql2oEndangeredDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }
}