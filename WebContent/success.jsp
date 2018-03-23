<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">
function validateForm() {
	var numElements = document.getElementById("printTable").getElementsByTagName("td").length;
	var x = document.forms["gradeForm"]["gradeInput"].value;
	var y = document.forms["gradeForm"]["courseInput"].value;
	//numRows = numElements / 4 columns
	//alert(numElements/4);
	
	//alert(nameTest);

	if (x == "") {
		alert("Grade must be filled out");
		return false;
	}

	if (isNaN(x)) {
		alert("Grade must be a number.");
		return false;
	} else {
		if (parseInt(x) > 100 || parseInt(x) < 0) {
			alert("Grade must be between 0 and 100.");
			return false;
		}  
	 }
	
	if (y == "") {
		alert("Course must be filled out");
		return false;
	} else if (y == "N/A") {
		alert("Invalid course name 'N\A'.");
		return false;
	} else if (y.indexOf('\\') != -1) {
		alert("Backslash character is not allowed.");
		return false;
	}

	 if (y.length > 30) {
	    	alert("Course too long. Must be less than 30 characters.");
	        return false;
	 }
	
	var placeholder = numElements/4;

	for (var w = 0; w < placeholder; w++)
	{
		nameTest = document.getElementById("printTable").childNodes[3+w].childNodes[0].childNodes[0].innerHTML;
		if (nameTest.toLowerCase() == y.toLowerCase())
		{
			alert("That course already exists, try to include course codes!");
			return false;
		}
	}
}

function deleteRow(o) {
	var c=o.parentNode.parentNode.childNodes[0].innerHTML;
	//var c=o.parentNode.parentNode.childNodes[1].innerHTML;
	//var d=o.parentNode.parentNode.childNodes[2].innerHTML;
	//alert(p);
	//alert(c);
	//alert(d);
    
	var idVar = ${personID};

	//jquery
	$(document).ready(function(){
		$.post('DeleteGrade', {varName : c, varID : idVar}, function(data){
			//alert(data);
			<% //request.getRequestDispatcher("DynamicStudent").forward(request, response); %>
			<% //request.getRequestDispatcher("DynamicStudent").forward(request, response); %>
			//window.location.replace("DynamicStudent");
		});
	});
	
    var p=o.parentNode.parentNode;
    p.parentNode.removeChild(p);
    
    var lastIndex = document.getElementById("test13").innerHTML.lastIndexOf("g");
    //alert("hi");
    var string = document.getElementById("test13").innerHTML.substring(0, lastIndex);
    //alert(document.getElementById("test13").innerHTML);
	//alert(string);
	//alert(string.length);
    //alert(document.getElementById("test13").value)
    //alert(document.getElementById("test12").value);

    //alert(string.charAt(string.length - 3)); //1
    lastIndex = string.lastIndexOf(string.charAt(string.length - 3));
    var numberTwo = string.charAt(string.length - 3) - 1;
    //alert(numberTwo);
	//alert(lastIndex);
    var newString = string.substring(0, lastIndex);
    document.getElementById("test13").innerHTML = newString + numberTwo + " grades entered.";

    //var number = parseInt(string.charAt(string.length - 3)) - 1;
    //alert(number);
    
    
    	//alert(data);
    	//alert(data.redirect);
    	//window.location.replace(data.redirect);
    	//var myObject = JSON.parse(data);
    	//alert(myObject[0]);
    	//alert(myObject);
    	//var test = JSON.stringify(data);
    	//window.location.replace("DeleteGrade");
	//window.location.replace("DynamicStudent");
    	/*
        var receivedData = [];
        
		alert("hey");
        $.each(data.jsonArray, function(index) {
            $.each(data.jsonArray[index], function(key, value) {
                var point = [];

                    point.push(key);
                    point.push(value);
                    receivedData.push(point);

                }); 
        });
		alert("hey");
		alert(receivedData);
        alert(receivedData[0]);
        */
    
    <% //response.sendRedirect("DynamicStudent");%>
	<% //response.sendRedirect("DeleteGrade"); %>
}

function parseGrades(arrayString) {
	var gradeArray = new Array();
	gradeArray = arrayString.split("`");
	return gradeArray;
}

function parseYears(arrayString) {
	var yearArray = new Array();
	yearArray = arrayString.split("`");
	return yearArray;
}

function fillStatistics() {
	var table = document.getElementById("printTable");
	var avgDiv, meanDiv;
	var orderedGrades = []; //dynamic array
	avgDiv = document.getElementById("average");
	meanDiv = document.getElementById("median");
	var average = 0, mean = 0;
	for (var i = 1, row; row = table.rows[i]; i++) {
		for (var j = 0, col; col = row.cells[j]; j++) {
			if (j == 1)
		   	{
		   		//alert(row.cells[j].innerHTML);
		   		//alert(row.cells[j].innerHTML);
		   		average += parseInt(row.cells[j].innerHTML);
		   		orderedGrades.push(parseInt(row.cells[j].innerHTML));
		   	}
	     //iterate through columns
	     //columns would be accessed using the "col" variable assigned in the for loop
	   	}
	}
	average = average / table.rows[0].cells.length;
	avgDiv.innerHTML = average;
	orderedGrades.sort();
	//alert(orderedGrades);
	if (orderedGrades.length % 2 == 0)
	{
		meanDiv.innerHTML = (orderedGrades[orderedGrades.length / 2] + orderedGrades[orderedGrades.length / 2 - 1]) / 2;
	}
	else 
	{
		meanDiv.innerHTML = orderedGrades[orderedGrades.length / 2];
	}
}

function printtoTable(c, g, y) {
	if (c.length < 2) //test for courses with 1 letter
	{
		document.getElementById("printTable").innerHTML += "<tr>" + "<td>" + "N/A" + "</td>" + "<td>" + "N/A" + "</td>" + "<td>" + "N/A" + "</td>" + "</tr>";
	}
	var courseArray = c.split("`");
	//document.getElementById("demo").innerHTML += courseString;
	//alert("hello");

	for (var d = 0; d < courseArray.length-1; d++)
	{
		//alert(g[d])
		document.getElementById("printTable").innerHTML += "<tr>" + "<td>" + courseArray[d] + "</td>" + "<td>" + g[d] + "</td>" + "<td>" + y[d] + "</td>" + "<td>" + "<button onclick='deleteRow(this)'>Delete</button></td>" + "</tr>";
	}
}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Homepage</title>
<link href="styleLogin.css" rel="stylesheet">
</head>
<body>
	<div class="header">
    	<div><a href="index.html  ">&nbsp&nbspHOME&nbsp&nbsp&nbsp</a></div>
    	<div><a>ABOUT&nbsp&nbsp&nbsp</a></div>
    	<div><a>HELP&nbsp&nbsp&nbsp</a></div>
    	<div><a href="index.html">LOG OUT&nbsp&nbsp&nbsp</a></div>
  	</div>

	<h1 id="test12">Welcome ${name}!</h1>
	<h5 id="test13">Stats for <%out.println(request.getAttribute("name"));%> : <%out.println(request.getAttribute("numGrades"));%> grades entered.</h5>

	<div id="testerino"></div>
	<p id="demo"></p>


	<div class="tableO">
	<table border="1" cellpadding="3" id="printTable">
		<tbody>
			<tr>
				<th>Course</th>
				<th>Grade</th>
				<th>Year</th>
				<th>Remove Grade</th>
			</tr>
		</tbody>
	</table>
</div>

<div class="formO">
	<form name="gradeForm" onsubmit="return validateForm()" method="post"
		action="AddGrade?id=<%= request.getAttribute("personID") %>">
		<div style="float:left; padding-right: 5px;">Grade: <input style="padding-right: 10px" type="text" name="gradeInput" /> <br />
		Course: <input style="padding-right: 4px;" type="text" name="courseInput" /> <br /></div>
			Label: <select id="year" name="yearInput">
				<option value="1">1</option>
				<option value="2">2</option>
				<option value="3">3</option>
				<option value="4">4</option>
				<option value="5">5</option>
				<option value="6">6</option>
				<option value="7">7</option>
				<option value="8">8</option>
			</select><br />
		<input type="submit" value="Submit" />
	</form>
</div><br>

<br>
<div style="height: 200px; width: 200px; background-color: white; color: black; margin-left: 200px;">
	Average: <div id="average" style="color:black;"></div><br>
	Median: <div id="median" style="color:black;"></div><br>
</div>

	<script>
		var foo = '${test}';
		var array = '${gradesList}';
		var gradeA = parseGrades('${test}');
		var yearA = parseYears('${year}');
	</script>

	<script>printtoTable('${courses}', gradeA, yearA);</script>

	<script>
		fillStatistics();
/*
	var string = "";
	for (var z = 0; z < array.length; z++)
	{
		var string = string + array[z];	
	} //document.getElementById("demo").innerHTML = string;
	document.getElementById("demo").innerHTML = '${test}';
	*/
	</script>

	<p id="blah">To do: Be able to enter a grade and a course. Have statistics
		(Average) somewhere. Be able to edit x course and add in weight
		percentages and fill some in (therefore gaining knowledge of current
		grade and what you need on exam to pass) also add in semesters
		possibly? Make table orderable, maybe move to middle, underneath login have stats</p>

</body>
</html>