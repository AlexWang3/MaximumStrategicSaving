package MaximumStrategicSaving;
import java.util.*;
public class MaximumStrategicSaving {
	static Scanner in=new Scanner (System.in);
	static int N,M,P,Q;
	static Path[] paths;
	static int totalCost=0;
	static int PlGroupNum;
	static int CiGroupNum;
	static int[] Planets;
	static int[] Cities;
	static int BeforeCost;
	public static void main(String[] args) {
		N=in.nextInt();//num of pl
		M=in.nextInt();//num of c
		P=in.nextInt();
		Q=in.nextInt();
		paths=new Path[P+Q];
		PlGroupNum=N;
		CiGroupNum=M;
		Planets=new int[N+1];
		Cities=new int[M+1];
		for(int i=1;i<N+1;i++) {
			Planets[i]=i;
		}
		for(int i=1;i<M+1;i++) {
			Cities[i]=i;
		}
		readPath();
		Arrays.sort(paths);
		for(Path path:paths) {
			addPath(path);
		}
		System.out.println(BeforeCost-totalCost);
	}
	private static void addPath(Path path) {
		if(getRoot(path.type,path.id1)==getRoot(path.type,path.id2))
			return;
		if(path.type) {//is planet to planet
			Planets[getRoot(path.type,path.id1)]=getRoot(path.type,path.id2);
			PlGroupNum--;
			totalCost+=path.cost*CiGroupNum;
		}
		else {//is city to city
			Cities[getRoot(path.type,path.id1)]=getRoot(path.type,path.id2);
			CiGroupNum--;
			totalCost+=path.cost*PlGroupNum;
		}
		
	}
	private static int getRoot(boolean type, int id) {
		if(type) {//is planet to planet
			if(Planets[id]==id)
				return id;
			else {
				Planets[id]=getRoot(type,Planets[id]);
				return Planets[id];
			}		
		}
		else {//is city to city
			if(Cities[id]==id)
				return id;
			else {
				Cities[id]=getRoot(type,Cities[id]);
				return Cities[id];
			}
		}
	}
	private static void readPath() {
		int a,b,c;
		for(int i=0;i<P;i++) {
			a=in.nextInt();
			b=in.nextInt();
			c=in.nextInt();
			BeforeCost+=c*N;
			paths[i]=new Path(a,b,c,false);
		}
		for(int i=P;i<P+Q;i++) {
			a=in.nextInt();
			b=in.nextInt();
			c=in.nextInt();
			BeforeCost+=c*M;
			paths[i]=new Path(a,b,c,true);
		}
		
	}
}
class Path implements Comparable{
	int id1;
	int id2;
	int cost;
	boolean type;
	public Path(int id1, int id2, int cost, boolean type) {
		this.id1=id1;
		this.id2=id2;
		this.cost=cost;
		this.type=type;		
	}
	@Override
	public int compareTo(Object o) {
		return this.cost-((Path)o).cost;
	}
}

