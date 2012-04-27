package SpriteTree;

// make sure this doesn't get pushed
public class randMain
{
    public Integer roundTheta (double theta)
    {
        Integer n = 0;
        n = (int) Math.round(theta);
        return n;
    }


    public static void main (String[] args)
    {
//		System.out.println((-18.66)-(-18.66)%10);
//		System.out.println(355-355%10);
//		System.out.println(100-100%10);
//		System.out.println(22-22%10);
        Integer n = new randMain().roundTheta(18.66);
        System.out.println(n);

    }

}
