import java.util.Vector;

public class OncelikKuyrugu {

	private Vector<Kenar> kuyrukVektor; // �ncelik kuyru�unda Kisi tipi de�i�ken
										// alan bir vekt�r kullan�l�yor.
	private int elemanSayisi;

	public OncelikKuyrugu() {
		
		kuyrukVektor = new Vector<Kenar>();
		elemanSayisi = 0;

	}

	public void ekle(Kenar eklenecekKenar) {

		if (kuyrukVektor.isEmpty()) // kuyruk bo�sa en ba�a ekleniyor
			kuyrukVektor.add(eklenecekKenar);
		else {
			int gecerliKapasite = kuyrukVektor.size(); // eleman eklendi�inde
														// kuyru�un boyutu
														// de�i�ece�indne
														// ba�lang��ta bir
														// de�i�kene at�l�yor
			for (int i = 0; i < gecerliKapasite; i++) {
				if (eklenecekKenar.agirlik < kuyrukVektor.elementAt(i)
						.agirlik) // elemanlar k���kten b�y��e
											// ekleniyor
				{
					kuyrukVektor.add(i, eklenecekKenar);
					break;
				} else if (kuyrukVektor.elementAt(i) == kuyrukVektor
						.lastElement()) // daha b�y�k bir eleman yoksa sona
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
				.firstElement())); // kuyru�un ba��ndan eleman ��kar�l�yor.

		elemanSayisi--;
		return temp;
	}


}
