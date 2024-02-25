import java.util.*;
import java.text.SimpleDateFormat;

class Expense {
    private String category;
    private double amount;
    private Date date;

    public Expense(String category, double amount, Date date) {
        this.category = category;
        this.amount = amount;
        this.date = date;
    }

    // Getters
    public String getCategory() {
        return category;
    }

    public double getAmount() {
        return amount;
    }

    public Date getDate() {
        return date;
    }
}

class ExpenseTracker {
    private Map<String, List<Expense>> expensesByUser;

    public ExpenseTracker() {
        expensesByUser = new HashMap<>();
    }

    public void addExpense(String username, Expense expense) {
        expensesByUser.computeIfAbsent(username, k -> new ArrayList<>()).add(expense);
    }

    public List<Expense> getExpenses(String username) {
        return expensesByUser.getOrDefault(username, new ArrayList<>());
    }

    public double getTotalExpenses(String username) {
        return getExpenses(username).stream().mapToDouble(Expense::getAmount).sum();
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ExpenseTracker expenseTracker = new ExpenseTracker();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        boolean exit = false;
        while (!exit) {
            System.out.println("Expense Tracker");
            System.out.println("1. Add Expense");
            System.out.println("2. View Expenses");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = getIntInput(scanner);

            switch (choice) {
                case 1:
                    System.out.print("Enter your username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter expense category: ");
                    String category = scanner.nextLine();
                    System.out.print("Enter expense amount: ");
                    double amount = getDoubleInput(scanner);

                    // For simplicity, let's assume the date is the current date
                    Expense expense = new Expense(category, amount, new Date());
                    expenseTracker.addExpense(username, expense);
                    System.out.println("Expense added successfully!");
                    break;
                case 2:
                    System.out.print("Enter your username: ");
                    String user = scanner.nextLine();
                    List<Expense> expenses = expenseTracker.getExpenses(user);
                    if (expenses.isEmpty()) {
                        System.out.println("No expenses found for the user.");
                    } else {
                        System.out.println("Expenses for " + user + ":");
                        for (Expense e : expenses) {
                            System.out.println("Category: " + e.getCategory() +
                                    ", Amount: " + e.getAmount() +
                                    ", Date: " + dateFormat.format(e.getDate()));
                        }
                        System.out.println("Total expenses: " + expenseTracker.getTotalExpenses(user));
                    }
                    break;
                case 3:
                    exit = true;
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }

    // Helper method to get integer input with validation
    private static int getIntInput(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next(); // Consume invalid input
        }
        return scanner.nextInt();
    }

    // Helper method to get double input with validation
    private static double getDoubleInput(Scanner scanner) {
        while (!scanner.hasNextDouble()) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next(); // Consume invalid input
        }
        return scanner.nextDouble();
    }
}
