// 5	Problem Statement: Student Record Management System in Student ID Order
// Objective: Develop a Student Record Management System in C using a singly linked list to maintain student records. The system should support efficient management of records with operations that maintain the list in ascending order of Student ID, achieving a time complexity of  O(nlogn) in all cases.
// Description:
// Managing student records efficiently is essential in educational institutions. This project involves designing a system that maintains a sorted list of student records using a singly linked list. Each record includes a student’s ID, name, and grades. Operations should maintain the records in sorted order to facilitate quick retrieval and ensure easy navigation through the list.
// Requirements:
// The Student Record Management System should perform the following operations:
// 1.	Add New Student Record
// o	Insert a new student record into the linked list while keeping the list sorted by Student ID.
// o	Each student record should contain:
// 	Student ID: A unique integer identifier for each student.
// 	Name: The full name of the student.
// 	Grades: The grades or scores obtained by the student.
// o	Ensure that each insertion maintains the list's order in O(nlog⁡n)O(n \log n)O(nlogn) time.
// 2.	Delete Student Record by ID
// o	Remove a student record from the list based on the Student ID.
// o	Adjust the linked list pointers appropriately to maintain the sorted order and list continuity after deletion.
// 3.	Search for Student Record by ID
// o	Search for a student record by Student ID.
// o	Display the student’s ID, name, and grades if found; otherwise, notify that the record is not found.
// 4.	Display All Student Records
// o	Traverse the linked list and display all student records in ascending order of Student ID.
// o	For each record, display the Student ID, Name, and Grades to provide a comprehensive view of the students.
// 5.	Additional Features (Optional)
// o	Update Student Grades: Allow updates to a student’s grades based on their Student ID.
// o	Count Total Students: Keep track of the total number of students in the list.
// o	Find Top Student: Identify the student with the highest grade in the list.





import java.util.Scanner;

class Student {
    int studentID;
    String studentName;
    float studentGrade;
    Student nextStudent;

    public Student(int id, String name, float grade) {
        this.studentID = id;
        this.studentName = name;
        this.studentGrade = grade;
        this.nextStudent = null;
    }
}

class StudentRecordManagement {
    private Student studentListHead = null;

    // Add a student record in sorted order
    public void addStudentRecord(int id, String name, float grade) {
        Student newStudent = new Student(id, name, grade);

        if (studentListHead == null || studentListHead.studentID > id) {
            newStudent.nextStudent = studentListHead;
            studentListHead = newStudent;
        } else {
            Student current = studentListHead;
            while (current.nextStudent != null && current.nextStudent.studentID < id) {
                current = current.nextStudent;
            }
            newStudent.nextStudent = current.nextStudent;
            current.nextStudent = newStudent;
        }
        System.out.println("Student record successfully added.");
    }

    // Remove a student record by ID
    public void removeStudentRecord(int id) {
        if (studentListHead == null) {
            System.out.println("No student records available to delete.");
            return;
        }

        if (studentListHead.studentID == id) {
            studentListHead = studentListHead.nextStudent;
            System.out.println("Student record successfully removed.");
            return;
        }

        Student current = studentListHead;
        while (current.nextStudent != null && current.nextStudent.studentID != id) {
            current = current.nextStudent;
        }

        if (current.nextStudent == null) {
            System.out.println("Student record with ID " + id + " not found.");
        } else {
            current.nextStudent = current.nextStudent.nextStudent;
            System.out.println("Student record successfully removed.");
        }
    }

    // Find and display a student record by ID
    public void findStudentRecord(int id) {
        Student current = studentListHead;
        while (current != null) {
            if (current.studentID == id) {
                System.out.printf("Student Found: ID = %d, Name = %s, Grade = %.2f%n",
                        current.studentID, current.studentName, current.studentGrade);
                return;
            }
            current = current.nextStudent;
        }
        System.out.println("No student record found with ID " + id + ".");
    }

    // Display all student records
    public void displayAllRecords() {
        if (studentListHead == null) {
            System.out.println("No student records available.");
            return;
        }

        Student current = studentListHead;
        System.out.println("Student Records:");
        while (current != null) {
            System.out.printf("ID: %d, Name: %s, Grade: %.2f%n",
                    current.studentID, current.studentName, current.studentGrade);
            current = current.nextStudent;
        }
    }
}

public class student_record_management {
    public static void main(String[] args) {
        StudentRecordManagement srm = new StudentRecordManagement();
        Scanner scanner = new Scanner(System.in);

        int choice;
        do {
            System.out.println("\n=== Student Records Management System ===");
            System.out.println("1. Add Student Record");
            System.out.println("2. Remove Student Record");
            System.out.println("3. Find Student Record");
            System.out.println("4. Display All Records");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Student ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Student Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Student Grade: ");
                    float grade = scanner.nextFloat();
                    srm.addStudentRecord(id, name, grade);
                    break;

                case 2:
                    System.out.print("Enter Student ID to remove: ");
                    id = scanner.nextInt();
                    srm.removeStudentRecord(id);
                    break;

                case 3:
                    System.out.print("Enter Student ID to find: ");
                    id = scanner.nextInt();
                    srm.findStudentRecord(id);
                    break;

                case 4:
                    srm.displayAllRecords();
                    break;

                case 5:
                    System.out.println("Exiting the system. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (choice != 5);

        scanner.close();
    }
}

