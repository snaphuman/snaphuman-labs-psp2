
$(document).ready(function () {

    var inNumSeg = $('input#num_seg');
    var inDof = $('input#dof');
    var inX = $('input#x');
    var inError = $('input#error');

    $("#test1").click(function (){
    console.log("Valores default test 1");
    inNumSeg.val(10);
    inDof.val(9);
    inX.val(1.1);
    inError.val(0.00001);
    });

    $("#test2").click(function (){
    console.log("Valores default test2");
    inNumSeg.val(10);
    inDof.val(10);
    inX.val(1.1812);
    inError.val(0.00001);
    });

    $("#test3").click(function (){
    console.log("Valores default test3");
    inNumSeg.val(10);
    inDof.val(30);
    inX.val(2.750);
    inError.val(0.00001);
    });

    $("#lab6 #test1").click(function () {
        $('input#trialX').val(1.0);
        $('input#p').val(0.20);
        $('input#dof').val(6);
    });

    $("#lab6 #test2").click(function () {
        $('input#trialX').val(1.0);
        $('input#p').val(0.45);
        $('input#dof').val(15);
    });

    $("#lab6 #test3").click(function () {
        $('input#trialX').val(1.0);
        $('input#p').val(0.495);
        $('input#dof').val(4);
    });

    $("#enviar").click(function () {
        var values = $("form#valores").serialize();
        console.log(values);
        $.ajax({
            url: "/regla-simpson/calcular",
            type: "post",
            data: values,
            type: "text",
            success: function(response) {
                alert("responde");
            }
        }).done(function (msg) {

                alert("mensaje");
        })
    });
})
