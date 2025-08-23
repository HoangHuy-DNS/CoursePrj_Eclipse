/**
 * @description CourseList.java - This is Hoang Huy's code
 * @author Hoang Huy
 * @version 1.0
 * @created Aug 20, 2025 9:28:17 AM
 */

package iuh.fit.khmt;
import java.util.Arrays;
/**
 * Lớp mô tả danh sách các khóa học trong trường học.
 * Cung cấp các thao tác thêm, xóa, tìm kiếm, sắp xếp.
 */
public class CourseList {
    private Course[] courses;
    private int size;

    /**
     * Khởi tạo danh sách khóa học.
     * @param capacity kích thước ban đầu (> 0)
     */
    public CourseList(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Length of the array must be greater than 0");
        }
        courses = new Course[capacity];
        size = 0;
    }

    /**
     * Thêm một khóa học vào danh sách.
     * Nếu id đã tồn tại thì không thêm.
     * @param c khóa học cần thêm
     */
    public void addCourse(Course c) {
        if (findById(c.getId()) != null) {
            System.out.println("Error: Course with ID " + c.getId() + " already exists.");
            return;
        }
        if (size >= courses.length) {
            courses = Arrays.copyOf(courses, courses.length * 2); // tăng mảng động
        }
        courses[size++] = c;
    }

    /**
     * Lấy toàn bộ danh sách khóa học.
     * @return mảng Course
     */
    public Course[] getAllCourses() {
        return Arrays.copyOf(courses, size);
    }

    /**
     * Xóa một khóa học theo ID.
     * @param id mã khóa học
     */
    public void removeById(String id) {
        for (int i = 0; i < size; i++) {
            if (courses[i].getId().equals(id)) {
                for (int j = i; j < size - 1; j++) {
                    courses[j] = courses[j + 1];
                }
                courses[--size] = null;
                System.out.println("Removed course with ID " + id);
                return;
            }
        }
        System.out.println("Error: Course ID not found.");
    }

    /**
     * Tìm kiếm khóa học theo ID.
     * @param id mã khóa học
     * @return Course nếu tìm thấy, ngược lại null
     */
    public Course findById(String id) {
        for (int i = 0; i < size; i++) {
            if (courses[i].getId().equals(id)) {
                return courses[i];
            }
        }
        return null;
    }

    /**
     * Tìm kiếm khóa học theo tên (tương đối).
     * @param keyword từ khóa cần tìm
     * @return mảng Course nếu có, ngược lại null
     */
    public Course[] searchByTitle(String keyword) {
        Course[] result = new Course[size];
        int count = 0;
        for (int i = 0; i < size; i++) {
            if (courses[i].getTitle().toLowerCase().contains(keyword.toLowerCase())) {
                result[count++] = courses[i];
            }
        }
        return count > 0 ? Arrays.copyOf(result, count) : null;
    }

    /**
     * Tìm kiếm khóa học theo khoa phụ trách.
     * @param dep khoa cần tìm
     * @return mảng Course nếu có, ngược lại null
     */
    public Course[] findByDepartment(String dep) {
        Course[] result = new Course[size];
        int count = 0;
        for (int i = 0; i < size; i++) {
            if (courses[i].getDepartment().equalsIgnoreCase(dep)) {
                result[count++] = courses[i];
            }
        }
        return count > 0 ? Arrays.copyOf(result, count) : null;
    }

    /**
     * Sắp xếp các khóa học theo tên.
     * @return mảng Course đã sắp xếp
     */
    public Course[] sortByTitle() {
        Course[] sorted = Arrays.copyOf(courses, size);
        Arrays.sort(sorted, (a, b) -> a.getTitle().compareToIgnoreCase(b.getTitle()));
        return sorted;
    }

    /**
     * Tìm các khóa học có số tín chỉ lớn nhất.
     * @return mảng Course có tín chỉ lớn nhất
     */
    public Course[] findMaxCredit() {
        if (size == 0) return null;
        int max = courses[0].getCredit();
        for (int i = 1; i < size; i++) {
            if (courses[i].getCredit() > max) {
                max = courses[i].getCredit();
            }
        }
        Course[] result = new Course[size];
        int count = 0;
        for (int i = 0; i < size; i++) {
            if (courses[i].getCredit() == max) {
                result[count++] = courses[i];
            }
        }
        return Arrays.copyOf(result, count);
    }

    /**
     * Tìm khoa có nhiều khóa học nhất.
     * @return tên khoa
     */
    public String departmentWithMostCourses() {
        if (size == 0) return null;
        String[] deps = new String[size];
        int[] counts = new int[size];
        int uniqueDep = 0;

        for (int i = 0; i < size; i++) {
            String dep = courses[i].getDepartment();
            int idx = -1;
            for (int j = 0; j < uniqueDep; j++) {
                if (deps[j].equalsIgnoreCase(dep)) {
                    idx = j;
                    break;
                }
            }
            if (idx == -1) {
                deps[uniqueDep] = dep;
                counts[uniqueDep] = 1;
                uniqueDep++;
            } else {
                counts[idx]++;
            }
        }

        int max = counts[0], maxIndex = 0;
        for (int i = 1; i < uniqueDep; i++) {
            if (counts[i] > max) {
                max = counts[i];
                maxIndex = i;
            }
        }
        return deps[maxIndex];
    }

}