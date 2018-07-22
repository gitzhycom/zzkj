import org.junit.Test;

import java.util.Scanner;

public class Hello {
    /**
     *
     * @author Administrator
     *1.将方向ESWN换为1234，方便编程
     *2.如果输入命令超出火星高原的长宽则让小车最后接触边界点位置
     */
    private static Scanner scanner = new Scanner(System.in);
    private static Integer X;
    private static Integer Y;
    static{
        System.out.println("火星高原的左下角坐标为(0,0),右上角坐标为:'格式--> x y 请指定:'");
        String location = scanner.nextLine();
        String[] split = location.split(" ");
        X = getIntByString(split[0]);
        Y = getIntByString(split[1]);
    }
    public static void main(String[] args){
        System.out.println("请输入漫游车 1 的起始位置：（格式:x y E）");
        String location1 = scanner.nextLine();
        System.out.println("请输入漫游车 1 需要完成的命令：（格式:LRM这三个字母的组合）");
        String command1 = scanner.next();
        scanner.nextLine();
        System.out.println("请输入漫游车 2 的起始位置：（格式:x y E）");
        String location2 = scanner.nextLine();
        System.out.println("请输入漫游车 2 需要完成的命令：（格式:LRM这三个字母的组合）");
        String command2 = scanner.next();
        System.out.println("漫游车 1 的开始执行。。");
        myRun(location1,command1);
        System.out.println("漫游车 2 的开始执行。。");
        myRun(location2,command2);
    }
    public static void myRun(String location,String command){
        String[] split = location.split(" ");
        if(split.length == 3 && split[2].length() == 1
                && "ESWN".contains(split[2])){
            try {
                Integer x = getIntByString(split[0]);
                Integer y = getIntByString(split[1]);
                Integer num = getNum(split[2]);
                String success = move(x,y,num,command);
                System.out.println("执行成功，结果为："+success);
            } catch (Exception e) {
                System.out.println("您的输入不符合规范！！！。。。");
            }
        }else{
            System.out.println("您的输入不符合规范！！！。。。");
        }
    }
    /**
     *
     * @param x
     * @param y
     * @param num 代表方向（1,2,3,4）==》（E,S,W,N）
     * @param command
     * @return
     */
    public static String move(Integer x,Integer y,Integer num,String command){
        if(x <= X && y <= Y){
            for (int i=0;i<command.length();i++) {
                if("L".equals(command.charAt(i)+"")){
                    if(num==1){
                        num=4;
                    }else{
                        num--;
                    }
                }else if("R".equals(command.charAt(i)+"")){
                    if(num==4){
                        num=1;
                    }else{
                        num++;
                    }
                }else if("M".equals(command.charAt(i)+"")){
                    switch (num) {
                        case 1:
                            x++;
                            break;
                        case 2:
                            y--;
                            break;
                        case 3:
                            x--;
                            break;
                        case 4:
                            y++;
                            break;
                    }
                    if(x > X || y > Y){
                        String direction=getDirection(num);
                        return X + "\t" + Y + "\t" + direction;
                    }
                }
            }
            String direction=getDirection(num);
            return x + "\t" + y + "\t" + direction;
        }
        return "输入不合理，请检查。。";
    }
    public static String getDirection(Integer num){
        String direction=null;
        switch (num) {
            case 1:
                direction="E";
                break;
            case 2:
                direction="S";
                break;
            case 3:
                direction="W";
                break;
            case 4:
                direction="N";
                break;
        }
        return direction;
    }
    /**
     * 将方向转化为整数类型方便后续  操作
     * @param direction
     * @return
     */
    public static Integer getNum(String direction){
        Integer num=null;
        switch (direction) {
            case "E":
                num=1;
                break;
            case "S":
                num=2;
                break;
            case "W":
                num=3;
                break;
            case "N":
                num=4;
                break;
        }
        return num;
    }
    /**
     * 将一个纯数字字符串转化为Integer类型
     * @param s
     * @return
     */
    public static Integer getIntByString(String s){
        return Integer.valueOf(s);
    }
}
