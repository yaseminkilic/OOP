import java.util.Vector;

public class OncelikKuyrugu {

	private Vector<Kenar> kuyrukVektor; // öncelik kuyruðunda Kisi tipi deðiþken
										// alan bir vektör kullanýlýyor.
	private int elemanSayisi;

	public OncelikKuyrugu() {
		
		kuyrukVektor = new Vector<Kenar>();
		elemanSayisi = 0;

	}

	public void ekle(Kenar eklenecekKenar) {

		if (kuyrukVektor.isEmpty()) // kuyruk boþsa en baþa ekleniyor
			kuyrukVektor.add(eklenecekKenar);
		else {
			int gecerliKapasite = kuyrukVektor.size(); // eleman eklendiðinde
														// kuyruðun boyutu
														// deðiþeceðindne
														// baþlangýçta bir
														// deðiþkene atýlýyor
			for (int i = 0; i < gecerliKapasite; i++) {
				if (eklenecekKenar.agirlik < kuyrukVektor.elementAt(i)
						.agirlik) // elemanlar küçükten büyüðe
											// ekleniyor
				{
					kuyrukVektor.add(i, eklenecekKenar);
					break;
				} else if (kuyrukVektor.elementAt(i) == kuyrukVektor
						.lastElement()) // daha büyük bir eleman yoksa sona
										// ekleniyor
				{
					kuyrukVektor.add(i + 1, eklenecekKenar);
					break;

				}
			}
		}
		elemanSayisi++;
	}

	public Kenar cikar() {
		Kenar temp = kuyrukVektor.remove(kuyrukVektor.indexOf(kuyrukVektor
				.firstElement())); // kuyruðun baþýndan eleman çýkarýlýyor.

		elemanSayisi--;
		return temp;
	}


}
