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
		<div class="panel panel-default">
			<header class="qna-header">
				<h2 class="qna-title">${question.title}</h2>
			</header>
			<div class="content-main">
				<article class="article">
					<div class="article-header">
						<div class="article-header-thumb">
							<img src="https://graph.facebook.com/v2.3/100000059371774/picture" class="article-author-thumb" alt="">
						</div>
						<div class="article-header-text">
							<a href="/users/92/kimmunsu" class="article-author-name">${question.writer}</a>
							<a href="/questions/413" class="article-header-time" title="퍼머링크">
								<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${question.createdDate}" />
								<i class="icon-link"></i>
							</a>
						</div>
					</div>
					<div class="article-doc">
						${question.contents}
					</div>
					<div class="article-util">
						<ul class="article-util-list">
							<li>
								<a class="link-modify-article" href="#">수정</a>
							</li>
							<li>
								<form class="form-delete" action="#" method="POST">
									<input type="hidden" name="_method" value="DELETE">
									<button class="link-delete-article" type="submit">삭제</button>
								</form>
							</li>
							<li>
								<a class="link-modify-article" href="/">목록</a>
							</li>
						</ul>
					</div>
				</article>

				<div class="qna-comment">
					<div class="qna-comment-slipp">
						<p class="qna-comment-count"><strong>${question.countOfComment}</strong>개의 의견</p>
						<div class="qna-comment-slipp-articles">

							<c:forEach items="${answers}" var="each">
							<article class="article">
								<div class="article-header">
									<div class="article-header-thumb">
										<img src="https://graph.facebook.com/v2.3/1324855987/picture" class="article-author-thumb" alt="">
									</div>
									<div class="article-header-text">
										${each.writer}
										<div class="article-header-time"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${each.createdDate}" /></div>
									</div>
								</div>
								<div class="article-doc comment-doc">
									<p>${each.contents}</p>
								</div>
								<div class="article-util">
									<ul class="article-util-list">
										<li>
											<a class="link-modify-article" href="/api/qna/updateAnswer?answerId=${each.answerId}">수정</a>
										</li>
										<li>
											<form class="form-delete" action="/api/qna/deleteAnswer" method="POST">
												<input type="hidden" name="answerId" value="${each.answerId}" />
												<button type="submit" class="link-delete-article">삭제</button>
											</form>
										</li>
									</ul>
								</div>
							</article>
                            </c:forEach>
							<div class="answerWrite">
								<form name="answer" method="post">
									<input type="hidden" name="questionId" value="${question.questionId}">
									<div class="form-group col-lg-4" style="padding-top:10px;">
										<input class="form-control" id="writer" name="writer" placeholder="이름">
									</div>
									<div class="form-group col-lg-12">
										<textarea name="contents" id="contents" class="form-control" placeholder=""></textarea>
									</div>
									<input class="btn btn-success pull-right" type="submit" value="답변하기" />
									<div class="clearfix" />
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<script type="text/template" id="answerTemplate">
	<article class="article">
		<div class="article-header">
			<div class="article-header-thumb">
				<img src="https://graph.facebook.com/v2.3/1324855987/picture" class="article-author-thumb" alt="">
			</div>
			<div class="article-header-text">
				{0}
				<div class="article-header-time">{1}</div>
			</div>
		</div>
		<div class="article-doc comment-doc">
			{2}
		</div>
		<div class="article-util">
			<ul class="article-util-list">
				<li>
					<a class="link-modify-article" href="/api/qna/updateAnswer/{3}">수정</a>
				</li>
				<li>
					<form class="form-delete" action="/api/qna/deleteAnswer" method="POST">
						<input type="hidden" name="answerId" value="{4}" />
						<button type="submit" class="link-delete-article">삭제</button>
					</form>
				</li>
			</ul>
		</div>
	</article>
</script>

<%@ include file="/include/footer.jspf" %>

<script type="text/javascript">
$(".answerWrite input[type=submit]").click(addAnswer);
$(".article-util button[type=submit]").click(deleteAnswer);

function addAnswer(e){
	e.preventDefault();
	var queryString = $("form[name='answer']").serialize();
	console.log(queryString);
	
	$.ajax({
		type : 'post',
		url : '/api/qna/addAnswer',
		data : queryString,
		dataType : 'json',
		error : onError,
		success : onSuccess
	});
}

function onSuccess(json, status){
	var answerTemplate = $('#answerTemplate').html();
	var template = answerTemplate.format(json.writer, new Date(json.createdDate), json.contents, json.answerId, json.answerId);
	$('.qna-comment-slipp-articles').prepend(template);
	
	// 기존 입력란 비움
	$('#writer').val('');
	$('#contents').val('');
}

function onError(){
	
}

function deleteAnswer(e){
	e.preventDefault();
	var button = $(this);
	var form = $(this).closest('form');
	var queryString = form.serialize();
	console.log(queryString);
	
	$.ajax({
		type : 'post',
		url : '/api/qna/deleteAnswer',   
		data : queryString,
		dataType : 'json',
        async: false,
		error : function(json, status){
			alert(json.message);
		},
		success : function(json, status){
			button.closest('article').remove();
		}
	});
}




</script>
</body>
</html>