function getStr(){
    var code = "mass-nq-fsdfasdfasfasddfasfadfasfasdfsffasfasdfaf-23423";
    while (code.length > 50){
        code = code.substring(code.indexOf("-") + 1)
    }
    console.log(code)
}
getStr();