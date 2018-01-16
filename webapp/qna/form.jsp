<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="kr">
<head>
	<%@ include file="/include/header.jspf" %>
</head>
<body>
<%@ include file="/include/navigation.jspf" %>

<div class="container" id="main">
   <div class="col-md-12 col-sm-12 col-lg-10 col-lg-offset-1">
      <div class="panel panel-default content-main">
          <form name="question" method="post" action="/qna/create">
              <div class="form-group">
                  <label for="writer">글쓴이</label>
                  <input class="form-control" id="writer" name="writer" placeholder="글쓴이" value="${question.writer }"/>
              </div>
              <div class="form-group">
                  <label for="title">제목</label>
                  <input type="text" class="form-control" id="title" name="title" placeholder="제목" value="${question.title }"/>
              </div>
              <div class="form-group">
                  <label for="contents">내용</label>
                  <textarea name="contents" id="contents" rows="5" class="form-control">${question.contents }</textarea>
              </div>
              <button type="submit" class="btn btn-success clearfix pull-right">질문하기</button>
              <div class="clearfix" />
          </form>
        </div>
    </div>
</div>

<%@ include file="/include/footer.jspf" %>
<script type="text/javascript">
$('form[name=question] button[type=submit]').click(createQuestion);

function createQuestion(e){
	e.preventDefault();
	
	var queryString = $("form[name=question]").serialize();
	 
	$.ajax({
	  type : 'post',
	  url : '/qna/create',
	  data : queryString,
	  dataType : 'json',
	  error: onError,
	  success : onSuccess,
	});
}

function onSuccess(json, status){
	if(true == json.insertRslt){
		// 등록 성공시 페이지 이동
		$('form[name=question]').attr('action','/');
		$('form[name=question]').submit();
	}
}

//jqXHR jqXHR, String textStatus, String errorThrown
function onError(xhr, status, errorThrown) {
	// TODO 세션에 user 속성이 없으면 AccessDeniedException 발생시켰으나 ServletException 으로 던짐
	// 미로그인으로 인한 예외인지 알지 못하고 처리하고 있음.
	alert('로그인이 필요합니다.');
}

</script>
</body>
</html>