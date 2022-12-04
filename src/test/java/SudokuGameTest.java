import nl.elridge.sudoku.model.Game;
import nl.elridge.sudoku.view.Sudoku;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;

public class SudokuGameTest extends BaseTestClass {
    private static Game game = new Game();
    private static BaseTestClass test = new BaseTestClass();


    @BeforeEach
    public void setupSudoku() {
        game.newGame();
    }

    // Ova test metoda treba da proveri da li je game.game niz promenjen nakon game.newGame()
    @Test
    public void generateNewGameTest() {
        int[][] tmp = copyArr(test.generateGameArray(game));
        game.newGame();
        Assertions.assertFalse(Arrays.deepEquals(test.generateGameArray(game), tmp));
    }

    // Metoda za proveru help)n checkbox-a
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

    // Metoda koja proverava da li je selected number nakon postavljanja u zadato polje zapravo u tom polju
    @Test
    public void selectedNumberTest() {
        int x = 3;
        int y = 6;
        int n = 5;
        game.setSelectedNumber(n);
        game.setNumber(x, y, game.getSelectedNumber());
        Assertions.assertEquals(game.getNumber(x, y), game.getSelectedNumber());
    }

    // Proverava da li je broj kandidat za to polje i da li to polje u nizu check nosi vrednost true
    // postavlja ga ako su ti uslovi ispunjeni i dokazuje da polje na koje je broj postavljen
    // ima vrednost true u nizu check[x][y]
    // korisceni su parametri ValueSource
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9 })
    public void isSelectedNumberCandidateValidTrue(int n) {
        // kreira boolean[][] check, gde vrednost true znaci da taj borj stoji na tom mestu
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
    // Za ovaj test sam koristio parametre iz sudoku-numbers.csv fajla
    @ParameterizedTest
    @CsvFileSource(resources = "sudoku-numbers.csv")
    public void isSelectedNumberCandidateValidFalse(int n) {
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

    // starts the gui
    @Test
    public void testGui() {
        Sudoku.main(null);
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
