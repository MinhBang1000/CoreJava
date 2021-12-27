
import java.util.*;

public class Graph {

    private int[][] A;
    private int n;
    private int[] pi;
    private int[] p;
    private boolean[] mark; // false - did not mark, true - marked

    public Graph(int n) {
        this.n = n;
        this.A = new int[50][50];
        this.mark = new boolean[n + 1];
        this.pi = new int[50];
        this.p = new int[50];
    }

    public void initGraph() {
        int i, j;
        for (i = 1; i <= this.n; i++) {
            for (j = 1; j <= this.n; j++) {
                this.A[i][j] = 0;
            }
            mark[i] = false;
        }
    }

    public void addEdgeGraph(int xIndex, int yIndex, int wValue) {
        this.A[xIndex][yIndex] = wValue;
//        this.A[yIndex][xIndex] = wValue;
    }

    public boolean adjacentGraph(int xV, int yV) {
        return this.A[xV][yV] != 0;
    }

    public List neighborsGraph(int xV) {
        int i;
        List rs = new List();
        rs.makeNullList();
        for (i = 1; i <= this.n; i++) {
            if (adjacentGraph(xV, i)) {
                rs.pushList(i);
            }
        }
        return rs;
    }

    public List bfs(int first) {
        List bfs = new List();
        mark[first] = true;
        Queue queue = new Queue();
        queue.pushQueue(first);
        while (!queue.emptyQueue()) {
            int element = queue.topQueue();
            bfs.pushList(element);
            queue.popQueue();
            List list = this.neighborsGraph(element);
            for (int i = 1; i <= list.getSize(); i++) {
                int vAdj = list.elementAt(i);
                if (this.mark[vAdj] == false) {
                    this.mark[vAdj] = true;
                    queue.pushQueue(vAdj);
                }
            }
        }
        return bfs;
    }

    public void resetMarkGraph() {
        for (int i = 0; i < this.n; i++) {
            this.mark[i + 1] = false;
        }
    }

    public List dfs(int first) {
        Stack stack = new Stack();
        List dfs = new List();
        stack.pushStack(first);
        while (!stack.emptyStack()) {
            int element = stack.topStack();
            stack.popStack();
            if (this.mark[element] == true) {
                continue;
            }
            this.mark[element] = true;
            dfs.pushList(element);
            List list = this.neighborsGraph(element);
            for (int i = 1; i <= list.getSize(); i++) {
                int y = list.elementAt(i);
                if (this.mark[y] == false) {
                    stack.pushStack(y);
                }
            }
        }
        return dfs;
    }

    public void dijkstra(int first) {
        int i, j, it;
        for (i = 1; i <= this.n; i++) {
            this.pi[i] = 9999;
            this.p[i] = 0;
            this.mark[i] = false;
        }
        this.pi[first] = 0;
        this.p[first] = -1;
        for (it = 0; it < this.n - 1; it++) {
            int minPi = 9999;
            for (j = 1; j <= this.n; j++) {
                if (this.mark[j] == false && minPi > this.pi[j]) {
                    i = j;
                    minPi = this.pi[j];
                }
            }
            this.mark[i] = true;
            List list = this.neighborsGraph(i);
            for (j = 1; j <= list.getSize(); j++) {
                int y = list.elementAt(j);
                if (mark[y] == false && this.pi[i] + this.A[i][y] < this.pi[y]) {
                    this.pi[y] = this.pi[i] + this.A[i][y];
                    p[y] = i;
                }
            }
        }
    }

    public int getN() {
        return n;
    }

    public int[] getPi() {
        return pi;
    }

    public int[] getP() {
        return p;
    }

    public boolean[] getMark() {
        return mark;
    }

    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        int n = kb.nextInt();
        int m = kb.nextInt();
        Graph graph = new Graph(n);
        graph.initGraph();
        for (int i = 0; i < m; i++) {
            int x = kb.nextInt();
            int y = kb.nextInt();
            int w = kb.nextInt();
            graph.addEdgeGraph(x, y, w);
        }
//        BFS and DFS
//        graph.bfs(1).printList();
//        graph.resetMarkGraph();
//        graph.dfs(1).printList();
//        DJIKSTRA
        graph.dijkstra(1);
        System.out.println("Shortest part to final node: " + graph.getPi()[n]);
    }
}
