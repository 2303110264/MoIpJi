<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<script>

    function HeadChange(headSelect){
	    let head = document.getElementById("HeadImg");
        head.src = headSelect;
    }

</script>
<hr>
<img id="HeadImg" width="50px" src="https://cdn-icons-png.flaticon.com/128/2806/2806254.png" 
alt="모자">
<select id="HeadSelect" onchange="HeadChange(this.value)">
	<option value = "https://cdn-icons-png.flaticon.com/128/2806/2806254.png" title = "https://cdn-icons-png.flaticon.com/128/2806/2806254.png">
	모자</option>
	<option value = "https://cdn-icons-png.flaticon.com/128/3727/3727260.png" title="https://cdn-icons-png.flaticon.com/128/3727/3727260.png">
	털모자</option>
</select>

<!-- 	
<select>
	<option value = "상의" title = "상의">
	상의</option>
</select>
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