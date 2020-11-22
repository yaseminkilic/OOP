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
				//dosyadan satýr satýr okunan deðerler boþluða göre ayrýlýp bir diziye atýlýyor. bu diziler de maliyet dizisine yüklenerek bir matris oluþturuluyor.
			}
		
		}
		
		for (int i = 0; i<ilSayisi; i++)
		{
			for (int j = 0; j<ilSayisi; j++)
			{
				graph[i][j] = Integer.parseInt(maliyet[i][j]);
				//string tipindeki maliyet matrisinin elemanlarý integer a dönüþtürülüp graph matrisine atýlýyor.
			}
		}
		//menü baþlýyor
		do{
		System.out.println();
		System.out.println("MENÜ");
		System.out.println("-------------");
		System.out.println("1- Tabloyu bastýr");
		System.out.println("2- Girilen köþeye gelen ve köþeden giden kenarlarý listele");
		System.out.println("3- Girilen köþe çifti arasýndaki en kýsa yolun bulunmasý");
		System.out.println("4- Geniþlikli Öncelikli Dolaþma");
		System.out.println("5- En Küçük Kapsayan Aðaç'ýn bulunmasý");
		System.out.println("Seçim: ");
		
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
		System.out.println("Köþe numarasý: ");
		int kose = sc.nextInt();
		
		if (kose < 0 || kose > ilSayisi-1)
			continue;
		
		System.out.println("Girilen köþenin adý: "+iller[kose]);
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
		System.out.print("Kaynak düðümü giriniz: ");
		int source = sc.nextInt();
		System.out.print("Hedef düðümü giriniz: ");
		int target = sc.nextInt();
		
		if (source < 0 || source > ilSayisi-1 || target < 0 || target > ilSayisi-1)
			continue;
		dijkstra(ilSayisi, graph, source,target);
		break;
		case 4:
		boolean visited[] = new boolean[ilSayisi];
		for (int i = 0; i<graph[0].length; i++)
			visited[i] = false;
		System.out.println("Köþe numarasý: ");
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
				kenarlar.add(new Kenar(i,j,graph[i][j])); //kenar adýnda oluþturduðumuz sýnýf graphtaki tüm kenarlarý hedef kaynak ve aðýrlýk özelliklerine göre tutuyor.
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
				System.out.println("En kýsa yol: "+distance[target]);
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
	//tüm düðümler aðaca eklenmemiþ olarak iþaretleniyor.
		
		for (int i = 0; i<kenarlar.size(); i++) 
		{
			 
			if (i == 0)
			{
				Iterator<Kenar> kenarlarItr = kenarlar.iterator();
				while (kenarlarItr.hasNext())
				{
					//ilk düðümü komþularýna baðlayan kenarlar öncelikli kuyruða ekleniyor.
					Kenar simdiki = kenarlarItr.next();
					
					if (simdiki.kaynak == 0) //0 numaralý düðümden çýkan bir kenar ise
						pq.ekle(simdiki);
				
				}
			}
			Kenar cikarilan = pq.cikar();
			
			if (eklendi[cikarilan.kaynak] == false || eklendi[cikarilan.hedef] == false) //kuyruktan çýkarýlan kenarýn hedefi ya da kaynaðý aðaçta bulunmuyorsa aðaca ekleniyor.
			{
				mst.add(cikarilan);
				eklendi[cikarilan.kaynak] = true;
				eklendi[cikarilan.hedef] = true;
			}
			Iterator<Kenar> kenarlarItr = kenarlar.iterator();
			
			while (kenarlarItr.hasNext())
			{
				Kenar simdiki = kenarlarItr.next();
				
				if (simdiki.kaynak == cikarilan.hedef) //en baþta yapýlan iþlem diðer düðümler için de tekrarlanýyor.
					pq.ekle(simdiki);
			
			}
		
			if (bittiMi(eklendi))//tüm düðümler eklendiyse iþlemler sonlandýrýlýyor.
				break;
		
			if (kenarlar.isEmpty() && !bittiMi(eklendi)) //þayet izole bir düðüm varsa onun da aðaca eklenmesi saðlanýyor.
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
		

		//ilk koseyi dolaþýlmýþ olarak iþaretleyip kuyruða ekliyoruz.
		System.out.print(kose+",");
		visited[kose] = true;
		
		kuyruk.add(kose);
		
		while (!kuyruk.isEmpty())
		{
			int cikarilan = kuyruk.remove();
			//kuyruk boþalana kuyruðun baþýndan bir eleman çýkarýlýyor ve daha sonra onun dolaþýlmamýþ komþularý tekrar kuyruða ekleniyor.
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
		//bu iþlemlerin sonunda þayet hedef komþusu olmayan düðümler varsa onlarýn da dolaþýlmasý için özyineleme yapýsý kullanýlýyor.
		
	}
	
}
