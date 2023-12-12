package fr.univ_lyon1.info.m1;

public class CharManipulator implements ICharManipulator{

    @Override
    public String invertOrder(String s) {
        char ch;
        String reverse = "";
        for (int i=0; i<s.length(); i++)
        {
            ch= s.charAt(i);
            reverse= ch + reverse;
        }
        return reverse;
    }

    @Override
    public String invertCase(String s) {
        char ch;
        String reverseCase = "";
        for (int i=0; i<s.length(); i++)
        {
            ch= s.charAt(i);
            if (Character.isLowerCase(ch)) {
                reverseCase= reverseCase + Character.toUpperCase(ch);
            }else{
                reverseCase= reverseCase + Character.toLowerCase(ch);
            }
        }
        return reverseCase;
    }

    @Override
    public String removePattern(String string, String string2) {
        return "cc"; // TODO
    }

}
