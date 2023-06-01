import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Admin
{
    Scanner cin = new Scanner(System.in);
    Print admin = new Print();
    Userfile userfile = new Userfile() ;
    public void adminMenus ( int return_mod, RandomAccessFile userFile, RandomAccessFile flightFile, RandomAccessFile ticketFile) throws IOException, InterruptedException {



        int inputAdmin = cin.nextInt();
        switch (inputAdmin)

        {
            case 1 :
                addedFlight(return_mod,  userFile,  flightFile,  ticketFile);
                break;
            case 2 :
                updateFlight(return_mod,  userFile,  flightFile,  ticketFile);
                break;
            case 3 :
                removeFlight( return_mod,  userFile,  flightFile,  ticketFile);
                break;
            case 4 :
                flightSchedule(return_mod , userFile,  flightFile, ticketFile);
                break;
            case 0 :
                signout( return_mod, userFile,  flightFile,  ticketFile);
                break;
            default:
                adminMenus( return_mod , userFile, flightFile,  ticketFile);
                break;
        }

    }
    public  void addedFlight(int return_mod, RandomAccessFile userFile, RandomAccessFile flightFile, RandomAccessFile ticketFile) throws IOException, InterruptedException {

        System.out.println("Enter flight id");
        String inputId = cin.next();
        if ( userfile.checkFlightId( inputId ,  flightFile ) == -1 )
        {
            admin.writeString((Print.flightCounter+1)* admin.FLIGHTLENGHT , inputId ,flightFile);
            System.out.println("Enter flight origin");
            String inputOrigin = cin.next();
            admin.writeString((Print.flightCounter+1)* admin.FLIGHTLENGHT + admin.FIXSTRING , inputOrigin ,flightFile);
            System.out.println("Enter flight destination");
            String inputDestination = cin.next();
            admin.writeString((Print.flightCounter+1)* admin.FLIGHTLENGHT + 2*admin.FIXSTRING , inputDestination ,flightFile);
            System.out.println("Enter flight date");
            String inputDate = cin.next();
            admin.writeString((Print.flightCounter+1)* admin.FLIGHTLENGHT + 3*admin.FIXSTRING , inputDate ,flightFile);
            System.out.println("Enter flight Time");
            String inputTime = cin.next();
            admin.writeString((Print.flightCounter+1)* admin.FLIGHTLENGHT + 4*admin.FIXSTRING , inputTime ,flightFile);
            System.out.println("Enter flight price");
            long inputPrice = cin.nextLong();
            flightFile.writeLong(inputPrice);
            System.out.println("Enter flight seat");
            int inputSeat = cin.nextInt();
            flightFile.writeInt(inputSeat);
            admin.adminMenu(return_mod , userFile, flightFile,  ticketFile);


        }
        else
        {
            System.out.println("Flight Id Exist");
            admin.adminMenu(return_mod , userFile, flightFile,  ticketFile);
        }
    }
    public void updateFlight(int return_mod, RandomAccessFile userFile, RandomAccessFile flightFile, RandomAccessFile ticketFile) throws IOException, InterruptedException {

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
            long inputNewPrice = cin.nextLong();
            flightFile.writeLong(inputNewPrice);
            System.out.println("Enter new seat ");
            int inputNewSeat = cin.nextInt();
            flightFile.writeInt(inputNewSeat);

            admin.adminMenu(return_mod ,userFile ,flightFile , ticketFile);

        }
        else
        {
            System.out.println("Flight id not exist");
            admin.adminMenu(return_mod ,userFile ,flightFile , ticketFile);
        }


    }
    public void removeFlight(int return_mod, RandomAccessFile userFile, RandomAccessFile flightFile, RandomAccessFile ticketFile) throws IOException, InterruptedException {
        System.out.println("Enter Flight Id");
        String inputId = cin.next();
       if (userfile.checkFlightId( inputId , flightFile ) == 1 )
       {
           admin.writeString(userfile.flightIndex * admin.FLIGHTLENGHT , admin.REMOVE , flightFile);
           admin.writeString(userfile.flightIndex * admin.FLIGHTLENGHT +admin.FIXSTRING , admin.REMOVE , flightFile);
           admin.writeString(userfile.flightIndex * admin.FLIGHTLENGHT + 2*admin.FIXSTRING , admin.REMOVE , flightFile);
           admin.writeString(userfile.flightIndex * admin.FLIGHTLENGHT +3*admin.FIXSTRING , admin.REMOVE , flightFile);
           admin.writeString(userfile.flightIndex * admin.FLIGHTLENGHT +4*admin.FIXSTRING , admin.REMOVE , flightFile);
           flightFile.writeLong(0);
           flightFile.writeInt(0);
           admin.adminMenu(  return_mod ,userFile,  flightFile,  ticketFile);
       }
       else
       {
           System.out.println("Flight Id Not Exist");
           admin.adminMenu( return_mod ,userFile ,flightFile , ticketFile);
       }


    }
    public void flightSchedule(int return_mod, RandomAccessFile userFile, RandomAccessFile flightFile, RandomAccessFile ticketFile) throws IOException, InterruptedException {

    Print.tableHeadPrinter();
        for (int i = 0; i <Print.flightCounter ; i++)
        {

            admin.flightTable( return_mod,  userFile,  flightFile,  ticketFile);

        }

    }
    public void signout( int return_mod , RandomAccessFile userFile, RandomAccessFile flightFile, RandomAccessFile ticketFile) throws IOException, InterruptedException {

        admin.adminMenu( return_mod , userFile,  flightFile, ticketFile);
    }



}
