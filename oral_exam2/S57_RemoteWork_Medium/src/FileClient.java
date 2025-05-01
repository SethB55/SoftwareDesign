import java.io.*;
import java.net.Socket;

/**
 * The FileClient class handles the networking functionality for the client side.
 * It connects to a server via a TCP socket to either upload or retrieve text files.
 *
 * Workflow:
 *
 *     Upload: Sends a file name and its contents to the server
 *     Retrieve: Requests a file from the server and receives its contents
 *
 */
public class FileClient {
    private final String serverAddress;
    private final int port;

    /**
     * Constructs a FileClient with the given server address and port number.
     *
     * @param serverAddress The server's hostname or IP address
     * @param port          The server's port number
     */
    public FileClient(String serverAddress, int port) {
        this.serverAddress = serverAddress;
        this.port = port;
    }

    /**
     * Retrieves the contents of a file from the server.
     *
     * @param fileName The name of the file to retrieve
     * @return The contents of the file as a string or an error message
     */
    public String retrieveFile(String fileName) {
        StringBuilder result = new StringBuilder();
        try (
                Socket socket = new Socket(serverAddress, port);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))
        ) {
            out.println("RETRIEVE");
            out.println(fileName);

            String response = in.readLine();
            if ("SUCCESS".equals(response)) {
                String line;
                while ((line = in.readLine()) != null) {
                    result.append(line).append("\n");
                }
            } else {
                result.append(response); // Error message from server
            }
        } catch (IOException e) {
            result.append("Error: ").append(e.getMessage());
        }
        return result.toString();
    }

    /**
     * Uploads a file's contents to the server.
     *
     * @param fileName The name of the file to upload
     * @param contents The contents of the file
     * @return The server's response (success or error message)
     */
    public String uploadFile(String fileName, String contents) {
        String result;
        try (
                Socket socket = new Socket(serverAddress, port);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))
        ) {
            out.println("UPLOAD");
            out.println(fileName);
            for (String line : contents.split("\n")) {
                out.println(line);
            }
            socket.shutdownOutput(); // Signal end of upload

            result = in.readLine();
        } catch (IOException e) {
            result = "Error: " + e.getMessage();
        }
        return result;
    }
}
