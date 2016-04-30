
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

    $("#enviar").click(function ( ev ) {
        ev.preventDefault();

        var $form = $("form#valores"),
            term = $("#datos").val(),
            url = $form.attr("action");

        $.post(url, {"datos": term}).done(function(data) {
            var obj = $.parseJSON(data);
            console.log(obj);
            var res = $("#resultados");
            res.append($("<div>").html($("<b>Rxy: </b>")).append(obj.rxy));
            res.append($("<div>").html($("<b>R<sup>2</sup>: </b>")).append(obj.R2));
            res.append($("<div>").html($("<b>Significancia: </b>")).append(obj.significancia));
            res.append($("<div>").html($("<b>B<sub>0</sub>: </b>")).append(obj.B0));
            res.append($("<div>").html($("<b>B<sub>1</sub>: </b>")).append(obj.B1));
            res.append($("<div>").html($("<b>Y<sub>k</sub>: </b>")).append(obj.yk));
            res.append($("<div>").html($("<b>Rango </b>")).append(obj.rango));
            res.append($("<div>").html($("<b>UPI (70%)-: </b>")).append(obj.yk + obj.rango));
            res.append($("<div>").html($("<b>LPI (70%): </b>")).append(obj.yk - obj.rango));
        })
    });
})
