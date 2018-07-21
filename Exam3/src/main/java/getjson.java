import com.alibaba.fastjson.JSONObject;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.*;

/**
 * Created by chenjin on 2018/7/21.
 */



public class getjson {
    static class byxml implements Runnable{

        public void run() {
            String  data = jsonutils.getjson("http://hq.sinajs.cn/list=sh601006");
            System.out.println(data);
            String [] arr1 = data.split(",");
            System.out.println(arr1);

            File f = new File("Exam3/temp/股票编码.xml");

            try {
                SAXReader reader = new SAXReader();
                Document doc = reader.read(f);

                Element root = doc.getRootElement();
                Element linkmanEl = root.addElement("stock");
                linkmanEl.addAttribute("id", "4");

                linkmanEl.addElement("name").setText(arr1[0]);
                linkmanEl.addElement("open").setText(arr1[1]);
                linkmanEl.addElement("close").setText(arr1[2]);
                linkmanEl.addElement("current").setText(arr1[3]);
                linkmanEl.addElement("high").setText(arr1[4]);
                linkmanEl.addElement("low").setText(arr1[5]);

                OutputFormat format = OutputFormat.createPrettyPrint();
                XMLWriter writer = new XMLWriter(new FileWriter(f), format);
                writer.write(doc);
                writer.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    static class byjson implements Runnable{

        public void run() {
            String data = jsonutils.getjson("http://hq.sinajs.cn/list=sh601006");
            System.out.println(data);
            String[] arr1 = data.split(",");
            System.out.println(arr1);

            JSONObject jsob = new JSONObject();
            jsob.put("name",arr1[0]);
            jsob.put("open",arr1[1]);
            jsob.put("close",arr1[2]);
            jsob.put("current",arr1[3]);
            jsob.put("high",arr1[4]);
            jsob.put("low",arr1[5]);

            File f = new File("Exam3/temp/股票编码.json");
            try {
                FileOutputStream fs = new FileOutputStream(f);
                fs.write(jsob.toString().getBytes());
                fs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    }
    public static void main(String[] args) {
        new Thread(new byxml()).start();
        new Thread(new byjson()).start();
    }
}
