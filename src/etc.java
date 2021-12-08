class etc {
    static String typeString(String s){
        try {
            int i = Integer.parseInt(s);
            return "int";
        } catch (NumberFormatException e0){
            try {
                double j = Float.parseFloat(s);
                return "double";
            }
            catch (NumberFormatException e1){
                return "String";
            }
        }
    }
}
