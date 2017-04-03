package util;
import java.security.Key;
import javax.crypto.Cipher;
public class DesUtils {
    private static String strDefaultKey = "national";
    public static String[] keyyy = {"BHkiOCs0bA","KWd4fP5X32","InMTRLN0UG","v9K3wdPzBu","Ah8Hx7NKzm","mLMp5xufgy","roYEwPq16Q","fnbHpcd34t","caSiHu9GhO","Nn967kfEcS","OVl90vuHTJ","K0ys5fTHxt","gLknjTuEaz","lzSFo5xdgA","02K5QiHalR","70UIPsqEMf","MhxszUKBN3","aTndBMvz4u","P64y80KQdA","gnv6moyMfu","jEZGKs1gIl","7WSHZiXvdz","0pPXRDmxc1","HRcIDomwN5","4RVhoHUlvZ","6Z4OqtMyp1","q6A9yzHOtP","bliqLZpnga","oVUdKzqhSm","UX59GcVwqK","vIyQj8sOrK","iWuCc1gN9e","RJcHVSjGvL","suxmBcCH0e","S5libYOKJa","7aV02mNeFW","dCkWujL01a","pn5FqAfsWX","NJME1KGive","HOQnwsaM7D","X3QsF9pZ6M","3Ofy62sbvX","L9kKip5Xe7","nR7e2KJ9hx","lbkwYDUMaP","ndqetlNhgW","cjlGVWz8Je","QI3htlJwGm","XxZCWbRtqY","q1ZIcVot36","M5XTxbfZyN","yUj87OldHD","nwgk4RjemD","vVQjAdws81","IGB6ms1Fqf","XP7dpg6EY4","N3kIUbZjvL","6S1iPeOl9y","9D7nml8Ruv","uPks7KWd1x","BY0yHM5GV3","PkDcqYFTbN","ngC7z8NvXK","nGBPt1hdJY","PzXgxhBpYm","CUMRA5Oe0Y","phyNJMKw0O","UFITm4ZtCG","FSeoa4kJ1q","d9PMsTLOBo","S5EnWBeTug","ePrm0vJjZU","gjnLyHTevM","VAlF7Mg4LO","qkCTh6LbvP","qjCYJ8Od2g","h57qp294nj","q2A0dD6tjc","FeVzS7QnPC","F5YK48LtkN","bYwUAXteDB","ZHapOKqRMV","gRk1dEz4v0","gMsjKQ9WTA","Gsmix1Y5Ke","Gahec1RYUt","MblwfctOh5","X5c4iKjMof","1CHIMBgqxA","lUM2VtGwSZ","5Z2ozFfXkd","4Yb3Rw7HsI","Y6RudaqCz7","kXyn9WLg7B","zh5J9edsoi","Bw4hHWpJoD","y0bjMtOkW7","G8ksESQuCK","hsHKNcjeuU","pz1QDfUMB0","VlIH8ycA1k","fP9sRcEI25","iLjwNpBmH3","TwmSpMDk6P","tiTQup7sU2","A8grmIB9Dl","p0MdRUIEwK","F2lgbAQMB3","oyPZ9eOb5G","OZEo2wFl1g","1orlIBbiPU","5G6iMUWHlv","wWNBLtJs92","Hop0zKiSsJ","5T9sUioKkq","GLdkbsiEvS","TARZmqJQHB","iADwpzJBMd","9zFUdwGpxg","DFgzoauEC6","5UomHJGipb","7o8tWiUksX","H7dwMNPBKe","HwbNxuBGAI","uX4WLHpyEh","Nm9V51WRZd","hKi4gwBpS8","1BQiu0dIyS","90AON5JXMg","FZ9pQmE0oU","RhVEbd3pOm","3k7Sfe42Za","t2i6HQaUfL","nC6mR4HPKx","DqnYgOrKCZ","amw8MU3K2f","ToEhyrweKd","EGtelLPFBA","wtizMa6nsq","NS2WTb70gk","27HiGJCT5O","nML6xA948g","bUDrGiOXPJ","ME4IWjb16H","VPByA8d3ah","fPtHhdiB58","9YR0XbHzhq","iA0IfUOMBl","tJzNG3kC9V","XQYu6zJl7f","m3KtlIV7Jw","tw6a8fBRFu","JFIwXNnre5","nhbPNjGztY","LizNlUra6O","AqMN4XIomw","a0eubTOqp6","mn9NAFPYKi","g5Pv39z1xk","q0htDR34FB","K7sk8QbSHW","SnlmXYMHh9","pvTzLoG8Sa","eI1AD4Kgjh","KpftvaoX3T","GdNMtj75nI","xY4Z6Bd0Vn","djs32c5Ghx","jrwDBFgu1R","WmPN9nKbuq","OwNcl6JQSv","b39om0JWPj","LjmydEASQH","emLIQWJYEB","mSZVC7dpIW","ct3HYkpSPW","um3rjCGFwh","1RTjfYq7bh","y8skdUTlQ4","hGKasgVPAy","UPNLux4Tpj","uz8odp0cTq","67VfwNcZLj","RLZQgulGpE","h1mBHrd9bv","mZkqzEbA0H","FvaBJ4Wxln","mnUE4SVvst","q3GcHXLjRQ","D3vCP7gNZS","NZXeBrjAJY","wzV0sRUIW5","ewqEOrHsYy","ERo3xAMvhu","i9faBHJEDc","1z3CVMxstD","i7L3pqx8Yy","hVXxPYMTWi","C9Q2JTtxab","R1U8iodeVY"};
    private Cipher encryptCipher = null;
    private Cipher decryptCipher = null;

    public static String byteArr2HexStr(byte[] arrB) throws Exception {
        int iLen = arrB.length;
// 每个byte用两个字符才能表示，所以字符串的长度是数组长度的两倍   
        StringBuffer sb = new StringBuffer(iLen * 2);
        for (int i = 0; i < iLen; i++) {
            int intTmp = arrB[i];
// 把负数转换为正数   
            while (intTmp < 0) {
                intTmp = intTmp + 256;
            }
// 小于0F的数需要在前面补0   
            if (intTmp < 16) {
                sb.append("0");
            }
            sb.append(Integer.toString(intTmp, 16));
        }
        return sb.toString();
    }

    public static byte[] hexStr2ByteArr(String strIn) throws Exception {
        byte[] arrB = strIn.getBytes();
        int iLen = arrB.length;
// 两个字符表示一个字节，所以字节数组长度是字符串长度除以2   
        byte[] arrOut = new byte[iLen / 2];
        for (int i = 0; i < iLen; i = i + 2) {
            String strTmp = new String(arrB, i, 2);
            arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
        }
        return arrOut;
    }

    public DesUtils() throws Exception {
        this(strDefaultKey);
    }

    public DesUtils(String strKey) {
//  Security.addProvider(null);
        Key key;
        try {
            key = getKey(strKey.getBytes());
            encryptCipher = Cipher.getInstance("DES");
            encryptCipher.init(Cipher.ENCRYPT_MODE, key);
            decryptCipher = Cipher.getInstance("DES");
            decryptCipher.init(Cipher.DECRYPT_MODE, key);
        } catch (Exception e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public byte[] encrypt(byte[] arrB) throws Exception {
        return encryptCipher.doFinal(arrB);
    }

    public String encrypt(String strIn) throws Exception {
        return byteArr2HexStr(encrypt(strIn.getBytes()));
    }

    public byte[] decrypt(byte[] arrB) throws Exception {
        return decryptCipher.doFinal(arrB);
    }

    public String decrypt(String strIn) throws Exception {
        return new String(decrypt(hexStr2ByteArr(strIn)));
    }

    private Key getKey(byte[] arrBTmp) throws Exception {
// 创建一个空的8位字节数组（默认值为0）   
        byte[] arrB = new byte[8];
// 将原始字节数组转换为8位   
        for (int i = 0; i < arrBTmp.length && i < arrB.length; i++) {
            arrB[i] = arrBTmp[i];
        }
// 生成密钥   
        Key key = new javax.crypto.spec.SecretKeySpec(arrB, "DES");
        return key;
    }
}