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

    Endangered testEndangered(){
        return new Endangered("tiger","okay", 1);
    }

    @Test
    public void addingAnAnimalSetsId() throws Exception{
        Endangered endangered = testEndangered();
        int endangeredId = endangered.getId();
        endangeredDao.add(endangered);
        assertNotEquals(endangeredId, endangered.getId());
    }

    @Test
    public void anAnimalCanBeFoundById() throws Exception{
        Endangered endangered = testEndangered();
        endangeredDao.add(endangered);
        Endangered foundEndangered = endangeredDao.findById(endangered.getId());
        assertEquals(foundEndangered, endangeredDao.findById(endangered.getId()));
    }

    @Test
    public void listOfAllAnimalsCanBeRetrived() throws Exception{
        Endangered endangered = testEndangered();
        endangeredDao.add(endangered);
        assertEquals(1, endangeredDao.getAll().size());
    }

    @Test
    public void ifNoAnimalReturnsZero() throws Exception{
        assertEquals(0, endangeredDao.getAll().size());
    }

    @Test
    public void deleteByIdDeletesASingleAnimal() throws Exception {
        Endangered endangered = testEndangered();
        endangeredDao.add(endangered);
        endangeredDao.deleteById(endangered.getId());
        assertEquals(0, endangeredDao.getAll().size());
    }

    @Test
    public void clearAllDeletesAllAnimals() throws Exception{
        Endangered endangered = testEndangered();
        Endangered anotherEndangered = new Endangered("gorilla","okay", 2);
        endangeredDao.add(endangered);
        endangeredDao.add(anotherEndangered);
        endangeredDao.clearAllEndangered();
        assertEquals(0, endangeredDao.getAll().size());
    }

    @Test
    public void rangerIdIsReturnedCorrectly() throws Exception {
        Endangered endangered = testEndangered();
        int originalRangerId = endangered.getRangerId();
        endangeredDao.add(endangered);
        assertEquals(originalRangerId, endangeredDao.findById(endangered.getId()).getRangerId());
    }
}