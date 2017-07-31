package dz_lesson17;

public class Solution {
    public static void main(String[] args) {
        String str = "http://www.test99.com";

        System.out.println(str);
        System.out.println(validate(str));
    }

    public static boolean validate(String address){
        if (address == null || address.isEmpty())
            return false;

        address = address.trim();

        if (!address.startsWith("http://") && !address.startsWith("https://"))
            return false;

        if (!address.endsWith(".com") && !address.endsWith(".net") && !address.endsWith(".org"))
            return false;

        address = address.replaceAll("www.", "");
        address = address.replaceAll("http://", "");
        address = address.replaceAll("https://", "");
        address = address.replaceAll(".com", "");
        address = address.replaceAll(".net", "");
        address = address.replaceAll(".org", "");

        return chek(address);
    }

    private static boolean chek(String body){
        char[] chars = body.trim().toCharArray();
        for (char c : chars) {
            if (!Character.isLetterOrDigit(c)){
                return false;
            }
        }
        return true;
    }
}
