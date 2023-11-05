// ConsoleTerminal.java: read keyboard and transmit to COM port - read any response
//
// note requires jSerialComm jar file from https://fazecast.github.io/jSerialComm/

// compile and run commands - note that jSerialComm file should be in same directory as java source file
// javac -cp .\jSerialComm-2.9.3.jar;.  ConsoleTerminal.java
// java -cp .\jSerialComm-2.9.3.jar;.  ConsoleTerminal
package gui;
import com.fazecast.jSerialComm.*;
public class ConsoleTerminal {
    private static SerialPort comPorts[];
    private static int port=-1;
    public  static SerialPort comPort;
    public  static StringBuilder text=new StringBuilder();  // will hold a line of text to be parse for an float

    // attempt to open COM port 
    static void openCOMport() {
        comPort = SerialPort.getCommPorts()[port];
        System.out.println("attempting to open " +  comPorts[port].getDescriptivePortName() + "\n");
        if(!comPort.openPort()) 
           { System.out.println("failed to open COM port " + comPorts[port].getDescriptivePortName() + "\n"); port=-1; return; }
        System.out.println("Opened COM port " + comPorts[port].getDescriptivePortName() + " OK\n");
        comPort.setBaudRate(9600);
        }        

    public static void main(String[] args) { 
        System.out.println("List COM ports"); // read list of COM ports and display them
        comPorts = SerialPort.getCommPorts();
        if(comPorts.length<1)           // if no COM ports found exit
            {System.out.println("no COM ports found"); return;}
        // print list of COM ports, add to COMportMenu and attach event handler
        for (int i = 0; i < comPorts.length; i++)   { 
           System.out.println("comPort[" + i + "] = " + comPorts[i].getDescriptivePortName());
                }
       if(comPorts.length==1)            // if 1 COM port found open it
            {port=0; openCOMport();}
       // loop read characters from keyboard transmit over serial
       //   read characters from serial and display them
       int flag=0;
       try {
         while (flag==0)
         {
           // if keyboard token entered read it
           // read serial port  and display data
           if(comPort.bytesAvailable() > 0) 
           {
             byte[] data = new byte[10];
             comPort.readBytes(data,1);
             if((char)data[0] >= ' ') 
               text.append((char)data[0]);  // if printable append to text
             else 
               if((char)data[0]== '\n'){
                  System.out.println("UID:" + text.toString());
                  flag=1;
                  comPorts[port].closePort();
             }
          }
         }
       } catch (Exception e) { e.printStackTrace(); }
       comPorts[port].closePort();         
   }
}