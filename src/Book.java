<<<<<<< HEAD
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class Book {
    private String bookId;
    private String title;
    private String author;
    private String category;
    private int publicationYear;
    private boolean isAvailable;
    private int totalCopies;
    private int availableCopies;

    private static int counter = 0;
    public static int totalBooks = 0;
    private static final List<String> VALID_CATEGORIES = Arrays.asList("Fiction", "Non-Fiction", "Science", "Technology", "History");

    // Default constructor
    public Book() {
        counter++;
        this.bookId = String.format("BK%03d", counter);
        totalBooks++;
    }

    // Parameterized constructor
    public Book(String title, String author, String category, int publicationYear, int totalCopies) {
        this(); // generate id
        setTitle(title);
        setAuthor(author);
        setCategory(category);
        setPublicationYear(publicationYear);
        setTotalCopies(totalCopies);
        setAvailableCopies(totalCopies);
        this.isAvailable = this.availableCopies > 0;
    }

    public void displayBookInfo() {
        System.out.println("[" + bookId + "] " + title);
        System.out.println("Penulis       : " + author);
        System.out.println("Kategori      : " + category);
        System.out.println("Tahun Terbit  : " + publicationYear);
        System.out.println("Umur Buku     : " + getBookAge() + " tahun" + (isNewRelease() ? " | [NEW RELEASE]" : ""));
        System.out.println("Total Copy    : " + totalCopies + " eksemplar");
        System.out.println("Tersedia      : " + availableCopies + " eksemplar | Status: " + getAvailabilityStatus());
        System.out.println("--------------------------------------------");
    }

    public boolean borrowBook() {
        if (availableCopies > 0) {
            availableCopies--;
            isAvailable = availableCopies > 0;
            return true;
        }
        return false;
    }

    public void returnBook() {
        if (availableCopies < totalCopies) {
            availableCopies++;
            isAvailable = true;
        }
    }

    public int getBookAge() {
        int currentYear = LocalDate.now().getYear();
        return Math.max(0, currentYear - publicationYear);
    }

    public boolean isNewRelease() {
        return getBookAge() <= 2;
    }

    public String getAvailabilityStatus() {
        if (availableCopies == 0) return "Tidak Tersedia";
        if (availableCopies <= 5) return "Terbatas";
        return "Banyak Tersedia";
    }

    public static int getTotalBooks() { return totalBooks; }

    // Getters & Setters with validations
    public String getBookId() { return bookId; }

    public String getTitle() { return title; }
    public void setTitle(String title) {
        if (title == null || title.trim().isEmpty()) throw new IllegalArgumentException("Error: title tidak boleh kosong");
        this.title = title.trim();
    }

    public String getAuthor() { return author; }
    public void setAuthor(String author) {
        if (author == null || author.trim().isEmpty()) throw new IllegalArgumentException("Error: author tidak boleh kosong");
        this.author = author.trim();
    }

    public String getCategory() { return category; }
    public void setCategory(String category) {
        if (category == null || !VALID_CATEGORIES.contains(category)) throw new IllegalArgumentException("Error: category harus salah satu dari " + VALID_CATEGORIES);
        this.category = category;
    }

    public int getPublicationYear() { return publicationYear; }
    public void setPublicationYear(int publicationYear) {
        if (publicationYear < 1900 || publicationYear > 2025) throw new IllegalArgumentException("Error: publicationYear harus antara 1900 - 2025");
        this.publicationYear = publicationYear;
    }

    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }

    public int getTotalCopies() { return totalCopies; }
    public void setTotalCopies(int totalCopies) {
        if (totalCopies < 0) throw new IllegalArgumentException("Error: totalCopies harus >= 0");
        this.totalCopies = totalCopies;
        // ensure availableCopies not exceed totalCopies
        if (this.availableCopies > totalCopies) this.availableCopies = totalCopies;
        this.isAvailable = this.availableCopies > 0;
    }

    public int getAvailableCopies() { return availableCopies; }
    public void setAvailableCopies(int availableCopies) {
        if (availableCopies < 0) throw new IllegalArgumentException("Error: availableCopies harus >= 0");
        if (availableCopies > this.totalCopies) throw new IllegalArgumentException("Error: availableCopies tidak boleh > totalCopies");
        this.availableCopies = availableCopies;
        this.isAvailable = availableCopies > 0;
    }
=======
package PACKAGE_NAME;

public class Book {
>>>>>>> e0f232aa685968f553a1e4a3ac2775f68a570afd
}
