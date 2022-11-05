package example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.rmi.Naming;
import java.time.Duration;
import java.time.Instant;

public class RMIClient {
    public static void main(String[] args) {
        String hostName = "localhost";
        int port = 8080;
        String RMI_HOSTNAME = "java.rmi.server.hostname";
        String SERVICE_PATH = "//" + hostName + ":" + port + "/Service";
        try {
            System.setProperty(RMI_HOSTNAME, hostName);
            Service service = (Service) Naming.lookup(SERVICE_PATH);
            Instant ts = Instant.now();
            while (true) {
                Integer n = service.pollElem();
                if (n == null) {
                    System.out.println("Received none! + queue is empty");
                    break;
                } else {
                    File f1=new File("\"C:\\Users\\user\\OneDrive\\Рабочий стол\\dc-task-4-new\\src\\main\\java\\example\\text.txt\"");
                    int count=0;
                    FileReader fstr = new FileReader(f1);
                    for(int i = 0; i<f1.length(); i++) {
                        count++;
                    }
                    fstr.close();
                    service.countNumberOfWords(count);
                    System.out.println("number of letters: " + count);
                }}
            Instant te = Instant.now();
            System.out.println("Time = " + Duration.between(ts, te).toNanos() / 1e9 + " sec.");
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}