import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
    //Константы
    private static char[][] map;
    private static int SIZE = 3;

    private static final char DOT_EMPTY = '●';
    private static final char DOT_X = 'X';
    private static final char DOT_0 = '0';

    private static final boolean SILLIY_MODE = false;

    private static Scanner scanner = new Scanner(System.in); //экземпляр класса
    private static Random random = new Random();


    public static void main(String[] args) {
        initMap();
        printMap();

        while (true) {
            humanTurn();    // Ход игрока
            if (isEndGame(DOT_X)) {
                break;

            }


            computerTurn();  // ход компьютера
            if (isEndGame(DOT_0)) {
                break;
            }


        }
    }


    /*
     *  Метод, который заоплнять символами ●, тем самым подготавливает поле к игре
     */
    private static void initMap() {
        map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }
    /*
     *   Метод выводит в консоль поля map
     */

    private static void printMap() {
        for (int i = 0; i <= SIZE; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i = 0; i < SIZE; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();

    }

    /*
     *   Ход человека
     */
    private static void humanTurn() {
        int x, y;

        do {
            System.out.println("Введите координаты ячейки");
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;

        } while (!isCellValid(x, y));

        map[y][x] = DOT_X;
    }

    private static void computerTurn() {

        int x = -1;
        int y = -1;

        int counter = 0;


        if (SILLIY_MODE) {
            do {
                y = random.nextInt(SIZE);
                x = random.nextInt(SIZE);

            } while (!isCellValid(x, y));


        } else {
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    if (neighoborCell(j, i) > counter) { //счетчик наибольшего количества ячеек по-соседству
                        x = j;
                        y = i;
                        counter = neighoborCell(j, i);
                    }
                }

            }
            System.out.println(counter);

            do {
                y = random.nextInt(SIZE);
                x = random.nextInt(SIZE);
            } while (!isCellValid(x, y));
        }
        System.out.println("Компьютер сгенерировал ячейку" + (x + 1) + " " + (y + 1));

        map[y][x] = DOT_0;
    }

    private static boolean isCellValid(int x, int y) {
        boolean result = true;

        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) {
            result = false;
        }

        if (map[y][x] != DOT_EMPTY) {
            result = false;
        }

        return result;
    }

    private static boolean isEndGame(char playerSymbol) {
        boolean result = false;
        printMap();
        //проверка необходимости следующего хода
        if (checkWin(playerSymbol)) {
            System.out.println("Победили " + playerSymbol);
            System.out.println("Игра закончина");
            result = true;
        }
        if (isMapFull()) {
            System.out.println("Ничья!");
            result = true;
        }


        return result;
    }

    /*
        Проверяем заполенность всех ячеек
     */
    private static boolean isMapFull() {
        boolean result = true;

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY) {
                    result = false;
                }
            }
        }
        return result;
    }

    /*
     *проверка победы
     */
    private static boolean checkWin(char playerSymbol) {
        boolean result = false;

        if (
                (map[0][0] == playerSymbol && map[0][1] == playerSymbol && map[0][2] == playerSymbol) ||
                        (map[1][0] == playerSymbol && map[1][1] == playerSymbol && map[1][2] == playerSymbol) ||
                        (map[2][0] == playerSymbol && map[2][1] == playerSymbol && map[2][2] == playerSymbol) ||
                        (map[0][0] == playerSymbol && map[1][0] == playerSymbol && map[2][0] == playerSymbol) ||
                        (map[0][1] == playerSymbol && map[1][1] == playerSymbol && map[2][1] == playerSymbol) ||
                        (map[0][2] == playerSymbol && map[1][2] == playerSymbol && map[2][2] == playerSymbol) ||
                        (map[0][0] == playerSymbol && map[1][1] == playerSymbol && map[2][2] == playerSymbol) ||
                        (map[2][0] == playerSymbol && map[1][1] == playerSymbol && map[0][2] == playerSymbol)) {
            result = true;
        }

        return result;
    }

    private static int neighoborCell(int x, int y) {  //метод ище соседние ячейки
        int result = 0;                   //переменная аккумулирует количество соседних ячеек типа 0
        if (isCellValid(x, y)) {
            if (x != 0) {
                if (map[x - 1][y] == DOT_0) {
                    result = result + 1;
                }
            }
            if (y != 0) {
                if (map[x][y - 1] == DOT_0) {
                    result = result + 1;
                }
            }
            if (x != 0 && y != 0) {
                if (map[x - 1][y - 1] == DOT_0) {
                    result = result + 1;
                }
            }
            if (x != SIZE - 1) {
                if (map[x + 1][y] == DOT_0) {
                    result = result + 1;
                }
            }
            if (y != SIZE - 1) {
                if (map[x][y + 1] == DOT_0) {
                    result = result + 1;
                }
            }
            if (x != 0 && y != SIZE - 1) {
                if (map[x - 1][y + 1] == DOT_0) {
                    result = result + 1;
                }
            }
            if (x != SIZE - 1 && y != 0) {
                if (map[x + 1][y - 1] == DOT_0) {
                    result = result + 1;
                }
            }
            if (x != SIZE - 1 && y != SIZE - 1) {
                if (map[x + 1][y + 1] == DOT_0) {
                    result = result + 1;
                }
            }
        }


        return result;
    }


}
