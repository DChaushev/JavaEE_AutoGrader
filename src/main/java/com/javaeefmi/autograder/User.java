package com.javaeefmi.autograder;


/**
 * @author Daniel Angelov
 */

import java.io.UnsupportedEncodingException; 
import java.security.MessageDigest; 
import java.security.NoSuchAlgorithmException; 
 
class UserSecurity { 
 
    private static String convertToHex(byte[] data) { 
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < data.length; i++) { 
            int halfbyte = (data[i] >>> 4) & 0x0F;
            int two_halfs = 0;
            do { 
                if ((0 <= halfbyte) && (halfbyte <= 9)) 
                    buf.append((char) ('0' + halfbyte));
                else 
                    buf.append((char) ('a' + (halfbyte - 10)));
                halfbyte = data[i] & 0x0F;
            } while(two_halfs++ < 1);
        } 
        return buf.toString();
    } 
 
    public static String MD5(String text) 
    throws NoSuchAlgorithmException, UnsupportedEncodingException  { 
        MessageDigest md;
        md = MessageDigest.getInstance("MD5");
        byte[] md5hash = new byte[32];
        md.update(text.getBytes("iso-8859-1"), 0, text.length());
        md5hash = md.digest();
        return convertToHex(md5hash);
    } 
} 

public class User {
    
    public enum Role {
        SysOp,
        Administrator,
        Moderator,
        User
    }
    
    private final int m_Id;
    private final Role m_RoleId;
    private final String m_Username;
    private final String m_HashedPassword;
    
    public User(int id, Role role, String username, String password)
    {
        m_Id = id;
        m_RoleId = role;
        m_Username = username;
        m_HashedPassword = password;
    }

    public String getHashedPassword()
    {
        return m_HashedPassword;
    }
    
    public int getId()
    {
        return m_Id;
    }
    
    public Role getRoleId()
    {
        return m_RoleId;
    }
    
    public String getUsername()
    {
        return m_Username;
    }

}
