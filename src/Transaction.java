<<<<<<< HEAD
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Transaction {
    private String transactionId;
    private Member member;
    private Book book;
    private String borrowDate; // DD-MM-YYYY
    private String dueDate;    // DD-MM-YYYY
    private String returnDate; // DD-MM-YYYY or null
    private int daysLate;
    private double lateFee;

    public static int totalTransactions = 0;
    public static final double LATE_FEE_PER_DAY = 2000.0;

    private static int counter = 0;
    private static final DateTimeFormatter DF = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public Transaction(Member member, Book book, String borrowDate, int borrowDurationDays) {
        // validate member/book
        if (member == null) throw new IllegalArgumentException("Error: member tidak boleh null");
        if (book == null) throw new IllegalArgumentException("Error: book tidak boleh null");
        // validate borrowDate format
        LocalDate bDate = parseDateOrThrow(borrowDate, "borrowDate format salah (DD-MM-YYYY)");
        LocalDate dDate = bDate.plusDays(Math.max(0, borrowDurationDays));

        // attempt to borrow book
        if (!book.borrowBook()) {
            throw new IllegalArgumentException("Error: Buku tidak tersedia untuk dipinjam");
        }

        counter++;
        totalTransactions++;
        this.transactionId = String.format("TRX%03d", counter);
        this.member = member;
        this.book = book;
        this.borrowDate = bDate.format(DF);
        this.dueDate = dDate.format(DF);
        this.returnDate = null;
        this.daysLate = 0;
        this.lateFee = 0.0;
    }

    // Process return
    public void processReturn(String returnDate) {
        LocalDate rDate = parseDateOrThrow(returnDate, "returnDate format salah (DD-MM-YYYY)");
        LocalDate bDate = LocalDate.parse(this.borrowDate, DF);
        if (rDate.isBefore(bDate)) throw new IllegalArgumentException("Error: returnDate tidak boleh sebelum borrowDate");
        this.returnDate = rDate.format(DF);

        LocalDate due = LocalDate.parse(this.dueDate, DF);
        if (rDate.isAfter(due)) {
            this.daysLate = (int) (rDate.toEpochDay() - due.toEpochDay());
        } else {
            this.daysLate = 0;
        }

        calculateLateFee();
        // return book to library (increase availableCopies)
        this.book.returnBook();
    }

    public void calculateLateFee() {
        double discount = member.getMembershipDiscount();
        this.lateFee = this.daysLate * LATE_FEE_PER_DAY * (1.0 - discount);
    }

    public void displayTransaction() {
        System.out.println("[" + transactionId + "] " + getTransactionStatus());
        System.out.println("Peminjam      : " + member.getName() + " (" + member.getMemberId() + ") - " + member.getMembershipType());
        System.out.println("Buku          : " + book.getTitle() + " (" + book.getBookId() + ")");
        System.out.println("Tgl Pinjam    : " + borrowDate);
        System.out.println("Tgl Tempo     : " + dueDate);
        if (returnDate != null) {
            System.out.println("Tgl Kembali   : " + returnDate);
            System.out.println("Terlambat     : " + daysLate + " hari");
            System.out.println("Denda         : Rp " + (long) lateFee);
        } else {
            System.out.println("Status        : Masih Dipinjam");
        }
        System.out.println("--------------------------------------------");
    }

    public boolean isOverdue(String currentDate) {
        LocalDate cur = parseDateOrThrow(currentDate, "currentDate format salah (DD-MM-YYYY)");
        LocalDate due = LocalDate.parse(this.dueDate, DF);
        return (this.returnDate == null) && cur.isAfter(due);
    }

    public String getTransactionStatus() {
        if (this.returnDate != null) return "SELESAI";
        LocalDate today = LocalDate.now();
        LocalDate due = LocalDate.parse(this.dueDate, DF);
        if (today.isAfter(due)) return "TERLAMBAT";
        return "AKTIF";
    }

    public static int getTotalTransactions() { return totalTransactions; }

    // Getters & Setters
    public String getTransactionId() { return transactionId; }
    public Member getMember() { return member; }
    public Book getBook() { return book; }
    public String getBorrowDate() { return borrowDate; }
    public String getDueDate() { return dueDate; }
    public String getReturnDate() { return returnDate; }
    public int getDaysLate() { return daysLate; }
    public double getLateFee() { return lateFee; }

    private LocalDate parseDateOrThrow(String dateStr, String errMsg) {
        try {
            return LocalDate.parse(dateStr, DF);
        } catch (DateTimeParseException ex) {
            throw new IllegalArgumentException(errMsg);
        }
    }
=======
package PACKAGE_NAME;

public class Transaction {
>>>>>>> e0f232aa685968f553a1e4a3ac2775f68a570afd
}
