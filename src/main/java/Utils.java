import org.apache.commons.codec.binary.Base64;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by sweet on 15-6-10.
 */
public class Utils {
    public static boolean saveAvatarImage(String imgData, String fileName) {
        try {
            byte[] imageDataBytes = Base64.decodeBase64(imgData);
            FileOutputStream file = new FileOutputStream(fileName);
            file.write(imageDataBytes);
            file.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
