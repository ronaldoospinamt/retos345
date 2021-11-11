var urlBaseReservacion = "/api/Reservation";

function traerReporteStatus() {
    console.log("test");
    $.ajax({
        url: urlBaseReservacion + "/report-status",
        type: "GET",
        datatype: "JSON",
        success: function (respuesta) {
            console.log(respuesta);
            pintarRespuesta(respuesta);
        }
    });
}
function pintarRespuesta(respuesta) {

    let myTable = "<table>";
    myTable += "<tr>";
    myTable += "<td>Completadas: </td>";
    myTable += "<th>" + respuesta.completed + "</th>";
    myTable += "</tr>";
    myTable += "<tr>";
    myTable += "<td>  Canceladas :</td>";
    myTable += "<th> " + respuesta.cancelled + "</th>";
    myTable += "</tr>";
    myTable += "</table>";
    $("#resultadoStatus").html(myTable);
}

function traerReporteDate() {

    var fechaInicio = document.getElementById("RstarDate").value;
    var fechaCierre = document.getElementById("RdevolutionDate").value;
    if (fechaInicio === "" || fechaCierre === "") {
        window.alert("Ingrese fechas");
    } else {

        $.ajax({
            url: urlBaseReservacion + "/report-dates/" + fechaInicio + "/" + fechaCierre,
            type: "GET",
            datatype: "JSON",
            success: function (respuesta) {
                console.log(respuesta);
                updateTable(respuesta);
            }
        });
    }
}

var updateTable = function (items) {
    var tabla = `<table class="table striped">
                  <tr>
                    <th>ID</th>
                    <th>FECHA INICIO</th>
                    <th>FECHA DEVOLUCIÓN</th>
                    <th>ESTADO</th>
                    <th>COMPUTADOR</th>
                    <th>CLIENTE</th>
                  </tr>`;


    for (var i = 0; i < items.length; i++) {
        tabla += `<tr>
                   <td>${items[i].idReservation}</td>
                   <td>${items[i].startDate}</td>
                   <td>${items[i].devolutionDate}</td>
                   <td>${items[i].status}</td>
                   <td>${items[i].computer.name}</td>
                   <td>${items[i].client.name}</td>
                </tr>`;
    }
    tabla += `</table>`;

    $("#tabla").html(tabla);
};

function traerReporteClientes() {
    $.ajax({
        url: urlBaseReservacion + "/report-clients",
        type: "GET",
        datatype: "JSON",
        success: function (respuesta) {
            console.log(respuesta);
            pintarRespuestaClientes(respuesta);
        }
    });
}
function pintarRespuestaClientes(items) {

    var tabla = `<table class="table striped">
                  <tr>
                    
                    <th>NOMBRE</th>
                    <th>EMAIL</th>
                    <th>EDAD</th>
                    <th>TOTAL</th>
                  </tr>`;


    for (var i = 0; i < items.length; i++) {
        tabla += `<tr>
                   <td>${items[i].client.name}</td>
                   <td>${items[i].client.email}</td>
                   <td>${items[i].client.age}</td>
                    <td>${items[i].total}</td>
                </tr>`;
    }
    tabla += `</table>`;


    $("#resultadoClientes").html(tabla);
}



