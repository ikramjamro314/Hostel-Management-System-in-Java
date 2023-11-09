import java.util.ArrayList;

class Student {
    private String name;
    private long Contact;
    private long cnic;
    private Room room;

    public Student(String name, long Contact, long cnic) {
        this.name = name;
        this.Contact = Contact;
        this.cnic = cnic;
    }

    public String get_details() {
        return "Name : " +name + " || Contact : " + Contact + " || Cnic : " + " " + cnic + "\n Room No: " + room.get_Room_No();
    }

    public void set_Room(Room room) {
        this.room = room;
    }

    public Room getRoom() {
        return room;
    }

    public String getName() {
        return name;
    }

}
interface Rooms{
    void occupy();
    void vacancy();
}
class Room implements Rooms{
    private int room_no;
    private int capacity;
    private int occupancy=0;
    private boolean is_Occupied;
    Room(int room_no,int capacity,int occupancy){
        this.room_no=room_no;
        this.capacity=capacity;
        this.occupancy=occupancy;
        is_Occupied=false;
    }
    public void isIs_Occupied(boolean is_Occupied){
        this.is_Occupied=is_Occupied;
    }
    public boolean is_Available(){
        return occupancy<capacity;
    }
    public int get_Room_No(){
        return room_no;
    }
    @Override
    public void occupy() {
        if (is_Available()) {
            occupancy++;
            is_Occupied = true;
        }
    }
    @Override
    public void vacancy() {
        if (is_Occupied) {
            occupancy--;
            is_Occupied = false;
        }
    }
}

class Hostel {
    private String name;
    private int No_of_Rooms;
    private int rent_per_Student;
    private String location;
    private ArrayList<Room> rooms;
    private ArrayList<Student> students;

    Hostel(String name, String location,int rent_per_Student,int No_of_Rooms) {
        this.name = name;
        this.location = location;
        this.No_of_Rooms=No_of_Rooms;
        this.rent_per_Student=rent_per_Student;
        this.rooms = new ArrayList<>();
        this.students = new ArrayList<>();
    }

    public ArrayList<Room> getAvailableRooms() {
        ArrayList<Room> availableRooms = new ArrayList<>();
        for (Room room : rooms) {
            if (room.is_Available()) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }

    public void add_Student(Student s1) {
        students.add(s1);
    }
    public void remove_Student(Student s){
        rooms.remove(s);
    }
    public void admission_In_Hostel(Student student, Room room) {
        if (room.is_Available()) {
            student.set_Room(room);
            room.occupy();
            students.add(student);
            System.out.println( student.getName()+ " Successfully Registered in "+name);
        } else {
            System.out.println("Room is not available for : "+student.getName());
        }
    }
    public void out_from_hostel(Student student) {
        Room room = student.getRoom();
        if (room != null) {
            room.vacancy();
            student.set_Room(null);
            System.out.println(student.getName()+"Succesfully Discharged from the "+name);
            students.remove(student);
        } else {
            System.out.println("Student is not in any room.");
        }
    }
    public void print_Students_details(){
        for(Student student :  students)
            System.out.println(student.get_details());
    }
    public void Print_Hostel_Details(){
        System.out.println("Name of Hostel : " + name + " || Location : " + location + " || No Of Rooms : " + No_of_Rooms + " || Rent per Student " +rent_per_Student );
    }
}
public class Main {
    public static void main(String[] args) {

        System.out.println();
        System.out.println();
        System.out.println(" ***************** Welcome To Hostel Management System *******************");
        Hostel hostel1=new Hostel("Mashallah Boys Hostel", "Sindh Society Phase 1",10000,6);
        System.out.println();
        System.out.println(" ***************** Hotel Details *******************");
        System.out.println();
        hostel1.Print_Hostel_Details();

        Room room1=new Room(1,4,2);
        Student student1=new Student("Ikram",03131364733,455043131);
        Student student2=new Student("Yousuf",034323424,455034334);
        Student student3=new Student("Waseo",03314567,438493904);

        Room room2=new Room(2,4,2);
        Student student4 = new Student("Gotu",03771411214,45503332);

        hostel1.admission_In_Hostel(student1,room1);
        hostel1.admission_In_Hostel(student2,room1);
        hostel1.admission_In_Hostel(student3,room1);
        hostel1.admission_In_Hostel(student4,room2);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println();
        System.out.println("************* Registered Students are **************************");
        hostel1.print_Students_details();



    }
}