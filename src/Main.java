import java.util.ArrayList;
import java.util.Scanner;

class Lagu {
    int kode;
    String judul;
    String artis;
    int durasi;

    public Lagu(int kode, String judul, String artis, int durasi) {
        this.kode = kode;
        this.judul = judul;
        this.artis = artis;
        this.durasi = durasi;
    }
}

class Playlist {
    ArrayList<Lagu> daftarLagu = new ArrayList<>();

    // Tambah lagu
    public void tambahLagu(Lagu lagu) {
        daftarLagu.add(lagu);
    }

    // Tampilkan playlist
    public void tampilkan() {
        System.out.println("\n===== PLAYLIST =====");
        for (Lagu l : daftarLagu) {
            System.out.println(l.kode + " | " + l.judul + " | " + l.artis + " | " + l.durasi + " detik");
        }
        System.out.println("=====================\n");
    }

    // ============================
    // QUICK SORT (kode)
    // ============================
    public void quickSortKode(int low, int high) {
        if (low < high) {
            int pi = partitionKode(low, high);
            quickSortKode(low, pi - 1);
            quickSortKode(pi + 1, high);
        }
    }

    private int partitionKode(int low, int high) {
        int pivot = daftarLagu.get(high).kode;
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (daftarLagu.get(j).kode < pivot) {
                i++;
                Lagu temp = daftarLagu.get(i);
                daftarLagu.set(i, daftarLagu.get(j));
                daftarLagu.set(j, temp);
            }
        }

        Lagu temp = daftarLagu.get(i + 1);
        daftarLagu.set(i + 1, daftarLagu.get(high));
        daftarLagu.set(high, temp);

        return i + 1;
    }

    // ============================
    // QUICK SORT (durasi)
    // ============================
    public void quickSortDurasi(int low, int high) {
        if (low < high) {
            int pi = partitionDurasi(low, high);
            quickSortDurasi(low, pi - 1);
            quickSortDurasi(pi + 1, high);
        }
    }

    private int partitionDurasi(int low, int high) {
        int pivot = daftarLagu.get(high).durasi;
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (daftarLagu.get(j).durasi < pivot) {
                i++;
                Lagu temp = daftarLagu.get(i);
                daftarLagu.set(i, daftarLagu.get(j));
                daftarLagu.set(j, temp);
            }
        }

        Lagu temp = daftarLagu.get(i + 1);
        daftarLagu.set(i + 1, daftarLagu.get(high));
        daftarLagu.set(high, temp);

        return i + 1;
    }

    // ============================
    // BINARY SEARCH (kode)
    // ============================
    public Lagu binarySearch(int kodeDicari) {
        quickSortKode(0, daftarLagu.size() - 1);

        int left = 0;
        int right = daftarLagu.size() - 1;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (daftarLagu.get(mid).kode == kodeDicari) {
                return daftarLagu.get(mid);
            } else if (daftarLagu.get(mid).kode < kodeDicari) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return null;
    }

    // ============================
    // Rekursi total durasi
    // ============================
    public int totalDurasiRekursif(int i) {
        if (i == daftarLagu.size()) return 0;
        return daftarLagu.get(i).durasi + totalDurasiRekursif(i + 1);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Playlist playlist = new Playlist();

        // ==============================
        // 30 DATA LAGU
        // ==============================
        playlist.tambahLagu(new Lagu(10, "Blinding Lights", "The Weeknd", 200));
        playlist.tambahLagu(new Lagu(11, "Save Your Tears", "The Weeknd", 215));
        playlist.tambahLagu(new Lagu(12, "Starboy", "The Weeknd", 230));

        playlist.tambahLagu(new Lagu(13, "Shape of You", "Ed Sheeran", 240));
        playlist.tambahLagu(new Lagu(14, "Perfect", "Ed Sheeran", 263));
        playlist.tambahLagu(new Lagu(15, "Bad Habits", "Ed Sheeran", 231));

        playlist.tambahLagu(new Lagu(16, "Stay", "The Kid LAROI", 140));
        playlist.tambahLagu(new Lagu(17, "Thousand Miles", "The Kid LAROI", 154));
        playlist.tambahLagu(new Lagu(18, "Without You", "The Kid LAROI", 178));

        playlist.tambahLagu(new Lagu(19, "Peaches", "Justin Bieber", 198));
        playlist.tambahLagu(new Lagu(20, "Ghost", "Justin Bieber", 152));
        playlist.tambahLagu(new Lagu(21, "Sorry", "Justin Bieber", 200));

        playlist.tambahLagu(new Lagu(22, "Love Story", "Taylor Swift", 234));
        playlist.tambahLagu(new Lagu(23, "Anti-Hero", "Taylor Swift", 201));
        playlist.tambahLagu(new Lagu(24, "Blank Space", "Taylor Swift", 231));

        playlist.tambahLagu(new Lagu(25, "Bad Guy", "Billie Eilish", 194));
        playlist.tambahLagu(new Lagu(26, "Lovely", "Billie Eilish", 200));
        playlist.tambahLagu(new Lagu(27, "Everything I Wanted", "Billie Eilish", 245));

        playlist.tambahLagu(new Lagu(28, "7 Rings", "Ariana Grande", 179));
        playlist.tambahLagu(new Lagu(29, "Thank U, Next", "Ariana Grande", 207));
        playlist.tambahLagu(new Lagu(30, "Positions", "Ariana Grande", 173));

        // tampil daftar awal
        playlist.tampilkan();

        while (true) {
            System.out.println("===== MENU =====");
            System.out.println("1. Tambah Lagu");
            System.out.println("2. Cari Lagu (Binary Search by Kode)");
            System.out.println("3. Urutkan berdasarkan Kode");
            System.out.println("4. Urutkan berdasarkan Durasi");
            System.out.println("5. Total Durasi Playlist");
            System.out.println("6. Keluar");
            System.out.print("Pilih menu: ");
            int menu = sc.nextInt();
            sc.nextLine();

            switch (menu) {
                case 1:
                    System.out.print("Kode Lagu: ");
                    int kode = sc.nextInt(); sc.nextLine();
                    System.out.print("Judul Lagu: ");
                    String judul = sc.nextLine();
                    System.out.print("Artis: ");
                    String artis = sc.nextLine();
                    System.out.print("Durasi: ");
                    int durasi = sc.nextInt();

                    playlist.tambahLagu(new Lagu(kode, judul, artis, durasi));
                    System.out.println("Lagu berhasil ditambahkan!");

                    playlist.tampilkan();
                    break;

                case 2:
                    System.out.print("Masukkan kode lagu: ");
                    int cari = sc.nextInt();
                    Lagu hasil = playlist.binarySearch(cari);

                    if (hasil != null) {
                        System.out.println("\nLagu ditemukan:");
                        System.out.println(hasil.kode + " | " + hasil.judul + " | " + hasil.artis + " | " + hasil.durasi + " detik");
                    } else {
                        System.out.println("\nLagu tidak ditemukan.");
                    }

                    playlist.tampilkan();
                    break;

                case 3:
                    playlist.quickSortKode(0, playlist.daftarLagu.size() - 1);
                    System.out.println("Playlist berhasil diurutkan berdasarkan kode!");
                    playlist.tampilkan();
                    break;

                case 4:
                    playlist.quickSortDurasi(0, playlist.daftarLagu.size() - 1);
                    System.out.println("Playlist berhasil diurutkan berdasarkan durasi!");
                    playlist.tampilkan();
                    break;

                case 5:
                    int total = playlist.totalDurasiRekursif(0);
                    System.out.println("Total durasi playlist: " + total + " detik");
                    playlist.tampilkan();
                    break;

                case 6:
                    System.out.println("Program selesai.");
                    return;

                default:
                    System.out.println("Menu tidak valid!");
            }
        }
    }
}
