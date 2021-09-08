<%@page import="dto.scmDTO.scmDTO"%>
<%@page import="dto.goodsDTO.goodsOptionDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dto.goodsDTO.goodsDTO"%>
<%@page import="dto.categoryDTO.categoryDTO"%>
<%@page import="dto.brandDTO.brandDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://kit.fontawesome.com/a61e1eaf11.js" crossorigin="anonymous"></script>
<script src="${pageContext.request.contextPath}/admin/js/goods/goodsMod.js"></script>

<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath }/admin/css/goods/goodsMod.css">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/partners/reset.css">

<%	
	
 	goodsDTO goods = (goodsDTO)request.getAttribute("g");
 	ArrayList<goodsOptionDTO> list = (ArrayList)request.getAttribute("o");
 	scmDTO scm = (scmDTO)session.getAttribute("scm");
 	
 	categoryDTO cate1 = (categoryDTO)request.getAttribute("c1");
 	categoryDTO cate2 = (categoryDTO)request.getAttribute("c2");
 	categoryDTO cate3 = (categoryDTO)request.getAttribute("c3");

 	
 	ArrayList<String> optionStr = null;
 	
 		if(list!=null && list.size()>0){
 			
 			optionStr = new ArrayList<>();
 			
 			if(list.get(0).getOptionNm1()!=null){
 				optionStr.add(list.get(0).getOptionNm1());
 			}
			if(list.get(0).getOptionNm2()!=null){
				optionStr.add(list.get(0).getOptionNm2());
 			}
			if(list.get(0).getOptionNm3()!=null){
 				optionStr.add(list.get(0).getOptionNm3());
 			}
			if(list.get(0).getOptionNm4()!=null){
 				optionStr.add(list.get(0).getOptionNm4());
 			}
			if(list.get(0).getOptionNm5()!=null){
 				optionStr.add(list.get(0).getOptionNm5());
 			}
 		}	
 	
%>
<jsp:include page="../adminHeader.jsp"/>
	<div class="cont_wrap">
		<div id="wrap">
        <div class="top_title">
            <h1>상품수정</h1>
        </div>
        <form id="form" action="./goodsModProcess" method="post" enctype="multipart/form-data">
        <input type="hidden" name="goodsNo" value="${g.goodsNo}">
        <input type="hidden" name="page" value="${page}"> 
        
        <div class="top_botton">
        	<button class="bottom" type="button" onclick="history.back()">취소</button>
        	<button class="bottom" type="button" onclick="save()">저장</button>
        </div>
        <div class="wrap wrap01">
            <h2>상품분류</h2>
            <div class="cont cont01">
                <p><i class="fas fa-circle"></i>상품이름</p>
                <input type="text" name="goodsNm" placeholder="[브랜드명] 상품명" value="${g.goodsNm }">
            </div>
            <div class="cont cont01">
                <p><i class="fas fa-circle"></i>카테고리</p>
	            <select name="cate" id="large" <%if(cate1 != null && cate1.getCateCd() != null){%> onclick="cateAction()" <%}%>  onchange="cateCheck()">
                   <option value=<%=(cate1 != null && cate1.getCateCd() != null)? cate1.getCateCd() : ""%>><%=(cate1 != null && cate1.getCateNm() != null)? cate1.getCateNm() : "대분류"%></option>
                </select>
                <select name="cate2" id="middle" <%if(cate1 != null && cate1.getCateCd() != null){%> onclick="cateMiddle('<%=cate1.getCateCd() %>')" <%}%>  onchange="cateCheck2()">
                   <option value=<%=(cate2 != null && cate2.getCateCd() != null)? cate2.getCateCd() : ""%>><%=(cate2 != null && cate2.getCateNm() != null)? cate2.getCateNm() : "중분류"%></option>
                </select>
                <select name="cate3" id="small" <%if(cate2 != null && cate2.getCateCd() != null){%> onclick="cateSmall('<%=cate2.getCateCd() %>')" <%}%> >
                   <option value=<%=(cate3 != null && cate3.getCateCd() != null)? cate3.getCateCd() : ""%>><%=(cate3 != null && cate3.getCateNm() != null)? cate3.getCateNm() : "소분류"%></option>
                </select>
            </div>
            <div class="cont cont01">
            	<input type="hidden" id="cateCd" name="cateCd" value=<%=(goods!=null && goods.getCateCd() != null)?goods.getCateCd():"" %>>
                <p><i class="fas fa-circle"></i>카테고리 수수료</p>
                <input type="text" id="commission" name="commission" value="${c3.commission}" readonly><span>%</span>
            </div>
            <div class="cont cont01">
                <p><i class="fas fa-circle"></i>검색 키워드</p>
                <input type="text" name="keyword" placeholder=",를 이용해 키워드 구분 (공백 사용X)" value=<%= (goods!=null && goods.getKeyword() != null)?goods.getKeyword():""%>>
            </div>
        </div>
        <div class="wrap wrap02">
            <h2>상품이미지</h2>
            <div class="cont cont02">
                <p><i class="fas fa-circle"></i>대표이미지</p>
                <input type="file" name="representImg" id="representImg">
            </div>
            <div class="cont cont02">
                <p><i class="fas fa-circle"></i>개별이미지</p>
                <input type="file" name="subImg" multiple="multiple">
            </div>
            <div class="cont cont02 cont_01">
                <p><i class="fas fa-circle"></i>상세설명</p>               
                <textarea class="summernote" name="goodsDescription"> <%=(goods!=null)? goods.getGoodsDescription() : "" %></textarea>
            </div>
        </div>
        <div class="wrap wrap03">
            <h2>상품필수정보</h2>
            <div class="cont cont03"  style="position:relative;">
                <p><i class="fas fa-circle"></i>브랜드</p>
                <input type="text" id="brand" placeholder="브랜드명" value="${b.brandNm}" onkeyup="brandSearch()">    
               	<div id="brand_search">
               	</div>
                <input type="hidden" name="brandCd" id="brandCd" value="${b.brandCd}">
            </div>
            <div class="cont cont03">
                <p><i class="fas fa-circle"></i>제조사</p>
                <input type="text" name="makerNm" placeholder="소매업" value=<%=(goods!=null)?goods.getMakerNm():"" %>>
            </div>
            <div class="cont cont03">
                <p><i class="fas fa-circle"></i>원산지</p>
                <input type="text" name="originNm" placeholder="원산지" value=<%=(goods!=null)?goods.getOriginNm():"" %>>
            </div>
            <div class="cont cont03">
                <p><i class="fas fa-circle"></i>성인인증여부</p>
                <label for="adult01"><input type="radio" name="adultFl" id="adult01" value="true" <%if(goods!=null && goods.isOnlyAdultFl()){%> checked="checked"  <%} %>><span>필요</span> </label>
                <label for="adult02"><input type="radio" name="adultFl" id="adult02" value="false" <%if(goods==null || !goods.isOnlyAdultFl()){%> checked="checked" <%} %>><span>불필요</span> </label>
            </div>
            <div class="cont cont03">
                <p><i class="fas fa-circle"></i>상품판매여부</p>
                <label for="goodsSellFl01"><input type="radio" name="goodsSellFl"  value="0" <%if(goods.getGoodsSellFl() == 0){ %> checked = "checked"<%} %> ><span>판매중지</span> </label>
                <label for="goodsSellFl02"><input type="radio" name="goodsSellFl"  value="1" <%if(goods.getGoodsSellFl() == 1){ %> checked = "checked"<%} %>><span>판매중</span> </label>
                <label for="goodsSellFl03"><input type="radio" name="goodsSellFl"  value="-1"<%if(goods.getGoodsSellFl() == -1){ %> checked = "checked"<%} %>><span>관리자판매중지</span> </label>
            </div>
            <div class="cont cont03">
                <p><i class="fas fa-circle"></i>멤버전용여부</p>
                <label for="memberOnly01"><input type="radio" name="memberOnly" value="true" <%if(goods.isMemberOnly()){ %>checked = "checked"<%} %>><span>회원전용</span> </label>
                <label for="memberOnly02"><input type="radio" name="memberOnly" value="false"<%if(!goods.isMemberOnly()){ %>checked = "checked"<%} %>><span>전체(회원+비회원)</span> </label>
            </div>
            <div class="cont cont03 cont_03">
                <p><i class="fas fa-circle"></i>과세여부선택</p>
                <label for="tax01"><input type="radio" name="tax" id="tax01"<%if(goods==null || goods!=null && goods.getTaxFreeFl().equals("t")){%> checked="checked"  <%} %> value="t"><span>과세</span></label>
                <label for="tax02"><input type="radio" name="tax" id="tax02"<%if(goods!=null && goods.getTaxFreeFl().equals("n")){%> checked="checked"  <%} %> value="n"><span>비과세</span></label>
                <label for="tax03"><input type="radio" name="tax" id="tax03"<%if(goods!=null && goods.getTaxFreeFl().equals("f")){%> checked="checked"  <%} %> value="f"><span>면세</span></label>
                <input type="text" name="taxPercent" value=<%=(goods!=null)?goods.getTaxPercent():"10" %>>%
            </div>
            <div class="cont cont03 cont_04">
                <p><i class="fas fa-circle"></i>상품무게/용량</p>
                <input type="text" name="goodsWeight" value=<%=(goods!=null)?goods.getGoodsWeight():"" %>><label for="goodsWeight"><span>g</span></label>
            </div>
            <div class="cont cont03 cont_05">
                <p><i class="fas fa-circle"></i>묶음주문 단위</p>
                <select name="fixedSales">
                    <option value="option">옵션 기준</option>
                    <option value="goods">상품 기준</option>
                </select>
                <input type="text" name="salesUnit" id="salesUnit" value="1"><label for="salesUnit"><span>개 단위로 주문 및 장바구니에 담김</span></label>
            </div>
            <div class="cont cont03 cont_06">
                <p><i class="fas fa-circle"></i>구매수량 설정</p>
               <label for="limit01"><input type="radio" name="limit" id="limit01" <%if(goods==null || goods!=null && !goods.isLimitFl()){ %> checked="checked" <%} %> value="false"><span>제한없음</span></label> 
               <label for="limit02"><input type="radio" name="limit" id="limit02" <%if(goods!=null && goods.isLimitFl()){ %> checked="checked" <%} %> value="true">
                <select name="limitOption" id="limitOption">
                    <option value="goods" <%if(goods==null || goods!=null && goods.getLimitOption().equals("goods")){ %> selected="selected" <%} %>>상품 기준</option>
                    <option value="goodsOption" <%if(goods!=null && goods.getLimitOption().equals("goodsOption")){%> selected="selected" <%} %> >옵션 기준</option>
                </select>
                <span class="small">최소구매수량</span>
                <input type="text" name="minOrderCnt" value=<%=(goods!=null)?goods.getMinOrderCnt():"" %>> <span class="small">개 / 최대구매수량</span>  <input type="text" name="maxOrderCnt" value=<%=(goods!=null)?goods.getMaxOrderCnt():"" %>><span class="small">개</span> 
                </label> 
            </div>           
        </div>
        <div class="wrap wrap04">
            <h2>가격설정</h2>
            <div class="cont cont04">
                <p><i class="fas fa-circle""></i>정가</p>
                <input type="text" name="goodsPrice" id="goodsPrice" onchange="calPercent()" value=<%=(goods!=null)?goods.getGoodsPrice():"" %>> <span>원</span> 
            </div>
            <div class="cont cont04">
                <p><i class="fas fa-circle"></i>판매가</p>
                <input type="text" name="fixedPrice" id="fixedPrice" onchange="calPercent()" value=<%=(goods!=null)?goods.getFixedPrice():""%>> <span>원</span> 
            </div>
            <div class="cont cont04">
                <p><i class="fas fa-circle"></i>매입가</p>
                <input type="text" name="costPrice" value=<%=(goods!=null)?goods.getCostPrice():"" %>> <span>원</span> 
            </div>
            <div class="cont cont04">
                <p><i class="fas fa-circle"></i>할인율</p>
                <input type="text" name="discountPercent" id="discountPercent" value="<%=(goods!=null)?goods.getDiscountPercent():"" %>" readonly> <span>%</span>
            </div>
        </div>
        <div class="wrap wrap05">
        	<div class="optionInfo">
	            <h2 class="optionTitle">옵션설정</h2>          
				<button type="button" class="optionBtn" onclick="optionMod(${g.goodsNo})">수정하기</button>
			</div>           
        </div>
        <div class="wrap wrap07">
            <h2>이용안내</h2>
            <div class="cont cont07 cont_01">
				<h3>배송안내</h3>
				<label for="not1"><input type="radio" name="del01" id="not1"><span>사용안함</span></label>
				<label for="self1"><input type="radio" name="del01" id="self1"><span>직접입력</span></label>
				<label for="auto1"><input type="radio" name="del01" id="auto1" checked="checked"><span>선택입력</span></label>
				<textarea class="summernote" name="detailInfoDelivery"><%=(goods!=null)?goods.getDetailInfoDelivery():"" %></textarea>
            </div>
            <div class="cont cont07 cont_01">
				<h3>AS안내</h3>
				<label for="not2"><input type="radio" name="del02" id="not2"><span>사용안함</span></label>
				<label for="self2"><input type="radio" name="del02" id="self2"><span>직접입력</span></label>
				<label for="auto2"><input type="radio" name="del02" id="auto2" checked="checked"><span>선택입력</span></label>
				<textarea class="summernote" name="detailInfoAS"><%=(goods!=null)?goods.getDetailInfoAS():"" %></textarea>
			</div>
			<div class="cont cont07 cont_01">
				<h3>환불안내</h3>
				<label for="not3"><input type="radio" name="del03" id="not3"><span>사용안함</span></label>
				<label for="self3"><input type="radio" name="del03" id="self3"><span>직접입력</span></label>
				<label for="auto3"><input type="radio" name="del03" id="auto3" checked="checked"><span>선택입력</span></label>
				<textarea class="summernote" name="detailInfoRefund"><%=(goods!=null)?goods.getDetailInfoRefund():"" %></textarea>
			</div>
			<div class="cont cont07 cont_01">
				<h3>교환안내</h3>
				<label for="not4"><input type="radio" name="del04" id="not4"><span>사용안함</span></label>
				<label for="self4"><input type="radio" name="del04" id="self4"><span>직접입력</span></label>
				<label for="auto4"><input type="radio" name="del04" id="auto4" checked="checked"><span>선택입력</span></label>
				<textarea class="summernote" name="detailInfoExchange"><%=(goods!=null)?goods.getDetailInfoExchange():"" %></textarea>
			</div>
        </div>
        </form>
        <button class="bottom" type="button" onclick="history.back()">취소</button>
       	<button class="bottom" type="button" onclick="save()">저장</button>
    </div>
	</div>
	<script src="${pageContext.request.contextPath }/admin/js/common/category.js"></script>
	<script>
		$('.summernote').summernote({
			height: 150,
			lang: "ko-KR",
		});
		<%if(cate1 == null){%>
		window.onload= function(){
		 	cateAction();
		}
		<%}%>
	</script>