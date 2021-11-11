var urlBaseCategoria = "/api/Category";

var getAll = function () {
    $.ajax({
        url: urlBaseCategoria + "/all",
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
}

var updateTable = function (items) {
    var tabla = `<table class="table striped">
                  <tr>
                    <th>ID</th>
                    <th>NOMBRE</th>
                    <th>DESCRIPCIÓN</th>
                    <th>ACCIONES</th>
                  </tr>`;


    for (var i = 0; i < items.length; i++) {
        tabla += `<tr>
                   <td>${items[i].id}</td>
                   <td>${items[i].name}</td>
                   <td>${items[i].description}</td>
                   <td>
                    <button type="button" class="btn btn-sm btn-primary" onclick="edit(${items[i].id}, '${items[i].name}', '${items[i].description}')">
                        Editar
                    </button>
                    <button type="button" class="btn btn-sm btn-danger" onclick="deleteCategory(${items[i].id})">
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

var addCategory = function () {
    $("#tituloModalCategoria").html('Nueva Categoria');
    $("#id").val('');
    $("#name").val('');
    $("#decription").val('');
    $('#modalCategoria').modal('show');
};

var closeModal = function () {
    $('#modalCategoria').modal('hide');
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
        ruta = urlBaseCategoria + "/update";
        payload = {
            id: +$("#id").val(),
            name: $("#name").val(),
            description: $("#description").val()
        };
        method = "PUT";
        msg = "Se ha actualizado la categoria";
    } else {
        ruta = urlBaseCategoria + "/save";
        payload = {
            name: $("#name").val(),
            description: $("#description").val(),
        };
        method = "POST";
        msg = "Se ha creado la categoria";
    }

    if ($("#name").val() === '' ||
            $("#description").val() === '') {

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
};

var edit = function (id, name, description) {
    $("#tituloModalCategoria").html('Actualizar Categoria');
    $("#id").val(id);
    $("#name").val(name);
    $("#description").val(description);
    $('#modalCategoria').modal('show');
};

var deleteCategory = function (id) {
    console.log("eliminando id: " + id);
    $.ajax({
        url: urlBaseCategoria + "/" + id,
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


