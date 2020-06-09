$(function () {
    $(".video-open").click(function () {
        var theModal = $(this).data("target"),
                videoSRC = $(this).attr("data-video"),
                videoSRCauto = videoSRC + "";
        $(theModal + ' source').attr('src', videoSRCauto);
        $(theModal + ' video').load();
        $(theModal + ' button.close').click(function () {
            $(theModal + ' source').attr('src', videoSRC);
        });
    });
});


