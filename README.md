ETL-X

Kakas bantu untuk migrasi data
Diimplementasi pada Java Web

Cara pakai:
1. upload file masukan terlebih dahulu ke sistem
2. buka editor
3. klik "New Project"
4. masukkan nama file yang sudah diupload
5. set mapping yang diinginkan
6. save mapping apabila sudah selesai
7. untuk generate mapping script, kembali ke menu awal lalu klik generate mapping script

File Web terkait
dist : Library JS dan CSS yang digunakan pada web
editor.jsp : Mapping editor
files : Penampungan file upload
index.jsp : Halaman menu utama aplikasi
jquery.dynatree-1.2.6-all : Library untuk menampilkan 'tree' pada web
upload.jsp : File upload handler
WEB-INF : Konfigurasi properti aplikasi web

File Java terkait
com.etlx.mapper
	CSVToSQL.java : konverter CSV menjadi SQL
	Dictionary.java : abstraksi dictionary pada aplikasi
	Mapping.java : abstraksi mapping + mapping script pada aplikasi
	MetaDataReader.java : parser metadata
	Ontology.java : parser + konverter ontologi
	Query.java : query handler
	QueryBuilder.java : query builder untuk ETL-X (belum diimplementasi)
com.etlx.matcher : untuk matching skema basis data
	Matcher.java
	OntoAlignment.java
	OntoMatcher.java
	SchemaAlignment.java
	SchemaMatcher.java (sudah diimplementasi)
	SchemaMatcherWOnto.java
com.etlx.metadata
	Column.java
	DB.java
	ForeignKey.java
	PrimaryKey.java
	Table.java
com.etlx.metadataparser
com.etlx.scenarios
	DBSchemaToOnto.java
	GenerateMappingScript.java
	Migrate.java
	Onto2Onto.java
	Onto2Onto2.java
	Schema2Schema.java
com.etlx.util
	StringDistances.java : algoritma penghitung perbedaan string
	Synonym.java : abstraksi sinonim pada aplikasi
com.etlx.web : web handler
	DBSchemaToOnto.java
	GenerateMappingScript.java
	JSON.java
	Mapping.java
	Migrate.java
	Schema2Schema.java