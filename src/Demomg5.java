import java.util.LinkedList;
import java.util.Scanner;

/**
 * 本程序主要运用递归思想，
 * 为了防止走回头路或者走死循环我就把它所经过的路都修改为1及不能再走
 * 由于其要走完所有可能所以要走过后回来
 * 有4个方向
 * @author Administrator
 *
 */
class Point{//定义坐标里面有x，y
    public int x;
    public int y;
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Point() {
    }

}
public class Demomg5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入坐标数用空格分开：");
        int a = sc.nextInt();
        int b= sc.nextInt();
        Point start =new Point(0,0);
        Point end = new Point(a,b);
        int[][] maze = null;
        LinkedList<Point> list = new LinkedList<Point>();
        maze=init();//初始化迷宫
        long startTime = System.currentTimeMillis();
        findWay(list,maze,start,end);//开始寻找出口
        long endTime = System.currentTimeMillis();
        System.out.println("用时为"+(endTime-startTime)/1000f+"秒");
    }



    public static void findWay(LinkedList<Point> list, int[][] maze, Point start,Point end) {
        // TODO Auto-generated method stub
        if(start.x==end.x&&start.y==end.y){//如果现在位子等于终点位子输出
            printOut(maze,list);
            return; //结束这一步
        }else{
            for(int i=0;i<4;i++){//有4个方向使其都可以走到
                Point p2 = dothere(i,start,maze);//试探的在某个方向上走动
                if(p2==null&&i!=3)//走不了但还有几个或一个方向没试探
                    continue;
                if(p2==null&&i==3){//走不了并且所有的方向都试探了
                    return;
                }
                list.addLast(p2);
                findWay(list,maze,p2,end);//走的了在开始下一步走
                if(list.size()!=0){//把原先该变走过得路径还原
                    Point ppt=list.removeLast();
                    maze[ppt.x][ppt.y]=0;
                }
            }

        }
    }

    private static void printOut(int[][] maze,LinkedList<Point> list) {
        System.out.print("("+0+","+0+")");
        for(Point p:list){
            System.out.print("-->"+"("+p.x+","+p.y+")");
        }
        System.out.println();
        return;
    }

    public static int[][] init() {
        // TODO Auto-generated method stub
        int[][] arr={
                {0,1,0,1,0,0,0},
                {0,0,1,0,0,1,0},
                {1,0,0,1,0,1,0},
                {0,0,1,0,0,1,0},
                {0,0,1,1,0,1,0},
                {1,0,0,0,0,0,0}
        };
        return arr;
    }

    public static Point dothere(int i,Point p,int[][] arr){
        boolean flag = false;
        int x=p.x,y=p.y;
        if(i==0){  //向着走试探走
            if(x-1>=0&&arr[x-1][y]==0){//走的了
                arr[x][y]=1;  //把上一步路径设为不能走
                x--;        //走动
            }else{
                flag = true;    //不能走则将不能走标记设为true
            }
        }else if(i==1){
            if(y-1>=0&&arr[x][y-1]==0){
                arr[x][y]=1;
                y--;
            }else{
                flag = true;
            }
        }else if(i==2){
            if(x+1<arr.length&&arr[x+1][y]==0){
                arr[x][y]=1;
                x++;
            }else{
                flag = true;
            }
        }else if(i==3){
            if(y+1<arr[1].length&&arr[x][y+1]==0){
                arr[x][y]=1;
                y++;
            }else{
                flag = true;
            }
        }
        if(flag) return null; //如果不能走返回null
        return new Point(x,y);//如果能走返回新的坐标
    }
}


