
public class Algorithm {

	final static int BOARD_SIZE = 8;

	public static boolean checkValid(int x, int y, int c, int matrix[][],
			boolean flag) {
		int i = 0;
		int j = 0;
		boolean result = false;

		i = x + 1;
		if (i < BOARD_SIZE && matrix[i][y] != c && matrix[i][y] != -1) {
			do {
				i++;
			} while (i < BOARD_SIZE && matrix[i][y] != c && matrix[i][y] != -1);

			if (i < BOARD_SIZE && matrix[i][y] == c) {
				result = true;
				if (flag) {
					for (int k = x + 1; k < i; k++) {
						matrix[k][y] = c;
					}
				}
			}
		}

		i = x - 1;
		if (i > 0 && matrix[i][y] != c && matrix[i][y] != -1) {
			do {
				i--;
			} while (i > 0 && matrix[i][y] != c && matrix[i][y] != -1);

			if (i >= 0 && matrix[i][y] == c) {
				result = true;
				if (flag) {
					for (int k = x - 1; k > i; k--) {
						matrix[k][y] = c;
					}
				}
			}
		}

		j = y + 1;
		if (j < BOARD_SIZE && matrix[x][j] != c && matrix[x][j] != -1) {
			do {
				j++;
			} while (j < BOARD_SIZE && matrix[x][j] != c && matrix[x][j] != -1);

			if (j < BOARD_SIZE && matrix[x][j] == c) {
				result = true;
				if (flag) {
					for (int k = y + 1; k < j; k++) {
						matrix[x][k] = c;
					}
				}
			}
		}

		j = y - 1;
		if (j > 0 && matrix[x][j] != c && matrix[x][j] != -1) {
			do {
				j--;
			} while (j > 0 && matrix[x][j] != c && matrix[x][j] != -1);

			if (j >= 0 && matrix[x][j] == c) {
				result = true;
				if (flag) {
					for (int k = y - 1; k > j; k--) {
						matrix[x][k] = c;
					}
				}
			}
		}

		i = x + 1;
		j = y + 1;
		if (i < BOARD_SIZE && j < BOARD_SIZE && matrix[i][j] != c
				&& matrix[i][j] != -1) {
			do {
				i++;
				j++;
			} while (i < BOARD_SIZE && j < BOARD_SIZE && matrix[i][j] != c
					&& matrix[i][j] != -1);

			if (i < BOARD_SIZE && j < BOARD_SIZE && matrix[i][j] == c) {
				result = true;
				if (flag) {
					for (int k = x + 1, m = y + 1; k < i && m < j; k++, m++) {
						matrix[k][m] = c;
					}
				}
			}
		}

		i = x - 1;
		j = y - 1;
		if (i > 0 && j > 0 && matrix[i][j] != c && matrix[i][j] != -1) {
			do {
				i--;
				j--;
			} while (i > 0 && j > 0 && matrix[i][j] != c && matrix[i][j] != -1);

			if (i >= 0 && j >= 0 && matrix[i][j] == c) {
				result = true;
				if (flag) {
					for (int k = x - 1, m = y - 1; k > i && m > j; k--, m--) {
						matrix[k][m] = c;
					}
				}
			}
		}

		i = x + 1;
		j = y - 1;
		if (i < BOARD_SIZE && j > 0 && matrix[i][j] != c && matrix[i][j] != -1) {
			do {
				i++;
				j--;
			} while (i < BOARD_SIZE && j > 0 && matrix[i][j] != c
					&& matrix[i][j] != -1);

			if (i < BOARD_SIZE && j >= 0 && matrix[i][j] == c) {
				result = true;
				if (flag) {
					for (int k = x + 1, m = y - 1; k < i && m > j; k++, m--) {
						matrix[k][m] = c;
					}
				}
			}
		}

		i = x - 1;
		j = y + 1;
		if (i > 0 && j < BOARD_SIZE && matrix[i][j] != c && matrix[i][j] != -1) {
			do {
				i--;
				j++;
			} while (i > 0 && j < BOARD_SIZE && matrix[i][j] != c
					&& matrix[i][j] != -1);

			if (i >= 0 && j < BOARD_SIZE && matrix[i][j] == c) {
				result = true;
				if (flag) {
					for (int k = x - 1, m = y + 1; k > i && m < j; k--, m++) {
						matrix[k][m] = c;
					}
				}
			}
		}

		return result;
	}

	public static boolean checkDoable(int c, int matrix[][]) {

		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
				if (matrix[i][j] == -1) {
					if (checkValid(i, j, c, matrix, false)) {
						return true;
					}
				}
			}
		}

		return false;
	}
	

}
