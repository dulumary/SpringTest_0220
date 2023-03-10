<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>즐겨찾기 추가</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

  <script src="https://code.jquery.com/jquery-3.6.3.min.js" integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU=" crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</head>
<body>

	<div class="container">
		<h1>즐겨 찾기 추가하기</h1>
		<label>제목</label>
		<input type="text" class="form-control" id="nameInput">
		<label class="mt-3">주소</label>
		<div class="d-flex">
			<input type="text" class="form-control" id="urlInput">
			<button type="button" class="btn btn-info" id="duplicateBtn">중복확인</button>
		</div>
		<div class="small text-danger d-none" id="duplicateText">중복된 url 입니다</div>
		<div class="small text-success d-none" id="availableText">저장 가능한 url 입니다</div>
		<button type="button" id="addBtn" class="btn btn-success btn-block mt-3">추가</button>
	</div>
	
	<script>
		$(document).ready(function() {
			
			var isChecked = false;
			var isDuplicate = true;
			
			
			$("#urlInput").on("input", function() {
				isChecked = false;
				isDuplicate = true;
				$("#duplicateText").addClass("d-none");
				$("#availableText").addClass("d-none");
			});
			
			$("#duplicateBtn").on("click", function() {
				let url = $("#urlInput").val();
				
				if(url == "") {
					alert("주소를 입력하세요");
					return ;
				}
				
				// http로 시작하지 않고 https 로 시작하지도 않으면 경고창을 띄워라
				// http://naver.com
				if(!url.startsWith("http://") && !url.startsWith("https://")) {
					alert("주소 형식이 잘못되었습니다");
					return ;
				}
				
				
				$.ajax({
					type:"post"
					, url:"/ajax/favorite/is_duplicate"
					, data:{"url":url}
					, success:function(data) {
						
						// 중복체크 여부 저장
						isChecked = true;
						
						// 중복된 경우 : {"is_duplicate":true}
						// 중복이 안된 경우 : {"is_duplicate":false}
						if(data.is_duplicate) {
							$("#duplicateText").removeClass("d-none");
							$("#availableText").addClass("d-none");
							
							isDuplicate = true;
						} else {
							$("#availableText").removeClass("d-none");
							$("#duplicateText").addClass("d-none");
							
							isDuplicate = false;
						}
					}
					, error:function() {
						
						alert("중복확인 에러");
					}
				});
				
			});
			
			$("#addBtn").on("click", function() {
				let name = $("#nameInput").val();
				let url = $("#urlInput").val();
				
				if(name == "") {
					alert("이름을 입력하세요");
					return ;
				}
				
				if(url == "") {
					alert("주소를 입력하세요");
					return ;
				}
				
				// http로 시작하지 않고 https 로 시작하지도 않으면 경고창을 띄워라
				// http://naver.com
				if(!url.startsWith("http://") && !url.startsWith("https://")) {
					alert("주소 형식이 잘못되었습니다");
					return ;
				}
				
				// 중복체크가 되지 않은 경우 
				//if(isChecked == false) {
				if(!isChecked) {
					alert("중복체크를 진행해주세요");
					return ;
				}
				
				// 중복된 url인 경우 
				if(isDuplicate) {
					alert("중복된 url 입니다");
					return ;
				}
				

				///ajax/favorite/add?name=네이버&url=https://naver.com
				$.ajax({
					type:"post"
					, url:"/ajax/favorite/add"
					, data:{"name":name, "url":url }
					, success:function(data) {
						// 성공 : {"result":"success"}
						// 실패 : {"result":"fail"}
					
						if(data.result == "success") {
							location.href = "/ajax/favorite/list";
							
						} else {
							alert("추가 실패");
						}
					}
					, error:function() {
						alert("추가 에러");
					}
				});
				
			});
			
			
		});
	
	</script>
	
</body>
</html>