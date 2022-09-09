$(document).ready(function() {
    var lastIdx = 999999999;
    init();
    showLatestPostList();
    function init() {
        console.log("init()");
        addLatestPostList($("#last_post_id").val());
    }
    function showLatestPostList() {
        $(window).scroll(function() {
            if ($(window).scrollTop() >= $(document).height() - $(window).height() - ($('#footer').height() / 15)) {
               let currIdx = $("#last_post_id").val();

               if (lastIdx != currIdx) {
                  lastIdx = currIdx;
                  addLatestPostList(currIdx);
               }
            }
        });
    }
    /**
     * Show post list
     */
    function addLatestPostList(lastIdx) {
        // ajax call get data from server and append to the div
        $.ajax({
            type: 'GET',
            url: '/blog-rest/time-line/latest/'+lastIdx,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
        }).done(function(json) {
            var body = "";
            json.forEach(function(val, idx) {
                body += "<div class='row g-0 border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative'>";
                body += "    <div class='col p-4 d-flex flex-column position-static'>";
                body += "        <strong class='d-inline-block mb-2 " + val.boardNameColor + "'>" + val.boardName + "</strong>";
                body += "        <h3 class='mb-0'>" + val.title + "</h3>";
                body += "        <div class='mb-1 text-muted'>" + val.createdDate+ "</div>";
                body += "        <p class='card-text mb-auto'>" + val.content + "</p>";
                body += "        <a href='/blog/it-bulletin/post/list/" + val.boardName + "?bno=" + val.id + "' class='stretched-link'>Continue";
                body += "        reading</a>";
                body += "    </div>";
                body += "</div>";
                $('#last_post_id').val(val.id);
            });
            if (body != "") {
                $('#timeline').append(body);
            }
        }).fail(function (error) {
            alert("error ->" + JSON.stringify(error));
        });
    }
});
