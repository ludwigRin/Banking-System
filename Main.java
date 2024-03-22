import java.util.Scanner;

public class Main {

    private static final int STATE_INIT = 0;
    private static final int STATE_BANK_MAIN_MENU = ;
    private static final int STATE_BANK_LOGIN = ;
    private static final int STATE_BANK_CREATE_USER = ;
    private static final int STATE_USER_SHOW_ACCOUNTS = 
    private static final int STATE_USER_ACCOUNT_SELECTION = ;
    private static final int STATE_USER_CREATE_ACCOUNT = ;
    private static final int STATE_ACCOUNT_MENU = ;
    private static final int STATE_ACCOUNT_WITHDRAW = 
    private static final int STATE_ACCOUNT_DEPOSIT =
    private static final int STATE_ACCOUNT_TRANSFER =
    private static final int STATE_ACCOUNT_BALANCE =
    private static final int STATE_ACCOUNT_HISTORY =
    private static final int STATE_ACCOUNT_LOGOUT =


    

    private static int currentState = 0;

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        Bank newBank = new Bank("Sparkasse");
        User activeUser = null;
        Account selectedAccount = null;
        int selectedAccountIndex = 0;

        newBank.createUser("Ludwig", "123");
        
        switch (currentState){

            case STATE_INIT:
                currentState = STATE_BANK_MAIN_MENU; 
                break;
            case STATE_BANK_MAIN_MENU:
                currentState = newBank.mainMenuSelect(in, newBank);
                break;
            case STATE_BANK_LOGIN: 
                boolean loggedIn = newBank.bankLogIn(in, newBank, activeUser);
                if (loggedIn) currentState = STATE_USER_ACCOUNT_SELECTION;
                else ...

                break;
                return;
            case STATE_BANK_CREATE_USER:
                newBank.userCreation(in, newBank);
                currentState = STATE_BANK_MAIN_MENU;
                break;
            case STATE_USER_ACCOUNT_SELECTION:
                if (activeUser.getAccounts().size() >= 1) {
                    selectedAccount = newBank.accountSelection(in, activeUser, selectedAccountIndex, selectedAccount);
                    currentState = STATE_ACCOUNT_MENU;
                } else {
                    currentState = STATE_USER_CREATE_ACCOUNT;
                }
                break;
            case STATE_USER_CREATE_ACCOUNT:
                
                break;
            case STATE_SHOW_ACCOUNT:
                account.printAccounts();
                currentState = STATE_ACTION_SELECTION;
                break;

            default: throw new RuntimeException();


        }


        Bank newBank = new Bank("Sparkasse");

        newBank.createUser("Ludwig", "123");

        


        


        boolean on = true;

        int selectedAccountIndex = 0;
        Account selectedAccount;

        do {

            if (activeUser == null) {

                System.out.println("Welcome to " + newBank.bankName + "!");
                System.out.println("How can i help you?");
                System.out.println("1. Log in");
                System.out.println("2. Create an Account");
                System.out.println("0. Exit");
                while (!in.hasNextInt()) {
                    System.out.println("Please enter an integer from 0 to 2!");
                    in.next();
                }
                int choice = in.nextInt();
                in.nextLine();

                switch (choice) {
                    case 1:
                        System.out.print("Please enter your username: ");
                        String inputUsername = in.nextLine();
                        System.out.print("Please enter your password: ");
                        String inputPassword = in.nextLine();
                        if (newBank.correctPassword(inputUsername, inputPassword)) {
                            activeUser = newBank.getAllUsers().get(newBank.searchForUser(inputUsername));
                        } else {
                            System.out.println("Wrong password or username!");
                        }
                        break;
                    case 2:
                        System.out.print("Please enter a username: ");
                        String newUsername = in.nextLine();
                        if (newBank.searchForUser(newUsername) != -1) {
                            System.out.println("The username already exists!");
                            break;
                        }
                        System.out.print("Please enter a password: ");
                        String newPassword = in.nextLine();
                        System.out.print("Please enter the password again: ");
                        String validateNewPassword = in.nextLine();
                        if (newPassword.equals(validateNewPassword)) {
                            newBank.createUser(newUsername, newPassword);
                            System.out.println("Your Account has been created");
                            break;
                        } else {
                            System.out.println("The passwords entered didn't match!");
                            break;
                        }
                    case 3:
                        on = false;
                }

            } else if (activeUser.getAccounts().size() > 1 && selectedAccountIndex == 0) {
                activeUser.printAccounts();
                System.out.print("Please select the account you want to use: ");
                while (!in.hasNextInt()) {
                    System.out.println("Please enter an integer!");
                    in.next();
                }
                selectedAccountIndex = in.nextInt();
                in.nextLine();

            }
            
            
            
            else {
                System.out.println("Welcome " + activeUser.getName());
                System.out.println("What would you like to do?");
                System.out.println("1. Withdraw");
                System.out.println("2. Deposit");
                System.out.println("3. Transfer");
                System.out.println("4. Transaction History");
                System.out.println("5. Check Balance");
                System.out.println("0. Log Out");
                while (!in.hasNextInt()) {
                    System.out.println("Please enter an integer from 0 to 5!");
                    in.next();
                }
                int accountChoice = in.nextInt();
                in.nextLine();

                selectedAccount = activeUser.getAccounts().get(selectedAccountIndex - 1);

                switch (accountChoice) {
                    case 1:
                        System.out.print("Please enter the amount you would like to withdraw: ");
                        double amountWithdraw = in.nextDouble();
                        in.nextLine();
                        if (amountWithdraw < selectedAccount.getBalance()) {
                            Transaction withdraw = new Transaction(newBank, null, -amountWithdraw, "Withdraw", selectedAccount);
                            withdraw.execute();
                            System.out.println("You successfully withdrew " + amountWithdraw + " Euro");
                            System.out.println("Your new balance is " + selectedAccount.getBalance());
                        } else {
                            System.out.println("You dont have enough money :(");
                        }

                    case 2:
                        System.out.print("Please enter the amount you would like to depsoit: ");
                        double amountDeposit = in.nextDouble();
                        in.nextLine();
                        Transaction deposit = new Transaction(newBank, null, amountDeposit, "Withdraw", selectedAccount);
                        deposit.execute();
                        System.out.println("You successfully deposited " + amountDeposit + " Euro");
                        System.out.println("Your new balance is " + selectedAccount.getBalance() + " Euro");
                        break;

                    case 5:
                        System.out.println(selectedAccount.getBalance());
                        
                }
            }
        }    
        while (on);
    }
}