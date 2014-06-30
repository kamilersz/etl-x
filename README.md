# ETL-X

Kakas bantu untuk migrasi data

Diimplementasi pada Java Web

##Cara pakai

1. Upload file masukan terlebih dahulu ke sistem
2. Buka editor
3. Klik "New Project"
4. Masukkan nama file yang sudah diupload
5. Klik "Load Candidate"
6. Set mapping yang diinginkan
7. Save mapping apabila sudah selesai
8. Untuk generate mapping script, kembali ke menu awal lalu klik generate mapping script

###File Web terkait
- dist : Library JS dan CSS yang digunakan pada web
- editor.jsp : Mapping editor
- files : Penampungan file upload
- index.jsp : Halaman menu utama aplikasi
- jquery.dynatree-1.2.6-all : Library untuk menampilkan 'tree' pada web
- upload.jsp : File upload handler
- WEB-INF : Konfigurasi properti aplikasi web

###File Java terkait
- com.etlx.mapper
 - CSVToSQL.java : konverter CSV menjadi SQL
 - Dictionary.java : abstraksi dictionary pada aplikasi
 - Mapping.java : abstraksi mapping + mapping script pada aplikasi
 - MetaDataReader.java : parser metadata
 - Ontology.java : parser + konverter ontologi
 - Query.java : query handler
 - QueryBuilder.java : query builder untuk ETL-X (belum diimplementasi)
- com.etlx.matcher : untuk matching skema basis data
 - Matcher.java: Kelas dasar algoritma pencocokan
 - OntoAlignment.java: (tidak dipakai)
 - OntoMatcher.java: Pencocokan antara dua buah ontologi
 - SchemaAlignment.java: (tidak dipakai)
 - SchemaMatcher.java: Pencocokan antara dua buah skema basis data (sudah diimplementasi)
 - SchemaMatcherWOnto.java: Pencocokan antara dua buah skema basis data menggunakan ontologi (belum diimplementasi)
- com.etlx.metadata: Abstraksi skema basis data dalam bentuk objek
 - Column.java: Abstraksi kolom
 - DB.java: Abstraksi basis data
 - ForeignKey.java: Abstraksi foreign key
 - PrimaryKey.java: Abstraksi Primary Key
 - Table.java: Abstraksi tabel
- com.etlx.metadataparser: Parser untuk membaca _DDL script_ menjadi metadata
- com.etlx.scenarios: Interface CLI
 - DBSchemaToOnto.java
 - GenerateMappingScript.java
 - Migrate.java
 - Onto2Onto.java
 - Onto2Onto2.java
 - Schema2Schema.java
- com.etlx.util: Untuk 
 - StringDistances.java : algoritma penghitung perbedaan string
 - Synonym.java : abstraksi sinonim pada aplikasi
- com.etlx.web : web handler untuk mengakses tools
 - DBSchemaToOnto.java
 - GenerateMappingScript.java
 - JSON.java
 - Mapping.java
 - Migrate.java
 - Schema2Schema.java
