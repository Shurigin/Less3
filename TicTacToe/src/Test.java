import java.util.Arrays;
public class Test {
    private static final char DOT_EMPTY = '●';
    private static final char DOT_X = 'X';
    private static final char DOT_0 = '0';


    public static void main(String[] arhs) {
        char[][] map = {{DOT_EMPTY, DOT_EMPTY, DOT_EMPTY},{DOT_EMPTY, DOT_EMPTY, DOT_EMPTY}, {DOT_EMPTY, DOT_EMPTY, DOT_EMPTY}};

        neighoborCell(1, 2);

       for(int i = 0; i < map.length; i++){
           for(int j = 0; j < map.length; j++){
               System.out.print(map[i][j]);
           }
           System.out.println();
       }

        
    }

    private static int neighoborCell(int x, int y) {  //метод ище соседние ячейки
        int result = 0; //переменная аккумулирует количество соседних ячеек типа 0
        char[][] map = new char[2][2];

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
        if (x != map.length - 1) {
            if (map[x + 1][y] == DOT_0) {
                result = result + 1;
            }
        }
        if (y != map.length - 1) {
            if (map[x][y + 1] == DOT_0) {
                result = result + 1;
            }
        }
        if (x != 0 && y != map.length - 1) {
            if (map[x - 1][y + 1] == DOT_0) {
                result = result + 1;
            }
        }
        if (x != map.length - 1 && y != 0) {
            if (map[x + 1][y - 1] == DOT_0) {
                result = result + 1;
            }
        }
        if (x != map.length - 1 && y != map.length - 1) {
            if (map[x + 1][y + 1] == DOT_0) {
                result = result + 1;
            }
        }



        return result;
    }
}
