package pl.coderslab.entity;
import pl.coderslab.ConsoleColors;
import java.util.Scanner;

public class MainDao {


       public static void main(String[] args) {

        String field = " 1 - Dodaj użytkownika, 2 - Usuń użytkownika, 3 - Zmień dane użytkownika, 4 - Pobierz dane o użytkowniku, 5 - Pobieranie danych o wszystkich użytkownikach, 6 - Wyjście";
        String option = "Wybierz opcje (podaj numer): ";
        System.out.println(ConsoleColors.BLUE + option);
        String[] splittedString = field.split(",");
        for (int i = 0; i < splittedString.length; i++) {
            System.out.println(ConsoleColors.RESET + splittedString[i]);
        }
        UserDao userDao = new UserDao();
        User user = new User();
        Scanner scanner = new Scanner(System.in);
        int userId1;
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            switch (input) {
                case "1":
                    user.setUsername(username1());
                    user.setEmail(email1());
                    user.setPassword(password1());
                    userDao.create(user);
                    System.out.println("Dane poprawnie dodane.");
                    break;
                case "2":
                    userId1 = askForUsersId(scanner);
                    userDao.delete(userId1);
                    System.out.println("Użytkownik usunięty.");
                    break;
                case "3":
                    userId1 = askForUsersId(scanner);
                    User userToUpdate = userDao.read(userId1);
                    userToUpdate.setUsername(username2());
                    userToUpdate.setEmail(email2());
                    userToUpdate.setPassword(password2());
                    userDao.update(userToUpdate);
                    System.out.println("Dane poprawnie zmienione.");
                    break;
                case "4":
                    userId1 = askForUsersId(scanner);
                    userDao.readOne(userId1);
                    break;
                case "5":
                    userDao.readAll();
                    break;
                case "6":
                    System.out.println(ConsoleColors.RED +"Dowidzenia.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Podaj poprawną opcje.");
            }
            field = "Dodaj użytkownika, Usuń użytkownika, Zmień dane użytkownika, Pobierz dane o użytkowniku, Pobieranie danych o wszystkich użytkownikach, Wyjście";
            option = "Wybierz opcje: ";
            System.out.println(ConsoleColors.BLUE + option);
            for (int i = 0; i < splittedString.length; i++) {
                System.out.println(ConsoleColors.RESET + splittedString[i]);
            }
        }
    }

    private static String username1(){
        System.out.println("Podaj nazwę Użytkownika: ");
        Scanner scan = new Scanner(System.in);
        String param;
        param = scan.nextLine();

        return param;
    }

    private static String email1(){
        System.out.println("Podaj email użytkownika: ");
        Scanner scan = new Scanner(System.in);
        String param;
        param = scan.nextLine();
        return param;
    }

    private static String password1(){
        System.out.println("Podaj hasło użytkownika: ");
        Scanner scan = new Scanner(System.in);
        String param;
        param = scan.nextLine();
        return param;
    }

    private static String username2(){
        System.out.println("Podaj nową nazwę użytkownika: ");
        Scanner scan = new Scanner(System.in);
        String param;
        param = scan.nextLine();
        return param;
    }

    private static String email2(){
        System.out.println("Podaj nowy adres email: ");
        Scanner scan = new Scanner(System.in);
        String param;
        param = scan.nextLine();
        return param;

    }
    private static String password2(){
        System.out.println("Podaj nowe hasło: ");
        Scanner scan = new Scanner(System.in);
        String param;
        param = scan.nextLine();
        return param;
    }

    private static int askForUsersId(Scanner scanner) {
        System.out.println("Podaj id użytkownika:");
        while(!scanner.hasNextInt()) {
            System.out.println("Podaj liczbę!");
            scanner.nextLine();
        }
        int providedId = scanner.nextInt();
        scanner.nextLine();
        return providedId;
    }
}
