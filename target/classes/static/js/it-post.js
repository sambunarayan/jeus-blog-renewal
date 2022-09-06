function onChange(evt) {
    var files = evt.target.files;
    var data = new FormData();
    data.append("file", files[0]);
    $.ajax({
        type: 'POST',
        url: '/blog-rest/image/upload',
        processData: false,
        contentType: false,
        cache: false,
        timeout: 600000,
        data: data,
    }).done(function(data) {
        const content = document.getElementById('content');
        console.log("success");
        console.log(data);
        content.value += "<img src='/blog/image/load?name=" + data.imageName +"'/>";
    }).fail(function (error) {
        console.log(error);
        alert(error.responseJSON.detail);
    });
}
document.getElementById("uploadFile").addEventListener("change", onChange, false);