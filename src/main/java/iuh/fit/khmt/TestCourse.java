package iuh.fit.khmt;
import java.util.Scanner;

public class TestCourse {

    // Hàm initData để thêm dữ liệu mẫu
    private static void initData(CourseList courseList) {
        courseList.addCourse(new Course("C001", "Java Programming", 3, "IT"));
        courseList.addCourse(new Course("C002", "Database Systems", 4, "IT"));
        courseList.addCourse(new Course("C003", "English 1", 2, "Languages"));
        courseList.addCourse(new Course("C004", "Calculus", 3, "Math"));
        courseList.addCourse(new Course("C005", "Physics", 3, "Science"));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CourseList courseList = new CourseList(5);
        initData(courseList); // load sẵn vài course mẫu

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

                        if (courseList.searchCourseById(id) != null) {
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
                        if (courseList.addCourse(c)) {
                            System.out.println("Course added successfully.");
                        } else {
                            System.out.println("Error: Could not add course.");
                        }

                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 2:
                    System.out.printf("%-10s %-20s %-10s %-15s%n", "ID", "Title", "Credit", "Department");
                    for (Course c : courseList.getCourses()) {
                        System.out.println(c);
                    }
                    break;

                case 3:
                    System.out.print("Enter ID to remove: ");
                    String rid = sc.nextLine();
                    if (courseList.removeCourse(rid)) {
                        System.out.println("Removed successfully.");
                    } else {
                        System.out.println("Course not found.");
                    }
                    break;

                case 4:
                    System.out.print("Enter ID to find: ");
                    String fid = sc.nextLine();
                    Course found = courseList.searchCourseById(fid);
                    System.out.println(found != null ? found : "Not found");
                    break;

                case 5:
                    System.out.print("Enter keyword: ");
                    String key = sc.nextLine();
                    Course[] results = courseList.searchCourse(key);
                    if (results != null) {
                        for (Course c : results) System.out.println(c);
                    } else System.out.println("No courses found");
                    break;

                case 6:
                    System.out.print("Enter department: ");
                    String dep = sc.nextLine();
                    Course[] byDep = courseList.searchCourseByDepartment(dep);
                    if (byDep != null) {
                        for (Course c : byDep) System.out.println(c);
                    } else System.out.println("No courses found");
                    break;

                case 7:
                    for (Course c : courseList.sortCourses()) System.out.println(c);
                    break;

                case 8:
                    for (Course c : courseList.findMaxCreditCourses()) System.out.println(c);
                    break;

                case 9:
                    System.out.println("Department with most courses: " + courseList.findDepartmentWithMostCourse());
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
