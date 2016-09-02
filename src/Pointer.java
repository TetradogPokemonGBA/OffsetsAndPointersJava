
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author pc
 */
public class Pointer {

    static final int MAXLENGTH = 4 * 2;//4 bytes y cada byte son dos char
    static int dieciseisMegas = Integer.parseInt("1000000", 16);
    static int trentaidosMegas = dieciseisMegas * 2;
    static final byte ESMAYORDEDECISEISMEGAS = 0x1;

    public static String GetPointer(String offset) {
        //quito espacios
        //valido
        //arreglo la string para hacer un caso
        //hacer la conversión

        boolean esMayor;
        String ptr = "";
        try {
            offset = offset.replaceAll(" ", "");
            if (EsValido(offset)) {
                offset = ArreglaOffset(offset);
                //ZZYYXX offset y si empieza en 01 y tiene seis char mas pues se le resta 0x FF FF FF al offset y acaba en 09
                esMayor = offset.substring(0, 2).equals("01");
                // XX YY ZZ 08 o 09 Pointer
                ptr = offset.substring(MAXLENGTH - 2, MAXLENGTH) + offset.substring(MAXLENGTH - 4, MAXLENGTH - 2) + offset.substring(MAXLENGTH - 6, MAXLENGTH - 4);
                if (esMayor) {
                    ptr += "09";
                } else {
                    ptr += "08";
                }
            }
        } catch (Exception ex) {
            ptr = "";
        }
        return ptr.toUpperCase();
    }

    public static boolean EsValido(String offset) {
        boolean esValido = false;
        if (offset.matches("-?[0-9a-fA-F]+")) {
            esValido = Integer.parseInt(offset, 16) < trentaidosMegas;

        }
        return esValido;//si es menor o igual de 01 FF FF FF es valido
    }

    private static String ArreglaOffset(String offset) {
        offset = Utils.PadLeft(offset, MAXLENGTH, '0');

        return offset;
    }
}
