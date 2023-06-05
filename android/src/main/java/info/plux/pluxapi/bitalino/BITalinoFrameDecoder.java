/*
*
* Copyright (c) PLUX S.A., All Rights Reserved.
* (www.plux.info)
*
* This software is the proprietary information of PLUX S.A.
* Use is subject to license terms.
*
*/
package info.plux.pluxapi.bitalino;

import android.annotation.TargetApi;
import android.util.Log;
import android.os.Build;
import java.nio.charset.StandardCharsets;

import java.io.IOException;
import java.util.Arrays;

public class BITalinoFrameDecoder {
    private static final String TAG = "BITalinoFrameDecoder";

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public static BITalinoFrame decode(final String identifier, final byte[] buffer, final int[] analogChannels, final int totalBytes) throws IOException, BITalinoException {

        try {
            //Log.e(TAG, Arrays.toString(buffer));
            //Log.e(TAG, bytesToHex(buffer));
            BITalinoFrame frame;
            //RTC: -4 à toutes les valeurs d'index du buffer pour prendre en compte
            //les données de temps ajoutées
            final int j = (totalBytes - 1);

            //get frame CRC
            byte byteCRC = (byte)((buffer[j - 4] & 0xFF) & 0x0F);

            //CRC4 is for the all packet, from the sequence_number until the last byte of byte_n
            byte[] arrayCRC = buffer;

            //test if the received CRC is equal to the one calculated
            if(Byte.compare(byteCRC, BITalinoCRC.getCRC4(arrayCRC)) == 0){
                frame = new BITalinoFrame(identifier);
                //RTC: Récupération des infos de temps
                frame.setSeconds(buffer[j] & 0x7F);
                frame.setMinutes(buffer[j-1] & 0x7F);
                frame.setHours(((buffer[j-2] & 0xFC) >> 2) & 0xCF);
                frame.setMilliseconds(((buffer[j-2] & 0x03) << 8 ) | (buffer[j-3] & 0xFF) & 0x3FF);
                frame.setSequence(((buffer[j - 4] & 0xF0) >> 4) & 0xf);
                frame.setDigital(0, (buffer[j - 5] >> 7) & 0x01);
                frame.setDigital(1, (buffer[j - 5] >> 6) & 0x01);
                frame.setDigital(2, (buffer[j - 5] >> 5) & 0x01);
                frame.setDigital(3, (buffer[j - 5] >> 4) & 0x01);

                // parse buffer frame
                if (analogChannels.length >= 1) {
                    frame.setAnalog(analogChannels[0], (((buffer[j - 5] & 0xF) << 6) | ((buffer[j - 6] & 0XFC) >> 2)) & 0x3ff);
                }
                if (analogChannels.length >= 2) {
                    frame.setAnalog(analogChannels[1], (((buffer[j - 6] & 0x3) << 8) | (buffer[j - 7]) & 0xff) & 0x3ff);
                }
                if (analogChannels.length >= 3) {
                    frame.setAnalog(analogChannels[2], (((buffer[j - 8] & 0xff) << 2) | (((buffer[j - 9] & 0xc0) >> 6))) & 0x3ff);
                }
                if (analogChannels.length >= 4) {
                    frame.setAnalog(analogChannels[3], (((buffer[j - 9] & 0x3F) << 4) | ((buffer[j - 10] & 0xf0) >> 4)) & 0x3ff);
                }
                if (analogChannels.length >= 5) {
                    frame.setAnalog(analogChannels[4], (((buffer[j - 10] & 0x0F) << 2) | ((buffer[j - 11] & 0xc0) >> 6)) & 0x3f);
                }
                if (analogChannels.length >= 6) {
                    frame.setAnalog(analogChannels[5], (buffer[j - 11] & 0x3F));
                }
            } else {
                frame = new BITalinoFrame(identifier);
                frame.setSequence(-1);
            }
            return frame;
        } catch (Exception e) {
            throw new BITalinoException(BITalinoErrorTypes.DECODE_INVALID_DATA);
        }
    }

    private static final byte[] HEX_ARRAY = "0123456789ABCDEF".getBytes(StandardCharsets.US_ASCII);
    public static String bytesToHex(byte[] bytes) {
        byte[] hexChars = new byte[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars, StandardCharsets.UTF_8);
    }
}