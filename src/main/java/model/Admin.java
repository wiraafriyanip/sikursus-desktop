package model;

/** Latihan mandiri pertemuan 6: subtype baru tanpa mengubah loop List<Orang>. */
public class Admin extends Orang {
    private String role;

    public Admin(int id, String nama, String noHp, String role) {
        super(id, nama, noHp);
        setRole(role);
    }

    public String getRole() { return role; }

    public void setRole(String role) {
        if (role == null || role.trim().isEmpty())
            throw new IllegalArgumentException("Role wajib diisi");
        this.role = role.trim();
    }

    @Override
    public String getInfo() {
        return "ADMIN | " + super.getInfo() + " | Role: " + getRole();
    }
}
