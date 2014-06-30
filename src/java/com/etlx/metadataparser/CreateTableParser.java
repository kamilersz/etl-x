package com.etlx.metadataparser;

import com.etlx.metadata.*;
// Generated from CreateTable.g4 by ANTLR 4.1

import java.util.ArrayList;
import java.util.List;


import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CreateTableParser extends Parser {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__39=1, T__38=2, T__37=3, T__36=4, T__35=5, T__34=6, T__33=7, T__32=8, 
		T__31=9, T__30=10, T__29=11, T__28=12, T__27=13, T__26=14, T__25=15, T__24=16, 
		T__23=17, T__22=18, T__21=19, T__20=20, T__19=21, T__18=22, T__17=23, 
		T__16=24, T__15=25, T__14=26, T__13=27, T__12=28, T__11=29, T__10=30, 
		T__9=31, T__8=32, T__7=33, T__6=34, T__5=35, T__4=36, T__3=37, T__2=38, 
		T__1=39, T__0=40, LEFT_PAREN=41, RIGHT_PAREN=42, COMMA=43, KUTIPSATU=44, 
		BACKSLASH=45, SEMICOLON=46, DOT=47, ASTERISK=48, NUMBER=49, DECIMAL=50, 
		ID=51, WS=52, KUTIP=53, NEWLINE=54;
	public static final String[] tokenNames = {
		"<INVALID>", "'character'", "']'", "'['", "'CONSTRAINT'", "'int'", "'FOREIGN'", 
		"'double'", "'numeric'", "'PRIMARY'", "'DATE'", "'DEFAULT'", "'PROMPT'", 
		"'VARCHAR2'", "'char'", "'date'", "'VARCHAR'", "'int4'", "'TIMESTAMP'", 
		"'NULL'", "'TABLE'", "'float8'", "'ALTER'", "'ADD'", "'NUMBER'", "'KEY'", 
		"'DATETIME'", "'datetime'", "'CLUSTERED'", "'CREATE'", "'REFERENCES'", 
		"'varbit'", "'table'", "'timestamp'", "'integer'", "'DROP'", "'CREATING'", 
		"'precision'", "'varying'", "'NOT'", "'bit'", "'('", "')'", "','", "'''", 
		"'\\'", "';'", "'.'", "'*'", "NUMBER", "DECIMAL", "ID", "WS", "'`'", "NEWLINE"
	};
	public static final int
		RULE_table_list = 0, RULE_drop_statement = 1, RULE_alter_table_fk = 2, 
		RULE_alter_table_pk = 3, RULE_prompt_def = 4, RULE_column_list = 5, RULE_table_def = 6, 
		RULE_table_name = 7, RULE_table_element_list = 8, RULE_table_element = 9, 
		RULE_column_name = 10, RULE_column_constraint = 11, RULE_default_value = 12, 
		RULE_primary_constraint = 13, RULE_primary_constraint2 = 14, RULE_data_type_def = 15, 
		RULE_data_type = 16, RULE_length_constraint = 17, RULE_schema = 18, RULE_table = 19;
	public static final String[] ruleNames = {
		"table_list", "drop_statement", "alter_table_fk", "alter_table_pk", "prompt_def", 
		"column_list", "table_def", "table_name", "table_element_list", "table_element", 
		"column_name", "column_constraint", "default_value", "primary_constraint", 
		"primary_constraint2", "data_type_def", "data_type", "length_constraint", 
		"schema", "table"
	};

	@Override
	public String getGrammarFileName() { return "CreateTable.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public CreateTableParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class Table_listContext extends ParserRuleContext {
		public List tables;
		public List pk;
		public List fk;
		public Table_defContext table_def;
		public Alter_table_fkContext alter_table_fk;
		public Alter_table_pkContext alter_table_pk;
		public Prompt_defContext prompt_def(int i) {
			return getRuleContext(Prompt_defContext.class,i);
		}
		public Drop_statementContext drop_statement(int i) {
			return getRuleContext(Drop_statementContext.class,i);
		}
		public List<Alter_table_fkContext> alter_table_fk() {
			return getRuleContexts(Alter_table_fkContext.class);
		}
		public Table_defContext table_def(int i) {
			return getRuleContext(Table_defContext.class,i);
		}
		public List<Drop_statementContext> drop_statement() {
			return getRuleContexts(Drop_statementContext.class);
		}
		public List<Prompt_defContext> prompt_def() {
			return getRuleContexts(Prompt_defContext.class);
		}
		public Alter_table_pkContext alter_table_pk(int i) {
			return getRuleContext(Alter_table_pkContext.class,i);
		}
		public List<Alter_table_pkContext> alter_table_pk() {
			return getRuleContexts(Alter_table_pkContext.class);
		}
		public Alter_table_fkContext alter_table_fk(int i) {
			return getRuleContext(Alter_table_fkContext.class,i);
		}
		public List<Table_defContext> table_def() {
			return getRuleContexts(Table_defContext.class);
		}
		public Table_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_table_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CreateTableListener ) ((CreateTableListener)listener).enterTable_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CreateTableListener ) ((CreateTableListener)listener).exitTable_list(this);
		}
	}

	public final Table_listContext table_list() throws RecognitionException {
		Table_listContext _localctx = new Table_listContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_table_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			((Table_listContext)_localctx).tables =  new ArrayList();
				((Table_listContext)_localctx).pk =  new ArrayList();
				((Table_listContext)_localctx).fk =  new ArrayList();
			setState(54);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 12) | (1L << 22) | (1L << 29) | (1L << 35))) != 0)) {
				{
				setState(52);
				switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
				case 1:
					{
					setState(41); ((Table_listContext)_localctx).table_def = table_def();
					 _localctx.tables.add(((Table_listContext)_localctx).table_def.tbl); 
					}
					break;

				case 2:
					{
					setState(44); prompt_def();
					}
					break;

				case 3:
					{
					setState(45); ((Table_listContext)_localctx).alter_table_fk = alter_table_fk();
					_localctx.fk.add(((Table_listContext)_localctx).alter_table_fk.fk);
					}
					break;

				case 4:
					{
					setState(48); ((Table_listContext)_localctx).alter_table_pk = alter_table_pk();
					_localctx.pk.add(((Table_listContext)_localctx).alter_table_pk.pk);
					}
					break;

				case 5:
					{
					setState(51); drop_statement();
					}
					break;
				}
				}
				setState(56);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Drop_statementContext extends ParserRuleContext {
		public TerminalNode SEMICOLON() { return getToken(CreateTableParser.SEMICOLON, 0); }
		public Table_nameContext table_name() {
			return getRuleContext(Table_nameContext.class,0);
		}
		public Drop_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_drop_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CreateTableListener ) ((CreateTableListener)listener).enterDrop_statement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CreateTableListener ) ((CreateTableListener)listener).exitDrop_statement(this);
		}
	}

	public final Drop_statementContext drop_statement() throws RecognitionException {
		Drop_statementContext _localctx = new Drop_statementContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_drop_statement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(57); match(35);
			setState(58); match(20);
			setState(59); table_name();
			setState(60); match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Alter_table_fkContext extends ParserRuleContext {
		public ForeignKey fk;
		public Table_nameContext table_name;
		public Token ID;
		public Column_listContext column_list;
		public TerminalNode SEMICOLON() { return getToken(CreateTableParser.SEMICOLON, 0); }
		public Column_listContext column_list(int i) {
			return getRuleContext(Column_listContext.class,i);
		}
		public TerminalNode ID() { return getToken(CreateTableParser.ID, 0); }
		public Table_nameContext table_name(int i) {
			return getRuleContext(Table_nameContext.class,i);
		}
		public List<Column_listContext> column_list() {
			return getRuleContexts(Column_listContext.class);
		}
		public TerminalNode RIGHT_PAREN() { return getToken(CreateTableParser.RIGHT_PAREN, 0); }
		public TerminalNode LEFT_PAREN() { return getToken(CreateTableParser.LEFT_PAREN, 0); }
		public List<Table_nameContext> table_name() {
			return getRuleContexts(Table_nameContext.class);
		}
		public Alter_table_fkContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_alter_table_fk; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CreateTableListener ) ((CreateTableListener)listener).enterAlter_table_fk(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CreateTableListener ) ((CreateTableListener)listener).exitAlter_table_fk(this);
		}
	}

	public final Alter_table_fkContext alter_table_fk() throws RecognitionException {
		Alter_table_fkContext _localctx = new Alter_table_fkContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_alter_table_fk);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(62); match(22);
			setState(63); match(20);
			setState(64); ((Alter_table_fkContext)_localctx).table_name = table_name();
			setState(65); match(23);
			setState(66); match(LEFT_PAREN);
			setState(67); match(4);
			setState(68); ((Alter_table_fkContext)_localctx).ID = match(ID);
			((Alter_table_fkContext)_localctx).fk =  new ForeignKey((((Alter_table_fkContext)_localctx).ID!=null?((Alter_table_fkContext)_localctx).ID.getText():null)); _localctx.fk.setTableFrom((((Alter_table_fkContext)_localctx).table_name!=null?_input.getText(((Alter_table_fkContext)_localctx).table_name.start,((Alter_table_fkContext)_localctx).table_name.stop):null)); 
			setState(70); match(6);
			setState(71); match(25);
			setState(72); ((Alter_table_fkContext)_localctx).column_list = column_list();
			_localctx.fk.setColFrom(((Alter_table_fkContext)_localctx).column_list.cols);
			setState(74); match(30);
			setState(75); ((Alter_table_fkContext)_localctx).table_name = table_name();
			setState(76); ((Alter_table_fkContext)_localctx).column_list = column_list();
			_localctx.fk.setTableTo((((Alter_table_fkContext)_localctx).table_name!=null?_input.getText(((Alter_table_fkContext)_localctx).table_name.start,((Alter_table_fkContext)_localctx).table_name.stop):null)); _localctx.fk.setColTo(((Alter_table_fkContext)_localctx).column_list.cols);
			setState(78); match(RIGHT_PAREN);
			setState(80);
			_la = _input.LA(1);
			if (_la==SEMICOLON) {
				{
				setState(79); match(SEMICOLON);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Alter_table_pkContext extends ParserRuleContext {
		public PrimaryKey pk;
		public Table_nameContext table_name;
		public Token ID;
		public Column_listContext column_list;
		public TerminalNode SEMICOLON() { return getToken(CreateTableParser.SEMICOLON, 0); }
		public TerminalNode ID() { return getToken(CreateTableParser.ID, 0); }
		public Column_listContext column_list() {
			return getRuleContext(Column_listContext.class,0);
		}
		public TerminalNode RIGHT_PAREN() { return getToken(CreateTableParser.RIGHT_PAREN, 0); }
		public TerminalNode LEFT_PAREN() { return getToken(CreateTableParser.LEFT_PAREN, 0); }
		public Table_nameContext table_name() {
			return getRuleContext(Table_nameContext.class,0);
		}
		public Alter_table_pkContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_alter_table_pk; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CreateTableListener ) ((CreateTableListener)listener).enterAlter_table_pk(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CreateTableListener ) ((CreateTableListener)listener).exitAlter_table_pk(this);
		}
	}

	public final Alter_table_pkContext alter_table_pk() throws RecognitionException {
		Alter_table_pkContext _localctx = new Alter_table_pkContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_alter_table_pk);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(82); match(22);
			setState(83); match(20);
			setState(84); ((Alter_table_pkContext)_localctx).table_name = table_name();
			setState(85); match(23);
			setState(86); match(LEFT_PAREN);
			setState(87); match(4);
			setState(88); ((Alter_table_pkContext)_localctx).ID = match(ID);
			((Alter_table_pkContext)_localctx).pk =  new PrimaryKey((((Alter_table_pkContext)_localctx).ID!=null?((Alter_table_pkContext)_localctx).ID.getText():null)); _localctx.pk.setTable((((Alter_table_pkContext)_localctx).table_name!=null?_input.getText(((Alter_table_pkContext)_localctx).table_name.start,((Alter_table_pkContext)_localctx).table_name.stop):null)); 
			setState(90); match(9);
			setState(91); match(25);
			setState(92); ((Alter_table_pkContext)_localctx).column_list = column_list();
			_localctx.pk.setCol(((Alter_table_pkContext)_localctx).column_list.cols);
			setState(94); match(RIGHT_PAREN);
			setState(96);
			_la = _input.LA(1);
			if (_la==SEMICOLON) {
				{
				setState(95); match(SEMICOLON);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Prompt_defContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(CreateTableParser.ID, 0); }
		public Prompt_defContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prompt_def; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CreateTableListener ) ((CreateTableListener)listener).enterPrompt_def(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CreateTableListener ) ((CreateTableListener)listener).exitPrompt_def(this);
		}
	}

	public final Prompt_defContext prompt_def() throws RecognitionException {
		Prompt_defContext _localctx = new Prompt_defContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_prompt_def);
		try {
			setState(113);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(98); match(12);
				setState(99); match(36);
				setState(100); match(20);
				setState(101); match(ID);
				}
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(102); match(12);
				setState(103); match(36);
				setState(104); match(9);
				setState(105); match(25);
				setState(106); match(ID);
				}
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				{
				setState(107); match(12);
				setState(108); match(36);
				setState(109); match(6);
				setState(110); match(25);
				setState(111); match(4);
				setState(112); match(ID);
				}
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Column_listContext extends ParserRuleContext {
		public List cols;
		public Column_nameContext cn;
		public List<Column_nameContext> column_name() {
			return getRuleContexts(Column_nameContext.class);
		}
		public List<TerminalNode> COMMA() { return getTokens(CreateTableParser.COMMA); }
		public Column_nameContext column_name(int i) {
			return getRuleContext(Column_nameContext.class,i);
		}
		public TerminalNode RIGHT_PAREN() { return getToken(CreateTableParser.RIGHT_PAREN, 0); }
		public TerminalNode LEFT_PAREN() { return getToken(CreateTableParser.LEFT_PAREN, 0); }
		public TerminalNode COMMA(int i) {
			return getToken(CreateTableParser.COMMA, i);
		}
		public Column_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_column_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CreateTableListener ) ((CreateTableListener)listener).enterColumn_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CreateTableListener ) ((CreateTableListener)listener).exitColumn_list(this);
		}
	}

	public final Column_listContext column_list() throws RecognitionException {
		Column_listContext _localctx = new Column_listContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_column_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(115); match(LEFT_PAREN);
			((Column_listContext)_localctx).cols =  new ArrayList();
			setState(117); ((Column_listContext)_localctx).cn = column_name();
			_localctx.cols.add(((Column_listContext)_localctx).cn.cn);
			setState(125);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(119); match(COMMA);
				setState(120); ((Column_listContext)_localctx).cn = column_name();
				_localctx.cols.add(((Column_listContext)_localctx).cn.cn);
				}
				}
				setState(127);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(128); match(RIGHT_PAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Table_defContext extends ParserRuleContext {
		public Table tbl;
		public Table_nameContext table_name;
		public Table_element_listContext table_element_list;
		public TerminalNode SEMICOLON() { return getToken(CreateTableParser.SEMICOLON, 0); }
		public TerminalNode KUTIP(int i) {
			return getToken(CreateTableParser.KUTIP, i);
		}
		public Table_element_listContext table_element_list() {
			return getRuleContext(Table_element_listContext.class,0);
		}
		public List<TerminalNode> KUTIP() { return getTokens(CreateTableParser.KUTIP); }
		public Table_nameContext table_name() {
			return getRuleContext(Table_nameContext.class,0);
		}
		public Table_defContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_table_def; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CreateTableListener ) ((CreateTableListener)listener).enterTable_def(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CreateTableListener ) ((CreateTableListener)listener).exitTable_def(this);
		}
	}

	public final Table_defContext table_def() throws RecognitionException {
		Table_defContext _localctx = new Table_defContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_table_def);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(130); match(29);
			setState(131);
			_la = _input.LA(1);
			if ( !(_la==20 || _la==32) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			setState(133);
			_la = _input.LA(1);
			if (_la==KUTIP) {
				{
				setState(132); match(KUTIP);
				}
			}

			setState(135); ((Table_defContext)_localctx).table_name = table_name();
			setState(137);
			_la = _input.LA(1);
			if (_la==KUTIP) {
				{
				setState(136); match(KUTIP);
				}
			}

			setState(139); ((Table_defContext)_localctx).table_element_list = table_element_list();

						((Table_defContext)_localctx).tbl =  new Table(String.valueOf((((Table_defContext)_localctx).table_name!=null?_input.getText(((Table_defContext)_localctx).table_name.start,((Table_defContext)_localctx).table_name.stop):null)), ((Table_defContext)_localctx).table_element_list.cols);
					
			setState(142);
			_la = _input.LA(1);
			if (_la==SEMICOLON) {
				{
				setState(141); match(SEMICOLON);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Table_nameContext extends ParserRuleContext {
		public TableContext table() {
			return getRuleContext(TableContext.class,0);
		}
		public TerminalNode DOT() { return getToken(CreateTableParser.DOT, 0); }
		public SchemaContext schema() {
			return getRuleContext(SchemaContext.class,0);
		}
		public Table_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_table_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CreateTableListener ) ((CreateTableListener)listener).enterTable_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CreateTableListener ) ((CreateTableListener)listener).exitTable_name(this);
		}
	}

	public final Table_nameContext table_name() throws RecognitionException {
		Table_nameContext _localctx = new Table_nameContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_table_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(147);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				{
				setState(144); schema();
				setState(145); match(DOT);
				}
				break;
			}
			setState(149); table();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Table_element_listContext extends ParserRuleContext {
		public List cols;
		public Table_elementContext te;
		public List<TerminalNode> COMMA() { return getTokens(CreateTableParser.COMMA); }
		public TerminalNode RIGHT_PAREN() { return getToken(CreateTableParser.RIGHT_PAREN, 0); }
		public Table_elementContext table_element(int i) {
			return getRuleContext(Table_elementContext.class,i);
		}
		public TerminalNode LEFT_PAREN() { return getToken(CreateTableParser.LEFT_PAREN, 0); }
		public Primary_constraint2Context primary_constraint2() {
			return getRuleContext(Primary_constraint2Context.class,0);
		}
		public TerminalNode COMMA(int i) {
			return getToken(CreateTableParser.COMMA, i);
		}
		public List<Table_elementContext> table_element() {
			return getRuleContexts(Table_elementContext.class);
		}
		public Table_element_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_table_element_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CreateTableListener ) ((CreateTableListener)listener).enterTable_element_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CreateTableListener ) ((CreateTableListener)listener).exitTable_element_list(this);
		}
	}

	public final Table_element_listContext table_element_list() throws RecognitionException {
		Table_element_listContext _localctx = new Table_element_listContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_table_element_list);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			((Table_element_listContext)_localctx).cols =  new ArrayList();
			setState(152); match(LEFT_PAREN);
			setState(153); ((Table_element_listContext)_localctx).te = table_element();
			_localctx.cols.add(((Table_element_listContext)_localctx).te.col);
			setState(161);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(155); match(COMMA);
					setState(156); ((Table_element_listContext)_localctx).te = table_element();
					_localctx.cols.add(((Table_element_listContext)_localctx).te.col);
					}
					} 
				}
				setState(163);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
			}
			setState(166);
			_la = _input.LA(1);
			if (_la==COMMA) {
				{
				setState(164); match(COMMA);
				setState(165); primary_constraint2();
				}
			}

			setState(168); match(RIGHT_PAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Table_elementContext extends ParserRuleContext {
		public Column col;
		public Column_nameContext column_name;
		public Data_type_defContext data_type_def;
		public Column_constraintContext column_constraint;
		public TerminalNode KUTIP(int i) {
			return getToken(CreateTableParser.KUTIP, i);
		}
		public Column_nameContext column_name() {
			return getRuleContext(Column_nameContext.class,0);
		}
		public Primary_constraintContext primary_constraint() {
			return getRuleContext(Primary_constraintContext.class,0);
		}
		public List<Column_constraintContext> column_constraint() {
			return getRuleContexts(Column_constraintContext.class);
		}
		public Default_valueContext default_value() {
			return getRuleContext(Default_valueContext.class,0);
		}
		public Data_type_defContext data_type_def() {
			return getRuleContext(Data_type_defContext.class,0);
		}
		public Column_constraintContext column_constraint(int i) {
			return getRuleContext(Column_constraintContext.class,i);
		}
		public List<TerminalNode> KUTIP() { return getTokens(CreateTableParser.KUTIP); }
		public Table_elementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_table_element; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CreateTableListener ) ((CreateTableListener)listener).enterTable_element(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CreateTableListener ) ((CreateTableListener)listener).exitTable_element(this);
		}
	}

	public final Table_elementContext table_element() throws RecognitionException {
		Table_elementContext _localctx = new Table_elementContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_table_element);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(171);
			_la = _input.LA(1);
			if (_la==KUTIP) {
				{
				setState(170); match(KUTIP);
				}
			}

			setState(173); ((Table_elementContext)_localctx).column_name = column_name();
			setState(175);
			_la = _input.LA(1);
			if (_la==KUTIP) {
				{
				setState(174); match(KUTIP);
				}
			}

			setState(177); ((Table_elementContext)_localctx).data_type_def = data_type_def();
			setState(179);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				{
				setState(178); ((Table_elementContext)_localctx).column_constraint = column_constraint();
				}
				break;
			}
			setState(182);
			_la = _input.LA(1);
			if (_la==9) {
				{
				setState(181); primary_constraint();
				}
			}

			setState(185);
			_la = _input.LA(1);
			if (_la==19 || _la==39) {
				{
				setState(184); ((Table_elementContext)_localctx).column_constraint = column_constraint();
				}
			}

			setState(188);
			_la = _input.LA(1);
			if (_la==11) {
				{
				setState(187); default_value();
				}
			}

			((Table_elementContext)_localctx).col =  new Column(((Table_elementContext)_localctx).column_name.cn, ((Table_elementContext)_localctx).data_type_def.tpe, (((Table_elementContext)_localctx).column_constraint!=null?_input.getText(((Table_elementContext)_localctx).column_constraint.start,((Table_elementContext)_localctx).column_constraint.stop):null), ((Table_elementContext)_localctx).data_type_def.len, ((Table_elementContext)_localctx).data_type_def.prec);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Column_nameContext extends ParserRuleContext {
		public String cn;
		public Token ID;
		public TerminalNode ID() { return getToken(CreateTableParser.ID, 0); }
		public Column_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_column_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CreateTableListener ) ((CreateTableListener)listener).enterColumn_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CreateTableListener ) ((CreateTableListener)listener).exitColumn_name(this);
		}
	}

	public final Column_nameContext column_name() throws RecognitionException {
		Column_nameContext _localctx = new Column_nameContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_column_name);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(193);
			_la = _input.LA(1);
			if (_la==3) {
				{
				setState(192); match(3);
				}
			}

			setState(195); ((Column_nameContext)_localctx).ID = match(ID);
			setState(197);
			_la = _input.LA(1);
			if (_la==2) {
				{
				setState(196); match(2);
				}
			}

			((Column_nameContext)_localctx).cn =  String.valueOf((((Column_nameContext)_localctx).ID!=null?((Column_nameContext)_localctx).ID.getText():null)); 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Column_constraintContext extends ParserRuleContext {
		public Column_constraintContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_column_constraint; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CreateTableListener ) ((CreateTableListener)listener).enterColumn_constraint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CreateTableListener ) ((CreateTableListener)listener).exitColumn_constraint(this);
		}
	}

	public final Column_constraintContext column_constraint() throws RecognitionException {
		Column_constraintContext _localctx = new Column_constraintContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_column_constraint);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(202);
			_la = _input.LA(1);
			if (_la==39) {
				{
				setState(201); match(39);
				}
			}

			setState(204); match(19);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Default_valueContext extends ParserRuleContext {
		public TerminalNode KUTIPSATU(int i) {
			return getToken(CreateTableParser.KUTIPSATU, i);
		}
		public TerminalNode ID() { return getToken(CreateTableParser.ID, 0); }
		public TerminalNode NUMBER() { return getToken(CreateTableParser.NUMBER, 0); }
		public TerminalNode DECIMAL() { return getToken(CreateTableParser.DECIMAL, 0); }
		public List<TerminalNode> KUTIPSATU() { return getTokens(CreateTableParser.KUTIPSATU); }
		public Default_valueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_default_value; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CreateTableListener ) ((CreateTableListener)listener).enterDefault_value(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CreateTableListener ) ((CreateTableListener)listener).exitDefault_value(this);
		}
	}

	public final Default_valueContext default_value() throws RecognitionException {
		Default_valueContext _localctx = new Default_valueContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_default_value);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(206); match(11);
			setState(216);
			switch (_input.LA(1)) {
			case ID:
				{
				setState(207); match(ID);
				}
				break;
			case 19:
				{
				setState(208); match(19);
				}
				break;
			case NUMBER:
				{
				setState(209); match(NUMBER);
				}
				break;
			case DECIMAL:
				{
				setState(210); match(DECIMAL);
				}
				break;
			case KUTIPSATU:
				{
				{
				setState(211); match(KUTIPSATU);
				setState(213);
				_la = _input.LA(1);
				if (_la==ID) {
					{
					setState(212); match(ID);
					}
				}

				setState(215); match(KUTIPSATU);
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Primary_constraintContext extends ParserRuleContext {
		public Primary_constraintContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primary_constraint; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CreateTableListener ) ((CreateTableListener)listener).enterPrimary_constraint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CreateTableListener ) ((CreateTableListener)listener).exitPrimary_constraint(this);
		}
	}

	public final Primary_constraintContext primary_constraint() throws RecognitionException {
		Primary_constraintContext _localctx = new Primary_constraintContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_primary_constraint);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(218); match(9);
			setState(219); match(25);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Primary_constraint2Context extends ParserRuleContext {
		public TerminalNode ID() { return getToken(CreateTableParser.ID, 0); }
		public Column_listContext column_list() {
			return getRuleContext(Column_listContext.class,0);
		}
		public Primary_constraint2Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primary_constraint2; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CreateTableListener ) ((CreateTableListener)listener).enterPrimary_constraint2(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CreateTableListener ) ((CreateTableListener)listener).exitPrimary_constraint2(this);
		}
	}

	public final Primary_constraint2Context primary_constraint2() throws RecognitionException {
		Primary_constraint2Context _localctx = new Primary_constraint2Context(_ctx, getState());
		enterRule(_localctx, 28, RULE_primary_constraint2);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(223);
			_la = _input.LA(1);
			if (_la==4) {
				{
				setState(221); match(4);
				setState(222); match(ID);
				}
			}

			setState(225); match(9);
			setState(226); match(25);
			setState(228);
			_la = _input.LA(1);
			if (_la==28) {
				{
				setState(227); match(28);
				}
			}

			setState(230); column_list();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Data_type_defContext extends ParserRuleContext {
		public String tpe;
		public Integer len;
		public Integer prec;
		public Data_typeContext data_type;
		public Length_constraintContext length_constraint;
		public Length_constraintContext length_constraint() {
			return getRuleContext(Length_constraintContext.class,0);
		}
		public Data_typeContext data_type() {
			return getRuleContext(Data_typeContext.class,0);
		}
		public Data_type_defContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_data_type_def; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CreateTableListener ) ((CreateTableListener)listener).enterData_type_def(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CreateTableListener ) ((CreateTableListener)listener).exitData_type_def(this);
		}
	}

	public final Data_type_defContext data_type_def() throws RecognitionException {
		Data_type_defContext _localctx = new Data_type_defContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_data_type_def);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(233);
			_la = _input.LA(1);
			if (_la==3) {
				{
				setState(232); match(3);
				}
			}

			setState(235); ((Data_type_defContext)_localctx).data_type = data_type();
			setState(237);
			_la = _input.LA(1);
			if (_la==2) {
				{
				setState(236); match(2);
				}
			}

			((Data_type_defContext)_localctx).tpe =  ((Data_type_defContext)_localctx).data_type.tp; ((Data_type_defContext)_localctx).len =  new Integer(0); ((Data_type_defContext)_localctx).prec =  new Integer(0); 
			setState(241);
			_la = _input.LA(1);
			if (_la==LEFT_PAREN) {
				{
				setState(240); ((Data_type_defContext)_localctx).length_constraint = length_constraint();
				}
			}


				if (((Data_type_defContext)_localctx).length_constraint != null) {
					((Data_type_defContext)_localctx).len =  ((Data_type_defContext)_localctx).length_constraint.length;
					((Data_type_defContext)_localctx).prec =  ((Data_type_defContext)_localctx).length_constraint.prec;
				}
				
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Data_typeContext extends ParserRuleContext {
		public String tp;
		public Token ID;
		public TerminalNode ID() { return getToken(CreateTableParser.ID, 0); }
		public Data_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_data_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CreateTableListener ) ((CreateTableListener)listener).enterData_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CreateTableListener ) ((CreateTableListener)listener).exitData_type(this);
		}
	}

	public final Data_typeContext data_type() throws RecognitionException {
		Data_typeContext _localctx = new Data_typeContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_data_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(288);
			switch ( getInterpreter().adaptivePredict(_input,35,_ctx) ) {
			case 1:
				{
				setState(251);
				switch (_input.LA(1)) {
				case 1:
					{
					{
					setState(245); match(1);
					setState(247);
					_la = _input.LA(1);
					if (_la==38) {
						{
						setState(246); match(38);
						}
					}

					}
					}
					break;
				case 16:
					{
					setState(249); match(16);
					}
					break;
				case 13:
					{
					setState(250); match(13);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				((Data_typeContext)_localctx).tp =  "varchar";
				}
				break;

			case 2:
				{
				setState(259);
				switch (_input.LA(1)) {
				case 40:
					{
					setState(254); match(40);
					setState(256);
					_la = _input.LA(1);
					if (_la==38) {
						{
						setState(255); match(38);
						}
					}

					}
					break;
				case 31:
					{
					setState(258); match(31);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				((Data_typeContext)_localctx).tp =  "varbit";
				}
				break;

			case 3:
				{
				setState(267);
				switch (_input.LA(1)) {
				case 7:
					{
					setState(262); match(7);
					setState(264);
					_la = _input.LA(1);
					if (_la==37) {
						{
						setState(263); match(37);
						}
					}

					}
					break;
				case 21:
					{
					setState(266); match(21);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				((Data_typeContext)_localctx).tp =  "float8"; 
				}
				break;

			case 4:
				{
				{
				setState(271);
				_la = _input.LA(1);
				if (_la==1) {
					{
					setState(270); match(1);
					}
				}

				setState(273); match(14);
				}
				((Data_typeContext)_localctx).tp =  "char";
				}
				break;

			case 5:
				{
				setState(276);
				_la = _input.LA(1);
				if ( !(_la==8 || _la==24) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				((Data_typeContext)_localctx).tp =  "number";
				}
				break;

			case 6:
				{
				setState(278);
				_la = _input.LA(1);
				if ( !(_la==18 || _la==33) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				((Data_typeContext)_localctx).tp =  "date";
				}
				break;

			case 7:
				{
				setState(280);
				_la = _input.LA(1);
				if ( !(_la==10 || _la==15) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				((Data_typeContext)_localctx).tp =  "date";
				}
				break;

			case 8:
				{
				setState(282);
				_la = _input.LA(1);
				if ( !(_la==26 || _la==27) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				((Data_typeContext)_localctx).tp =  "date";
				}
				break;

			case 9:
				{
				setState(284);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 5) | (1L << 17) | (1L << 34))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				((Data_typeContext)_localctx).tp =  "integer";
				}
				break;

			case 10:
				{
				setState(286); ((Data_typeContext)_localctx).ID = match(ID);
				((Data_typeContext)_localctx).tp =  (((Data_typeContext)_localctx).ID!=null?((Data_typeContext)_localctx).ID.getText():null); 
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Length_constraintContext extends ParserRuleContext {
		public Integer length;
		public Integer prec;
		public Token NUMBER;
		public TerminalNode ASTERISK() { return getToken(CreateTableParser.ASTERISK, 0); }
		public TerminalNode COMMA() { return getToken(CreateTableParser.COMMA, 0); }
		public TerminalNode NUMBER(int i) {
			return getToken(CreateTableParser.NUMBER, i);
		}
		public TerminalNode RIGHT_PAREN() { return getToken(CreateTableParser.RIGHT_PAREN, 0); }
		public List<TerminalNode> NUMBER() { return getTokens(CreateTableParser.NUMBER); }
		public TerminalNode LEFT_PAREN() { return getToken(CreateTableParser.LEFT_PAREN, 0); }
		public Length_constraintContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_length_constraint; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CreateTableListener ) ((CreateTableListener)listener).enterLength_constraint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CreateTableListener ) ((CreateTableListener)listener).exitLength_constraint(this);
		}
	}

	public final Length_constraintContext length_constraint() throws RecognitionException {
		Length_constraintContext _localctx = new Length_constraintContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_length_constraint);
		try {
			setState(307);
			switch ( getInterpreter().adaptivePredict(_input,36,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(290); match(LEFT_PAREN);
				setState(291); ((Length_constraintContext)_localctx).NUMBER = match(NUMBER);
				setState(292); match(RIGHT_PAREN);
				((Length_constraintContext)_localctx).length =  Integer.valueOf((((Length_constraintContext)_localctx).NUMBER!=null?((Length_constraintContext)_localctx).NUMBER.getText():null)); ((Length_constraintContext)_localctx).prec =  new Integer(0); 
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(294); match(LEFT_PAREN);
				setState(295); ((Length_constraintContext)_localctx).NUMBER = match(NUMBER);
				((Length_constraintContext)_localctx).length =  Integer.valueOf((((Length_constraintContext)_localctx).NUMBER!=null?((Length_constraintContext)_localctx).NUMBER.getText():null));
				setState(297); match(COMMA);
				setState(298); ((Length_constraintContext)_localctx).NUMBER = match(NUMBER);
				setState(299); match(RIGHT_PAREN);
				((Length_constraintContext)_localctx).prec =  Integer.valueOf((((Length_constraintContext)_localctx).NUMBER!=null?((Length_constraintContext)_localctx).NUMBER.getText():null));
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(301); match(LEFT_PAREN);
				setState(302); match(ASTERISK);
				setState(303); match(COMMA);
				setState(304); ((Length_constraintContext)_localctx).NUMBER = match(NUMBER);
				setState(305); match(RIGHT_PAREN);
				((Length_constraintContext)_localctx).prec =  Integer.valueOf((((Length_constraintContext)_localctx).NUMBER!=null?((Length_constraintContext)_localctx).NUMBER.getText():null)); ((Length_constraintContext)_localctx).length =  new Integer(38);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SchemaContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(CreateTableParser.ID, 0); }
		public SchemaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_schema; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CreateTableListener ) ((CreateTableListener)listener).enterSchema(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CreateTableListener ) ((CreateTableListener)listener).exitSchema(this);
		}
	}

	public final SchemaContext schema() throws RecognitionException {
		SchemaContext _localctx = new SchemaContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_schema);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(310);
			_la = _input.LA(1);
			if (_la==3) {
				{
				setState(309); match(3);
				}
			}

			setState(312); match(ID);
			setState(314);
			_la = _input.LA(1);
			if (_la==2) {
				{
				setState(313); match(2);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TableContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(CreateTableParser.ID, 0); }
		public TableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_table; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CreateTableListener ) ((CreateTableListener)listener).enterTable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CreateTableListener ) ((CreateTableListener)listener).exitTable(this);
		}
	}

	public final TableContext table() throws RecognitionException {
		TableContext _localctx = new TableContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_table);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(317);
			_la = _input.LA(1);
			if (_la==3) {
				{
				setState(316); match(3);
				}
			}

			setState(319); match(ID);
			setState(321);
			_la = _input.LA(1);
			if (_la==2) {
				{
				setState(320); match(2);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\38\u0146\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2"+
		"\3\2\3\2\7\2\67\n\2\f\2\16\2:\13\2\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4"+
		"\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4S\n\4\3\5"+
		"\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5c\n\5\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6t\n\6\3\7\3\7"+
		"\3\7\3\7\3\7\3\7\3\7\3\7\7\7~\n\7\f\7\16\7\u0081\13\7\3\7\3\7\3\b\3\b"+
		"\3\b\5\b\u0088\n\b\3\b\3\b\5\b\u008c\n\b\3\b\3\b\3\b\5\b\u0091\n\b\3\t"+
		"\3\t\3\t\5\t\u0096\n\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\7\n\u00a2"+
		"\n\n\f\n\16\n\u00a5\13\n\3\n\3\n\5\n\u00a9\n\n\3\n\3\n\3\13\5\13\u00ae"+
		"\n\13\3\13\3\13\5\13\u00b2\n\13\3\13\3\13\5\13\u00b6\n\13\3\13\5\13\u00b9"+
		"\n\13\3\13\5\13\u00bc\n\13\3\13\5\13\u00bf\n\13\3\13\3\13\3\f\5\f\u00c4"+
		"\n\f\3\f\3\f\5\f\u00c8\n\f\3\f\3\f\3\r\5\r\u00cd\n\r\3\r\3\r\3\16\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\5\16\u00d8\n\16\3\16\5\16\u00db\n\16\3\17\3"+
		"\17\3\17\3\20\3\20\5\20\u00e2\n\20\3\20\3\20\3\20\5\20\u00e7\n\20\3\20"+
		"\3\20\3\21\5\21\u00ec\n\21\3\21\3\21\5\21\u00f0\n\21\3\21\3\21\5\21\u00f4"+
		"\n\21\3\21\3\21\3\22\3\22\5\22\u00fa\n\22\3\22\3\22\5\22\u00fe\n\22\3"+
		"\22\3\22\3\22\5\22\u0103\n\22\3\22\5\22\u0106\n\22\3\22\3\22\3\22\5\22"+
		"\u010b\n\22\3\22\5\22\u010e\n\22\3\22\3\22\5\22\u0112\n\22\3\22\3\22\3"+
		"\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\5\22\u0123"+
		"\n\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23"+
		"\3\23\3\23\3\23\3\23\5\23\u0136\n\23\3\24\5\24\u0139\n\24\3\24\3\24\5"+
		"\24\u013d\n\24\3\25\5\25\u0140\n\25\3\25\3\25\5\25\u0144\n\25\3\25\2\26"+
		"\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(\2\b\4\2\26\26\"\"\4\2\n"+
		"\n\32\32\4\2\24\24##\4\2\f\f\21\21\3\2\34\35\5\2\7\7\23\23$$\u016b\2*"+
		"\3\2\2\2\4;\3\2\2\2\6@\3\2\2\2\bT\3\2\2\2\ns\3\2\2\2\fu\3\2\2\2\16\u0084"+
		"\3\2\2\2\20\u0095\3\2\2\2\22\u0099\3\2\2\2\24\u00ad\3\2\2\2\26\u00c3\3"+
		"\2\2\2\30\u00cc\3\2\2\2\32\u00d0\3\2\2\2\34\u00dc\3\2\2\2\36\u00e1\3\2"+
		"\2\2 \u00eb\3\2\2\2\"\u0122\3\2\2\2$\u0135\3\2\2\2&\u0138\3\2\2\2(\u013f"+
		"\3\2\2\2*8\b\2\1\2+,\5\16\b\2,-\b\2\1\2-\67\3\2\2\2.\67\5\n\6\2/\60\5"+
		"\6\4\2\60\61\b\2\1\2\61\67\3\2\2\2\62\63\5\b\5\2\63\64\b\2\1\2\64\67\3"+
		"\2\2\2\65\67\5\4\3\2\66+\3\2\2\2\66.\3\2\2\2\66/\3\2\2\2\66\62\3\2\2\2"+
		"\66\65\3\2\2\2\67:\3\2\2\28\66\3\2\2\289\3\2\2\29\3\3\2\2\2:8\3\2\2\2"+
		";<\7%\2\2<=\7\26\2\2=>\5\20\t\2>?\7\60\2\2?\5\3\2\2\2@A\7\30\2\2AB\7\26"+
		"\2\2BC\5\20\t\2CD\7\31\2\2DE\7+\2\2EF\7\6\2\2FG\7\65\2\2GH\b\4\1\2HI\7"+
		"\b\2\2IJ\7\33\2\2JK\5\f\7\2KL\b\4\1\2LM\7 \2\2MN\5\20\t\2NO\5\f\7\2OP"+
		"\b\4\1\2PR\7,\2\2QS\7\60\2\2RQ\3\2\2\2RS\3\2\2\2S\7\3\2\2\2TU\7\30\2\2"+
		"UV\7\26\2\2VW\5\20\t\2WX\7\31\2\2XY\7+\2\2YZ\7\6\2\2Z[\7\65\2\2[\\\b\5"+
		"\1\2\\]\7\13\2\2]^\7\33\2\2^_\5\f\7\2_`\b\5\1\2`b\7,\2\2ac\7\60\2\2ba"+
		"\3\2\2\2bc\3\2\2\2c\t\3\2\2\2de\7\16\2\2ef\7&\2\2fg\7\26\2\2gt\7\65\2"+
		"\2hi\7\16\2\2ij\7&\2\2jk\7\13\2\2kl\7\33\2\2lt\7\65\2\2mn\7\16\2\2no\7"+
		"&\2\2op\7\b\2\2pq\7\33\2\2qr\7\6\2\2rt\7\65\2\2sd\3\2\2\2sh\3\2\2\2sm"+
		"\3\2\2\2t\13\3\2\2\2uv\7+\2\2vw\b\7\1\2wx\5\26\f\2x\177\b\7\1\2yz\7-\2"+
		"\2z{\5\26\f\2{|\b\7\1\2|~\3\2\2\2}y\3\2\2\2~\u0081\3\2\2\2\177}\3\2\2"+
		"\2\177\u0080\3\2\2\2\u0080\u0082\3\2\2\2\u0081\177\3\2\2\2\u0082\u0083"+
		"\7,\2\2\u0083\r\3\2\2\2\u0084\u0085\7\37\2\2\u0085\u0087\t\2\2\2\u0086"+
		"\u0088\7\67\2\2\u0087\u0086\3\2\2\2\u0087\u0088\3\2\2\2\u0088\u0089\3"+
		"\2\2\2\u0089\u008b\5\20\t\2\u008a\u008c\7\67\2\2\u008b\u008a\3\2\2\2\u008b"+
		"\u008c\3\2\2\2\u008c\u008d\3\2\2\2\u008d\u008e\5\22\n\2\u008e\u0090\b"+
		"\b\1\2\u008f\u0091\7\60\2\2\u0090\u008f\3\2\2\2\u0090\u0091\3\2\2\2\u0091"+
		"\17\3\2\2\2\u0092\u0093\5&\24\2\u0093\u0094\7\61\2\2\u0094\u0096\3\2\2"+
		"\2\u0095\u0092\3\2\2\2\u0095\u0096\3\2\2\2\u0096\u0097\3\2\2\2\u0097\u0098"+
		"\5(\25\2\u0098\21\3\2\2\2\u0099\u009a\b\n\1\2\u009a\u009b\7+\2\2\u009b"+
		"\u009c\5\24\13\2\u009c\u00a3\b\n\1\2\u009d\u009e\7-\2\2\u009e\u009f\5"+
		"\24\13\2\u009f\u00a0\b\n\1\2\u00a0\u00a2\3\2\2\2\u00a1\u009d\3\2\2\2\u00a2"+
		"\u00a5\3\2\2\2\u00a3\u00a1\3\2\2\2\u00a3\u00a4\3\2\2\2\u00a4\u00a8\3\2"+
		"\2\2\u00a5\u00a3\3\2\2\2\u00a6\u00a7\7-\2\2\u00a7\u00a9\5\36\20\2\u00a8"+
		"\u00a6\3\2\2\2\u00a8\u00a9\3\2\2\2\u00a9\u00aa\3\2\2\2\u00aa\u00ab\7,"+
		"\2\2\u00ab\23\3\2\2\2\u00ac\u00ae\7\67\2\2\u00ad\u00ac\3\2\2\2\u00ad\u00ae"+
		"\3\2\2\2\u00ae\u00af\3\2\2\2\u00af\u00b1\5\26\f\2\u00b0\u00b2\7\67\2\2"+
		"\u00b1\u00b0\3\2\2\2\u00b1\u00b2\3\2\2\2\u00b2\u00b3\3\2\2\2\u00b3\u00b5"+
		"\5 \21\2\u00b4\u00b6\5\30\r\2\u00b5\u00b4\3\2\2\2\u00b5\u00b6\3\2\2\2"+
		"\u00b6\u00b8\3\2\2\2\u00b7\u00b9\5\34\17\2\u00b8\u00b7\3\2\2\2\u00b8\u00b9"+
		"\3\2\2\2\u00b9\u00bb\3\2\2\2\u00ba\u00bc\5\30\r\2\u00bb\u00ba\3\2\2\2"+
		"\u00bb\u00bc\3\2\2\2\u00bc\u00be\3\2\2\2\u00bd\u00bf\5\32\16\2\u00be\u00bd"+
		"\3\2\2\2\u00be\u00bf\3\2\2\2\u00bf\u00c0\3\2\2\2\u00c0\u00c1\b\13\1\2"+
		"\u00c1\25\3\2\2\2\u00c2\u00c4\7\5\2\2\u00c3\u00c2\3\2\2\2\u00c3\u00c4"+
		"\3\2\2\2\u00c4\u00c5\3\2\2\2\u00c5\u00c7\7\65\2\2\u00c6\u00c8\7\4\2\2"+
		"\u00c7\u00c6\3\2\2\2\u00c7\u00c8\3\2\2\2\u00c8\u00c9\3\2\2\2\u00c9\u00ca"+
		"\b\f\1\2\u00ca\27\3\2\2\2\u00cb\u00cd\7)\2\2\u00cc\u00cb\3\2\2\2\u00cc"+
		"\u00cd\3\2\2\2\u00cd\u00ce\3\2\2\2\u00ce\u00cf\7\25\2\2\u00cf\31\3\2\2"+
		"\2\u00d0\u00da\7\r\2\2\u00d1\u00db\7\65\2\2\u00d2\u00db\7\25\2\2\u00d3"+
		"\u00db\7\63\2\2\u00d4\u00db\7\64\2\2\u00d5\u00d7\7.\2\2\u00d6\u00d8\7"+
		"\65\2\2\u00d7\u00d6\3\2\2\2\u00d7\u00d8\3\2\2\2\u00d8\u00d9\3\2\2\2\u00d9"+
		"\u00db\7.\2\2\u00da\u00d1\3\2\2\2\u00da\u00d2\3\2\2\2\u00da\u00d3\3\2"+
		"\2\2\u00da\u00d4\3\2\2\2\u00da\u00d5\3\2\2\2\u00db\33\3\2\2\2\u00dc\u00dd"+
		"\7\13\2\2\u00dd\u00de\7\33\2\2\u00de\35\3\2\2\2\u00df\u00e0\7\6\2\2\u00e0"+
		"\u00e2\7\65\2\2\u00e1\u00df\3\2\2\2\u00e1\u00e2\3\2\2\2\u00e2\u00e3\3"+
		"\2\2\2\u00e3\u00e4\7\13\2\2\u00e4\u00e6\7\33\2\2\u00e5\u00e7\7\36\2\2"+
		"\u00e6\u00e5\3\2\2\2\u00e6\u00e7\3\2\2\2\u00e7\u00e8\3\2\2\2\u00e8\u00e9"+
		"\5\f\7\2\u00e9\37\3\2\2\2\u00ea\u00ec\7\5\2\2\u00eb\u00ea\3\2\2\2\u00eb"+
		"\u00ec\3\2\2\2\u00ec\u00ed\3\2\2\2\u00ed\u00ef\5\"\22\2\u00ee\u00f0\7"+
		"\4\2\2\u00ef\u00ee\3\2\2\2\u00ef\u00f0\3\2\2\2\u00f0\u00f1\3\2\2\2\u00f1"+
		"\u00f3\b\21\1\2\u00f2\u00f4\5$\23\2\u00f3\u00f2\3\2\2\2\u00f3\u00f4\3"+
		"\2\2\2\u00f4\u00f5\3\2\2\2\u00f5\u00f6\b\21\1\2\u00f6!\3\2\2\2\u00f7\u00f9"+
		"\7\3\2\2\u00f8\u00fa\7(\2\2\u00f9\u00f8\3\2\2\2\u00f9\u00fa\3\2\2\2\u00fa"+
		"\u00fe\3\2\2\2\u00fb\u00fe\7\22\2\2\u00fc\u00fe\7\17\2\2\u00fd\u00f7\3"+
		"\2\2\2\u00fd\u00fb\3\2\2\2\u00fd\u00fc\3\2\2\2\u00fe\u00ff\3\2\2\2\u00ff"+
		"\u0123\b\22\1\2\u0100\u0102\7*\2\2\u0101\u0103\7(\2\2\u0102\u0101\3\2"+
		"\2\2\u0102\u0103\3\2\2\2\u0103\u0106\3\2\2\2\u0104\u0106\7!\2\2\u0105"+
		"\u0100\3\2\2\2\u0105\u0104\3\2\2\2\u0106\u0107\3\2\2\2\u0107\u0123\b\22"+
		"\1\2\u0108\u010a\7\t\2\2\u0109\u010b\7\'\2\2\u010a\u0109\3\2\2\2\u010a"+
		"\u010b\3\2\2\2\u010b\u010e\3\2\2\2\u010c\u010e\7\27\2\2\u010d\u0108\3"+
		"\2\2\2\u010d\u010c\3\2\2\2\u010e\u010f\3\2\2\2\u010f\u0123\b\22\1\2\u0110"+
		"\u0112\7\3\2\2\u0111\u0110\3\2\2\2\u0111\u0112\3\2\2\2\u0112\u0113\3\2"+
		"\2\2\u0113\u0114\7\20\2\2\u0114\u0115\3\2\2\2\u0115\u0123\b\22\1\2\u0116"+
		"\u0117\t\3\2\2\u0117\u0123\b\22\1\2\u0118\u0119\t\4\2\2\u0119\u0123\b"+
		"\22\1\2\u011a\u011b\t\5\2\2\u011b\u0123\b\22\1\2\u011c\u011d\t\6\2\2\u011d"+
		"\u0123\b\22\1\2\u011e\u011f\t\7\2\2\u011f\u0123\b\22\1\2\u0120\u0121\7"+
		"\65\2\2\u0121\u0123\b\22\1\2\u0122\u00fd\3\2\2\2\u0122\u0105\3\2\2\2\u0122"+
		"\u010d\3\2\2\2\u0122\u0111\3\2\2\2\u0122\u0116\3\2\2\2\u0122\u0118\3\2"+
		"\2\2\u0122\u011a\3\2\2\2\u0122\u011c\3\2\2\2\u0122\u011e\3\2\2\2\u0122"+
		"\u0120\3\2\2\2\u0123#\3\2\2\2\u0124\u0125\7+\2\2\u0125\u0126\7\63\2\2"+
		"\u0126\u0127\7,\2\2\u0127\u0136\b\23\1\2\u0128\u0129\7+\2\2\u0129\u012a"+
		"\7\63\2\2\u012a\u012b\b\23\1\2\u012b\u012c\7-\2\2\u012c\u012d\7\63\2\2"+
		"\u012d\u012e\7,\2\2\u012e\u0136\b\23\1\2\u012f\u0130\7+\2\2\u0130\u0131"+
		"\7\62\2\2\u0131\u0132\7-\2\2\u0132\u0133\7\63\2\2\u0133\u0134\7,\2\2\u0134"+
		"\u0136\b\23\1\2\u0135\u0124\3\2\2\2\u0135\u0128\3\2\2\2\u0135\u012f\3"+
		"\2\2\2\u0136%\3\2\2\2\u0137\u0139\7\5\2\2\u0138\u0137\3\2\2\2\u0138\u0139"+
		"\3\2\2\2\u0139\u013a\3\2\2\2\u013a\u013c\7\65\2\2\u013b\u013d\7\4\2\2"+
		"\u013c\u013b\3\2\2\2\u013c\u013d\3\2\2\2\u013d\'\3\2\2\2\u013e\u0140\7"+
		"\5\2\2\u013f\u013e\3\2\2\2\u013f\u0140\3\2\2\2\u0140\u0141\3\2\2\2\u0141"+
		"\u0143\7\65\2\2\u0142\u0144\7\4\2\2\u0143\u0142\3\2\2\2\u0143\u0144\3"+
		"\2\2\2\u0144)\3\2\2\2+\668Rbs\177\u0087\u008b\u0090\u0095\u00a3\u00a8"+
		"\u00ad\u00b1\u00b5\u00b8\u00bb\u00be\u00c3\u00c7\u00cc\u00d7\u00da\u00e1"+
		"\u00e6\u00eb\u00ef\u00f3\u00f9\u00fd\u0102\u0105\u010a\u010d\u0111\u0122"+
		"\u0135\u0138\u013c\u013f\u0143";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}