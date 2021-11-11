var urlBaseReservacion = "/api/Reservation";
var urlBaseComputer = "/api/Computer";
var urlBaseClient = "/api/Client";

var getAll = function () {
    $.ajax({
        url: urlBaseReservacion + "/all",
        type: 'GET',
        dataType: 'json',
        success: function (respuesta) {
            console.log(respuesta);
            updateTable(respuesta);
        },
        error: function (xhr, status) {
            console.log(xhr);
            console.log(status);
            alert('ha sucedido un problema');
        }
    });
};


var getAllComputer = function (idComputer) {
    $.ajax({
        url: urlBaseComputer + "/all",
        type: 'GET',
        dataType: 'json',
        success: function (respuesta) {
            var select = `<select class="form-select" id="computer">`;
            for (var i = 0; i < respuesta.length; i++) {
                select += `<option value="${respuesta[i].id}">${respuesta[i].name}</option>`;
            }
            select += `</select>`;
            $("#computer-select").html(select);

            if (idComputer !== 'undefined' && idComputer !== null) {
                $("#computer").val(idComputer);
            }

        },
        error: function (xhr, status) {
            console.log(xhr);
            console.log(status);
            alert('ha sucedido un problema');
        }
    });
};

var getAllClient = function (idClient) {
    $.ajax({
        url: urlBaseClient + "/all",
        type: 'GET',
        dataType: 'json',
        success: function (respuesta) {
            var select = `<select class="form-select" id="client">`;
            for (var i = 0; i < respuesta.length; i++) {
                select += `<option value="${respuesta[i].idClient}">${respuesta[i].name}</option>`;
            }
            select += `</select>`;
            $("#client-select").html(select);

            if (idClient !== 'undefined' && idClient !== null) {
                $("#client").val(idClient);
            }

        },
        error: function (xhr, status) {
            console.log(xhr);
            console.log(status);
            alert('ha sucedido un problema');
        }
    });
};

var updateTable = function (items) {
    var tabla = `<table class="table striped">
                  <tr>
                    <th>ID</th>
                    <th>FECHA INICIO</th>
                    <th>FECHA DEVOLUCIÓN</th>
                    <th>ESTADO</th>
                    <th>CUATRIMOTO</th>
                    <th>CLIENTE</th>
                    <th>PUNTAJE</th>
                    <th>ACCIONES</th>
                  </tr>`;


    for (var i = 0; i < items.length; i++) {
        tabla += `<tr>
                   <td>${items[i].idReservation}</td>
                   <td>${items[i].startDate}</td>
                   <td>${items[i].devolutionDate}</td>
                   <td>${items[i].status}</td>
                   <td>${items[i].computer.name}</td>
                   <td>${items[i].client.name}</td>
                   <td>${items[i].score}</td>
        
                   <td>
                    <button type="button" class="btn btn-sm btn-primary" onclick="edit(${items[i].idReservation}, '${items[i].startDate}', '${items[i].devolutionDate}', '${items[i].status}', '${items[i].computer.id}', '${items[i].client.idClient}', '${items[i].score}')">
                        Editar
                    </button>
                    <button type="button" class="btn btn-sm btn-danger" onclick="deleteReservation(${items[i].idReservation})">
                        Eliminar
                    </button>
                   </td>
                </tr>`;
    }
    tabla += `</table>`;

    $("#tabla").html(tabla);
};

$(document).ready(function () {
    console.log("document ready");
    getAll();
});

var addReservation = function () {
    $("#tituloModalReservacion").html('Nueva Reservación');
    getAllComputer(null);
    getAllClient(null);
    $("#idReservation").val('');
    $("#startDate").val('');
    $("#devolutionDate").val('');
    $("#status").val('');
    $("#score").val('');
    $('#modalReservacion').modal('show');
};

var closeModal = function () {
    $('#modalReservacion').modal('hide');
};

var showMenssage = function (mensaje) {
    $("#mensaje").html(mensaje);
    $('#modalMensaje').modal('show');
};

var closeModalMessage = function () {
    $('#modalMensaje').modal('hide');
};

var updateChanges = function () {
    var payload;
    var method;
    var idReservation = $("#idReservation").val();
    var msg;
    var ruta;
    if (idReservation !== 'undefined' && idReservation !== null && idReservation.length > 0) {
        ruta = urlBaseReservacion + "/update";
        payload = {
            idReservation: +$("#idReservation").val(),
            startDate: $("#startDate").val(),
            devolutionDate: $("#devolutionDate").val(),
            status: $("#status").val(),
            score: $("#score").val(),
            client: {
                idClient: +$("#client").val()
            },
            computer: {
                id: +$("#computer").val()
            }
        };
        method = "PUT";
        msg = "Se ha actualizado la categoria";
    } else {
        ruta = urlBaseReservacion + "/save";
        payload = {
            startDate: $("#startDate").val(),
            devolutionDate: $("#devolutionDate").val(),
            status: $("#status").val(),
            score: $("#score").val(),
            client: {
                idClient: +$("#client").val()
            },
            computer: {
                id: +$("#computer").val()
            }

        };
        method = "POST";
        msg = "Se ha creado la categoria";
    }

    if ($("#startDate").val() === '' ||
            $("#devolutionDate").val() === '' ||
            $("#score").val() === '' ||
            $("#status").val() === '' ||
            $("#client").val() === '' ||
            $("#computer").val() === '') {

        showMenssage('Todos los campos son obligatorios!!');
    } else {

        console.log("guardando ", payload)
        console.log("ruta ", ruta)
        console.log("method ", method)

        $.ajax({
            url: ruta,
            type: method,
            dataType: 'json',
            headers: {
                "Content-Type": "application/json"
            },
            data: JSON.stringify(payload),
            statusCode: {
                201: function () {
                    showMenssage(msg);
                    closeModal();
                    getAll();
                }
            }
        });
    }
    ;
};

var edit = function (idReservation, startDate, devolutionDate, status, idComputer, idCliente, score) {
    $("#tituloModalReservacion").html('Actualizar Reservacion');
    getAllComputer(idComputer);
    getAllClient(idCliente);
    var listaFechaInicio = startDate.split("T");
    var listaFechaDevo = startDate.split("T");
    $("#idReservation").val(idReservation);
    $("#startDate").val( new Date(listaFechaInicio[0]));
    $("#devolutionDate").val( new Date(listaFechaDevo[0]));
    $("#status").val(status);
    $("#score").val(score);
    $('#modalReservacion').modal('show');
};

var deleteReservation = function (id) {
    console.log("eliminando id: " + id);
    $.ajax({
        url: urlBaseReservacion + "/" + id,
        type: 'DELETE',
        dataType: 'json',
        headers: {
            "Content-Type": "application/json"
        },
        statusCode: {
            204: function () {
                showMenssage('Se ha eliminado la categoria');
                closeModal();
                getAll();
            }
        }
    });
};
$.get("/user", function (data) {
    console.log("get");
    console.log(data);
    $("#user").html(data.name);
    $(".unauthenticated").hide();
    $(".authenticated").show();
});


