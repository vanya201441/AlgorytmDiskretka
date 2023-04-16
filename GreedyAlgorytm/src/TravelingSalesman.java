import java.util.Arrays;

public class TravelingSalesman {

    public static void main(String[] args) {
        int[][] graph = {
                {0, 10, 15, 20},
                {10, 0, 35, 25},
                {15, 35, 0, 30},
                {20, 25, 30, 0}
        };
        int[] path = new int[graph.length];

        // Выбираем случайный город, чтобы начать путешествие
        int city = (int) (Math.random() * graph.length);
        path[0] = city;

        // Выбираем на каждом шаге ближайший не посещенный город
        for (int i = 1; i < graph.length; i++) {
            int nextCity = -1;
            int minDistance = Integer.MAX_VALUE;
            for (int j = 0; j < graph.length; j++) {
                if (!contains(path, j)) {
                    int distance = graph[city][j];
                    if (distance < minDistance) {
                        minDistance = distance;
                        nextCity = j;
                    }
                }
            }
            path[i] = nextCity;
            city = nextCity;
        }

        System.out.println(Arrays.toString(path));
    }

    private static boolean contains(int[] array, int value) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                return true;
            }
        }
        return false;
    }

}
