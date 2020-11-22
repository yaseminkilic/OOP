import java.io.*;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.Iterator;

public class GraphTest {

	private static final int INFINITY = Integer.MAX_VALUE;
	
	/**
	 * @param args
	 * @throws IOException 
	 * @throws NumberFormatException 
	 */
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		ArrayList<Kenar> mst;
		File dosya=new File("d:\\iller.txt");
		File dosya2=new File("d:\\maliyet.txt");
		BufferedReader oku = null;
		int secim;
		Scanner sc = new Scanner(System.in);
		
		try
		{
			oku=new BufferedReader(new FileReader(dosya));
			
			
		}catch (Exception ex)
		{
			ex.getMessage();
		}
		
		int ilSayisi = Integer.parseInt(oku.readLine());
		String iller[] = new String[ilSayisi];
		String maliyet[][] = new String[ilSayisi][ilSayisi];
		int graph[][] = new int[ilSayisi][ilSayisi];
		
	
		while (oku.ready())
		{
			for (int i = 0; i<ilSayisi; i++)
			{
				iller[i]=oku.readLine();
			}
		}
		
		try
		{
			oku=new BufferedReader(new FileReader(dosya2));
		}
		catch (Exception ex)
		{
			ex.getMessage();
		}
		
		while (oku.ready())
		{
			for (int i = 0; i<ilSayisi; i++)
			{
				maliyet[i] = oku.readLine().split(" ");
				//dosyadan sat�r sat�r okunan de�erler bo�lu�a g�re ayr�l�p bir diziye at�l�yor. bu diziler de maliyet dizisine y�klenerek bir matris olu�turuluyor.
			}
		
		}
		
		for (int i = 0; i<ilSayisi; i++)
		{
			for (int j = 0; j<ilSayisi; j++)
			{
				graph[i][j] = Integer.parseInt(maliyet[i][j]);
				//string tipindeki maliyet matrisinin elemanlar� integer a d�n��t�r�l�p graph matrisine at�l�yor.
			}
		}
		//men� ba�l�yor
		do{
		System.out.println();
		System.out.println("MEN�");
		System.out.println("-------------");
		System.out.println("1- Tabloyu bast�r");
		System.out.println("2- Girilen k��eye gelen ve k��eden giden kenarlar� listele");
		System.out.println("3- Girilen k��e �ifti aras�ndaki en k�sa yolun bulunmas�");
		System.out.println("4- Geni�likli �ncelikli Dola�ma");
		System.out.println("5- En K���k Kapsayan A�a�'�n bulunmas�");
		System.out.println("Se�im: ");
		
		secim = sc.nextInt();

		switch (secim)
		{
		case 1:
		for (int i = 0; i<9; i++,System.out.print(" "));
		
		for (int i = 0; i<ilSayisi; i++)
			System.out.print(""+iller[i]+ " ");
		
		System.out.println();
		
		for (int i = 0; i<ilSayisi; i++)
		{
			System.out.print(iller[i]);
			for (int j = 0; j<ilSayisi; j++)
			{
				System.out.print("      "+graph[i][j]);
			}
			System.out.println();
		}
	    break;
	
		case 2:
		System.out.println("K��e numaras�: ");
		int kose = sc.nextInt();
		
		if (kose < 0 || kose > ilSayisi-1)
			continue;
		
		System.out.println("Girilen k��enin ad�: "+iller[kose]);
		System.out.println("Gelen Kenarlar: ");
		for (int i = 0; i<ilSayisi; i++)
		{
			if (graph[i][kose] != -1)
			System.out.print(iller[i]+": "+graph[i][kose]+"("+i+"), ");
			
		}
		System.out.println("\nGiden Kenarlar: ");
		for (int i = 0; i<ilSayisi; i++)
		{
			if (graph[kose][i] != -1)
			System.out.print(iller[i]+": "+graph[kose][i]+"("+i+"), ");
			
		}
		
		break;
		case 3:
		System.out.print("Kaynak d���m� giriniz: ");
		int source = sc.nextInt();
		System.out.print("Hedef d���m� giriniz: ");
		int target = sc.nextInt();
		
		if (source < 0 || source > ilSayisi-1 || target < 0 || target > ilSayisi-1)
			continue;
		dijkstra(ilSayisi, graph, source,target);
		break;
		case 4:
		boolean visited[] = new boolean[ilSayisi];
		for (int i = 0; i<graph[0].length; i++)
			visited[i] = false;
		System.out.println("K��e numaras�: ");
		kose = sc.nextInt();
		if (kose < 0 || kose > ilSayisi-1)
			continue;
		bfs(graph,kose,visited);
		break;
		case 5:
		ArrayList<Kenar> kenarlar = new ArrayList<Kenar>();
		
		
		for (int i = 0; i<ilSayisi; i++)
		{
			for (int j = 0; j<ilSayisi; j++)
			{
				if (graph[i][j] != -1)
				kenarlar.add(new Kenar(i,j,graph[i][j])); //kenar ad�nda olu�turdu�umuz s�n�f graphtaki t�m kenarlar� hedef kaynak ve a��rl�k �zelliklerine g�re tutuyor.
			}
		}
		
		 mst = prim(kenarlar,ilSayisi);
		 System.out.print(mst);
		 break;
	}
		}while (secim != 0);	
	}

	private static void dijkstra(int ilSayisi, int[][] graph, int source,
			int target) {
		
		boolean fin[] = new boolean[ilSayisi];
		int distance[] = new int[ilSayisi];
		
		int v=0,w,min=INFINITY;
		
		fin[source] = true;
		
		for (int i = 0; i<ilSayisi; i++)
		{
			if (i != source)
			{
				fin[i]=false;
				if (graph[source][i] != -1)
					distance[i] = graph[source][i];
				else
					distance[i]=INFINITY;
			}
			
		}
		
		for (int i = 0; i<ilSayisi; i++)
		{
			min = INFINITY;
			for (w=0; w<ilSayisi; w++)
				if (!fin[w])
					if (distance[w]<min)
					{
						v=w;
						min=distance[w];
					}
			fin[v]=true;
			if (fin[target] == true)
			{
				System.out.println("En k�sa yol: "+distance[target]);
				break;
			}
			for (w=0; w<ilSayisi; w++)
				if (!fin[w])
					if (min+graph[v][w] < distance[w])
						distance[w]=min+graph[v][w];
		
		}

					
	}

	private static ArrayList<Kenar> prim(ArrayList<Kenar> kenarlar,int ilSayisi) {
		ArrayList<Kenar> mst = new ArrayList<Kenar>();
		OncelikKuyrugu pq = new OncelikKuyrugu();
		boolean eklendi[] = new boolean[ilSayisi];
		
		for (int i = 0; i<ilSayisi; i++)
			eklendi[i]=false;
	//t�m d���mler a�aca eklenmemi� olarak i�aretleniyor.
		
		for (int i = 0; i<kenarlar.size(); i++) 
		{
			 
			if (i == 0)
			{
				Iterator<Kenar> kenarlarItr = kenarlar.iterator();
				while (kenarlarItr.hasNext())
				{
					//ilk d���m� kom�ular�na ba�layan kenarlar �ncelikli kuyru�a ekleniyor.
					Kenar simdiki = kenarlarItr.next();
					
					if (simdiki.kaynak == 0) //0 numaral� d���mden ��kan bir kenar ise
						pq.ekle(simdiki);
				
				}
			}
			Kenar cikarilan = pq.cikar();
			
			if (eklendi[cikarilan.kaynak] == false || eklendi[cikarilan.hedef] == false) //kuyruktan ��kar�lan kenar�n hedefi ya da kayna�� a�a�ta bulunmuyorsa a�aca ekleniyor.
			{
				mst.add(cikarilan);
				eklendi[cikarilan.kaynak] = true;
				eklendi[cikarilan.hedef] = true;
			}
			Iterator<Kenar> kenarlarItr = kenarlar.iterator();
			
			while (kenarlarItr.hasNext())
			{
				Kenar simdiki = kenarlarItr.next();
				
				if (simdiki.kaynak == cikarilan.hedef) //en ba�ta yap�lan i�lem di�er d���mler i�in de tekrarlan�yor.
					pq.ekle(simdiki);
			
			}
		
			if (bittiMi(eklendi))//t�m d���mler eklendiyse i�lemler sonland�r�l�yor.
				break;
		
			if (kenarlar.isEmpty() && !bittiMi(eklendi)) //�ayet izole bir d���m varsa onun da a�aca eklenmesi sa�lan�yor.
			{
				for (int a = 0; a<ilSayisi; i++)
					if(eklendi[a] == false)
					{
						eklendi[a]=true;
						mst.add(new Kenar(a,-1,-1));
					}
			}
		}
		
		return mst;
	}

	private static boolean bittiMi(boolean[] eklendi) {
		
		
		for (int i=0; i<eklendi.length; i++)
		{
			if (eklendi[i] == false)
				return false;
		}
		
		return true;
	}

	private static void bfs(int[][] graph, int kose, boolean visited[]) {
		
		Queue<Integer> kuyruk = new LinkedList<Integer>();
		

		//ilk koseyi dola��lm�� olarak i�aretleyip kuyru�a ekliyoruz.
		System.out.print(kose+",");
		visited[kose] = true;
		
		kuyruk.add(kose);
		
		while (!kuyruk.isEmpty())
		{
			int cikarilan = kuyruk.remove();
			//kuyruk bo�alana kuyru�un ba��ndan bir eleman ��kar�l�yor ve daha sonra onun dola��lmam�� kom�ular� tekrar kuyru�a ekleniyor.
			for (int i = 0; i<graph[0].length; i++)
			{
				if (graph[cikarilan][i] != -1)
				{
					if(visited[i] == false)
					{
						System.out.print(i+",");
						visited[i] = true;
						kuyruk.add(i);
					}
				}
			}
		}
		
		for (int i = 0; i<graph[0].length; i++)
			if (visited[i] == false)
				bfs(graph,i,visited);
		//bu i�lemlerin sonunda �ayet hedef kom�usu olmayan d���mler varsa onlar�n da dola��lmas� i�in �zyineleme yap�s� kullan�l�yor.
		
	}
	
}
