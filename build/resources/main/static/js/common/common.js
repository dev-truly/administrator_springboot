/**
 *
 * @param{action domain} url
 * @param{success function} sucFunc
 * @param{error function} errorFunc
 * @param{request param} param
 * @param{form method:default=POST} method
 */
var ajaxProcess = function (url, sucFunc, errorFunc, param = "", method = 'POST') {
    //생성된 json 객체를 설정되어 있는 서버에 저장 -----------------------------
    $.ajax({
        type : method,url : url
        ,data : param//넘길 파라메타 값
        ,success: sucFunc
        ,error: errorFunc
    });
}