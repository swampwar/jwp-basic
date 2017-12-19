// $(".qna-comment").on("click", ".answerWrite input[type=submit]", addAnswer);
var deletedArticle;
$(".answerWrite input[type=submit]").click(addAnswer);
$(".link-delete-article").click(delAnswer);

function delAnswer(event){
	event.preventDefault();
	
	// 답변의 삭제버튼 클릭시 가장 가까운 form을 serialize 한다.
	var queryString = $(this).closest('form').serialize();
	deletedArticle = $(this).closest('article');
	
    $.ajax({
      type : 'post',
      url : '/api/qna/deleteAnswer',
      data : queryString,
      dataType : 'json',
      error: onError,
      success : onDelAnswerSuccess,
    });
	
}

function onDelAnswerSuccess(json, status){
	if(json.result.status){
		alert('삭제성공');
		deletedArticle.remove();
		var cnt = $('.qna-comment-count strong').text();
		$('.qna-comment-count strong').text(cnt-1);
	}
}

function addAnswer(e) {
  e.preventDefault();

  var queryString = $("form[name=answer]").serialize();

  $.ajax({
    type : 'post',
    url : '/api/qna/addAnswer',
    data : queryString,
    dataType : 'json',
    error: onError,
    success : onSuccess,
  });
}

function listQuestionTest(e){
	  e.preventDefault();

	  var queryString = $("form[name=answer]").serialize();

	  $.ajax({
	    type : 'post',
	    url : '/api/qna/list',
	    data : queryString,
	    dataType : 'json',
	    error: onError,
	    success : function(json, status){
	    	console.log('질문목록 조회완료');
	    	console.log(json.qList);
	    },
	  });
}

function onSuccess(json, status){
  var answer = json.answer;
  var countOfComment = json.countOfComment;
  $(".qna-comment-count strong").text(countOfComment);
  var answerTemplate = $("#answerTemplate").html();
  var template = answerTemplate.format(answer.writer, new Date(answer.createdDate), answer.contents, answer.answerId, answer.answerId);
  $(".qna-comment-slipp-articles").prepend(template);
}

function onError(xhr, status) {
  alert("error");
}

String.prototype.format = function() {
  var args = arguments;
  return this.replace(/{(\d+)}/g, function(match, number) {
    return typeof args[number] != 'undefined'
        ? args[number]
        : match
        ;
  });
};