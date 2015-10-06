package com.bobwang.string;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

public class StringUtil {

	public static void main(String[] args) {
		
//		char cha = 140;
//		
//		String s = "à â ä è é ê ë î ï ô œ ù û ü ÿ ç Ÿ" + cha;
//		
//		StringBuffer sb = new StringBuffer(s);
//
//		for (int i = 0; i < sb.length(); ++i)
//		{
//			char ch = sb.charAt(i);
//			System.out.println((int)ch);
//			switch (sb.charAt(i))
//			{
//				case '<':
//					sb.setCharAt(i, '&');
//					sb.insert(i + 1, "lt;");
//					break;
//				case '>':
//					sb.setCharAt(i, '&');
//					sb.insert(i + 1, "gt;");
//					break;
//				case '&':
//					sb.insert(i + 1, "amp;");
//					break;
//				case '\"':
//					sb.setCharAt(i, '&');
//					sb.insert(i + 1, "quot;");
//					break;
//				case '\\':
//					sb.setCharAt(i, '&');
//					sb.insert(i + 1, "#92;");
//					break;
//				case '\'':
//					sb.setCharAt(i, '&');
//					sb.insert(i + 1, "#39;");
//					break;
//				case 156:
//					sb.setCharAt(i, '&');
//					sb.insert(i + 1, "#339;");
//					break;
//				case 140:
//					sb.setCharAt(i, '&');
//					sb.insert(i + 1, "#338;");
//					break;
//			}
//		}
//		
//		s = sb.toString();
//		
//		System.out.println(s);
//		
//		System.out.println(URLEncoder.encode(s));
//		
//		s="%C5%93";
//		
//		
//		System.out.println(URLDecoder.decode(s));
//		
//		System.out.println(HtmlUtils.encode(s));
//		
//		s = "title=%C3%A0+%C3%A2+%C3%A4+%C3%A8+%C3%A9+%C3%AA+%C3%AB+%C3%AE+%C3%AF+%C3%B4+%C5%93+%C3%B9+%C3%BB+%C3%BC+%C3%BF+%C3%A7+%C5%B8";
//		
//		try {
//			System.out.println(URLDecoder.decode(s, "ISO-8859-1"));
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		System.out.println(System.getProperty("file.encoding"));
		
//		 String s="àâäèéêëîïôœùûüçàâäèéêëîïôœùûüçàâäèéêëîïôœùûüçàâäè";
//		 String encodedS = URLEncoder.encode(s);
//		 try {
//			String decodeS = URLDecoder.decode(encodedS, "UTF-8");
//			System.out.println(s.getBytes().length);
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		 
//		char ch = 156;
		
//		byte[] result = s.getBytes("UTF-8");
		
//		try {
//			String s = new String(new byte[]{(byte) 156,(byte) 140}, "windows-1252");
//			System.out.println(s);
//			
//			byte[] bytes = s.getBytes();
//			for(byte b : bytes){
//				System.out.println((int)b);
//			}
//			
//			String  result  = new String(s.getBytes("windows-1252"), "utf-8"); 
//			
//			String CP1252String = URLDecoder.decode(s, "windows-1252");
//			System.out.println(CP1252String);
//			
//			String result = URLDecoder.decode(CP1252String);		
//			System.out.println(result);
			
//			String sb = "abc"+"œŒ"+"def";
//			
//			String s1 = new String(sb.getBytes("windows-1252"));
//			System.out.println(s1);
//			
//			
//			System.out.println(new String(s1.getBytes(), "windows-1252"));
//			
//			final Charset windowsCharset = Charset.forName("windows-1252");
//			final Charset utfCharset = Charset.forName("UTF-8");
//			final CharBuffer windowsEncoded = windowsCharset.decode(ByteBuffer.wrap(new byte[] {(byte) 140}));
//			final byte[] utfEncoded = utfCharset.encode(windowsEncoded).array();
//			System.out.println(new String(utfEncoded, utfCharset.displayName()));
//			
//		} catch (UnsupportedEncodingException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//	StringUtil.printI18NString("Sorry, currently these characters are not supported: %STRING%, please remove them and try again.");
//		System.out.println(StringUtils.getNumericID("0000fafafazvzvz0001-1-00014-000002"));
//		  String pageID = "0a3f143f-03-142c850f628-ee10baaa-c5a";
//          BigInteger bi = new BigInteger(s.getBytes());
//		  String encodedPageID = StringUtils.encodeGUID(pageID);
//          System.out.println(encodedPageID);
//          System.out.println(encodedPageID.length());
//          
//          System.out.println(String.valueOf(Character.toChars(48)));
//          
//          System.out.println(StringUtils.decodeGUID("486551704952517045485145495250675653487054505645696949486665656545675365"));
			String errorMsg = "{\"statusCode\":18,\"statusMessage\":\"Failed to create user. User 'tt@dd.cc' already exists.\"}";
			if(errorMsg.contains("statusMessage"))
				errorMsg = errorMsg.substring(errorMsg.indexOf("\"statusMessage\":\"")+"\"statusMessage\":\"".length(), errorMsg.indexOf("\"}"));	
			System.out.println(errorMsg);
	}
	
	public static String encode(String s)
	{
		if (s == null)
		{
			return "";
		}
		StringBuffer sb = new StringBuffer(s);
		StringBuffer result = new StringBuffer("");

		for (int i = 0; i < sb.length(); ++i)
		{
			char ch = sb.charAt(i);
			if(ch >= 'a' && ch <='f')
				ch = (char) (ch-32);
			result.append((int)ch);
		}
		return result.toString();
	}
	
	public void testConvertEncoding() throws UnsupportedEncodingException{
		byte[] OEligInCP1252 = new byte[]{(byte) 0x8c,(byte)0};
		String OEligInUTF8 = new String(new byte[]{(byte)0xc5,(byte)0x92, (byte)0});
		System.out.println(new String(OEligInCP1252));
		System.out.println(OEligInUTF8);
//		System.out.println(HtmlUtils.encode(new String(OEligInCP1252)));
//		assertEquals(OEligInUTF8, new String(StringUtils.convertCharSet(OEligInCP1252, "windows-1252", "UTF-8")));
	}
	
	
	public static void printI18NString(String en_US){
		StringBuffer de_DE = new StringBuffer("");
		for(int i=0 ; i< en_US.length(); i++)
			de_DE.append(en_US.charAt(i)).append(en_US.charAt(i));
		System.out.println(de_DE.toString());
		
		System.out.println("en_"+en_US+"_en");
		
	}
	public static byte[] convertCharSet(byte[] incomingBytes, String srcEnc,String destEnc) {

		final Charset srcCharset = Charset.forName(srcEnc);
		final Charset destCharset = Charset.forName(destEnc);

		final CharBuffer srcEncoded = srcCharset.decode(ByteBuffer.wrap(incomingBytes));

		final byte[] destEncoded = destCharset.encode(srcEncoded).array();
		return destEncoded;
	}
}
