import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Gameboard extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ChessBoard chessBoard;
	private DisplayBoard displayBoard;
	private ImageIcon background;
	private JPanel imagePanel;
	
	private int roundCount;
	private String name;
	private int player;
	ReversiClient client;

	public boolean myTurn= false;

	public Gameboard(String name, ReversiClient client) {
		this.name= name;
		this.client= client;
		this.setTitle("Reversi Team "+ name );

		chessBoard = new ChessBoard( client, this);
		displayBoard = new DisplayBoard();
//		background = new ImageIcon(FN_BG);
		background = new ImageIcon(Gameboard.class.getResource("resource\\background.jpg"));
		
		JLabel picLabel = new JLabel(background);
		picLabel.setBounds(0, 0, background.getIconWidth(), background.getIconHeight());

		imagePanel = (JPanel) this.getContentPane();
		imagePanel.add(picLabel);
		imagePanel.setOpaque(false);
		imagePanel.setLayout(null);

		displayBoard.setBounds(670, 50, 300, 600);

		this.getLayeredPane().setLayout(null);
		this.getLayeredPane().add(chessBoard);
		this.getLayeredPane().add(displayBoard);

		this.setBounds(
				(Toolkit.getDefaultToolkit().getScreenSize().width - background
						.getIconWidth()) / 2,
				(Toolkit.getDefaultToolkit().getScreenSize().height - background
						.getIconHeight()) / 2, background.getIconWidth(),
				background.getIconHeight());
		this.setResizable(false);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public static void main(String[] args) {

		if(args.length < 3)
		{
			System.out.println("usage: Gameboard teamName address port");
			//example:
			//java ReversiClient 127.0.0.1 4000
			return;
		}
				
		ReversiClient client= new ReversiClient(args[1], args[2]);
		Gameboard gb = new Gameboard(args[0], client);
		client.setName( args[0]);
		client.setGame( gb);	
		gb.initGame(client);	
		client.init(args[0]);
		
	}

	public void initGame(ReversiClient client ) {

		roundCount = 1;
		this.client= client;

/*		chessBoard.setChess(3, 3, 1);
		chessBoard.setChess(3, 4, 0);
		chessBoard.setChess(4, 3, 0);
		chessBoard.setChess(4, 4, 1);
*/		
/*		displayBoard.setNumber(0, 2);
		displayBoard.setNumber(1, 2);
*/		
	}
	
	public void AIMove(){
		
		Integer[] point = Ai.tree(player, chessBoard.chessMatrix);

		client.sendMove(point[0], point[1]);
		Algorithm.checkValid(point[0], point[1], player, chessBoard.chessMatrix, true);
		chessBoard.setChess(point[0], point[1], player);
		chessBoard.repaintChessboard();
		chessBoard.countChess();
		displayNameCount(chessBoard.whiteCount, chessBoard.blackCount );
	}
	
	private class ChessBoardCell extends JLabel {
		int x;
		int y;
		
		public ChessBoardCell( int x, int y){
			super();
			this.x=x;
			this.y=y;
		}
	}

	private class ChessBoard extends JPanel implements MouseListener {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		final static int BOARD_SIZE = 8;

		private JLabel[][] chessMatrixLabel = new JLabel[BOARD_SIZE][BOARD_SIZE];
		public int chessMatrix[][] = new int[BOARD_SIZE][BOARD_SIZE];
		
		public int blackCount;
		public int whiteCount;
		ReversiClient client;
		Gameboard game;
		
		public ChessBoard(ReversiClient client, Gameboard game) {
			blackCount = 0;
			whiteCount = 0;
			this.client= client;
			this.game= game;
			
			this.setLayout(null);

			for (int i = 0; i < BOARD_SIZE; i++) {
				for (int j = 0; j < BOARD_SIZE; j++) {
					chessMatrix[i][j] = -1;
					chessMatrixLabel[i][j] = new ChessBoardCell( i,j);
					chessMatrixLabel[i][j].setBounds(i * 78 + 15, j * 78 + 15, 75, 75);
					chessMatrixLabel[i][j].addMouseListener(this);
					this.add(chessMatrixLabel[i][j]);
				}
			}

			this.setVisible(true);
			this.setSize(650, 650);
		}

		private void countChess(){
			blackCount = 0;
			whiteCount = 0;
			for (int i = 0; i < BOARD_SIZE; i++) {
				for (int j = 0; j < BOARD_SIZE; j++) {
					if( chessMatrix[i][j] == 1){
						blackCount++;
					}else if(chessMatrix[i][j] == 0){
						whiteCount++;
					}
				}
			}
		}

		
		public void setChess(int x, int y, int c) {
			if (c == 1) {
				chessMatrixLabel[x][y].setIcon(new ImageIcon(Gameboard.class.getResource("resource//black.PNG")));
			} else if (c == 0) {
				chessMatrixLabel[x][y].setIcon(new ImageIcon(Gameboard.class.getResource("resource//white.PNG")));
			}

			chessMatrix[x][y] = c;
		}

		private void repaintChessboard() {
			for (int i = 0; i < BOARD_SIZE; i++) {
				for (int j = 0; j < BOARD_SIZE; j++) {
					if (chessMatrix[i][j] == 1) {
						chessMatrixLabel[i][j].setIcon(new ImageIcon(Gameboard.class.getResource("resource//black.PNG")));
					} else if (chessMatrix[i][j] == 0) {
						chessMatrixLabel[i][j].setIcon(new ImageIcon(Gameboard.class.getResource("resource//white.PNG")));
					}
				}
			}
		}
		
		private void endGame(){
			String end;
			if(blackCount + whiteCount == 64){
				if(blackCount > whiteCount){
					end = "Black Wins!";
				}else if(whiteCount > blackCount){
					end = "White Wins!";
				}else{
					end = "Drawn Game!";
				}
				
				System.out.println("==========" + end + "==========");
			}
			
		}

		@Override
		public void paintComponent(Graphics g) {
			ImageIcon icon = new ImageIcon(Gameboard.class.getResource("resource//chessbord.PNG"));
			g.drawImage(icon.getImage(), 0, 0, getWidth(), getHeight(), this);
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			if ( ! this.game.myTurn){
				System.out.println("Sorry Not your turn !");
				return;
			}
			ChessBoardCell cell= (ChessBoardCell) e.getSource();
			if( ! Algorithm.checkValid(cell.x, cell.y, this.game.player, chessMatrix, true))
				return;
			this.game.myTurn= false;
			client.sendMove(cell.x, cell.y);
			setChess(cell.x, cell.y, this.game.player);
			repaintChessboard();
			roundCount++;
			countChess();
			displayNameCount(whiteCount, blackCount );
			
			/*
			  for (int i = 0; i < BOARD_SIZE; i++) {
			 
				for (int j = 0; j < BOARD_SIZE; j++) {
					if (chessMatrixLabel[i][j].equals((JLabel) e.getSource()) && chessMatrix[i][j] == -1) {
						//if (Algorithm.checkValid(i, j, roundCount % 2, chessMatrix, true)) {
							setChess(i, j, roundCount % 2);
							client.sendMove(i, j);
							repaintChessboard();
							roundCount++;
							countChess();
							displayBoard.setNumber(0, whiteCount,"");
							displayBoard.setNumber(1, blackCount,"");
							//endGame();
						//}
						
						if( !Algorithm.checkDoable(roundCount % 2, chessMatrix)){
							System.out.println("++++++++++" + "No Move!" + "++++++++++");
							roundCount++;
						}
					}
				}	
			}  */

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}

	private class DisplayBoard extends JPanel {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		final static String FN_HB = "resource\\HumanBlack.png";
		final static String FN_HW = "resource\\HumanWhite.png";
		final static String FN_CB = "resource\\ComputerBlack.png";
		final static String FN_CW = "resource\\ComputerWhite.png";	
		

		private PlayerLable jlPlayer1;
		private PlayerLable jlPlayer2;

		public DisplayBoard() {

			
			jlPlayer1 = new PlayerLable(FN_HB, 1);
			jlPlayer2 = new PlayerLable(FN_HW, 2);

			this.add(jlPlayer1);
			this.add(jlPlayer2);

			jlPlayer1.setBounds(0, 0, 300, 100);
			jlPlayer2.setBounds(0, 450, 300, 100);

			this.setOpaque(false);
			this.setLayout(null);
			this.setSize(300, 650);
			this.setVisible(true);
		}
		/* Amr
		public void setNumber(int c, int number){
			if(c == 1){
				jlPlayer1.setNumberLabel(number);
			}else{
				jlPlayer2.setNumberLabel(number);
			}
		}*/
		
		public void setNumber(int c, int number, String name){
			if(c == 1){
				jlPlayer1.setNumberLabel(number, name);
			}else{
				jlPlayer2.setNumberLabel(number, name);
			}
		}

	}

	private class PlayerLable extends JPanel {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		private ImageIcon playerImage;
		private JLabel numberLabel;

		public PlayerLable(String playerFile, int color) {

			playerImage = new ImageIcon(Gameboard.class.getResource(playerFile));
			numberLabel = new JLabel("00");
			

			//numberLabel.setBounds(30, 35, 30, 30);
			numberLabel.setBounds(30, 35, 200, 30);  // Amr
			numberLabel.setFont(new Font("arial", Font.BOLD, 24));


			if (color == 1) {
				numberLabel.setForeground(Color.white);
			} else {
				numberLabel.setForeground(Color.black);
			}

			this.add(numberLabel);
			this.setOpaque(false);
			this.setLayout(null);
			this.setSize(playerImage.getIconWidth(), playerImage.getIconHeight());
			this.setVisible(true);
		}

		public void setNumberLabel(int number){
			numberLabel.setText(String.format("%02d", number));
		}
	
		// Amr
		public void setNumberLabel(int number, String name){
			numberLabel.setText(String.format("%02d", number)+"  "+name);
		}		
		
		@Override
		public void paintComponent(Graphics g) {
		//Amr	g.drawImage(playerImage.getImage(), 0, 0, getWidth(), getHeight(), this);
		}

	}

	private class TimeLable extends JPanel {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

	}

	public void displayNameCount(int whiteCount, int blackCount ){
		if ( this.player ==0) {
			displayBoard.setNumber(0, blackCount, "Competitor");
			displayBoard.setNumber(1, whiteCount, this.name);		
		} else{
			displayBoard.setNumber(0, blackCount, this.name);
			displayBoard.setNumber(1, whiteCount, "Competitor");		
			
		}
	}
	public void setPlayer(int i) {
		this.player=i;
		displayNameCount(0,0);
	}

	public void setBoard(int[][] board) {
		// Each number ni will be either 0, 1 or 2, which means the square(xi,yi) is either empty(0), occupied by White(1) or occupied by Black(2).	
		for ( int i=0; i< 8; i++)
			for ( int j=0; j< 8; j++){
				if ( board[i][j] != 0)
					chessBoard.setChess(i, j, (board[i][j])-1);
			}
		chessBoard.repaintChessboard();
		this.myTurn= true;

	}

	public void setWinner(int i) {
		// TODO Auto-generated method stub
		
	}

	public void gameOver() {
		// TODO Auto-generated method stub

	}

}
