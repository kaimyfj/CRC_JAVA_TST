import java.io.*;
import java.util.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * Created by yangfanjing on 2017/11/1.
 */
public class Login {
    /*5.登录校验*/
    public  void checkLogin (String userName,String passWord){

        String checkUserName="YANGFANJING";
        String checkPassWord="123";

        if (checkUserName==userName&& passWord==checkPassWord)
        {
            System.out.println("登录成功");
        }

            else{   System.out.println("用户名或密码不正确！");  }

    }
    /*6.通过循环1-200之间的数，找出其中的素数并打印*/
    public void practiceNo6 (int startNum,int endNum)
    {
        for (int i = startNum; i < endNum; i++) {
            /*初始化默认素数*/
            boolean bool= true;
            /*排除1,1不是素数*/
            if (i<2)
            {
                bool= false;
            }
            else
            {
                /*循环除以比他小的数据*/
                for (int j = 2; j <i ; ++j) {
                   if(i%j==0)
                   {
                        /*一旦整除，跳出循环，不是素数*/
                        bool= false;
                        break;
                   }
                   else
                   {
                       /*没有可以整除的数，就是素数*/
                       bool= true;
                   }
                }
            }
            if (bool==true)
            {
                /*输出素数*/
                System.out.println(i);
            }
        }
    }

    /*9：找出上面无序数组的最大值以及所在元素的位置；并打印*/
    public void getMaxInArr(int arr[])
    {
       int max=arr[0];
       int maxIndex=0;
        for(int i=1;i<arr.length;i++)
        {
            if(arr[i]>max)
            {
                max=arr[i];
                maxIndex=i;
            }

        }
        System.out.println(max+"&&"+maxIndex);

    }

    /*7.生成一个大小为1000的无序数组并打印，而且数组中的元素为1-100W之间的随机整数（可重复），之后再生成一个有序的随机整数数组并打印；*/
   public void practiceNo7 () throws Exception {
       Random rand = new Random();
       int[] arrUnOrder=new int[1000];
       for (int i=0;i<arrUnOrder.length;i++)
       {
            /*获取1到100W的随机数*/
           int random=rand.nextInt(100*10000) + 1;
           arrUnOrder[i]=random;
       }

       System.out.println(Arrays.toString(arrUnOrder));
       /*9.数组中最大的数字及位置*/
       getMaxInArr(arrUnOrder);

       getrepeatInArr(arrUnOrder);
       /*8：那上面无序的随机数组做从小到大的排序，并打印；*/
       //Arrays.sort(arrUnOrder); //数组排序
       arrUnOrder=arrSort(arrUnOrder);
       System.out.println(Arrays.toString(arrUnOrder));

       Login login=new Login();
       login.writeArrToTxt(arrUnOrder);


   }


    /*10：找出上面无序数组的重复元素以及重复的个数；并打印*/
    public void getrepeatInArr(int[] nums)
    {
        /*
        Map<Integer,Integer> map=new HashMap<Integer,Integer>();
        for(int i=1;i<arr.length;i++)
        {
           int count=0;
            for (int j = 0; j <arr.length ; j++) {
                if (arr[i]==arr[j])
                {
                    count++;
                }
            }
            if (count>1)
            {
                map.put(arr[i], count);
            }
        }
       System.out.println(map.toString());
       for (Map.Entry entry :map.entrySet())
       {
           System.out.println("重复元素"+entry.getKey()+",重复个数"+entry.getValue());
       }
       */
        Map<Integer, List<Integer>> map = new LinkedHashMap<>(); // LinkedHashMap 可以维护键值对 加入 map 的顺序

        for (int i = 0; i < nums.length; i++) {
            List<Integer> positions = map.get(nums[i]);

            if (positions == null) { // 如果 map 的键 中不存在这个整数
                positions = new ArrayList<>(1);
                map.put(nums[i], positions); // 将这个整数和与其关联的位置 positions 放入 map
            }

            positions.add(i);
        }
        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            List<Integer> positions = entry.getValue();
            if (positions.size() > 1) { // 如果一个整数对应的位置数量大于 1，说明这个整数重复
                int num = entry.getKey();
                    System.out.println("重复元素："+num + "，重复个数：" + positions.size());
            }
        }
    }




    /*数组写到文本文件中*/
    public void writeArrToTxt(int arr[]) throws Exception
    {
//        FileOutputStream fos = new FileOutputStream("D:\\Arrtest.txt");
//        OutputStreamWriter osw = new OutputStreamWriter(fos, "utf-8");
//        for (int i = 0; i <arr.length ; i++) {
//            osw.write(String.valueOf(arr[i]));// 往文件里写入字符串
//            osw.write("\r\n");
//        }
//        osw.flush();

        File f = new File("D:\\Arrtest.txt");
        OutputStreamWriter writer = null;
        BufferedWriter bw = null;
        try {
            OutputStream os = new FileOutputStream(f);
            writer = new OutputStreamWriter(os);
            bw = new BufferedWriter(writer);
            for (int i = 0; i < arr.length; i++) {
                bw.write(String.valueOf(arr[i]));
                bw.write("\r\n");
            }
            bw.flush();
            if(f.exists()){
                f.delete();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /*从文本文件中读取数组*/
    public int[] readArrFromTxt() throws Exception
    {

        File file = new File("D:\\Arrtest.txt");

        StringBuilder sb = new StringBuilder();
        String s ="";
        BufferedReader br = new BufferedReader(new FileReader(file));

        int[] arr=new int[100*10000];
        int i=0;
        while( (s = br.readLine()) != null) {
            arr[i] = Integer.parseInt(s);
            i++;
        }
        br.close();
        return arr;
    }


    /*11.生成一个大小为100*10000的无序数组并打印，而且数组中的元素为1-100W之间的随机整数（可重复），之后再生成一个有序的随机整数数组并打印；*/
    public void practiceNo11 () throws Exception {
        Random rand = new Random();
        int[] arrUnOrder=new int[100*10000];
        for (int i=0;i<arrUnOrder.length;i++)
        {
            /*获取1到100W的随机数*/
            int random=rand.nextInt(100*10000) + 1;
            arrUnOrder[i]=random;
        }

        Login login=new Login();
        Arrays.sort(arrUnOrder);
        login.writeArrToTxt(arrUnOrder);
//      login.getrepeatInArr(arrUnOrder);

    }

    /*排序算法-冒泡排序，获取有序数组*/
    private int[] arrSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {

            for (int j = i; j < arr.length; j++) {

                if (arr[i] > arr[j]) {
                    int tmp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = tmp;
                }

            }
        }
        return arr;
    }



    /*调用案例*/
   public static void main(String[] arg) throws Exception
   {
       Login login=new Login();
       long startTime=System.currentTimeMillis();
       long endTime;
//       login.checkLogin("YANGFANJING","1234");
//       login.practiceNo6(1,200);
         /*第7题含8.9的输出*/
//        login.practiceNo7();
//

       /*附加题1*/
//       login.practiceNo11();
//       endTime=System.currentTimeMillis(); //获取结束时间
//       System.out.println("附加题1运行时间： "+(endTime-startTime)+"ms");
       // 附加题1运行时间： 2080ms
       // 优化后 附加题1运行时间： 642ms（主要优化写入TXT的逻辑，采用PrintStream代替原OutputStreamWriter类）
       int[] arrFromTxt= login.readArrFromTxt();

       /*附加题2*/
//       login.getrepeatInArr(arrFromTxt);
//       endTime=System.currentTimeMillis();
//       System.out.println("附加题2运行时间： "+(endTime-startTime)+"ms");
       //附加题4运行时间： 4429ms

       /*附加题3*/
//     login.getMaxInArr(arrFromTxt);
//     endTime=System.currentTimeMillis();
//     System.out.println("附加题3运行时间： "+(endTime-startTime)+"ms");
//       附加题3运行时间： 383ms

        /*附加题4*/
//       arrFromTxt=login.arrSort(arrFromTxt); //数组排序
//       Arrays.sort(arrFromTxt);
//       System.out.println(Arrays.toString(arrFromTxt));
//       endTime=System.currentTimeMillis();
//       System.out.println("附加题4运行时间： "+(endTime-startTime)+"ms");
       //附加题4运行时间： 1313ms
   }
}
