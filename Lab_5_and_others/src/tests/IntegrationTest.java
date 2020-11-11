import agh.cs.lab5_6.World;
import agh.cs.lab5_6.maps.RectangularMap;
import agh.cs.lab5_6.movement.Vector2d;
import agh.cs.lab5_6.objects.Animal;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Integration tests including in-out streams,
 * String parsing and map updating
 */
public class IntegrationTest {
    private static final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private static final ByteArrayOutputStream err = new ByteArrayOutputStream();

    private static final PrintStream outOriginal = System.out;
    private static final PrintStream errOriginal = System.err;

    public static String[] input1;
    public static String[] input2;
    public static String[] input3;

    public static Animal animal1;
    public static Animal animal2;
    public static Animal animal3;
    public static Animal animal4;
    public static Animal animal5;

    public static RectangularMap small_map;
    public static RectangularMap big_map;

    @BeforeEach
    void setUpStreams() {
        System.setOut(new PrintStream(out));
        System.setErr(new PrintStream(err));

    }

    @AfterEach
    void restoreStreams() {
        System.setOut(outOriginal);
        System.setErr(errOriginal);
    }

    @BeforeAll
    static void initBeforeAll() {
        input1 = new String[]{"Hello", "from", "Belarus", "f", "f", "r", "r", "backwards", "f"};
        input2 = new String[]{"f", "l", "b", "r", "l", "b", "r", "f", "r", "l", "b", "forward", "r", "f", "f", "r",
                "r", "backwards", "f"};
        input3 = new String[]{"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"};
        small_map = new RectangularMap(2, 2);
        animal1 = new Animal(small_map, new Vector2d(0, 0));
        animal2 = new Animal(small_map, new Vector2d(1, 0));
        animal3 = new Animal(small_map, new Vector2d(0, 1));
        animal4 = new Animal(small_map, new Vector2d(1, 1));
        animal5 = new Animal(small_map, new Vector2d(1, 1));
        big_map = new RectangularMap(5, 5);
    }

    /**
     * It is impossible to check String because every time we get different
     * (because of random)
     */
    @Test
    void firstIntegrationTest() {
        World.main(input1);
        HashMap<Vector2d, Animal> HashMap = World.map.getAnimalHashMap();
        assertEquals(HashMap.toString(), "{(1,2)=>, (3,6)=^, (31,40)=>}");
    }

    @Test
    void secondIntegrationTest() {
        World.main(input2);
        HashMap<Vector2d, Animal> HashMap = World.map.getAnimalHashMap();
        assertEquals(HashMap.toString(), "{(3,3)=<, (1,2)=<, (34,38)=>}");
    }

    @Test
    void thirdIntegrationTest() {
        World.main(input3);
        HashMap<Vector2d, Animal> HashMap = World.map.getAnimalHashMap();
        assertEquals(HashMap.toString(), "{(5,4)=>, (1,5)=^, (35,40)=>}");
    }

    /**
     * Just to see that we have something on output
     * Also can be done better with for example checking if our Animals are at good position
     * But in this level of complexity it seems useless
     */
    @Test
    void outputIntegrationTest() {
        World.main(input3);
        assertTrue(
                out.toString().length() > 0
        );
    }
}
