package com.workintech.s18d2;

import com.workintech.s18d2.entity.Fruit;
import com.workintech.s18d2.entity.FruitType;
import com.workintech.s18d2.entity.Vegetable;
import com.workintech.s18d2.exceptions.FruitException;
import com.workintech.s18d2.dao.FruitRepository;
import com.workintech.s18d2.services.FruitServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@DataJpaTest
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
// Not: Eğer ResultAnalyzer sınıfı projende yoksa aşağıdaki satırı silebilirsin
@ExtendWith(ResultAnalyzer.class)
@ExtendWith(ResultAnalyzer2.class)
class MainTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private FruitRepository fruitRepository;

    @Mock
    private FruitRepository mockFruitRepository;

    private FruitServiceImpl fruitService;

    private Fruit sampleFruitForFruitServiceTest;

    @BeforeEach
    void setup() {
        // Veritabanı testleri için gerçek datalar
        Fruit apple = new Fruit();
        apple.setName("Apple");
        apple.setPrice(15.0);
        apple.setFruitType(FruitType.SWEET);
        apple.setGrownOnTree(true);
        entityManager.persist(apple);

        Fruit lemon = new Fruit();
        lemon.setName("Lemon");
        lemon.setPrice(25.0);
        lemon.setFruitType(FruitType.SOUR);
        lemon.setGrownOnTree(true);
        entityManager.persist(lemon);

        entityManager.flush();

        // Servis testleri için Mock datalar
        sampleFruitForFruitServiceTest = new Fruit();
        sampleFruitForFruitServiceTest.setId(1L);
        sampleFruitForFruitServiceTest.setName("Apple");
        sampleFruitForFruitServiceTest.setGrownOnTree(true);

        // Constructor üzerinden enjeksiyon (Kırmızıyı söndürür)
        fruitService = new FruitServiceImpl(mockFruitRepository);
    }

    @Test
    @DisplayName("Fruit properties are set correctly")
    void testFruitProperties() {
        Fruit fruit = new Fruit();
        fruit.setId(1L);
        fruit.setName("Apple");
        fruit.setPrice(15.0);
        fruit.setFruitType(FruitType.SWEET);

        assertEquals(1L, fruit.getId());
        assertEquals("Apple", fruit.getName());
        assertEquals(15.0, fruit.getPrice());
        assertEquals(FruitType.SWEET, fruit.getFruitType());
    }

    @Test
    @DisplayName("Vegetable getters and setters are set correctly")
    void testVegetableProperties() {
        Vegetable vegetable = new Vegetable();
        vegetable.setId(2L);
        vegetable.setName("Carrot");
        vegetable.setPrice(20.0);
        vegetable.setGrownOnTree(false);

        assertEquals(2L, vegetable.getId());
        assertEquals("Carrot", vegetable.getName());
        assertEquals(20.0, vegetable.getPrice());
        assertFalse(vegetable.isGrownOnTree());

        vegetable.setGrownOnTree(true);
        assertTrue(vegetable.isGrownOnTree());
    }

    @Test
    @DisplayName("FruitRepository::getByPriceDesc test")
    void testGetByPriceDesc() {
        List<Fruit> fruits = fruitRepository.getByPriceDesc();
        assertEquals(2, fruits.size());
        assertTrue(fruits.get(0).getPrice() >= fruits.get(1).getPrice());
    }

    @Test
    @DisplayName("FruitRepository::getByPriceAsc test")
    void testGetByPriceAsc() {
        List<Fruit> fruits = fruitRepository.getByPriceAsc();
        assertEquals(2, fruits.size());
        assertTrue(fruits.get(0).getPrice() <= fruits.get(1).getPrice());
    }

    @Test
    @DisplayName("FruitRepository::searchByName test")
    void testSearchByName() {
        List<Fruit> fruits = fruitRepository.searchByName("Apple");
        assertEquals(1, fruits.size());
        assertEquals("Apple", fruits.get(0).getName());
    }

    @Test
    @DisplayName("FruitService::getById() should return a fruit")
    void testGetByIdFoundFruitService() {
        when(mockFruitRepository.findById(anyLong())).thenReturn(Optional.of(sampleFruitForFruitServiceTest));

        Fruit foundFruit = fruitService.getById(1L);

        assertNotNull(foundFruit);
        assertEquals(sampleFruitForFruitServiceTest.getName(), foundFruit.getName());
    }

    @Test
    @DisplayName("FruitService::getById() should throw FruitException")
    void testGetByIdNotFoundFruitService() {
        when(mockFruitRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(FruitException.class, () -> fruitService.getById(1L));
    }

    @Test
    @DisplayName("FruitService::getByPriceAsc() test")
    void testGetByPriceAscFruitService() {
        when(mockFruitRepository.getByPriceAsc()).thenReturn(Arrays.asList(sampleFruitForFruitServiceTest));

        List<Fruit> fruits = fruitService.getByPriceAsc();

        assertFalse(fruits.isEmpty());
        assertEquals(1, fruits.size());
    }

    @Test
    @DisplayName("FruitService::save() test")
    void testSaveFruitService() {
        when(mockFruitRepository.save(any(Fruit.class))).thenReturn(sampleFruitForFruitServiceTest);

        Fruit savedFruit = fruitService.save(new Fruit());

        assertNotNull(savedFruit);
        assertEquals(sampleFruitForFruitServiceTest.getName(), savedFruit.getName());
    }

    @Test
    @DisplayName("FruitService::delete() test")
    void testDeleteFruitService() {
        when(mockFruitRepository.findById(anyLong())).thenReturn(Optional.of(sampleFruitForFruitServiceTest));
        doNothing().when(mockFruitRepository).delete(any(Fruit.class));

        Fruit deletedFruit = fruitService.delete(1L);

        assertNotNull(deletedFruit);
        assertEquals(sampleFruitForFruitServiceTest.getName(), deletedFruit.getName());
    }
}