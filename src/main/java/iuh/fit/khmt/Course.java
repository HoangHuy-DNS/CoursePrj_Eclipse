/**
 * @description Course.java - This is Hoang Huy's code
 * @author Hoang Huy
 * @version 1.0
 * @created Aug 20, 2025 9:28:04 AM
 */

package iuh.fit.khmt;

/**
 * Lớp mô tả một khóa học trong trường học.
 * Bao gồm: id, title, credit, department.
 */
public class Course {
    private String id;
    private String title;
    private int credit;
    private String department;

    /**
     * Khởi tạo khóa học.
     * @param id mã khóa học (>= 3 ký tự, chỉ chữ hoặc số)
     * @param title tên khóa học (không rỗng)
     * @param credit số tín chỉ (> 0)
     * @param department khoa phụ trách (không rỗng)
     */
    public Course(String id, String title, int credit, String department) {
        setId(id);
        setTitle(title);
        setCredit(credit);
        setDepartment(department);
    }

    /**
     * Lấy mã khóa học.
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * Gán mã khóa học.
     * @param id mã khóa học (>= 3 ký tự, chỉ chữ hoặc số)
     */
    public void setId(String id) {
        if (id == null || id.length() < 3) {
            throw new IllegalArgumentException("ID must have at least 3 characters");
        }
        if (!id.matches("[a-zA-Z0-9]+")) {
            throw new IllegalArgumentException("ID must contain only letters or digits");
        }
        this.id = id;
    }

    /**
     * Lấy tên khóa học.
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gán tên khóa học.
     * @param title tên khóa học (không rỗng)
     */
    public void setTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title must not be empty");
        }
        this.title = title.trim();
    }

    /**
     * Lấy số tín chỉ.
     * @return credit
     */
    public int getCredit() {
        return credit;
    }

    /**
     * Gán số tín chỉ.
     * @param credit số tín chỉ (> 0)
     */
    public void setCredit(int credit) {
        if (credit <= 0) {
            throw new IllegalArgumentException("Credit must be greater than 0");
        }
        this.credit = credit;
    }

    /**
     * Lấy khoa phụ trách.
     * @return department
     */
    public String getDepartment() {
        return department;
    }

    /**
     * Gán khoa phụ trách.
     * @param department khoa phụ trách (không rỗng)
     */
    public void setDepartment(String department) {
        if (department == null || department.trim().isEmpty()) {
            throw new IllegalArgumentException("Department must not be empty");
        }
        this.department = department.trim();
    }

    /**
     * Hiển thị thông tin khóa học.
     * @return chuỗi mô tả khóa học
     */
    @Override
    public String toString() {
        return String.format("%-10s %-20s %-10d %-15s", id, title, credit, department);
    }

}

