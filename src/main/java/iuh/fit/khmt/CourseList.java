package iuh.fit.khmt;
import java.util.Arrays;

/**
 * Lớp mô tả danh sách các khóa học trong trường học.
 * Cung cấp các thao tác thêm, xóa, tìm kiếm, sắp xếp.
 */
public class CourseList {
    private Course[] courses;
    private int count;

    /**
     * Khởi tạo danh sách khóa học.
     * @param capacity kích thước ban đầu (> 0)
     */
    public CourseList(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Length of the array must be greater than 0");
        }
        courses = new Course[capacity];
        count = 0;
    }

    /**
     * Thêm một khóa học vào danh sách.
     * Nếu id đã tồn tại thì không thêm.
     * @param c khóa học cần thêm
     * @return true nếu thêm thành công, false nếu trùng id
     */
    public boolean addCourse(Course c) {
        if (exists(c)) {
            return false;
        }
        if (count >= courses.length) {
            courses = Arrays.copyOf(courses, courses.length * 2);
        }
        courses[count++] = c;
        return true;
    }

    /**
     * Kiểm tra khóa học có tồn tại trong danh sách chưa.
     * @param c khóa học cần kiểm tra
     * @return true nếu tồn tại
     */
    public boolean exists(Course c) {
        return searchCourseById(c.getId()) != null;
    }

    /**
     * Lấy toàn bộ danh sách khóa học.
     * @return mảng Course
     */
    public Course[] getCourses() {
        return Arrays.copyOf(courses, count);
    }

    /**
     * Xóa một khóa học theo ID.
     * @param id mã khóa học
     * @return true nếu xóa thành công, false nếu không tìm thấy
     */
    public boolean removeCourse(String id) {
        for (int i = 0; i < count; i++) {
            if (courses[i].getId().equals(id)) {
                for (int j = i; j < count - 1; j++) {
                    courses[j] = courses[j + 1];
                }
                courses[--count] = null;
                return true;
            }
        }
        return false;
    }

    /**
     * Tìm kiếm khóa học theo ID.
     * @param id mã khóa học
     * @return Course nếu tìm thấy, ngược lại null
     */
    public Course searchCourseById(String id) {
        for (int i = 0; i < count; i++) {
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
    public Course[] searchCourse(String keyword) {
        Course[] result = new Course[count];
        int found = 0;
        for (int i = 0; i < count; i++) {
            if (courses[i].getTitle().toLowerCase().contains(keyword.toLowerCase())) {
                result[found++] = courses[i];
            }
        }
        return found > 0 ? Arrays.copyOf(result, found) : null;
    }

    /**
     * Tìm kiếm khóa học theo khoa phụ trách.
     * @param dep khoa cần tìm
     * @return mảng Course nếu có, ngược lại null
     */
    public Course[] searchCourseByDepartment(String dep) {
        Course[] result = new Course[count];
        int found = 0;
        for (int i = 0; i < count; i++) {
            if (courses[i].getDepartment().equalsIgnoreCase(dep)) {
                result[found++] = courses[i];
            }
        }
        return found > 0 ? Arrays.copyOf(result, found) : null;
    }

    /**
     * Sắp xếp các khóa học theo tên.
     * @return mảng Course đã sắp xếp
     */
    public Course[] sortCourses() {
        Course[] sorted = Arrays.copyOf(courses, count);
        Arrays.sort(sorted, (a, b) -> a.getTitle().compareToIgnoreCase(b.getTitle()));
        return sorted;
    }

    /**
     * Tìm các khóa học có số tín chỉ lớn nhất.
     * @return mảng Course có tín chỉ lớn nhất
     */
    public Course[] findMaxCreditCourses() {
        if (count == 0) return null;
        int max = courses[0].getCredit();
        for (int i = 1; i < count; i++) {
            if (courses[i].getCredit() > max) {
                max = courses[i].getCredit();
            }
        }
        Course[] result = new Course[count];
        int found = 0;
        for (int i = 0; i < count; i++) {
            if (courses[i].getCredit() == max) {
                result[found++] = courses[i];
            }
        }
        return Arrays.copyOf(result, found);
    }

    /**
     * Tìm khoa có nhiều khóa học nhất.
     * @return tên khoa
     */
    public String findDepartmentWithMostCourse() {
        if (count == 0) return null;
        String[] deps = new String[count];
        int[] counts = new int[count];
        int uniqueDep = 0;

        for (int i = 0; i < count; i++) {
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
