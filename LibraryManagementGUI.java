import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class LibraryManagementGUI extends JFrame {

    private JTextField idField, titleField, authorField, searchField;
    private JTable table;
    private DefaultTableModel model;

    public LibraryManagementGUI() {

        setTitle("Library Management System");
        setSize(1000, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main panel
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(15, 20, 15, 20)
        );

        // =========================
        // TITLE
        // =========================
        JLabel heading = new JLabel("LIBRARY MANAGEMENT SYSTEM");

        heading.setFont(new Font("Arial", Font.BOLD, 30));
        heading.setForeground(new Color(0, 80, 150));
        heading.setHorizontalAlignment(SwingConstants.CENTER);

        mainPanel.add(heading, BorderLayout.NORTH);

        // =========================
        // BOOK INPUT PANEL
        // =========================
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));

        inputPanel.setBorder(
                BorderFactory.createTitledBorder("Book Details")
        );

        JLabel idLabel = new JLabel("Book ID:");
        JLabel titleLabel = new JLabel("Book Title:");
        JLabel authorLabel = new JLabel("Author:");

        idField = new JTextField();
        titleField = new JTextField();
        authorField = new JTextField();

        inputPanel.add(idLabel);
        inputPanel.add(idField);

        inputPanel.add(titleLabel);
        inputPanel.add(titleField);

        inputPanel.add(authorLabel);
        inputPanel.add(authorField);

        // =========================
        // ADD / CLEAR BUTTONS
        // =========================
        JPanel addPanel = new JPanel(new FlowLayout());

        JButton addButton = new JButton("Add Book");
        JButton clearButton = new JButton("Clear");

        addButton.setPreferredSize(new Dimension(130, 35));
        clearButton.setPreferredSize(new Dimension(130, 35));

        addPanel.add(addButton);
        addPanel.add(clearButton);

        JPanel topPanel = new JPanel(new BorderLayout());

        topPanel.add(inputPanel, BorderLayout.CENTER);
        topPanel.add(addPanel, BorderLayout.SOUTH);

        mainPanel.add(topPanel, BorderLayout.NORTH);

        // =========================
        // TABLE
        // =========================
        String[] columns = {
                "Book ID",
                "Book Title",
                "Author",
                "Status"
        };

        model = new DefaultTableModel(columns, 0);

        table = new JTable(model);

        table.setRowHeight(30);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.getTableHeader().setFont(
                new Font("Arial", Font.BOLD, 14)
        );

        JScrollPane scrollPane = new JScrollPane(table);

        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // =========================
        // SEARCH PANEL
        // =========================
        JPanel searchPanel = new JPanel(new FlowLayout());

        searchPanel.setBorder(
                BorderFactory.createTitledBorder("Search Book")
        );

        searchField = new JTextField(20);

        JButton searchButton = new JButton("Search");
        JButton showAllButton = new JButton("Show All");

        searchPanel.add(new JLabel("Enter Title / Author:"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        searchPanel.add(showAllButton);

        // =========================
        // ACTION BUTTONS
        // =========================
        JPanel actionPanel = new JPanel(new FlowLayout());

        JButton issueButton = new JButton("Issue Book");
        JButton returnButton = new JButton("Return Book");
        JButton deleteButton = new JButton("Delete Book");

        issueButton.setPreferredSize(new Dimension(130, 35));
        returnButton.setPreferredSize(new Dimension(130, 35));
        deleteButton.setPreferredSize(new Dimension(130, 35));

        actionPanel.add(issueButton);
        actionPanel.add(returnButton);
        actionPanel.add(deleteButton);

        JPanel bottomPanel = new JPanel(new BorderLayout());

        bottomPanel.add(searchPanel, BorderLayout.NORTH);
        bottomPanel.add(actionPanel, BorderLayout.SOUTH);

        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        // =========================
        // BUTTON ACTIONS
        // =========================

        addButton.addActionListener(e -> addBook());

        clearButton.addActionListener(e -> clearFields());

        issueButton.addActionListener(e -> issueBook());

        returnButton.addActionListener(e -> returnBook());

        deleteButton.addActionListener(e -> deleteBook());

        searchButton.addActionListener(e -> searchBook());

        showAllButton.addActionListener(e -> showAllBooks());

        add(mainPanel);
    }

    // =========================
    // ADD BOOK
    // =========================

    private void addBook() {

        String id = idField.getText().trim();
        String title = titleField.getText().trim();
        String author = authorField.getText().trim();

        if (id.isEmpty() ||
                title.isEmpty() ||
                author.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter all book details!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        // Check duplicate ID
        for (int i = 0; i < model.getRowCount(); i++) {

            if (model.getValueAt(i, 0)
                    .toString()
                    .equals(id)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Book ID already exists!"
                );

                return;
            }
        }

        model.addRow(new Object[]{
                id,
                title,
                author,
                "Available"
        });

        JOptionPane.showMessageDialog(
                this,
                "Book added successfully!"
        );

        clearFields();
    }

    // =========================
    // ISSUE BOOK
    // =========================

    private void issueBook() {

        int row = table.getSelectedRow();

        if (row == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a book from the table!"
            );

            return;
        }

        String status =
                model.getValueAt(row, 3).toString();

        if (status.equals("Issued")) {

            JOptionPane.showMessageDialog(
                    this,
                    "This book is already issued!"
            );

        } else {

            model.setValueAt("Issued", row, 3);

            JOptionPane.showMessageDialog(
                    this,
                    "Book issued successfully!"
            );
        }
    }

    // =========================
    // RETURN BOOK
    // =========================

    private void returnBook() {

        int row = table.getSelectedRow();

        if (row == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a book from the table!"
            );

            return;
        }

        String status =
                model.getValueAt(row, 3).toString();

        if (status.equals("Available")) {

            JOptionPane.showMessageDialog(
                    this,
                    "This book is already available!"
            );

        } else {

            model.setValueAt("Available", row, 3);

            JOptionPane.showMessageDialog(
                    this,
                    "Book returned successfully!"
            );
        }
    }

    // =========================
    // DELETE BOOK
    // =========================

    private void deleteBook() {

        int row = table.getSelectedRow();

        if (row == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a book from the table!"
            );

            return;
        }

        int choice = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to delete this book?",
                "Delete Book",
                JOptionPane.YES_NO_OPTION
        );

        if (choice == JOptionPane.YES_OPTION) {

            model.removeRow(row);

            JOptionPane.showMessageDialog(
                    this,
                    "Book deleted successfully!"
            );
        }
    }

    // =========================
    // SEARCH BOOK
    // =========================

    private void searchBook() {

        String search =
                searchField.getText()
                        .trim()
                        .toLowerCase();

        if (search.isEmpty()) {

            showAllBooks();
            return;
        }

        table.clearSelection();

        boolean found = false;

        for (int i = 0; i < model.getRowCount(); i++) {

            String title =
                    model.getValueAt(i, 1)
                            .toString()
                            .toLowerCase();

            String author =
                    model.getValueAt(i, 2)
                            .toString()
                            .toLowerCase();

            if (title.contains(search)
                    || author.contains(search)) {

                table.setRowSelectionInterval(i, i);

                found = true;
                break;
            }
        }

        if (!found) {

            JOptionPane.showMessageDialog(
                    this,
                    "Book not found!"
            );
        }
    }

    // =========================
    // SHOW ALL BOOKS
    // =========================

    private void showAllBooks() {

        searchField.setText("");
        table.clearSelection();

        JOptionPane.showMessageDialog(
                this,
                "All books are displayed in the table."
        );
    }

    // =========================
    // CLEAR FIELDS
    // =========================

    private void clearFields() {

        idField.setText("");
        titleField.setText("");
        authorField.setText("");
        searchField.setText("");
    }

    // =========================
    // MAIN METHOD
    // =========================

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            LibraryManagementGUI app =
                    new LibraryManagementGUI();

            app.setVisible(true);
        });
    }
}