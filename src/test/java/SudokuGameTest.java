import nl.elridge.sudoku.model.Game;
import nl.elridge.sudoku.view.Sudoku;
import org.junit.jupiter.api.*;

import javax.swing.*;
import java.util.Arrays;

public class SudokuGameTest extends BaseTestClass {
    private static Game game = new Game();
    private static BaseTestClass test = new BaseTestClass();


    @BeforeEach
    public void setupSudoku() {
        game.newGame();
    }

    @Test
    public void setNumbersTest() {
        int x = 3;
        int y = 6;
        int n = 5;
        game.setNumber(x, y, n);
        Assertions.assertEquals(game.getNumber(x, y), test.generateGameArray(game)[y][x]);
    }

    // Ova test metoda treba da proveri da li je game.game niz promenjen nakon game.newGame()
    @Test
    public void clickNewGameTest() {
        int[][] tmp = copyArr(test.generateGameArray(game));
        game.newGame();
        Assertions.assertFalse(Arrays.deepEquals(test.generateGameArray(game), tmp));
    }

    @Test
    public void helpOnCheckBoxTrueTest() {
        game.setHelp(true);
        Assertions.assertTrue(game.isHelp());
    }

    @Test
    public void helpOnCheckBoxFalseTest() {
        game.setHelp(false);
        Assertions.assertFalse(game.isHelp());
    }

    @Test
    public void selectedNumberTest() {
        int x = 3;
        int y = 6;
        int n = 5;
        game.setSelectedNumber(n);
        game.setNumber(x, y, game.getSelectedNumber());
        Assertions.assertEquals(game.getNumber(x, y), game.getSelectedNumber());
    }

    @Test
    public void isSelectedNumberCandidateValidTrue() {
        int n = 1;
        game.checkGame();
        game.setSelectedNumber(n);
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                if (game.isSelectedNumberCandidate(x, y) && game.isCheckValid(x, y)) {
                    game.setNumber(x, y, game.getSelectedNumber());
                    Assertions.assertTrue(game.isCheckValid(x, y));
                }
            }
        }
    }

    @Test
    public void isSelectedNumberCandidateValidFalse() {
        int n = 1;
        game.checkGame();
        game.setSelectedNumber(n);
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                if (game.isSelectedNumberCandidate(x, y) && !game.isCheckValid(x, y)) {
                    game.setNumber(x, y, game.getSelectedNumber());
                    Assertions.assertFalse(game.isCheckValid(x, y));
                }
            }
        }
    }

    @Test
    public void testGui() {
        // Use System Look and Feel
        try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); }
        catch (Exception ex) { ex.printStackTrace(); }
        new Sudoku();
    }

    @AfterEach
    public void endTestMessage() {
        System.out.println("Test Method Over");
    }
    @AfterAll
    public static void quitSudoku() {
        System.exit(0);
    }
}
