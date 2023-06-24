# Word Count Comparison
Perbandingan program Wordcount antara menggunakan framework Hadoop dan tidak menggunakan framework hanya java saja.
## Anggota Kelompok
- Akmal Rabbani - 2106731610
- Arka Brian Dewara - 2106731421
- Muhammad Farrel Mirawan - 2106731554
- Nevanda Fairuz Pahlevi - 2106731541

## Apa itu Hadoop ?
Hadoop adalah kerangka kerja open-source yang dirancang untuk mengelola dan menganalisis data yang sangat besar (big data). Hadoop terdiri dari dua komponen utama: Hadoop Distributed File System (HDFS) dan Apache MapReduce.

**Kelebihan**
- Skalabilitas: Hadoop dapat dengan mudah dikembangkan secara horizontal, sehingga dapat mengelola data lebih besar
- Efisiensi penyimpanan: Hadoop menggunakan HDFS yang memecah data menjadi blok-blok kecil dan mendistribusikannya di seluruh kluster, sehingga mengoptimalkan memori
- Pemrosesan data paralel: Hadoop menggunakan pendekatan MapReduce yang memungkinkan pemrosesan data secara paralel di berbagai node.

**Kekurangan**
- Kompleksitas: Pengaturan dan konfigurasi awal Hadoop dapat rumit dan memerlukan pemahaman yang mendalam tentang sistem distribusi.
- Keterbatasan analisis real-time: Meskipun Hadoop efisien dalam pemrosesan batch data besar, ia tidak terlalu cocok untuk analisis real-time yang membutuhkan pemrosesan instan.
- Kesesuaian data: Hadoop lebih sesuai untuk data yang terstruktur dan semi-terstruktur daripada data yang tidak terstruktur.

## Installasi Hadoop
1. Download Prerequisite
- Download [Java 8](https://www.oracle.com/id/java/technologies/javase/javase8-archive-downloads.html)
- Download [Hadoop](https://archive.apache.org/dist/hadoop/common/)
- Downloand [Additional Binary](https://github.com/cdarlint/winutils) yang sesuai dengan versi Hadoop
2. Install Java dan konfigurasi path variable
3. Extract hadoop
4. Konfigurasi Hadoop
- Edit core-site.xml

        <configuration>
            <property>
                <name>fs.defaultFS</name>
                <value>hdfs://localhost:9000</value>
             </property>
        </configuration>

- Edit mapred-site.xml

        <configuration>
            <property>
                <name>mapreduce.framework.name</name>
                <value>yarn</value>
            </property>
        </configuration>

- Edit yarn-site.xml

        <configuration>
            <property>
                <name>yarn.nodemanager.aux-services</name>
                <value>mapreduce_shuffle</value>
            </property>
            <property>
                <name>yarn.nodemanager.aux-services.mapreduce.shuffle.class</name>
                <value>org.apache.hadoop.mapred.ShuffleHandler</value>
            </property>
        </configuration>

- Edit hdfs-site.xml

        <configuration>
        <property>
            <name>dfs.replication</name>
            <value>1</value>
        </property>

        <property>
            <name>dfs.namenode.name.dir</name>
            <value>C:\hadoop-3.2.2\data\namenode</value>
        </property>

        <property>
            <name>dfs.datanode.data.dir</name>
            <value>C:\hadoop-3.2.2\data\datanode</value>
        </property>
        </configuration>

- Edit hadoop-env.cmd
        
        set JAVA_HOME = "Path_Variable"
5. Verifikasi apakah hadoop terinstall dengan benar

         hadoop version
6. Format untuk folder namenode

        hdfs namenode -format

## Perbandingan Wordcount Antara Hadoop dan Java
### Hardware yang digunakan
CPU : Intel(R) Core(TM) i7-7500U CPU @ 2.70GHz2.90 GHz

RAM : 8,00 GB
### Ukuran file yang diuji
- 1 Mb 
- 10 Mb
- 100 Mb 
- 500 Mb 
- 1 Gb 
- 10 Gb 
### Hasil Eksperimen
| Size   | Hadoop    | Java      |
|--------|-----------|-----------|
| 1 Mb   | 14.248 S  | 0.11 S    |
| 10 Mb  | 17.266 S  | 0.509 S   |
| 100 Mb | 42.591 S  | 5.774 S   |
| 500 Mb | 91.074 S  | 29.594 S  |
| 1 Gb   | 191.408 S | 51.647 S  |
| 10 Gb  | 806 S     | 628.307 S |
### Grafik
![Screenshot 2023-06-22 145956](https://github.com/farrelmrwn/WordCountComparison/assets/87571919/c9192e66-f90e-4985-91e8-f1d628fb9d83)
![Screenshot 2023-06-22 150020](https://github.com/farrelmrwn/WordCountComparison/assets/87571919/3fb1945f-3236-4448-b659-42bf5fe97736)

## Analisis
Percobaan perbandingan antara kecepatan program worcount dengan menggunakan hadoop mapreduce dan java sudah dilakukan. Yang dimana Java lebih cepat daripada Hadoop dalam kebanyakan kasus, kecuali untuk file 10GB di mana perbedaan kecepatan tidak signifikan.
Ada beberapa faktor yang dapat menjelaskan mengapa Hadoop bisa membutuhkan lebih banyak waktu daripada Java dalam beberapa skenario:
- Overhead Hadoop
  Hadoop memiliki beberapa overhead tambahan yang harus diatasi dalam lingkungan yang didistribusikan. Proses ini dapat memakan waktu yang signifikan, terutama dalam kasus pengolahan data yang relatif kecil seperti file-file berukuran 1MB hingga 1GB. Jadi, dalam file kecil ini, overhead Hadoop menyebabkan waktu eksekusi yang lebih lama.
- Latensi jaringan
  Dalam Hadoop, tugas-tugas pemetaan (mapping) dan pengurutan (reducing) dijalankan pada beberapa mesin dalam sebuah cluster. Komunikasi antara tugas-tugas ini melalui jaringan membutuhkan waktu yang lebih lama dibandingkan dengan pemrosesan lokal dalam Java biasa
- Didesain untuk big data
  Hadoop dirancang untuk menangani pemrosesan data yang sangat besar. Dalam kasus-kasus di mana ukuran file yang diuji cukup besar (diatas 10GB), Hadoop dapat menghasilkan hasil runtime yang lebih cepat dibanding dengan Java biasa. Karena Hadoop dapat memanfaatkan distribusi dan paralelisasi data untuk memproses file yang sangat besar dengan memecahnya menjadi bagian-bagian yang lebih kecil dan memprosesnya secara bersamaan.

## Kesimpulan
Dari percobaan yang telah dilakukan didapat kesimpulan bahwa Hadoop MapReduce lebih lambat daripada program Java biasa dalam pengolahan data berukuran kecil (dibawah 10GB) karena overhead Hadoop dan latensi jaringan. Namun, saat ukuran file menjadi sangat besar (diatas 10GB), Hadoop dapat memberikan keuntungan dalam hal skalabilitas dan memanfaatkan arsitektur distribusi untuk memproses data dengan efisien sehingga akan lebih cepat dibanding Java pada data yang sangat besar.

