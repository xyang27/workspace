Reversi Protocol
Cheng Chen, Yizhou Lin

Sep 16 2012


This file is about the protocol of Reversi in the final project of CS541.
Rules of the game Reversi can be found in http://en.wikipedia.org/wiki/Reversi


The connection is based on TCP/IP. You can find the examples about how to establish the connection and how to send/receive data to/from the server in the client demos.



After the TCP/IP connection was established, the Client should send its name in the following format,

Nteamname

where teamname is a string no longer than 10 characters, and it should contain only alphabets, numbers and underscores(_).


When the server accepts 2 connections and their names, the game will start.
The server will randomly decide who plays first. The player who plays first is Black/Dark. The player who doesn't play first is White/Light.
The server will send the following message to the Black player:

U1

and it will send the following message to the White player:

U0


In each turn, the server will send the board situation to the player who is going to place a piece in the following format,

Bn0n1n2n3n4...n63

which is an alphabet "B" followed by 64 numbers. The ith number ni indicates the square(xi,yi) in the board, where xi = int(i/8) and yi = i mod 8 (for example if i = 20, then xi = int(i/8) = 2 and yi = 20 mod 8 = 4). The left-lower corner is (0,0), the left-upper corner is (0,7), the right-lower corner is (7,0) and the right-upper corner is (7,7). Each number ni will be either 0, 1 or 2, which means the square(xi,yi) is either empty(0), occupied by White(1) or occupied by Black(2).


The client should place a piece in T seconds after the server sent the board situation, where T is the time limit for each move. The message of placing a piece at (x,y) is in the following format,

Mxy

If the client sends an illegal move, then the server will send the board situation to that client again; otherwise the server will update the board, and send the board situation to the next player.


When neither of the players can place legal moves, the game will be over. The server will decide the winner. The following message will be sent to the winner client,

W1

and the following message will be sent to the loser client,

W0

If it is a draw/tie then the server send the following message to both the clients,

W2


Finally the server will send a single character,

G

which indicates the game is over. Then the server will close all the connections, and wait for new connections to start a new game.


If you have any questions, please contact ylin8 at stevens.edu.