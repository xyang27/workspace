import java.util.ArrayList;
import java.util.HashMap;

public class Ai {
	final static int BOARD_SIZE = 8;
	static int[][] simplematrix = new int[BOARD_SIZE][BOARD_SIZE];
    
	public static Integer[] tree(int c, int matrix[][]) {
		
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


		HashMap<Integer, Integer[]> newmap = numberDoable(c, matrix);

		int score = -100;
		int score1 = 0;
		Integer[] point = new Integer[2];
		for (int key : newmap.keySet()) {
			
			Integer[] value = newmap.get(key);
			System.out.println(value[0]+","+value[1]+" "+simplematrix[value[0]][value[1]]);
			
			int[][] matrix1 = new int[BOARD_SIZE][BOARD_SIZE];
			for (int i = 0; i < BOARD_SIZE; i++) {
				for (int j = 0; j < BOARD_SIZE; j++) {
					matrix1[i][j] = matrix[i][j];
					//System.out.print(matrix1[i][j]);

				}
				//System.out.println();
			}
			
			Algorithm.checkValid(value[0], value[1], c, matrix1, true);
			matrix1[value[0]][value[1]]=c;

			
			HashMap<Integer, Integer[]> newmap1 = numberDoable((c+1)%2, matrix1);
			for (int key1 : newmap1.keySet()) 
			{
				Integer[] value1 = newmap1.get(key1);
				System.out.println("*"+value1[0]+","+value1[1]+" "+simplematrix[value1[0]][value1[1]]);
				if(score1<simplematrix[value1[0]][value1[1]])
				{
					score1 = simplematrix[value1[0]][value1[1]];
					
				}
			}
			
			
			
			
			if(score<=(simplematrix[value[0]][value[1]]-score1))
			{
				score = (simplematrix[value[0]][value[1]]-score1);
				point[0]=value[0]; 
				point[1]=value[1];
			}

		}
			System.out.println("Score:"+score);
			System.out.println("-------------------"); 
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



