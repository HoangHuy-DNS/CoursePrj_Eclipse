/**
 * @description TestCourse.java - This is Hoang Huy's code
 * @author Hoang Huy
 * @version 1.0
 * @created Aug 20, 2025 9:28:29 AM
 */

package iuh.fit.khmt;
import java.util.Scanner;
/**
 * Lớp chạy thử (test) cho Course và CourseList.
 * Cung cấp menu thao tác với danh sách khóa học:
 * thêm, xóa, tìm kiếm, sắp xếp, hiển thị, ...
 */
public class TestCourse {

    /**
     * Phương thức main - hiển thị menu và cho phép người dùng thao tác
     * với danh sách các khóa học.
     *
     * Các chức năng menu:
     * <ul>
     *   <li>1 - Thêm khóa học</li>
     *   <li>2 - Hiển thị toàn bộ khóa học</li>
     *   <li>3 - Xóa khóa học theo ID</li>
     *   <li>4 - Tìm khóa học theo ID</li>
     *   <li>5 - Tìm khóa học theo tên (tương đối)</li>
     *   <li>6 - Tìm khóa học theo khoa</li>
     *   <li>7 - Sắp xếp khóa học theo tên</li>
     *   <li>8 - Tìm khóa học có tín chỉ lớn nhất</li>
     *   <li>9 - Tìm khoa có nhiều khóa học nhất</li>
     *   <li>0 - Thoát chương trình</li>
     * </ul>
     *
     * @param args tham số dòng lệnh
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CourseList courseList = new CourseList(5);

        while (true) {
            System.out.println("\n===== COURSE MENU =====");
            System.out.println("1. Add course");
            System.out.println("2. Display all courses");
            System.out.println("3. Remove course by ID");
            System.out.println("4. Find course by ID");
            System.out.println("5. Search courses by title");
            System.out.println("6. Find courses by department");
            System.out.println("7. Sort courses by title");
            System.out.println("8. Find courses with max credit");
            System.out.println("9. Find department with most courses");
            System.out.println("0. Exit");
            System.out.print("Choose: ");
            int choice = sc.nextInt(); sc.nextLine();

            switch (choice) {
            case 1:
                try {
                    System.out.print("Enter ID: ");
                    String id = sc.nextLine();

                    // kiểm tra trùng ID trước khi nhập tiếp
                    if (courseList.findById(id) != null) {
                        System.out.println("Error: Course with ID " + id + " already exists.");
                        break;
                    }

                    System.out.print("Enter Title: ");
                    String title = sc.nextLine();
                    System.out.print("Enter Credit: ");
                    int credit = sc.nextInt(); sc.nextLine();
                    System.out.print("Enter Department: ");
                    String dep = sc.nextLine();

                    Course c = new Course(id, title, credit, dep);
                    courseList.addCourse(c);

                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;

                case 2:
                    System.out.printf("%-10s %-20s %-10s %-15s%n", "ID", "Title", "Credit", "Department");
                    for (Course c : courseList.getAllCourses()) {
                        System.out.println(c);
                    }
                    break;
                case 3:
                    System.out.print("Enter ID to remove: ");
                    String rid = sc.nextLine();
                    courseList.removeById(rid);
                    break;
                case 4:
                    System.out.print("Enter ID to find: ");
                    String fid = sc.nextLine();
                    Course found = courseList.findById(fid);
                    System.out.println(found != null ? found : "Not found");
                    break;
                case 5:
                    System.out.print("Enter keyword: ");
                    String key = sc.nextLine();
                    Course[] results = courseList.searchByTitle(key);
                    if (results != null) {
                        for (Course c : results) System.out.println(c);
                    } else System.out.println("No courses found");
                    break;
                case 6:
                    System.out.print("Enter department: ");
                    String dep = sc.nextLine();
                    Course[] byDep = courseList.findByDepartment(dep);
                    if (byDep != null) {
                        for (Course c : byDep) System.out.println(c);
                    } else System.out.println("No courses found");
                    break;
                case 7:
                    for (Course c : courseList.sortByTitle()) System.out.println(c);
                    break;
                case 8:
                    for (Course c : courseList.findMaxCredit()) System.out.println(c);
                    break;
                case 9:
                    System.out.println("Department with most courses: " + courseList.departmentWithMostCourses());
                    break;
                case 0:
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

}