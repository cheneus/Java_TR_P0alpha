document.addEventListener('DOMContentLoaded', function () {
    var elems = document.querySelectorAll('.sidenav');
    var instances = M.Sidenav.init(elems);
    // var instances = M.Sidenav.init(elems, options);
    var elemsM = document.querySelectorAll('.modal');
    var instancesM = M.Modal.init(elemsM);
    // var instancesM = M.Modal.init(elemsM, options);
});