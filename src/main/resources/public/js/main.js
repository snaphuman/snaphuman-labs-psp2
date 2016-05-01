
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

    $("#historico #test1").click(function () {

        var datos = "130.0,186.0;"
            datos += "650.0,699.0;"
            datos += "99.0,132.0;"
            datos += "150.0,272.0;"
            datos += "128.0,291.0;"
            datos += "302.0,331.0;"
            datos += "95.0,199.0;"
            datos += "945.0,1890.0;"
            datos += "368.0,788.0;"
            datos += "961.0,1601.0;"

        $("#datos").val(datos);
    });

    $("#historico #test2").click(function () {

        var datos = "130.0,15.0;"
            datos += "650.0,69.9;"
            datos += "99.0,6.5;"
            datos += "150.0,22.4;"
            datos += "128.0,28.4;"
            datos += "302.0,65.9;"
            datos += "95.0,19.4;"
            datos += "945.0,198.7;"
            datos += "368.0,38.8;"
            datos += "961.0,138.2;"

        $("#datos").val(datos);
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
            res.append($("<hr>"));
        })
    });
})
