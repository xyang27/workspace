ReversiServer v1.0
Yizhou Lin
ylin8 at Stevens.edu

Last Update: Sep 23 2012


This program will be used as a server. Please run it before runing any clients.

It will print out everything it receives and sends, which will make you easier to debug your own client. It will also print the board situation in each turn.
Please notice that you does not have time limit for each move now, but you will have when you play against another client in the final project.


0. Install Java

You should install JRE/JDK before running this program.
JRE or JDK can be downloaded from http://www.java.com.


1. Run

To run this program,
run "java ReversiServer PORT",
where PORT is the port number.



2. Example

(type) java ReversiServer 4000
ReversiServer: Starting
ReversiServer: Waiting for connections
(wait)
ReversiServer: accpted a connection from /127.0.0.1
(wait)
ReversiServer: Receive <- 127.0.0.1 : Nteamname
(wait)
ReversiServer: accpted a connection from /127.0.0.1
(wait)
ReversiServer: Receive <- 127.0.0.1 : NClient2
ReversiServer: Send -> teamname : U1
ReversiServer: Send -> Client2 : U0
ReversiServer: BLACK : teamname
ReversiServer: WHITE : Client2
ReversiServer: Send -> teamname : B0000000000000000000000000002100000012000000000000000000000000000
(wait)
ReversiServer: Receive <- teamname : M42
........
........
........
...WB...
...BB...
....B...
........
........
ReversiServer: Send -> Client2 : B0000000000000000000000000002100000222000000000000000000000000000
(wait)
ReversiServer: Receive <- Client2 : M22
........
........
........
...WB...
...BB...
....B...
........
........
ReversiServer: Send -> Client2 : B0000000000000000000000000002100000222000000000000000000000000000

...
...
...


ReversiServer: Send -> teamname : W1
ReversiServer: Send -> teamname : G
ReversiServer: Send -> Client2 : W0
ReversiServer: Send -> Client2 : G
ReversiServer: Winner: BLACK (teamname)
ReversiServer: Restarting
ReversiServer: Waiting for connections
(wait)


3. Other
This program was tested under WINDOWS 7 64-Bit, Java 1.7.0_07 64-Bit.
If you have any questions please contact ylin8 at stevens.edu.