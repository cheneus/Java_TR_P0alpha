// document.addEventListener('DOMContentLoaded', function () {
  
//     // var instancesM = M.Modal.init(elemsM, options);
// });

$(document).ready(function() {
    // $('#loginPg').hide();
    $('#slide-out').hide();
    $('#slide-out-icon').hide();

    var elems = document.querySelectorAll('.sidenav');
    var instances = M.Sidenav.init(elems);
    // var instances = M.Sidenav.init(elems, options);
    var elemsM = document.querySelectorAll('.modal');
    var instancesM = M.Modal.init(elemsM);

})