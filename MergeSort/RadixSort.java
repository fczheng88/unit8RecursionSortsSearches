import java.util.*;
public class RadixSort
{
    // instance variables - replace the example below with your own
    private int[] nums;
    private ArrayList[] buckets;
    public RadixSort(int[] numberArray)
    {
        nums = numberArray;
        buckets = new ArrayList[10];
        for(int i = 0; i<buckets.length; i++)
        {
            buckets[i] = new ArrayList<Integer>();
        }
    }

    public int[] sort()
    {
        int maxLength = findMaxLength();
        for(int i = 0; i < maxLength; i++)
        {
            for(int j: nums) //nums to bucket
            {
                String jString = Integer.toString(j);
                while(jString.length() < maxLength)
                {
                    jString = "0"+jString;
                }
                String sigDigit = jString.substring(jString.length()-i-1, jString.length()-i);
                int sigDig = Integer.parseInt(sigDigit);
                buckets[sigDig].add(j);
            }
            int numsNextPos = 0;
            for(ArrayList<Integer> list : buckets)
            {
                for(int l : list)
                {
                    nums[numsNextPos] = l;
                    numsNextPos++;
                }
            }
            for(int j = 0; j<buckets.length; j++)
            {
                buckets[j] = new ArrayList<Integer>();
            }
        }
        return nums;
    }

    public int findMaxLength()
    {
        int maxLength = 0;
        for(int i : nums)
        {
            String iString = Integer.toString(i);
            if(iString.length()>maxLength)
            {
                maxLength = iString.length();
            }
        }
        return maxLength;
    }

    public void swapElements(int index1, int index2)
    {
        int x = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = x;
    }

    public static void main(String[] args)
    {
        int[] randArray = ArrayUtil.randomIntArray(1000, 10000);
        System.out.println(Arrays.toString(randArray));
        RadixSort sorter = new RadixSort(randArray);
        System.out.println(Arrays.toString(sorter.sort()));
    }
}
