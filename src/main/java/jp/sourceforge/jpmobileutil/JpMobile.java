package jp.sourceforge.jpmobileutil;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

public final class JpMobile {

    private static final Map<MobileCarrier, String> USER_AGENT_REGEX = new HashMap<MobileCarrier, String>();
    static {
    	USER_AGENT_REGEX.put(MobileCarrier.DOCOMO, "^DoCoMo\\/\\d\\.\\d[ \\/]?");
    	USER_AGENT_REGEX.put(MobileCarrier.SOFTBANK,"^(?:(?:SoftBank|Vodafone|J-PHONE)\\/\\d\\.\\d|MOT-)");
    	USER_AGENT_REGEX.put(MobileCarrier.AU,"^(?:KDDI-[A-Z]+\\d+[A-Z]?\\s)?UP\\.Browser\\/");
    	USER_AGENT_REGEX.put(MobileCarrier.WILLCOM,"");
    }

    public static MobileDisplay getDisplay(HttpServletRequest request){

    	return new MobileDisplay();

    }

    public static String getUserAgent(HttpServletRequest request){
    	return request.getHeader("user-agent");
    }

    public static MobileCarrier getCarrier(String userAgent){
    	return detectCarrire(userAgent);
    }

    public static boolean isDocomo(String userAgent){
    	return (MobileCarrier.DOCOMO == detectCarrire(userAgent));
    }

    public static boolean isAu(String userAgent){
    	return (MobileCarrier.AU == detectCarrire(userAgent));
    }

    public static boolean isSoftbank(String userAgent){
    	return (MobileCarrier.SOFTBANK == detectCarrire(userAgent));
    }

    public static boolean isWillcom(String userAgent){
    	return (MobileCarrier.WILLCOM == detectCarrire(userAgent));
    }

    private static MobileCarrier detectCarrire(String userAgent){
    	for(Map.Entry<MobileCarrier, String> entry : USER_AGENT_REGEX.entrySet()){
    		Pattern p = Pattern.compile(entry.getValue());
    		Matcher m = p.matcher(userAgent);
    		if(m.find()){
    			return entry.getKey();
    		}
    	}
    	return MobileCarrier.UNKNOWN;
    }
}
