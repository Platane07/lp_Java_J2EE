
    console.log("allo");

    // On ajoute un événement au bouton refresh
    function editNote(idEtudiant, idModule){
        console.log("editNotes");
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
        cell.removeAttribute("onclick");
    }

    function updateNote(idEtudiant, idModule){
        const cell = document.getElementById(idEtudiant + '' + idModule);

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
                },

                error: function (error) {
                    console.log("erreur lors de la modification de la note");
                }
            })
        } else {
            deleteNote(idEtudiant, idModule);
        }


    }

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
            },

            error: function (error) {
                console.log("erreur de la requête ajax");
            }
        })

      //  cell.setAttribute("onClick", 'editNote(' + idEtudiant + ',' + idModule +')');
    }

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
            },

            error: function (error) {
                console.log("erreur de la requête ajax");
                cell.innerHTML = '';
                cell.setAttribute("onClick", 'editNote(' + idEtudiant + ',' + idModule +')');
            }
        })

    }

        //
       /* $.ajax({
            url: urlgetetudiants,       // url créée dans le fichier etudiants.jsp
            type: 'post',
            dataType: 'json',
            data: {test: "testajax"},   // Donnée envoyée au serveur

            success: function (data) {    // Donnée reçue du serveur
                console.log("Succès de la requête ajax");

                var textedJson = "<div>" + JSON.stringify(data) + "</div>";
                $("#etudiants").append(textedJson);
            },

            error: function (error) {
                console.log("Erreur de la requête ajax");
            }
        });*/
