document.addEventListener('DOMContentLoaded', function() {
  var elems = document.querySelectorAll('.sidenav');
  var instances = M.Sidenav.init(elems);
  // var instances = M.Sidenav.init(elems, options);
  var elemsM = document.querySelectorAll('.modal');
  var instancesM = M.Modal.init(elemsM);
  // var instancesM = M.Modal.init(elemsM, options);
  var elemsF = document.querySelectorAll('select');
  var instancesF = M.FormSelect.init(elemsF);
  // var instancesF = M.FormSelect.init(elems, options);
  var elemsC = document.querySelectorAll('.datepicker');
  // var instancesC = M.Datepicker.init(elemsC, options);
  var instancesC = M.Datepicker.init(elemsC, {
    autoClose: 'true',
    format: 'dd-mmm-yy'
  });
});

$(document).ready(function() {
  // $('#loginPg').hide();
  // $('#slide-out').hide();
  // $('#slide-out-icon').hide();
  // $('#mainPg').hide();

  // $('select').material_select();

  $(document).on('click', '#trCheck', function(event) {
    var tag = $(this).data('trid');
    var status =$(this).data('trstat');
    console.log($(this).data('trid'));
    utils.approvedTRF(tag, status);
  });
  $('#loginBtn').on('click', function() {
    utils.userLogin();
    console.log(`loggged = ${currentUser} `);
  });

  var objJsonInit;
  $('#navLogout').click(function() {
    console.log(`logging out`);
    utils.logOut();
  });
  $('#sidebar_add').click(function() {
    var elemsF = document.querySelectorAll('select');
    var instancesF = M.FormSelect.init(elemsF);
  });

  $('#sidebar_viewmine').click(function() {
    utils.getMyTRF(currentUser.id);
  });

  $('#sidebar_viewall').click(function() {
    utils.getTRFmgr(currentUser.id, currentUser.employee_id.dept_id.id);
  });

  $('#sidebar_viewNA').on('click', function() {
    utils.getTRFmgrSI(currentUser.id, currentUser.employee_id.dept_id.id);
  });
  $('#sidebar_my').on('click', function() {
    utils.getAddress(currentUser.employee_id.address.id)
  });

  $('#addNewTR').submit(function() {
    // goes second
  });
});

var formtest = function() {
  alert('test');
};
