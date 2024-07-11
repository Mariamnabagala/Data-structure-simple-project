import java.util.LinkedList;
import java.util.Queue;

// Student Class
class Student {
    private final String name;
    private final int id;

    public Student(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Student{id=" + id + ", name='" + name + "'}";
    }
}

// StudentLinkedList Class
class StudentLinkedList {
    private Node head;

    private class Node {
        Student student;
        Node next;

        Node(Student student) {
            this.student = student;
            this.next = null;
        }
    }

    public void addStudent(Student student) {
        Node newNode = new Node(student);
        if (head == null) {
            head = newNode;
        } else {
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
    }

    public void removeStudent(int id) {
        if (head == null) return;

        if (head.student.getId() == id) {
            head = head.next;
            return;
        }

        Node temp = head;
        while (temp.next != null && temp.next.student.getId() != id) {
            temp = temp.next;
        }

        if (temp.next != null) {
            temp.next = temp.next.next;
        }
    }

    public void displayStudents() {
        Node temp = head;
        while (temp != null) {
            System.out.println(temp.student);
            temp = temp.next;
        }
    }
}

// RoomManagement Class
class RoomManagement {
    private final boolean[] rooms;

    public RoomManagement(int numberOfRooms) {
        rooms = new boolean[numberOfRooms];
    }

    public Integer allocateRoom() {
        for (int i = 0; i < rooms.length; i++) {
            if (!rooms[i]) {
                rooms[i] = true;
                return i + 1;  // Room number (1-based index)
            }
        }
        return null;  // No available rooms
    }

    public void deallocateRoom(int roomNumber) {
        if (roomNumber > 0 && roomNumber <= rooms.length) {
            rooms[roomNumber - 1] = false;
        }
    }

    public void displayAvailableRooms() {
        System.out.print("Available rooms: ");
        for (int i = 0; i < rooms.length; i++) {
            if (!rooms[i]) {
                System.out.print((i + 1) + " ");
            }
        }
        System.out.println();
    }
}

// WaitingList Class
class WaitingList {
    private final Queue<Student> waitingList;

    public WaitingList() {
        waitingList = new LinkedList<>();
    }

    public void addToWaitingList(Student student) {
        waitingList.add(student);
    }

    public Student removeFromWaitingList() {
        return waitingList.poll();
    }

    public void displayWaitingList() {
        System.out.println("Waiting List:");
        for (Student student : waitingList) {
            System.out.println(student);
        }
    }
}

// Main Class
public class HostelManagementSystem {
    public static void main(String[] args) {
        StudentLinkedList studentList = new StudentLinkedList();
        RoomManagement roomManagement = new RoomManagement(10);  // Assume 10 rooms
        WaitingList waitingList = new WaitingList();

        // Adding Students
        Student s1 = new Student("Alice", 1);
        Student s2 = new Student("Bob", 2);
        Student s3 = new Student("Charlie", 3);

        studentList.addStudent(s1);
        studentList.addStudent(s2);
        studentList.addStudent(s3);

        // Display Students
        System.out.println("Student List:");
        studentList.displayStudents();

        // Room Allocation
        System.out.println("\nRoom Allocation:");
        Integer room1 = roomManagement.allocateRoom();
        Integer room2 = roomManagement.allocateRoom();
        System.out.println("Room allocated to Alice: " + room1);
        System.out.println("Room allocated to Bob: " + room2);

        // Deallocate Room
        roomManagement.deallocateRoom(room1);
        System.out.println("\nAfter deallocating Room " + room1 + ":");
        roomManagement.displayAvailableRooms();

        // Waiting List
        waitingList.addToWaitingList(s3);
        System.out.println("\nWaiting List:");
        waitingList.displayWaitingList();
    }
}
