
package com.wxm.util;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.BitSet;

/**
 * <p>Cookie 值编码</p>
 *
 * @author bao110908
 * @since 2011-9-16 12:56:53
 */
public class CookieCode {

    private CookieCode(){}

    private static BitSet dontNeedEncoding = new BitSet(0x80);
    private static char[] HEX = "0123456789abcdef".toCharArray();

    static {
        for (int i = 0x21; i <= 0x7e; i++) {  //
            dontNeedEncoding.set(i);
        }

        // <SP> " , ; \ 共 5 个字符不允许出现在 Cookie 的 value 当中
        // 其中 ASCII 编码为 0x20 的空格已经在上面代码中排除
        // 详见 RFC 6265 4.1.1 节: http://www.ietf.org/rfc/rfc6265.txt
        dontNeedEncoding.clear('\"');
        dontNeedEncoding.clear(',');
        dontNeedEncoding.clear(';');
        dontNeedEncoding.clear('\\');
        // 为了能使用 URLDecoder 解码将“+”和“%”进行处理
        dontNeedEncoding.clear('%');
        dontNeedEncoding.clear('+');
    }

    /**
     * <p>将数据编码为 Cookie 安全的字符串</p>
     *
     * @param str 原始数据
     * @return 作为 Cookie 安全值的字符串
     *
     * @author bao110908
     * @since 2011-9-21 16:18:20
     */
    public static String encode(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }
        StringBuilder builder = new StringBuilder();
        char[] chs = str.toCharArray();
        for (char c : chs) {
            if (c == ' ') {
                // 空格使用“+”替换
                builder.append('+');
                continue;
            }
            if (dontNeedEncoding.get(c)) {
                builder.append(c);
                continue;
            }
            // 以下根据 Unicode 与 UTF-8 规范进行转换
            // 忽略代理对编码 U+D800～U+DFFF，即忽略 U+FFFF 以上的字符
            if (c < 0x80) {
                builder.append('%');
                builder.append(HEX[(c & 0xf0) >> 4]);
                builder.append(HEX[c & 0x0f]);
                continue;
            }
            if (c < 0x800) {
                builder.append('%');
                builder.append(HEX[(0xc | (c >>> 10)) & 0xf]);
                builder.append(HEX[(c >>> 6) & 0xf]);
                builder.append('%');
                builder.append(HEX[(0x8 | ((c >>> 4) & 0x3)) & 0xf]);
                builder.append(HEX[c & 0xf]);
                continue;
            }
            if (c >=0xd800 && c <= 0xdfff) {
                builder.append("%3F");
                continue;
            }
            if (c < 0x10000) {
                builder.append('%');
                builder.append(HEX[0xe]);
                builder.append(HEX[(c >>> 12) & 0xf]);
                builder.append('%');
                builder.append(HEX[(0x8 | ((c >>> 10) & 0x3)) & 0xf]);
                builder.append(HEX[(c >>> 6) & 0xf]);
                builder.append('%');
                builder.append(HEX[(0x8 | ((c >>> 4) & 0x3)) & 0xf]);
                builder.append(HEX[c & 0xf]);
            }
        }
        return builder.toString();
    }

    /**
     * <p>解码 Cookie 中编码的值</p>
     *
     * @param str
     * @return
     *
     * @author bao110908
     * @since 2011-9-21 下午04:20:31
     */
    public static String decode(String str) {
        try {
            return URLDecoder.decode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        for (int i = 0; i < 0x10000; i++) {
            String c = String.valueOf((char)i);
            String u = URLEncoder.encode(c, "UTF-8");
            String p = encode(c);
            if (!u.equals(p)) {
                System.out.printf("%04x %s  %s  %s%n", i, c, u, p);
            }
            if (!URLDecoder.decode(p, "UTF-8").equals(c)) {
                System.out.printf(">> %04x %s  %s  %s  %s%n", i, c, u, p, URLDecoder.decode(p, "UTF-8"));
            }
        }
        System.out.println(URLDecoder.decode("+", "UTF-8"));
    }
}