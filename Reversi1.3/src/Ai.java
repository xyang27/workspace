import java.util.HashMap;

public class Ai {
	final static int BOARD_SIZE = 8;
	static int[][] simplematrix = new int[BOARD_SIZE][BOARD_SIZE];
    
	public static Integer[] tree(int c, int matrix[][]) {
		
		simplematrix[0][0] = simplematrix[0][7] = simplematrix[7][0] = simplematrix[7][7] = 100;
		simplematrix[1][1] = simplematrix[1][6] = simplematrix[6][1] = simplematrix[6][6] = 1;
		simplematrix[0][1] = simplematrix[1][0] = simplematrix[0][6] = simplematrix[1][7] = 5;
		simplematrix[6][0] = simplematrix[7][1] = simplematrix[6][7] = simplematrix[7][6] = 5;
		simplematrix[0][2]=simplematrix[0][5]=simplematrix[7][2]=simplematrix[7][5]=10;
		simplematrix[2][0]=simplematrix[5][0]=simplematrix[2][7]=simplematrix[5][7]=10;
		simplematrix[3][0]=simplematrix[4][0]=simplematrix[3][7]=simplematrix[4][7]=10;
		simplematrix[0][3]=simplematrix[0][4]=simplematrix[7][3]=simplematrix[7][4]=10;
		simplematrix[1][2]=simplematrix[1][5]=simplematrix[6][2]=simplematrix[6][5]=2;
		simplematrix[2][1]=simplematrix[5][1]=simplematrix[2][6]=simplematrix[5][6]=2;
		simplematrix[3][1]=simplematrix[4][1]=simplematrix[3][6]=simplematrix[4][6]=2;
		simplematrix[1][3]=simplematrix[1][4]=simplematrix[6][3]=simplematrix[6][4]=2;
		simplematrix[2][2]=simplematrix[2][5]=simplematrix[5][2]=simplematrix[5][5]=2;
		simplematrix[5][3]=simplematrix[4][2]=simplematrix[5][4]=simplematrix[4][5]=2;
		simplematrix[3][2]=simplematrix[2][3]=simplematrix[2][4]=simplematrix[3][5]=2;
		simplematrix[3][3]=simplematrix[3][4]=simplematrix[4][3]=simplematrix[4][4]=2;


		HashMap<Integer, Integer[]> newmap = numberDoable(c, matrix);

		int score =-10000;

		Integer[] value = new Integer[2];
		Integer[] value1 = new Integer[2];
		Integer[] value2 = new Integer[2];
		Integer[] value3 = new Integer[2];
		Integer[] value4 = new Integer[2];
		
		
		Integer[] point = new Integer[2];
/*
 * first layer
 */
		for (int key : newmap.keySet()) {
			
			value = newmap.get(key);
			System.out.println(value[0]+","+value[1]+" "+simplematrix[value[0]][value[1]]);
			
			int[][] matrix1 = new int[BOARD_SIZE][BOARD_SIZE];
			
			for (int i = 0; i < BOARD_SIZE; i++) {
				for (int j = 0; j < BOARD_SIZE; j++) {
					matrix1[i][j] = matrix[i][j];
					

				}
				//System.out.println();
			}
			
			Algorithm.checkValid(value[0], value[1], c, matrix1, true);
			matrix1[value[0]][value[1]]=c;
			
			
			HashMap<Integer, Integer[]> newmap1 = numberDoable((c+1)%2, matrix1);
			int score1 = -10000;
			/*
			 * second layer
			 */			
			for (int key1 : newmap1.keySet()) 
			{
				value1 = newmap1.get(key1);
				
				
				
				int[][] matrix2 = new int[BOARD_SIZE][BOARD_SIZE];
				
				for (int i = 0; i < BOARD_SIZE; i++) {
					for (int j = 0; j < BOARD_SIZE; j++) {
						matrix2[i][j] = matrix1[i][j];
					}
				}
				
				Algorithm.checkValid(value1[0], value1[1], (c+1)%2, matrix2, true);
				matrix2[value1[0]][value1[1]]=(c+1)%2;
								
				HashMap<Integer, Integer[]> newmap2 = numberDoable(c, matrix2);
				int score2 = -10000;
				/*
				 * third layer
				 */
				for (int key2 : newmap2.keySet()) 
				{
					value2 = newmap2.get(key2);
					
					int[][] matrix3 = new int[BOARD_SIZE][BOARD_SIZE];
					
					for (int i = 0; i < BOARD_SIZE; i++) {
						for (int j = 0; j < BOARD_SIZE; j++) {
							matrix3[i][j] = matrix2[i][j];
						}
						
					}
		
					Algorithm.checkValid(value2[0], value2[1], c, matrix3, true);
					matrix3[value2[0]][value2[1]]=c;
					
					HashMap<Integer, Integer[]> newmap3 = numberDoable((c+1)%2, matrix2);
					int score3 = -10000;
					/*
					 * fourth layer
					 */
					for (int key3 : newmap3.keySet()) 
					{
						value3 = newmap3.get(key3);
						
						int[][] matrix4 = new int[BOARD_SIZE][BOARD_SIZE];
						
						for (int i = 0; i < BOARD_SIZE; i++) {
							for (int j = 0; j < BOARD_SIZE; j++) {
								matrix4[i][j] = matrix3[i][j];
							}
							
						}
			
						Algorithm.checkValid(value3[0], value3[1],(c+1)%2, matrix4, true);
						matrix4[value3[0]][value3[1]]=(c+1)%2;
						
						HashMap<Integer, Integer[]> newmap4 = numberDoable(c, matrix2);
						int score4 = -10000;
						/*
						 * fifth layer
						 */
						for (int key4 : newmap4.keySet()) 
						{
							value4 = newmap4.get(key4);
							
							int[][] matrix5 = new int[BOARD_SIZE][BOARD_SIZE];
							
							for (int i = 0; i < BOARD_SIZE; i++) {
								for (int j = 0; j < BOARD_SIZE; j++) {
									matrix5[i][j] = matrix4[i][j];
								}
								
							}
				
							Algorithm.checkValid(value4[0], value4[1],c, matrix5, true);
							matrix4[value4[0]][value4[1]]=c;
							
							if(score4<=totalScore(matrix5,c))
							{
								score4 = totalScore(matrix5,c);
								
							}
							System.out.println("                       **score4:"+totalScore(matrix5,c));
						}
						if(score4==-10000)
						{
							score4=0;
						}
						if(score3<=totalScore(matrix4,(c+1)%2)-score4)
						{
							score3 = totalScore(matrix4,(c+1)%2)-score4;
							
						}
						System.out.println("                       **score3:"+(totalScore(matrix4,(c+1)%2)-score4));
					}
					if(score3==-10000)
					{
						score3=0;
					}
					
					if(score2<=totalScore(matrix3,c)-score3)
					{
						score2 = totalScore(matrix3,c)-score3;
						
					}
					System.out.println("                       **score2:"+ (totalScore(matrix3,c)-score3));
				}
				
				if(score2==-10000)
				{
					score2=0;
				}
				
				
				if(score1<=(totalScore(matrix2,(c+1)%2)-score2))
				{
					score1 = (totalScore(matrix2,(c+1)%2)-score2);
				
				}
				System.out.println("          *totalScore:"+totalScore(matrix2,(c+1)%2)+"......score1:"+(totalScore(matrix2,(c+1)%2)-score2));
				
			}
			
			
			if(score1==-10000)
			{
				score1=0;
			}
			
			if(score<=(totalScore(matrix1,c)-score1))
			{
				score = (totalScore(matrix1,c)-score1);
				point[0]=value[0]; 
				point[1]=value[1];
			}
			System.out.println("   *totalScore:"+totalScore(matrix1,c)+"......score:"+(totalScore(matrix1,c)-score1));
		}

		    
			System.out.println("Score:"+score);

			System.out.println("-----------------------------"); 
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
	

	
	public static int totalScore(int matrix[][],int c)
	{

		int black = 0;
		int white = 0;
		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
				if (matrix[i][j] == 1) {
					black += simplematrix[i][j];
					
				}
				if(matrix[i][j]==0)
				{
					white+=simplematrix[i][j];
				}
				
			}
		}
		if(c==0)
		{return white;}
		else
		{return black;}
		
		
	}
	

}



