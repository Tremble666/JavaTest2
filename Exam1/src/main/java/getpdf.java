import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by chenjin on 2018/7/21.
 */
public class getpdf {
    private static final String FILE_URL = "http://file.choerodon.com.cn/trainning/SampleChapter1.pdf";
    private static final String LOCAL_FILE_PATH = "Exam1/temp/SampleChapter1.pdf"; // 改成你保存 文件的路径

    public static void main(String[] args) {
        new getpdf(FILE_URL, LOCAL_FILE_PATH).download();
    }

    private String remoteFileUrl;
    private String localFilePath;

    public getpdf(String remoteFileUrl, String localFilePath) {
        this.remoteFileUrl = remoteFileUrl;
        this.localFilePath = localFilePath;
    }

    public void download() {
        try {
            URL url = new URL(remoteFileUrl);

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setConnectTimeout(5 * 1000);
            httpURLConnection.connect(); // 连接

            int fileLenght = httpURLConnection.getContentLength();

            try (DataInputStream dis = new DataInputStream(httpURLConnection.getInputStream());
                 FileOutputStream fos = new FileOutputStream(localFilePath)) {
                byte[] buf = new byte[1024];
                for (int readSize; (readSize = dis.read(buf)) > 0;) {
                    fos.write(buf, 0, readSize);
                }
                System.out.println("ok~");
            } catch (IOException ex) {
                System.out.println("error");
            }

            httpURLConnection.disconnect();
        } catch (IOException ex) {
            System.out.println("time out");
        }
    }
}
