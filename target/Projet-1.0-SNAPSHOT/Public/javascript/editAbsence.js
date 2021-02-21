function deleteAbsence(idAbsence, el, ind) {

    $.ajax({
        url: urlDeleteAbsence,
        type: 'post',
        dataType: 'json',
        data: {
            idAbsence: idAbsence,
        },
        success: function (data) {
            console.log("succès de la suppression de l'absence");
            el.parentElement.parentElement.remove();
            ind = 0;
        },

        error: function (error) {
           console.log("erreur de la requête, l'absence n'a pas pu être supprimée");
        }
    })
}


    function updateAbsence(idAbsence, el) {

        var row = el.parentNode.parentNode;

        var cellDebut = row.cells[1];
        console.log(cellDebut);
        var cellFin = row.cells[2];
        console.log(cellFin);
        var cellJustifie = row.cells[3];
        console.log(cellJustifie);

        var debut = cellDebut.innerHTML;
        var fin = cellFin.innerHTML;
        var justifie = cellJustifie.innerHTML;
        console.log(justifie);


        cellDebut.innerHTML = "<input type=\"datetime-local\" placeholder=\"Date de début\"/>";
        cellDebut.firstChild.value = debut;
        cellFin.innerHTML = "<input type=\"datetime-local\" placeholder=\"Date de fin\"/>";
        cellFin.firstChild.value = fin;
        if (justifie === "oui" ) {
            cellJustifie.innerHTML = "<input type=\"checkbox\" value=\"true\" checked/>";
        } else {
            cellJustifie.innerHTML = "<input type=\"checkbox\" value=\"true\"/>";
        }
        console.log("1");

        el.setAttribute("onClick", "validerAbsence(" + idAbsence + ",this)");
        el.innerHTML = "Valider";
        console.log("1");


    }

function validerAbsence(idAbsence, el) {
        console.log("2");

        var row = el.parentNode.parentNode;

        var cellDebut = row.cells[1].firstChild;
        console.log(cellDebut);
        var cellFin = row.cells[2].firstChild;
        console.log(cellFin);
        var cellJustifie = row.cells[3].firstChild;
        console.log("justifie.value");
        console.log(cellJustifie.value);

        $.ajax({
            url: urlUpdateAbsence,
            type: 'post',
            dataType: 'json',
            data: {
                debut: cellDebut.value,
                fin: cellFin.value,
                justifie: cellJustifie.checked === true ? "true" : "false",
                idAbsence: idAbsence,
            },
            success: function (data) {
                console.log("succès de la requête ajax, modification de l'absence : " + data);
                cellDebut.parentNode.innerHTML = cellDebut.value;
                cellFin.parentNode.innerHTML = cellFin.value;
                cellJustifie.parentNode.innerHTML = cellJustifie.checked === true ? "oui" : "non";
                el.setAttribute("onClick", "updateAbsence(" + idAbsence + ",this)");
                el.innerHTML = "Modifier";
            },

            error: function (error) {
                console.log("erreur de la requête ajax");

            }
        })

    }