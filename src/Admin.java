import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Admin
{
    Scanner cin = new Scanner(System.in);


    public void adminMenus ( String username,String password,int return_mod, RandomAccessFile userFile, RandomAccessFile flightFile, RandomAccessFile ticketFile) throws IOException, InterruptedException {



        int inputAdmin = cin.nextInt();
        switch (inputAdmin)

        {
            case 1 :
                addedFlight(username,password,return_mod,  userFile,  flightFile,  ticketFile);
                break;
            case 2 :
                updateFlight(username,password,return_mod,  userFile,  flightFile,  ticketFile);
                break;
            case 3 :
                removeFlight( username,password,return_mod,  userFile,  flightFile,  ticketFile);
                break;
            case 4 :
               flightSchedules (username,password,return_mod , userFile,  flightFile, ticketFile);
                break;
            case 0 :
                signout( username,password,return_mod, userFile,  flightFile,  ticketFile);
                break;
            default:
                adminMenus( username,password,return_mod , userFile, flightFile,  ticketFile);
                break;
        }

    }
    public  void addedFlight(String username,String passwor,int return_mod, RandomAccessFile userFile, RandomAccessFile flightFile, RandomAccessFile ticketFile) throws IOException, InterruptedException {
        Userfile userfile = new Userfile() ;
        Print admin = new Print();
        System.out.println("Enter flight id");
        String inputId = cin.next();
        if ( userfile.checkFlightId( inputId ,  flightFile ) == -1 )
        {
            admin.writeString(Print.flightCounter * admin.FLIGHTLENGHT , inputId ,flightFile);

            System.out.println("Enter flight origin");
            String inputOrigin = cin.next();
            admin.writeString(Print.flightCounter* admin.FLIGHTLENGHT + admin.FIXSTRING , inputOrigin ,flightFile);

            System.out.println("Enter flight destination");
            String inputDestination = cin.next();
            admin.writeString(Print.flightCounter* admin.FLIGHTLENGHT + 2*admin.FIXSTRING , inputDestination ,flightFile);

            System.out.println("Enter flight date");
            String inputDate = cin.next();
            admin.writeString(Print.flightCounter* admin.FLIGHTLENGHT + 3*admin.FIXSTRING , inputDate ,flightFile);

            System.out.println("Enter flight Time");
            String inputTime = cin.next();
            admin.writeString(Print.flightCounter* admin.FLIGHTLENGHT + 4*admin.FIXSTRING , inputTime ,flightFile);

            System.out.println("Enter flight price");
            int inputPrice = cin.nextInt();
            flightFile.writeInt(inputPrice);

            System.out.println("Enter flight seat");
            int inputSeat = cin.nextInt();
            flightFile.writeInt(inputSeat);

            admin.adminMenu(username,passwor,return_mod , userFile, flightFile,  ticketFile);


        }
        else
        {
            System.out.println("Flight Id Exist");
            admin.adminMenu(username,passwor,return_mod , userFile, flightFile,  ticketFile);
        }
    }
    public void updateFlight(String username,String passwor,int return_mod, RandomAccessFile userFile, RandomAccessFile flightFile, RandomAccessFile ticketFile) throws IOException, InterruptedException {
        Userfile userfile = new Userfile() ;
        Print admin = new Print();
        System.out.println("Enter flight id");
        String inputId = cin.next();
        if (userfile.checkFlightId(inputId , flightFile) == 1)
        {
            System.out.println("Enter new flight id");
            String inputNewId = cin.next();
            admin.writeString(userfile.flightIndex * Print.flightCounter , inputNewId , flightFile);
            System.out.println("Enter new flight origin");
            String inputNewOrigin = cin.next();
            admin.writeString(userfile.flightIndex * Print.flightCounter + admin.FIXSTRING , inputNewOrigin , flightFile);
            System.out.println("Enter new destination");
            String inputNewDestination = cin.next();
            admin.writeString(userfile.flightIndex * Print.flightCounter + 2 * admin.FIXSTRING , inputNewDestination , flightFile);
            System.out.println("Enter new date");
            String inputNewDate = cin.next();
            admin.writeString(userfile.flightIndex * Print.flightCounter + 3 * admin.FIXSTRING , inputNewDate , flightFile);
            System.out.println("Enter new time");
            String inputNewTime = cin.next();
            admin.writeString(userfile.flightIndex * Print.flightCounter + 4 * admin.FIXSTRING , inputNewTime , flightFile);
            System.out.println("Enter new price");
            int inputNewPrice = cin.nextInt();
            flightFile.writeInt(inputNewPrice);
            System.out.println("Enter new seat ");
            int inputNewSeat = cin.nextInt();
            flightFile.writeInt(inputNewSeat);

            admin.adminMenu(username,passwor,return_mod ,userFile ,flightFile , ticketFile);

        }
        else
        {
            System.out.println("Flight id not exist");
            admin.adminMenu(username,passwor,return_mod ,userFile ,flightFile , ticketFile);
        }


    }
    public void removeFlight(String username,String passwor,int return_mod, RandomAccessFile userFile, RandomAccessFile flightFile, RandomAccessFile ticketFile) throws IOException, InterruptedException {
        Userfile userfile = new Userfile() ;
        Print admin = new Print();
        System.out.println("Enter Flight Id");
        String inputId = cin.next();
       if (userfile.checkFlightId( inputId , flightFile ) == 1 )
       {
           admin.writeString(Print.flightCounter * admin.FLIGHTLENGHT , admin.REMOVE , flightFile);
           admin.writeString(Print.flightCounter * admin.FLIGHTLENGHT +admin.FIXSTRING , admin.REMOVE , flightFile);
           admin.writeString(Print.flightCounter * admin.FLIGHTLENGHT + 2*admin.FIXSTRING , admin.REMOVE , flightFile);
           admin.writeString(Print.flightCounter * admin.FLIGHTLENGHT +3*admin.FIXSTRING , admin.REMOVE , flightFile);
           admin.writeString(Print.flightCounter * admin.FLIGHTLENGHT +4*admin.FIXSTRING , admin.REMOVE , flightFile);
           flightFile.writeInt(0);
           flightFile.writeInt(0);
           admin.adminMenu( username,passwor, return_mod ,userFile,  flightFile,  ticketFile);
       }
       else
       {
           System.out.println("Flight Id Not Exist");
           admin.adminMenu(username,passwor, return_mod ,userFile ,flightFile , ticketFile);
       }


    }


        protected void flightSchedules(String username,String password,int return_mod, RandomAccessFile userFile, RandomAccessFile flightFile, RandomAccessFile ticketFile) throws IOException, InterruptedException {

            Print admin = new Print();

          //  System.out.println("\n\n\n\n flight counter ==> "+ Print.flightCounter +"\nflight lenght ==> "+flightFile.length()+"\n\n\n\n\n\n\n");

            System.out.print("\n\t\t\t\t\t\t\t\t\t      << Flights List >>\n");


            System.out.print("\t\t\t\t\t");
            System.out.print("+---------------------------------------------------------------------------------------------+");
            try{Thread.sleep(80);}catch(InterruptedException e) {};


            System.out.print("\n\t\t\t\t\t");
            System.out.printf("| %-1s| %-1s| %-1s| %-1s| %-1s| %-1s| %-1s|","  Flight ID  ","   Origins   ","  Destention  ","    Data    ","   Time   ","   Price   "," Seats ");
            System.out.println();
            try{Thread.sleep(80);}catch(InterruptedException e) {};

            for (int i = 0; i < Print.flightCounter; i++)
            {
                String str = admin.readString (i*admin.FLIGHTLENGHT, flightFile);

                if(str.contains(admin.REMOVE))
                {}
                else {
                    System.out.print("\t\t\t\t\t");
                    System.out.print("+---------------------------------------------------------------------------------------------+");
                    try {Thread.sleep(80);} catch (InterruptedException e) {};

                    int seek = i*admin.FLIGHTLENGHT;
                    flightFile.seek(seek + 200);
                    int price = flightFile.readInt();

                    flightFile.seek(seek + 204);
                    int seat = flightFile.readInt();


                    System.out.print("\n\t\t\t\t\t");

                    System.out.printf("|    %-10s|    %-10s|    %-11s| %-12s|   %-8s|  %,-10d|  % -4d  "
                            , admin.readString(seek,flightFile), admin.readString(seek+40, flightFile), admin.readString(seek+80,flightFile), admin.readString(seek+120,flightFile), admin.readString(seek+160,flightFile), price,seat);
                    System.out.println("|");
                    try {Thread.sleep(80);} catch (InterruptedException e) {};
                }
            }

            System.out.print("\t\t\t\t\t");
            System.out.println("+---------------------------------------------------------------------------------------------+");

            admin.adminMenu(username,password,return_mod,userFile,flightFile,ticketFile);




        }
    public void signout(String username,String passwor ,int return_mod , RandomAccessFile userFile, RandomAccessFile flightFile, RandomAccessFile ticketFile) throws IOException, InterruptedException {
        Print admin = new Print();
        admin.adminMenu( username,passwor,return_mod , userFile,  flightFile, ticketFile);
    }



}
