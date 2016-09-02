/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author pc
 */
public class Offset {

    static final int MAXLENGTH = 4 * 2;//4 bytes y cada byte son dos char
    static int dieciseisMegas = Integer.parseInt("1000000", 16);
    static int trentaidosMegas = dieciseisMegas * 2;

    public static String GetOffset(String pointer) {
        //quito espacios
        //valido
        //arreglo la string para hacer un caso
        //hacer la conversión
        String offset = "";
        boolean acabaEn8;
        try {
            pointer = pointer.replaceAll(" ", "");
            if (EsValido(pointer)) {
                pointer = ArreglaString(pointer);
                // XX YY ZZ 08 o 09 Pointer
                acabaEn8 = pointer.substring(pointer.length() - 1, pointer.length()).equals("8");
                //ZZYYXX offset y si acaba en 09 se le suma 0x FF FF FF al offset
                offset = pointer.substring(4, 6) + pointer.substring(2, 4) + pointer.substring(0, 2);
                if (!acabaEn8) {
                    //le sumo 0x FF FF FF al offset
                    offset = Integer.toHexString(dieciseisMegas + Integer.parseInt(offset, 16));
                }

            }
        } catch (Exception ex) {
            offset = "";
        }
        return offset.toUpperCase();
    }

    static boolean EsValido(String ptr) {
        final String End8 = "" + 8, End9 = 9 + "";
        boolean valido = false;
        boolean esMasPequeño;
        if (ptr.matches("-?[0-9a-fA-F]+") && ptr.length() <= MAXLENGTH) {

            esMasPequeño = ptr.length() < MAXLENGTH - 1;
            valido = esMasPequeño;
            String lastChar;
            if (!esMasPequeño) {
                lastChar = ptr.substring(ptr.length() - 1);
                valido = lastChar.equals(End8) || lastChar.equals(End9);
            }
        }
        return valido;//si mide la longitud maxima mirar si acaba en 8 o 9
    }

    private static String ArreglaString(String pointer) {
        boolean poner08 = true;
        if (pointer.length() > MAXLENGTH - 2) {
            poner08 = pointer.substring(pointer.length() - 1, pointer.length()).equals("8");
            pointer = pointer.substring(0, MAXLENGTH - 2);
        } else {
            pointer = Utils.PadLeft(pointer, MAXLENGTH - 2, '0');
        }
        if (poner08) {
            pointer += "08";
        } else {
            pointer += "09";
        }

        return pointer;
    }
}
