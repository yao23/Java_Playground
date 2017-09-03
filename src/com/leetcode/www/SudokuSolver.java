package com.leetcode.www;

public class SudokuSolver { // LC 37
    public void solveSudoku(char[][] board) { // beast 57.67%
        solveSudokuRecursive(board);
    }
    private boolean solveSudokuRecursive(char[][] board) {
        int[] pairs = getFirstEmpty(board);
        if (pairs[0] == -1 && pairs[1] == -1) {
            return true;
        }
        for (int i = 1; i <= 9; i++) {
            board[pairs[0]][pairs[1]] = (char)(i + '0');
            if (isValid(board, pairs[0], pairs[1]) && solveSudokuRecursive(board)) {
                return true;
            }
            board[pairs[0]][pairs[1]] = '.'; //backtrack
        }
        return false;
    }
    private int[] getFirstEmpty(char[][] board) {
        int[] pairs = null;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.' ) {
                    pairs = new int[]{i, j};
                    return pairs;
                }
            }
        }
        pairs = new int[]{-1, -1};
        return pairs;
    }
    private boolean isValid(char[][] board, int x, int y) {
        for (int i = 0; i < 9; i++) {
            if (i != y && board[x][i] == board[x][y]) { // col
                return false;
            }
            if (i != x && board[i][y] == board[x][y]) { // row
                return false;
            }
        }
        int xIdx = (x / 3) * 3; // offset in row index
        int yIdx = (y / 3) * 3; // offset in col index
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i + xIdx != x && j + yIdx != y && board[i + xIdx][j + yIdx] == board[x][y]) {
                    return false;    // nine grids have same one existed
                }
            }
        }
        return true;
    }


    // Original author: Hsien Loong Lee (http://bit.ly/1zfIGMc)
    // Slight modification by @1337c0d3r to adapt to run on LeetCode OJ.
    // https://leetcode.com/problems/sudoku-solver/
    private int[] InBlock = new int[81];
    private int[] InRow = new int[81];
    private int[] InCol = new int[81];
    private static int BLANK = 0;
    private static int ONES = 0x3fe; 	// Binary 1111111110

    private int[] Entry = new int[81];	// Records entries 1-9 in the grid, as the corresponding bit set to 1
    private int[] Block = new int[9];
    private int[] Row = new int[9];
    private int[] Col = new int[9];	// Each int is a 9-bit array

    private int SeqPtr = 0;
    private int[] Sequence = new int[81];

    private void swapSeqEntries(int S1, int S2) {
        int temp = Sequence[S2];
        Sequence[S2] = Sequence[S1];
        Sequence[S1] = temp;
    }

    private void initEntry(int i, int j, int val) {
        int Square = 9 * i + j;
        int valbit = 1 << val;
        int SeqPtr2;

        // add suitable checks for data consistency

        Entry[Square] = valbit;
        Block[InBlock[Square]] &= ~valbit;
        Col[InCol[Square]] &= ~valbit; // Simpler Col[j] &= ~valbit;
        Row[InRow[Square]] &= ~valbit; // Simpler Row[i] &= ~valbit;

        SeqPtr2 = SeqPtr;
        while (SeqPtr2 < 81 && Sequence[SeqPtr2] != Square)
            SeqPtr2++ ;

        swapSeqEntries(SeqPtr, SeqPtr2);
        SeqPtr++;
    }

    void printArray(char[][] board) {
        int i, j, valbit, val, Square;
        char ch = '.';

        Square = 0;

        for (i = 0; i < 9; i++) {
            for (j = 0; j < 9; j++) {
                valbit = Entry[Square++];
                if (valbit == 0) ch = '-';
                else {
                    for (val = 1; val <= 9; val++)
                        if (valbit == (1 << val)) {
                            ch = (char)('0' + val);
                            break;
                        }
                }
                board[i][j] = ch;
            }
        }
    }

    private int nextSeq(int S) {
        int S2 = 0, Square, Possibles, BitCount;
        int T, MinBitCount = 100;

        for (T = S; T < 81; T++) {
            Square = Sequence[T];
            Possibles = Block[InBlock[Square]] & Row[InRow[Square]] & Col[InCol[Square]];
            BitCount = 0;
            while (Possibles != 0) {
                Possibles &= ~(Possibles & -Possibles);
                BitCount++;
            }

            if (BitCount < MinBitCount) {
                MinBitCount = BitCount;
                S2 = T;
            }
        }

        return S2;
    }


    private void place(int S, char[][] board) {
        if (S >= 81) {
            printArray(board);
            return;
        }

        int S2 = nextSeq(S);
        swapSeqEntries(S, S2);

        int Square = Sequence[S];

        int 	BlockIndex = InBlock[Square],
                RowIndex = InRow[Square],
                ColIndex = InCol[Square];

        int Possibles = Block[BlockIndex] & Row[RowIndex] & Col[ColIndex];
        while (Possibles != 0) {
            int valbit = Possibles & (-Possibles); // Lowest 1 bit in Possibles
            Possibles &= ~valbit;
            Entry[Square] = valbit;
            Block[BlockIndex] &= ~valbit;
            Row[RowIndex] &= ~valbit;
            Col[ColIndex] &= ~valbit;

            place(S + 1, board);

            Entry[Square] = BLANK; // Could be moved out of the loop
            Block[BlockIndex] |= valbit;
            Row[RowIndex] |= valbit;
            Col[ColIndex] |= valbit;
        }

        swapSeqEntries(S, S2);
    }

    void solveSudoku(char[][] board, int m, int n) {
        SeqPtr = 0;
        int i, j, Square;

        for (i = 0; i < 9; i++)
            for (j = 0; j < 9; j++) {
                Square = 9 * i + j;
                InRow[Square] = i;
                InCol[Square] = j;
                InBlock[Square] = (i / 3) * 3 + ( j / 3);
            }


        for (Square = 0; Square < 81; Square++) {
            Sequence[Square] = Square;
            Entry[Square] = BLANK;
        }

        for (i = 0; i < 9; i++)
            Block[i] = Row[i] = Col[i] = ONES;

        for (int r = 0; r < 9; ++r) {
            for (int c = 0; c < 9; ++c) {
                if ('.' != board[r][c]) {
                    initEntry(r, c, board[r][c] - '0');
                }
            }
        }

        place(SeqPtr, board);
    }
}
