import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

class StudentGUI {
    String name;
    int marks;

    StudentGUI(String name, int marks) {
        this.name = name;
        this.marks = marks;
    }
}

public class GradeTrackerGUI extends JFrame {

    ArrayList<StudentGUI> students = new ArrayList<>();
    JTextArea outputArea;

    public GradeTrackerGUI() {

        setTitle("Student Grade Tracker");
        setSize(400, 400);
        setLayout(new FlowLayout());

        JTextField nameField = new JTextField(10);
        JTextField marksField = new JTextField(5);
        JButton addButton = new JButton("Add Student");
        JButton reportButton = new JButton("Generate Report");

        outputArea = new JTextArea(15, 30);

        add(new JLabel("Name:"));
        add(nameField);
        add(new JLabel("Marks:"));
        add(marksField);
        add(addButton);
        add(reportButton);
        add(new JScrollPane(outputArea));

        addButton.addActionListener(e -> {
            String name = nameField.getText();
            int marks = Integer.parseInt(marksField.getText());
            students.add(new StudentGUI(name, marks));
            nameField.setText("");
            marksField.setText("");
        });

        reportButton.addActionListener(e -> generateReport());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    void generateReport() {
        int total = 0, high = Integer.MIN_VALUE, low = Integer.MAX_VALUE;

        outputArea.setText("");

        for (StudentGUI s : students) {
            total += s.marks;
            high = Math.max(high, s.marks);
            low = Math.min(low, s.marks);
            outputArea.append(s.name + " : " + s.marks + "\n");
        }

        double avg = (double) total / students.size();

        outputArea.append("\nAverage: " + avg);
        outputArea.append("\nHighest: " + high);
        outputArea.append("\nLowest: " + low);
    }

    public static void main(String[] args) {
        new GradeTrackerGUI();
    }
} 