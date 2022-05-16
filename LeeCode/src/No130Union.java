public class No130Union {
    public static void main(String[] args) {
        char[][] board = new char[4][6];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = 'O';
            }
        }
        board[0][0] = 'X';
        board[0][2] = 'X';
        board[0][4] = 'X';
        board[1][1] = 'X';
        board[1][3] = 'X';
        board[1][5] = 'X';
        board[2][0] = 'X';
        board[2][2] = 'X';
        board[2][4] = 'X';
        board[3][1] = 'X';
        board[3][3] = 'X';
        board[3][5] = 'X';
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.printf("%c ",board[i][j]);
            }
            System.out.println();
        }
        Solution s = new Solution();
        System.out.println("*************************************************************");
        s.solve(board);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.printf("%c ",board[i][j]);
            }
            System.out.println();
        }
    }
}


class Solution {

    private int[] id;
    private int m, n;
    private int[] boundary;
    private int b_cnt = 0;
    private int i_cnt = 0;

    public void solve(char[][] board) {

        m = board.length;
        n = board[0].length;
        id = new int[m * n];
        boundary = new int[m * n];

        // 初始化 id 数组
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                id[i * n + j] = i * n + j;
            }
        }

        // union
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 边界为 ‘o’
                if (board[i][j] == 'O' && (i == 0 || i == m - 1 || j == 0 || j == n - 1)) {
                    boundary[b_cnt++] = i * n + j;
                } else if (board[i][j] == 'O') {
                    i_cnt++;
                    if (board[i - 1][j] == 'O') union((i - 1) * n + j, i * n + j);
                    if (board[i + 1][j] == 'O') union((i + 1) * n + j, i * n + j);
                    if (board[i][j - 1] == 'O') union(i * n + j - 1, i * n + j);
                    if (board[i][j + 1] == 'O') union(i * n + j + 1, i * n + j);
                }
            }
        }
        if (b_cnt == 0) {
            for (int i = 1; i < n - 1; i++) {
                for (int j = 0; j < m - 1; j++) {
                    board[i][j] = 'X';
                }
            }
            return;
        }
        for (int i = 1; i < m - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                if (board[i][j] == 'O') {
                    boolean flag = false;
                    for (int k = 0; k < b_cnt; k++) {
                        // 如果有一个联通，flag = true， 推出 k 循环
                        if (find(i * n + j) == find(boundary[k])) {
                            flag = true;
                            break;
                        }
                    }
                    if (!flag) {
                        board[i][j] = 'X';
                    }
                }
            }
        }

    }

    public int find(int p) {
        while (id[p] != p) {
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