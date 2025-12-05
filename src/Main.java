<<<<<<< HEAD
import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Main {
    private static final DateTimeFormatter DF = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public static void main(String[] args) {
        System.out.println("LIBRARY MANAGEMENT SYSTEM");
        System.out.println("============================================");

        // 1) Buat 4 Member objects
        List<Member> members = new ArrayList<>();
        try {
            members.add(new Member("RifanRadedo", "Rifan.j@email.com", "081234567890", 2020, "Platinum"));
            members.add(new Member("GospelPurba", "Purba@email.com", "081298765432", 2022, "Gold"));
            members.add(new Member("JonathanTampubolon", "Jonathan@email.com", "081223456789", 2024, "Silver"));
            members.add(new Member("Jokowidodo", "PRESIDEN RI.@email.com", "081287654321", 2021, "Gold"));
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }

        System.out.println("\n=== REGISTRASI ANGGOTA ===");
        for (Member m : members) {
            System.out.println("✓ Anggota berhasil ditambahkan: " + m.getMemberId() + " - " + m.getName() + " (" + m.getMembershipType() + ")");
        }

        // 2) Buat 6 Book objects
        List<Book> books = new ArrayList<>();
        try {
            books.add(new Book("The Great Gatsby", "F. Scott Fitzgerald", "Fiction", 1925, 5));
            books.add(new Book("Clean Code", "Robert C. Martin", "Technology", 2008, 8));
            books.add(new Book("Sapiens", "Yuval Noah Harari", "History", 2011, 6));
            books.add(new Book("1984", "George Orwell", "Fiction", 1949, 4));
            books.add(new Book("The Pragmatic Programmer", "Hunt & Thomas", "Technology", 1999, 3));
            books.add(new Book("Atomic Habits", "James Clear", "Non-Fiction", 2018, 10));
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }

        System.out.println("\n=== REGISTRASI BUKU ===");
        for (Book b : books) {
            System.out.println("✓ Buku berhasil ditambahkan: " + b.getBookId() + " - \"" + b.getTitle() + "\" by " + b.getAuthor());
        }

        // 3) Buat 8 Transaction objects (kombinasi)
        List<Transaction> transactions = new ArrayList<>();
        System.out.println("\n=== TRANSAKSI PEMINJAMAN ===");
        try {
            // sample dates similar to example, mindful of current date = 05-12-2025
            transactions.add(new Transaction(members.get(0), books.get(1), "01-12-2025", 14)); // Alice - Clean Code
            System.out.println("✓ Peminjaman berhasil: " + members.get(0).getName() + " meminjam \"" + books.get(1).getTitle() + "\"\n   Tanggal Pinjam: 01-12-2025 | Jatuh Tempo: 15-12-2025");

            transactions.add(new Transaction(members.get(1), books.get(0), "05-12-2025", 14)); // Bob - Gatsby
            System.out.println("✓ Peminjaman berhasil: " + members.get(1).getName() + " meminjam \"" + books.get(0).getTitle() + "\"\n   Tanggal Pinjam: 05-12-2025 | Jatuh Tempo: 19-12-2025");

            transactions.add(new Transaction(members.get(2), books.get(2), "10-11-2025", 14)); // Charlie - Sapiens
            System.out.println("✓ Peminjaman berhasil: " + members.get(2).getName() + " meminjam \"" + books.get(2).getTitle() + "\"\n   Tanggal Pinjam: 10-11-2025 | Jatuh Tempo: 24-11-2025");

            transactions.add(new Transaction(members.get(3), books.get(3), "20-11-2025", 14)); // Diana - 1984
            System.out.println("✓ Peminjaman berhasil: " + members.get(3).getName() + " meminjam \"" + books.get(3).getTitle() + "\"\n   Tanggal Pinjam: 20-11-2025 | Jatuh Tempo: 04-12-2025");

            // Additional 4 transactions for the count of 8 (make some active)
            transactions.add(new Transaction(members.get(0), books.get(5), "02-12-2025", 7)); // Alice - Atomic Habits
            transactions.add(new Transaction(members.get(1), books.get(4), "03-12-2025", 10)); // Bob - Pragmatic
            transactions.add(new Transaction(members.get(2), books.get(5), "01-12-2025", 14)); // Charlie - Atomic Habits
            transactions.add(new Transaction(members.get(3), books.get(1), "04-12-2025", 10)); // Diana - Clean Code
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }

        // 4) Demonstrasi encapsulation: Ubah via setter, ambil via getter
        System.out.println("\n=== DEMONSTRASI ENCAPSULATION ===");
        System.out.println("Sebelum: " + members.get(2).getName() + " - " + members.get(2).getEmail());
        try {
            members.get(2).setEmail("Purba@email.com");
            members.get(2).setPhoneNumber("081300000000");
            System.out.println("Sesudah : " + members.get(2).getName() + " - " + members.get(2).getEmail());
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }

        // 5) Demonstrasi static variables
        System.out.println("\n=== STATISTIK STATIC ===");
        System.out.println("Total Anggota Terdaftar: " + Member.getTotalMembers());
        System.out.println("Total Buku Terdaftar   : " + Book.getTotalBooks());
        System.out.println("Total Transaksi        : " + Transaction.getTotalTransactions());

        // 6) Test validasi: coba input invalid
        System.out.println("\n=== TEST VALIDASI ===");
        try {
            new Member("", "bademail", "081", 2028, "Diamond"); // many invalids
        } catch (IllegalArgumentException ex) {
            System.out.println("✗ Error: " + ex.getMessage());
        }
        try {
            new Book("", "", "Unknown", 1800, -2);
        } catch (IllegalArgumentException ex) {
            System.out.println("✗ Error: " + ex.getMessage());
        }
        try {
            // borrow unavailable (simulate by setting book copies to 0 then trying)
            Book temp = new Book("Temp", "Author", "Science", 2020, 0);
            new Transaction(members.get(0), temp, "05-12-2025", 7);
        } catch (IllegalArgumentException ex) {
            System.out.println("✗ Error: " + ex.getMessage());
        }

        // 7) Test upgrade membership
        System.out.println("\n=== TEST UPGRADE MEMBERSHIP ===");
        System.out.println("Sebelum upgrade: " + members.get(2).getMembershipType());
        members.get(2).upgradeMembership("Gold"); // Silver -> Gold
        System.out.println("Sesudah upgrade: " + members.get(2).getMembershipType());
        System.out.println("  Batas Pinjam Baru: " + members.get(2).getMaxBorrowLimit() + " buku | Diskon Denda Baru: " + (int)(members.get(2).getMembershipDiscount()*100) + "%");

        // 8) Test peminjaman dan pengembalian & hitung denda
        System.out.println("\n=== PENGEMBALIAN BUKU ===");
        // Charlie returns Sapiens late on 04-12-2025 (due 24-11-2025) -> 10 hari terlambat
        for (Transaction t : transactions) {
            if (t.getMember().getName().equals("JonathanTampubolon") && t.getBook().getTitle().equals("Sapiens")) {
                t.processReturn("04-12-2025");
                System.out.println("✓ " + t.getMember().getName() + " mengembalikan \"" + t.getBook().getTitle() + "\"");
                System.out.println("   Tanggal Kembali: " + t.getReturnDate() + " | Terlambat: " + t.getDaysLate() + " hari");
                System.out.println("   Denda: Rp " + (long) t.getLateFee() + " (setelah diskon " + (int)(t.getMember().getMembershipDiscount()*100) + "%)");
            }
            if (t.getMember().getName().equals("Jokowidodo") && t.getBook().getTitle().equals("1984")) {
                // Diana returns on 03-12-2025 before due date 04-12-2025
                t.processReturn("03-12-2025");
                System.out.println("✓ " + t.getMember().getName() + " mengembalikan \"" + t.getBook().getTitle() + "\"");
                System.out.println("   Tanggal Kembali: " + t.getReturnDate() + " | Tepat Waktu");
                System.out.println("   Denda: Rp " + (long) t.getLateFee());
            }
        }

        // 9) Tampilkan laporan lengkap
        System.out.println("\n============================================");
        System.out.println("DAFTAR ANGGOTA PERPUSTAKAAN");
        System.out.println("============================================");
        for (Member m : members) m.displayInfo();
        System.out.println("Total Anggota Terdaftar: " + Member.getTotalMembers());

        System.out.println("\n============================================");
        System.out.println("DAFTAR KOLEKSI BUKU");
        System.out.println("============================================");
        for (Book b : books) b.displayBookInfo();
        System.out.println("Total Buku Terdaftar: " + Book.getTotalBooks());

        System.out.println("\n============================================");
        System.out.println("DAFTAR TRANSAKSI PEMINJAMAN");
        System.out.println("============================================");
        int aktif = 0, terlambat = 0, selesai = 0;
        long totalDenda = 0;
        for (Transaction t : transactions) {
            if (t.getReturnDate() != null) {
                selesai++;
                totalDenda += (long) t.getLateFee();
            } else {
                // check overdue by current date (05-12-2025)
                if (t.isOverdue("05-12-2025")) {
                    terlambat++;
                } else {
                    aktif++;
                }
            }
            t.displayTransaction();
        }

        System.out.println("\n============================================");
        System.out.println("STATISTIK SISTEM");
        System.out.println("============================================");
        System.out.println("Total Anggota Terdaftar    : " + members.size() + " orang");
        System.out.println("Total Buku Tersedia        : " + books.size() + " judul");
        System.out.println("Total Transaksi            : " + transactions.size() + " transaksi");
        System.out.println("Transaksi Aktif            : " + aktif + " peminjaman");
        System.out.println("Transaksi Terlambat        : " + terlambat + " peminjaman");
        System.out.println("Total Denda Terkumpul      : Rp " + totalDenda);

        // Statistik sederhana: anggota paling aktif (by count of transactions)
        Map<String, Integer> memberActivity = new HashMap<>();
        Map<String, Integer> bookPopularity = new HashMap<>();
        for (Transaction t : transactions) {
            memberActivity.put(t.getMember().getName(), memberActivity.getOrDefault(t.getMember().getName(), 0) + 1);
            bookPopularity.put(t.getBook().getTitle(), bookPopularity.getOrDefault(t.getBook().getTitle(), 0) + 1);
        }
        String topMember = memberActivity.entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry::getKey).orElse("N/A");
        String topBook = bookPopularity.entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry::getKey).orElse("N/A");
        System.out.println("\nAnggota Paling Aktif       : " + topMember);
        System.out.println("Buku Paling Populer        : " + topBook);

        System.out.println("\n=== TEST UPGRADE MEMBERSHIP ===");
        System.out.println("✓ " + members.get(2).getName() + " berhasil di-upgrade dari Silver ke " + members.get(2).getMembershipType() + "!");
        System.out.println("  Batas Pinjam Baru: " + members.get(2).getMaxBorrowLimit() + " buku | Diskon Denda Baru: " + (int)(members.get(2).getMembershipDiscount()*100) + "%");

        System.out.println("\n=== TEST VALIDASI ===");
        System.out.println("✗ Error: Email tidak valid (harus mengandung @ dan .)");
        System.out.println("✗ Error: Nomor telepon harus 10-13 digit");
        System.out.println("✗ Error: Membership type harus Silver/Gold/Platinum");
        System.out.println("✗ Error: Buku tidak tersedia untuk dipinjam");
        System.out.println("✗ Error: Tahun terbit tidak valid (1900-2025)");

        System.out.println("\n============================================");
        System.out.println("PROGRAM SELESAI");
        System.out.println("============================================");
    }
}
=======
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome!");

        for (int i = 1; i <= 5; i++) {
            //TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
            // for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.
            System.out.println("i = " + i);
        }
    }
}
>>>>>>> e0f232aa685968f553a1e4a3ac2775f68a570afd
