public class StudentLinkedList {
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

    public static void main(String[] args) {
        StudentLinkedList list = new StudentLinkedList();
        list.addStudent(new Student(1, "Alice"));
        list.addStudent(new Student(2, "Bob"));
        list.addStudent(new Student(3, "Charlie"));

        System.out.println("Students in the list:");
        list.displayStudents();

        list.removeStudent(2);
        System.out.println("\nStudents after removing student with id 2:");
        list.displayStudents();
    }
}

class Student {
    private int id;
    private String name;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Student{id=" + id + ", name='" + name + "'}";
    }
}
