package chatrooms;

import java.util.ArrayList;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class MainServer{
  protected ArrayList < User > userList;
  protected ArrayList < ChatRoom > chatRooms;
  protected ArrayList < Integer > portNumbersUsed;
  public ServerSocket serverSocket;
  private int threadNr;
  public class MainServer (){
    this.userList = new ArrayList < User > ();
    this.chatRooms = new ArrayList < Chatroom > ();
    try{
      this.serverSocket = new ServerSocket (1234);
    } catch (IOException e){
      e.printStackTrace ();
    }
    threadNr = 1;

  }
  public void createUser (){
    try{
      Socket userSocket = serverSocket.accept ();
      System.out.println ("Spawning thread: " + threadNr);  
      Thread user = new Thread (new User (userSocket, threadNr));
      threadNr++;
      user.start ();
    } catch (IOException e){
      e.printStackTrace ();
    }
  }
  private int generatePortNumber() {
    Random rand = new Random();
    int resRandom = rand.nextInt((9999 - 100) + 1) + 10;
    return resRandom;
  }
  protected Chatroom createChatRoom (String roomName, int portNumber){
    this.portNumber = generatePortNumber();    
    Chatroom room = new Chatroom (roomName, this.portNumber);
    this.chatRooms.add (room);
    this.portNumbersUsed.add (portNumber);
    return room;
  }
  public static void main (String args[]){
    MainServer server = new MainServer ();
    server.start();
  }
}
