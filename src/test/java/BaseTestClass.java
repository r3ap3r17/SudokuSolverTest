import nl.elridge.sudoku.model.Game;

public class BaseTestClass {
    private static boolean equals;
    public BaseTestClass() {}

    // Ova metoda vraca kopiju niza
    protected int[][] copyArr(int[][] source) {
        int[][] destination = new int[source.length][];

        for (int i = 0; i < source.length; ++i) {

            // allocating space for each row of destination array
            destination[i] = new int[source[i].length];
            System.arraycopy(source[i], 0, destination[i], 0, destination[i].length);
        }

        // displaying destination array
        return destination;
    }

    // Ova metoda vraca niz
    protected int[][] generateGameArray(Game game) {
        int[][] array = new int[9][9];
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++)
               array[y][x] =  game.getNumber(x, y);
        }

        return array;
    }

}
