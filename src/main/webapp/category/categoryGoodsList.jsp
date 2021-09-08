<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="https://kit.fontawesome.com/a61e1eaf11.js" crossorigin="anonymous"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/goods/alldipe_cate_banner.css">
<!--상단-->
<jsp:include page="../header.jsp" flush="false"/>
<div class="top_tit01">카테고리명 > 분류명>DFSF>SDFSDF</div>
<div class="cont_wrap">
        <div class="nav_left">
           <div class="categoryfilter">
				<!-- 대분류 -->
				<div class="largeCate">	
					<ul>
						<c:forEach var="c" items="${lc}" varStatus="status">
							<li id="lc${status.index}"><a href="">${c.cateNm}</a> <input type="button" value="▼" onclick="categoryOpen(${status.index},${c.cateCd })"></li>
							<!-- 중분류 -->
							<div class="mcate" id="medium_cate${status.index}"></div>
						</c:forEach>
					</ul>
				</div>
			</div>        
			<script>		
				function categoryOpen(i,cateCd){
					let cc = "00"+cateCd;
					$.ajax({
						type:"post",
						url:"mediumCategory",
						data:{
							cateCd:cc,
						},
						dataType:"json",
						success:function(cate){
							mc(cate,i);	
						},
						error:function(){
						
						}
					});	
				}
				
				function mc(cate,i){
					let s = "";
					s += "<ul>";
					for(let z = 0; z < cate.length; z++){	
						s += "<li>"+cate[z].cateNm+"<input type='button' value='▼' onclick='categorySmallOpen("+z+","+cate[z].cateCd+")'><div class='scate' id='small_cate"+z+"'></div></li>";
					}
					s += "</ul>";
					document.getElementById('medium_cate'+i).innerHTML = s;
					document.getElementById('medium_cate'+i).classList.toggle('active');
					document.getElementById('lc'+i).style.color = "#396ef3";
				}
				
				//중분류에 마우스를 올리면 소분류 on
				function categorySmallOpen(j,k){
					let cateCd;
					
					if(String(k).charAt(0) == "8"){
						cateCd = "00"+k;
					}else{
						cateCd = "00"+k.toString(8);
					}
					console.log(cateCd);
						$.ajax({
							type:"post",
							url:"smallCategory",
							data:{
								cateCd:cateCd,
							},
							dataType:"json",
							success:function(i){
								sc(i,j);
								console.log(i);
							},
							error:function(){
							
							}
						});
				}
				
				//소분류 on
				function sc(i,j){
					let s = "";
					s += "<ul>";
					for(let k = 0; k < i.length; k++){	
						s += "<li><a href='categoryGoods?cateCd="+i[k].cateCd+"'>"+i[k].cateNm+"</a></li>";
					}
					s += "</ul>";
					document.getElementById('small_cate'+j).innerHTML = s;
					document.getElementById('small_cate'+j).classList.toggle('active');					
				}
			</script>    
        </div>
        <div class="cont_right">
            <div class="banner_cont">
                <div class="banner_img banner_img01">
                   <img src="" alt=""> 
                </div>
                <div class="banner_img banner_img02">
                 <img src="" alt="">
                </div>
                <div class="banner_img banner_img03">
                    <img src="" alt="">
                </div>
            </div>
            <div class="top_tit02">
                전체상품
                <div class="top_tit02_right">
                   <button><i class="fas fa-chevron-down"></i> 무료배송</button>
                    <select>
                        <option value="">인기순</option>
                    </select> 
                </div>
                
            </div>
            
            <div class="content">
            	<c:forEach var="g" items="${cd}">
			    	<div class="content_box">
			        	<a href="${pageContext.request.contextPath}/goodsView?goodsNo=${g.goodsNo}">
			            	<img src="${g.representImg}" alt="">
		                	<div class="content_txt">
		                    	<div class="content_title">${g.goodsNm }</div>
		                        <div class="price">
		                        	할인혜택가 <span>${g.goodsDiscountPercent}%</span> <p>${g.goodsPrice}원</p>  <span>${g.fixedPrice}원</span>
		                        </div>
                                <div class="mark">
                                    <p>페스티벌</p>
                                    <p>무료배송</p>
                                </div>
		                    </div> 
	                    </a>            
	            	</div>
	        	</c:forEach>
            </div>
     		<div style="width:100%; margin:10px; display: flex; justify-content: center;">
                <c:if test="${paging.page<=1 }">[이전]&nbsp;</c:if>
                <c:if test="${paging.page>1 }">
                    <a href="goodsList?page=${paging.page-1}&cateCd=${gd.get(0).cateCd}">[이전]</a>&nbsp;</c:if> 
                    <c:forEach begin="${paging.startPage}" end="${paging.endPage }" var="i" step="1">
                    <c:choose>
                        <c:when test="${i eq paging.page }">	
                            [${i}]								
                        </c:when>
                        <c:otherwise>
                            <a href="goodsList?page=${i}&cateCd=${gd.get(0).cateCd}">${i}</a>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <c:if test="${paging.page>=paging.maxPage}">&nbsp;[다음]</c:if>
                <c:if test="${paging.page<paging.maxPage}">
                    <a href="goodsList?page=${paging.page+1}&cateCd=${gd.get(0).cateCd}">&nbsp;[다음]</a>
                </c:if>
            </div>        
        </div>
    </div>
    
</body>
<!--하단-->
<jsp:include page="../footer.jsp" flush="false"/>