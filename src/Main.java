import java.util.ArrayList;
import java.util.Scanner;

class Book {
    private int id;
    private String title;
    private String author;
    private boolean available;

    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.available = true;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}

class Library {
    private ArrayList<Book> books;

    public Library() {
        books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public Book searchBook(int id) {
        for (Book book : books) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null;
    }
}

class AdminModule {
    public void addBook(Library library) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter book id:");
        int id = scanner.nextInt();
        System.out.println("Enter book title:");
        String title = scanner.next();
        System.out.println("Enter book author:");
        String author = scanner.next();
        Book book = new Book(id, title, author);
        library.addBook(book);
        System.out.println("Book added successfully.");
    }
}

class UserModule {
    public void searchBook(Library library) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter book id:");
        int id = scanner.nextInt();
        Book book = library.searchBook(id);
        if (book != null && book.isAvailable()) {
            System.out.println("Book found:");
            System.out.println("Title: " + book.getTitle());
            System.out.println("Author: " + book.getAuthor());
            book.setAvailable(false);
            System.out.println("Book issued successfully.");
        } else {
            System.out.println("Book not found or not available.");
        }
    }

    public void returnBook(Library library) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter book id:");
        int id = scanner.nextInt();
        Book book = library.searchBook(id);
        if (book != null && !book.isAvailable()) {
            book.setAvailable(true);
            System.out.println("Book returned successfully.");
        } else {
            System.out.println("Book not found or already returned.");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        AdminModule adminModule = new AdminModule();
        UserModule userModule = new UserModule();
        Scanner scanner = new Scanner(System.in);
        int role;
        do {
            System.out.println("\nSelect your role:");
            System.out.println("1. Admin");
            System.out.println("2. User");
            System.out.println("3. Exit");
            role = scanner.nextInt();
            switch (role) {
                case 1:
                    int adminChoice;
                    do {
                        System.out.println("\nAdmin Menu:");
                        System.out.println("1. Add Book");
                        System.out.println("2. Exit");
                        adminChoice = scanner.nextInt();
                        switch (adminChoice) {
                            case 1:
                                adminModule.addBook(library);
                                break;
                            case 2:
                                System.out.println("Exiting admin menu...");
                                break;
                            default:
                                System.out.println("Invalid choice. Please try again.");
                        }
                    } while (adminChoice != 2);
                    break;
                case 2:
                    int userChoice;
                    do {
                        System.out.println("\nUser Menu:");
                        System.out.println("1. Search Book");
                        System.out.println("2. Return Book");
                        System.out.println("3. Exit");
                        userChoice = scanner.nextInt();
                        switch (userChoice) {
                            case 1:
                                userModule.searchBook(library);
                                break;
                            case 2:
                                userModule.returnBook(library);
                                break;
                            case 3:
                                System.out.println("Exiting user menu...");
                                break;
                            default:
                                System.out.println("Invalid choice. Please try again.");
                        }
                    } while (userChoice != 3);
                    break;
                case 3:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (role != 3);
    }
}
