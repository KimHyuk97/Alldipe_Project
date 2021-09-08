function reviewContent(i){
	$.ajax({
		type:"post",
		url:"./reviewView",
		data:{
			sno:i
		},
		dataType:"json",
		success:function(o){
			reviewView2(o);
		},
		error:function(request,status,error){
		    alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	    }
	});
}

function reviewView2(o){
	const j = document.getElementById('primgList');

	var k = "";
	k += "<div class='primgListContent'>";
	k += "<div class='primgListTitle'><h1>구매 후기</h1><input type='button' value='X' class='primgListClose' onclick='closeReview()'></div>";
	k += "<div class='reviewTable'>";
	k += "<table>";
	k += "<tr><th>주문자</th><td>"+o[0].writer+"</td></tr>";
	k += "<tr><th>제목</th><td>"+o[0].title+"</td></tr>";
	k += "<tr><th>주문번호</th><td><a href='orderInfo?orderNo="+o[0].orderNo+"'>"+o[0].orderNo+"</a></td></tr>";
	k += "<tr><th>마일리지 지급여부</th><td>"
	if(o[0].addMileageFl){
		k += "지급완료";
	}else{
		k += "미지급";
	}
	k += "</td></tr>";
	k += "<tr><td colspan='2'>";
	for(var pt = 0; pt < o[0].goodsPt; pt++){
	k += "<i class='fas fa-star'></i>"				
	}
	k += "</td></tr>";			
	k += "<tr><td colspan='2'><div class='reviewGoodsInfo'><img src='"+o[0].representImg+"'><span>"+o[0].goodsNm+"</span></div></td></tr>";
	k += "<tr><td colspan='2'>"+o[0].contents+"</td></tr>";
	k += "<tr><td colspan='2'><div class='reviewImgInfo'><img src='"+o[0].reviewImg+"'></td></div></tr>";
	k += "</table>";
	k += "</div>";
	k += "</div>";
	
	j.innerHTML = k;
	j.style.display = "block";
}

function closeReview(){
	document.getElementById('primgList').style.display = "none";
}



function deleteFl(){
    var check = document.querySelectorAll('input[name="ck"]:checked');
    if(check.length > 0){		    	
	    var val = [];
	    for(var i = 0; i < check.length; i++){
			val.push(check[i].value);
		}
		var d = confirm("선택한"+check.length+"개를 삭제하시겠습니가?");
		if(d){
			$.ajax({
				type:"post",
				url:"./deleteFl",
				traditional : true,	//ajax 배열 보내는법
				data: {
					sno:val
				},
				dataType:"json",
				success:function(i){
					if(i == 1){
						alert("삭제되었습니다.");
						location.reload();
					}else{
						alert("삭제 실패");
					}
				},
				error:function(){
					alert('삭제실패(통신장애)')
				}
				
			});
		}
    }else{
    	alert("삭제할 리뷰를 선택해주세요.");
    }
	
}  