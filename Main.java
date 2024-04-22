import java.util.Scanner;

public class Main {

    private static final int STATE_INIT = 0;
    private static final int STATE_BANK_MAIN_MENU = 1;
    private static final int STATE_BANK_LOGIN = 2;
    private static final int STATE_BANK_CREATE_USER = 3;
    private static final int STATE_USER_ACCOUNT_CREATE_OR_SELECT = 4;
    private static final int STATE_USER_ACCOUNT_SELECTION = 5;
    private static final int STATE_USER_CREATE_ACCOUNT = 6;
    private static final int STATE_ACCOUNT_MENU = 7;
    private static final int STATE_ACCOUNT_WITHDRAW = 8; 
    private static final int STATE_ACCOUNT_DEPOSIT = 9;
    private static final int STATE_ACCOUNT_TRANSFER = 10;
    private static final int STATE_ACCOUNT_BALANCE = 11;
    private static final int STATE_ACCOUNT_HISTORY = 12;
    private static final int STATE_ACCOUNT_LOGOUT = 13;
    private static final int STATE_EXIT = 14;

    

    private static int currentState = 0;

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        Bank newBank = new Bank("Sparkasse");
        User activeUser = null;
        Account selectedAccount = null;
        int selectedAccountIndex = 0;
        boolean on = true;

        newBank.createUser("Ludwig", "123");
        newBank.createUser("Alf", "123");


        do {
            switch (currentState){

                case STATE_INIT:
                    currentState = STATE_BANK_MAIN_MENU; 
                    break;
                case STATE_BANK_MAIN_MENU:
                    currentState = newBank.mainMenuSelect(in, newBank);
                    break;
                case STATE_BANK_LOGIN:
                    if (newBank.bankLogIn(in, newBank, activeUser)) {
                        activeUser = newBank.setUser();
                        currentState = STATE_USER_ACCOUNT_CREATE_OR_SELECT;
                    } else {
                        currentState = STATE_BANK_MAIN_MENU;
                    }
                    break;
                case STATE_BANK_CREATE_USER:
                    newBank.userCreation(in, newBank);
                    currentState = STATE_BANK_MAIN_MENU;
                    break;
                case STATE_USER_ACCOUNT_CREATE_OR_SELECT:
                    currentState = newBank.accountCreateOrSelectButton(in);
                    break;
                case STATE_USER_ACCOUNT_SELECTION:
                    if (activeUser.getAccounts().size() >= 1) {
                        selectedAccount = newBank.accountSelection(in, activeUser, selectedAccountIndex, selectedAccount);
                        currentState = STATE_ACCOUNT_MENU;
                    } else {
                        System.out.println("You have to create a bank account before you can continue");
                        currentState = STATE_USER_CREATE_ACCOUNT;
                    }
                    break;
                case STATE_USER_CREATE_ACCOUNT:
                    newBank.accountCreation(in, activeUser, newBank);
                    currentState = STATE_USER_ACCOUNT_SELECTION;
                    break;
                case STATE_ACCOUNT_MENU:
                    currentState = newBank.accountMenu(in, activeUser, selectedAccount, selectedAccountIndex);
                    break;
                case STATE_ACCOUNT_WITHDRAW:
                    newBank.accountWithdraw(in, newBank, selectedAccount);
                    currentState = STATE_ACCOUNT_MENU;
                    break;
                case STATE_ACCOUNT_DEPOSIT:
                    newBank.accountDeposit(in, newBank, selectedAccount);
                    currentState = STATE_ACCOUNT_MENU;
                    break;
                case STATE_ACCOUNT_TRANSFER:
                    newBank.accountTransfer(in, newBank, selectedAccount);
                    currentState = STATE_ACCOUNT_MENU;
                    break;
                case STATE_ACCOUNT_BALANCE:
                    newBank.accountGetBalance(selectedAccount);
                    currentState = STATE_ACCOUNT_MENU;
                    break;
                case STATE_ACCOUNT_HISTORY:
                    newBank.accountGetHistory(selectedAccount);
                    currentState = STATE_ACCOUNT_MENU;
                    break;
                case STATE_ACCOUNT_LOGOUT:
                    newBank.userLogout(activeUser, selectedAccount, selectedAccountIndex);
                    currentState = STATE_BANK_MAIN_MENU;
                    break;
                case STATE_EXIT:
                    on = false;
                    break;
                default: throw new RuntimeException();
            }
        }
        while (on);    
    }
}