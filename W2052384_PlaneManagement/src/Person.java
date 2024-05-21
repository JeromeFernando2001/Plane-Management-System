public class Person {
    private String firstname;
    private String surname;
    private String email;

    // Default constructor
    public Person() {

    }

    //Creating the parameterized constructor
    public Person(String firstname, String surname, String email) {
        this.firstname = firstname;
        this.surname = surname;
        this.email = email;
    }

    //Getters and Setters
    public String getName() {
        return firstname;
    }

    public void setFirstnameame(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    //methods to print the information
    public void printPersonInfo() {
        System.out.println("Full Name: " + firstname + " " + surname);
        System.out.println("Email: " + email);
    }

    public String getPersonInfo() {
        return "Full Name: " + firstname + " " + surname + "\n" + "Email: " + email + "\n";
    }
}
