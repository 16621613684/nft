function addCart(goodsId){
    let strings = window.location.href.split("/");
    let mohu=window.location.href.split("=");
    console.log(strings[4]);
    console.log(strings[3]);
    console.log(mohu[1]);
    if (mohu[1]==null)
        mohu[1]="";

    if (strings[4]!=null){
    window.location.href='cart/'+goodsId+'/'+strings[4];}


    if (mohu[1]!=null&&mohu[1]!=""){

        window.location.href= 'mohu/'+goodsId+'/'+mohu[1];}

    if (strings[4]==null&&strings[3]!=null&&(mohu[1]==null|mohu[1]==""))
    window.location.href='sort/'+goodsId+'/'+strings[3];
}
