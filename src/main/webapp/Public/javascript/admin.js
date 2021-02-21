function populateSelect(el){
    const id = el.value;
    const nom = el.innerHTML;

    let addSelect = el.parentNode.parentNode.lastChild;

    console.log(addSelect);

    var option = document.createElement("option");
    option.value = id;
    option.text = nom ;
    option.selected = true;
    option.setAttribute("onClick", "depopulateSelect(this)");

    addSelect.appendChild(option);
    el.remove();
}

function depopulateSelect(el){
    const id = el.value;
    const nom = el.innerHTML;

    let select = el.parentNode.parentNode.firstChild;
    let option = document.createElement("option");
    option.value = id;
    option.text = nom ;
    option.setAttribute("onClick", "populateSelect(this)");

    select.appendChild(option);
    el.remove();

}

function deleteGroupe(id, el, url){
        $.ajax({
            url: urlDeleteGroupe,
            type: 'post',
            dataType: 'json',
            data: {
                id: id,
            },
            success: function (data) {
                console.log("succès de la requête ajax");
                el.parentElement.parentElement.remove();
            },

            error: function (error) {
                console.log("erreur de la requête ajax");
            }
        })
}

function deleteModule(id, el){
    $.ajax({
        url: urlDeleteModule,
        type: 'post',
        dataType: 'json',
        data: {
            id: id,
        },
        success: function (data) {
            console.log("succès de la requête ajax");
            el.parentElement.parentElement.remove();
        },

        error: function (error) {
            console.log("erreur de la requête ajax");
        }
    })
}

function deleteEtudiant(id, el){
    $.ajax({
        url: urlDeleteEtudiant,
        type: 'post',
        dataType: 'json',
        data: {
            id: id,
        },
        success: function (data) {
            console.log("succès de la requête ajax");
            el.parentElement.parentElement.remove();
        },

        error: function (error) {
            console.log("erreur de la requête ajax");
        }
    })
}

function deleteModuleOfGroupe(idModule, idGroupe, el) {
    $.ajax({
        url: urlDeleteModuleOfGroupe,
        type: 'post',
        dataType: 'json',
        data: {
            idModule: idModule,
            idGroupe: idGroupe,
        },
        success: function (data) {
            console.log("succès de la requête ajax");
            el.parentElement.parentElement.remove();
        },

        error: function (error) {
            console.log("erreur de la requête ajax");
        }
    })
}

function deleteEtudiantOfGroupe(idEtudiant, idGroupe, el) {
    console.log(idEtudiant);
    console.log(idGroupe);
    $.ajax({
        url: urlDeleteEtudiantOfGroupe,
        type: 'post',
        dataType: 'json',
        data: {
            idEtudiant: idEtudiant,
            idGroupe: idGroupe,
        },
        success: function (data) {
            console.log("succès de la requête ajax");
            el.parentElement.parentElement.remove();
        },

        error: function (error) {
            console.log("erreur de la requête ajax");
        }
    })
}

function deleteGroupeOfModule(idGroupe, idModule, el) {
    console.log(idModule);
    console.log(idGroupe);
    $.ajax({
        url: urlDeleteGroupeOfModule,
        type: 'post',
        dataType: 'json',
        data: {
            idGroupe: idGroupe,
            idModule: idModule,
        },
        success: function (data) {
            console.log("succès de la requête ajax");
            el.parentElement.parentElement.remove();
        },

        error: function (error) {
            console.log("erreur de la requête ajax");
        }
    })
}