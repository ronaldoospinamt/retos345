var urlBaseComputer = "/api/Computer";
var urlBaseCategoria = "/api/Category";



var getAll = function () {
    $.ajax({
        url: urlBaseComputer + "/all",
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

var getAllCategory = function (idCategory) {
    $.ajax({
        url: urlBaseCategoria + "/all",
        type: 'GET',
        dataType: 'json',
        success: function (respuesta) {
            var select = `<select class="form-select" id="idCategory">`;
            for (var i = 0; i < respuesta.length; i++) {
                select += `<option value="${respuesta[i].id}">${respuesta[i].name}</option>`;
            }
            select += `</select>`;
            $("#category-select").html(select);
            console.log(idCategory);
            if (idCategory !== 'undefined' && idCategory !== null) {
                $("#idCategory").val(idCategory);
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
                    <th>NOMBRE</th>
                    <th>MARCA</th>
                    <th>AÑO</th>
                    <th>CATEGORIA</th>
                    <th>DESCRIPCIÓN</th>
                    <th>ACCIONES</th>
                  </tr>`;


    for (var i = 0; i < items.length; i++) {
        tabla += `<tr>
                   <td>${items[i].id}</td>
                   <td>${items[i].name}</td>
                    <td>${items[i].brand}</td>
                    <td>${items[i].year}</td>
                    <td>${items[i].category === null ? "Vacio" : items[i].category.name}</td>
                   <td>${items[i].description}</td>
                   <td>
                    <button type="button" class="btn btn-sm btn-primary" onclick="edit(${items[i].id}, '${items[i].name}', '${items[i].brand}', '${items[i].year}', '${items[i].category === null ? 0 : items[i].category.id}', '${items[i].description}')">
                        Editar
                    </button>
                    <button type="button" class="btn btn-sm btn-danger" onclick="deleteComputer(${items[i].id})">
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

var addComputer = function () {
    console.log("nuevo pc");
    getAllCategory(null);
    $("#tituloModalComputer").html('Nuevo computador');
    $("#id").val('');
    $("#name").val('');
    $("#decription").val('');
    $('#modalComputer').modal('show');
};

var closeModal = function () {
    $('#modalComputador').modal('hide');
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
        ruta = urlBaseComputer + "/update";
        payload = {
            id: +$("#id").val(),
            name: $("#name").val(),
            brand: $("#brand").val(),
            year: +$("#year").val(),
            category: {
                id: +$("#idCategory").val()
            },
            description: $("#description").val()
        };
        method = "PUT";
        msg = "Se ha actualizado el computador";
    } else {
        ruta = urlBaseComputer + "/save";
        payload = {
            name: $("#name").val(),
            brand: $("#brand").val(),
            year: +$("#year").val(),
            category: {
                id: +$("#idCategory").val()
            },
            description: $("#description").val()
        };
        method = "POST";
        msg = "Se ha creado el computador";
    }

    if ($("#name").val() === '' ||
            $("#brand").val() === '' ||
            $("#description").val() === '' ||
            $("#idCategory").val() === '' ||
            $("#year").val() === '') {

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

var edit = function (id, name, brand, year, idCategory, description) {

    
    $("#tituloModalComputer").html('Actualizar Computador');
    getAllCategory(null);
    $("#id").val(id);
    $("#name").val(name);
    $("#brand").val(brand);
    $("#year").val(year);
    $("#description").val(description);
    $('#modalComputer').modal('show');
};

var deleteComputer = function (id) {
    console.log("eliminando id: " + id);
    $.ajax({
        url: urlBaseComputer + "/" + id,
        type: 'DELETE',
        dataType: 'json',
        headers: {
            "Content-Type": "application/json"
        },
        statusCode: {
            204: function () {
                showMenssage('Se ha eliminado el computador');
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


