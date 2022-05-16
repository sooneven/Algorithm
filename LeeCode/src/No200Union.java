public class No200Union {
    public static void main(String[] args) {
        Solution200 slu = new Solution200();
        char[][] grid = new char[2][1];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = '1';
            }
        }
        grid[0][0] = '1';
        grid[1][0] = '1';
//        grid[0][4] = '0';
//        grid[1][2] = '0';
//        grid[1][4] = '0';
//        grid[2][2] = '0';
//        grid[2][3] = '0';
//        grid[2][4] = '0';
//        grid[3][0] = '0';
//        grid[3][1] = '0';
//        grid[3][2] = '0';
//        grid[3][3] = '0';
//        grid[3][4] = '0';
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                System.out.printf("%c ", grid[i][j]);
            }
            System.out.println();
        }
        System.out.println("************************************************");
        System.out.println(slu.numIslands(grid));
    }
}

class Solution200 {

    private int cnt;
    private int m;
    private int n;
    private int[] id;
    private int ans;
    private int zeros;

    public int numIslands(char[][] grid) {

        m = grid.length;
        n = grid[0].length;
        cnt = m * n;
        id = new int[m * n];
        ans = 0;
        zeros = 0;
        // 初始化 id 数组
        for (int i = 0; i < cnt; i++) {
            id[i] = i;
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // i 行， j 列
                if (grid[i][j] == '1') ans++;
                if (grid[i][j] == '1' && i - 1 >= 0 && grid[i - 1][j] == '1') union((i - 1) * n + j, i * n + j);
                if (grid[i][j] == '1' && i + 1 <= m - 1 && grid[i + 1][j] == '1') union((i + 1) * n + j, i * n + j);
                if (grid[i][j] == '1' && j - 1 >= 0 && grid[i][j - 1] == '1') union(i * n + j - 1, i * n + j);
                if (grid[i][j] == '1' && j + 1 <= n - 1 && grid[i][j + 1] == '1') union(i * n + j + 1, i * n + j);
            }
        }
        // System.out.println(ans);
        for (int i = 0; i < m * n; i++) {
            if (id[i] != i) ans--;
        }
        return ans;
    }

    public int find(int p) {
        while(p != id[p]) {
            p = id[p];
        }
        return p;
    }

    public void union(int p, int q) {
        int pid = find(p);
        int qid = find(q);
        id[pid] = qid;
    }

}