import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;


public class Driver {

    private final ArrayList<Student> students = new ArrayList<>();
    private final ArrayList<Course> courses = new ArrayList<>();
    private final ArrayList<Faculty> faculties = new ArrayList<>();

    // --- Table models ---
    private DefaultTableModel studentTableModel;
    private DefaultTableModel courseTableModel;
    private DefaultTableModel facultyTableModel;

    // --- Theme colors ---
    private static final Color TEAL = new Color(0, 150, 136);
    private static final Color TEAL_LIGHT = new Color(224, 242, 241);
    private static final Color PANEL_BG = Color.WHITE;
    private static final Font HEADER_FONT = new Font("SansSerif", Font.BOLD, 14);
    private static final Font NORMAL_FONT = new Font("SansSerif", Font.PLAIN, 13);

    public Driver() {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ignored) {}

        SwingUtilities.invokeLater(this::createAndShowGUI);
    }

    private void createAndShowGUI() {
        JFrame frame = new JFrame("AUST — Student Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1100, 650);
        frame.setLocationRelativeTo(null);

        //Header
        JPanel top = new JPanel(new BorderLayout());
        top.setBackground(TEAL);
        top.setBorder(new EmptyBorder(12, 12, 12, 12));
        JLabel title = new JLabel("Student Management System");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("SansSerif", Font.BOLD, 20));
        top.add(title, BorderLayout.WEST);

        frame.add(top, BorderLayout.NORTH);

        // Tabs
        JTabbedPane tabs = new JTabbedPane();
        tabs.setFont(NORMAL_FONT);
        tabs.setBorder(new EmptyBorder(8,8,8,8));

        tabs.addTab("Students", createStudentsPanel());
        tabs.addTab("Courses", createCoursesPanel());
        tabs.addTab("Faculties", createFacultiesPanel());

        frame.add(tabs, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    //students panel
    private JPanel createStudentsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(PANEL_BG);
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        studentTableModel = new DefaultTableModel(new Object[]{"ID", "First Name", "Last Name", "Father", "Gender", "Age", "Courses Done", "Credits"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // read-only table
            }
        };
        JTable table = new JTable(studentTableModel);
        styleTable(table);

        panel.add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel btnRow = new JPanel();
        btnRow.setBackground(PANEL_BG);
        btnRow.setBorder(new EmptyBorder(10,0,0,0));
        JButton addBtn = makeButton("Add Student");
        JButton deleteBtn = makeButton("Delete Student");
        JButton searchBtn = makeButton("Search Student");
        JButton refreshBtn = makeButton("Refresh");

        addBtn.addActionListener(e -> showAddStudentDialog());
        deleteBtn.addActionListener(e -> deleteStudentDialog());
        searchBtn.addActionListener(e -> searchStudentDialog());
        refreshBtn.addActionListener(e -> refreshStudentTable());

        btnRow.add(addBtn);
        btnRow.add(deleteBtn);
        btnRow.add(searchBtn);
        btnRow.add(refreshBtn);

        panel.add(btnRow, BorderLayout.SOUTH);

        return panel;
    }

    //course panel
    private JPanel createCoursesPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(PANEL_BG);
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        courseTableModel = new DefaultTableModel(new Object[]{"Course Code", "Course Name", "Credits", "Capacity", "Enrolled", "Faculty"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        JTable table = new JTable(courseTableModel);
        styleTable(table);

        panel.add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel btnRow = new JPanel();
        btnRow.setBackground(PANEL_BG);
        btnRow.setBorder(new EmptyBorder(10,0,0,0));
        JButton addBtn = makeButton("Add Course");
        JButton searchBtn = makeButton("Search Course");
        JButton refreshBtn = makeButton("Refresh");

        addBtn.addActionListener(e -> showAddCourseDialog());
        searchBtn.addActionListener(e -> searchCourseDialog());
        refreshBtn.addActionListener(e -> refreshCourseTable());

        btnRow.add(addBtn);
        btnRow.add(searchBtn);
        btnRow.add(refreshBtn);

        panel.add(btnRow, BorderLayout.SOUTH);

        return panel;
    }

    //fucuty panel
    private JPanel createFacultiesPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(PANEL_BG);
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        facultyTableModel = new DefaultTableModel(new Object[]{"Faculty Name", "Dean Name", "Location"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        JTable table = new JTable(facultyTableModel);
        styleTable(table);

        panel.add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel btnRow = new JPanel();
        btnRow.setBackground(PANEL_BG);
        btnRow.setBorder(new EmptyBorder(10,0,0,0));
        JButton addBtn = makeButton("Add Faculty");
        JButton modifyBtn = makeButton("Modify Faculty");
        JButton searchBtn = makeButton("Search Faculty");
        JButton refreshBtn = makeButton("Refresh");

        addBtn.addActionListener(e -> showAddFacultyDialog());
        modifyBtn.addActionListener(e -> modifyFacultyDialog());
        searchBtn.addActionListener(e -> searchFacultyDialog());
        refreshBtn.addActionListener(e -> refreshFacultyTable());

        btnRow.add(addBtn);
        btnRow.add(modifyBtn);
        btnRow.add(searchBtn);
        btnRow.add(refreshBtn);

        panel.add(btnRow, BorderLayout.SOUTH);

        return panel;
    }

    //Actions
    private void showAddStudentDialog() {
        JPanel p = new JPanel(new GridLayout(7,2,8,8));
        p.setBorder(new EmptyBorder(8,8,8,8));
        JTextField fname = new JTextField();
        JTextField father = new JTextField();
        JTextField lname = new JTextField();
        JRadioButton male = new JRadioButton("Male");
        JRadioButton female = new JRadioButton("Female");
        ButtonGroup g = new ButtonGroup();
        g.add(male); g.add(female);
        JTextField ageField = new JTextField();
        JTextField coursesDoneField = new JTextField();

        p.add(new JLabel("First Name:")); p.add(fname);
        p.add(new JLabel("Father Name:")); p.add(father);
        p.add(new JLabel("Last Name:")); p.add(lname);
        p.add(new JLabel("Gender:")); p.add(male);
        p.add(new JLabel("")); p.add(female);
        p.add(new JLabel("Age:")); p.add(ageField);
        p.add(new JLabel("Courses Completed:")); p.add(coursesDoneField);

        int res = JOptionPane.showConfirmDialog(null, p, "Add Student", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (res == JOptionPane.OK_OPTION) {
            // Validation
            if (fname.getText().trim().isEmpty() || lname.getText().trim().isEmpty() || father.getText().trim().isEmpty()
                    || ageField.getText().trim().isEmpty() || coursesDoneField.getText().trim().isEmpty()
                    || (!male.isSelected() && !female.isSelected())) {
                JOptionPane.showMessageDialog(null, "Please fill all fields and select gender.", "Validation", JOptionPane.WARNING_MESSAGE);
                return;
            }
            try {
                int age = Integer.parseInt(ageField.getText().trim());
                int coursesDone = Integer.parseInt(coursesDoneField.getText().trim());
                int credits = (coursesDone > 0 && coursesDone < 35) ? 105 - coursesDone * 3 : 0;
                String gender = male.isSelected() ? "Male" : "Female";

                // Use model constructor (it generates ID inside constructor)
                Student s = new Student(0, fname.getText().trim(), lname.getText().trim(), father.getText().trim(), gender, age, credits, coursesDone);
                students.add(s);
                refreshStudentTable();
                JOptionPane.showMessageDialog(null, "Student added:\n" + s.getFirstName() + " " + s.getLastName(), "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Age and Courses Completed must be valid integers.", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    
    private void deleteStudentDialog() {
        String idStr = JOptionPane.showInputDialog(null, "Enter Student ID to delete:", "Delete Student", JOptionPane.QUESTION_MESSAGE);
        if (idStr == null) return;
        try {
            int id = Integer.parseInt(idStr.trim());
            Student found = null;
            for (Student s : students) if (s.getId() == id) { found = s; break; }
            if (found != null) {
                int confirm = JOptionPane.showConfirmDialog(null, "Delete student: " + found.getFirstName() + " " + found.getLastName() + "?", "Confirm", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    students.remove(found);
                    refreshStudentTable();
                    JOptionPane.showMessageDialog(null, "Student deleted.", "Deleted", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Student not found with ID: " + id, "Not found", JOptionPane.WARNING_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Please enter a valid numeric ID.", "Invalid", JOptionPane.ERROR_MESSAGE);
        }
    }

 
    private void searchStudentDialog() {
        String idStr = JOptionPane.showInputDialog(null, "Enter Student ID to search:", "Search Student", JOptionPane.QUESTION_MESSAGE);
        if (idStr == null) return;
        try {
            int id = Integer.parseInt(idStr.trim());
            Student found = null;
            for (Student s : students) if (s.getId() == id) { found = s; break; }
            if (found != null) {
                JOptionPane.showMessageDialog(null, found.toString(), "Student Found", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Student not found with ID: " + id, "Not found", JOptionPane.WARNING_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Please enter a valid numeric ID.", "Invalid", JOptionPane.ERROR_MESSAGE);
        }
    }

  
    private void showAddCourseDialog() {
        JPanel p = new JPanel(new GridLayout(6,2,8,8));
        p.setBorder(new EmptyBorder(8,8,8,8));
        JTextField code = new JTextField();
        JTextField name = new JTextField();
        JTextField creditsField = new JTextField();
        JTextField capacityField = new JTextField();
        JComboBox<String> facultyCombo = new JComboBox<>();


        facultyCombo.addItem("No faculty (optional)");
        for (Faculty f : faculties) facultyCombo.addItem(f.getFacultyName());

        p.add(new JLabel("Course Code:")); p.add(code);
        p.add(new JLabel("Course Name:")); p.add(name);
        p.add(new JLabel("Credits:")); p.add(creditsField);
        p.add(new JLabel("Capacity:")); p.add(capacityField);
        p.add(new JLabel("Faculty:")); p.add(facultyCombo);

        int res = JOptionPane.showConfirmDialog(null, p, "Add Course", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (res == JOptionPane.OK_OPTION) {
            if (code.getText().trim().isEmpty() || name.getText().trim().isEmpty() || creditsField.getText().trim().isEmpty() || capacityField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please complete all fields.", "Validation", JOptionPane.WARNING_MESSAGE);
                return;
            }
            try {
                int credits = Integer.parseInt(creditsField.getText().trim());
                int capacity = Integer.parseInt(capacityField.getText().trim());
                
                for (Course c : courses) {
                    if (c.getCourseCode().equalsIgnoreCase(code.getText().trim()) || c.getCourseName().equalsIgnoreCase(name.getText().trim())) {
                        JOptionPane.showMessageDialog(null, "Course already exists (code or name).", "Duplicate", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                }
                Faculty selectedFaculty = null;
                String facName = (String) facultyCombo.getSelectedItem();
                if (facName != null && !facName.equals("No faculty (optional)")) {
                    for (Faculty f : faculties) if (f.getFacultyName().equals(facName)) { selectedFaculty = f; break; }
                }
                Course course = new Course(code.getText().trim(), name.getText().trim(), credits, capacity, selectedFaculty);
                courses.add(course);
                refreshCourseTable();
                JOptionPane.showMessageDialog(null, "Course added: " + course.getCourseName(), "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Credits and Capacity must be integers.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


    private void searchCourseDialog() {
        String code = JOptionPane.showInputDialog(null, "Enter Course Code to search:", "Search Course", JOptionPane.QUESTION_MESSAGE);
        if (code == null) return;
        String q = code.trim();
        if (q.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter a code.", "Input", JOptionPane.WARNING_MESSAGE);
            return;
        }
        Course found = null;
        for (Course c : courses) if (c.getCourseCode().equalsIgnoreCase(q)) { found = c; break; }
        if (found != null) {
            JOptionPane.showMessageDialog(null, found.toString(), "Course found", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Course not found: " + q, "Not found", JOptionPane.WARNING_MESSAGE);
        }
    }

    
    private void showAddFacultyDialog() {
        JPanel p = new JPanel(new GridLayout(3,2,8,8));
        p.setBorder(new EmptyBorder(8,8,8,8));
        JTextField name = new JTextField();
        JTextField dean = new JTextField();
        JTextField location = new JTextField();

        p.add(new JLabel("Faculty Name:")); p.add(name);
        p.add(new JLabel("Dean Name:")); p.add(dean);
        p.add(new JLabel("Location:")); p.add(location);

        int res = JOptionPane.showConfirmDialog(null, p, "Add Faculty", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (res == JOptionPane.OK_OPTION) {
            if (name.getText().trim().isEmpty() || dean.getText().trim().isEmpty() || location.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please complete all fields.", "Validation", JOptionPane.WARNING_MESSAGE);
                return;
            }
            for (Faculty f : faculties) {
                if (f.getFacultyName().equalsIgnoreCase(name.getText().trim())) {
                    JOptionPane.showMessageDialog(null, "Faculty already exists.", "Duplicate", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }
            Faculty faculty = new Faculty(name.getText().trim(), dean.getText().trim(), location.getText().trim());
            faculties.add(faculty);
            refreshFacultyTable();
            JOptionPane.showMessageDialog(null, "Faculty added: " + faculty.getFacultyName(), "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void modifyFacultyDialog() {
        String q = JOptionPane.showInputDialog(null, "Enter Faculty name to modify:", "Modify Faculty", JOptionPane.QUESTION_MESSAGE);
        if (q == null) return;
        String name = q.trim();
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter a faculty name.", "Input", JOptionPane.WARNING_MESSAGE);
            return;
        }
        Faculty found = null;
        for (Faculty f : faculties) if (f.getFacultyName().equalsIgnoreCase(name)) { found = f; break; }
        if (found == null) {
            JOptionPane.showMessageDialog(null, "Faculty not found: " + name, "Not found", JOptionPane.WARNING_MESSAGE);
            return;
        }

        JPanel p = new JPanel(new GridLayout(3,2,8,8));
        p.setBorder(new EmptyBorder(8,8,8,8));
        JTextField newName = new JTextField(found.getFacultyName());
        JTextField newDean = new JTextField(found.getDeanName());
        JTextField newLocation = new JTextField(found.getFacultyLocation());

        p.add(new JLabel("Faculty Name:")); p.add(newName);
        p.add(new JLabel("Dean Name:")); p.add(newDean);
        p.add(new JLabel("Location:")); p.add(newLocation);

        int res = JOptionPane.showConfirmDialog(null, p, "Modify Faculty", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (res == JOptionPane.OK_OPTION) {
            if (newName.getText().trim().isEmpty() || newDean.getText().trim().isEmpty() || newLocation.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please complete all fields.", "Validation", JOptionPane.WARNING_MESSAGE);
                return;
            }
            found.setFacultyName(newName.getText().trim());
            found.setDeanName(newDean.getText().trim());
            found.setFacultyLocation(newLocation.getText().trim());
            refreshFacultyTable();
            refreshCourseTable(); 
            JOptionPane.showMessageDialog(null, "Faculty updated.", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void searchFacultyDialog() {
        String q = JOptionPane.showInputDialog(null, "Enter Faculty name to search:", "Search Faculty", JOptionPane.QUESTION_MESSAGE);
        if (q == null) return;
        String name = q.trim();
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter a faculty name.", "Input", JOptionPane.WARNING_MESSAGE);
            return;
        }
        Faculty found = null;
        for (Faculty f : faculties) if (f.getFacultyName().equalsIgnoreCase(name)) { found = f; break; }
        if (found != null) {
            JOptionPane.showMessageDialog(null, found.toString(), "Faculty found", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Faculty not found: " + name, "Not found", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void refreshStudentTable() {
        studentTableModel.setRowCount(0);
        for (Student s : students) {
            studentTableModel.addRow(new Object[]{
                    s.getId(),
                    s.getFirstName(),
                    s.getLastName(),
                    s.getFatherName(),
                    s.getGender(),
                    s.getAge(),
                    s.getCoursesDone(),
                    s.getRemainingCredits()
            });
        }
    }

    private void refreshCourseTable() {
        courseTableModel.setRowCount(0);
        for (Course c : courses) {
            String facultyName = c.getFaculty() != null ? c.getFaculty().getFacultyName() : "";
            courseTableModel.addRow(new Object[]{
                    c.getCourseCode(),
                    c.getCourseName(),
                    c.getNumberOfCredits(),
                    c.getCourseCapacity(),
                    c.getNumberOfStudents(),
                    facultyName
            });
        }
    }

    private void refreshFacultyTable() {
        facultyTableModel.setRowCount(0);
        for (Faculty f : faculties) {
            facultyTableModel.addRow(new Object[]{
                    f.getFacultyName(),
                    f.getDeanName(),
                    f.getFacultyLocation()
            });
        }
    }

    private JButton makeButton(String text) {
        JButton b = new JButton(text);
        b.setBackground(TEAL);
        b.setForeground(Color.WHITE);
        b.setFocusPainted(false);
        b.setFont(NORMAL_FONT);
        b.setBorder(BorderFactory.createEmptyBorder(6,12,6,12));
        return b;
    }

    private void styleTable(JTable table) {
        table.setFont(NORMAL_FONT);
        table.setRowHeight(26);
        table.getTableHeader().setFont(HEADER_FONT);
        table.getTableHeader().setBackground(TEAL);
        table.getTableHeader().setForeground(Color.WHITE);
        table.setSelectionBackground(TEAL_LIGHT);
        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(SwingConstants.CENTER);

        for (int i = 0; i < table.getColumnCount(); i++) table.getColumnModel().getColumn(i).setCellRenderer(center);
    }


    public static void main(String[] args) {
        new Driver();
    }
}
