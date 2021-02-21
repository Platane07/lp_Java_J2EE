
//Utilisée pour récupérer les champs d'un select multiple et les assigner à un autre champ select qui enverra ses values au controlleur
function populateSelect(el){
    const id = el.value;
    const nom = el.innerHTML;

    let addSelect = el.parentNode.parentNode.lastChild;

    let option = document.createElement("option");
    option.value = id;
    option.text = nom ;
    option.selected = true;
    option.setAttribute("onClick", "depopulateSelect(this)");

    addSelect.appendChild(option);
    el.remove();
}


//function appelée qui fait l'inverse de celle d'avant, elle enlève une value et la remet en non selectionnée dans le select précedent
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



//Fonction qui sert à supprimer un groupe (il y en a une multitude, pas moyen de faire passer l'url directement dans un onClick
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


//Suppression d'un module (pareil mais différent)
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

//Suppression d'un étudiant
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


//suppression d'un module d'un groupe
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


//suppression d'un étudiant d'un groupe
function deleteEtudiantOfGroupe(idEtudiant, idGroupe, el) {
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



//Suppression d'un groupe d'un module
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