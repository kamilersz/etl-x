<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
        <title>ETL-X</title>
  <link rel="stylesheet" href="dist/themes/default/style.min.css" />
  <link rel="stylesheet" href="dist/style.css" />
  <link href="dist/skin/ui.dynatree.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="left_db box_db">
  <div class="box_db_title">DB 1</div>
  <div id="db_1">
  </div>
</div>
  <div id="candidate_lines">
  </div>
  <div id="map_lines">
  </div>
<div class="right_db box_db">
  <div class="box_db_title">DB 2</div>
  <div id="db_2">
  </div>
</div>
<button id='new_project'>New Project</button>
<button id='load_candidate'>Load Candidate</button>
<button id='save_mapping'>Save Mapping</button>
<button id='load_mapping'>Load Mapping</button>
<button id='set_map'>Set Map</button>
<button id='clear_mapping'>Clear</button>
	<script src="dist/libs/jquery.js" type="text/javascript"></script>
	<script src="dist/libs/jquery-ui.custom.js" type="text/javascript"></script>
	<script src="dist/libs/jquery.cookie.js" type="text/javascript"></script>
  <script src="dist/jquery.dynatree.min.js"></script>
  <script>
$("#load_candidate").on("click",function() {
	loadCandidate();
	drawCandidate();
});
$("#load_mapping").on("click",function() {
	mapping = JSON.parse(localStorage.getItem("mapping"));
	drawMapping();
});
$("#save_mapping").on("click",function() {
	localStorage.setItem("mapping",JSON.stringify(mapping));
});
$("#clear_mapping").on("click",function() {
	$('#candidate_lines').empty();
	$('#map_lines').empty();
});
$("#set_map").on("click",function() {
	var found = false;
	for (i = 0; i < mapping.length; i++) {
		if (mapping[i].destination == selected_2.table) {
			if (typeof selected_2.column == "undefined") {
				mapping[i].source = [selected_1.table];
			} else {
				mapping[i].map[selected_2.column] = selected_1.column;
			}
			found = true;
		}
	}
	if (!found) {
		var _map = new Object;
		if (typeof selected_2.column != "undefined") {
			_map[selected_2.column] = selected_1.column;
		}
		var _mapping = {source: [selected_1.table], destination: selected_2.table, map: _map};
		mapping[mapping.length] = _mapping;
	}
	drawMapping();
});
var db_1;
var db_2;
var selected_1;
var selected_2;
var candidate;
var mapping = new Array();
$("#new_project").on("click",function() {
	var db_1_fn = prompt("Masukkan nama file DDL script 1", "");
	var db_2_fn = prompt("Masukkan nama file DDL script 2", "");
	$.post('schema2schema', {db_1: db_1_fn, db_2: db_2_fn}, function(response) {
    db_1 = response.db_1;
    db_2 = response.db_2;
    candidate = response.mapping;
		loadTable("db_1",db_1,1,true);
	loadTable("db_2",db_2,2,true);

}, 'json');

});
var table_map;
var column_map;
function loadCandidate() {
	table_map = candidate['table_map'];
	column_map = candidate['column_map'];
}
function drawCandidate(db_1_t,db_1_c,db_2_t,db_2_c) {
	$('#candidate_lines').empty();
	console.log(db_1_t,db_1_c,db_2_t,db_2_c);
	for (i=0; i < table_map.length; i++) {
		if (typeof db_1_t === "undefined") {
			if (typeof db_2_t === "undefined")
				drawLine(table_map[i][0],table_map[i][1]);
			else if (db_2_t == table_map[i][1] && typeof db_2_c === "undefined")
				drawLine(table_map[i][0],table_map[i][1]);
		} else {
		if (db_1_t == table_map[i][0] && typeof db_1_c === "undefined") {
			drawLine(table_map[i][0],table_map[i][1]);
		}}
	}
	for (i=0; i < column_map.length; i++) {
		if (typeof db_1_c === "undefined" && typeof db_1_t === "undefined") {
			if (typeof db_2_c === "undefined" && typeof db_2_t === "undefined")
				drawLine(column_map[i][0]+"_"+column_map[i][1],column_map[i][2]+"_"+column_map[i][3]);
			else if (db_2_t == column_map[i][2] && db_2_c == column_map[i][3])
				drawLine(column_map[i][0]+"_"+column_map[i][1],column_map[i][2]+"_"+column_map[i][3]);
		} else { if (db_1_t == column_map[i][0] && db_1_c == column_map[i][1]) {
			drawLine(column_map[i][0]+"_"+column_map[i][1],column_map[i][2]+"_"+column_map[i][3]);
		}}
	}
}
function drawMapping() {
	$('#map_lines').empty();
	for (i=0; i < mapping.length; i++) {
		drawMapLine(mapping[i].source[0],mapping[i].destination);
		for (var prop in mapping[i].map) {
			drawMapLine(mapping[i].source[0]+"_"+mapping[i].map[prop],mapping[i].destination+"_"+prop);
		}
	}
}
function drawMapLine(el1,el2) {
	var width = $("#map_lines").width();
	var from = $("#db_1").dynatree("getTree").getNodeByKey(el1);
	var to = $("#db_2").dynatree("getTree").getNodeByKey(el2);
	var x1 = width*0.4+9;
	var x2 = width*0.6+7;
	if (from != null && to != null) {
		var y1 = $(from.li).offset().top + 10;
		var y2 = $(to.li).offset().top + 10;
		var length = Math.sqrt((y2-y1)*(y2-y1)+(x2-x1)*(x2-x1));
		var angle = 180 / 3.14 * Math.asin((y2 - y1) / length);
		jQuery('<div></div>', {
			'id': "M"+"_"+el1+"_"+el2+"_1",
			'class': 'mapline',
		}).css('top',y1).css('left',x1).css('width',length)
		.css('-webkit-transform', 'rotate(' + angle + 'deg)')
		.css('-moz-transform', 'rotate(' + angle + 'deg)')
		.css('-o-transform', 'rotate(' + angle + 'deg)')
		.css('-ms-transform', 'rotate(' + angle + 'deg)')
		.css('transform', 'rotate(' + angle + 'deg)')
		.appendTo('#map_lines');
	}
}
function drawLine(el1,el2) {
	var width = $("#candidate_lines").width();
	var from = $("#db_1").dynatree("getTree").getNodeByKey(el1);
	var to = $("#db_2").dynatree("getTree").getNodeByKey(el2);
	var x1 = width*0.4+9;
	var x2 = width*0.6+7;
	if (from != null && to != null) {
		var y1 = $(from.li).offset().top + 10;
		var y2 = $(to.li).offset().top + 10;
		var length = Math.sqrt((y2-y1)*(y2-y1)+(x2-x1)*(x2-x1));
		var angle = 180 / 3.14 * Math.asin((y2 - y1) / length);
		jQuery('<div></div>', {
			'id': el1+"_"+el2+"_1",
			'class': 'line',
		}).css('top',y1).css('left',x1).css('width',length)
		.css('-webkit-transform', 'rotate(' + angle + 'deg)')
		.css('-moz-transform', 'rotate(' + angle + 'deg)')
		.css('-o-transform', 'rotate(' + angle + 'deg)')
		.css('-ms-transform', 'rotate(' + angle + 'deg)')
		.css('transform', 'rotate(' + angle + 'deg)')
		.appendTo('#candidate_lines');
	}
}
function gambar() {
		jQuery('<div></div>', {
			'id': "_1",
			'class': 'line',
		}).css('top',"123px").css('left',"41%").css('height',"100px").appendTo('#candidate_lines');
}
function getEntityName(entity) {
	return entity.split('#')[1];
}
function createMapping(source,destination) {
	var mapping = new Object();
	mapping.source = [source];
	mapping.destination = destination;
}
function getAttrText(column) {
	switch(column.data_type) {
		case 'varchar':
			return column.name+" [" +column.data_type + "("+column.length+")]";
		case 'date':
			return column.name+" [" +column.data_type + "]";
		case 'number':
			if (column.length == 0)
				return column.name+" [" +column.data_type + "]";
			else
				return column.name+" [" +column.data_type + "("+column.length+","+column.precision+")]";;
		case 'integer':
		case 'int':
			return column.name+" [" +column.data_type + "]";
		
	}
	
}
function getTable(dbname,db,num,isNotTree) {
	var tables = db.tables;
	var tables_obj = new Object();
	var data = new Array();
	for (i=0; i < tables.length; i++) {
		var name = tables[i].name;
			data.push({
			'key' : name,
			'title': name,
			'table': name,
			'db': dbname,
			'isLazy': true,
			'columns' : tables[i].columns,
			'icon' : 'table_16x16.png'
			});
	}	
	return data;
}

function colType(column) {
	switch(column.data_type) {
		case 'varchar':
			return 'varchar';
		case 'date':
			return 'date';
		case 'number':
			return 'decimal';
		case 'integer':
			return 'integer';
	}
}
function isPk(db,table,column) {
	var pks = db.primary_keys;
	for(i=0; i<pks.length; i++) {
		if (pks[i].table == table) {
			if ($.inArray(column,pks[i].columns) != -1) {
				return true;
			}
			return false;
		}
	}
	return false;
}
function loadTable(name,db,num,isNotTree) {
    $('#'+name).dynatree({
        'children' : getTable(name,db,num,isNotTree),
		onActivate: function(node) {
			// A DynaTreeNode object is passed to the activation handler
			// Note: we also get this event, if persistence is on, and the page is reloaded.
			if (node.data.db == "db_1") {
				drawCandidate(node.data.table,node.data.column,undefined,undefined);
				selected_1 = {table: node.data.table, column: node.data.column};
			} else {
				drawCandidate(undefined,undefined,node.data.table,node.data.column);
				selected_2 = {table: node.data.table, column: node.data.column};
			}
		},
		'persist' : false,
		'imagePath' : 'dist/icon/',
		'onLazyRead': function(node) {
			var columns = node.data.columns;
			var table_name = node.data.title;
			for(j=0; j < columns.length; j++) {
				node.addChild({
					'title': getAttrText(columns[j]),
					'key': table_name+"_"+columns[j].name,
					'table': table_name,
					'column': columns[j].name,
					'db': name,
					'icon': (isPk(db,table_name,columns[j].name)?'key':colType(columns[j])) + "_16x16.png",
				});
			}
			return true;
		},
	});
  }
function countTable(x) { 
	var sum = 0;
	for (i=0; i<x.length; i++) {
		sum += x[i].columns.length;
	}
	return sum;
}
  </script>
</body>
</html>
