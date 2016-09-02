
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import javax.xml.bind.DatatypeConverter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author pc
 */
//metodos sacados de internet!
public class Utils {

    public static int bytesToInt(byte[] int_bytes) throws IOException {
        ByteArrayInputStream bis = new ByteArrayInputStream(int_bytes);
        ObjectInputStream ois = new ObjectInputStream(bis);
        int my_int = ois.readInt();
        ois.close();
        return my_int;
    }

    public static String byteArrayToHex(byte[] array) {
        return DatatypeConverter.printHexBinary(array);
    }

    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }

    public static String PadLeft(String string, int maxLenght, char caracter) {
        StringBuilder str = new StringBuilder();
        for (int i = string.length(); i < maxLenght; i++) {
            str.append(caracter);
        }
        str.append(string);

        return str.toString();
    }

    public static String PadRight(String string, int maxLenght, char caracter) {
        StringBuilder str = new StringBuilder();
        str.append(string);
        for (int i = string.length(); i < maxLenght; i++) {
            str.append(caracter);
        }
        return str.toString();
    }
}
