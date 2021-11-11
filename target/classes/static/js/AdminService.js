var urlBaseAdmin = "/api/Admin";

var getAll = function () {
    $.ajax({
        url: urlBaseAdmin + "/all",
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
                    <th>ACCIONES</th>
                  </tr>`;


    for (var i = 0; i < items.length; i++) {
        tabla += `<tr>
                   <td>${items[i].id}</td>
                   <td>${items[i].name}</td>
                    <td>${items[i].email}</td>
                   <td>
                    <button type="button" class="btn btn-sm btn-primary" onclick="edit(${items[i].id}, '${items[i].name}', '${items[i].email}')">
                        Editar
                    </button>
                    <button type="button" class="btn btn-sm btn-danger" onclick="deleteAdmin(${items[i].id})">
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

var addAdmin = function () {
    $("#tituloModalAdmin").html('Nuevo Admin');
    $("#id").val('');
    $("#name").val('');
    $("#email").val('');
    $("#password").val('');
    $('#modalAdmin').modal('show');
};

var closeModal = function () {
    $('#modalAdmin').modal('hide');
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
    var id = $("#id").val();
    var msg;
    var ruta;
    if (id !== 'undefined' && id !== null && id.length > 0) {
        ruta = urlBaseAdmin + "/update";
        payload = {
            id: +$("#id").val(),
            name: $("#name").val(),
            email: $("#email").val(),
            password: $("#password").val()
        };
        method = "PUT";
        msg = "Se ha actualizado el administrador";
    } else {
        ruta = urlBaseAdmin + "/save";
        payload = {
            name: $("#name").val(),
            email: $("#email").val(),
            password: $("#password").val()
        };
        method = "POST";
        msg = "Se ha creado el administrador";
    }

    if($("#name").val() === '' ||
        $("#email").val() === '' ||
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
    };
    
};

var edit = function (id, name, email, password) {
    $("#tituloModalAdmin").html('Actualizar Admin');
    $("#id").val(id);
    $("#name").val(name);
    $("#email").val(email);
    $("#password").val(password);
    $('#modalAdmin').modal('show');
};

var deleteAdmin = function (id) {
    console.log("eliminando id: " + id);
    $.ajax({
        url: urlBaseAdmin + "/" + id,
        type: 'DELETE',
        dataType: 'json',
        headers: {
            "Content-Type": "application/json"
        },
        statusCode: {
            204: function () {
                showMenssage('Se ha eliminado el Admin');
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


