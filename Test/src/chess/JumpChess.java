package chess;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ConcurrentLinkedQueue;

public class JumpChess {

	//棋子单边的长度
	int sideLength;
	
	//棋盘单边的长度
	int maxLength;
	
	//初始棋盘
	int[][] initPlate;
	
	//最终棋盘, 用以判断棋盘是否达到最终结果
	int[][] finalPlate;
	
	//用以保存待遍历值的队列
	ConcurrentLinkedQueue<PlateState> queue = new ConcurrentLinkedQueue<PlateState>();
	
	public JumpChess(int sideLength) {
		this.sideLength = sideLength;
	}
	
	public static void main(String[] args) {
		JumpChess jc = new JumpChess(3);
		jc.singleFinish();
	}
	
	/**
	 * 单人游戏
	 */
	public void singleFinish() {
		//初始化
		initPlate();
		
		//循环广度优先查找最快的路线
		execute();
	}
	
	/**
	 * 输出每一步的路线
	 */
	private void pushResult(PlateState ps) {
//		for(int i=1;i<ps.history.size();i++) {
//			int[] history = ps.history.get(i);
//			System.out.println("Move NO."+i+" : from ["+history[0]+","+history[1]+"] to ["+history[2]+","+history[3]+"]");
//		}
		PlateState tmp = ps;
		do {
			System.out.println("Move NO."+tmp.depth+" : from ["+tmp.fromI+","+tmp.fromJ+"] to ["+tmp.i+","+tmp.j+"]");
			tmp = tmp.father;
		}
		while((tmp.father)!=null);
	}
	
	/**
	 * 初始化
	 */
	private void initPlate() {
		maxLength = 2 * sideLength + 1;
		initPlate = new int[maxLength][maxLength];
		finalPlate = new int[maxLength][maxLength];
		for(int i=0;i<maxLength;i++) {
			for(int j=0;j<maxLength;j++) {
				if((i+j) < sideLength) {
					initPlate[i][j] = 1;
					finalPlate[i][j] = 0;
				} else if((i+j) >3*sideLength) {
					finalPlate[i][j] = 1;
					initPlate[i][j] = 0;
				}else {
					initPlate[i][j] = 0;
					finalPlate[i][j] = 0;
				}
			}
		}
	}
	
	/**
	 * 广度优先搜索
	 * @return 是否找到最终结果
	 */
	private boolean execute() {
//		PlateState ps = new PlateState(0,0,0,0,initPlate,0,new ArrayList<int[]>());
		PlateState ps = new PlateState(0,0,0,0,initPlate,0,null);
		int flag = 0;
//		int loop = 0;
		do {
			for(int i=0;i<maxLength;i++) {
				for(int j=0;j<maxLength;j++) {
					if(ps.plate[i][j] == 1) {
						PlateState list = possibleMove(i,j,ps);
						if(list!=null) {
							if(list.depth>flag) {
								System.out.println("["+new SimpleDateFormat("HH:mm:ss.SSS").format(new Date())+"] move "+(++flag));
							}
							if(pushMoves(list)) {
								return true;
							}	
						}
					}
				}
			}
//			System.out.println("loop: "+(loop++) + ", queue depth: "+ queue.size());
//			System.out.println("loop: "+(loop++));
			ps = queue.poll();
		}while(ps!=null);
		return false;
	}
	
	/**
	 * 将符合的操作推送到队列中
	 * @param ps
	 * @return 当前移动是否达到最终目标
	 */
	private boolean pushMoves(PlateState ps) {
//		for(PlateState ps:list) {
			if(judgePlate(ps.plate)) {
				pushResult(ps);
				return true;
			}
			queue.add(ps);
//		}
		return false;
	}
	
	/**
	 * 判断是否最终完成
	 * @param curPlate
	 * @return
	 */
	private boolean judgePlate(int[][] curPlate) {
		for(int i=0;i<maxLength;i++) {
			for(int j=0;j<maxLength;j++) {
				if(curPlate[i][j]!=finalPlate[i][j]) {
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * 可能的移动
	 * 现只返回(i,j)棋子的最远移动 以i+j 为依据判断
	 * @param i
	 * @param j
	 * @param ps
	 * @return
	 */
	private PlateState possibleMove(int i,int j,PlateState ps) {
		int depth = i+j;
		PlateState bestMove = null;
//		List<PlateState> retPlate=new ArrayList<PlateState>();
		int[][] thisPlate = copyPlate(ps.plate);
		thisPlate[i][j] = 2;
		boolean flag = true;
		while(flag) {
			flag = false;
			for(int k = 0; k<maxLength; k++) {
				for(int l = 0; l<maxLength;l++) {
					if(thisPlate[k][l]!=0)
						continue;
					if(k-2>=0&&thisPlate[k-1][l]==1&&thisPlate[k-2][l]==2) {
						flag = true;
						thisPlate[k][l]=2;
						continue;
					}
					if(l-2>=0&&thisPlate[k][l-1]==1&&thisPlate[k][l-2]==2) {
						flag = true;
						thisPlate[k][l] = 2;
						continue;
					}
					if(l-2>=0&&k+2<maxLength&&thisPlate[k+1][l-1]==1&&thisPlate[k+2][l-2]==2) {
						flag = true;
						thisPlate[k][l]=2;
						continue;
					}
					if(l+2<maxLength&&k-2>=0&&thisPlate[k-1][l+1]==1&&thisPlate[k-2][l+2]==2) {
						flag = true;
						thisPlate[k][l]=2;
						continue;
					}
					if(l+2<maxLength&&thisPlate[k][l+1]==1&&thisPlate[k][l+2]==2) {
						flag = true;
						thisPlate[k][l]=2;
						continue;
					}
					if(k+2<maxLength&&thisPlate[k+1][l]==1&&thisPlate[k+2][l]==2) {
						flag = true;
						thisPlate[k][l]=2;
						continue;
					}
				}
			}
		}
		
		if(i+1<maxLength&&thisPlate[i+1][j] == 0) {
			thisPlate[i+1][j]=2;
		} 
		if(j+1<maxLength&&thisPlate[i][j+1]==0) {
			thisPlate[i][j+1]=2;
		}
		if(i-1>=0&&j+1<maxLength&&thisPlate[i-1][j+1]==0) {
			thisPlate[i-1][j+1]=2;
		}
		if(i+1<maxLength&&j-1>=0&&thisPlate[i+1][j-1]==0) {
			thisPlate[i+1][j-1]=2;
		}
		
		thisPlate[i][j] = 0;
		
		for(int k=0;k<maxLength;k++) {
			for(int l=0;l<maxLength;l++) {
				if(thisPlate[k][l]==2&&k+l>depth) {
					int[][] newPlate = clearPlate(copyPlate(thisPlate));
					newPlate[k][l] = 1;
					depth = k+l;
//					bestMove = new PlateState(i,j,k,l,newPlate, ps.depth +1,ps.history);
					bestMove = new PlateState(i,j,k,l,newPlate, ps.depth +1,ps);
				}
			}
		}
		return bestMove;
	}
	
	/**
	 * 复制棋盘
	 * @param curPlate
	 * @return 新的棋盘
	 */
	private int[][] copyPlate(int[][] curPlate){
		int length = curPlate.length;
		int[][] newPlate = new int[length][length];
		for(int i=0;i<length;i++) {
			for(int j=0;j<length;j++) {
				newPlate[i][j] = curPlate[i][j];
			}
		}
		return newPlate;
	}
	
	private int[][] clearPlate(int[][] curPlate){
		for(int i=0;i<curPlate.length;i++) {
			for(int j=0;j<curPlate.length;j++) {
				if(curPlate[i][j]==2){
					curPlate[i][j]=0;
				}
			}
		}
		return curPlate;
	}
	
	/**
	 * 记录一次移动 
	 * @author admin
	 */
	class PlateState {
//		List<int[]> history = new ArrayList<int[]>();
		
		//已走多少步
		int depth;
		
		//移动的目标点
		int i;
		int j;
		
		//移动的起始点
		int fromI;
		int fromJ;
		
		//棋盘当前的状态
		int[][] plate;
//		boolean finished = false;
		
		//前一步
		PlateState father;
		
//		PlateState(int fromI, int fromJ,int i, int j, int[][] plate, int depth, List<int[]> history){
//			this.fromI  = fromI;
//			this.fromJ = fromJ;
//			this.i = i;
//			this.j = j;
//			this.plate = plate;
//			this.depth = depth;
//			for(int c=0;c<history.size();c++)
//				this.history.add(history.get(c));
//			this.history.add(new int[]{fromI,fromJ,i,j});
//		}
		
		PlateState(int fromI, int fromJ,int i, int j, int[][] plate, int depth, PlateState father){
			this.fromI  = fromI;
			this.fromJ = fromJ;
			this.i = i;
			this.j = j;
			this.plate = plate;
			this.depth = depth;
			this.father = father;
		}
	}
}
