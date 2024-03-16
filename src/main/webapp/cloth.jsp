<%@page import="kr.ac.kopo.cloth.service.ClothService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	ClothService cs = new ClothService();
%>
    
<script>
/*
    function HeadChange(headSelect){
	    let head = document.getElementById("HeadImg");
        head.src = headSelect;
    }
*/
    function clothChange(sel){
	    let icon = sel.parentNode.parentNode.children[0].children[0];
        icon.src = sel.value;
    }

</script>
<hr>
<table class="clothtable">
	<tr>
		<td>
			<img id="HeadImg" width="50px" src="https://cdn-icons-png.flaticon.com/128/2806/2806254.png" 
			alt="모자">
		</td>
		<td>
			<select id="HeadSelect" onchange="clothChange(this)">
				<option value = "https://cdn-icons-png.flaticon.com/128/2806/2806254.png" title = "https://cdn-icons-png.flaticon.com/128/2806/2806254.png">
				모자</option>
				<option value = "https://cdn-icons-png.flaticon.com/128/3727/3727260.png" title="https://cdn-icons-png.flaticon.com/128/3727/3727260.png">
				털모자</option>
			</select>
		</td>
	</tr>
	<tr>
		<td>
			<img id="TopImg" width="50px" src="https://img.icons8.com/ios-filled/50/t-shirt--v1.png" alt="상의">
		</td>
		<td>
			<select id="TopSelect" onchange="clothChange(this)">
				<option value = "https://img.icons8.com/ios-filled/50/t-shirt--v1.png" title = "https://img.icons8.com/ios-filled/50/t-shirt--v1.png">
				티셔츠</option>
				<option value = "https://img.icons8.com/ios-filled/50/shirt.png" title = "https://img.icons8.com/ios-filled/50/shirt.png">
				셔츠</option>
			</select>
		</td>
	</tr>
</table>
<!-- 	
<select>
	<option value = "하의" title = "바지">
	바지</option>
</select>
<select>
	<option value = "신발" title = "신발">
	신발</option>
</select>
<select>
	<option value = "기타" title = "기타">
	기타</option>
</select>
 -->