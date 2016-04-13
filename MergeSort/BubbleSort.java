import java.util.Arrays;
public class BubbleSort
{
    // instance variables - replace the example below with your own
    private int[] nums;

    public BubbleSort(int[] numberArray)
    {
        nums = numberArray;
    }

    public int[] sort()
    {
        boolean isSorted = false;
        while(!isSorted)
        {
            isSorted=true;
            for(int i = 0; i<nums.length-1; i++)
            {
                if(nums[i]>nums[i+1])
                {
                    swapElements(i, i+1);
                    isSorted=false;
                }
                
            }
        }
        return nums;
    }

    public void swapElements(int index1, int index2)
    {
        int x = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = x;
    }
    
    public static void main(String[] args)
    {
        int[] randArray = ArrayUtil.randomIntArray(10, 10);
        System.out.println(Arrays.toString(randArray));
        BubbleSort sorter = new BubbleSort(randArray);
        System.out.println(Arrays.toString(sorter.sort()));
    }
}
