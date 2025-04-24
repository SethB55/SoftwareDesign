import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * The FileServer class implements a simple TCP server that allows clients to upload and retrieve text files.
 * Files are stored in a specified directory on the server.
 *
 * <p>Supported commands:
 * <ul>
 *     <li>UPLOAD - Uploads a file from the client to the server</li>
 *     <li>RETRIEVE - Retrieves a file from the server to the client</li>
 * </ul>
 *
 * <p>Usage:
 * <ul>
 *     <li>Start the server, which listens on a specific port (23645)</li>
 *     <li>The server continuously accepts client connections and processes their requests</li>
 * </ul>
 */
public class FileServer {

    private static final int PORT = 23645;
    private static final String FILE_DIRECTORY = "/iahome/s/se/sebolen/IdeaProjects/sebolen_swd/oral_exam2/S57_RemoteWork_Medium/textfiles";

    private static final String UPLOAD = "UPLOAD";
    private static final String RETRIEVE = "RETRIEVE";
    private static final String SUCCESS = "SUCCESS";
    private static final String ERROR = "ERROR";

    /**
     * Main method to start the server.
     * @param args Command-line arguments (not used)
     */
    public static void main(String[] args) {
        File directory = new File(FILE_DIRECTORY);
        if (!directory.exists()) {
            System.err.println("Directory does not exist: " + FILE_DIRECTORY);
            return;
        }

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server started on port " + PORT);

            while (true) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    handleClient(clientSocket);
                } catch (IOException e) {
                    System.err.println("Error accepting client connection: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Could not start server on port " + PORT);
        }
    }

    /**
     * Handles communication with a single client.
     *
     * @param socket The client's socket connection
     */
    private static void handleClient(Socket socket) {
        try (
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
        ) {
            String command = in.readLine();
            String fileName = in.readLine();

            if (RETRIEVE.equals(command)) {
                handleRetrieve(fileName, out);
            } else if (UPLOAD.equals(command)) {
                handleUpload(fileName, in, out);
            } else {
                out.println(ERROR + ": Unknown command");
            }
        } catch (IOException e) {
            System.err.println("Client communication error: " + e.getMessage());
        }
    }

    /**
     * Retrieves a file and sends its content to the client.
     *
     * @param fileName The name of the file to retrieve
     * @param out      The output stream to send data back to the client
     */
    private static void handleRetrieve(String fileName, PrintWriter out) {
        File file = new File(FILE_DIRECTORY, fileName);
        if (file.exists()) {
            out.println(SUCCESS);
            try (BufferedReader fileReader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = fileReader.readLine()) != null) {
                    out.println(line);
                }
            } catch (IOException e) {
                out.println(ERROR + ": Error reading file");
            }
        } else {
            out.println(ERROR + ": File not found");
        }
    }

    /**
     * Receives a file's content from the client and saves it on the server.
     *
     * @param fileName The name of the file to save
     * @param in       The input stream to receive data from the client
     * @param out      The output stream to send responses back to the client
     */
    private static void handleUpload(String fileName, BufferedReader in, PrintWriter out) {
        File file = new File(FILE_DIRECTORY, fileName);
        try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(file))) {
            String line;
            while ((line = in.readLine()) != null) {
                fileWriter.write(line);
                fileWriter.newLine();
            }
            out.println(SUCCESS);
        } catch (IOException e) {
            out.println(ERROR + ": Error writing file");
        }
    }
}
