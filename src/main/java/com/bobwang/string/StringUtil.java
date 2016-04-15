package com.bobwang.string;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.annotate.JsonIgnore;

import com.mstr.Meta;

public class StringUtil {

	private abstract class IgnoredMethodsForQueueVariable {
		@JsonIgnore
		public abstract boolean isCaseVariable();

		@JsonIgnore
		public abstract boolean isToastVariable();
	}
	
	enum test{ONE, TWO};

	public static void main(String[] args) {
		
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//定义格式，不显示毫秒
//		Timestamp now = new Timestamp(System.currentTimeMillis());//获取系统当前时间
//		String str = df.format(now);
//		
//		System.out.println(str);
		
		String forwardMapURL = "/DomainEditor?command=displayPages&domainCode=0a346a42-02-14f19159b47-ef82c5e8-c7a";
		String dc = forwardMapURL.substring(forwardMapURL.indexOf(Meta.DOMAIN_CODE_TAG())+11, forwardMapURL.length());
		System.out.println(dc);

//		for (int i = 0; i < 1000; i++) {
//			ACDVar var = new ACDVar();
//			var.setDisplayName("456");
//			var.setAttachedDataKey("123");
//
//			Method[] methods = var.getClass().getDeclaredMethods();
//			for(Method meth : methods){
//				System.out.println(meth.getName());
//			}
//			ObjectMapper queueVarMapper = new ObjectMapper();
//			queueVarMapper.getSerializationConfig().addMixInAnnotations(ACDQueueVariable.class, IgnoredMethodsForQueueVariable.class);
//			try {
//				System.out.println("Jackson: " + queueVarMapper.writeValueAsString(var));
//			} catch (JsonGenerationException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (JsonMappingException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} finally {
//				queueVarMapper = null;
//				var = null;
//			}
//		}
		
//		printI18NString("Your email address cannot be longer than 256 characters, in which local part shouldn't be longer than 64.");
//			String email = "ewangchong@gmail.com";
//			int indexOfAt = email.indexOf('@');
//			String local = email.substring(0, indexOfAt);
//			String domain = email.substring(indexOfAt+1);
//			System.out.println(local + "@"+domain);
	}

	public static String encode(String s) {
		if (s == null) {
			return "";
		}
		StringBuffer sb = new StringBuffer(s);
		StringBuffer result = new StringBuffer("");

		for (int i = 0; i < sb.length(); ++i) {
			char ch = sb.charAt(i);
			if (ch >= 'a' && ch <= 'f')
				ch = (char) (ch - 32);
			result.append((int) ch);
		}
		return result.toString();
	}

	public void testConvertEncoding() throws UnsupportedEncodingException {
		byte[] OEligInCP1252 = new byte[] { (byte) 0x8c, (byte) 0 };
		String OEligInUTF8 = new String(new byte[] { (byte) 0xc5, (byte) 0x92, (byte) 0 });
		System.out.println(new String(OEligInCP1252));
		System.out.println(OEligInUTF8);
		// System.out.println(HtmlUtils.encode(new String(OEligInCP1252)));
		// assertEquals(OEligInUTF8, new
		// String(StringUtils.convertCharSet(OEligInCP1252, "windows-1252",
		// "UTF-8")));
	}

	public static void printI18NString(String en_US) {
		StringBuffer de_DE = new StringBuffer("");
		for (int i = 0; i < en_US.length(); i++)
			de_DE.append(en_US.charAt(i)).append(en_US.charAt(i));
		System.out.println(de_DE.toString());

		System.out.println("en_" + en_US + "_en");

	}

	public static byte[] convertCharSet(byte[] incomingBytes, String srcEnc, String destEnc) {

		final Charset srcCharset = Charset.forName(srcEnc);
		final Charset destCharset = Charset.forName(destEnc);

		final CharBuffer srcEncoded = srcCharset.decode(ByteBuffer.wrap(incomingBytes));

		final byte[] destEncoded = destCharset.encode(srcEncoded).array();
		return destEncoded;
	}

	private static List<String> formatGWSStringList(List<String> listToFormat) {
		System.out.println("Before Format GWS String: " + listToFormat.toString());
		List<String> result = new ArrayList<String>();
		StringBuffer sb = new StringBuffer("");
		boolean iskey = true;
		boolean inMetadata = false;
		boolean isMetadataKey = true;
		for (int i = 0; i < listToFormat.size(); i++) {
			String str = listToFormat.get(i);
			if (StringUtils.isBlank(str)) {
				// in case str is null
				continue;
			} else {
				str = str.trim();
			}
			if (inMetadata) {
				if ("{".equals(str)) {
					sb.append(str);
					isMetadataKey = true;
					continue;
				}
				if ("}".equals(str)) {
					sb.append(str);
					inMetadata = false;
					if (!"}".equals(listToFormat.get(i + 1).trim())) {
						sb.append(",");
					}
					continue;
				}
				if (isMetadataKey) {
					sb.append("\"");
					sb.append(str);
					sb.append("\":");
					isMetadataKey = !isMetadataKey;
					continue;
				} else {
					sb.append("\"");
					sb.append(str);
					sb.append("\"");
					if (!"}".equals(listToFormat.get(i + 1).trim())) {
						sb.append(",");
					}
					isMetadataKey = !isMetadataKey;
				}
			} else {
				if ("{".equals(str)) {
					sb.append(str);
					iskey = true;
					continue;
				}
				if ("}".equals(str)) {
					sb.append(str);
					result.add(sb.toString());
					sb.setLength(0);
					continue;
				}
				if (iskey) {
					if (Meta.ACD_VARIABLE_META_DATA().equals(str) && StringUtils.isBlank(listToFormat.get(i + 1))) {
						System.out.println("GWS returns \"metaData\" :null");
						if ("}".equals(listToFormat.get(i + 2).trim())) {
							System.out.println("flag");
							sb.deleteCharAt(sb.lastIndexOf(","));
						}
						continue;
					}
					sb.append("\"");
					sb.append(str);
					sb.append("\":");
					if (Meta.ACD_VARIABLE_META_DATA().equals(str)) {
						inMetadata = true;
						continue;
					}
					iskey = !iskey;
					continue;
				} else {
					sb.append("\"");
					sb.append(str);
					sb.append("\"");
					if (!"}".equals(listToFormat.get(i + 1).trim())) {
						sb.append(",");
					}
					iskey = !iskey;
				}
			}
		}
		System.out.println(result.toString());
		return result;
	}
}
