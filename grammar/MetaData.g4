grammar MetaData;

@header {
import java.util.ArrayList;
import java.util.List;

}

table_list returns [List tables, List pk, List fk]
	: {$tables = new ArrayList();
	$pk = new ArrayList();
	$fk = new ArrayList();} (
		table_def { $tables.add($table_def.tbl); } |
		prompt_def |
		alter_table_fk {$fk.add($alter_table_fk.fk);} |
		alter_table_pk {$pk.add($alter_table_pk.pk);} |
		drop_statement
	)*;
	
//IF_STATEMENT : 'IF' .* 'END' {skip();};
//SL_COMMENT	: ( ('--'|'#') ~('\n'|'\r')* '\r'? '\n' ) {skip();} ;
//ML_COMMENT	: '/*' .* '*/' {skip();} ;

drop_statement
	: 'DROP' 'TABLE' table_name SEMICOLON;

alter_table_fk returns [ForeignKey fk]
	: 'ALTER' 'TABLE' table_name 
	'ADD' LEFT_PAREN 'CONSTRAINT' ID {$fk = new ForeignKey($ID.text); $fk.setTableFrom($table_name.text); } 
	'FOREIGN' 'KEY' column_list {$fk.setColFrom($column_list.cols);} 
	'REFERENCES' table_name column_list {$fk.setTableTo($table_name.text); $fk.setColTo($column_list.cols);} 
	RIGHT_PAREN SEMICOLON?;

alter_table_pk returns [PrimaryKey pk]
	: 'ALTER' 'TABLE' table_name 
	'ADD' LEFT_PAREN 'CONSTRAINT' ID {$pk = new PrimaryKey($ID.text); $pk.setTable($table_name.text); } 
	'PRIMARY' 'KEY' column_list {$pk.setCol($column_list.cols);} 
	RIGHT_PAREN SEMICOLON?;

prompt_def : ('PROMPT' 'CREATING' 'TABLE' ID) | ('PROMPT' 'CREATING' 'PRIMARY' 'KEY' ID) | ('PROMPT' 'CREATING' 'FOREIGN' 'KEY' 'CONSTRAINT' ID);

column_list returns [List cols]
	: LEFT_PAREN {$cols = new ArrayList();} cn=column_name {$cols.add($cn.cn);} (COMMA cn=column_name {$cols.add($cn.cn);})* RIGHT_PAREN;

table_def returns [Table tbl]
	: 'CREATE' ('table' | 'TABLE') (KUTIP)? table_name (KUTIP)?
		table_element_list {
			$tbl = new Table(String.valueOf($table_name.text), $table_element_list.cols);
		}
		SEMICOLON?;

table_name
	: (schema DOT)? table;

table_element_list returns [List cols]
	: {$cols = new ArrayList();} LEFT_PAREN te=table_element {$cols.add($te.col);} (COMMA te=table_element {$cols.add($te.col);})*  (COMMA primary_constraint2)? RIGHT_PAREN;

table_element returns [Column col]
	: (KUTIP)? column_name (KUTIP)? data_type_def (column_constraint)? (primary_constraint)? (column_constraint)? (default_value)? {$col = new Column($column_name.cn, $data_type_def.tpe, $column_constraint.text, $data_type_def.len, $data_type_def.prec);};

column_name returns [String cn]
	: '['? ID ']'? {$cn = String.valueOf($ID.text); };

column_constraint
	: 'NOT'? 'NULL';

default_value
	: 'DEFAULT' (ID | 'NULL' | NUMBER | DECIMAL | (KUTIPSATU (ID)? KUTIPSATU));

primary_constraint
	: 'PRIMARY' 'KEY';

primary_constraint2
	: ('CONSTRAINT' ID)? 'PRIMARY' 'KEY' 'CLUSTERED'? column_list;

data_type_def returns [String tpe, Integer len, Integer prec]
	: '['? data_type ']'? {$tpe = $data_type.tp; $len = new Integer(0); $prec = new Integer(0); } (length_constraint)? {
	if (((Data_type_defContext)_localctx).length_constraint != null) {
		$len = $length_constraint.length;
		$prec = $length_constraint.prec;
	}
	}
	;

data_type returns [String tp]
	:
	((('character' 'varying'?) | 'VARCHAR' | 'VARCHAR2') {$tp = "varchar";}
		| ('bit' 'varying'? | 'varbit' ) {$tp = "varbit";}
		| ('double' 'precision'? | 'float8') {$tp = "float8"; }
		| ('character'? 'char' ) {$tp = "char";}
		| ('NUMBER' | 'numeric') {$tp = "number";}
		| ('TIMESTAMP' | 'timestamp') {$tp = "date";}
		| ('DATE' | 'date') {$tp = "date";}
		| ('DATETIME' | 'datetime') {$tp = "date";}
		| ('integer' | 'int' | 'int4') {$tp = "integer";}
		| ID {$tp = $ID.text; }
		);
	

length_constraint returns [Integer length, Integer prec]
	: LEFT_PAREN NUMBER RIGHT_PAREN {$length = Integer.valueOf($NUMBER.text); $prec = new Integer(0); }
	| LEFT_PAREN NUMBER {$length = Integer.valueOf($NUMBER.text);} COMMA NUMBER RIGHT_PAREN {$prec = Integer.valueOf($NUMBER.text);}
	| LEFT_PAREN ASTERISK COMMA NUMBER RIGHT_PAREN {$prec = Integer.valueOf($NUMBER.text); $length = new Integer(38);}
	;

schema : '['? ID ']'?;
table : '['? ID ']'?;

LEFT_PAREN : '(';
RIGHT_PAREN : ')';
COMMA : ',';
KUTIPSATU : '\'';
BACKSLASH : '\\';
SEMICOLON : ';';
DOT : '.';
ASTERISK : '*';
NUMBER : (DIGIT)+;
DECIMAL : (DIGIT)+ DOT (DIGIT)+;
ID : ('a'..'z' | DIGIT | '_' | 'A'..'Z' | BACKSLASH)+;
WS : ('\t' | ' ' | '\r' | '\n' | '\u000C' )+ {skip();} ;
fragment DIGIT : '0'..'9';
KUTIP : '`' {skip();} ;
NEWLINE:'\r'? '\n' ;
