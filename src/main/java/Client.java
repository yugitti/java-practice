import java.io.*;
import java.net.Socket;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Client {
    static public void runTcpBySocket() throws IOException {
        try(
            var soc = new Socket("localhost", 1600);
            OutputStream output = soc.getOutputStream();
        ){
            output.write(234);
        }
//        output.close();
//        soc.close();
    }

    static public void runHttpBySocket(String domain) throws IOException{
//        var domain = "example.com";
        try(
            var soc = new Socket(domain, 80);
            var pw = new PrintWriter(soc.getOutputStream()); // writer from client to sever
            var isr = new InputStreamReader(soc.getInputStream()); // stream from server to client
            var bur = new BufferedReader(isr); // buffer reader from server to client
        ){
            // client --> server
            pw.println("GET /index.html HTTP/1.1"); // write to pw writer
            pw.println("Host: " + domain); // write to pw writer
            pw.println(); // write to pw writer
            pw.flush(); // output all data on writer to socket
            // server --> client
            bur.lines(). // save response data to bur, then change to stream by lines function
                    limit(18)
                    .forEach(System.out::println);
        }

    }

    // HttpClientAPI
    static public void runWebClientByHttpClientAPI(String url) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        URI uri = URI.create(url);
        HttpRequest req = HttpRequest.newBuilder(uri).GET().build();
        /*
            BodyHandeler
            ofString: String
            ofLines: Stream<String>
            ofFile: Path
            ofByteArray: byte[]
            ofInputStream: InputStream
         */
        HttpResponse<String> response = client.send(
                req, HttpResponse.BodyHandlers.ofString()
        );

        String body = response.body();
        body.lines().limit(5).forEach(System.out::println);
    }
}
