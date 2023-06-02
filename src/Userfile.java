import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Userfile
{
    Scanner cin = new Scanner(System.in);
    Print user = new Print();
    int flightIndex = 0 ;

    public void userMenus(String username ,String password, int return_mod, RandomAccessFile userFile , RandomAccessFile flightFile , RandomAccessFile ticketFile) throws IOException, InterruptedException
    {

        int input_user = cin.nextInt() ;
        switch (input_user)
        {

            case 1 :
                change_pass(username,password,return_mod,userFile,flightFile,ticketFile);
                break;
            case 2 :
                searching(username,password,return_mod,userFile, flightFile, ticketFile);
                break;
            case  3 :
                booking_ticket(return_mod, userFile, flightFile, ticketFile);
                break;
            case 4 :
                canceling();
                break;
            case 5 :
                booked_ticket();
                break;
            case 6 :
                charge(username,password,return_mod,userFile ,flightFile , ticketFile );
                break;
            case 0 :
                sign_out(username , password,return_mod);
                break;

        }

    }



    public void change_pass(String username,String password,int return_mod,RandomAccessFile userFile, RandomAccessFile flightFile, RandomAccessFile ticketFile) throws IOException, InterruptedException {
        System.out.println("Enter last password");
        String lastPass = cin.next();


        if ( checkPassword( lastPass , userFile ) == 1 )
        {
            System.out.println("Enter a new password");
            String newPass = cin.next();
            user.writeString(user.loginIndex*user.USERLENGHT +40, newPass , userFile);
            System.out.println("password changed");
            user.userMenu(username,password,return_mod,userFile,flightFile,ticketFile);



        }

        else
        {

            System.out.println("last password not correct ");
            change_pass( username,password,return_mod,userFile,flightFile,ticketFile);

        }


    }

    public void searching(String username,String password,int return_mod,RandomAccessFile userFile, RandomAccessFile flightFile,RandomAccessFile ticketFile) throws IOException, InterruptedException {

        user.searchMenu();
        int inputSearch = cin.nextInt();
        switch (inputSearch)
        {
            case 1 :
                searchOrigin(username,password,return_mod, userFile, flightFile,  ticketFile);
                break;
            case 2 :
                searchDestination(username,password,return_mod, userFile, flightFile,  ticketFile);
                break;
            case 3 :
                searchDate(username,password,return_mod, userFile, flightFile,  ticketFile);
                break;
            case 4 :
                searchTime(username,password,return_mod, userFile, flightFile,  ticketFile);
                break;
            case  5 :
                searchPrice();
                break;
            case  6 :
                searchId(username,password,return_mod,userFile,flightFile, ticketFile);
                break;
            default:
                user.searchMenu();
                break;




        }
        user.userMenu(username,password,return_mod,userFile , flightFile , ticketFile);


    }
    public void booking_ticket(int return_mod,RandomAccessFile userFile,RandomAccessFile flightFile,RandomAccessFile  ticketFile) throws IOException, InterruptedException {

        System.out.println("Enter flight id");
        String inputId = cin.next();
        if ( checkFlightId(inputId , flightFile) == 1 )
        {
            for (int i = 0; i <Print.flightCounter ; i++)
            {
                if ( flightFile.readInt() >= 1 )
                {
                    if (userFile.readLong() >= flightFile.readInt())
                    {
                        bookEnding( userFile, flightFile,  ticketFile);
                    }
                }

            }


        }




    }
    public void canceling()
    {

    }



    public void booked_ticket()
    {

    }
    public void charge(String username,String password,int return_mod ,RandomAccessFile userFile ,  RandomAccessFile flightFile, RandomAccessFile ticketFile) throws IOException, InterruptedException {

        System.out.println("Enter How much do you want to charge");
        int inputValet = cin.nextInt();
        userFile.seek(Print.loginIndex *user.USERLENGHT+ 80);
        userFile.writeInt(inputValet);
        System.out.println("charging don ");
        user.userMenu(username,password,return_mod,userFile , flightFile , ticketFile );

    }
    public void sign_out(String username ,String password ,int return_mod) throws IOException, InterruptedException {
        user.firstMenu(username ,password ,return_mod);

    }

    public void searchId(String username,String password,int return_mod, RandomAccessFile userFile, RandomAccessFile flightFile, RandomAccessFile ticketFile) throws IOException, InterruptedException
    {

        System.out.println("Enter flight id");
        String inputId = cin.next();
        Print.tableHeadPrinter();

        if ( checkFlightId(inputId  , flightFile) == 1 )
        {
            user.flightTable( username,password,return_mod, userFile, flightFile, ticketFile);
        }


    }

    public void searchOrigin (String username,String password,int return_mod, RandomAccessFile userFile, RandomAccessFile flightFile, RandomAccessFile ticketFile) throws IOException, InterruptedException
    {
        System.out.println("Enter origin");
        String inputOrigin = cin.next();
        Print.tableHeadPrinter();

        if ( checkFlightOrigin(inputOrigin , flightFile) == 1 )
        {
            user.flightTable(username,password,return_mod, userFile, flightFile, ticketFile);
        }


    }
    public void searchDestination(String username,String password,int return_mod, RandomAccessFile userFile, RandomAccessFile flightFile, RandomAccessFile ticketFile) throws IOException, InterruptedException
    {
        System.out.println("Enter destination");
        String inputDestination = cin.next();
        Print.tableHeadPrinter();

        if ( checkFlightOrigin(inputDestination, flightFile) == 1 )
        {
            user.flightTable(username ,password,return_mod, userFile, flightFile, ticketFile);
        }

    }
    public void searchDate(String username,String password,int return_mod, RandomAccessFile userFile, RandomAccessFile flightFile, RandomAccessFile ticketFile) throws IOException, InterruptedException
    {
        System.out.println("Enter date");
        String inputDate = cin.next();
        Print.tableHeadPrinter();

        if ( checkFlightOrigin(inputDate , flightFile) == 1 )
        {
            user.flightTable(username ,password,return_mod, userFile, flightFile, ticketFile);
        }

    }
    public void searchTime (String username,String password,int return_mod, RandomAccessFile userFile, RandomAccessFile flightFile, RandomAccessFile ticketFile) throws IOException, InterruptedException
    {
        System.out.println("Enter time");
        String inputTime = cin.next();
        Print.tableHeadPrinter();

        if ( checkFlightOrigin(inputTime , flightFile) == 1 )
        {
            user.flightTable(username ,password,return_mod, userFile, flightFile, ticketFile);
        }
    }
    public void searchPrice ()
    {
        System.out.println("Enter price");
        double inputPrice = cin.nextInt();

    }


    public int checkPassword(String lastPass , RandomAccessFile userFile ) throws IOException
    {
        System.out.println("password:");
        System.out.println( user. readString(user.loginIndex * user.USERLENGHT + 40 , userFile ));
        System.out.println(user.loginIndex);
       if( user. readString(user.loginIndex * user.USERLENGHT + 40 , userFile ).equals(lastPass))
           return 1 ;
       else
           return -1;


    }
    public int checkPrice(double inputPrice , RandomAccessFile flightFile ) throws IOException {
        for (int i = 0; i <Print.flightCounter ; i++)
        {
            if (flightFile.readDouble() == inputPrice)
            {
                return 1 ;
            }

        }
        return -1 ;

    }
    public int checkFlightId  (String inputId , RandomAccessFile flightFile ) throws IOException
    {
        for (int i = 0; i < Print.flightCounter ; i++)
        {

            if (user.readString(i * user.FLIGHTLENGHT, flightFile).equals(inputId)) {
                flightIndex = i;
                return 1;
            }
        }

        return -1;

    }


    public int checkFlightOrigin  (String inputId , RandomAccessFile flightFile ) throws IOException
    {
        for (int i = 0; i < Print.flightCounter ; i++)
        {
            if ( user.readString(i*user.FLIGHTLENGHT +40 , flightFile ).equals(inputId) ) {
                flightIndex = i ;
                return 1;
            }
        }

        return -1;

    }
    public int checkFlightDestination  (String inputId , RandomAccessFile flightFile ) throws IOException
    {
        for (int i = 0; i < Print.flightCounter ; i++)
        {
            if ( user.readString(i*user.FLIGHTLENGHT +80 , flightFile ).equals(inputId) ) {
                flightIndex = i ;
                return 1;
            }
        }

        return -1;

    }
    public int checkFlightDate  (String inputId , RandomAccessFile flightFile ) throws IOException
    {
        for (int i = 0; i < Print.flightCounter ; i++)
        {
            if ( user.readString(i*user.FLIGHTLENGHT+120 , flightFile ).equals(inputId) ) {
                flightIndex = i ;
                return 1;
            }
        }

        return -1;

    }
    public int checkFlightTime  (String inputId , RandomAccessFile flightFile ) throws IOException
    {
        for (int i = 0; i < Print.flightCounter ; i++)
        {
            if ( user.readString(i*user.FLIGHTLENGHT + 160 , flightFile ).equals(inputId) ) {
                flightIndex = i ;
                return 1;
            }
        }

        return -1;

    }
    public void bookEnding(RandomAccessFile userFile,RandomAccessFile flightFile,RandomAccessFile  ticketFile) throws IOException {

    }
}







