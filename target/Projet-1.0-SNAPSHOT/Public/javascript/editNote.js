
    console.log("allo");

    // Remplacement de la note par un champ input pour permettre d'éditer la note
    function editNote(idEtudiant, idModule){
        const cell = document.getElementById(idEtudiant + '' + idModule);
        const note = cell.innerText;
        cell.innerHTML = '';
        let button = document.createElement("input");
        button.setAttribute("type", "button");
        button.setAttribute('value', 'Valider');


        //Si la cellule de la note était vide au départ, passe par la route pour créer une note dans la base de données, sinon on update juste la note
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
        cell.removeAttribute("onclick");
    }


    //Function onclick qui sert à modifier une note déjà existante
    function updateNote(idEtudiant, idModule){
        const cell = document.getElementById(idEtudiant + '' + idModule);

        //Si la cellule est vide au moment de la validation, envoie vers la route pour supprimer la note de la base de données
        if (cell.children[0].value) {
            $.ajax({
                url: urlEditNote,
                type: 'post',
                dataType: 'json',
                data: {
                    idEtudiant: idEtudiant,
                    idModule: idModule,
                    value: cell.children[0].value,
                },
                success: function (data) {
                    console.log("succès de la requête ajax"+ data);
                    cell.innerHTML = cell.children[0].value;
                    cell.setAttribute("onClick", 'editNote(' + idEtudiant + ',' + idModule + ')');
                    cell.setAttribute("class", "note");
                },

                error: function (error) {
                    console.log("erreur lors de la modification de la note");
                }
            })
        } else {
            deleteNote(idEtudiant, idModule);
        }
    }

    //Fonction qui permet de créer la note dans la base de données
    function createNote(idEtudiant, idModule){
        const cell = document.getElementById(idEtudiant + '' + idModule);
        cell.removeAttribute("onClick");

        $.ajax({
            url: urlCreateNote,
            type: 'post',
            dataType: 'json',
            data: {
                idEtudiant : idEtudiant,
                idModule : idModule,
                value: cell.children[0].value,
            },
            success: function (data) {
                console.log("succès de la requête ajax"+data);
                cell.innerHTML = cell.children[0].value;
                cell.setAttribute("onClick", 'editNote(' + idEtudiant + ',' + idModule +')');
                cell.setAttribute("class", "note");
            },

            error: function (error) {
                console.log("erreur de la requête ajax");
            }
        })
    }

    // Suppression d'une note en laissant le champ vide
    function deleteNote(idEtudiant, idModule){
        console.log("deleteNote");
        const cell = document.getElementById(idEtudiant + '' + idModule);
        cell.removeAttribute("onClick");

        $.ajax({
            url: urlDeleteNote,
            type: 'post',
            dataType: 'json',
            data: {
                idEtudiant : idEtudiant,
                idModule : idModule,
            },
            success: function () {
                console.log("succès de la requête ajax");
                cell.innerHTML = '';
                cell.setAttribute("onClick", 'editNote(' + idEtudiant + ',' + idModule +')');
                cell.setAttribute("class", "note");
            },

            error: function (error) {
                console.log("erreur de la requête ajax");
            }
        })

    }