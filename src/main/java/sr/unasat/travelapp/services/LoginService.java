package sr.unasat.travelapp.services;

import sr.unasat.travelapp.chainofresponsibilities.AdminReport;
import sr.unasat.travelapp.chainofresponsibilities.AdminReportHandler;
import sr.unasat.travelapp.dao.AccountDAO;
import sr.unasat.travelapp.dao.TravelGroupDAO;
import sr.unasat.travelapp.dao.TravelPackageDAO;
import sr.unasat.travelapp.dao.TravelerDAO;
import sr.unasat.travelapp.entities.Account;
import sr.unasat.travelapp.entities.TravelPackage;
import sr.unasat.travelapp.entities.Traveler;
import sr.unasat.travelapp.reports.RawReport;
import sr.unasat.travelapp.reports.TextFile;
import sr.unasat.travelapp.reports.ReportAdapter;
import sr.unasat.travelapp.travelpackagefactory.TravelGroupCreator;
import sr.unasat.travelapp.travelpackagefactory.TravelPackageCreator;
import sr.unasat.travelapp.travelpackagefactory.TravelPlanCreator;
import sr.unasat.travelapp.travelpackagefactory.TravelPackageFactory;

import java.util.Scanner;

public class LoginService {

    private boolean inLogin = true;
    private boolean isSuccessful;
    private boolean inAdminSection = true;
    private boolean inAdminReport = true;
    private boolean inUserSection = true;
    private boolean inCreateTravelPackage = true;
    private boolean inViewTravelPackageReport = true;
    private boolean inManageTravelerData = true;

    private Scanner scanner = new Scanner(System.in);

    private AccountDAO accountDAO = new AccountDAO();
    private Account account;
    private AdminReportHandler adminReportHandler;
    private AdminReport adminReport;
    private TravelerDAO travelerDAO = new TravelerDAO();
    private TravelGroupDAO travelGroupDAO = new TravelGroupDAO();
    private TravelPackageDAO travelPackageDAO = new TravelPackageDAO();
    private TravelPackage latestTravelPackageFromAccount;

    public void login() {
        System.out.println("Welcome to your virtual travel agent");
        String selection;
        do {
            System.out.println("Would you like to sign in or sign up");
            System.out.println("1: Sign up \n2: Sign in \n0: Exit App");
            selection = scanner.next();
            switch (selection) {
                case "1":
                    System.out.println("Not available yet \n");
                    break;
                case "2":
                    while (!isSuccessful) {
                        System.out.println("Enter username: ");
                        String username = scanner.next();
                        System.out.println("Enter password: ");
                        String password = scanner.next();
                        if (accountDAO.verifyAccount(username, password)) {
                            account = accountDAO.retrieveAccount(username, password);
                            if (account.getAccountType().equals("user")) {
                                latestTravelPackageFromAccount = travelPackageDAO.retrieveLastTravelPackageByAccount(account);
                                System.out.println("You have successfully signed in \n");
                                userSection();
                            } else {
                                System.out.println("You have successfully signed in \n");
                                adminSection();
                            }
                            isSuccessful = true;
                        } else {
                            System.out.println("Incorrect username or password entered \n");
                            isSuccessful = true;
                        }
                    }
                    isSuccessful = false;
                    break;
                case "0":
                    inLogin = false;
                    break;
                default:
                    System.out.println("Incorrect choice made \nTry again \n");
                    break;
            }
        } while (inLogin);
        System.out.println("----------Exiting app---------- \n");
    }

    public void adminSection() {
        System.out.println("Welcome to the Admin section");
        String selection;
        do {
            System.out.println("What would you like to do");
            System.out.println("1: Handle data \n2: View admin reports \n0: Sign Out");
            selection = scanner.next();
            switch (selection) {
                case "1":
                    System.out.println("Not available yet \n");
                    break;
                case "2":
                    viewAdminReport();
                    break;
                case "0":
                    inAdminSection = false;
                    break;
                default:
                    System.out.println("Incorrect choice made \nTry again \n");
                    break;
            }
        } while (inAdminSection);
        System.out.println("----------Exiting Admin Section----------\n");
        inAdminSection = true;
    }

    public void viewAdminReport() {
        adminReport = new AdminReport();
        adminReportHandler = new AdminReportHandler();
        String input;
        int selection = -1;
        int count;
        do {
            System.out.println("Which report would you like to view");
            count = 1;
            for (String reportType : adminReport.getReportList()) {
                System.out.println(count + ": " + reportType);
                count++;
            }
            System.out.println("0: Cancel");
            input = scanner.next();
            if (isStringInt(input)) {
                selection = Integer.parseInt(input);
                if (selection >= 1 && selection < count) {
                    adminReportHandler.startHandling(selection);
                } else if (selection == 0) {
                    break;
                } else {
                    System.out.println("Incorrect choice made \nTry again \n");
                }
            } else {
                System.out.println("Incorrect choice made \nTry again \n");
            }
        } while ((selection < 0 || selection >= count));
        System.out.println("----------End of report----------\n");
    }

    public void userSection() {
        System.out.println("Welcome to the User section");
        String selection;
        do {
            System.out.println("What would you like to do");
            System.out.println("1: Create a travel package \n2: Manage traveler data \n3: View latest travel package report \n0: Sign Out");
            selection = scanner.next();
            switch (selection) {
                case "1":
                    createTravelPackage();
                    break;
                case "2":
                    manageTravelerData();
                    break;
                case "3":
                    viewTravelPackageReport();
                    break;
                case "0":
                    inUserSection = false;
                    break;
                default:
                    System.out.println("Incorrect choice made \nTry again \n");
                    break;
                }
            } while (inUserSection);
        System.out.println("----------Exiting User Section----------\n");
        inUserSection = true;
    }

    public void createTravelPackage() {
        String selection;
        do {
            System.out.println("What type of travel would you like");
            System.out.println("1: Tour \n2: Budget travel \n0: Cancel");
            selection = scanner.next();
            switch (selection) {
                case "1":
                    System.out.println("You choose: Tour");
                    travelPackageCreator(selection);
                    inCreateTravelPackage = false;
                    break;
                case "2":
                    System.out.println("You choose: Budget travel");
                    break;
                case "0":
                    inCreateTravelPackage = false;
                    break;
                default:
                    System.out.println("Incorrect choice made \nTry again \n");
                    break;
            }
        } while (inCreateTravelPackage);
        //test entity ophaal probleem
//        System.out.println("OVERVIEW OF TRAVEL PACKAGE");
//        System.out.println(latestTravelPackageFromAccount);
//        System.out.println("Test: " + travelerDAO.findLastTravelerRecord());
        System.out.println("----------Exiting Travel Package Creator---------- \n");
        inCreateTravelPackage = true;
    }

    public void travelPackageCreator(String selection) {
        TravelGroupCreator travelGroupCreator = TravelPackageFactory.createTravelGroup(Integer.parseInt(selection));
        TravelPlanCreator travelPlanCreator = TravelPackageFactory.createTravelPlan(Integer.parseInt(selection));
        TravelPackageCreator travelPackageCreator = TravelPackageFactory.createTravelPackage(Integer.parseInt(selection));

        travelGroupCreator.addTravelGroupToDatabase();
        travelPlanCreator.addTravelPlanToDatabase();
        latestTravelPackageFromAccount = travelPackageCreator.addTravelPackageToDatabase(account);
    }


    public void manageTravelerData() {
        String selection;
        if (latestTravelPackageFromAccount != null) {
            do {
                System.out.println("What would you like to do");
                System.out.println("1: Update travelers in travel package \n2: Remove travelers in travel package \n0: Cancel");
                selection = scanner.next();
                switch (selection) {
                    case "1":
                        updateTravelers();
                        break;
                    case "2":
                        deleteTraveler();
                        break;
                    case "0":
                        inManageTravelerData = false;
                        break;
                    default:
                        System.out.println("Incorrect choice made \nTry again \n");
                        break;
                }
            } while (inManageTravelerData);
        } else {
            System.out.println("You have no travel packages yet \n");
        }
        System.out.println("----------Exiting Traveler Data Manager----------");
        inManageTravelerData = true;
    }

    public void updateTravelers() {
        System.out.println("Enter updated information of traveler");
        System.out.println("Enter first name: ");
        String firstName = scanner.next();
        System.out.println("Enter last name: ");
        String lastName = scanner.next();
        System.out.println("Enter passport: ");
        String passport = scanner.next();
        System.out.println("Enter age: ");
        int age = scanner.nextInt();
        Traveler traveler = new Traveler(firstName, lastName, passport, age);
        travelerDAO.updateTraveler(traveler);
        System.out.println("Traveler has been updated");
    }

    public void deleteTraveler() {
        int deletionResult = 2;
        do {
            System.out.println("Enter passport of traveler to be removed");
            System.out.println("Enter passport: ");
            String passport = scanner.next();
            deletionResult = travelerDAO.deleteTraveler(passport);
            if (deletionResult == 1) {
                System.out.println("Traveler has been deleted");
            } else if (deletionResult == 0) {
                System.out.println("Traveler has not been deleted");
            } else {
                System.out.println("Incorrect passport entered \nTry again \n");
            }
        }
        while (deletionResult != 1);
    }

    public void viewTravelPackageReport() {
        String selection;
        if (latestTravelPackageFromAccount != null) {
            do {
                System.out.println("How would you like to view your latest travel package");
                System.out.println("1: Raw report \n2: Text file \n0: Cancel");
                selection = scanner.next();
                switch (selection) {
                    case "1":
                        RawReport rawReport = new RawReport(latestTravelPackageFromAccount);
                        rawReport.displayReport();
                        inViewTravelPackageReport = false;
                        break;
                    case "2":
                        ReportAdapter reportAdapter = new ReportAdapter(new TextFile(latestTravelPackageFromAccount));
                        reportAdapter.displayReport();
                        inViewTravelPackageReport = false;
                        break;
                    case "0":
                        inViewTravelPackageReport = false;
                        break;
                    default:
                        System.out.println("Incorrect choice made \nTry again \n");
                        break;
                }
            } while (inViewTravelPackageReport);
        } else {
            System.out.println("You have no travel packages yet \n");
        }
        System.out.println("----------Exiting Travel Package Viewer---------- \n");
        inViewTravelPackageReport = true;
    }

    public boolean isStringInt(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

}
