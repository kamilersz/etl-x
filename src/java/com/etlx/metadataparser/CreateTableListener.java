package com.etlx.metadataparser;

import com.etlx.metadata.*;
// Generated from CreateTable.g4 by ANTLR 4.1

import java.util.ArrayList;
import java.util.List;


import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link CreateTableParser}.
 */
public interface CreateTableListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link CreateTableParser#drop_statement}.
	 * @param ctx the parse tree
	 */
	void enterDrop_statement(@NotNull CreateTableParser.Drop_statementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CreateTableParser#drop_statement}.
	 * @param ctx the parse tree
	 */
	void exitDrop_statement(@NotNull CreateTableParser.Drop_statementContext ctx);

	/**
	 * Enter a parse tree produced by {@link CreateTableParser#column_constraint}.
	 * @param ctx the parse tree
	 */
	void enterColumn_constraint(@NotNull CreateTableParser.Column_constraintContext ctx);
	/**
	 * Exit a parse tree produced by {@link CreateTableParser#column_constraint}.
	 * @param ctx the parse tree
	 */
	void exitColumn_constraint(@NotNull CreateTableParser.Column_constraintContext ctx);

	/**
	 * Enter a parse tree produced by {@link CreateTableParser#data_type_def}.
	 * @param ctx the parse tree
	 */
	void enterData_type_def(@NotNull CreateTableParser.Data_type_defContext ctx);
	/**
	 * Exit a parse tree produced by {@link CreateTableParser#data_type_def}.
	 * @param ctx the parse tree
	 */
	void exitData_type_def(@NotNull CreateTableParser.Data_type_defContext ctx);

	/**
	 * Enter a parse tree produced by {@link CreateTableParser#prompt_def}.
	 * @param ctx the parse tree
	 */
	void enterPrompt_def(@NotNull CreateTableParser.Prompt_defContext ctx);
	/**
	 * Exit a parse tree produced by {@link CreateTableParser#prompt_def}.
	 * @param ctx the parse tree
	 */
	void exitPrompt_def(@NotNull CreateTableParser.Prompt_defContext ctx);

	/**
	 * Enter a parse tree produced by {@link CreateTableParser#table}.
	 * @param ctx the parse tree
	 */
	void enterTable(@NotNull CreateTableParser.TableContext ctx);
	/**
	 * Exit a parse tree produced by {@link CreateTableParser#table}.
	 * @param ctx the parse tree
	 */
	void exitTable(@NotNull CreateTableParser.TableContext ctx);

	/**
	 * Enter a parse tree produced by {@link CreateTableParser#primary_constraint2}.
	 * @param ctx the parse tree
	 */
	void enterPrimary_constraint2(@NotNull CreateTableParser.Primary_constraint2Context ctx);
	/**
	 * Exit a parse tree produced by {@link CreateTableParser#primary_constraint2}.
	 * @param ctx the parse tree
	 */
	void exitPrimary_constraint2(@NotNull CreateTableParser.Primary_constraint2Context ctx);

	/**
	 * Enter a parse tree produced by {@link CreateTableParser#column_name}.
	 * @param ctx the parse tree
	 */
	void enterColumn_name(@NotNull CreateTableParser.Column_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CreateTableParser#column_name}.
	 * @param ctx the parse tree
	 */
	void exitColumn_name(@NotNull CreateTableParser.Column_nameContext ctx);

	/**
	 * Enter a parse tree produced by {@link CreateTableParser#schema}.
	 * @param ctx the parse tree
	 */
	void enterSchema(@NotNull CreateTableParser.SchemaContext ctx);
	/**
	 * Exit a parse tree produced by {@link CreateTableParser#schema}.
	 * @param ctx the parse tree
	 */
	void exitSchema(@NotNull CreateTableParser.SchemaContext ctx);

	/**
	 * Enter a parse tree produced by {@link CreateTableParser#length_constraint}.
	 * @param ctx the parse tree
	 */
	void enterLength_constraint(@NotNull CreateTableParser.Length_constraintContext ctx);
	/**
	 * Exit a parse tree produced by {@link CreateTableParser#length_constraint}.
	 * @param ctx the parse tree
	 */
	void exitLength_constraint(@NotNull CreateTableParser.Length_constraintContext ctx);

	/**
	 * Enter a parse tree produced by {@link CreateTableParser#table_element_list}.
	 * @param ctx the parse tree
	 */
	void enterTable_element_list(@NotNull CreateTableParser.Table_element_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link CreateTableParser#table_element_list}.
	 * @param ctx the parse tree
	 */
	void exitTable_element_list(@NotNull CreateTableParser.Table_element_listContext ctx);

	/**
	 * Enter a parse tree produced by {@link CreateTableParser#column_list}.
	 * @param ctx the parse tree
	 */
	void enterColumn_list(@NotNull CreateTableParser.Column_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link CreateTableParser#column_list}.
	 * @param ctx the parse tree
	 */
	void exitColumn_list(@NotNull CreateTableParser.Column_listContext ctx);

	/**
	 * Enter a parse tree produced by {@link CreateTableParser#table_element}.
	 * @param ctx the parse tree
	 */
	void enterTable_element(@NotNull CreateTableParser.Table_elementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CreateTableParser#table_element}.
	 * @param ctx the parse tree
	 */
	void exitTable_element(@NotNull CreateTableParser.Table_elementContext ctx);

	/**
	 * Enter a parse tree produced by {@link CreateTableParser#data_type}.
	 * @param ctx the parse tree
	 */
	void enterData_type(@NotNull CreateTableParser.Data_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link CreateTableParser#data_type}.
	 * @param ctx the parse tree
	 */
	void exitData_type(@NotNull CreateTableParser.Data_typeContext ctx);

	/**
	 * Enter a parse tree produced by {@link CreateTableParser#primary_constraint}.
	 * @param ctx the parse tree
	 */
	void enterPrimary_constraint(@NotNull CreateTableParser.Primary_constraintContext ctx);
	/**
	 * Exit a parse tree produced by {@link CreateTableParser#primary_constraint}.
	 * @param ctx the parse tree
	 */
	void exitPrimary_constraint(@NotNull CreateTableParser.Primary_constraintContext ctx);

	/**
	 * Enter a parse tree produced by {@link CreateTableParser#default_value}.
	 * @param ctx the parse tree
	 */
	void enterDefault_value(@NotNull CreateTableParser.Default_valueContext ctx);
	/**
	 * Exit a parse tree produced by {@link CreateTableParser#default_value}.
	 * @param ctx the parse tree
	 */
	void exitDefault_value(@NotNull CreateTableParser.Default_valueContext ctx);

	/**
	 * Enter a parse tree produced by {@link CreateTableParser#table_name}.
	 * @param ctx the parse tree
	 */
	void enterTable_name(@NotNull CreateTableParser.Table_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CreateTableParser#table_name}.
	 * @param ctx the parse tree
	 */
	void exitTable_name(@NotNull CreateTableParser.Table_nameContext ctx);

	/**
	 * Enter a parse tree produced by {@link CreateTableParser#table_list}.
	 * @param ctx the parse tree
	 */
	void enterTable_list(@NotNull CreateTableParser.Table_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link CreateTableParser#table_list}.
	 * @param ctx the parse tree
	 */
	void exitTable_list(@NotNull CreateTableParser.Table_listContext ctx);

	/**
	 * Enter a parse tree produced by {@link CreateTableParser#alter_table_pk}.
	 * @param ctx the parse tree
	 */
	void enterAlter_table_pk(@NotNull CreateTableParser.Alter_table_pkContext ctx);
	/**
	 * Exit a parse tree produced by {@link CreateTableParser#alter_table_pk}.
	 * @param ctx the parse tree
	 */
	void exitAlter_table_pk(@NotNull CreateTableParser.Alter_table_pkContext ctx);

	/**
	 * Enter a parse tree produced by {@link CreateTableParser#alter_table_fk}.
	 * @param ctx the parse tree
	 */
	void enterAlter_table_fk(@NotNull CreateTableParser.Alter_table_fkContext ctx);
	/**
	 * Exit a parse tree produced by {@link CreateTableParser#alter_table_fk}.
	 * @param ctx the parse tree
	 */
	void exitAlter_table_fk(@NotNull CreateTableParser.Alter_table_fkContext ctx);

	/**
	 * Enter a parse tree produced by {@link CreateTableParser#table_def}.
	 * @param ctx the parse tree
	 */
	void enterTable_def(@NotNull CreateTableParser.Table_defContext ctx);
	/**
	 * Exit a parse tree produced by {@link CreateTableParser#table_def}.
	 * @param ctx the parse tree
	 */
	void exitTable_def(@NotNull CreateTableParser.Table_defContext ctx);
}