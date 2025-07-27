import org.example.hw1.SimpleHashMap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HW1SimpleHashMapTest {

    @Test
    public void nullKeyTest() {
        SimpleHashMap<Integer, Integer> map = new SimpleHashMap<>();
        map.put(null, 5);
        Assertions.assertEquals(5, map.get(null));
        map.put(null, 10);
        Assertions.assertEquals(10, map.get(null));
        map.remove(null);
        Assertions.assertNull(map.get(null));
    }

    @Test
    public void simpleKeysTest() {
        SimpleHashMap<Integer, Integer> map = new SimpleHashMap<>();
        map.put(4, 5);
        Assertions.assertEquals(5, map.get(4));
        map.put(20, 10);
        Assertions.assertEquals(10, map.get(20));
        map.remove(4);
        Assertions.assertEquals(10, map.get(20));
    }
}
