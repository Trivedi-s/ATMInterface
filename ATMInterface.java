import java.util.Scanner;

class BankAccount {

    String name;
    String username;
    String password;
    String accountno;
    float balance=200000f; 
    int transactions = 0; 
    String transactionhistory = "";

    public void register(){
        Scanner sc = new Scanner(System.in);
        System.out.println("\nEnter your Name: ");
        this.name = sc.nextLine();
        System.out.println("\nEnter your Username: ");
        this.username = sc.nextLine();
        System.out.println("\nEnter your Password: ");
        this.password = sc.nextLine();
        System.out.println("\nEnter your Account Number: ");
        this.accountno = sc.nextLine();
        System.out.println("\n Registration Done. Please Login");
    }

    public boolean login(){
        boolean isLogin = false;
        Scanner sc = new Scanner(System.in);
        while(!isLogin){
            System.out.println("\nEnter your Username: ");
            String Username = sc.nextLine();
            if(Username.equals(username)){
                while(!isLogin){
                    System.out.println("\nEnter your Password: ");
                    String Password = sc.nextLine();
                    if(Password.equals(password)){
                        System.out.println("\n Login Successful. Welcome " );
                        isLogin = true;
                    }
                    else{
                        System.out.println("\n Incorrect Password. Try Again");
                    }
                }
            }
            else{
                System.out.println("Username not found");
            }
        }
        return isLogin;
    }

    public void withdraw(){

        System.out.println("\nEnter amount to withdraw: ");
        Scanner sc = new Scanner(System.in);
        float amount = sc.nextFloat();
        try{

            if(balance >= amount){
                transactions++;
                balance = balance - amount;
                System.out.println("\n Withdrawn successfully\n");
                String str = amount + "Rs Withdrawn\n";
                transactionhistory = transactionhistory.concat(str);
           
            }
            else{
                System.out.println("\n Insufficient Balance");
            }
        }
        catch( Exception e){
        }

    }

    public void deposit(){

        System.out.println("\nEnter amount to deposit: ");
        Scanner sc = new Scanner(System.in);
        float amount = sc.nextFloat();

        try{
            if(amount <= 200000f){
                transactions++;
                balance = balance + amount;
                 System.out.println("\n Deposited successfully\n");
                String str = amount + "Rs Deposited\n";
                transactionhistory = transactionhistory.concat(str);
            }
            else {
                System.out.println("Limit is 200000");    
            }

        }
        catch( Exception e){
        }
    }

    public void transfer(){

        Scanner sc = new Scanner(System.in);
        System.out.println("\nEnter Receipent's name: ");
        String recipient = sc.nextLine();
        System.out.println("\nEnter amount to transfer: ");
        float amount = sc.nextFloat();

        try{
            if(balance >= amount){
                if(amount < 100000f){
                    transactions++;
                    balance = balance - amount;
                    System.out.println("\nTransfered successfully to " + recipient);
                    String str = amount + "Rs Transfered to " + recipient + "\n";
                    transactionhistory = transactionhistory.concat(str);
                }
                else{
                    System.out.println("\nLimit is 100000");
                }
            } 
            else{
                System.out.println("\nInsufficient Balance");
            }
        }
        catch( Exception e){
        }
    }

    public void checkBalance(){
        System.out.println("\n Your balance is: " + balance + "Rs\n\n");
    }

    public void transHistory(){
        if(transactions==0){
            System.out.println("No Transactions Yet");
        }
        else {
            System.out.println("Transaction History: \n" + transactionhistory);
        }    
    }
}


public class ATMInterface {


    public static int takeIntegerInput(int limit){
        int input = 0;
        boolean flag = false;

        while(!flag){
            try{
                Scanner sc = new Scanner(System.in);
                input = sc.nextInt();
                flag = true;

                if(flag && input > limit || input < 1){
                    System.out.println("choose number between 1 to " + limit);
                    flag = false;
                    }
            }
            catch( Exception e){
                System.out.println("Invalid Input");
                flag = false;
            }
        };
        return input;
    }


    public static void main(String[] args) {

        System.out.println("\n Welcome to ATM Interface");
        System.out.println("1.Register \n2.Exit");
        System.out.println("\nEnter your choice: ");
        int choice = takeIntegerInput(2);

        if(choice == 1){
            BankAccount b = new BankAccount();
            b.register();
            while(true){
                System.out.println("\n1.Login \n2.Exit");
                System.out.println("\nEnter your choice: ");
                int ch =takeIntegerInput(2);
                if(ch == 1){
                    if(b.login()){
                        System.out.println("\n Welcome back " + b.name+ "\n");
                        boolean isFinished = false;
                        while(!isFinished){
                            System.out.println("\n1.Withdraw \n2.Deposit \n3.Transfer \n4.checkBalance \n5.transHistory \n6.Exit");
                            System.out.println("\n Enter your choice: ");
                            int c = takeIntegerInput(6);
                            switch(c) {
                                case 1:
                                b.withdraw();
                                break;
                                case 2:
                                b.deposit();
                                break;
                                case 3:
                                b.transfer();
                                break;
                                case 4:
                                b.checkBalance();
                                break;
                                case 5:
                                b.transHistory();
                                break;
                                case 6:
                                isFinished = true;
                               break; 
                            }
                        }
                    }
                }
                else {
                    System.exit(0);
                }
            }
        }
        else{
            System.exit(0);
        }


        
    }
}