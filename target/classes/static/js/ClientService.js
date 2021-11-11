var urlBaseCliente = "/api/Client";

var getAll = function () {
    $.ajax({
        url: urlBaseCliente + "/all",
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

var updateTable = function (items) {
    var tabla = `<table class="table striped">
                  <tr>
                    <th>ID</th>
                    <th>NOMBRE</th>
                    <th>EMAIL</th>
                    <th>EDAD</th>
                    <th>ACCIONES</th>
                  </tr>`;


    for (var i = 0; i < items.length; i++) {
        tabla += `<tr>
                   <td>${items[i].idClient}</td>
                   <td>${items[i].name}</td>
                   <td>${items[i].email}</td>
                   <td>${items[i].age}</td>
                   <td>
                    <button type="button" class="btn btn-sm btn-primary" onclick="edit(${items[i].idClient}, '${items[i].name}', '${items[i].email}', '${items[i].age}')">
                        Editar
                    </button>
                    <button type="button" class="btn btn-sm btn-danger" onclick="deleteClient(${items[i].idClient})">
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

var addClient = function () {
    $("#tituloModalClient").html('Nuevo Cliente');
    $("#idClient").val('');
    $("#name").val('');
    $("#email").val('');
    $("#age").val('');
    $('#modalClient').modal('show');
};

var closeModal = function () {
    $('#modalClient').modal('hide');
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
    var idClient = $("#idClient").val();
    var msg;
    var ruta;
    if (idClient !== 'undefined' && idClient !== null && idClient.length > 0) {
        ruta = urlBaseCliente + "/update";
        payload = {
            idClient: +$("#idClient").val(),
            name: $("#name").val(),
            email: $("#email").val(),
            age: +$("#age").val(),
            password: $("#password").val()
        };
        method = "PUT";
        msg = "Se ha actualizado el cliente";
    } else {
        ruta = urlBaseCliente + "/save";
        payload = {
            name: $("#name").val(),
            email: $("#email").val(),
            age: +$("#age").val(),
            password: $("#password").val()
        };
        method = "POST";
        msg = "Se ha creado el cliente";
    }

    if ($("#name").val() === '' ||
            $("#email").val() === '' ||
            $("#age").val() === '' || $("#email").val() < 0 ||
            $("#password").val() === '') {

        showMenssage('Todos los campos son obligatorios!!');

    } else {
        console.log("guardando ", payload);
        console.log("ruta ", ruta);
        console.log("method ", method);

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
};

var edit = function (idClient, name, email, age) {
    $("#tituloModalClient").html('Actualizar Cliente');
    $("#idClient").val(idClient);
    $("#name").val(name);
    $("#email").val(email);
    $("#age").val(age);
    $('#modalClient').modal('show');
};

var deleteClient = function (idClient) {
    console.log("eliminando idClient: " + idClient);
    $.ajax({
        url: urlBaseCliente + "/" + idClient,
        type: 'DELETE',
        dataType: 'json',
        headers: {
            "Content-Type": "application/json"
        },
        statusCode: {
            204: function () {
                showMenssage('Se ha eliminado el cliente');
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


