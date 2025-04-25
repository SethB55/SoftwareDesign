import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The ClientApp class provides a graphical user interface (GUI) for interacting with the FileClient.
 * Users can upload and retrieve text files from the server through this Swing-based interface.
 *
 * <p>Features:
 * <ul>
 *     <li>Enter a file name</li>
 *     <li>Enter or display file contents</li>
 *     <li>Upload or retrieve files using buttons</li>
 * </ul>
 */
public class ClientApp extends JFrame {                                                                                                                                                                                    

    private final JTextField fileNameField = new JTextField(20);
    private final JTextArea fileContentArea = new JTextArea(15, 40);
    private final JTextArea logArea = new JTextArea(10, 40);
    private final FileClient fileClient = new FileClient("localhost", 23645);

    /**
     * Constructs the ClientApp GUI, initializing the user interface and event listeners.
     */
    public ClientApp() {
        super("S57_RemoteWork_Medium Client");

        JButton retrieveButton = new JButton("Retrieve");
        JButton uploadButton = new JButton("Upload");

        JScrollPane contentScroll = new JScrollPane(fileContentArea);
        JScrollPane logScroll = new JScrollPane(logArea);

        fileContentArea.setLineWrap(true);
        logArea.setEditable(false);

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Filename:"));
        inputPanel.add(fileNameField);
        inputPanel.add(retrieveButton);
        inputPanel.add(uploadButton);

        setLayout(new BorderLayout());
        add(inputPanel, BorderLayout.NORTH);
        add(contentScroll, BorderLayout.CENTER);
        add(logScroll, BorderLayout.SOUTH);

        retrieveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                retrieveFile();
            }
        });

        uploadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                uploadFile();
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    /**
     * Handles the retrieval of a file from the server, updating the text area and log.
     */
    private void retrieveFile() {
        String fileName = fileNameField.getText().trim();
        if (!fileName.isEmpty()) {
            String result = fileClient.retrieveFile(fileName);
            fileContentArea.setText(result);
            logArea.append("Retrieved file: " + fileName + "\n");
        } else {
            logArea.append("Filename cannot be empty.\n");
        }
    }

    /**
     * Handles the upload of a file's content to the server.
     */
    private void uploadFile() {
        String fileName = fileNameField.getText().trim();
        String contents = fileContentArea.getText();
        if (!fileName.isEmpty() && !contents.isEmpty()) {
            String result = fileClient.uploadFile(fileName, contents);
            logArea.append("Upload result for " + fileName + ": " + result + "\n");
        } else {
            logArea.append("Filename and content cannot be empty.\n");
        }
    }

    /**
     * Main method to launch the ClientApp GUI.
     * @param args Command-line arguments (not used)
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(ClientApp::new);
    }
}
