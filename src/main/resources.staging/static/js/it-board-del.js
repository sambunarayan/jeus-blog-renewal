function clickClose() {
    $(this).find("#card_div").addClass("bg-light");
    $(this).find("#continue").removeClass("stretched-link");
    $(this).find("#closeBtn").removeClass("invisible");
}
$('div').mouseover(function() {
//    $(this).find("#card_div").addClass("bg-light");
//    $(this).find("#continue").removeClass("stretched-link");
//    $(this).find("#closeBtn").removeClass("invisible");
//    if ($("#continue").classList.contains('stretched-link') == true) {
//        console.log('マウスオーバーしました！');
//        $("#continue").removeClass("stretched-link");
//    }
}).mouseout(function() {
//    $(this).find("#card_div").removeClass("bg-light");
//    $(this).find("#continue").addClass("stretched-link");
//    $(this).find("#closeBtn").addClass("invisible");
//    if ($("#continue").classList.contains('stretched-link') == false) {
//        $("#continue").addClass("stretched-link");
//        console.log('マウスオーバーしました！');
//    }
});
