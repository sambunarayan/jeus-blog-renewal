$(document).ready(function(){
    refreshPost();
    list();
    function refreshPost() {
        var content = document.getElementById("content");
        if (content != null) {
            var converted = "";
            var text = content.textContent.replaceAll(/\r?\n/g,'<br>');
            var inHtmlTag = false;
            for (var i = 0; i < text.length; i++) {
                if (!inHtmlTag && text[i] == ' ') {
                    converted += '&nbsp;';
                } else {
                    if (text[i] == '<') {
                        inHtmlTag = true;
                    } else if (text[i] == '>') {
                        inHtmlTag = false;
                    }
                    converted += text[i];
                }
            }
            $("#content").html(converted);
        }
    }
    function list() {

        var regForm = $("#hidden_form");
        showPostList(regForm.find('#boardName').val(),
            regForm.find('#page').val(), regForm.find('#bno').val());
    }
    /**
     * Show post list
     */
    function showPostList(boardName, page, currId) {
        $.ajax({
            type: 'GET',
            url: '/blog-rest/it-bulletin/' + boardName +'/page/'+page,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
        }).done(function(json) {
            var tableBody = $("#tbody");
            var body = "";
            var no = json.totalPostNum - ((page - 1) * 10);
            json.posts.forEach(function(val, idx) {
                let active = "";
                if (currId == val.id) {
                    active = "table-active";
                }
                body += "<tr class='"+ active +"'>";
                body += "<td>"+ no-- +"</td>";
                body += "<td><a id='"+ val.id +"' href='/blog/it-bulletin/post/list/" + val.boardName
                        + "?bno="+ val.id +"&page=" + page + "'>"+ val.title +"</a></td>";
                body += "<td>"+ val.author +"</td>";
                body += "<td>"+ val.createdDate +"</td>";
                body += "</tr>";
            });
            tableBody.html(body);
            showPageList(boardName, json.totalPostNum, page);
        }).fail(function (error) {
            alert("error ->" + JSON.stringify(error));
        });
    }

    function showPageList(boardName, totalNum, currPageNum) {
        var endNum = Math.ceil(totalNum / 10.0) * 10;
        var next = false;
        if (endNum * 10 >= totalNum) {
            endNum = Math.ceil(totalNum / 10.0);
        }
        if (endNum * 10 < totalNum) {
            next = true;
        }
        var startNum = endNum - 9;
        if (startNum < 1) {
            startNum = 1;
        }
        var prev = startNum >= 10;
        var page = "<ul class='pagination pull-right'>";
        if (prev) {
            page += "<li class='page-item'><a class='page-link' href='/blog/it-bulletin/post/list/" + boardName + "?page=" + (startNum - 1) + "'>Previous</a></li>";
        }
        for (var i = startNum; i <= endNum; i++) {
            var active = currPageNum == i? "active":"";
            page+="<li class='page-item " + active +" '><a class='page-link' href='/blog/it-bulletin/post/list/" + boardName + "?page=" + i + "'>" + i + "</a></li>";
        }
        if (next) {
            page+="<li class='page-item'><a class='page-link' href='/blog/it-bulletin/post/list/" + boardName + "?page=" + (endNum + 1) + "'>Next</a></li>";
        }
        page += "</ul></div>";
        $("#pageDiv").html(page);
    }
});

var main = {
    init : function() {
        var _this = this;
        $('#modal-btn-delete').on('click', function() {
            _this.delete();
        });
    },
    delete : function() {
        var id = $('#bno').val();
        var boardName = $('#boardName').val();
        var page = $('#page').val();

        $.ajax({
            type: 'DELETE',
            url: '/blog-rest/it-bulletin/' + boardName + "/delete/"+ id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8'
        }).done(function() {
            window.location.href='/blog/it-bulletin/post/list/' + boardName + '?page=' + page;
        }).fail(function(error) {
            alert(JSON.stringify(error));
        });
    }
}
main.init();