import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.io.File;

public class WrapperDownloader {
    public static void main(String[] args) {
        String url = "https://repo.maven.apache.org/maven2/org/apache/maven/wrapper/maven-wrapper/3.1.1/maven-wrapper-3.1.1.jar";
        String target = ".mvn/wrapper/maven-wrapper.jar";
        
        try {
            File targetFile = new File(target);
            if (!targetFile.getParentFile().exists()) {
                targetFile.getParentFile().mkdirs();
            }
            
            System.out.println("Downloading maven-wrapper.jar from " + url);
            URL website = new URL(url);
            ReadableByteChannel rbc = Channels.newChannel(website.openStream());
            FileOutputStream fos = new FileOutputStream(target);
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            fos.close();
            System.out.println("Download complete.");
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
