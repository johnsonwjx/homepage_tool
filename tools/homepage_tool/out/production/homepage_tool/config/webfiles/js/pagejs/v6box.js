var tips;

var theTop = 145;

var old = theTop;

function initFloatTips() {

    tips = document.getElementById("v6box");

    moveTips();

};

function moveTips() {

    var tt = 50;

    if (window.innerHeight) {

        pos = window.pageYOffset;

    } else if (document.documentElement && document.documentElement.scrollTop) {

        pos = document.documentElement.scrollTop;

    } else if (document.body) {

        pos = document.body.scrollTop;

    }

    pos = pos - tips.offsetTop + theTop;

    pos = tips.offsetTop + pos / 10;

    if (pos < theTop) pos = theTop;

    if (pos != old) {

        tips.style.top = pos + "px";

        tt = 2;

    }

    old = pos;

    setTimeout(moveTips, tt);

}

