import java.util.ArrayList;
import java.util.HashMap;

public class AiPro {
	final static int BOARD_SIZE = 8;
	static int[][] simplematrix = new int[BOARD_SIZE][BOARD_SIZE];
    
	public static Integer[] tree(int c, int matrix[][]) {
		
		//ArrayList<Integer> resultlist = new ArrayList<Integer>();
		
		simplematrix[0][0] = simplematrix[0][7] = simplematrix[7][0] = simplematrix[7][7] = 100;
		simplematrix[1][1] = simplematrix[1][6] = simplematrix[6][1] = simplematrix[6][6] = 10;
		simplematrix[0][1] = simplematrix[1][0] = simplematrix[0][6] = simplematrix[1][7] = 20;
		simplematrix[6][0] = simplematrix[7][1] = simplematrix[6][7] = simplematrix[7][6] = 20;
		simplematrix[0][2]=simplematrix[0][5]=simplematrix[7][2]=simplematrix[7][5]=90;
		simplematrix[2][0]=simplematrix[5][0]=simplematrix[2][7]=simplematrix[5][7]=90;
		simplematrix[3][0]=simplematrix[4][0]=simplematrix[3][7]=simplematrix[4][7]=60;
		simplematrix[0][3]=simplematrix[0][4]=simplematrix[7][3]=simplematrix[7][4]=60;
		simplematrix[1][2]=simplematrix[1][5]=simplematrix[6][2]=simplematrix[6][5]=30;
		simplematrix[2][1]=simplematrix[5][1]=simplematrix[2][6]=simplematrix[5][6]=30;
		simplematrix[3][1]=simplematrix[4][1]=simplematrix[3][6]=simplematrix[4][6]=40;
		simplematrix[1][3]=simplematrix[1][4]=simplematrix[6][3]=simplematrix[6][4]=40;
		simplematrix[2][2]=simplematrix[2][5]=simplematrix[5][2]=simplematrix[5][5]=50;
		simplematrix[5][3]=simplematrix[4][2]=simplematrix[5][4]=simplematrix[4][5]=50;
		simplematrix[3][2]=simplematrix[2][3]=simplematrix[2][4]=simplematrix[3][5]=50;
		//simplematrix[5][2]=simplematrix[5][3]=simplematrix[5][4]=simplematrix[5][5]=30;
	
		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
				System.out.print(simplematrix[i][j]+" ");
			}
			System.out.println();
			}
	
		

		HashMap<Integer, Integer[]> newmap = numberDoable(c, matrix);

		int score = 0;
		Integer[] point = new Integer[2];
		for (int key : newmap.keySet()) {
			
			Integer[] value = newmap.get(key);
			System.out.println(simplematrix[value[0]][value[1]]);
			if(score<simplematrix[value[0]][value[1]])
			{
				score = simplematrix[value[0]][value[1]];
				point[0]=value[0]; 
				point[1]=value[1];
			}

		}
			System.out.println("Score:"+score);
			 
			return point;

	}
	

	
	public static HashMap<Integer, Integer[]> numberDoable(int c,
			int matrix[][]) {
		int d = 0;
		// System.out.println(c);
		HashMap<Integer, Integer[]> map = new HashMap<Integer, Integer[]>();
		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
				if (matrix[i][j] == -1) {
					if (Algorithm.checkValid(i, j, c, matrix, false) == true) {
						d++;
						Integer[] x = new Integer[] { i, j };
						map.put(d, x);
						// System.out.println("[" + i + "," + j + "] ");
					}
				}
			}

		}
		// System.out.println(d + "---------------");
		return map;

	}

}



