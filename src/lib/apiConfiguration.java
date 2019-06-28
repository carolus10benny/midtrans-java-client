package lib;

/**
 *
 * @author benny.setiawan
 */
public class apiConfiguration {
    private static boolean isProduction;
    private static String serverKey;
    private static String clearKey;
    public static final String CORE_SANDBOX_BASE_URL = "https://api.sandbox.midtrans.com/v2";
    public static final String CORE_PRODUCTION_BASE_URL = "https://api.midtrans.com/v2";
    public static final String SNAP_SANDBOX_BASE_URL = "https://app.sandbox.midtrans.com/snap/v1";
    public static final String SNAP_PRODUCTION_BASE_URL = "https://app.midtrans.com/snap/v1";
    
    public static String getSnapApiBaseUrl(){
        return isProduction ? SNAP_PRODUCTION_BASE_URL : SNAP_SANDBOX_BASE_URL;
    }

    /**
     * @return the isProduction
     */
    public boolean isIsProduction() {
        return isProduction;
    }

    /**
     * @return the serverKey
     */
    public static String getServerKey() {
        return serverKey;
    }

    /**
     * @return the clearKey
     */
    public static String getClearKey() {
        return clearKey;
    }

    /**
     * @param aIsProduction the isProduction to set
     */
    public static void setIsProduction(boolean aIsProduction) {
        isProduction = aIsProduction;
    }

    /**
     * @param aServerKey the serverKey to set
     */
    public static void setServerKey(String aServerKey) {
        serverKey = aServerKey;
    }

    /**
     * @param aClearKey the clearKey to set
     */
    public static void setClearKey(String aClearKey) {
        clearKey = aClearKey;
    }
    
}
