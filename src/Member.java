<<<<<<< HEAD
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class Member {
    private String memberId;
    private String name;
    private String email;
    private String phoneNumber;
    private int registrationYear;
    private String membershipType;

    private static int counter = 0;
    private static final List<String> VALID_TYPES = Arrays.asList("Silver", "Gold", "Platinum");
    public static int totalMembers = 0;

    // Default constructor -> generate auto memberId (MBR###)
    public Member() {
        counter++;
        this.memberId = String.format("UNAI%03d", counter);
        totalMembers++;
    }

    // Parameterized constructor
    public Member(String name, String email, String phoneNumber, int registrationYear, String membershipType) {
        this(); // generate ID and increment totals
        setName(name);
        setEmail(email);
        setPhoneNumber(phoneNumber);
        setRegistrationYear(registrationYear);
        setMembershipType(membershipType);
    }

    // Display info
    public void displayInfo() {
        System.out.println("[" + memberId + "] " + name);
        System.out.println("Email         : " + email);
        System.out.println("Phone         : " + phoneNumber);
        System.out.println("Membership    : " + membershipType + membershipStars());
        System.out.println("Tahun Daftar  : " + registrationYear);
        System.out.println("Durasi Member : " + getMembershipDuration() + " tahun");
        System.out.println("Batas Pinjam  : " + getMaxBorrowLimit() + " buku");
        System.out.println("Diskon Denda  : " + (int)(getMembershipDiscount()*100) + "%");
        System.out.println("--------------------------------------------");
    }

    private String membershipStars() {
        switch (membershipType) {
            case "Platinum": return " ⭐⭐⭐";
            case "Gold": return " ⭐⭐";
            case "Silver": return " ⭐";
            default: return "";
        }
    }

    // Upgrade membership valid sequence Silver -> Gold -> Platinum
    public void upgradeMembership(String newType) {
        validateMembershipType(newType);
        if (this.membershipType == null) {
            this.membershipType = newType;
            return;
        }
        int currentRank = rank(this.membershipType);
        int newRank = rank(newType);
        if (newRank > currentRank) {
            this.membershipType = newType;
            System.out.println("✓ " + name + " berhasil di-upgrade menjadi " + newType + "!");
        } else {
            System.out.println("✗ Upgrade gagal: newType harus lebih tinggi dari current membership.");
        }
    }

    private int rank(String type) {
        switch (type) {
            case "Silver": return 1;
            case "Gold": return 2;
            case "Platinum": return 3;
            default: return 0;
        }
    }

    public int getMaxBorrowLimit() {
        switch (membershipType) {
            case "Platinum": return 10;
            case "Gold": return 7;
            case "Silver": return 5;
            default: return 0;
        }
    }

    public int getMembershipDuration() {
        int currentYear = LocalDate.now().getYear();
        return Math.max(0, currentYear - registrationYear);
    }

    public double getMembershipDiscount() {
        switch (membershipType) {
            case "Platinum": return 0.50;
            case "Gold": return 0.30;
            case "Silver": return 0.10;
            default: return 0.0;
        }
    }

    public static int getTotalMembers() {
        return totalMembers;
    }

    // Getters & Setters with validations
    public String getMemberId() { return memberId; }

    public String getName() { return name; }
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) throw new IllegalArgumentException("Error: name tidak boleh kosong");
        this.name = name.trim();
    }

    public String getEmail() { return email; }
    public void setEmail(String email) {
        if (email == null || !(email.contains("@") && email.contains("."))) throw new IllegalArgumentException("Error: email tidak valid (harus mengandung @ dan .)");
        this.email = email.trim();
    }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber == null) throw new IllegalArgumentException("Error: phoneNumber tidak boleh null");
        String digits = phoneNumber.replaceAll("\\D", "");
        if (digits.length() < 10 || digits.length() > 13) throw new IllegalArgumentException("Error: Nomor telepon harus 10-13 digit");
        this.phoneNumber = phoneNumber;
    }

    public int getRegistrationYear() { return registrationYear; }
    public void setRegistrationYear(int registrationYear) {
        if (registrationYear < 2015 || registrationYear > 2025) throw new IllegalArgumentException("Error: registrationYear harus antara 2015 - 2025");
        this.registrationYear = registrationYear;
    }

    public String getMembershipType() { return membershipType; }
    public void setMembershipType(String membershipType) {
        validateMembershipType(membershipType);
        this.membershipType = membershipType;
    }

    private void validateMembershipType(String membershipType) {
        if (membershipType == null || !VALID_TYPES.contains(membershipType)) throw new IllegalArgumentException("Error: membershipType harus Silver/Gold/Platinum");
    }
=======
package PACKAGE_NAME;

public class Member {
>>>>>>> e0f232aa685968f553a1e4a3ac2775f68a570afd
}
