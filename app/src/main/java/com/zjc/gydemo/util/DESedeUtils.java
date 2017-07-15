package com.zjc.gydemo.util;

import org.apache.commons.codec.binary.Hex;

import java.util.Locale;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

/**
 * 对密码进行加密
 * Created by zjc on 2016/11/17.
 */

public class DESedeUtils {
    public static String des(String password) {
        byte[] desPassword = DESedeUtils.encryptProperty(password);
        String DESPassword = new String(Hex.encodeHex(desPassword));
        return DESPassword.toUpperCase(Locale.US);
    }

    private static byte[] encryptProperty(String clearText) {

        String KEY_STRING = "Sa#qk5usfmMI-@2dbZP9`jL3";
        String spec = "beLd7$lB";
        return DESedeUtils.performDESedeCoder(clearText, KEY_STRING, spec, true);
    }

    private static byte[] performDESedeCoder(String inputValue, String key, String spec, boolean encrypt) {
        Cipher cipher = null;
        boolean flag = true;
        String KEY_ALGORITHM = "DESede";
        String CIPHER_ALGORITHM = "DESede/CBC/PKCS5Padding";

        byte byte0 = ((byte) (!flag ? 2 : 1));
        byte[] data = null;
        try {
            DESedeKeySpec dks = new DESedeKeySpec(key.getBytes());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(KEY_ALGORITHM);
            SecretKey secretKey = keyFactory.generateSecret(dks);
            cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            IvParameterSpec ivSpec = new IvParameterSpec(spec.getBytes());
            byte[] input = null;
            if (encrypt) {
                cipher.init(byte0, secretKey, ivSpec);
                input = inputValue.getBytes();
            } else {
                cipher.init(byte0, secretKey, ivSpec);
                input = inputValue.getBytes();
            }
            input = pad(input, 8);
            data = cipher.doFinal(input);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return data;
    }

    private static byte[] pad(byte[] in, int padLen) {
        if (padLen == 0) {
            return in;
        }
        int inlen = in.length;
        int outlen = inlen;
        int rem = inlen % padLen;
        if (rem > 0) {
            outlen = inlen + (padLen - rem);
        }
        byte[] out = new byte[outlen];
        for (int xx = 0; xx < inlen; xx++) {
            out[xx] = in[xx];
        }
        return out;
    }
}
