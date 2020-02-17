import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

/**
 * 研究bfs和dfs
 */

public class Test {

    //图的邻接矩阵表示法
    private static String[] vertexs = {"A", "B", "C", "D", "E", "F", "G", "H", "I"};

    static int[][] maze = new int[][]{
            {0, 1, 0, 0, 0, 1, 1, 0, 0},
            {1, 0, 1, 0, 0, 0, 1, 0, 1},
            {0, 1, 0, 1, 0, 0, 0, 0, 1},
            {0, 0, 1, 0, 1, 0, 1, 1, 1},
            {0, 0, 0, 1, 0, 1, 0, 1, 0},
            {1, 0, 0, 0, 1, 0, 1, 0, 0},
            {0, 1, 0, 1, 0, 1, 0, 1, 0},
            {0, 0, 0, 1, 1, 0, 1, 0, 0},
            {0, 1, 1, 1, 0, 0, 0, 0, 0}
    };

    private static boolean flag[] = new boolean[maze.length];

    //todo 图的邻接表再说

    public static void main(String[] args) {

//        BFS();
        //     DFS();
        DFSRecursion();
    }

    //递归
    private static void DFSRecursion() {
        for (int i = 0; i < maze.length; i++) {
            if (flag[i] == false) {
                DFSRecursion(i);
            }
        }
    }

    private static void DFSRecursion(int i) {
        flag[i] = true;
        System.out.println(vertexs[i]);
        for (int j = 0; j < maze.length; j++) {
            if (flag[j] = false && maze[i][j] != 0) {
                DFSRecursion(j);
            }
        }
    }

    //非递归
    private static void DFS() {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < maze.length; i++) {
            if (!flag[i]) {
                flag[i] = true;
                System.out.println(vertexs[i]);
                stack.push(i);
            }
            while (!stack.isEmpty()) {
                int k = stack.pop();
                for (int j = 0; j < maze.length; j++) {
                    if (maze[k][j] != 0 && flag[j] == false) {
                        flag[j] = true;
                        System.out.println(vertexs[j]);
                        stack.push(j);
                        break;
                    }
                }
            }
        }
    }


    private static void BFS() {
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < maze.length; i++) {
            if (!flag[i]) {
                flag[i] = true;
                System.out.println(vertexs[i]);
                queue.add(i);
                while (!queue.isEmpty()) {
                    int k = queue.poll();
                    for (int j = 0; j < maze.length; j++) {
                        if (maze[k][j] == 1 && flag[j] == false) {
                            flag[j] = true;
                            System.out.println(vertexs[j]);
                            queue.add(j);
                        }
                    }
                }
            }
        }
    }


}



