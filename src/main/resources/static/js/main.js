/**
 * Created by amowel on 10.02.17.
 */

window.onload = ()=>{
    document.querySelector(".section-left").className+=" animated fadeOutLeft";
    document.querySelector(".section-right").className+=" animated fadeOutRight";


    window.setTimeout(()=>document.querySelector("#loader-wrapper").style.display = "none",200);

    $("button").click(()=>$("button,form+h1").animate({opacity:0},500,()=>{ $("button,form+h1").css("display","none");$("form,form h1").css("display","block");$("form").animate({opacity:1},600)}));
    $("input[type='submit']").click(()=>
    {
        if(!$("#usr").is(":checked")){
            $("form").attr("action","register");
        }

    })
};


