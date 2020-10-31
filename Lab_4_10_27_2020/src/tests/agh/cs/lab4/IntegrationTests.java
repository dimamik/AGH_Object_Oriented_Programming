package agh.cs.lab4;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Integrationa Tests on the base of JUnit 5.0 Based on taking the out stream
 */
public class IntegrationTests {
    private static ByteArrayOutputStream out = new ByteArrayOutputStream();
    private static ByteArrayOutputStream err = new ByteArrayOutputStream();

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

    @Before
    public static void setUpStreams() {
        System.setOut(new PrintStream(out));
        System.setErr(new PrintStream(err));

    }

    public void SetUpStream2() {
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        System.setErr(new PrintStream(err));
    }

    @BeforeAll
    public static void init() {
        setUpStreams();
        input1 = new String[] { "Hello", "from", "Belarus", "f", "f", "r", "r", "backwards", "f" };
        input2 = new String[] { "f", "l", "b", "r", "l", "b", "r", "f", "r", "l", "b", "forward", "r", "f", "f", "r",
                "r", "backwards", "f" };
        input3 = new String[] { "f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f" };
        small_map = new RectangularMap(2, 2);
        animal1 = new Animal(small_map, new Vector2d(0, 0));
        animal2 = new Animal(small_map, new Vector2d(1, 0));
        animal3 = new Animal(small_map, new Vector2d(0, 1));
        animal4 = new Animal(small_map, new Vector2d(1, 1));
        animal5 = new Animal(small_map, new Vector2d(1, 1));
        big_map = new RectangularMap(5, 5);
    }

    @After
    public static void restoreStreams() {
        System.setOut(outOriginal);
        System.setErr(errOriginal);
    }

    @Test
    public void IT_Main_CheckClassesCompatibilityAndIntegrationOfMethods() {

        World.main(input3);
        assertEquals(" y\\x  0 1 2 3 4 5 6 7 8 9\r\n" + "  5: ---------------------\r\n"
                + "  4: | | | |^| | | | | | |\r\n" + "  3: | | | | | | | | | | |\r\n" + "  2: | | | | | | | | | | |\r\n"
                + "  1: | | | | | | | | | | |\r\n" + "  0: | | |v| | | | | | | |\r\n"
                + " -1: ---------------------\r\n\r\n", out.toString());
        restoreStreams();
    }

    @Test
    public void IT_CheckHowIsDealingWithAnimalsFillingTheMap() {
        small_map.place(animal1);
        small_map.place(animal2);
        small_map.place(animal3);
        small_map.place(animal4);
        small_map.place(animal5);
        List<MoveDirection> directions = new OptionsParser().parse(input1);
        small_map.run(directions);
        assertEquals(" y\\x  0 1\r\n" + "  2: -----\r\n" + "  1: |>|>|\r\n" + "  0: |^|^|\r\n" + " -1: -----\r\n",
                small_map.toString());
    }

    @Test
    public void IT_CheckMapCornerCasesOnLargeData() {
        SetUpStream2();
        World.main(input2);
        assertEquals(" y\\x  0 1 2 3 4 5 6 7 8 9\r\n" + "  5: ---------------------\r\n"
                + "  4: | |^| | | | | | | | |\r\n" + "  3: | | | | | | | | | | |\r\n" + "  2: | | | | | | | | | | |\r\n"
                + "  1: | |<| | | | | | | | |\r\n" + "  0: | | | | | | | | | | |\r\n" + " -1: ---------------------\r\n"
                + "\r\n", out.toString());

    }
}
