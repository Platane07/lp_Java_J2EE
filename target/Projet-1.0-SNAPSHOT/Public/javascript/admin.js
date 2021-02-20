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
