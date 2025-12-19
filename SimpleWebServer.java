import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class SimpleWebServer {
    public static void main(String[] args) throws IOException {
        // Create HTTP server on port 8080
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        // Create a context for the root path "/"
        server.createContext("/", new MyHandler());

        // Start the server
        server.setExecutor(null); // creates a default executor
        server.start();

        System.out.println("Server started on http://localhost:8080");
        System.out.println("Press Ctrl+C to stop the server");
    }

    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String response = """
                <!DOCTYPE html>
                <html lang="en">
                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <title>Simple Java Website</title>
                    <style>
                        body {
                            font-family: Arial, sans-serif;
                            margin: 0;
                            padding: 20px;
                            background-color: #f0f0f0;
                            text-align: center;
                        }
                        .container {
                            max-width: 800px;
                            margin: 0 auto;
                            background-color: white;
                            padding: 40px;
                            border-radius: 10px;
                            box-shadow: 0 0 10px rgba(0,0,0,0.1);
                        }
                        h1 {
                            color: #333;
                            margin-bottom: 20px;
                        }
                        p {
                            color: #666;
                            line-height: 1.6;
                        }
                        .highlight {
                            color: #007bff;
                            font-weight: bold;
                        }
                    </style>
                </head>
                <body>
                    <div class="container">
                        <h1>Welcome to My Simple Java Website!</h1>
                        <p>This website is generated using <span class="highlight">Java</span> and the built-in HttpServer.</p>
                        <p>It's a basic example showing how to create a web server in Java without external dependencies.</p>
                        <p>Features:</p>
                        <ul style="text-align: left; display: inline-block;">
                            <li>Simple HTTP server</li>
                            <li>HTML response</li>
                            <li>CSS styling</li>
                            <li>No external libraries needed</li>
                        </ul>
                        <p><em>Server running on port 8080</em></p>
                    </div>
                </body>
                </html>
                """;

            exchange.sendResponseHeaders(200, response.getBytes().length);
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}