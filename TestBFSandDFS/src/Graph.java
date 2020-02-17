import java.util.LinkedList;

public class Graph {
    private final int v; //顶点数
    private int e;  //边数
    private LinkedList<Integer>[] adj; //邻接表

    public Graph(int v) {
        this.v = v;
        this.e = 0;
        for (int i = 0; i < v; i++) {
            adj[i - 1] = new LinkedList<>();
        }
    }

    public int getV() {
        return v;
    }

    public int getE() {
        return e;
    }

    //无向图有向图的区别基本就在这里
    public void addEdge(int v, int w) {
        adj[v].add(w);
//        adj[w].add(v);
        e++;
    }

    public Iterable<Integer> adj(int v) {
        return adj[v];
    }
}
