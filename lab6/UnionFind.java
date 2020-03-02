import java.util.Arrays;

public class UnionFind {

    private final int INI_VERT = -1;
    private int[] unionFind;
    private int uFSize;

    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int n) {
        unionFind = new int[n];
        uFSize = n;
        Arrays.fill(unionFind, INI_VERT);
//        unionFind[1] = 3;
    }

    /* Throws an exception if v1 is not a valid index. */
    private void validate(int vertex) {
        if(vertex >= uFSize) {
            throw new IllegalArgumentException("Input is not a valid index. ");
        }
    }

    /* Returns the size of the set v1 belongs to. */
    public int sizeOf(int v1) {
        validate(v1);
        if(unionFind[v1] < 0) {
            return Math.abs(unionFind[v1]);
        }
        return sizeOf(unionFind[v1]);
    }

    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */
    public int parent(int v1) {
        validate(v1);
        return unionFind[v1];
    }

    /* Returns the root of v1. */
    private  int findRoot(int v1) {
        if(unionFind[v1] < 0) {
            return v1;
        } else {
            return findRoot(unionFind[v1]);
        }
    }

    /* Returns true if nodes v1 and v2 are connected. */
    public boolean connected(int v1, int v2) {
        validate(v1);
        validate(v2);
        if (findRoot(v1) == findRoot(v2)) {
            return true;
        }
        return false;
    }

    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid 
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v1's root to v2's root. Unioning a 
       vertex with itself or vertices that are already connected should not 
       change the sets but may alter the internal structure of the data. */
    public void union(int v1, int v2) {
        if(!connected(v1, v2)) {
            int root1 = findRoot(v1);
            int root2 = findRoot(v2);
            if(sizeOf(v1) <= sizeOf(v2)) {
                unionFind[root2] -= sizeOf(v1);
                unionFind[root1] = root2;
                unionFind[v1] = root2;
            } else {
                unionFind[root1] -= sizeOf(v2);
                unionFind[root2] = root1;
                unionFind[v2] = root1;
            }
        }
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. */
    public int find(int vertex) {
        return findRoot(vertex);
    }

    public void printUF() {
        for (int i : unionFind) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        UnionFind uF = new UnionFind(5);
//        uF.printUF();
        uF.union(1, 3);
//        uF.printUF();
        uF.union(2, 4);
//        uF.printUF();
        uF.union(1, 4);
        uF.printUF();
        uF.union(1, 0);
        uF.printUF();
    }
}

