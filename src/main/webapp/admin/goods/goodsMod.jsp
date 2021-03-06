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
            <h1>????????????</h1>
        </div>
        <form id="form" action="./goodsModProcess" method="post" enctype="multipart/form-data">
        <input type="hidden" name="goodsNo" value="${g.goodsNo}">
        <input type="hidden" name="page" value="${page}"> 
        
        <div class="top_botton">
        	<button class="bottom" type="button" onclick="history.back()">??????</button>
        	<button class="bottom" type="button" onclick="save()">??????</button>
        </div>
        <div class="wrap wrap01">
            <h2>????????????</h2>
            <div class="cont cont01">
                <p><i class="fas fa-circle"></i>????????????</p>
                <input type="text" name="goodsNm" placeholder="[????????????] ?????????" value="${g.goodsNm }">
            </div>
            <div class="cont cont01">
                <p><i class="fas fa-circle"></i>????????????</p>
	            <select name="cate" id="large" <%if(cate1 != null && cate1.getCateCd() != null){%> onclick="cateAction()" <%}%>  onchange="cateCheck()">
                   <option value=<%=(cate1 != null && cate1.getCateCd() != null)? cate1.getCateCd() : ""%>><%=(cate1 != null && cate1.getCateNm() != null)? cate1.getCateNm() : "?????????"%></option>
                </select>
                <select name="cate2" id="middle" <%if(cate1 != null && cate1.getCateCd() != null){%> onclick="cateMiddle('<%=cate1.getCateCd() %>')" <%}%>  onchange="cateCheck2()">
                   <option value=<%=(cate2 != null && cate2.getCateCd() != null)? cate2.getCateCd() : ""%>><%=(cate2 != null && cate2.getCateNm() != null)? cate2.getCateNm() : "?????????"%></option>
                </select>
                <select name="cate3" id="small" <%if(cate2 != null && cate2.getCateCd() != null){%> onclick="cateSmall('<%=cate2.getCateCd() %>')" <%}%> >
                   <option value=<%=(cate3 != null && cate3.getCateCd() != null)? cate3.getCateCd() : ""%>><%=(cate3 != null && cate3.getCateNm() != null)? cate3.getCateNm() : "?????????"%></option>
                </select>
            </div>
            <div class="cont cont01">
            	<input type="hidden" id="cateCd" name="cateCd" value=<%=(goods!=null && goods.getCateCd() != null)?goods.getCateCd():"" %>>
                <p><i class="fas fa-circle"></i>???????????? ?????????</p>
                <input type="text" id="commission" name="commission" value="${c3.commission}" readonly><span>%</span>
            </div>
            <div class="cont cont01">
                <p><i class="fas fa-circle"></i>?????? ?????????</p>
                <input type="text" name="keyword" placeholder=",??? ????????? ????????? ?????? (?????? ??????X)" value=<%= (goods!=null && goods.getKeyword() != null)?goods.getKeyword():""%>>
            </div>
        </div>
        <div class="wrap wrap02">
            <h2>???????????????</h2>
            <div class="cont cont02">
                <p><i class="fas fa-circle"></i>???????????????</p>
                <input type="file" name="representImg" id="representImg">
            </div>
            <div class="cont cont02">
                <p><i class="fas fa-circle"></i>???????????????</p>
                <input type="file" name="subImg" multiple="multiple">
            </div>
            <div class="cont cont02 cont_01">
                <p><i class="fas fa-circle"></i>????????????</p>               
                <textarea class="summernote" name="goodsDescription"> <%=(goods!=null)? goods.getGoodsDescription() : "" %></textarea>
            </div>
        </div>
        <div class="wrap wrap03">
            <h2>??????????????????</h2>
            <div class="cont cont03"  style="position:relative;">
                <p><i class="fas fa-circle"></i>?????????</p>
                <input type="text" id="brand" placeholder="????????????" value="${b.brandNm}" onkeyup="brandSearch()">    
               	<div id="brand_search">
               	</div>
                <input type="hidden" name="brandCd" id="brandCd" value="${b.brandCd}">
            </div>
            <div class="cont cont03">
                <p><i class="fas fa-circle"></i>?????????</p>
                <input type="text" name="makerNm" placeholder="?????????" value=<%=(goods!=null)?goods.getMakerNm():"" %>>
            </div>
            <div class="cont cont03">
                <p><i class="fas fa-circle"></i>?????????</p>
                <input type="text" name="originNm" placeholder="?????????" value=<%=(goods!=null)?goods.getOriginNm():"" %>>
            </div>
            <div class="cont cont03">
                <p><i class="fas fa-circle"></i>??????????????????</p>
                <label for="adult01"><input type="radio" name="adultFl" id="adult01" value="true" <%if(goods!=null && goods.isOnlyAdultFl()){%> checked="checked"  <%} %>><span>??????</span> </label>
                <label for="adult02"><input type="radio" name="adultFl" id="adult02" value="false" <%if(goods==null || !goods.isOnlyAdultFl()){%> checked="checked" <%} %>><span>?????????</span> </label>
            </div>
            <div class="cont cont03">
                <p><i class="fas fa-circle"></i>??????????????????</p>
                <label for="goodsSellFl01"><input type="radio" name="goodsSellFl"  value="0" <%if(goods.getGoodsSellFl() == 0){ %> checked = "checked"<%} %> ><span>????????????</span> </label>
                <label for="goodsSellFl02"><input type="radio" name="goodsSellFl"  value="1" <%if(goods.getGoodsSellFl() == 1){ %> checked = "checked"<%} %>><span>?????????</span> </label>
                <label for="goodsSellFl03"><input type="radio" name="goodsSellFl"  value="-1"<%if(goods.getGoodsSellFl() == -1){ %> checked = "checked"<%} %>><span>?????????????????????</span> </label>
            </div>
            <div class="cont cont03">
                <p><i class="fas fa-circle"></i>??????????????????</p>
                <label for="memberOnly01"><input type="radio" name="memberOnly" value="true" <%if(goods.isMemberOnly()){ %>checked = "checked"<%} %>><span>????????????</span> </label>
                <label for="memberOnly02"><input type="radio" name="memberOnly" value="false"<%if(!goods.isMemberOnly()){ %>checked = "checked"<%} %>><span>??????(??????+?????????)</span> </label>
            </div>
            <div class="cont cont03 cont_03">
                <p><i class="fas fa-circle"></i>??????????????????</p>
                <label for="tax01"><input type="radio" name="tax" id="tax01"<%if(goods==null || goods!=null && goods.getTaxFreeFl().equals("t")){%> checked="checked"  <%} %> value="t"><span>??????</span></label>
                <label for="tax02"><input type="radio" name="tax" id="tax02"<%if(goods!=null && goods.getTaxFreeFl().equals("n")){%> checked="checked"  <%} %> value="n"><span>?????????</span></label>
                <label for="tax03"><input type="radio" name="tax" id="tax03"<%if(goods!=null && goods.getTaxFreeFl().equals("f")){%> checked="checked"  <%} %> value="f"><span>??????</span></label>
                <input type="text" name="taxPercent" value=<%=(goods!=null)?goods.getTaxPercent():"10" %>>%
            </div>
            <div class="cont cont03 cont_04">
                <p><i class="fas fa-circle"></i>????????????/??????</p>
                <input type="text" name="goodsWeight" value=<%=(goods!=null)?goods.getGoodsWeight():"" %>><label for="goodsWeight"><span>g</span></label>
            </div>
            <div class="cont cont03 cont_05">
                <p><i class="fas fa-circle"></i>???????????? ??????</p>
                <select name="fixedSales">
                    <option value="option">?????? ??????</option>
                    <option value="goods">?????? ??????</option>
                </select>
                <input type="text" name="salesUnit" id="salesUnit" value="1"><label for="salesUnit"><span>??? ????????? ?????? ??? ??????????????? ??????</span></label>
            </div>
            <div class="cont cont03 cont_06">
                <p><i class="fas fa-circle"></i>???????????? ??????</p>
               <label for="limit01"><input type="radio" name="limit" id="limit01" <%if(goods==null || goods!=null && !goods.isLimitFl()){ %> checked="checked" <%} %> value="false"><span>????????????</span></label> 
               <label for="limit02"><input type="radio" name="limit" id="limit02" <%if(goods!=null && goods.isLimitFl()){ %> checked="checked" <%} %> value="true">
                <select name="limitOption" id="limitOption">
                    <option value="goods" <%if(goods==null || goods!=null && goods.getLimitOption().equals("goods")){ %> selected="selected" <%} %>>?????? ??????</option>
                    <option value="goodsOption" <%if(goods!=null && goods.getLimitOption().equals("goodsOption")){%> selected="selected" <%} %> >?????? ??????</option>
                </select>
                <span class="small">??????????????????</span>
                <input type="text" name="minOrderCnt" value=<%=(goods!=null)?goods.getMinOrderCnt():"" %>> <span class="small">??? / ??????????????????</span>  <input type="text" name="maxOrderCnt" value=<%=(goods!=null)?goods.getMaxOrderCnt():"" %>><span class="small">???</span> 
                </label> 
            </div>           
        </div>
        <div class="wrap wrap04">
            <h2>????????????</h2>
            <div class="cont cont04">
                <p><i class="fas fa-circle""></i>??????</p>
                <input type="text" name="goodsPrice" id="goodsPrice" onchange="calPercent()" value=<%=(goods!=null)?goods.getGoodsPrice():"" %>> <span>???</span> 
            </div>
            <div class="cont cont04">
                <p><i class="fas fa-circle"></i>?????????</p>
                <input type="text" name="fixedPrice" id="fixedPrice" onchange="calPercent()" value=<%=(goods!=null)?goods.getFixedPrice():""%>> <span>???</span> 
            </div>
            <div class="cont cont04">
                <p><i class="fas fa-circle"></i>?????????</p>
                <input type="text" name="costPrice" value=<%=(goods!=null)?goods.getCostPrice():"" %>> <span>???</span> 
            </div>
            <div class="cont cont04">
                <p><i class="fas fa-circle"></i>?????????</p>
                <input type="text" name="discountPercent" id="discountPercent" value="<%=(goods!=null)?goods.getDiscountPercent():"" %>" readonly> <span>%</span>
            </div>
        </div>
        <div class="wrap wrap05">
        	<div class="optionInfo">
	            <h2 class="optionTitle">????????????</h2>          
				<button type="button" class="optionBtn" onclick="optionMod(${g.goodsNo})">????????????</button>
			</div>           
        </div>
        <div class="wrap wrap07">
            <h2>????????????</h2>
            <div class="cont cont07 cont_01">
				<h3>????????????</h3>
				<label for="not1"><input type="radio" name="del01" id="not1"><span>????????????</span></label>
				<label for="self1"><input type="radio" name="del01" id="self1"><span>????????????</span></label>
				<label for="auto1"><input type="radio" name="del01" id="auto1" checked="checked"><span>????????????</span></label>
				<textarea class="summernote" name="detailInfoDelivery"><%=(goods!=null)?goods.getDetailInfoDelivery():"" %></textarea>
            </div>
            <div class="cont cont07 cont_01">
				<h3>AS??????</h3>
				<label for="not2"><input type="radio" name="del02" id="not2"><span>????????????</span></label>
				<label for="self2"><input type="radio" name="del02" id="self2"><span>????????????</span></label>
				<label for="auto2"><input type="radio" name="del02" id="auto2" checked="checked"><span>????????????</span></label>
				<textarea class="summernote" name="detailInfoAS"><%=(goods!=null)?goods.getDetailInfoAS():"" %></textarea>
			</div>
			<div class="cont cont07 cont_01">
				<h3>????????????</h3>
				<label for="not3"><input type="radio" name="del03" id="not3"><span>????????????</span></label>
				<label for="self3"><input type="radio" name="del03" id="self3"><span>????????????</span></label>
				<label for="auto3"><input type="radio" name="del03" id="auto3" checked="checked"><span>????????????</span></label>
				<textarea class="summernote" name="detailInfoRefund"><%=(goods!=null)?goods.getDetailInfoRefund():"" %></textarea>
			</div>
			<div class="cont cont07 cont_01">
				<h3>????????????</h3>
				<label for="not4"><input type="radio" name="del04" id="not4"><span>????????????</span></label>
				<label for="self4"><input type="radio" name="del04" id="self4"><span>????????????</span></label>
				<label for="auto4"><input type="radio" name="del04" id="auto4" checked="checked"><span>????????????</span></label>
				<textarea class="summernote" name="detailInfoExchange"><%=(goods!=null)?goods.getDetailInfoExchange():"" %></textarea>
			</div>
        </div>
        </form>
        <button class="bottom" type="button" onclick="history.back()">??????</button>
       	<button class="bottom" type="button" onclick="save()">??????</button>
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