import java.io.*;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by chenjin on 2018/7/21.
 */
public class getpdf1 {
    public static void main(String[] args) throws IOException {
        File dir = new File("Exam1/tmp/");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        // "http://192.168.11.205:18080/trainning/SampleChapter1.pdf"
        URL url = new URL("http://file.choerodon.com.cn/trainning/SampleChapter1.pdf");
        URLConnection connection = url.openConnection();
        try (
                BufferedInputStream bis = new BufferedInputStream(connection.getInputStream());
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("Exam1/tmp/SampleChapter1.pdf"))
        ) {
            byte[] bytes = new byte[512];
            int len;
            while ((len = bis.read(bytes)) != -1) {
                bos.write(bytes, 0, len);
            }
            bos.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
