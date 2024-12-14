# Kelompok-41-Project

---

## Deskripsi Proyek
**SnakeBoard** adalah permainan ular tangga klasik dua pemain yang dibuat dengan Java dan Swing. Permainan ini memiliki papan yang interaktif, animasi pion, serta fitur untuk mengatur giliran pemain, pause, dan restart permainan.

---

## Fitur Utama
1. **Papan Permainan**:
   - Papan 10x10 dengan nomor kotak dan pola warna berselang-seling.
   - Ada ular dan tangga yang memindahkan pemain ke posisi tertentu.

2. **Dadu Dinamis**:
   - Pemain mengocok dadu untuk menentukan langkah.
   - Dadu menampilkan animasi sebelum berhenti.

3. **Giliran Pemain**:
   - Giliran pemain dikelola otomatis.
   - Pemain dengan angka 6 mendapat giliran tambahan.

4. **Menu Pause**:
   - Resume permainan, restart, keluar, atau lihat panduan bermain.

5. **Tangga dan Ular**:
   - Naik tangga jika mendarat di bawahnya, turun ular jika mendarat di kepalanya.

6. **Animasi Pion**:
   - Pion bergerak kotak per kotak.

7. **Penyimpanan Pemenang**:
   - Nama pemenang disimpan di database.

---
## Penggunaan Thread
Thread digunakan untuk memastikan animasi dan pergerakan pion tidak mengganggu antarmuka pengguna (UI). Berikut adalah penerapan thread dalam permainan:

1. **Thread untuk Pengocokan Dadu**: Menggunakan thread untuk mengocok dadu secara animasi sebelum menunjukkan nilai akhir dadu.
2. **Thread untuk Pergerakan Pion**: Setiap langkah pergerakan pion dilakukan dalam thread terpisah untuk memberikan efek animasi pergerakan yang mulus.
3. **Thread untuk Pergantian Giliran**: Giliran pemain diubah di dalam thread untuk menjaga agar pergantian berjalan lancar tanpa membekukan UI.

Dengan menggunakan thread, permainan tetap responsif meskipun ada animasi yang berjalan.

---
## Cara Bermain
1. **Mulai permainan**: Pemain pertama yang mencapai kotak 100 menang.
2. **Aturan Dadu**:
   - Pemain melempar dadu dan bergerak sesuai angka.
   - Jika dadu menunjukkan angka 6, pemain mendapat giliran tambahan.
3. **Tangga dan Ular**:
   - Naik tangga atau turun ular sesuai posisi.

---

## Teknologi yang Digunakan
1. **Bahasa Pemrograman**: Java
2. **Antarmuka Grafis**: Swing
3. **Database**: Untuk menyimpan data pemenang.
4. **Paradigma Pemrograman**: Berorientasi Objek (OOP)

---

## Kontributor
- **[Muh. Ressa Arsy Ma'rif]**
- **[Talyta Alyadia Azkiah]**
- **[Arnetha Nirwana Putri]**
- **[Muhammad Daffa Dzaki Ahnaf]**

---