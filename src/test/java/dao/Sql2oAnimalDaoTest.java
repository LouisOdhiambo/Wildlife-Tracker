package dao;

import models.Animal;
import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class Sql2oAnimalDaoTest {
    private Sql2oAnimalDao animalDao;
    private Connection conn;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        animalDao = new Sql2oAnimalDao(sql2o); //ignore me for now
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    Animal testAnimal(){
        return new Animal("Lion", 1);
    }

    @Test
    public void addingAnAnimalSetsId() throws Exception{
        Animal animal = testAnimal();
        int animalId = animal.getId();
        animalDao.add(animal);
        assertNotEquals(animalId, animal.getId());
    }

    @Test
    public void anAnimalCanBeFoundById() throws Exception{
        Animal animal = testAnimal();
        animalDao.add(animal);
        Animal foundAnimal = animalDao.findById(animal.getId());
        assertEquals(foundAnimal, animalDao.findById(animal.getId()));
    }

    @Test
    public void listOfAllAnimalsCanBeRetrived() throws Exception{
        Animal animal = testAnimal();
        animalDao.add(animal);
        assertEquals(1, animalDao.getAll().size());
    }

    @Test
    public void ifNoAnimalReturnsZero() throws Exception{
        assertEquals(0, animalDao.getAll().size());
    }

    @Test
    public void deleteByIdDeletesASingleAnimal() throws Exception {
        Animal animal = testAnimal();
        animalDao.add(animal);
        animalDao.deleteById(animal.getId());
        assertEquals(0, animalDao.getAll().size());
    }

    @Test
    public void clearAllDeletesAllAnimals() throws Exception{
        Animal animal = testAnimal();
        Animal anotherAnimal = new Animal("Zebra", 2);
        animalDao.add(animal);
        animalDao.add(anotherAnimal);
        animalDao.clearAllAnimals();
        assertEquals(0, animalDao.getAll().size());
    }

    @Test
    public void rangerIdIsReturnedCorrectly() throws Exception {
        Animal animal = testAnimal();
        int originalRangerId = animal.getRangerId();
        animalDao.add(animal);
        assertEquals(originalRangerId, animalDao.findById(animal.getId()).getRangerId());
    }
}