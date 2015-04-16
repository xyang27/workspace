/*
 * ReversiClient v1.0
 * Yizhou Lin
 * ylin8 at Stevens.edu
 * 
 * Last Update: Sep 23 2012
 * 
 * This is a very simple program that shows how the client connects to the server.
 * 
 * It mainly has two parts/threads.
 * sendThread is a thread that read everything from standard input and then write send them to the socket; and
 * receiveThread is a thread that read everything from the socket and then write them to the standard output.
 */

import java.io.*;
import java.net.Socket;
public class ReversiClient
{
	private static final String SNDNAME_COMMAND = "Name";
	
	public String IP;
    public int port;
    public String name;
    PrintWriter out;
    Gameboard game;
    
    public ReversiClient( String IpAddress, String port){
    	this.IP= IpAddress;
    	this.port= Integer.parseInt(port);
  	
    }

    
	public Gameboard getGame() {
		return game;
	}


	public void setGame(Gameboard game) {
		this.game = game;
	}


	public static void main(String[] args)
	{
		if(args.length < 2)
		{
			System.out.println("usage: ReversiClient address port");
			return;
		}
		
		ReversiClient client= new ReversiClient(args[0], args[1]);
		client.init("Test");
	}


	public void init(String name) {
		Socket s;
		this.name= name;
		try
		{
			s = new Socket(IP,port);
		
			//socket input
			BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
	    	
	    	//establish read thread
	    	Thread receive = new Thread(new receiveThread(in, game));
	    	
	    	//start receiver thread
	    	receive.start();
	    	
			//socket output
		   	out = new PrintWriter(s.getOutputStream());
		   	sendName();

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public void sendName(){
		String str="N"+this.name+"\n";
		out.println(str);   
		out.flush();   
		System.out.println("Reading Client-> sending name command: "+ str); 				
		
	}

	public void sendMove(int x, int y){
		String str="M"+Integer.toString(x)+Integer.toString(y)+"\n";
		out.println(str);   
		out.flush();   
		System.out.println("Reading Client-> sending move command: "+ str); 				
	}
	
	
	
	public void setName(String name) {
		this.name= name;
	}
}


class receiveThread implements Runnable
{
	public BufferedReader in;
	Gameboard game;
	public receiveThread(BufferedReader br, Gameboard game)
	{
		in=br;
		this.game= game;
	}
	
	public void run()
	{
		try
		{
			while(true)
			{
				System.out.println("Reading Client-> reading server command: "); 				
				String command= in.readLine();
				System.out.println("Reading Client-> read server command: "+ command); 
				if ( command.startsWith("U")){
					if ( command.equals("U0"))
						game.setPlayer( 0 );
					else
						game.setPlayer( 1);
					
				} else if ( command.startsWith("B")){
					int x=1;
					int [][] board= new int[8][8];
					for ( int i=0; i< 8; i++)
						for (int j=0; j<8; j++){
							board[i][j]=Integer.parseInt(command.substring(x,x+1));
							x++;
						}
					game.setBoard( board);	

					game.AIMove();
					
				} else if ( command.startsWith("W")){
					if ( command.equals("W0"))
						game.setWinner( 0 );
					else
						game.setWinner( 1);
				} else if ( command.startsWith("G")){
						game.gameOver() ;
				}
				
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}