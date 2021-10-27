function myFun(checkbox) {
    var checkboxes = document.getElementsByName('gender');

    checkboxes.forEach(item => {
        if (item !== checkbox) {
            item.checked = false;
        }
    })
}