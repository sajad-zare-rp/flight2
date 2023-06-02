import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Print {
    Scanner cin = new Scanner(System.in);
    Admin admin = new Admin();


    String str;
   static int  loginIndex ;
    int startPoint;
    int flag ;
    int userIndex = 0 ;
    final int FIXSTRING = 40;
    final int USERLENGHT = 84;
    final int FLIGHTLENGHT = 208;
    final int TICKETLENGHT = 120;
    final String REMOVE = "removed";
    RandomAccessFile rFile;
    static public long userCounter = 0;
    static public long flightCounter = 0;
    static public long ticketCounter = 0;


    public void userMenu(String username,String password,int return_mod,RandomAccessFile userFile, RandomAccessFile flightFile, RandomAccessFile ticketFile) throws IOException, InterruptedException {
        Userfile user = new Userfile();
        System.out.println("Enter your number");
        System.out.println("1-change password");
        System.out.println("2-search flight");
        System.out.println("3-booking ticket");
        System.out.println("4-booked ticket");
        System.out.println("5-cancel ticket");
        System.out.println("6-charge valet");
        System.out.println("0-sign out");

        user.userMenus( username , password, return_mod,  userFile ,  flightFile , ticketFile);

    }

    public void firstMenu(String username , String password ,int return_mod) throws IOException, InterruptedException
    {

        RandomAccessFile userFile = new RandomAccessFile("UserFile.saj", "rw");
        RandomAccessFile flightFile = new RandomAccessFile("FlightFile.saj", "rw");
        RandomAccessFile ticketFile = new RandomAccessFile("TicketFile.saj", "rw");



        userCounter = userFile.length() / USERLENGHT;
        flightCounter = flightFile.length() / FLIGHTLENGHT;
        ticketCounter = ticketFile.length() / TICKETLENGHT;
        defaultFlight(flightFile);


        System.out.println("Enter Your number");
        System.out.println("1-sign up");
        System.out.println("2-sign in");
        int input = cin.nextInt();
        switch (input) {
            case 1:
                signUp(return_mod , userFile);
            case 2:
                signIn(username , password ,return_mod , userFile, flightFile, ticketFile);
        }


    }

    public void adminMenu( String username,String password,int return_mod ,RandomAccessFile userFile, RandomAccessFile flightFile, RandomAccessFile ticketFile) throws IOException, InterruptedException {

        System.out.println("Enter your number");
        System.out.println("1-Add Flight");
        System.out.println("2-Update Flight");
        System.out.println("3-Remove Flight");
        System.out.println("4-Flight schedules Flight");
        System.out.println("0-Sign out Flight");

        admin.adminMenus( username,password,return_mod , userFile ,  flightFile,  ticketFile);

    }

    public void signUp( int return_mod ,RandomAccessFile userFile) throws IOException, InterruptedException {

        System.out.println("Enter your username and password :");
        System.out.println("username :");
        System.out.println("password :");
        String username = cin.next();
       String password = cin.next();
        if ( username.equals("admin") )
        {
            System.out.println("username is not correct");
            signUp(return_mod , userFile);
        }
        for (int i = 0; i < userCounter; i++)
        {
            if (readString(i*USERLENGHT,userFile).equals(username)) {
                {
                    System.out.println("username is exist ");
                    signUp(return_mod ,userFile);


                }


            }
        }

        writeString(userCounter * USERLENGHT, username, userFile);
        writeString(userCounter * USERLENGHT + FIXSTRING, password , userFile);
        userFile.writeInt(0);
        firstMenu(username,password,return_mod);




    }

    public void signIn ( String username, String password ,int return_mod , RandomAccessFile userFile, RandomAccessFile flightFile , RandomAccessFile ticketFile) throws IOException, InterruptedException {
        System.out.println("Enter username and password");
        System.out.println("username :");
        System.out.println("password :");
        username = cin.next();
        password = cin.next();
        if (username.equals("admin") && password.equals("admin"))
        {
            adminMenu( username,password,return_mod , userFile, flightFile,  ticketFile);
        }
        if (checkUser(username, userFile, password) == true)
            userMenu(username,password,return_mod,userFile, flightFile, ticketFile);
        else {
            System.out.println("username or password is not correct");
            firstMenu(username,password,return_mod);
        }


    }

    public String readString(int startPoint, RandomAccessFile rFile) throws IOException {

        String str = "";
        rFile.seek(startPoint);

        for (int i = 0; i < 20; i++)
        {
            str += rFile.readChar();

        }

        return str.trim();

    }



    public boolean checkUser(String username, RandomAccessFile userFile, String password) throws IOException {
        for (int i = 0; i < userCounter; i++) {
            if (readString(i * USERLENGHT, userFile).equals(username))
            {

                if (readString((i * USERLENGHT) + FIXSTRING, userFile).equals(password))
                    loginIndex = i;
                System.out.println(loginIndex);
                return true;

            }


        }

        return false;

    }


    public void writeString(long startPoint, String str, RandomAccessFile rFile) throws IOException {

        str = fixString(str);


        rFile.seek(startPoint);


        rFile.writeChars(str);

    }

    public String fixString(String str) {
        while (str.length() <= 20) {
            str += " ";
        }


        return str.substring(0, 20);

    }

    public void searchMenu() {
        System.out.println("Choose How To search ");
        System.out.println("1-Origin");
        System.out.println("2-Destination");
        System.out.println("3-Date");
        System.out.println("4-Time");
        System.out.println("5-Price");
        System.out.println("6-Id");
    }

    public void defaultFlight(RandomAccessFile flightFile) throws IOException {

        writeString(0, ("WX-10"), flightFile);
        writeString(1 * FIXSTRING, ("SHIRAZ"), flightFile);
        writeString(2 * FIXSTRING, ("TEHRAN"), flightFile);
        writeString(3 * FIXSTRING, ("1402-11-18"), flightFile);
        writeString(4 * FIXSTRING, ("9:45"), flightFile);
        flightFile.writeInt(700000);
        flightFile.writeInt(8);


        writeString(FLIGHTLENGHT, fixString("WX-11"), flightFile);
        writeString(FLIGHTLENGHT + FIXSTRING, fixString("YAZD"), flightFile);
        writeString(FLIGHTLENGHT + 2 * FIXSTRING, fixString("GHOM"), flightFile);
        writeString(FLIGHTLENGHT + 3 * FIXSTRING, fixString("1401-11-13"), flightFile);
        writeString(FLIGHTLENGHT + 4 * FIXSTRING, fixString("7:30"), flightFile);
        flightFile.writeInt(400000);
        flightFile.writeInt(32);


        writeString(2 * FLIGHTLENGHT , fixString("WX-12"), flightFile);
        writeString(2 * FLIGHTLENGHT +  FIXSTRING, fixString("YAZD"), flightFile);
        writeString(2 * FLIGHTLENGHT + 2* FIXSTRING, fixString("MASHHAD"), flightFile);
        writeString(2 * FLIGHTLENGHT + 3 * FIXSTRING, fixString("1401-12-20"), flightFile);
        writeString(2 * FLIGHTLENGHT + 4 * FIXSTRING, fixString("17:45"), flightFile);
        flightFile.writeInt(800000);
        flightFile.writeInt(75);
    }
    public static void tableHeadPrinter() throws IOException, InterruptedException {
        System.out.print("\n\n\n\n\n\n\t\t");
        System.out.print("+---------------------------------------------------------------------------------------------+");
        System.out.printf("\n\t\t| %-1s| %-1s| %-1s| %-1s| %-1s| %-1s| %-1s|", "  Flight ID  ", "   Origins   ", "  Destination  ", "    Data    ", "   Time   ", "   Price   ", " Seats ");
        System.out.print("\n\t\t");
        System.out.print("+---------------------------------------------------------------------------------------------+");
    }

    public void flightTable(String username,String password,int return_mod, RandomAccessFile userFile, RandomAccessFile flightFile, RandomAccessFile ticketFile) throws IOException, InterruptedException {
        try {
            Userfile user_ins = new Userfile();

            tableHeadPrinter();

            for (int i = 0; (long) i < flightCounter; ++i) {
                if (!readString(i * FLIGHTLENGHT, flightFile).equals(REMOVE)) {
                    System.out.print("\n\t\t");
                    System.out.printf("|    %-10s  |     %-10s |    %-10s|     %-9s  |  %-10s |   %-7s |   %-9s|   %-4s ", readString(i * USERLENGHT, flightFile), readString(i * USERLENGHT + FIXSTRING, flightFile), readString(i * USERLENGHT + 2 * FIXSTRING, flightFile), readString(i * USERLENGHT + 3 * FIXSTRING, flightFile), readString(i * USERLENGHT + 4 * FIXSTRING, flightFile), flightFile.readLong(), flightFile.readInt());
                    System.out.printf("|");
                    System.out.print("\n\t\t");
                    System.out.print("+-----------------------------------------------------------------------------------------------------------------+");
                }
            }

            System.out.print("\n\n\n\t\t\t\t\t");
            System.out.println("press any key to return...");
            cin.next();
            Admin admin1 = new Admin();
            if (return_mod == 1) {
                adminMenu( username,password ,return_mod ,userFile, flightFile, ticketFile);
            } else {
                userMenu(username,password,return_mod,userFile, flightFile, ticketFile);
            }
        } catch (Exception var7) {

            cin.next();

            flightTable(username,password,return_mod, userFile, flightFile, ticketFile);
        }


    }



}
