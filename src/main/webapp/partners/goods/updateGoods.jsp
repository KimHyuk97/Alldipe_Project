<%@page import="dto.brandDTO.brandDTO"%>
<%@page import="dto.scmDTO.scmDTO"%>
<%@page import="dto.goodsDTO.goodsDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../header.jsp"/>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/partners/partners_p_en.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/partners/js/goods/updateGoods.js"></script>
<script src="${pageContext.request.contextPath}/partners/js/brand.js"></script>
<script src="${pageContext.request.contextPath}/partners/js/category.js"></script>

<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/partners/reset.css">
<%
	scmDTO scm = (scmDTO)session.getAttribute("scm");
	goodsDTO goods = (goodsDTO)session.getAttribute("goods");
	brandDTO brand = (brandDTO)session.getAttribute("brand");
	String goodsNo = request.getParameter("goods");
%>
	<div class="cont_wrap">
		<div id="wrap">
        <div class="top_title">
            <h1>상품수정</h1>
        </div>
        <form id="form" action="./updateGoods" method="post" enctype="multipart/form-data" onsubmit="return false">
        <input type="hidden" name="scmNo" value="<%=scm!=null?scm.getScmNo():null%>">
        <div class="wrap wrap01">
            <h2>상품분류</h2>
            <div class="cont cont01">
                <p><i class="fas fa-circle"></i>상품이름</p>
                <input type="text" name="goodsNm" placeholder="[브랜드명] 상품명" value="<%=(goods!=null)?goods.getGoodsNm():"" %>">
                <input type="hidden" name="goodsNo" value="<%=goodsNo %>">
            </div>
            <div class="cont cont01">
                <p><i class="fas fa-circle"></i>카테고리</p>
                <select id="cate1" onchange="cate2Action()">
                    <option>대분류</option>
                </select>
                <select id="cate2" onchange="cate3Action()">
                    <option>중분류</option>
                </select>
                <select id="cate3" onchange="onCategory()">
                    <option>소분류</option>
                </select>
            </div>
            <div class="cont cont01">
            	<input type="hidden" id="cateCd" name="cateCd" value=<%=(goods!=null)?goods.getCateCd():"" %>>
                <p><i class="fas fa-circle"></i>카테고리 수수료</p>
                <input type="text" id="commission" name="commission" value="<%=(goods!=null)?goods.getCommission():"" %>" readonly><span>%</span>
            </div>
            <div class="cont cont01">
                <p><i class="fas fa-circle"></i>검색 키워드</p>
                <input type="text" name="keyword" placeholder=",를 이용해 키워드 구분 (공백 사용X)" value=<%= (goods!=null)?goods.getKeyword():""%>>
            </div>
        </div>
        <div class="wrap wrap02">
            <h2>상품이미지</h2>
            <div class="cont cont02">
                <p><i class="fas fa-circle"></i>대표이미지</p>
                <input type="file" name="representImg" id="representImg" flies="<%=(goods!=null)?goods.getRepresentImg():""%>">
            </div>
            <div class="cont cont02">
                <p><i class="fas fa-circle"></i>개별이미지</p>
                <input type="file" name="subImg" multiple="multiple" files="<%=(goods!=null)?goods.getSubImg():""%>">
            </div>
            <div class="cont cont02 cont_01">
                <p><i class="fas fa-circle"></i>상세설명</p>
<!--                 <div class="ck_box"> -->
<!--                    <ul> -->
<!--                     <li>PC</li> -->
<!--                     <li>모바일</li> -->
<!--                     </ul> -->
<!--                     <label for="ck01"> -->
<!--                     <input type="checkbox" name="" id="ck01" checked="checked">  -->
<!--                     PC/모바일 상세설명 동일 사용  -->
<!--                     </label>   -->
<!--                 </div> -->
               
                <textarea class="summernote" name="goodsDescription"> <%=(goods!=null)? goods.getGoodsDescription() : "" %></textarea>
            </div>
        </div>
        <div class="wrap wrap03">
            <h2>상품필수정보</h2>
            <div class="cont cont03" style="position:relative;">
                <p><i class="fas fa-circle"></i>브랜드</p>
                <input type="text" id="brand" placeholder="브랜드명" onkeyup="brandSearch()" value=<%=(brand!=null)?brand.getBrandNm():"" %>>
                <div>
                	<ul id="brand_search">
                	</ul>
                </div>
                <input type="hidden" name="brandCd" id="brandCd" value=<%=(goods!=null)?goods.getBrandCd():"" %>>
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
               <label for="limit02"><input type="radio" name="limit" id="limit02" <%if(goods!=null && goods.isLimitFl()){ %> checked="checked" <%} %> vlaue="true">
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
               	<button type="button" class="optionBtn" onclick="window.open('${pageContext.request.contextPath }/admin/goods/goodsOptionMod?goodsNo=<%=goodsNo %>', '_blank', 'toolbar=yes,scrollbars=yes,resizable=yes,top=50,left=450,width=1700,height=800')">옵션 수정</button>
            </div>
        </div>
        <div class="wrap wrap06">
            <h2>배송지</h2>
            <div class="cont cont06 cont_01">
            	<p><i class="fas fa-circle"></i>출고지</p>
                <button type="button" onclick="window.open('${pageContext.request.contextPath}/partners/addressList?target=shipment','_blank', 'width=600,height=600')">검색</button>
				우편번호 <input type="text" name="shipmentZonecode" class="shipment" value="<%=(goods!=null)?goods.getShipmentZonecode():""%>" readonly>
				주소 <input type="text" name="shipmentAddress" class="shipment" value="<%=(goods!=null)?goods.getShipmentAddress():"" %>" readonly>
				상세 <input type="text" name="shipmentAddressSub" class="shipment" value="<%=(goods!=null)?goods.getShipmentAddressSub():"" %>" readonly>
            </div>
            <div class="cont cont06 cont_02">
                <p><i class="fas fa-circle"></i>반품배송지</p>
                <button type="button" onclick="window.open('${pageContext.request.contextPath}/partners/addressList?target=recovery','_blank', 'width=600,height=600')">검색</button>
				우편번호 <input type="text" name="recoveryZonecode" class="recovery" value="<%=(goods!=null)?goods.getRecoveryZonecode():"" %>" readonly>   
				주소 <input type="text" name="recoveryAddress" class="recovery" value="<%=(goods!=null)?goods.getRecoveryAddress():"" %>" readonly>
				상세 <input type="text" name="recoveryAddressSub" class="recovery" value="<%=(goods!=null)?goods.getRecoveryAddressSub():"" %>" readonly>
            </div>
            <div class="cont cont06 cont_03">
                <p><i class="fas fa-circle"></i>제주/도서산간 여부</p>
                <label for="deliveryAreaTrue"><input type="radio" name="deliveryAreaFl" id="deliveryAreaTrue" <%if(goods!=null && goods.getDeliveryArea() != null){%>checked="checked" <%} %>><span>가능</span></label>
                <label for="deliveryAreaFalse"><input type="radio" name="deliveryAreaFl" id="deliveryAreaFalse" <%if(goods!=null && goods.getDeliveryArea() != null){ %> checked="checked" <%} %> ><span>불가능</span></label>
            </div>
            <div class="cont cont06 cont_04">
                <p><i class="fas fa-circle"></i>배송비</p>
                <input type="text" name="deliveryCost" value=<%=(goods!=null)?goods.getDeliveryCost():"" %>> 원
                <p><i class="fas fa-circle"></i>교환,환불 배송비</p>
                <input type="text" name="deliveryRefundCost" value=<%=(goods!=null)?goods.getDeliveryRefundCost():"" %>> 원
            </div>
            <div class="cont cont06 cont_04">
                <p><i class="fas fa-circle"></i>제주 추가배송비</p>
                <input type="text" name="deliveryCostAddJeju" value=<%=(goods!=null)?goods.getDeliveryCostAddJeju():"" %>> 원
                <p><i class="fas fa-circle"></i>도서산간 추가배송비</p>
                <input type="text" name="deliveryCostAdd" value=<%=(goods!=null)?goods.getDeliveryCostAdd():"" %>> 원
            </div>
            <div class="cont cont06 cont_05">
                <p><i class="fas fa-circle"></i>택배사</p>
                <select name="deliveryCompany" id="deliveryCompany">
                    <option value="CJ대한통운">CJ대한통운</option>
                </select>
            </div>
            <div class="cont cont06 cont_06">
                <p><i class="fas fa-circle"></i>배송방법</p>
                <select name="deliveryWay" id="deliveryWay">
                    <option value="일반배송" <%if(goods==null || goods!=null && goods.getDeliveryWay().equals("일반배송")){ %> selected="selected" <%} %>>일반배송</option>
                    <option value="신선냉동" <%if(goods!=null && goods.getDeliveryWay().equals("신선냉동")){ %> selected="selected" <%} %>>신선냉동</option>
                    <option value="주문제작"<%if(goods!=null && goods.getDeliveryWay().equals("주문제작")){ %> selected="selected" <%} %>>주문제작</option>
                    <option value="구매대행"<%if(goods!=null && goods.getDeliveryWay().equals("구매대행")){ %> selected="selected" <%} %>>구매대행</option>
                </select>
                <span>배송비 종류</span>
                <select name="deliveryKind" id="deliveryKind">
                    <option value="무료배송"  <%if(goods==null || goods!=null && goods.getDeliveryWay().equals("무료배송")){ %> selected="selected" <%} %>>무료배송</option>
                    <option value="유료배송" <%if(goods!=null && goods.getDeliveryKind().equals("유료배송")){ %> selected="selected" <%} %>>유료배송</option>
                    <option value="조건부무료배송" <%if(goods!=null && goods.getDeliveryWay().equals("조건부무료배송")){ %> selected="selected" <%} %>>조건부무료배송</option>
                </select>
            </div>
            <div class="cont cont06 cont_07">
                <p><i class="fas fa-circle"></i>무료배송 조건</p>
                <input type="text" name="deliveryFreeCondition" value=<%=(goods!=null)?goods.getDeliveryFreeCondition():"" %>>
                <span>원 이상 무료배송</span>
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
        <button class="bottom" type="button" onclick="update()">수정하기</button>
    </div>
    <jsp:include page="../footer.jsp"></jsp:include>
	</div>
	<script>
		
		window.onload = function(){
			cate1Action();
			setCategory();
			endLoading();
			$('.summernote').summernote({
				height: 400,
				lang: "ko-KR",
				callbacks : {
					onImageUpload : function(files, editor, welEditable){
						sendFile(files[0], this);
					}
				}
			});
		}
		
		function sendFile(file, editor){
			data = new FormData();
			
			data.append("uploadFile", file);
			$.ajax({
				data : data,
				type : "POST",
				url : "../../summernote/uploadImg",
				cache : false,
				contentType : false,
				processData : false,
				success : function(data) {
					$(editor).summernote('editor.insertImage', "${pageContext.request.contextPath}/fileF/summernote/" + data.url);
				}
			});
		}
	</script>
</body>
</html>