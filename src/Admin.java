import java.util.Scanner;

public class Admin
{
    Scanner cin = new Scanner(System.in);
    Print admin = new Print();
    public void adminMenus ()
    {



        int inputAdmin = cin.nextInt();
        switch (inputAdmin)

        {
            case 1 :
                addedFlight();
                break;
            case 2 :
                updateFlight();
                break;
            case 3 :
                removeFlight();
                break;
            case 4 :
                flightSchedule();
                break;
            case 0 :
                signout();
                break;
            default:
                adminMenus();
                break;
        }

    }
    public  void addedFlight()
    {

        System.out.println("Enter Flight Id");
        String inputId = cin.next();
        for (int i = 0; i <Print.flightObjects.length ; i++)
        {

            if ( Print.flightObjects[i].getFlightId() != null && Print.flightObjects[i].getFlightId().equals(inputId) )
            {
                flagAdd = 1 ;
                break;
            }

        }
        if ( flagAdd == 0  )
        {
            Print.flightObjects[counterAdd].setFlightId(inputId);

            System.out.println("Enter Flight Origin ");
            String inputOrigin = cin.next();
            Print.flightObjects[counterAdd].setFlightOrigin(inputOrigin);
            /********************************************************************/
            System.out.println("Enter Flight Destination ");
            String inputDest = cin.next();
            Print.flightObjects[counterAdd].setFlightDestination(inputDest);
            /*********************************************************************/
            System.out.println("Enter Flight Date ");
            String inputDate = cin.next();
            Print.flightObjects[counterAdd].setFlightDate(inputDate);
            /**********************************************************************/
            System.out.println("Enter Flight Time");
            String inputTime = cin.next();
            Print.flightObjects[counterAdd].setFlightTime(inputTime);
            /**********************************************************************/
            System.out.println("Enter Flight Price");
            int inputPrice = cin.nextInt();
            Print.flightObjects[counterAdd].setFlightPrice(inputPrice);
            /**********************************************************************/
            System.out.println("Enter Flight Seat");
            int inputSeat = cin.nextInt();
            Print.flightObjects[counterAdd].setFlightSeat(inputSeat);
            /***********************************************************************/
            counterAdd++;
            Print.adminMenu();


        }
        else
        {

            System.out.println("Flight Id Exist");
            flagAdd = 0 ;
            addedFlight();

        }






        flagAdd = 0 ;
    }
    public void updateFlight()
    {
        System.out.println("Enter last flight id ");
        inputUp = cin.next();
        checkLastId();
        if ( lastId == 1 )
        {

            editFlight();
            Print.adminMenu();

        }
        else
        {
            System.out.println("Flight id not exist");
            Print.adminMenu();
        }



    }
    public void removeFlight()
    {
        System.out.println("Enter FlightId");
        String input = cin.next();
        for (int i = 0; i <Print.flightObjects.length ; i++)
        {

            if ( Print.flightObjects[i].getFlightId() != null && Print.flightObjects[i].getFlightId().equals(input) )
            {
                Print.flightObjects[i].setFlightId(null);
                Print.flightObjects[i].setFlightOrigin(null);
                Print.flightObjects[i].setFlightDestination(null);
                Print.flightObjects[i].setFlightPrice(0);
                Print.flightObjects[i].setFlightDate(null);
                Print.flightObjects[i].setFlightTime(null);
                Print.flightObjects[i].setFlightSeat(0);
                System.out.println(" remove done ");
                Print.adminMenu();

            }

        }

    }
    public void flightSchedule()
    {

        System.out.print("\n\t\t\t\t\t\t\t\t\t\033[90m      << Flights List >>\033[97m\n");

        System.out.println("\033[35m");
        System.out.print("\t\t\t\t\t");
        System.out.print("+---------------------------------------------------------------------------------------------+");
        System.out.print("\n\t\t\t\t\t");
        System.out.printf("| %-1s| %-1s| %-1s| %-1s| %-1s| %-1s| %-1s|", "\033[33m  Flight ID  \033[35m", "\033[33m   Origins   \033[35m", "\033[33m  Destention  \033[35m", "\033[33m    Data    \033[35m", "\033[33m   Time   \033[35m", "\033[33m   Price   \033[35m", "\033[33m Seats \033[35m");
        System.out.println();
        for (int i = 0; i < Print.flightObjects.length ; i++)
        {

            if ( Print.flightObjects[i].getFlightId() != null )
            {

                System.out.print("\033[35m\t\t\t\t\t");
                System.out.print("+---------------------------------------------------------------------------------------------+");
                try {
                    Thread.sleep(80);
                } catch (InterruptedException e) {
                }
                ;


                System.out.print("\n\t\t\t\t\t");
                System.out.printf("|\033[97m    %-10s\033[35m|\033[97m    %-10s\033[35m|\033[97m    %-11s\033[35m|\033[97m %-12s\033[35m|\033[97m  %-9s\033[35m|\033[97m  %-10s\033[35m|\033[97m  %-4s\033[35m  ", Print.flightObjects[i].getFlightId(), Print.flightObjects[i].getFlightOrigin(), Print.flightObjects[i].getFlightDestination(), Print.flightObjects[i].getFlightDate(), Print.flightObjects[i].getFlightTime(),Print.flightObjects[i].getFlightPrice(), Print.flightObjects[i].getFlightSeat());
                System.out.println("|");
                try {
                    Thread.sleep(80);
                } catch (InterruptedException e) {
                }


            }


        }
        Print.adminMenu();

    }
    public void signout()
    {

        admin.adminMenu();
    }
    public void checkAddid ()
    {
        for (int i = 0; i <Print.flightObjects.length ; i++)
        {

            if ( Print.flightObjects[i].getFlightId().equals(null) )
            {
                countIdi = i ;
                checkIddefreants();
            }

        }



    }
    public  int  checkIddefreants ()
    {
        if ( Print.flightObjects[countIdi].getFlightId().equals(inputUp) )
        {

            flagCheck = 1 ;


        }

        return flagCheck ;
    }
    public void editFlight ()
    {
        System.out.println("Enter new flight id");

        String inputNewid = cin.next();
        Print.flightObjects[countUp].setFlightId(inputNewid);

        System.out.println("Enter new flight origin");

        String inputNewOrigin = cin.next();
        Print.flightObjects[countUp].setFlightOrigin(inputNewOrigin);

        System.out.println("Enter new flight destination");
        String inputNewDestination = cin.next();
        Print.flightObjects[countUp].setFlightDestination(inputNewDestination);

        System.out.println("Enter new flight date");
        String inputNewDate = cin.next();
        Print.flightObjects[countUp].setFlightDate(inputNewDate);

        System.out.println("Enter new flight time ");
        String inputNewTime = cin.next();
        Print.flightObjects[countUp].setFlightTime(inputNewTime);

        System.out.println("Enter new flight price");
        int inputNewPrice = cin.nextInt();
        Print.flightObjects[countUp].setFlightPrice(inputNewPrice);

        System.out.println("Enter new flight seat");
        int inputNewSeat = cin.nextInt();
        Print.flightObjects[countUp].setFlightPrice(inputNewSeat);


    }
    public int checkLastId()
    {

        for (int i = 0; i <Print.flightObjects.length ; i++)
        {

            if ( Print.flightObjects[i].getFlightId()!= null && Print.flightObjects[i].getFlightId().equals(inputUp) )
            {
                lastId = 1 ;
                countUp = i ;
            }

        }

        return lastId ;

    }
}
