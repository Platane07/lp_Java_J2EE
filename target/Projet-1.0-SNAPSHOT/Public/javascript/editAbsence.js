function deleteAbsence(idAbsence, el, ind) {

    $.ajax({
        url: urlDeleteAbsence,
        type: 'post',
        dataType: 'json',
        data: {
            idAbsence: idAbsence,
        },
        success: function () {
            console.log("succès de la requête ajax");
            el.parentElement.parentElement.remove();
            ind = 0;
        },

        error: function (error) {
            console.log("erreur de la requête ajax");
            el.parentElement.parentElement.remove();
            ind = 0;
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
            success: function () {
                console.log("succès de la requête ajax");
                cellDebut.parentNode.innerHTML = cellDebut.value;
                console.log(cellDebut.value);
                cellFin.parentNode.innerHTML = cellFin.value;
                cellDebut.parentNode.innerHTML = cellJustifie.value === "true" ? "oui" : "non";
                el.setAttribute("onClick", "updateAbsence(" + idAbsence + ",this)");
                el.innerHTML = "Modifier";
            },

            error: function (error) {
                console.log("erreur de la requête ajax");
                cellDebut.parentNode.innerHTML = cellDebut.value;
                console.log(cellDebut.value);
                cellFin.parentNode.innerHTML = cellFin.value;
                console.log(cellJustifie.parentNode);
                if(cellJustifie.checked === true) {
                    cellJustifie.parentNode.innerHTML = "oui";
                } else {
                    cellJustifie.parentNode.innerHTML = "non";
                }
                console.log("4");
                el.setAttribute("onClick", "updateAbsence(" + idAbsence + ",this)");
                el.innerHTML = "Modifier";
            }
        })

    }
        /*console.log("editNotes");
        const cell = document.getElementById(idEtudiant + '' + idModule);
        console.log(idEtudiant+''+idModule);
        const note = cell.innerText;
        console.log(note);
        cell.innerHTML = '';
        let button = document.createElement("input");
        button.setAttribute("type", "button");
        button.setAttribute('value', 'Valider');
        if (note == '') {
            button.setAttribute('onClick', 'createNote(' + idEtudiant + ',' + idModule +')');
        } else {
            button.setAttribute('onClick', 'updateNote(' + idEtudiant + ',' + idModule + ')');
        }
        let input = document.createElement("input");
        input.setAttribute("value", note);
        input.setAttribute("type", "number");
        input.setAttribute("min", 0);
        input.setAttribute("max", 20);
        cell.appendChild(input);
        cell.appendChild(button);
        cell.removeAttribute("onclick");*/